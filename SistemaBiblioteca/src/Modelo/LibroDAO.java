/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import javax.swing.JComboBox;
/**
 *
 * @author pc
 */
public class LibroDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistrarLibro(Libro li) {
        String sql = "INSERT INTO libro (titulo,id_categoria,id_editorial,id_autor,id_materia,edicion,estado,codigo) VALUES(?,?,?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, li.getTitulo());
            ps.setInt(2, li.getId_categoria());
            ps.setInt(3, li.getId_editorial());
            ps.setInt(4, li.getId_autor());
            ps.setInt(5, li.getId_materia());
            ps.setString(6, li.getEdicion());
            ps.setString(7, li.getEstado());
            ps.setString(8, li.getCodigo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: Debe seleccionar un pais de la barra de opciones");
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
}
