/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author ADRIANA
 */
public class UsuarioDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarUsuario(Usuario us){
        String sql = "INSERT INTO usuario (Id_usuario, Carnet, Nombre, Apellido, Domicilio, Id_tipo_usuario, Telefono, id_cargo, id_carrera, Estado) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, us.getId_usuario());
            ps.setString(2, us.getCarnet());
            ps.setString(3, us.getNombre());
            ps.setString(4, us.getApellido());
            ps.setString(5, us.getDomicilio());
            ps.setInt(6, us.getId_tipo_usuario());
            ps.setString(7, us.getTelefono());
            ps.setInt(8, us.getId_cargo());
            ps.setInt(9, us.getId_carrera());
            ps.setInt(10, us.getEstado());
            ps.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar usuario: " + e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión: " + e.toString());
            }
        }
    }
}
