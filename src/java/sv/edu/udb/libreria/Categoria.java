/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.libreria;

import java.util.List;
import sv.edu.udb.controladores.Categoria_Controller;

/**
 *
 * @author Frank
 */
public class Categoria {
    private int idCategoria;
    private String nombre;
    private String descripcion;
    private List<Libro> libros;
    
    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Categoria(int idCategoria, String nombre, String descripcion) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public Categoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Categoria(int idCategoria, String nombre, String descripcion, List<Libro> libros) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.libros = libros;
    }
    
    public Categoria(int _id, boolean relaciones){
        Categoria _c = Categoria_Controller.obtenerCategoria(_id, relaciones);
        if(_c != null){
            this.idCategoria = _c.getIdCategoria();
            this.nombre = _c.getNombre();
            this.descripcion = _c.getDescripcion();
            this.libros = _c.getLibros();
        }
    }
    
}
