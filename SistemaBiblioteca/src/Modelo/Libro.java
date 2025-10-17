/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author pc
 */
public class Libro {
    private int id_libro;
    private String titulo;
    private int id_categoria;
    private int id_editorial;
    private int id_autor;
    private int id_materia;
    private String edicion;
    private int estado;
    private String codigo;
    private int stock;
    private int anio;
    private String descripcion;
    private int id_estado;
    private String nombreCategoria;
    private String nombreEditorial;
    private String nombreAutor;
    private String nombreMateria;
    private String nombreEstado;
    private String fecha;
    
    public Libro() {
    }

    public Libro(int id_libro, String titulo, int id_categoria, int id_editorial, int id_autor, int id_materia, String edicion, int estado, String codigo, int stock, int anio, String descripcion, int id_estado, String nombreCategoria, String nombreEditorial, String nombreAutor, String nombreMateria, String nombreEstado, String fecha) {
        this.id_libro = id_libro;
        this.titulo = titulo;
        this.id_categoria = id_categoria;
        this.id_editorial = id_editorial;
        this.id_autor = id_autor;
        this.id_materia = id_materia;
        this.edicion = edicion;
        this.estado = estado;
        this.codigo = codigo;
        this.stock = stock;
        this.anio = anio;
        this.descripcion = descripcion;
        this.id_estado = id_estado;
        this.nombreCategoria = nombreCategoria;
        this.nombreEditorial = nombreEditorial;
        this.nombreAutor = nombreAutor;
        this.nombreMateria = nombreMateria;
        this.nombreEstado = nombreEstado;
        this.fecha = fecha;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public int getId_editorial() {
        return id_editorial;
    }

    public void setId_editorial(int id_editorial) {
        this.id_editorial = id_editorial;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getNombreEditorial() {
        return nombreEditorial;
    }

    public void setNombreEditorial(String nombreEditorial) {
        this.nombreEditorial = nombreEditorial;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

   
}
