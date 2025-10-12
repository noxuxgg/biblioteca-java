/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author pc
 */
public class Autor {
    private int id_autor;
    private String nombre;
    private String apellido;
    private int id_pais;
    private int estado;
    private String NombrePais;

    public Autor() {
    }

    public Autor(int id_autor, String nombre, String apellido, int id_pais, int estado, String NombrePais) {
        this.id_autor = id_autor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.id_pais = id_pais;
        this.estado = estado;
        this.NombrePais = NombrePais;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getId_pais() {
        return id_pais;
    }

    public void setId_pais(int id_pais) {
        this.id_pais = id_pais;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNombrePais() {
        return NombrePais;
    }

    public void setNombrePais(String NombrePais) {
        this.NombrePais = NombrePais;
    }

    
    
    
}
