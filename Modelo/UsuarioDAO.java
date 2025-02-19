package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioDAO extends BaseDAO<Usuario> {

    public List<Usuario> listarUsuarios() {
        String sql = "SELECT * FROM usuarios where rol='2'";
        return listar(sql, rs -> {
            Usuario u = new Usuario();
            u.setId_usuario(rs.getInt("id_usuario"));
            u.setNombre(rs.getString("nombre"));
            u.setContrasena(rs.getString("contrasena"));
            u.setRol(rs.getInt("rol"));
            return u;
        });
    }

    public int agregar(Usuario user) {
        String sql = "INSERT INTO usuarios (nombre, contrasena, rol) VALUES (?, ?, 2)";
        return ejecutarActualizacion(sql, ps -> {
            ps.setString(1, user.getNombre());
            // Hashea la contraseña antes de guardarla en la BD
            String hashedPassword = BCrypt.hashpw(user.getContrasena(), BCrypt.gensalt());
            ps.setString(2, hashedPassword);
        });
    }

    public int actualizar(Usuario user) {
        String sql = "UPDATE usuarios SET nombre=?, contrasena=?, rol=? WHERE id_usuario=?";
        return ejecutarActualizacion(sql, ps -> {
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getContrasena());
            ps.setInt(3, user.getRol());
            ps.setInt(4, user.getId_usuario());
        });
    }

    public boolean eliminarUsuario(int id_usuario) {
        String sql = "DELETE FROM usuarios WHERE id_usuario=?";
        eliminar(sql, id_usuario);
        return true;
    }
}
