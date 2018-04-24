/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.libreria;

import sv.edu.udb.controladores.Libro_Controller;
import java.util.List;

/**
 *
 * @author Frank
 */
public class Libro {
    private String idLibro;
    private String titulo;
    private String isbn;
    private String edicion;
    private String descripcion;
    private String notas;
    private String imagen;
    private int cant_ejemplares;
    private Imprenta imprenta;
    private Categoria categoria;
    private List<Autor> autores;
    private List<Tema> temas;
    private List<Ejemplar> ejemplares;
    
    
    public String getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(String idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
    
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    public int getCant_ejemplares() {
        return cant_ejemplares;
    }

    public void setCant_ejemplares(int cant_ejemplares) {
        this.cant_ejemplares = cant_ejemplares;
    }

    public Imprenta getImprenta() {
        return imprenta;
    }

    public void setImprenta(Imprenta imprenta) {
        this.imprenta = imprenta;
    }
    
    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Tema> getTemas() {
        return temas;
    }

    public void setTemas(List<Tema> temas) {
        this.temas = temas;
    }
    
    public List<Ejemplar> getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(List<Ejemplar> ejemplares) {
        this.ejemplares = ejemplares;
    }
    
    public int ejemplaresPorTipo(String estado){
        Libro _l;
        if(this.ejemplares == null){
            _l = this.cargarRelaciones(this);            
        }else{
            _l = this;
        }
        
        int i = 0;
        i = _l.ejemplares.stream().map((_e) -> _e.getEstado().equals(estado) ? 1 : 0).reduce(i, Integer::sum);

        return i;
    }

    public boolean esProcesable(){
        return (this.cant_ejemplares == this.ejemplaresPorTipo("PM"));
    }

    public Libro(String idLibro, String titulo, String isbn, String edicion, String descripcion, String notas, String imagen, int cant_ejemplares, Imprenta imprenta, Categoria categoria, List<Autor> autores, List<Tema> temas, List<Ejemplar> ejemplares) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.isbn = isbn;
        this.edicion = edicion;
        this.descripcion = descripcion;
        this.notas = notas;
        this.imagen = imagen;
        this.cant_ejemplares = cant_ejemplares;
        this.imprenta = imprenta;
        this.categoria = categoria;
        this.autores = autores;
        this.temas = temas;
        this.ejemplares = ejemplares;
    }

    public Libro(String idLibro, String titulo, String isbn, String edicion, String descripcion, String notas, String imagen, int cant_ejemplares) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.isbn = isbn;
        this.edicion = edicion;
        this.descripcion = descripcion;
        this.notas = notas;
        this.imagen = imagen;
        this.cant_ejemplares = cant_ejemplares;
    }

    public Libro(String idLibro, String titulo, String isbn, String edicion, String descripcion, String notas, String imagen, int cant_ejemplares, Imprenta imprenta, Categoria categoria) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.isbn = isbn;
        this.edicion = edicion;
        this.descripcion = descripcion;
        this.notas = notas;
        this.imagen = imagen;
        this.cant_ejemplares = cant_ejemplares;
        this.imprenta = imprenta;
        this.categoria = categoria;
    }

    public Libro(String idLibro, String titulo, String isbn, String edicion, String descripcion, String notas, String imagen, Imprenta imprenta, Categoria categoria, List<Autor> autores, List<Tema> temas) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.isbn = isbn;
        this.edicion = edicion;
        this.descripcion = descripcion;
        this.notas = notas;
        this.imagen = imagen;
        this.imprenta = imprenta;
        this.categoria = categoria;
        this.autores = autores;
        this.temas = temas;
    }

    public Libro(String idLibro, String titulo, String isbn, String edicion, String descripcion, String notas, String imagen, Imprenta imprenta, Categoria categoria) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.isbn = isbn;
        this.edicion = edicion;
        this.descripcion = descripcion;
        this.notas = notas;
        this.imagen = imagen;
        this.imprenta = imprenta;
        this.categoria = categoria;
    }

    public Libro(String idLibro, String titulo, String isbn, String edicion, String descripcion, String notas, String imagen) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.isbn = isbn;
        this.edicion = edicion;
        this.descripcion = descripcion;
        this.notas = notas;
        this.imagen = imagen;
    }
    
    public Libro cargarRelaciones(Libro _l){
        _l = Libro_Controller.obtenerLibro(_l.getIdLibro(), true);
//        _l.setCategoria(new Categoria(_l.getCategoria().getIdCategoria(), false));
//        _l.setImprenta(new Imprenta(_l.getImprenta().getIdImprenta(), false));
        
        return _l;
    }
    
    public Libro(String _id, boolean relaciones){
        Libro _l = Libro_Controller.obtenerLibro(_id, relaciones);
        if(_l != null){
            this.idLibro = _l.getIdLibro();
            this.titulo = _l.getTitulo();
            this.isbn = _l.getIsbn();
            this.edicion = _l.getEdicion();
            this.descripcion = _l.getDescripcion();
            this.notas = _l.getNotas();
            this.imagen = _l.getImagen();
            this.cant_ejemplares = _l.getCant_ejemplares();
            this.imprenta = _l.getImprenta();
            this.categoria = _l.getCategoria();
            this.autores = _l.getAutores();
            this.temas = _l.getTemas();
            this.ejemplares = _l.getEjemplares();
        }
    }
}
