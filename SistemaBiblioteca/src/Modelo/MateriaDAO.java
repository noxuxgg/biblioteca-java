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
public class MateriaDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarMateria(Materia ma){
        String sql = "INSERT INTO materia (sigla,Nombre,estado) VALUES(?,?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ma.getSigla());
            ps.setString(2, ma.getNombre());
            ps.setInt(3, ma.getEstado());
            ps.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error: "+e.toString());
            return false;
        }finally{
            try{
                con.close();
            } catch(SQLException e){
                System.out.println(e.toString());
            }
        }
    }
    
    public List ListarMateria(){
        List<Materia> ListaMa = new ArrayList();
        String sql = "SELECT * FROM materia WHERE estado = 1";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Materia ma = new Materia();
                ma.setId_materia(rs.getInt("id_materia"));
                ma.setSigla(rs.getString("sigla"));
                ma.setNombre(rs.getString("nombre"));
                ListaMa.add(ma);
            }
        } catch (SQLException e) {
            System.out.println("Error"+ e.toString());
        }
        return ListaMa;
    }
    
    public boolean EliminarMateria(int id){
        String sql = "UPDATE materia SET estado = 0 WHERE id_materia = ?";
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
    
}
