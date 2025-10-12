/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author pc
 */
public class Editorial {
    private int id_editorial;
    private String nombre;
    private String direccion;
    private String telefono;
    private int id_Pais;
    private int estado;
    private String nombrePais;
    
    public Editorial() {
    }

    public Editorial(int id_editorial, String nombre, String direccion, String telefono, int id_Pais, int estado, String nombrePais) {
        this.id_editorial = id_editorial;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.id_Pais = id_Pais;
        this.estado = estado;
        this.nombrePais = nombrePais;
    }

    public int getId_editorial() {
        return id_editorial;
    }

    public void setId_editorial(int id_editorial) {
        this.id_editorial = id_editorial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getId_Pais() {
        return id_Pais;
    }

    public void setId_Pais(int id_Pais) {
        this.id_Pais = id_Pais;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }
    
    
}
