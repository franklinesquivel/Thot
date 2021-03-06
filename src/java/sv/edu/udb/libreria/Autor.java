/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.libreria;

import java.util.Date;
import java.util.List;
import sv.edu.udb.controladores.Autor_Controller;
import sv.edu.udb.utilidades.Formatos;

/**
 *
 * @author Frank
 */
public class Autor {

    private String idAutor;
    private String nombres;
    private String apellidos;
    private Date fechaNac;
    private String pais;
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
    
    public String getDisplayName(){
        return this.nombres + " " + this.apellidos;
    }
    
    public String getFechaNacFormato(){
        return Formatos.formatearFecha(fechaNac, "dd-MM-yyyy");
    }

    public Autor(String idAutor, String nombres, String apellidos, Date fechaNac, String pais, List<Libro> libros) {
        this.idAutor = idAutor;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.pais = pais;
        this.libros = libros;
    }

    public Autor(String idAutor, String nombres, String apellidos, Date fechaNac, String pais) {
        this.idAutor = idAutor;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.pais = pais;
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
        Autor _a = Autor_Controller.obtenerAutor(idAutor, relaciones);
        if (_a != null) {
            this.idAutor = _a.getIdAutor();
            this.nombres = _a.getNombres();
            this.apellidos = _a.getApellidos();
            this.fechaNac = _a.getFechaNac();
            this.pais = _a.getPais();
            this.libros = _a.getLibros();
        }
    }

    public static String crearNombreAutor(int numAutor) {
        String nombreAutor = "A";
        if (numAutor < 10) {
            nombreAutor += "000" + (numAutor += 1);
        } else if (numAutor >= 10 && numAutor < 100) {
            nombreAutor += "00" + (numAutor += 1);
        } else if (numAutor >= 100 && numAutor < 1000) {
            nombreAutor += "0" + (numAutor += 1);
        } else if (numAutor >= 1000) {
            nombreAutor += (numAutor += 1);
        }
        return nombreAutor;
    }
}
