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
public class AutorDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistrarAutor(Autor au) {
        String sql = "INSERT INTO autores (nombre, apellido, id_pais, estado) VALUES(?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, au.getNombre());
            ps.setString(2, au.getApellido());
            ps.setInt(3, au.getId_pais());
            ps.setInt(4, au.getEstado());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: Debe seleccionar un pais de la barra de opciones"+ e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public int ObtenerIdPais(String nombrePais) {
        String sql = "SELECT id_pais FROM paises WHERE nombre = ?";
        int id = 0;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombrePais);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_pais");
            }
            return id;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
            return -1;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public void ConsultarPais(JComboBox pais){
        String sql = "SELECT nombre FROM paises";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                pais.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());;
        }
    }
    
    public List ListarAutor(){
        List<Autor> ListaAu = new ArrayList();
        String sql = "SELECT a.id_autor, a.nombre, a.apellido, p.nombre FROM autores a, paises p WHERE p.id_pais = a.id_pais AND a.estado = 1";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Autor au = new Autor();
                au.setId_autor(rs.getInt("a.id_autor"));
                au.setNombre(rs.getString("a.nombre"));
                au.setApellido(rs.getString("a.apellido"));
                au.setNombrePais(rs.getString("p.nombre"));
                ListaAu.add(au);
            }
        } catch (SQLException e) {
            System.out.println("Error"+ e.toString());
        }
        return ListaAu;
    }
    
    public boolean EliminarAutor(int id){
        String sql = "UPDATE autores SET estado = 0 WHERE id_autor = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error: "+e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public boolean ModificarAutor(Autor au){
        String sql = "UPDATE autores SET nombre = ?, apellido = ?, id_pais = ? WHERE id_autor = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, au.getNombre());
            ps.setString(2, au.getApellido());
            ps.setInt(3, au.getId_pais());
            ps.setInt(4, au.getId_autor());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error"+e.toString());
            return false;
        } finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
}
