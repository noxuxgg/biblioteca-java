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
public class EditorialDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistrarEditorial(Editorial ed) {
        String sql = "INSERT INTO editoriales (nombre,direccion,telefono,id_pais,estado) VALUES(?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ed.getNombre());
            ps.setString(2, ed.getDireccion());
            ps.setString(3, ed.getTelefono());
            ps.setInt(4, ed.getId_Pais());
            ps.setInt(5, ed.getEstado());
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
    
    public void ConsultarEditorial(JComboBox editorial){
        String sql = "SELECT nombre FROM paises";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                editorial.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());;
        }
    }
    
    public List ListarEditorial(){
        List<Editorial> ListaEd = new ArrayList();
        String sql = "SELECT e.id_editorial, e.nombre, e.direccion, e.telefono, p.nombre FROM editoriales e, paises p WHERE p.id_pais = e.id_pais AND e.estado = 1";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Editorial ed = new Editorial();
                ed.setId_editorial(rs.getInt("e.id_editorial"));
                ed.setNombre(rs.getString("e.nombre"));
                ed.setDireccion(rs.getString("e.direccion"));
                ed.setTelefono(rs.getString("e.telefono"));
                ed.setNombrePais(rs.getString("p.nombre"));
                ListaEd.add(ed);
            }
        } catch (SQLException e) {
            System.out.println("Error"+ e.toString());
        }
        return ListaEd;
    }
    
    public boolean EliminarEditorial(int id){
        String sql = "UPDATE editoriales SET estado = 0 WHERE id_editorial = ?";
        try {
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
    
    public boolean ModificarEditorial(Editorial ed){
        String sql = "UPDATE editoriales SET nombre = ?, direccion = ?, telefono = ?, id_pais = ? WHERE id_editorial = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ed.getNombre());
            ps.setString(2, ed.getDireccion());
            ps.setString(3, ed.getTelefono());
            ps.setInt(4, ed.getId_Pais());
            ps.setInt(5, ed.getId_editorial());
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
