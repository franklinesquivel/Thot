/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.libreria;

import java.util.List;
import sv.edu.udb.controladores.Imprenta_Controller;

/**
 *
 * @author Frank
 */
public class Imprenta {
    private String idImprenta;
    private String nombre;
    private String direccion;
    private List<Libro> libros;
    
    public String getIdImprenta() {
        return idImprenta;
    }

    public void setIdImprenta(String idImprenta) {
        this.idImprenta = idImprenta;
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
    
    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Imprenta(String idImprenta, String nombre, String direccion) {
        this.idImprenta = idImprenta;
        this.nombre = nombre;
        this.direccion = direccion;
    }
    public Imprenta(String idImprenta, String nombre, String direccion, List<Libro> libros) {
        this.idImprenta = idImprenta;
        this.nombre = nombre;
        this.direccion = direccion;
        this.libros = libros;
    }

    public Imprenta(String idImprenta, boolean relaciones) {
        Imprenta _i = Imprenta_Controller.obtenerImprenta(idImprenta, relaciones);
        if(_i != null){
            this.idImprenta = _i.getIdImprenta();
            this.nombre = _i.getNombre();
            this.direccion = _i.getDireccion();
            this.libros = _i.getLibros();
        }
    }
}
