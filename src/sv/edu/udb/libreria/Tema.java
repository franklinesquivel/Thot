/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.libreria;

import java.util.List;
import sv.edu.udb.modelos.Tema_Model;

/**
 *
 * @author Frank
 */
public class Tema {
    private int idTema;
    private String descripcion;
    private List<Libro> libros;
    
    public int getIdTema() {
        return idTema;
    }

    public void setIdTema(int idTema) {
        this.idTema = idTema;
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

    public Tema(int idTema, String descripcion, List<Libro> libros) {
        this.idTema = idTema;
        this.descripcion = descripcion;
        this.libros = libros;
    }

    public Tema(int idTema, String descripcion) {
        this.idTema = idTema;
        this.descripcion = descripcion;
    }
    public Tema(String descripcion){
        this.descripcion = descripcion;
    }
    public Tema(int idTema, boolean relaciones) {
        Tema _t = Tema_Model.obtenerTema(idTema, relaciones);
        if(_t != null){
            this.idTema = _t.getIdTema();
            this.descripcion = _t.getDescripcion();
            this.libros = _t.getLibros();
        }
    }
}
