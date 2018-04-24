/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.libreria;

import sv.edu.udb.controladores.Ejemplar_Controller;

/**
 *
 * @author Frank
 */
public class Ejemplar {
    private String idEjemplar;
    private String codigo;
    private String observaciones;
    private String estado;
    private Libro libro;

    public String getIdEjemplar() {
        return idEjemplar;
    }

    public void setIdEjemplar(String idEjemplar) {
        this.idEjemplar = idEjemplar;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Ejemplar(String idEjemplar, boolean relaciones) {
        Ejemplar _e = Ejemplar_Controller.obtenerEjemplar(idEjemplar);
        if (_e != null) {
            this.idEjemplar = _e.getIdEjemplar();
            this.codigo  = _e.getCodigo();
            this.observaciones = _e.getObservaciones();
            this.estado = _e.getEstado();
        }
    }

    public Ejemplar(String codigo, String observaciones, String estado, Libro libro) {
        this.codigo = codigo;
        this.observaciones = observaciones;
        this.estado = estado;
        this.libro = libro;
    }

    public Ejemplar(String idEjemplar, String codigo, String observaciones, String estado, Libro libro) {
        this.idEjemplar = idEjemplar;
        this.codigo = codigo;
        this.observaciones = observaciones;
        this.estado = estado;
        this.libro = libro;
    }

    public Ejemplar(String idEjemplar, String codigo, String observaciones, String estado) {
        this.idEjemplar = idEjemplar;
        this.codigo = codigo;
        this.observaciones = observaciones;
        this.estado = estado;
    }
    
    
}
