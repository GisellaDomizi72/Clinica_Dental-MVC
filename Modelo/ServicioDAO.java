package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ServicioDAO extends BaseDAO<Servicio> {

    public List<Servicio> listarServicios() {
        String sql = "SELECT * FROM servicios";
        return listar(sql, rs -> {
            Servicio s = new Servicio();
            s.setId_servicio(rs.getInt("id_servicio"));
            s.setNombre(rs.getString("nombre_s"));
            s.setPrecio(rs.getInt("precio_s"));
            s.setDescripcion(rs.getString("descripcion_s"));
            s.setId_dentista(rs.getInt("id_dentista"));
            return s;
        });
    }

    public int agregar(Servicio servis) {
        String sql = "INSERT INTO servicios (nombre_s, precio_s, descripcion_s, id_dentista) VALUES (?, ?, ?, ?)";
        return ejecutarActualizacion(sql, ps -> {
            ps.setString(1, servis.getNombre());
            ps.setInt(2, servis.getPrecio());
            ps.setString(3, servis.getDescripcion());
            ps.setInt(4, servis.getId_dentista());
        });
    }

    public int actualizar(Servicio servis) {
        String sql = "UPDATE servicios SET nombre_s=?, precio_s=?, descripcion_s=?, id_dentista=? WHERE id_servicio=?";
        return ejecutarActualizacion(sql, ps -> {
            ps.setString(1, servis.getNombre());
            ps.setInt(2, servis.getPrecio());
            ps.setString(3, servis.getDescripcion());
            ps.setInt(4, servis.getId_dentista());
            ps.setInt(5, servis.getId_servicio());
        });
    }

    public boolean eliminarServicio(int id_servicio) {
        String sql = "DELETE FROM servicios WHERE id_servicio=?";
        eliminar(sql, id_servicio);
        return true;
    }
}