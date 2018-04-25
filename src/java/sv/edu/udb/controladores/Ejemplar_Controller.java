/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controladores;

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
import sv.edu.udb.libreria.Libro;

/**
 *
 * @author Frank
 */
public class Ejemplar_Controller {
    
    public static boolean insertar(Ejemplar _e){
        try(Connection _cn = DBConnection.getConnection()){
            try {
                try (PreparedStatement insertarSQL = DBConnection.getStatement("INSERT INTO ejemplar(observaciones, idLibro) VALUES(?, ?);", _cn)) {
                    insertarSQL.setString(1, _e.getObservaciones());
                    insertarSQL.setString(2, _e.getLibro().getIdLibro());
                    insertarSQL.executeUpdate();
                }
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Ejemplar_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ejemplar_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean modificar(Ejemplar _e){
        try (Connection _cn = DBConnection.getConnection()) {
            try {
                try (PreparedStatement modificarSQL = DBConnection.getStatement("UPDATE ejemplar SET codigo = ?, observaciones = ?, estado = ? WHERE idEjemplar = ?;", _cn)) {
                    modificarSQL.setString(1, _e.getCodigo());
                    modificarSQL.setString(2, _e.getObservaciones());
                    modificarSQL.setString(3, _e.getEstado());
                    modificarSQL.setString(4, _e.getIdEjemplar());
                    modificarSQL.executeUpdate();
                }
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Ejemplar_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ejemplar_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static Ejemplar obtenerEjemplar(String idEjemplar){
        try (Connection _cn = DBConnection.getConnection()) {
            try {
                Ejemplar _e;
                try (PreparedStatement obtenerEjemplar = DBConnection.getStatement("SELECT * FROM ejemplar WHERE idEjemplar = ?;", _cn)) {
                    obtenerEjemplar.setString(1, idEjemplar);
                    try (ResultSet DataEjemplar = obtenerEjemplar.executeQuery()) {
                        if (DataEjemplar.next()) {
                            _e = new Ejemplar(
                                DataEjemplar.getString(1),
                                DataEjemplar.getString(2),
                                DataEjemplar.getString(3),
                                DataEjemplar.getString(4),
                                new Libro(DataEjemplar.getString(5), false)
                            );
                        } else {
                            _e = null;
                        }
                    }
                }
                return _e;
            } catch (SQLException ex) {
                Logger.getLogger(Ejemplar_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ejemplar_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Ejemplar> obtenerEjemplares(){
        try (Connection _cn = DBConnection.getConnection()) {
            try {
                List<Ejemplar> _e = new ArrayList<>();
                try (PreparedStatement obtenerEjemplar = DBConnection.getStatement("SELECT * FROM ejemplar;", _cn); ResultSet DataEjemplar = obtenerEjemplar.executeQuery()) {
                    while (DataEjemplar.next()) {
                        _e.add(
                            new Ejemplar(
                                DataEjemplar.getString(1),
                                DataEjemplar.getString(2),
                                DataEjemplar.getString(3),
                                DataEjemplar.getString(4),
                                new Libro(DataEjemplar.getString(5), false)
                            )
                        );
                    }
                }
                return _e;
            } catch (SQLException ex) {
                Logger.getLogger(Ejemplar_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ejemplar_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static Ejemplar obtenerEjemplarParaProceso(Libro _l){
        if(_l == null) {return null;}
        try (Connection _cn = DBConnection.getConnection()) {
            try {
                Ejemplar _e;
                try (PreparedStatement obtenerEjemplar = DBConnection.getStatement("SELECT * FROM ejemplar WHERE idLibro= ? AND estado = 'D' LIMIT 1;", _cn)) {
                    obtenerEjemplar.setString(1, _l.getIdLibro());
                    
                    try (ResultSet DataEjemplar = obtenerEjemplar.executeQuery()) {
                        if (DataEjemplar.next()) {
                            _e = new Ejemplar(
                                DataEjemplar.getString(1),
                                DataEjemplar.getString(2),
                                DataEjemplar.getString(3),
                                DataEjemplar.getString(4)
                            );
                        } else {
                            _e = null;
                        }
                    }
                }
                return _e;
            } catch (SQLException ex) {
                Logger.getLogger(Ejemplar_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ejemplar_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
