package rpg.model;

import rpg.config.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonajeDAO {
    public boolean insertar(Personaje p) {
        String sql = "INSERT INTO personajes (nombre, tipo, nivel) VALUES (?, ?, ?)";
        try (Connection con = Conexion.getConexion(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTipo());
            ps.setInt(3, p.getNivel());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
            return false;
        }
    }
    public List<Personaje> listar() {
        List<Personaje> lista = new ArrayList<>();
        String sql = "SELECT * FROM personajes";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Personaje(rs.getInt("id"), rs.getString("nombre"), rs.getString("tipo"), rs.getInt("nivel")));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }
    public List<Personaje> buscarPorNombre(String nombre) {
        List<Personaje> lista = new ArrayList<>();
        String sql = "SELECT * FROM personajes WHERE nombre LIKE ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + nombre + "%"); // Permite coincidencias parciales
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Personaje(rs.getInt("id"), rs.getString("nombre"), rs.getString("tipo"), rs.getInt("nivel")));
                }
            }
        } catch (SQLException e) {
            System.out.println(" Error al buscar: " + e.getMessage());
        }
        return lista;
    }
    public boolean actualizarNivel(int id, int nuevoNivel) {
        String sql = "UPDATE personajes SET nivel = ? WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nuevoNivel);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar nivel: " + e.getMessage());
            return false;
        }
    }
    public boolean eliminar(int id) {
        String sql = "DELETE FROM personajes WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }
}