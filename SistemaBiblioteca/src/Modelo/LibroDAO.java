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
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
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

    public List ListarLibro() {
        List<Libro> ListaLi = new ArrayList();
        //String sql = "SELECT l.id_libro, l.titulo, l.codigo, l.fechaRegistro, CONCAT(a.nombre, a.apellido), m.nombre, l.stock, l.descripcion, e.nombre, l.anio, l.edicion, c.categoria, el.estado FROM libro l, autores a, materia m, editoriales e, categoria c, estadolibro el  WHERE  l.id_categoria = c.id_categoria AND l.id_editorial = e.id_editorial AND l.id_autor = a.id_autor AND l.id_materia = m.id_materia AND l.id_estado = el.id_estado AND l.estado = 1;";
        String sql = "SELECT l.id_libro, l.titulo, l.codigo, l.fechaRegistro, CONCAT(a.nombre, ' ', a.apellido) autor, m.nombre materia, l.stock, l.descripcion, e.nombre editorial, l.anio, l.edicion, c.categoria, el.estado FROM libro l LEFT JOIN autores a ON l.id_autor = a.id_autor LEFT JOIN materia m ON l.id_materia = m.id_materia LEFT JOIN editoriales e ON l.id_editorial = e.id_editorial LEFT JOIN categoria c ON l.id_categoria = c.id_categoria LEFT JOIN estadolibro el ON l.id_estado = el.id_estado WHERE l.estado = 1;";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Libro li = new Libro();
                li.setId_libro(rs.getInt("id_libro"));
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

                ListaLi.add(li);
            }
        } catch (SQLException e) {
            System.out.println("Error" + e.toString());
        }
        return ListaLi;
    }

    public boolean EliminarLibro(int id) {
        String sql = "UPDATE libro SET estado = 0 WHERE id_libro = ?";
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
}
