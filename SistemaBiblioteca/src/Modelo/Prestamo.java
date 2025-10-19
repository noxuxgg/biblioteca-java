/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Henry Quispe
 */
public class Prestamo {
    private int id_prestamo;
    private int id_usuario;
    private int id_libro;
    private String fecha_prestamo;
    private String fecha_devolucion;
    private String estado;
    private String carnetUsuario;
    private String nombreUsuario;
    private String TituloLibro;
    private String EstadoPrestamo;
    private String codigoLibro;
    public Prestamo(){
        
    }

    public Prestamo(int id_prestamo, int id_usuario, int id_libro, String fecha_prestamo, String fecha_devolucion, String estado, String carnetUsuario, String nombreUsuario, String TituloLibro, String EstadoPrestamo, String codigoLibro) {
        this.id_prestamo = id_prestamo;
        this.id_usuario = id_usuario;
        this.id_libro = id_libro;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_devolucion = fecha_devolucion;
        this.estado = estado;
        this.carnetUsuario = carnetUsuario;
        this.nombreUsuario = nombreUsuario;
        this.TituloLibro = TituloLibro;
        this.EstadoPrestamo = EstadoPrestamo;
        this.codigoLibro = codigoLibro;
    }

    

  

   

    public int getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public String getFecha_prestamo() {
        return fecha_prestamo;
    }

    public void setFecha_prestamo(String fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    public String getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(String fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCarnetUsuario() {
        return carnetUsuario;
    }

    public void setCarnetUsuario(String carnetUsuario) {
        this.carnetUsuario = carnetUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTituloLibro() {
        return TituloLibro;
    }

    public void setTituloLibro(String TituloLibro) {
        this.TituloLibro = TituloLibro;
    }

    public String getEstadoPrestamo() {
        return EstadoPrestamo;
    }

    public void setEstadoPrestamo(String EstadoPrestamo) {
        this.EstadoPrestamo = EstadoPrestamo;
    }

    public String getCodigoLibro() {
        return codigoLibro;
    }

    public void setCodigoLibro(String codigoLibro) {
        this.codigoLibro = codigoLibro;
    }
    
    
}
