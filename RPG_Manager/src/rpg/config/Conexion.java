package rpg.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/rpg_manager";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Coloca tu contraseña de MySQL si usas una

    public static Connection getConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("⚠️ Driver de MySQL no encontrado.");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}