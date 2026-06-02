package repository;

import model.Empleado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoRepositoryMySQL implements EmpleadoRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/empleados_db";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "tu_password";

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }

    @Override
    public void agregar(Empleado e) {
        String sql = "INSERT INTO empleados (id, nombre, edad, departamento) VALUES (?, ?, ?, ?)";
        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, e.getId());
            ps.setString(2, e.getNombre());
            ps.setInt(3, e.getEdad());
            ps.setString(4, e.getDepartamento());
            ps.executeUpdate();
            System.out.println("Empleado agregado correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al agregar: " + ex.getMessage());
        }
    }

    @Override
    public Empleado buscarPorId(int id) {
        String sql = "SELECT * FROM empleados WHERE id = ?";
        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Empleado e = new Empleado();
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                e.setEdad(rs.getInt("edad"));
                e.setDepartamento(rs.getString("departamento"));
                return e;
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Empleado> listarTodos() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleados";
        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Empleado e = new Empleado();
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                e.setEdad(rs.getInt("edad"));
                e.setDepartamento(rs.getString("departamento"));
                lista.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar: " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizar(Empleado e) {
        String sql = "UPDATE empleados SET nombre=?, edad=?, departamento=? WHERE id=?";
        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, e.getNombre());
            ps.setInt(2, e.getEdad());
            ps.setString(3, e.getDepartamento());
            ps.setInt(4, e.getId());
            ps.executeUpdate();
            System.out.println("Empleado actualizado correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al actualizar: " + ex.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM empleados WHERE id=?";
        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Empleado eliminado correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al eliminar: " + ex.getMessage());
        }
    }
}