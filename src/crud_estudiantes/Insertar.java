/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud_estudiantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Insertar {
    public static boolean insertar(String cc, String nombre, String apellido, String correo, String telefono) {
        try {
            Connection con = Conexion.conectar();

            String check = "SELECT * FROM dbo.Estudiantes_Tabla WHERE cc=? OR correo=? OR telefono=?";
            PreparedStatement psCheck = con.prepareStatement(check);
            psCheck.setString(1, cc);
            psCheck.setString(2, correo);
            psCheck.setString(3, telefono);

            ResultSet rs = psCheck.executeQuery();
            if (rs.next()) return false;

            String sql = "INSERT INTO dbo.Estudiantes_Tabla (cc, nombre, apellido, correo, telefono) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, cc);
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            ps.setString(4, correo);
            ps.setString(5, telefono);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}