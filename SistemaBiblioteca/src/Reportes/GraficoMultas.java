/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reportes;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.renderer.category.BarRenderer;
import java.awt.Color;
import java.text.DecimalFormat;

/**
 * Clase para generar gráficos estadísticos de multas
 * @author Lima
 */
public class GraficoMultas {
    
    /**
     * Gráfico de multas pagadas vs sin pagar
     * Muestra la comparación del estado de las multas
     */
    public static void GraficarEstadoMultas() {
        Connection con;
        Conexion cn = new Conexion();
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            String sql = "SELECT Estado, COUNT(*) as cantidad, SUM(Monto) as total " +
                        "FROM multa " +
                        "GROUP BY Estado";
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            DefaultPieDataset dataset = new DefaultPieDataset();
            
            while(rs.next()) {
                String estado = rs.getString("Estado");
                int cantidad = rs.getInt("cantidad");
                double total = rs.getDouble("total");
                
                String label = String.format("%s (%d - %.2f Bs)", estado, cantidad, total);
                dataset.setValue(label, cantidad);
            }
            
            JFreeChart chart = ChartFactory.createPieChart(
                "Estado de Multas en el Sistema", 
                dataset, 
                true, 
                true, 
                false
            );
            
            PiePlot plot = (PiePlot) chart.getPlot();
            plot.setSectionOutlinesVisible(false);
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));
            
            // Colores personalizados
            plot.setSectionPaint("Activa", new Color(255, 102, 102)); // Rojo claro
            plot.setSectionPaint("Pagada", new Color(102, 255, 102)); // Verde claro
            plot.setSectionPaint("Inactiva", new Color(200, 200, 200)); // Gris
            
            ChartFrame frame = new ChartFrame("Estado de Multas", chart);
            frame.setSize(900, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
        } catch(SQLException e) {
            System.out.println("Error en gráfica de estado de multas: " + e.toString());
        }
    }
    
    /**
     * Gráfico de usuarios con más multas
     * Muestra el top 10 de usuarios con mayor cantidad de multas
     */
    public static void GraficarUsuariosConMasMultas() {
        Connection con;
        Conexion cn = new Conexion();
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            String sql = "SELECT CONCAT(u.Nombre, ' ', u.Apellido) as usuario, " +
                        "COUNT(m.Id_multa) as cantidad, " +
                        "SUM(m.Monto) as total_monto " +
                        "FROM multa m " +
                        "INNER JOIN usuario u ON m.Id_usuario = u.Id_usuario " +
                        "GROUP BY m.Id_usuario, u.Nombre, u.Apellido " +
                        "ORDER BY cantidad DESC " +
                        "LIMIT 10";
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            
            while(rs.next()) {
                String usuario = rs.getString("usuario");
                int cantidad = rs.getInt("cantidad");
                
                // Acortar nombres largos
                if(usuario.length() > 20) {
                    usuario = usuario.substring(0, 17) + "...";
                }
                
                dataset.addValue(cantidad, "Multas", usuario);
            }
            
            JFreeChart chart = ChartFactory.createBarChart(
                "Top 10 Usuarios con Más Multas",
                "Usuario",
                "Cantidad de Multas",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
            );
            
            CategoryPlot plot = chart.getCategoryPlot();
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setSeriesPaint(0, new Color(255, 153, 51)); // Naranja
            
            ChartFrame frame = new ChartFrame("Usuarios con Más Multas", chart);
            frame.setSize(1000, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
        } catch(SQLException e) {
            System.out.println("Error en gráfica de usuarios con multas: " + e.toString());
        }
    }
    
    /**
     * Gráfico de recaudación mensual por multas pagadas
     * Muestra el total recaudado por mes
     */
    public static void GraficarRecaudacionMensual() {
        Connection con;
        Conexion cn = new Conexion();
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            String sql = "SELECT DATE_FORMAT(mp.Fecha, '%Y-%m') as mes, " +
                        "COUNT(mp.Id_multa_pagada) as cantidad, " +
                        "SUM(m.Monto) as total_recaudado " +
                        "FROM multa_pagada mp " +
                        "INNER JOIN multa m ON mp.Id_multa = m.Id_multa " +
                        "WHERE mp.Estado = 1 " +
                        "GROUP BY DATE_FORMAT(mp.Fecha, '%Y-%m') " +
                        "ORDER BY mes DESC " +
                        "LIMIT 12";
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            
            while(rs.next()) {
                String mes = rs.getString("mes");
                double totalRecaudado = rs.getDouble("total_recaudado");
                
                dataset.addValue(totalRecaudado, "Recaudación (Bs)", mes);
            }
            
            JFreeChart chart = ChartFactory.createBarChart(
                "Recaudación Mensual por Multas Pagadas",
                "Mes (Año-Mes)",
                "Monto Recaudado (Bs)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
            );
            
            CategoryPlot plot = chart.getCategoryPlot();
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setSeriesPaint(0, new Color(51, 153, 255)); // Azul
            
            ChartFrame frame = new ChartFrame("Recaudación Mensual", chart);
            frame.setSize(1000, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
        } catch(SQLException e) {
            System.out.println("Error en gráfica de recaudación mensual: " + e.toString());
        }
    }
    
    /**
     * Gráfico de multas por rango de fechas
     * @param fechaInicio Fecha inicial en formato YYYY-MM-DD
     * @param fechaFin Fecha final en formato YYYY-MM-DD
     */
    public static void GraficarMultasPorFechas(String fechaInicio, String fechaFin) {
        Connection con;
        Conexion cn = new Conexion();
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            String sql = "SELECT m.Estado, COUNT(*) as cantidad, SUM(m.Monto) as total " +
                        "FROM multa m " +
                        "INNER JOIN prestamos p ON m.Id_prestamo = p.Id_prestamo " +
                        "WHERE p.Fecha_prestamo >= ? AND p.Fecha_prestamo <= ? " +
                        "GROUP BY m.Estado";
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, fechaInicio);
            ps.setString(2, fechaFin);
            rs = ps.executeQuery();
            
            DefaultPieDataset dataset = new DefaultPieDataset();
            
            while(rs.next()) {
                String estado = rs.getString("Estado");
                int cantidad = rs.getInt("cantidad");
                double total = rs.getDouble("total");
                
                String label = String.format("%s (%d - %.2f Bs)", estado, cantidad, total);
                dataset.setValue(label, cantidad);
            }
            
            JFreeChart chart = ChartFactory.createPieChart(
                "Multas por Periodo (" + fechaInicio + " a " + fechaFin + ")", 
                dataset, 
                true, 
                true, 
                false
            );
            
            PiePlot plot = (PiePlot) chart.getPlot();
            plot.setSectionOutlinesVisible(false);
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));
            
            ChartFrame frame = new ChartFrame("Multas por Periodo", chart);
            frame.setSize(900, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
        } catch(SQLException e) {
            System.out.println("Error en gráfica de multas por fechas: " + e.toString());
        }
    }
    
    /**
     * Gráfico de libros que generan más multas
     * Muestra los libros más problemáticos
     */
    public static void GraficarLibrosConMasMultas() {
        Connection con;
        Conexion cn = new Conexion();
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            String sql = "SELECT l.Titulo, COUNT(m.Id_multa) as cantidad, " +
                        "SUM(m.Monto) as total_monto " +
                        "FROM multa m " +
                        "INNER JOIN prestamos p ON m.Id_prestamo = p.Id_prestamo " +
                        "INNER JOIN libro l ON p.Id_libro = l.Id_libro " +
                        "GROUP BY l.Id_libro, l.Titulo " +
                        "ORDER BY cantidad DESC " +
                        "LIMIT 10";
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            
            while(rs.next()) {
                String titulo = rs.getString("Titulo");
                int cantidad = rs.getInt("cantidad");
                
                if(titulo.length() > 25) {
                    titulo = titulo.substring(0, 22) + "...";
                }
                
                dataset.addValue(cantidad, "Multas", titulo);
            }
            
            JFreeChart chart = ChartFactory.createBarChart(
                "Top 10 Libros que Generan Más Multas",
                "Libro",
                "Cantidad de Multas",
                dataset,
                PlotOrientation.HORIZONTAL,
                true,
                true,
                false
            );
            
            CategoryPlot plot = chart.getCategoryPlot();
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setSeriesPaint(0, new Color(204, 0, 0)); // Rojo oscuro
            
            ChartFrame frame = new ChartFrame("Libros con Más Multas", chart);
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
        } catch(SQLException e) {
            System.out.println("Error en gráfica de libros con multas: " + e.toString());
        }
    }
    
    /**
     * Gráfico de distribución de montos de multas
     * Muestra rangos de montos más comunes
     */
    public static void GraficarDistribucionMontos() {
        Connection con;
        Conexion cn = new Conexion();
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            String sql = "SELECT " +
                        "CASE " +
                        "    WHEN Monto <= 5 THEN '0-5 Bs' " +
                        "    WHEN Monto <= 10 THEN '6-10 Bs' " +
                        "    WHEN Monto <= 20 THEN '11-20 Bs' " +
                        "    WHEN Monto <= 50 THEN '21-50 Bs' " +
                        "    ELSE 'Más de 50 Bs' " +
                        "END as rango_monto, " +
                        "COUNT(*) as cantidad " +
                        "FROM multa " +
                        "GROUP BY rango_monto " +
                        "ORDER BY " +
                        "CASE rango_monto " +
                        "    WHEN '0-5 Bs' THEN 1 " +
                        "    WHEN '6-10 Bs' THEN 2 " +
                        "    WHEN '11-20 Bs' THEN 3 " +
                        "    WHEN '21-50 Bs' THEN 4 " +
                        "    ELSE 5 " +
                        "END";
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            DefaultPieDataset dataset = new DefaultPieDataset();
            
            while(rs.next()) {
                String rango = rs.getString("rango_monto");
                int cantidad = rs.getInt("cantidad");
                
                dataset.setValue(rango + " (" + cantidad + ")", cantidad);
            }
            
            JFreeChart chart = ChartFactory.createPieChart(
                "Distribución de Montos de Multas", 
                dataset, 
                true, 
                true, 
                false
            );
            
            PiePlot plot = (PiePlot) chart.getPlot();
            plot.setSectionOutlinesVisible(false);
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));
            
            ChartFrame frame = new ChartFrame("Distribución de Montos", chart);
            frame.setSize(900, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
        } catch(SQLException e) {
            System.out.println("Error en gráfica de distribución de montos: " + e.toString());
        }
    }
    
    /**
     * Gráfico de promedio de días de retraso por mes
     */
    public static void GraficarPromedioDiasRetraso() {
        Connection con;
        Conexion cn = new Conexion();
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            String sql = "SELECT DATE_FORMAT(mp.Fecha, '%Y-%m') as mes, " +
                        "AVG(m.Dias_retraso) as promedio_dias, " +
                        "COUNT(*) as cantidad " +
                        "FROM multa_pagada mp " +
                        "INNER JOIN multa m ON mp.Id_multa = m.Id_multa " +
                        "WHERE mp.Estado = 1 " +
                        "GROUP BY DATE_FORMAT(mp.Fecha, '%Y-%m') " +
                        "ORDER BY mes DESC " +
                        "LIMIT 12";
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            
            while(rs.next()) {
                String mes = rs.getString("mes");
                double promedioDias = rs.getDouble("promedio_dias");
                
                dataset.addValue(promedioDias, "Promedio días", mes);
            }
            
            JFreeChart chart = ChartFactory.createBarChart(
                "Promedio de Días de Retraso por Mes",
                "Mes (Año-Mes)",
                "Promedio de Días",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
            );
            
            CategoryPlot plot = chart.getCategoryPlot();
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setSeriesPaint(0, new Color(255, 153, 153)); // Rosa
            
            ChartFrame frame = new ChartFrame("Promedio Días de Retraso", chart);
            frame.setSize(1000, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
        } catch(SQLException e) {
            System.out.println("Error en gráfica de promedio días: " + e.toString());
        }
    }
    
}