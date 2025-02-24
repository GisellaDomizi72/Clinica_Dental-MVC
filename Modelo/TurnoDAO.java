package Modelo;

import java.sql.*;
import java.util.List;

public class TurnoDAO extends BaseDAO<Turno> {
    
    public List<Turno> listarTurno() {
        // SQL modificado para incluir el nombre y apellido del paciente, y el nombre del servicio
        String sql = "SELECT t.id_turno, t.hora, t.fecha, p.nombre_p, p.apellido_p, s.nombre_s " +
                     "FROM turnos t " +
                     "INNER JOIN pacientes p ON t.id_paciente = p.id_paciente " +
                     "INNER JOIN servicios s ON t.id_servicio = s.id_servicio";

        return listar(sql, rs -> {
            Turno t = new Turno();
            t.setId_turno(rs.getInt("id_turno"));
            t.setHora(rs.getString("hora"));
            t.setFecha(rs.getString("fecha"));
            // Asignamos el nombre y apellido del paciente
            t.setNombre_paciente(rs.getString("nombre_p") + " " + rs.getString("apellido_p"));
            // Asignamos el nombre del servicio
            t.setNombre_servicio(rs.getString("nombre_s"));
            return t;
        });
    }

    public boolean eliminarTurno(int id_turno) {
        try (Connection con = getConnection())  {
            //Obtengo los datos del turno antes de eliminarlo
            String sqlSelect = "SELECT id_turno, fecha, hora, id_paciente, id_servicio FROM turnos WHERE id_turno = ?";
            PreparedStatement ps = con.prepareStatement(sqlSelect);
            ps.setInt(1, id_turno);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //Guardo el turno eliminado en cancelaciones_turnos
                String sqlInsert = "INSERT INTO tpcancel (fecha, hora, id_paciente, id_servicio) VALUES (?, ?, ?, ?)";
                PreparedStatement psInsert = con.prepareStatement(sqlInsert);
                psInsert.setString(1, rs.getString("fecha"));
                psInsert.setString(2, rs.getString("hora"));
                psInsert.setInt(3, rs.getInt("id_paciente"));
                psInsert.setInt(4, rs.getInt("id_servicio"));
                psInsert.executeUpdate();
                psInsert.close();
            } else {
                return false; // No se encontró el turno, cancelar eliminación
            }

            // Elimino el turno de la tabla turnos
            String deleteSQL = "DELETE FROM turnos WHERE id_turno = ?";
            PreparedStatement psDelete = con.prepareStatement("DELETE FROM turnos WHERE id_turno = ?");
            psDelete.setInt(1, id_turno);
            psDelete.executeUpdate();
            psDelete.close();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
    }
}