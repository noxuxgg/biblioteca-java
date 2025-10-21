/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Henry Quispe
 */
public class PrestamoDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarPrestamo(Prestamo pre){
        //String sql = "INSERT INTO prestamos (id_prestamo,id_usuario,id_libro,fecha_prestamo,fecha_devolucion,estado) VALUES (?,?,?,?,?,?)";
        String sql = "INSERT INTO prestamos (id_prestamo,id_usuario,id_libro,fecha_devolucion) VALUES (?,?,?,?)";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pre.getId_prestamo());
            ps.setInt(2, pre.getId_usuario());
            ps.setInt(3, pre.getId_libro());
            //ps.setString(4, pre.getFecha_prestamo());
            ps.setString(4, pre.getFecha_devolucion());
            //ps.setString(5, pre.getEstado());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
       public List ListarPrestamo() {
        List<Prestamo> Listapre = new ArrayList();
        //String sql = "SELECT l.id_libro, l.titulo, l.codigo, l.fechaRegistro, CONCAT(a.nombre, a.apellido), m.nombre, l.stock, l.descripcion, e.nombre, l.anio, l.edicion, c.categoria, el.estado FROM libro l, autores a, materia m, editoriales e, categoria c, estadolibro el  WHERE  l.id_categoria = c.id_categoria AND l.id_editorial = e.id_editorial AND l.id_autor = a.id_autor AND l.id_materia = m.id_materia AND l.id_estado = el.id_estado AND l.estado = 1;";
        String sql = "SELECT p.id_prestamo, p.fecha_prestamo, p.fecha_devolucion, CONCAT(u.nombre, ' ', u.apellido) usu, u.carnet carnet,l.codigo codi, l.titulo titulo, CASE WHEN NOW() BETWEEN p.fecha_prestamo AND p.fecha_devolucion AND p.id_estado_devolucion = 1 THEN 'Activo' WHEN NOW() BETWEEN p.fecha_prestamo AND p.fecha_devolucion AND p.id_estado_devolucion = 2 THEN 'Devuelto' WHEN NOW() > p.fecha_devolucion AND p.id_estado_devolucion = 1 THEN 'Activo sin devolver' WHEN NOW() > p.fecha_devolucion AND p.id_estado_devolucion = 2 THEN 'Devuelto con retraso' END AS estado_prestamo FROM prestamos p LEFT JOIN usuario u ON p.id_usuario = u.id_usuario LEFT JOIN libro l ON l.id_libro = p.id_libro WHERE p.estado = 1;";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Prestamo pre = new Prestamo();
                pre.setId_prestamo(rs.getInt("id_prestamo"));
                pre.setCarnetUsuario(rs.getString("carnet"));
                pre.setNombreUsuario(rs.getString("usu"));
                pre.setTituloLibro(rs.getString("titulo"));
                pre.setCodigoLibro(rs.getString("codi"));
                pre.setFecha_prestamo(rs.getString("fecha_prestamo"));
                pre.setFecha_devolucion(rs.getString("fecha_devolucion"));
                pre.setEstadoPrestamo(rs.getString("estado_prestamo"));
               /* li.setId_libro(rs.getInt("id_libro"));
                li.setTitulo(rs.getString("titulo"));
                li.setCodigo(rs.getString("codigo"));
                li.setFecha(rs.getString("fechaRegistro"));
                li.setNombreAutor(rs.getString("autor"));
                li.setNombreMateria(rs.getString("materia"));
                li.setStock(rs.getInt("stock"));
                li.setDescripcion(rs.getString("descripcion"));
                li.setNombreEditorial(rs.getString("editorial"));
                li.setAnio(rs.getInt("anio"));
                li.setEdicion(rs.getString("edicion"));
                li.setNombreCategoria(rs.getString("categoria"));
                li.setNombreEstado(rs.getString("estado"));
                    */
                Listapre.add(pre);
            }
        } catch (SQLException e) {
            System.out.println("Error" + e.toString());
        }
        return Listapre;
    }
       
    public boolean EliminarPrestamo(int id) {
        String sql = "UPDATE prestamos SET estado = 0 WHERE id_prestamo = ?";
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
    public boolean DevolverPrestamo(int id) {
        String sql = "UPDATE prestamos SET id_estado_devolucion = 2 WHERE id_prestamo = ?";
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
    
    public boolean ActualizarStockLibro(int cant, int cod){
        String sql = "UPDATE libro SET stock = ? WHERE id_libro = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setInt(2, cod);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    
        public boolean ModificarPrestamo(Prestamo pre){
        String sql = "UPDATE prestamos SET id_usuario = ?, id_libro = ? WHERE id_prestamo = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pre.getId_usuario());
            ps.setInt(2, pre.getId_libro());
            //ps.setString(4, pre.getFecha_prestamo());
          //  ps.setString(3, pre.getFecha_devolucion());
            ps.setInt(3, pre.getId_prestamo());
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
