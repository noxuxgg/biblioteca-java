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
/**
 *
 * @author pc
 */
public class CategoriaDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarCategoria(Categoria ca){
        String sql = "INSERT INTO categoria (categoria, estado) VALUES (?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ca.getCategoria());
            ps.setInt(2, ca.getEstado());
            ps.execute();
            return true;
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error: "+e.toString());
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        }
    }
    
    public List ListarCategoria(){
        List<Categoria> ListaCa = new ArrayList();
        String sql = "SELECT * FROM categoria WHERE estado = 1";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Categoria ca = new Categoria();
                ca.setId_categoria(rs.getInt("id_categoria"));
                ca.setCategoria(rs.getString("categoria"));
                ListaCa.add(ca);
            }
        } catch (SQLException e) {
            System.out.println("Error"+ e.toString());
        }
        return ListaCa;
    }
    
    public boolean EliminarCategoria(int id){
        String sql = "UPDATE categoria SET estado = 0 WHERE id_categoria = ?";
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
    
    public boolean ModificarCategoria(Categoria ca){
        String sql = "UPDATE categoria SET categoria = ? WHERE id_categoria=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ca.getCategoria());
            ps.setInt(2, ca.getId_categoria());
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
