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
    private String estado;
    private String NombreCategoria;
    private String NombreEditorial;
    private String NombreAutor;
    private String NombreMateria;
    private String codigo;
    

    public Libro() {
    }

    public Libro(int id_libro, String titulo, int id_categoria, int id_editorial, int id_autor, int id_materia, String edicion, String estado, String NombreCategoria, String NombreEditorial, String NombreAutor, String NombreMateria, String codigo) {
        this.id_libro = id_libro;
        this.titulo = titulo;
        this.id_categoria = id_categoria;
        this.id_editorial = id_editorial;
        this.id_autor = id_autor;
        this.id_materia = id_materia;
        this.edicion = edicion;
        this.estado = estado;
        this.NombreCategoria = NombreCategoria;
        this.NombreEditorial = NombreEditorial;
        this.NombreAutor = NombreAutor;
        this.NombreMateria = NombreMateria;
        this.codigo = codigo;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombreCategoria() {
        return NombreCategoria;
    }

    public void setNombreCategoria(String NombreCategoria) {
        this.NombreCategoria = NombreCategoria;
    }

    public String getNombreEditorial() {
        return NombreEditorial;
    }

    public void setNombreEditorial(String NombreEditorial) {
        this.NombreEditorial = NombreEditorial;
    }

    public String getNombreAutor() {
        return NombreAutor;
    }

    public void setNombreAutor(String NombreAutor) {
        this.NombreAutor = NombreAutor;
    }

    public String getNombreMateria() {
        return NombreMateria;
    }

    public void setNombreMateria(String NombreMateria) {
        this.NombreMateria = NombreMateria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    
    
}
