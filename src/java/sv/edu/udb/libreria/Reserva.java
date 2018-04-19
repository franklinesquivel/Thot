/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.libreria;

import java.util.Date;

/**
 *
 * @author Frank
 */
public class Reserva {
    private String idReserva;
    private Date fecha_reserva;
    private Date fecha_vencimiento;
    private Ejemplar ejemplar;
    private Usuario usuario;

    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }

    public Date getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(Date fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
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

    public Reserva(String idReserva) {
        this.idReserva = idReserva;
    }

    public Reserva(String idReserva, Date fecha_reserva, Date fecha_vencimiento, Ejemplar ejemplar, Usuario usuario) {
        this.idReserva = idReserva;
        this.fecha_reserva = fecha_reserva;
        this.fecha_vencimiento = fecha_vencimiento;
        this.ejemplar = ejemplar;
        this.usuario = usuario;
    }

    public Reserva(String idReserva, Date fecha_reserva, Ejemplar ejemplar, Usuario usuario) {
        this.idReserva = idReserva;
        this.fecha_reserva = fecha_reserva;
        this.ejemplar = ejemplar;
        this.usuario = usuario;
    }

    public Reserva(String idReserva, Ejemplar ejemplar, Usuario usuario) {
        this.idReserva = idReserva;
        this.ejemplar = ejemplar;
        this.usuario = usuario;
    }
}
