/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud_estudiantes;

import java.sql.*;

public class Listar {
    public static void listar() {
        try {
            Connection con = Conexion.conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM dbo.Estudiantes_Tabla");

            while (rs.next()) {
                System.out.println(
                    rs.getString("cc") + " " +
                    rs.getString("nombre") + " " +
                    rs.getString("apellido")
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}