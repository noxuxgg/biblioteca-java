/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*package Modelo;

/**
 *
 * @author LIMA
 */
//public class Multa_pagadaDAO {
    
//}*/

package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Multa_pagadaDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    // Registrar pago de multa y generar factura automáticamente
    public boolean registrarPagoMulta(Multa_pagada mp) {
        String sql = "INSERT INTO multa_pagada (Id_multa, Fecha, Estado) VALUES (?,?,?)";
        try {
            con = cn.getConnection();
            con.setAutoCommit(false); // Iniciar transacción
            
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, mp.getId_multa());
            ps.setTimestamp(2, mp.getFecha());
            ps.setInt(3, mp.getEstado());
            ps.execute();
            
            // Obtener el ID generado
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int idMultaPagada = rs.getInt(1);
                
                // Generar factura automáticamente
                if (generarFactura(idMultaPagada, mp.getId_multa())) {
                    // Actualizar estado de la multa a "Pagada"
                    actualizarEstadoMulta(mp.getId_multa());
                    con.commit(); // Confirmar transacción
                    return true;
                } else {
                    con.rollback(); // Revertir si falla la factura
                    return false;
                }
            }
            
            con.rollback();
            return false;
            
        } catch (SQLException e) {
            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) {
                System.out.println("Error al revertir transacción: " + ex.toString());
            }
            System.out.println("Error al registrar pago de multa: " + e.toString());
            return false;
        } finally {
            try {
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
public Multa_pagada obtenerDatosFactura(int idMultaPagada) {
    Multa_pagada mp = null;
    String sql = """
        SELECT 
            mp.Id_multa_pagada,
            mp.Id_multa,
            mp.Fecha,
            mp.Estado,
            m.Id_usuario,
            m.Id_prestamo,
            m.Dias_retraso,
            m.Monto,
            u.Nombre,
            u.Apellido,
            u.Carnet,
            u.Telefono,
            u.Domicilo,
            l.Titulo,
            f.Numero_factura
        FROM multa_pagada mp
        INNER JOIN multa m ON mp.Id_multa = m.Id_multa
        INNER JOIN usuario u ON m.Id_usuario = u.Id_usuario
        INNER JOIN prestamos p ON m.Id_prestamo = p.Id_prestamo
        INNER JOIN libro l ON p.Id_libro = l.Id_libro
        LEFT JOIN factura f ON mp.Id_multa_pagada = f.Id_multa_pagada
        WHERE mp.Id_multa_pagada = ?
    """;
    
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idMultaPagada);
        rs = ps.executeQuery();
        
        if (rs.next()) {
            mp = new Multa_pagada();
            
            // Datos básicos
            mp.setId_multa_pagada(rs.getInt("Id_multa_pagada"));
            mp.setId_multa(rs.getInt("Id_multa"));
            mp.setFecha(rs.getTimestamp("Fecha"));
            mp.setEstado(rs.getInt("Estado"));
            
            // Datos del usuario (para la factura)
            mp.setNombreUsuario(rs.getString("Nombre"));
            mp.setApellidoUsuario(rs.getString("Apellido"));
            mp.setCarnetUsuario(rs.getString("Carnet"));
            mp.setTelefonoUsuario(rs.getString("Telefono"));
            mp.setDomicilioUsuario(rs.getString("Domicilo"));
            
            // Datos de la multa
            mp.setMontoPagado(rs.getFloat("Monto"));
            mp.setDiasRetraso(rs.getInt("Dias_retraso"));
            mp.setIdPrestamo(rs.getInt("Id_prestamo"));
            
            // Datos del libro
            mp.setTituloLibro(rs.getString("Titulo"));
            
            // Número de factura
            mp.setNumeroFactura(rs.getString("Numero_factura"));
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener datos para factura: " + e.toString());
        e.printStackTrace();
    } finally {
        try { 
            if (con != null) con.close(); 
        } catch (SQLException e) { 
            System.out.println(e.toString()); 
        }
    }
    
    return mp;
}
    // Generar factura automáticamente
    private boolean generarFactura(int idMultaPagada, int idMulta) {
        String sql = """
            INSERT INTO factura (Id_multa_pagada, Id_usuario, Numero_factura, Fecha_emision, Monto, Concepto, Estado)
            SELECT ?, m.Id_usuario, ?, NOW(), m.Monto, 
                   CONCAT('Pago de multa por ', m.Dias_retraso, ' día(s) de retraso - Préstamo ID: ', m.Id_prestamo),
                   'Emitida'
            FROM multa m
            WHERE m.Id_multa = ?
        """;
        
        try {
            String numeroFactura = generarNumeroFactura(idMultaPagada);
            ps = con.prepareStatement(sql);
            ps.setInt(1, idMultaPagada);
            ps.setString(2, numeroFactura);
            ps.setInt(3, idMulta);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al generar factura: " + e.toString());
            return false;
        }
    }

    // Generar número de factura único
    private String generarNumeroFactura(int idMultaPagada) {
        LocalDateTime now = LocalDateTime.now();
        String fecha = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return String.format("FACT-%s-%06d", fecha, idMultaPagada);
    }

    // Actualizar estado de la multa a "Pagada"
    private void actualizarEstadoMulta(int idMulta) throws SQLException {
        String sql = "UPDATE multa SET Estado = 'Pagada' WHERE Id_multa = ?";
        ps = con.prepareStatement(sql);
        ps.setInt(1, idMulta);
        ps.execute();
    }

    // Listar todos los pagos con información completa para factura
    public List<Multa_pagada> listarPagosMultas() {
        List<Multa_pagada> lista = new ArrayList<>();
        String sql = """
            SELECT mp.Id_multa_pagada, mp.Id_multa, mp.Fecha, mp.Estado,
                   u.Nombre, u.Apellido, u.Carnet, u.Telefono, u.Domicilo,
                   m.Monto, m.Dias_retraso, m.Id_prestamo,
                   l.Titulo,
                   f.Numero_factura
            FROM multa_pagada mp
            INNER JOIN multa m ON mp.Id_multa = m.Id_multa
            INNER JOIN usuario u ON m.Id_usuario = u.Id_usuario
            INNER JOIN prestamos p ON m.Id_prestamo = p.Id_prestamo
            INNER JOIN libro l ON p.Id_libro = l.Id_libro
            LEFT JOIN factura f ON mp.Id_multa_pagada = f.Id_multa_pagada
            ORDER BY mp.Fecha DESC;
        """;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Multa_pagada mp = new Multa_pagada();
                mp.setId_multa_pagada(rs.getInt("Id_multa_pagada"));
                mp.setId_multa(rs.getInt("Id_multa"));
                mp.setFecha(rs.getTimestamp("Fecha"));
                mp.setEstado(rs.getInt("Estado"));
                mp.setNombreUsuario(rs.getString("Nombre"));
                mp.setApellidoUsuario(rs.getString("Apellido"));
                mp.setCarnetUsuario(rs.getString("Carnet"));
                mp.setTelefonoUsuario(rs.getString("Telefono"));
                mp.setDomicilioUsuario(rs.getString("Domicilo"));
                mp.setMontoPagado(rs.getFloat("Monto"));
                mp.setDiasRetraso(rs.getInt("Dias_retraso"));
                mp.setIdPrestamo(rs.getInt("Id_prestamo"));
                mp.setTituloLibro(rs.getString("Titulo"));
                mp.setNumeroFactura(rs.getString("Numero_factura"));
                lista.add(mp);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar pagos de multas: " + e.toString());
        } finally {
            try { con.close(); } catch (SQLException e) { System.out.println(e.toString()); }
        }
        return lista;
    }

    // Buscar pago por ID con toda la información para factura
    public Multa_pagada buscarPagoPorId(int id) {
        Multa_pagada mp = null;
        String sql = """
            SELECT mp.Id_multa_pagada, mp.Id_multa, mp.Fecha, mp.Estado,
                   u.Nombre, u.Apellido, u.Carnet, u.Telefono, u.Domicilo,
                   m.Monto, m.Dias_retraso, m.Id_prestamo,
                   l.Titulo,
                   f.Numero_factura
            FROM multa_pagada mp
            INNER JOIN multa m ON mp.Id_multa = m.Id_multa
            INNER JOIN usuario u ON m.Id_usuario = u.Id_usuario
            INNER JOIN prestamos p ON m.Id_prestamo = p.Id_prestamo
            INNER JOIN libro l ON p.Id_libro = l.Id_libro
            LEFT JOIN factura f ON mp.Id_multa_pagada = f.Id_multa_pagada
            WHERE mp.Id_multa_pagada = ?;
        """;
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                mp = new Multa_pagada();
                mp.setId_multa_pagada(rs.getInt("Id_multa_pagada"));
                mp.setId_multa(rs.getInt("Id_multa"));
                mp.setFecha(rs.getTimestamp("Fecha"));
                mp.setEstado(rs.getInt("Estado"));
                mp.setNombreUsuario(rs.getString("Nombre"));
                mp.setApellidoUsuario(rs.getString("Apellido"));
                mp.setCarnetUsuario(rs.getString("Carnet"));
                mp.setTelefonoUsuario(rs.getString("Telefono"));
                mp.setDomicilioUsuario(rs.getString("Domicilo"));
                mp.setMontoPagado(rs.getFloat("Monto"));
                mp.setDiasRetraso(rs.getInt("Dias_retraso"));
                mp.setIdPrestamo(rs.getInt("Id_prestamo"));
                mp.setTituloLibro(rs.getString("Titulo"));
                mp.setNumeroFactura(rs.getString("Numero_factura"));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar pago: " + e.toString());
        } finally {
            try { con.close(); } catch (SQLException e) { System.out.println(e.toString()); }
        }
        return mp;
    }

 public List<Multa_pagada> listarPagosPorCarnet(String carnet) {
    List<Multa_pagada> lista = new ArrayList<>();
    String sql = """
        SELECT 
            mp.Id_multa_pagada,
            mp.Id_multa,
            mp.Fecha,
            mp.Estado,
            m.Id_prestamo,
            m.Dias_retraso,
            m.Monto,
            u.Nombre,
            u.Apellido,
            u.Carnet,
            u.Telefono,
            u.Domicilo,
            l.Titulo,
            f.Numero_factura
        FROM multa_pagada mp
        INNER JOIN multa m ON mp.Id_multa = m.Id_multa
        INNER JOIN usuario u ON m.Id_usuario = u.Id_usuario
        INNER JOIN prestamos p ON m.Id_prestamo = p.Id_prestamo
        INNER JOIN libro l ON p.Id_libro = l.Id_libro
        LEFT JOIN factura f ON mp.Id_multa_pagada = f.Id_multa_pagada
        WHERE u.Carnet LIKE ?
        ORDER BY mp.Fecha DESC
    """;

    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, "%" + carnet + "%");
        rs = ps.executeQuery();

        while (rs.next()) {
            Multa_pagada mp = new Multa_pagada();
            mp.setId_multa_pagada(rs.getInt("Id_multa_pagada"));
            mp.setId_multa(rs.getInt("Id_multa"));
            mp.setFecha(rs.getTimestamp("Fecha"));
            mp.setEstado(rs.getInt("Estado"));
            mp.setIdPrestamo(rs.getInt("Id_prestamo"));
            mp.setDiasRetraso(rs.getInt("Dias_retraso"));
            mp.setMontoPagado(rs.getFloat("Monto"));
            mp.setNombreUsuario(rs.getString("Nombre"));
            mp.setApellidoUsuario(rs.getString("Apellido"));
            mp.setCarnetUsuario(rs.getString("Carnet"));
            mp.setTelefonoUsuario(rs.getString("Telefono"));
            mp.setDomicilioUsuario(rs.getString("Domicilo"));
            mp.setTituloLibro(rs.getString("Titulo"));
            mp.setNumeroFactura(rs.getString("Numero_factura"));
            
            lista.add(mp);
        }
    } catch (SQLException e) {
        System.out.println("Error al listar pagos por carnet: " + e.toString());
    } finally {
        try { con.close(); } catch (SQLException e) { System.out.println(e.toString()); }
    }
    
    return lista;
}

    // Buscar pagos por estado (1 = Pagado, 0 = Pendiente)
    public List<Multa_pagada> listarPagosPorEstado(int estado) {
        List<Multa_pagada> lista = new ArrayList<>();
        String sql = """
            SELECT mp.Id_multa_pagada, mp.Id_multa, mp.Fecha, mp.Estado,
                   u.Nombre, u.Apellido, u.Carnet, u.Telefono, u.Domicilo,
                   m.Monto, m.Dias_retraso, m.Id_prestamo,
                   l.Titulo,
                   f.Numero_factura
            FROM multa_pagada mp
            INNER JOIN multa m ON mp.Id_multa = m.Id_multa
            INNER JOIN usuario u ON m.Id_usuario = u.Id_usuario
            INNER JOIN prestamos p ON m.Id_prestamo = p.Id_prestamo
            INNER JOIN libro l ON p.Id_libro = l.Id_libro
            LEFT JOIN factura f ON mp.Id_multa_pagada = f.Id_multa_pagada
            WHERE mp.Estado = ?
            ORDER BY mp.Fecha DESC;
        """;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, estado);
            rs = ps.executeQuery();

            while (rs.next()) {
                Multa_pagada mp = new Multa_pagada();
                mp.setId_multa_pagada(rs.getInt("Id_multa_pagada"));
                mp.setId_multa(rs.getInt("Id_multa"));
                mp.setFecha(rs.getTimestamp("Fecha"));
                mp.setEstado(rs.getInt("Estado"));
                mp.setNombreUsuario(rs.getString("Nombre"));
                mp.setApellidoUsuario(rs.getString("Apellido"));
                mp.setCarnetUsuario(rs.getString("Carnet"));
                mp.setTelefonoUsuario(rs.getString("Telefono"));
                mp.setDomicilioUsuario(rs.getString("Domicilo"));
                mp.setMontoPagado(rs.getFloat("Monto"));
                mp.setDiasRetraso(rs.getInt("Dias_retraso"));
                mp.setIdPrestamo(rs.getInt("Id_prestamo"));
                mp.setTituloLibro(rs.getString("Titulo"));
                mp.setNumeroFactura(rs.getString("Numero_factura"));
                lista.add(mp);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar pagos por estado: " + e.toString());
        } finally {
            try { con.close(); } catch (SQLException e) { System.out.println(e.toString()); }
        }
        return lista;
    }

    public List<Multa_pagada> listarPagosPagados() {
    List<Multa_pagada> lista = new ArrayList<>();
    String sql = """
        SELECT 
            mp.Id_multa_pagada,
            mp.Id_multa,
            mp.Fecha,
            mp.Estado,
            m.Id_prestamo,
            m.Dias_retraso,
            m.Monto,
            CONCAT(u.Nombre, ' ', u.Apellido) AS nombreCompleto,
            u.Carnet,
            u.Telefono,
            u.Domicilo,
            l.Titulo,
            f.Numero_factura
        FROM multa_pagada mp
        INNER JOIN multa m ON mp.Id_multa = m.Id_multa
        INNER JOIN usuario u ON m.Id_usuario = u.Id_usuario
        INNER JOIN prestamos p ON m.Id_prestamo = p.Id_prestamo
        INNER JOIN libro l ON p.Id_libro = l.Id_libro
        LEFT JOIN factura f ON mp.Id_multa_pagada = f.Id_multa_pagada
        WHERE mp.Estado = 1
        ORDER BY mp.Fecha DESC
    """;

    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            Multa_pagada mp = new Multa_pagada();
            mp.setId_multa_pagada(rs.getInt("Id_multa_pagada"));
            mp.setId_multa(rs.getInt("Id_multa"));
            mp.setFecha(rs.getTimestamp("Fecha"));
            mp.setEstado(rs.getInt("Estado"));
            mp.setIdPrestamo(rs.getInt("Id_prestamo"));
            mp.setDiasRetraso(rs.getInt("Dias_retraso"));
            mp.setMontoPagado(rs.getFloat("Monto"));
            
            // Separar nombre completo
            String[] nombreApellido = rs.getString("nombreCompleto").split(" ", 2);
            mp.setNombreUsuario(nombreApellido[0]);
            mp.setApellidoUsuario(nombreApellido.length > 1 ? nombreApellido[1] : "");
            
            mp.setCarnetUsuario(rs.getString("Carnet"));
            mp.setTelefonoUsuario(rs.getString("Telefono"));
            mp.setDomicilioUsuario(rs.getString("Domicilo"));
            mp.setTituloLibro(rs.getString("Titulo"));
            mp.setNumeroFactura(rs.getString("Numero_factura"));
            
            lista.add(mp);
        }
    } catch (SQLException e) {
        System.out.println("Error al listar pagos: " + e.toString());
    } finally {
        try { con.close(); } catch (SQLException e) { System.out.println(e.toString()); }
    }
    
    return lista;
}

    // Listar solo pagos pendientes
    public List<Multa_pagada> listarPagosPendientes() {
        return listarPagosPorEstado(0);
    }

    // Eliminar (lógico) - cambiar estado a pendiente
    public boolean eliminarPago(int id) {
        String sql = "UPDATE multa_pagada SET Estado = 0 WHERE Id_multa_pagada = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar pago: " + e.toString());
            return false;
        } finally {
            try { con.close(); } catch (SQLException e) { System.out.println(e.toString()); }
        }
    }

    // Modificar pago
    public boolean modificarPago(Multa_pagada mp) {
        String sql = "UPDATE multa_pagada SET Fecha=?, Estado=? WHERE Id_multa_pagada=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setTimestamp(1, mp.getFecha());
            ps.setInt(2, mp.getEstado());
            ps.setInt(3, mp.getId_multa_pagada());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al modificar pago: " + e.toString());
            return false;
        } finally {
            try { con.close(); } catch (SQLException e) { System.out.println(e.toString()); }
        }
    }

   

    // Buscar pagos por rango de fechas
    public List<Multa_pagada> listarPagosPorFechas(String fechaInicio, String fechaFin) {
        List<Multa_pagada> lista = new ArrayList<>();
        String sql = """
            SELECT mp.Id_multa_pagada, mp.Id_multa, mp.Fecha, mp.Estado,
                   u.Nombre, u.Apellido, u.Carnet, u.Telefono, u.Domicilo,
                   m.Monto, m.Dias_retraso, m.Id_prestamo,
                   l.Titulo,
                   f.Numero_factura
            FROM multa_pagada mp
            INNER JOIN multa m ON mp.Id_multa = m.Id_multa
            INNER JOIN usuario u ON m.Id_usuario = u.Id_usuario
            INNER JOIN prestamos p ON m.Id_prestamo = p.Id_prestamo
            INNER JOIN libro l ON p.Id_libro = l.Id_libro
            LEFT JOIN factura f ON mp.Id_multa_pagada = f.Id_multa_pagada
            WHERE DATE(mp.Fecha) BETWEEN ? AND ?
            ORDER BY mp.Fecha DESC;
        """;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, fechaInicio);
            ps.setString(2, fechaFin);
            rs = ps.executeQuery();

            while (rs.next()) {
                Multa_pagada mp = new Multa_pagada();
                mp.setId_multa_pagada(rs.getInt("Id_multa_pagada"));
                mp.setId_multa(rs.getInt("Id_multa"));
                mp.setFecha(rs.getTimestamp("Fecha"));
                mp.setEstado(rs.getInt("Estado"));
                mp.setNombreUsuario(rs.getString("Nombre"));
                mp.setApellidoUsuario(rs.getString("Apellido"));
                mp.setCarnetUsuario(rs.getString("Carnet"));
                mp.setTelefonoUsuario(rs.getString("Telefono"));
                mp.setDomicilioUsuario(rs.getString("Domicilo"));
                mp.setMontoPagado(rs.getFloat("Monto"));
                mp.setDiasRetraso(rs.getInt("Dias_retraso"));
                mp.setIdPrestamo(rs.getInt("Id_prestamo"));
                mp.setTituloLibro(rs.getString("Titulo"));
                mp.setNumeroFactura(rs.getString("Numero_factura"));
                lista.add(mp);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar pagos por fechas: " + e.toString());
        } finally {
            try { con.close(); } catch (SQLException e) { System.out.println(e.toString()); }
        }
        return lista;
    }
}