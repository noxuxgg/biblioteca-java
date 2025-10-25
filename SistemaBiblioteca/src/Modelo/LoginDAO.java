package Modelo;

import java.sql.*;
import javax.swing.JOptionPane;

public class LoginDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Registrar un cliente
    public boolean registrar(login reg) {
        try {
            con = cn.getConnection();
            String sqlTipo = "SELECT id_tipo_usuario FROM tipo_usuario WHERE Tipo_usuario = ?";
            ps = con.prepareStatement(sqlTipo);
            ps.setString(1, reg.getTipo());
            rs = ps.executeQuery();

            int idTipo = 0;
            if (rs.next()) {
                idTipo = rs.getInt("id_tipo_usuario");
            } else {
                JOptionPane.showMessageDialog(null, 
                    "El tipo de usuario '" + reg.getTipo() + "' no existe en la base de datos.");
                return false;
            }
            
            String sqlInsert = "INSERT INTO cliente (nombre, correo, pass, id_tipo_usuario) VALUES (?,?,?,?)";
            ps = con.prepareStatement(sqlInsert);
            ps.setString(1, reg.getNombre());
            ps.setString(2, reg.getCorreo());
            ps.setString(3, reg.getPassword());
            ps.setInt(4, idTipo);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario registrado correctamente!");
            return true;

        } catch (SQLException e) {
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error al registrar: " + e.getMessage());
            return false;
        }
    }

    // Login corregido
    public login log(String nombre, String correo, String password) {
        login l = new login();
        String sql = "SELECT c.*, t.tipo_usuario "
                   + "FROM cliente c "
                   + "INNER JOIN tipo_usuario t ON c.id_tipo_usuario = t.id_tipo_usuario "
                   + "WHERE c.nombre = ? AND c.correo = ? AND c.pass = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, correo);
            ps.setString(3, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                l.setId(rs.getInt("id_cliente"));
                l.setNombre(rs.getString("nombre"));
                l.setCorreo(rs.getString("correo"));
                l.setPassword(rs.getString("pass"));
                l.setId_tipo(rs.getInt("id_tipo_usuario"));
                l.setTipo(rs.getString("tipo_usuario")); // ✅ esta línea evita el NullPointerException
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error en login: " + e.getMessage());
        }
        return l;
    }
}
