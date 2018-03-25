/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.library;

import java.util.List;

/**
 *
 * @author Frank
 */
public class Pais {
    private String idPais;
    private String nombre;
    private List<Autor> autores;

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public String getIdPais() {
        return idPais;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Pais(String idPais, String nombre, List<Autor> autores) {
        this.idPais = idPais;
        this.nombre = nombre;
        this.autores = autores;
    }

    public Pais(String idPais, String nombre) {
        this.idPais = idPais;
        this.nombre = nombre;
    }

    public Pais() {
    
    }
}
