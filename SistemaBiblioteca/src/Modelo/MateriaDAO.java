/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.SQLException;
/**
 *
 * @author pc
 */
public class MateriaDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    public boolean RegistrarMateria(Materia ma){
        String sql = "INSERT INTO materia (sigla,Nombre) VALUES(?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ma.getSigla());
            ps.setString(2, ma.getNombre());
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
}
