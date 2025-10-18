/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author ADRIANA
 */
public class Usuario {
    private int Id_usuario;
    private String Carnet;
    private String Nombre;
    private String Apellido;
    private String Domicilio;
    private String Telefono;
    private int Id_tipo_usuario;
    private int id_cargo;
    private int id_carrera;
    private int Estado;

    public Usuario() {
    }

    public Usuario(int Id_usuario, String Carnet, String Nombre, String Apellido, String Domicilio, String Telefono, int Id_tipo_usuario, int id_cargo, int id_carrera, int estado) {
        this.Id_usuario = Id_usuario;
        this.Carnet = Carnet;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Domicilio = Domicilio;
        this.Telefono = Telefono;
        this.Id_tipo_usuario = Id_tipo_usuario;
        this.id_cargo = id_cargo;
        this.id_carrera = id_carrera;
        this.Estado = estado;
    }

    public int getId_usuario() {
        return Id_usuario;
    }

    public void setId_usuario(int Id_usuario) {
        this.Id_usuario = Id_usuario;
    }

    public String getCarnet() {
        return Carnet;
    }

    public void setCarnet(String Carnet) {
        this.Carnet = Carnet;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getDomicilio() {
        return Domicilio;
    }

    public void setDomicilio(String Domicilio) {
        this.Domicilio = Domicilio;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public int getId_tipo_usuario() {
        return Id_tipo_usuario;
    }

    public void setId_tipo_usuario(int Id_tipo_usuario) {
        this.Id_tipo_usuario = Id_tipo_usuario;
    }

    public int getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
    }

    public int getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(int id_carrera) {
        this.id_carrera = id_carrera;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }
    
    
}
    
