/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Timestamp;

/**
 *
 * @author LIMA
 */
public class Multa_pagada {
    private int id_multa_pagada;
    private int id_multa;
    private Timestamp fecha;
    private int estado;
    
    // Campos opcionales para mostrar información relacionada y generar factura
    private String nombreUsuario;
    private String apellidoUsuario;
    private String carnetUsuario;
    private String telefonoUsuario;
    private String domicilioUsuario;
    private float montoPagado;
    private int diasRetraso;
    private String nombreLibro;
    private String tituloLibro;
    private int idPrestamo;
    private String numeroFactura;
    
    // 🔹 Constructor vacío (requerido por frameworks o para inicializaciones simples)
    public Multa_pagada() {
    }
    
    // 🔹 Constructor con todos los campos principales
    public Multa_pagada(int id_multa_pagada, int id_multa, Timestamp fecha, int estado) {
        this.id_multa_pagada = id_multa_pagada;
        this.id_multa = id_multa;
        this.fecha = fecha;
        this.estado = estado;
    }
    
    // 🔹 Constructor extendido con datos básicos para factura
    public Multa_pagada(int id_multa_pagada, int id_multa, Timestamp fecha, int estado, 
                        String nombreUsuario, String apellidoUsuario, float montoPagado) {
        this.id_multa_pagada = id_multa_pagada;
        this.id_multa = id_multa;
        this.fecha = fecha;
        this.estado = estado;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.montoPagado = montoPagado;
    }
    
    // 🔹 Constructor completo con todos los datos necesarios para generar factura (útil para consultas con JOIN)
    public Multa_pagada(int id_multa_pagada, int id_multa, Timestamp fecha, int estado,
                        String nombreUsuario, String apellidoUsuario, String carnetUsuario,
                        String telefonoUsuario, String domicilioUsuario, float montoPagado,
                        int diasRetraso, String tituloLibro, int idPrestamo, String numeroFactura) {
        this.id_multa_pagada = id_multa_pagada;
        this.id_multa = id_multa;
        this.fecha = fecha;
        this.estado = estado;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.carnetUsuario = carnetUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.domicilioUsuario = domicilioUsuario;
        this.montoPagado = montoPagado;
        this.diasRetraso = diasRetraso;
        this.tituloLibro = tituloLibro;
        this.idPrestamo = idPrestamo;
        this.numeroFactura = numeroFactura;
    }
    
    // 🔹 Getters y Setters
    public int getId_multa_pagada() {
        return id_multa_pagada;
    }
    
    public void setId_multa_pagada(int id_multa_pagada) {
        this.id_multa_pagada = id_multa_pagada;
    }
    
    public int getId_multa() {
        return id_multa;
    }
    
    public void setId_multa(int id_multa) {
        this.id_multa = id_multa;
    }
    
    public Timestamp getFecha() {
        return fecha;
    }
    
    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
    
    public int getEstado() {
        return estado;
    }
    
    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    public String getApellidoUsuario() {
        return apellidoUsuario;
    }
    
    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }
    
    public String getCarnetUsuario() {
        return carnetUsuario;
    }
    
    public void setCarnetUsuario(String carnetUsuario) {
        this.carnetUsuario = carnetUsuario;
    }
    
    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }
    
    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }
    
    public String getDomicilioUsuario() {
        return domicilioUsuario;
    }
    
    public void setDomicilioUsuario(String domicilioUsuario) {
        this.domicilioUsuario = domicilioUsuario;
    }
    
    public float getMontoPagado() {
        return montoPagado;
    }
    
    public void setMontoPagado(float montoPagado) {
        this.montoPagado = montoPagado;
    }
    
    public int getDiasRetraso() {
        return diasRetraso;
    }
    
    public void setDiasRetraso(int diasRetraso) {
        this.diasRetraso = diasRetraso;
    }
    
    public String getNombreLibro() {
        return nombreLibro;
    }
    
    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }
    
    public String getTituloLibro() {
        return tituloLibro;
    }
    
    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }
    
    public int getIdPrestamo() {
        return idPrestamo;
    }
    
    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }
    
    public String getNumeroFactura() {
        return numeroFactura;
    }
    
    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }
    
    // 🔹 Métodos auxiliares para la factura
    
    /**
     * Obtiene el nombre completo del usuario
     * @return Nombre y apellido del usuario
     */
    public String getNombreCompletoUsuario() {
        if (nombreUsuario != null && apellidoUsuario != null) {
            return nombreUsuario + " " + apellidoUsuario;
        }
        return nombreUsuario != null ? nombreUsuario : "Sin nombre";
    }
    
    /**
     * Obtiene el concepto de pago para la factura
     * @return Descripción del concepto
     */
    public String getConceptoPago() {
        return "Pago de multa por " + diasRetraso + " día(s) de retraso en la devolución del libro: " + 
               (tituloLibro != null ? tituloLibro : "Sin título");
    }
    
    /**
     * Verifica si el pago está confirmado
     * @return true si está pagado (estado = 1)
     */
    public boolean estaPagado() {
        return this.estado == 1;
    }
    
    /**
     * Obtiene el estado como texto
     * @return "Pagado" o "Pendiente"
     */
    public String getEstadoTexto() {
        return this.estado == 1 ? "Pagado" : "Pendiente";
    }
    
    /**
     * Formatea el monto con 2 decimales
     * @return Monto formateado como String
     */
    public String getMontoFormateado() {
        return String.format("%.2f Bs.", montoPagado);
    }
    
    /**
     * Obtiene la fecha formateada para mostrar en factura
     * @return Fecha en formato legible
     */
    public String getFechaFormateada() {
        if (fecha != null) {
            return fecha.toString().substring(0, 19); // Formato: yyyy-MM-dd HH:mm:ss
        }
        return "Sin fecha";
    }
    
    // 🔹 Método para mostrar información útil del pago (opcional)
    @Override
    public String toString() {
        return "Multa_pagada{" +
                "id_multa_pagada=" + id_multa_pagada +
                ", id_multa=" + id_multa +
                ", fecha=" + fecha +
                ", estado=" + getEstadoTexto() +
                ", usuario='" + getNombreCompletoUsuario() + '\'' +
                ", carnet='" + carnetUsuario + '\'' +
                ", monto=" + montoPagado +
                ", diasRetraso=" + diasRetraso +
                ", libro='" + tituloLibro + '\'' +
                ", numeroFactura='" + numeroFactura + '\'' +
                '}';
    }
}