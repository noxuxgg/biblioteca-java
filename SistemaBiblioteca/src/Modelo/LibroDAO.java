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
import java.time.Year;
import java.util.Date;

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
        String errores = validarLibroCompleto(li);
        if (!errores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Errores de validación:\n" + errores);
            return false;
        }
        String sql = "INSERT INTO libro (titulo, id_categoria, id_editorial, id_autor, id_materia, edicion, estado, codigo, stock, anio, descripcion, id_estado, tipo) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
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
            ps.setString(13, li.getTipo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: Debe tener llenados los campos obligatorios");
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

    public void ConsultarNombre(JComboBox nombre) {
        String sql = "SELECT titulo FROM libro";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                nombre.addItem(rs.getString("titulo"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());;
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
        String sql = "SELECT l.id_libro, l.titulo, l.codigo, l.fechaRegistro, CONCAT(a.nombre, ' ', a.apellido) autor, m.nombre materia, l.stock, l.descripcion, e.nombre editorial, l.anio, l.edicion, c.categoria, el.estado, l.tipo FROM libro l LEFT JOIN autores a ON l.id_autor = a.id_autor LEFT JOIN materia m ON l.id_materia = m.id_materia LEFT JOIN editoriales e ON l.id_editorial = e.id_editorial LEFT JOIN categoria c ON l.id_categoria = c.id_categoria LEFT JOIN estadolibro el ON l.id_estado = el.id_estado WHERE l.estado = 1;";
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
                li.setTipo(rs.getString("tipo"));
                ListaLi.add(li);
            }
        } catch (SQLException e) {
            System.out.println("Error" + e.toString());
        }
        return ListaLi;
    }

    public List<Libro> ListarLibroPorFechas(Date fechaInicio, Date fechaFin) {
        List<Libro> ListaLi = new ArrayList<>();

        String sql = "SELECT l.id_libro, l.titulo, l.codigo, l.fechaRegistro, "
                + "CONCAT(a.nombre, ' ', a.apellido) autor, m.nombre materia, "
                + "l.stock, l.descripcion, e.nombre editorial, l.anio, l.edicion, "
                + "c.categoria, el.estado, l.tipo "
                + "FROM libro l "
                + "LEFT JOIN autores a ON l.id_autor = a.id_autor "
                + "LEFT JOIN materia m ON l.id_materia = m.id_materia "
                + "LEFT JOIN editoriales e ON l.id_editorial = e.id_editorial "
                + "LEFT JOIN categoria c ON l.id_categoria = c.id_categoria "
                + "LEFT JOIN estadolibro el ON l.id_estado = el.id_estado "
                + "WHERE l.estado = 1 AND l.fechaRegistro >= ? AND l.fechaRegistro <= ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            // Convertimos java.util.Date a java.sql.Date
            java.sql.Date fInicioSQL = new java.sql.Date(fechaInicio.getTime());
            java.sql.Date fFinSQL = new java.sql.Date(fechaFin.getTime());

            ps.setDate(1, fInicioSQL);
            ps.setDate(2, fFinSQL);

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
                li.setTipo(rs.getString("tipo"));

                ListaLi.add(li);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
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

    public Libro BuscarLibro(String cod) {
        Libro libro = new Libro();
        String sql = "SELECT * FROM libro WHERE codigo = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            if (rs.next()) {
                libro.setId_libro(rs.getInt("id_libro"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setEdicion(rs.getString("edicion"));
                libro.setStock(rs.getInt("stock"));
                libro.setId_estado(rs.getInt("id_estado"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return libro;
    }

    public Libro BuscarLibroPorTitulo(String titulo) {
        Libro libro = new Libro();
        String sql = "SELECT * FROM libro WHERE titulo = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, titulo);
            rs = ps.executeQuery();
            if (rs.next()) {
                libro.setId_libro(rs.getInt("id_libro"));
                libro.setCodigo(rs.getString("codigo"));
                libro.setEdicion(rs.getString("edicion"));
                libro.setStock(rs.getInt("stock"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return libro;
    }

    public Libro BuscarLibroPorId(int id) {
        Libro l = null;
        String sql = "SELECT * FROM libro WHERE Id_libro = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                l = new Libro();
                l.setId_libro(rs.getInt("Id_libro"));
                l.setTitulo(rs.getString("Titulo"));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar libro: " + e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
            }
        }
        return l;
    }

    public boolean ModificarLibro(Libro li) {
        // titulo, id_categoria, id_editorial, id_autor, id_materia, edicion, estado, codigo, stock, anio, descripcion, id_estado
        // titulo, codigo, autor, materia, descripcion, editorial, año, edicion, categoria, estado, tipo
        String errores = validarLibroCompleto(li);
        if (!errores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Errores de validación:\n" + errores);
            return false;
        }
        String sql = "UPDATE libro SET titulo = ?, id_categoria = ?, id_autor = ?, id_materia = ?, edicion = ?, codigo = ?, anio = ?, descripcion = ?, tipo=?, id_editorial=?, id_estado=?  WHERE id_libro = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, li.getTitulo());
            int idCategoria = li.getId_categoria();
            if (idCategoria == 0) {
                ps.setNull(2, java.sql.Types.INTEGER);
            } else {
                ps.setInt(2, idCategoria);
            }
            int idAutor = li.getId_autor();
            if (idAutor == 0) {
                ps.setNull(3, java.sql.Types.INTEGER);
            } else {
                ps.setInt(3, idAutor);
            }
            int idMateria = li.getId_materia();
            if (idMateria == 0) {
                ps.setNull(4, java.sql.Types.INTEGER);
            } else {
                ps.setInt(4, idMateria);
            }
            ps.setString(5, li.getEdicion());
            ps.setString(6, li.getCodigo());
            ps.setInt(7, li.getAnio());
            ps.setString(8, li.getDescripcion());
            ps.setString(9, li.getTipo());
            int idEditorial = li.getId_editorial();
            if (idEditorial == 0) {
                ps.setNull(10, java.sql.Types.INTEGER);
            } else {
                ps.setInt(10, idEditorial);
            }
            ps.setInt(11, li.getId_estado());
            ps.setInt(12, li.getId_libro());
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

    public boolean validarCodigoLibro(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return false;
        }
        return codigo.matches("^[A-Z][\\-\\.]?\\d{2,5}$");
    }

    public boolean validarAnio(String anioString) {
        if (anioString == null || anioString.trim().isEmpty()) {
            return false;
        }
        try {
            int anio = Integer.parseInt(anioString.trim());
            int anioActual = Year.now().getValue();
            if (anio > anioActual) {
                return false;
            }
            if (anio < 1500) {
                return false;
            }
            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean validarEdicion(String edicion) {
        if (edicion == null || edicion.trim().isEmpty()) {
            return false;
        }
        return edicion.matches("^[A-Za-z0-9\\s.,/\\-]{1,50}$");
    }

    //LLAMADA A VALIDACIONES
    public String validarLibroCompleto(Libro li) {
        StringBuilder errores = new StringBuilder();

        if (!validarCodigoLibro(li.getCodigo())) {
            errores.append("- El codigo debe estar en un formato separado por - o .\n");
        }
        if (!validarAnio((li.getAnio() + ""))) {
            errores.append("- El año tiene que estar en un rango Coherente\n");
        }

        if (!validarEdicion(li.getEdicion())) {
            errores.append("- La edicion debe estar en un formato permitido como 10ma, 10, X, Ampliacion 10.0, 10.0 \n");
        }
        return errores.toString();
    }

    // Titulo Autor Materia Estado
    public List ListarLibro2() {
        List<Libro> ListaLi = new ArrayList();
        String sql = "SELECT l.codigo, l.titulo,CONCAT(a.nombre, ' ', a.apellido) autor, m.nombre materia, c.categoria, el.estado, l.tipo FROM libro l LEFT JOIN autores a ON l.id_autor = a.id_autor LEFT JOIN materia m ON l.id_materia = m.id_materia LEFT JOIN editoriales e ON l.id_editorial = e.id_editorial LEFT JOIN categoria c ON l.id_categoria = c.id_categoria LEFT JOIN estadolibro el ON l.id_estado = el.id_estado WHERE l.estado = 1;";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Libro li = new Libro();
                li.setCodigo(rs.getString("codigo"));
                li.setTitulo(rs.getString("titulo"));
                li.setNombreAutor(rs.getString("autor"));
                li.setNombreMateria(rs.getString("materia"));
                li.setNombreCategoria(rs.getString("categoria"));
                li.setNombreEstado(rs.getString("estado"));
                li.setTipo(rs.getString("tipo"));
                ListaLi.add(li);
            }
        } catch (SQLException e) {
            System.out.println("Error" + e.toString());
        }
        return ListaLi;
    }

    public List ListarLibro3(Libro lib) {
        List<Libro> ListaLi = new ArrayList();
        String sql = "SELECT l.codigo, l.titulo,CONCAT(a.nombre, ' ', a.apellido) autor, m.nombre materia, c.categoria, el.estado, l.tipo FROM libro l LEFT JOIN autores a ON l.id_autor = a.id_autor LEFT JOIN materia m ON l.id_materia = m.id_materia LEFT JOIN editoriales e ON l.id_editorial = e.id_editorial LEFT JOIN categoria c ON l.id_categoria = c.id_categoria LEFT JOIN estadolibro el ON l.id_estado = el.id_estado WHERE l.estado = 1 AND (l.titulo = ? OR CONCAT(a.nombre, ' ', a.apellido) = ? OR m.nombre = ? OR c.categoria = ?);";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, lib.getTitulo());
            ps.setString(2, lib.getNombreAutor());
            ps.setString(3, lib.getNombreMateria());
            ps.setString(4, lib.getNombreCategoria());
            rs = ps.executeQuery();
            while (rs.next()) {
                Libro li = new Libro();
                li.setCodigo(rs.getString("codigo"));
                li.setTitulo(rs.getString("titulo"));
                li.setNombreAutor(rs.getString("autor"));
                li.setNombreMateria(rs.getString("materia"));
                li.setNombreCategoria(rs.getString("categoria"));
                li.setNombreEstado(rs.getString("estado"));
                li.setTipo(rs.getString("tipo"));
                ListaLi.add(li);
            }
        } catch (SQLException e) {
            System.out.println("Error" + e.toString());
        }
        return ListaLi;
    }

    public List<Libro> ListarMasPrestados(String materia) {
        List<Libro> ListaLi = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT l.codigo, l.titulo, CONCAT(a.nombre, ' ', a.apellido) autor, ")
                .append("m.nombre materia, c.categoria, el.estado, l.tipo, ")
                .append("COUNT(p.id_libro) AS total_prestamos ")
                .append("FROM libro l ")
                .append("LEFT JOIN autores a ON l.id_autor = a.id_autor ")
                .append("LEFT JOIN materia m ON l.id_materia = m.id_materia ")
                .append("LEFT JOIN editoriales e ON l.id_editorial = e.id_editorial ")
                .append("LEFT JOIN categoria c ON l.id_categoria = c.id_categoria ")
                .append("LEFT JOIN estadolibro el ON l.id_estado = el.id_estado ")
                .append("LEFT JOIN prestamos p ON l.id_libro = p.id_libro ")
                .append("WHERE l.estado = 1 ");

        // Si materia no es nula ni vacía, agregamos el filtro
        if (materia != null && !materia.isEmpty()) {
            sql.append("AND m.nombre = ? ");
        }

        sql.append("GROUP BY l.id_libro ")
                .append("ORDER BY total_prestamos DESC");

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql.toString());

            // Si materia se ingresó, seteamos el parámetro
            if (materia != null && !materia.isEmpty()) {
                ps.setString(1, materia);
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                Libro li = new Libro();
                li.setCodigo(rs.getString("codigo"));
                li.setTitulo(rs.getString("titulo"));
                li.setNombreAutor(rs.getString("autor"));
                li.setNombreMateria(rs.getString("materia"));
                li.setNombreCategoria(rs.getString("categoria"));
                li.setNombreEstado(rs.getString("estado"));
                li.setTipo(rs.getString("tipo"));
                // opcional: li.setTotalPrestamos(rs.getInt("total_prestamos"));

                ListaLi.add(li);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }

        return ListaLi;
    }

    public boolean validarSeleccionCombo(javax.swing.JComboBox<String> comboBox, String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return true;
        }

        for (int i = 0; i < comboBox.getItemCount(); i++) {
            Object item = comboBox.getItemAt(i);
            if (item != null && item.toString().trim().equalsIgnoreCase(valor.trim())) {
                return true;
            }
        }

        return false;
    }
}
