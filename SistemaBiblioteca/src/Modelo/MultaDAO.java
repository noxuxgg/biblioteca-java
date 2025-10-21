package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MultaDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    // Registrar multa
    public boolean registrarMulta(Multa m) {
        String sql = "INSERT INTO multa (Id_prestamo, Id_usuario, Dias_retraso, Monto, Estado) VALUES (?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, m.getId_prestamo());
            ps.setInt(2, m.getId_usuario());
            ps.setInt(3, m.getDias_retraso());
            ps.setFloat(4, m.getMonto());
            ps.setString(5, m.getEstado());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar multa: " + e.toString());
            return false;
        } finally {
            try { con.close(); } catch (SQLException e) { System.out.println(e.toString()); }
        }
    }

    // Listar todas las multas con información relacionada
    public List<Multa> listarMultas() {
        List<Multa> lista = new ArrayList<>();
        String sql = """
            SELECT m.Id_multa, m.Id_prestamo, m.Id_usuario, m.Dias_retraso, m.Monto, m.Estado,
                   CONCAT(u.Nombre, ' ', u.Apellido) AS nombreUsuario,
                   l.Titulo AS nombreLibro
            FROM multa m
            INNER JOIN prestamos p ON m.Id_prestamo = p.Id_prestamo
            INNER JOIN usuario u ON m.Id_usuario = u.Id_usuario
            INNER JOIN libro l ON p.Id_libro = l.Id_libro;
        """;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Multa m = new Multa();
                m.setId_multa(rs.getInt("Id_multa"));
                m.setId_prestamo(rs.getInt("Id_prestamo"));
                m.setId_usuario(rs.getInt("Id_usuario"));
                m.setDias_retraso(rs.getInt("Dias_retraso"));
                m.setMonto(rs.getFloat("Monto"));
                m.setEstado(rs.getString("Estado"));
                m.setNombreUsuario(rs.getString("nombreUsuario"));
                m.setNombreLibro(rs.getString("nombreLibro"));
                lista.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar multas: " + e.toString());
        } finally {
            try { con.close(); } catch (SQLException e) { System.out.println(e.toString()); }
        }
        return lista;
    }

    // Eliminar (lógico)
    public boolean eliminarMulta(int id) {
        String sql = "UPDATE multa SET Estado = 'Inactiva' WHERE Id_multa = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar multa: " + e.toString());
            return false;
        } finally {
            try { con.close(); } catch (SQLException e) { System.out.println(e.toString()); }
        }
    }

    // Actualizar multa
    public boolean modificarMulta(Multa m) {
        String sql = "UPDATE multa SET Dias_retraso=?, Monto=?, Estado=? WHERE Id_multa=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, m.getDias_retraso());
            ps.setFloat(2, m.getMonto());
            ps.setString(3, m.getEstado());
            ps.setInt(4, m.getId_multa());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al modificar multa: " + e.toString());
            return false;
        } finally {
            try { con.close(); } catch (SQLException e) { System.out.println(e.toString()); }
        }
    }

    //  Buscar multa por ID
    public Multa buscarPorId(int id) {
        Multa m = null;
        String sql = "SELECT * FROM multa WHERE Id_multa = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                m = new Multa();
                m.setId_multa(rs.getInt("Id_multa"));
                m.setId_prestamo(rs.getInt("Id_prestamo"));
                m.setId_usuario(rs.getInt("Id_usuario"));
                m.setDias_retraso(rs.getInt("Dias_retraso"));
                m.setMonto(rs.getFloat("Monto"));
                m.setEstado(rs.getString("Estado"));
            }
        } catch (SQLException e) {
            System.out.println(" Error al buscar multa: " + e.toString());
        } finally {
            try { con.close(); } catch (SQLException e) { System.out.println(e.toString()); }
        }
        return m;
    }
}
