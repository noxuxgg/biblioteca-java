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
public class PaisDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    public boolean registrarPais(Pais pa){
        String sql = "INSERT INTO paises (nombre) VALUES (?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pa.getNombre());
            ps.execute();
            return true;
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error: "+ e.toString());
            return false;
        } finally{
            try{
                con.close();
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        }
    }
}
