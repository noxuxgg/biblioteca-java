/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reportes;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import java.sql.SQLException;

/**
 *
 * @author Henry Quispe
 */
public class Grafico {
    public static void GraficarPrestamos(String fechaInicio, String fechaFin){
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    try {
        String sql = "SELECT l.titulo, COUNT(p.id_prestamo) as cantidad " +
                     "FROM prestamos p " +
                     "INNER JOIN libro l ON p.id_libro = l.id_libro " +
                     "WHERE p.fecha_prestamo >= ? AND p.fecha_prestamo <= ? " +
                     "AND p.estado = 1 " +
                     "GROUP BY l.titulo " +
                     "ORDER BY cantidad DESC";
        
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, fechaInicio);
        ps.setString(2, fechaFin);
        rs = ps.executeQuery();
        
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        while(rs.next()){
            String tituloLibro = rs.getString("titulo");
            int cantidad = rs.getInt("cantidad");
            
            if(tituloLibro.length() > 20){
                tituloLibro = tituloLibro.substring(0, 17) + "...";
            }
            
            dataset.setValue(tituloLibro, cantidad);
        }
        
        JFreeChart chart = ChartFactory.createPieChart(
            "Préstamos por Libro (" + fechaInicio + " a " + fechaFin + ")", 
            dataset, 
            true, 
            true, 
            false
        );
        
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionOutlinesVisible(false);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));
        plot.setSimpleLabels(true);
        
        ChartFrame frame = new ChartFrame("Reporte de Préstamos por Libro", chart);
        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    } catch(SQLException e) {
        System.out.println("Error en gráfica: " + e.toString());
    }
}
    public static void GraficarLibrosMasPrestados(){
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    try {
        String sql = "SELECT l.titulo, COUNT(p.id_prestamo) as cantidad " +
                     "FROM prestamos p " +
                     "INNER JOIN libro l ON p.id_libro = l.id_libro " +
                     "WHERE p.estado = 1 " +
                     "GROUP BY l.titulo " +
                     "ORDER BY cantidad DESC " +
                     "LIMIT 10";
        
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        while(rs.next()){
            String tituloLibro = rs.getString("titulo");
            int cantidad = rs.getInt("cantidad");
            
            if(tituloLibro.length() > 20){
                tituloLibro = tituloLibro.substring(0, 17) + "...";
            }
            
            dataset.setValue(tituloLibro, cantidad);
        }
        
        JFreeChart chart = ChartFactory.createPieChart(
            "Top 10 Libros Más Prestados", 
            dataset, 
            true, 
            true, 
            false
        );
        
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionOutlinesVisible(false);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));
        plot.setSimpleLabels(true);
        
        ChartFrame frame = new ChartFrame("Reporte de Libros Más Prestados", chart);
        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    } catch(SQLException e) {
        System.out.println("Error en gráfica: " + e.toString());
    }
}
    
}
