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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class PaisDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean registrarPais(Pais pa) {
        String errores = validarPaisCompleto(pa);
        if (!errores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Errores de validación:\n" + errores);
            return false;
        }
        String sql = "INSERT INTO paises (nombre,estado) VALUES (?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pa.getNombre());
            ps.setInt(2, pa.getEstado());
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

    public List ListarPais() {
        List<Pais> ListaPais = new ArrayList();
        String sql = "SELECT * FROM paises WHERE estado = 1";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pais pa = new Pais();
                pa.setId_pais(rs.getInt("id_pais"));
                pa.setNombre(rs.getString("nombre"));
                ListaPais.add(pa);
            }
        } catch (SQLException e) {
            System.out.println("Error" + e.toString());
        }
        return ListaPais;
    }

    public boolean EliminarPais(int id) {
        String sql = "UPDATE paises SET estado = 0 WHERE id_pais = ?";
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

    public boolean ModificarPais(Pais pa) {
        String errores = validarPaisCompleto(pa);
        if (!errores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Errores de validación:\n" + errores);
            return false;
        }
        String sql = "UPDATE paises SET nombre = ? WHERE id_pais=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pa.getNombre());
            ps.setInt(2, pa.getId_pais());
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

    public String validarPaisCompleto(Pais pa) {
        StringBuilder errores = new StringBuilder();
        if (!validarNombre(pa.getNombre())) {
            errores.append("- El nombre del país solo puede tener letras \n- No puede contener unicamente espacios\n");
        }
        return errores.toString();
    }

    public boolean existePais(String nombrePais) {
        String sql = "SELECT nombre FROM paises WHERE estado = 1 AND nombre = ?";
        String nombre = "";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombrePais);
            rs = ps.executeQuery();
            if (rs.next()) {
                nombre = rs.getString("nombre");
            }
            if (nombre.equalsIgnoreCase(nombrePais)) {
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
