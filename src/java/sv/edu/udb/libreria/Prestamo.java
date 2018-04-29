/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.libreria;

import java.util.Date;
import sv.edu.udb.utilidades.Formatos;

/**
 *
 * @author Frank
 */
public class Prestamo {
    private String idPrestamo;
    private Date fecha_prestamo;
    private Date fecha_devolucion;
    private float mora;
    private String estado;
    private Ejemplar ejemplar;
    private Usuario usuario;

    public String getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(String idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Date getFecha_prestamo() {
        return fecha_prestamo;
    }

    public void setFecha_prestamo(Date fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    public Date getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(Date fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public float getMora() {
        return mora;
    }

    public void setMora(float mora) {
        this.mora = mora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public double getMoraDecimales(int _p) {
        return Formatos.roundDbl(Double.parseDouble("" + this.mora), _p);
    }
    
    public String getFechaPrestamoFormato(){
        return Formatos.formatearFecha(fecha_prestamo, "dd-MM-yyyy");
    }
    
    public String getFechaDevolucionFormato() {
        return Formatos.formatearFecha(fecha_devolucion, "dd-MM-yyyy");
    }
    
    public String getDisplayEstado(){
        return this.estado.equals("VO") ? "Vencido" : this.estado.equals("EP") ? "En proceso" : "Finalizado";
    }

    public Prestamo(String idPrestamo, Date fecha_prestamo, Date fecha_devolucion, float mora, String estado, Ejemplar ejemplar, Usuario usuario) {
        this.idPrestamo = idPrestamo;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_devolucion = fecha_devolucion;
        this.mora = mora;
        this.estado = estado;
        this.ejemplar = ejemplar;
        this.usuario = usuario;
    }

    public Prestamo(Date fecha_prestamo, Date fecha_devolucion, float mora, String estado, Ejemplar ejemplar, Usuario usuario) {
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_devolucion = fecha_devolucion;
        this.mora = mora;
        this.estado = estado;
        this.ejemplar = ejemplar;
        this.usuario = usuario;
    }

    public Prestamo(Date fecha_devolucion, Ejemplar ejemplar, Usuario usuario) {
        this.fecha_devolucion = fecha_devolucion;
        this.ejemplar = ejemplar;
        this.usuario = usuario;
    }

    public Prestamo(Ejemplar ejemplar, Usuario usuario) {
        this.ejemplar = ejemplar;
        this.usuario = usuario;
    }
}
