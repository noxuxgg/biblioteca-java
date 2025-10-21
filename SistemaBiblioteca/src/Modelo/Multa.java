/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author LIMA
 */
public class Multa {
    private int id_multa;
    private int id_prestamo;
    private int id_usuario;
    private int dias_retraso;
    private float monto;
    private String estado;
 
    // Campos opcionales para mostrar información relacionada
    private String nombreUsuario;
    private String nombreLibro;

    // 🔹 Constructor vacío (requerido por frameworks o para inicializaciones simples)
    public Multa() {
    }

    // 🔹 Constructor con todos los campos principales
    public Multa(int id_multa, int id_prestamo, int id_usuario, int dias_retraso, float monto, String estado) {
        this.id_multa = id_multa;
        this.id_prestamo = id_prestamo;
        this.id_usuario = id_usuario;
        this.dias_retraso = dias_retraso;
        this.monto = monto;
        this.estado = estado;
  
    }

    // 🔹 Constructor extendido con nombres descriptivos (útil para consultas con JOIN)
    public Multa(int id_multa, int id_prestamo, int id_usuario, int dias_retraso, float monto, String estado, String nombreUsuario, String nombreLibro) {
        this.id_multa = id_multa;
        this.id_prestamo = id_prestamo;
        this.id_usuario = id_usuario;
        this.dias_retraso = dias_retraso;
        this.monto = monto;
        this.estado = estado;
        this.nombreUsuario = nombreUsuario;
        this.nombreLibro = nombreLibro;
    }

    // 🔹 Getters y Setters
    public int getId_multa() {
        return id_multa;
    }

    public void setId_multa(int id_multa) {
        this.id_multa = id_multa;
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

    public int getDias_retraso() {
        return dias_retraso;
    }

    public void setDias_retraso(int dias_retraso) {
        this.dias_retraso = dias_retraso;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    // 🔹 Método para mostrar información útil de la multa (opcional)
    @Override
    public String toString() {
        return "Multa{" +
                "id_multa=" + id_multa +
                ", id_prestamo=" + id_prestamo +
                ", id_usuario=" + id_usuario +
                ", dias_retraso=" + dias_retraso +
                ", monto=" + monto +
                ", estado='" + estado + '\'' +
                ", usuario='" + nombreUsuario + '\'' +
                ", libro='" + nombreLibro + '\'' +
                '}';
    }
}
