/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud_estudiantes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class Formulario extends JFrame {

    JTextField txtCc, txtNombre, txtApellido, txtCorreo, txtTelefono;
    JTable tabla;
    DefaultTableModel modelo;

    public Formulario() {
        setTitle("Estudiantes");
        setSize(650, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titulo = new JLabel("Estudiantes");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(250, 10, 200, 30);
        panel.add(titulo);

        JLabel lblCc = new JLabel("CC");
        lblCc.setBounds(50, 80, 100, 25);
        panel.add(lblCc);

        txtCc = new JTextField();
        txtCc.setBounds(150, 80, 180, 25);
        panel.add(txtCc);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(50, 120, 100, 25);
        panel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(150, 120, 180, 25);
        panel.add(txtNombre);

        JLabel lblApellido = new JLabel("Apellido");
        lblApellido.setBounds(50, 160, 100, 25);
        panel.add(lblApellido);

        txtApellido = new JTextField();
        txtApellido.setBounds(150, 160, 180, 25);
        panel.add(txtApellido);

        JLabel lblCorreo = new JLabel("Correo");
        lblCorreo.setBounds(50, 200, 100, 25);
        panel.add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(150, 200, 180, 25);
        panel.add(txtCorreo);

        JLabel lblTelefono = new JLabel("Telefono");
        lblTelefono.setBounds(50, 240, 100, 25);
        panel.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(150, 240, 180, 25);
        panel.add(txtTelefono);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(50, 300, 90, 30);
        panel.add(btnGuardar);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.setBounds(150, 300, 100, 30);
        panel.add(btnModificar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(260, 300, 100, 30);
        panel.add(btnEliminar);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(370, 300, 90, 30);
        panel.add(btnSalir);

        modelo = new DefaultTableModel();
        modelo.addColumn("CC");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Correo");
        modelo.addColumn("Telefono");

        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(350, 80, 250, 180);
        panel.add(scroll);

        add(panel);

        btnGuardar.addActionListener(e -> guardar());
        btnModificar.addActionListener(e -> modificar());
        btnEliminar.addActionListener(e -> eliminar());
        btnSalir.addActionListener(e -> System.exit(0));

        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int fila = tabla.getSelectedRow();
                txtCc.setText(tabla.getValueAt(fila, 0).toString());
                txtNombre.setText(tabla.getValueAt(fila, 1).toString());
                txtApellido.setText(tabla.getValueAt(fila, 2).toString());
                txtCorreo.setText(tabla.getValueAt(fila, 3).toString());
                txtTelefono.setText(tabla.getValueAt(fila, 4).toString());
            }
        });

        cargarTabla();
        setVisible(true);
    }

    private void cargarTabla() {
        modelo.setRowCount(0);
        try {
            Connection con = Conexion.conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM dbo.Estudiantes_Tabla");

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("cc"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("correo"),
                    rs.getString("telefono")
                });
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void guardar() {
        Insertar.insertar(
            txtCc.getText(),
            txtNombre.getText(),
            txtApellido.getText(),
            txtCorreo.getText(),
            txtTelefono.getText()
        );
        limpiar();
        cargarTabla();
    }

    private void modificar() {
        try {
            Connection con = Conexion.conectar();

            String sql = "UPDATE dbo.Estudiantes_Tabla SET nombre=?, apellido=?, correo=?, telefono=? WHERE cc=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, txtNombre.getText());
            ps.setString(2, txtApellido.getText());
            ps.setString(3, txtCorreo.getText());
            ps.setString(4, txtTelefono.getText());
            ps.setString(5, txtCc.getText());

            ps.executeUpdate();

            limpiar();
            cargarTabla();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void eliminar() {
        try {
            Connection con = Conexion.conectar();

            String sql = "DELETE FROM dbo.Estudiantes_Tabla WHERE cc=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, txtCc.getText());

            ps.executeUpdate();

            limpiar();
            cargarTabla();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void limpiar() {
        txtCc.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
    }
}