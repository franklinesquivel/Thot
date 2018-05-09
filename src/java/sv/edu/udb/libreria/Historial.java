/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.libreria;

/**
 *
 * @author Frank
 */
public class Historial {
    private String idProceso;
    private String inicio;
    private String limite;
    private String estado;
    private String tipoProceso;
    private String idUsuario;

    public Historial(String idProceso, String inicio, String limite, String estado, String tipoProceso, String idUsuario) {
        this.idProceso = idProceso;
        this.inicio = inicio;
        this.limite = limite;
        this.estado = estado;
        this.tipoProceso = tipoProceso;
        this.idUsuario = idUsuario;
    }
}
