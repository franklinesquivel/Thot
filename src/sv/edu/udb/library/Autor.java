/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.library;

import java.util.Date;
import java.util.List;
import sv.edu.udb.models.Autor_Model;

/**
 *
 * @author Frank
 */
public class Autor {
    private String idAutor;
    private String nombres;
    private String apellidos;
    private Date fechaNac;
    private Pais pais;
    private List<Libro> libros;
    
    public String getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(String idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }
    
    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
    
    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Autor(String idAutor, String nombres, String apellidos, Date fechaNac, Pais pais, List<Libro> libros) {
        this.idAutor = idAutor;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.pais = pais;
        this.libros = libros;
    }

    public Autor(String idAutor, String nombres, String apellidos, Date fechaNac) {
        this.idAutor = idAutor;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
    }

    public Autor(String idAutor, String nombres, String apellidos) {
        this.idAutor = idAutor;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public Autor(String nombres, String apellidos, List<Libro> libros) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.libros = libros;
    }

    public Autor(String nombres, String apellidos) {
        this.nombres = nombres;
        this.apellidos = apellidos;
    }
    
    public Autor(String idAutor, boolean relaciones) {
        Autor _a = Autor_Model.obtenerAutor(idAutor, relaciones);
        if(_a != null){
            this.idAutor = _a.getIdAutor();
            this.nombres = _a.getNombres();
            this.apellidos = _a.getApellidos();
            this.fechaNac = _a.getFechaNac();
            this.pais = _a.getPais();
            this.libros = _a.getLibros();
        }
    }
}
