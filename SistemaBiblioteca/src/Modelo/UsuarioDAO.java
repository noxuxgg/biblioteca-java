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
import javax.swing.JComboBox;

/**
 *
 * @author ADRIANA
 */
public class UsuarioDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarUsuario(Usuario us){
        String sql = "INSERT INTO usuario (Id_usuario, Carnet, Nombre, Apellido, Domicilo, Id_tipo_usuario, Telefono, id_cargo, id_carrera, Estado, id_estado_usuario) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, us.getId_usuario());
            ps.setString(2, us.getCarnet());
            ps.setString(3, us.getNombre());
            ps.setString(4, us.getApellido());
            ps.setString(5, us.getDomicilio());
            ps.setInt(6, us.getId_tipo_usuario());
            ps.setString(7, us.getTelefono());
            ps.setInt(8, us.getId_cargo());
            ps.setInt(9, us.getId_carrera());
            ps.setInt(10, us.getEstado());
            ps.setInt(11, us.getId_estado_prestamo());
            ps.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar usuario: " + e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión: " + e.toString());
            }
        }
    }
    
    public int ObtenerIdTipoUsuario(String nombreTipoUsuario) {
        String sql = "SELECT Id_tipo_usuario FROM tipo_usuario WHERE Tipo_usuario = ?";
        int id = 0;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreTipoUsuario);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("Id_tipo_usuario");
            }
            return id;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener ID tipo usuario: " + e.toString());
            return -1;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public int ObtenerIdCargo(String nombreCargo) {
        String sql = "SELECT id_cargo FROM cargo WHERE nombre = ?";
        int id = 0;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreCargo);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_cargo");
            }
            return id;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener ID cargo: " + e.toString());
            return -1;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public int ObtenerIdCarrera(String nombreCarrera) {
        String sql = "SELECT id_carrera FROM carrera WHERE nombre = ?";
        int id = 0;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreCarrera);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_carrera");
            }
            return id;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener ID carrera: " + e.toString());
            return -1;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public int ObtenerIdEstadoPrestamo(String nombreEstado) {
        String sql = "SELECT id_estado_usuario FROM estado_usuario WHERE estado_usuario = ?";
        int id = 0;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreEstado);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_estado_usuario");
            }
            return id;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener ID estado usuario: " + e.toString());
            return -1;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public void ConsultarTipoUsuario(JComboBox tipoUsuario){
        String sql = "SELECT Tipo_usuario FROM tipo_usuario";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                tipoUsuario.addItem(rs.getString("Tipo_usuario"));
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar tipo usuario: " + e.toString());
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public void ConsultarCargo(JComboBox cargos){
        String sql = "SELECT nombre FROM cargo";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                cargos.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar cargo: " + e.toString());
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public void ConsultarCarreras(JComboBox carreras){
        String sql = "SELECT nombre FROM carrera";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                carreras.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar carrera: " + e.toString());
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public void ConsultarEstadoUsuario(JComboBox estadoUsuario){
        String sql = "SELECT estado_usuario FROM estado_usuario";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                estadoUsuario.addItem(rs.getString("estado_usuario"));
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar estado usuario: " + e.toString());
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public List ListarUsuario(){
        List<Usuario> ListaUs = new ArrayList();
        String sql = "SELECT a.Id_usuario, a.Carnet, a.Nombre, a.Apellido, a.Domicilo, " +
             "p.Tipo_usuario, a.Telefono, f.nombre as nombre_cargo, j.nombre as nombre_carrera, m.estado_usuario as estado_usuario " +
             "FROM usuario a " +
             "INNER JOIN tipo_usuario p ON p.Id_tipo_usuario = a.Id_tipo_usuario " +
             "INNER JOIN cargo f ON f.id_cargo = a.id_cargo " +
             "INNER JOIN carrera j ON j.id_carrera = a.id_carrera " +
             "INNER JOIN estado_usuario m ON m.id_estado_usuario = a.id_estado_usuario " + 
             "WHERE a.estado = 1";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Usuario us = new Usuario();
                us.setId_usuario(rs.getInt("Id_usuario"));
                us.setCarnet(rs.getString("Carnet"));
                us.setNombre(rs.getString("Nombre"));
                us.setApellido(rs.getString("Apellido"));
                us.setDomicilio(rs.getString("Domicilo"));
                us.setTelefono(rs.getString("Telefono"));

                // ASIGNAR LOS NOMBRES EN LUGAR DE LOS IDs
                us.setTipoUsuarioNombre(rs.getString("Tipo_usuario"));
                us.setCargoNombre(rs.getString("nombre_cargo"));
                us.setCarreraNombre(rs.getString("nombre_carrera"));
                us.setEstadoPrestamo(rs.getString("estado_usuario"));
                
                ListaUs.add(us);
            }
        } catch (SQLException e) {
            System.out.println("Error en ListarUsuario"+ e.toString());
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return ListaUs;
    }   
    
    public boolean existeCarnet(String carnet) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE Carnet = ? AND estado = 1";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, carnet);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Si count > 0, el carnet ya existe
            }
            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar carnet: " + e.toString());
            return false;
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public boolean EliminarUsuario(int id){
        String sql = "UPDATE usuario SET estado = 0 WHERE Id_usuario = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: "+e.toString());
            return false;
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public boolean ModificarUsuario(Usuario us){
        String sql = "UPDATE usuario SET Carnet = ?, Nombre = ?, Apellido = ?, Domicilo = ?, Telefono = ?, Id_tipo_usuario = ?, id_cargo = ?, id_carrera = ?, id_estado_usuario = ? WHERE Id_usuario = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, us.getCarnet());
            ps.setString(2, us.getNombre());
            ps.setString(3, us.getApellido());
            ps.setString(4, us.getDomicilio());
            ps.setString(5, us.getTelefono());
            ps.setInt(6, us.getId_tipo_usuario());
            ps.setInt(7, us.getId_cargo());
            ps.setInt(8, us.getId_carrera());
            ps.setInt(9, us.getId_usuario());
            ps.setInt(10, us.getId_estado_prestamo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al modificar usuario: "+e.toString());
            return false;
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    
      public Usuario BuscarUsuario(String cod){
        Usuario usuario = new Usuario();
        String sql = "SELECT * FROM usuario WHERE carnet = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            if(rs.next()){
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setDomicilio(rs.getString("domicilo"));
                usuario.setId_estado_prestamo(rs.getInt("id_estado_usuario"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return usuario;
    }

    
    
    
    
}
