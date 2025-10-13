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
public class LibroDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistrarLibro(Libro li) {
        String sql = "INSERT INTO libro (titulo, id_categoria, id_editorial, id_autor, id_materia, edicion, estado, codigo, stock, anio, descripcion, id_estado) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            /*ps.setString(1, li.getTitulo());
            ps.setInt(2, li.getId_categoria());
            ps.setInt(3, li.getId_editorial());
            ps.setInt(4, li.getId_autor());
            ps.setInt(5, li.getId_materia());
            ps.setString(6, li.getEdicion());
            ps.setInt(7, li.getEstado());
            ps.setString(8, li.getCodigo());
            ps.setInt(9, li.getStock());
            ps.setInt(10, li.getAnio());
            ps.setString(11, li.getDescripcion());
            ps.setInt(12, li.getId_estado());*/
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, li.getTitulo());
            int idCategoria = li.getId_categoria();
            if (idCategoria == 0) {
                ps.setNull(2, java.sql.Types.INTEGER);
            } else {
                ps.setInt(2, idCategoria);
            }
            int idEditorial = li.getId_editorial();
            if (idEditorial == 0) {
                ps.setNull(3, java.sql.Types.INTEGER);
            } else {
                ps.setInt(3, idEditorial);
            }
            int idAutor = li.getId_autor();
            if (idAutor == 0) {
                ps.setNull(4, java.sql.Types.INTEGER);
            } else {
                ps.setInt(4, idAutor);
            }
            int idMateria = li.getId_materia();
            if (idMateria == 0) {
                ps.setNull(5, java.sql.Types.INTEGER);
            } else {
                ps.setInt(5, idMateria);
            }
            ps.setString(6, li.getEdicion());
            ps.setInt(7, li.getEstado());
            ps.setString(8, li.getCodigo());
            ps.setInt(9, li.getStock());
            int anio = li.getAnio();
            if (anio <= 0) {
                ps.setNull(10, java.sql.Types.INTEGER);
            } else {
                ps.setInt(10, anio);
            }
            ps.setString(11, li.getDescripcion());
            ps.setInt(12, li.getId_estado());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: Debe seleccionar un pais de la barra de opciones");
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public int ObtenerIdAutor(String nombreAutor) {
        String sql = "SELECT id_autor FROM autores WHERE CONCAT(nombre, ' ', apellido) = ?";
        int id = 0;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreAutor);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_autor");
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

    public int ObtenerIdMateria(String nombreMateria) {
        String sql = "SELECT id_materia FROM materia WHERE nombre = ?";
        int id = 0;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreMateria);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_materia");
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

    public int ObtenerIdEditorial(String nombreEditorial) {
        String sql = "SELECT id_editorial FROM editoriales WHERE nombre = ?";
        int id = 0;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreEditorial);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_editorial");
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

    public int ObtenerIdCategoria(String nombreCategoria) {
        String sql = "SELECT id_categoria FROM categoria WHERE categoria = ?";
        int id = 0;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreCategoria);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_categoria");
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

    public int ObtenerIdEstado(String nombreEstado) {
        String sql = "SELECT id_estado FROM estadoLibro WHERE estado = ?";
        int id = 0;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreEstado);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_estado");
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

    public void ConsultarAutor(JComboBox autor) {
        String sql = "SELECT CONCAT(nombre,' ',apellido) FROM autores WHERE estado = 1";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                autor.addItem(rs.getString("CONCAT(nombre,' ',apellido)"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());;
        }
    }

    public void ConsultarMateria(JComboBox materia) {
        String sql = "SELECT nombre FROM materia WHERE estado = 1";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                materia.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());;
        }
    }

    public void ConsultarEditorial(JComboBox editorial) {
        String sql = "SELECT nombre FROM editoriales WHERE estado = 1";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                editorial.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());;
        }
    }

    public void ConsultarCategoria(JComboBox categoria) {
        String sql = "SELECT categoria FROM categoria WHERE estado = 1";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                categoria.addItem(rs.getString("categoria"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());;
        }
    }

    public void ConsultarEstado(JComboBox estado) {
        String sql = "SELECT estado FROM estadoLibro";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                estado.addItem(rs.getString("estado"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());;
        }
    }

}
