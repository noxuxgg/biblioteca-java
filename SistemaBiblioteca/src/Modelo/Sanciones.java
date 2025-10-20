/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author LIMA
 */
public class Sanciones {
    private int Id_sancion;
    private int Id_usuario;
    private String Motivo;
    private String Fecha_inicio;
    private String Fecha_fin;
    private int Estado;

    public void setId_sancion(int Id_sancion) {
        this.Id_sancion = Id_sancion;
    }

    public void setId_usuario(int Id_usuario) {
        this.Id_usuario = Id_usuario;
    }

    public void setMotivo(String Motivo) {
        this.Motivo = Motivo;
    }

    public void setFecha_inicio(String Fecha_inicio) {
        this.Fecha_inicio = Fecha_inicio;
    }

    public void setFecha_fin(String Fecha_fin) {
        this.Fecha_fin = Fecha_fin;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }

    public int getId_sancion() {
        return Id_sancion;
    }

    public int getId_usuario() {
        return Id_usuario;
    }

    public String getMotivo() {
        return Motivo;
    }

    public String getFecha_inicio() {
        return Fecha_inicio;
    }

    public String getFecha_fin() {
        return Fecha_fin;
    }

    public int getEstado() {
        return Estado;
    }
    
}
