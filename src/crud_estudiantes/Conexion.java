package crud_estudiantes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection conectar() {
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Estudiantes;encrypt=true;trustServerCertificate=true";
            String user = "sa";
            String password = "12345";
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}