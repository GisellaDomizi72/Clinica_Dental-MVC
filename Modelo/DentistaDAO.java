package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DentistaDAO extends BaseDAO<Dentista> {

    public List<Dentista> listarDentistas() {
        String sql = "SELECT * FROM dentistas";
        return listar(sql, rs -> {
            Dentista d = new Dentista();
            d.setId_dentista(rs.getInt("id_dentista"));
            d.setNombre(rs.getString("nombre_d"));
            d.setApellido(rs.getString("apellido_d"));
            d.setTelefono(rs.getString("telefono_d"));
            d.setEmail(rs.getString("email"));
            d.setFoto(rs.getString("foto"));
            d.setExp(rs.getString("experiencia"));
            d.setId_usuario(rs.getInt("id_usuario"));
            return d;
        });
    }

    public int agregar(Dentista dentist) {
        String sql = "INSERT INTO dentistas (nombre_d, apellido_d, telefono_d, email, foto, experiencia, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return ejecutarActualizacion(sql, ps -> {
            ps.setString(1, dentist.getNombre());
            ps.setString(2, dentist.getApellido());
            ps.setString(3, dentist.getTelefono());
            ps.setString(4, dentist.getEmail());
            ps.setString(5, dentist.getFoto());
            ps.setString(6, dentist.getExp());
            ps.setInt(7, dentist.getId_usuario());
        });
    }

    public int actualizar(Dentista dentist) {
        String sql = "UPDATE dentistas SET nombre_d=?, apellido_d=?, telefono_d=?, email=?, foto=?, experiencia=?, id_usuario=? WHERE id_dentista=?";
        return ejecutarActualizacion(sql, ps -> {
            ps.setString(1, dentist.getNombre());
            ps.setString(2, dentist.getApellido());
            ps.setString(3, dentist.getTelefono());
            ps.setString(4, dentist.getEmail());
            ps.setString(5, dentist.getFoto());
            ps.setString(6, dentist.getExp());
            ps.setInt(7, dentist.getId_usuario());
            ps.setInt(8, dentist.getId_dentista());
        });
    }

    public boolean eliminarDentista(int id_dentista) {
        String sql = "DELETE FROM dentistas WHERE id_dentista=?";
        eliminar(sql, id_dentista);
        return true;
    }
}