package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAO<T> {
    protected Conexion conectar = new Conexion(); 
    protected Connection con;
    protected PreparedStatement ps;
    protected ResultSet rs;

    // Método genérico para obtener la conexión
    protected Connection getConnection() throws SQLException {
        return conectar.getConnection();
    }

    // Método genérico para listar elementos
    protected List<T> listar(String sql, RowMapper<T> mapper) {
        List<T> lista = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            System.out.println("error"+e);
        }
        return lista;
    }

    // Método genérico para agregar o actualizar elementos
    protected int ejecutarActualizacion(String sql, SQLConsumer<PreparedStatement> setter) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            setter.accept(ps);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Método genérico para eliminar elementos
    protected void eliminar(String sql, int id) {
        try (Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Interfaz para mapear filas de ResultSet a objetos
    public interface RowMapper<T> {
        T mapRow(ResultSet rs) throws SQLException;
    }

    // Interfaz funcional para preparar sentencias SQL
    public interface SQLConsumer<T> {
        void accept(T t) throws SQLException;
    }
}