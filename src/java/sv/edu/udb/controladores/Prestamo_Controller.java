/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controladores;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.connection.DBConnection;
import sv.edu.udb.libreria.Ejemplar;
import sv.edu.udb.libreria.Prestamo;

/**
 *
 * @author Frank
 */
public class Prestamo_Controller {
    
    public static boolean insertar(Prestamo _p){
        try(Connection _cn = DBConnection.getConnection()){
            try {
                int res;
                try (CallableStatement prestamo = DBConnection.getProcedure("{CALL prestamo_libro(?, ?, ?, ?)}", _cn)) {
                    prestamo.setString(1, _p.getEjemplar().getIdEjemplar());
                    prestamo.setString(2, _p.getUsuario().getIdUsuario());
                    prestamo.registerOutParameter(3, java.sql.Types.INTEGER);
                    prestamo.setTimestamp(4, new java.sql.Timestamp(_p.getFecha_devolucion().getTime()));
                    prestamo.executeQuery();
                    res = prestamo.getInt(3);
                }
                return res == 1;
            } catch (SQLException ex) {
                Logger.getLogger(Prestamo_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Prestamo_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static Prestamo obtenerPrestamo(String idPrestamo, boolean relaciones){
        try(Connection _cn = DBConnection.getConnection()){
            try {
                Prestamo _p;
                try (PreparedStatement obtener = DBConnection.getStatement("SELECT * FROM prestamo WHERE idPrestamo = ?;", _cn)) {
                    obtener.setString(1, idPrestamo);
                    try (ResultSet data = obtener.executeQuery()) {
                        if (data.next()) {
                            _p = new Prestamo(
                                data.getString(1),
                                data.getDate(2),
                                data.getDate(3),
                                data.getFloat(4),
                                data.getString(5),
                                new Ejemplar(data.getString(6), relaciones),
                                Usuario_Controller.obtenerUsuario(data.getString(7))
                            );
                        } else {
                            _p = null;
                        }
                    }
                }

                return _p;
            } catch (SQLException e) {
                Logger.getLogger(Prestamo_Controller.class.getName()).log(Level.SEVERE, null, e);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Prestamo_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Prestamo> obtenerPrestamos(boolean relaciones){
        try (Connection _cn = DBConnection.getConnection()) {
            try {
                List<Prestamo> _p = new ArrayList<>();
                try (PreparedStatement obtener = DBConnection.getStatement("SELECT * FROM prestamo", _cn); ResultSet data = obtener.executeQuery()) {
                    while (data.next()) {
                        _p.add(
                            new Prestamo(
                                data.getString(1),
                                data.getDate(2),
                                data.getDate(3),
                                data.getFloat(4),
                                data.getString(5),
                                new Ejemplar(data.getString(6), relaciones),
                                Usuario_Controller.obtenerUsuario(data.getString(7))
                            )
                        );
                    }
                }
                return _p;
            } catch (SQLException e) {
                Logger.getLogger(Prestamo_Controller.class.getName()).log(Level.SEVERE, null, e);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Prestamo_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Prestamo> obtenerPrestamos(boolean relaciones, String idUsuario){
        try (Connection _cn = DBConnection.getConnection()) {
            try {
                List<Prestamo> _p = new ArrayList<>();
                try (PreparedStatement obtener = DBConnection.getStatement("SELECT * FROM prestamo WHERE idUsuario = ?", _cn);) {
                    obtener.setString(1, idUsuario);
                    try (ResultSet data = obtener.executeQuery()) {
                        while (data.next()) {
                            _p.add(
                                new Prestamo(
                                    data.getString(1),
                                    data.getDate(2),
                                    data.getDate(3),
                                    data.getFloat(4),
                                    data.getString(5),
                                    new Ejemplar(data.getString(4), relaciones),
                                    Usuario_Controller.obtenerUsuario(data.getString(5))
                                )
                            );
                        }
                    }
                }
                return _p;
            } catch (SQLException e) {
                Logger.getLogger(Prestamo_Controller.class.getName()).log(Level.SEVERE, null, e);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Prestamo_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static boolean renovarPrestamo(Prestamo _p){
        try (Connection _cn = DBConnection.getConnection()) {
            try {
                int res;
                try (CallableStatement renovar = DBConnection.getProcedure("{CALL renovar_prestamo(?, ?)}", _cn)) {
                    renovar.setString(1, _p.getEjemplar().getIdEjemplar());
                    renovar.registerOutParameter(2, java.sql.Types.INTEGER);
                    renovar.executeQuery();
                    res = renovar.getInt(2);
                }
                
                return res == 1;
            } catch (SQLException ex) {
                Logger.getLogger(Prestamo_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Prestamo_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean finalizarPrestamo(Prestamo _p) {
        try (Connection _cn = DBConnection.getConnection()) {
            try {
                int res;
                try (CallableStatement renovar = DBConnection.getProcedure("{CALL finalizar_prestamo(?, ?)}", _cn)) {
                    renovar.setString(1, _p.getIdPrestamo());
                    renovar.registerOutParameter(2, java.sql.Types.INTEGER);
                    renovar.executeQuery();
                    res = renovar.getInt(2);
                }

                return res == 1;
            } catch (SQLException ex) {
                Logger.getLogger(Prestamo_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Prestamo_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
