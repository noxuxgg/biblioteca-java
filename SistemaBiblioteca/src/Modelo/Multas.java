/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author LIMA
 */
public class Multas {
    private int Id_multa;
    private int Id_prestamo;
    private int Id_usuario;
    private int Dias_retraso;
    private float Monto;
    private String Estado;

    public void setId_multa(int Id_multa) {
        this.Id_multa = Id_multa;
    }

    public void setId_prestamo(int Id_prestamo) {
        this.Id_prestamo = Id_prestamo;
    }

    public void setId_usuario(int Id_usuario) {
        this.Id_usuario = Id_usuario;
    }

    public void setDias_retraso(int Dias_retraso) {
        this.Dias_retraso = Dias_retraso;
    }

    public void setMonto(float Monto) {
        this.Monto = Monto;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public int getId_multa() {
        return Id_multa;
    }

    public int getId_prestamo() {
        return Id_prestamo;
    }

    public int getId_usuario() {
        return Id_usuario;
    }

    public int getDias_retraso() {
        return Dias_retraso;
    }

    public float getMonto() {
        return Monto;
    }

    public String getEstado() {
        return Estado;
    }
    
}
