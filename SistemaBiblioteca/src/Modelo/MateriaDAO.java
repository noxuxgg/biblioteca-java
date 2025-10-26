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

    public boolean RegistrarMateria(Materia ma) {
        String errores = validarMateriaCompleto(ma);
        if (!errores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Errores de validación:\n" + errores);
            return false;
        }
        String sql = "INSERT INTO materia (sigla,Nombre,estado) VALUES(?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ma.getSigla());
            ps.setString(2, ma.getNombre());
            ps.setInt(3, ma.getEstado());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public List ListarMateria() {
        List<Materia> ListaMa = new ArrayList();
        String sql = "SELECT * FROM materia WHERE estado = 1";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Materia ma = new Materia();
                ma.setId_materia(rs.getInt("id_materia"));
                ma.setSigla(rs.getString("sigla"));
                ma.setNombre(rs.getString("nombre"));
                ListaMa.add(ma);
            }
        } catch (SQLException e) {
            System.out.println("Error" + e.toString());
        }
        return ListaMa;
    }

    public boolean EliminarMateria(int id) {
        String sql = "UPDATE materia SET estado = 0 WHERE id_materia = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    public boolean ModificarMateria(Materia ma) {
        String errores = validarMateriaCompleto(ma);
        if (!errores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Errores de validación:\n" + errores);
            return false;
        }
        String sql = "UPDATE materia SET sigla = ?, nombre = ? WHERE id_materia=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ma.getSigla());
            ps.setString(2, ma.getNombre());
            ps.setInt(3, ma.getId_materia());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error" + e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public boolean validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }
        return nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$");
    }

    public boolean validarSigla(String sigla) {
        if (sigla == null || sigla.trim().isEmpty()) {
            return false;
        }
        return sigla.matches("^[A-Z]{3}-\\d{4}$");
    }
    
    public String validarMateriaCompleto(Materia ma) {
        StringBuilder errores = new StringBuilder();
        if (!validarNombre(ma.getNombre())) {
            errores.append("- El nombre solo puede contener letras\n- No puede existir unicamente espacios\n");
        }
        if (!validarSigla(ma.getSigla())) {
            errores.append("- La sigla debe estar en formato XXX-DDDD\n- No puede existir unicamente espacios\n");
        }
        return errores.toString();
    }
    
    public boolean existeMateria(String siglaMateria) {
        String sql = "SELECT sigla FROM materia WHERE estado = 1 AND sigla = ?";
        String nombre = "";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, siglaMateria);
            rs = ps.executeQuery();
            if (rs.next()) {
                nombre = rs.getString("sigla");
            }
            if (nombre.equalsIgnoreCase(siglaMateria)) {
                return true;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return false;
    }

}
