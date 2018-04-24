/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.connection.DB;
import sv.edu.udb.libreria.Ejemplar;
import sv.edu.udb.libreria.Libro;

/**
 *
 * @author Frank
 */
public class Ejemplar_Controller {
    
    private final static DB _db = new DB();
    
    public static boolean insertar(Ejemplar _e){
        _db.open();
        if(_db.isOpen()){
            try {
                try (PreparedStatement insertarSQL = _db.getStatement("INSERT INTO ejemplar VALUES(?, ?, ?)")) {
                    insertarSQL.setString(1, _e.getCodigo());
                    insertarSQL.setString(2, _e.getObservaciones());
                    insertarSQL.setString(3, _e.getLibro().getIdLibro());
                    insertarSQL.executeUpdate();
                }
                _db.close();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Ejemplar_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return false;
            }
        }else{
            _db.close();
            return false;
        }
        
    }
    
    public static boolean modificar(Ejemplar _e){
        _db.open();
        if(_db.isOpen()){
            try {
                try (PreparedStatement modificarSQL = _db.getStatement("UPDATE ejemplar SET codigo = ?, observaciones = ?, estado = ? WHERE idEjemplar = ?;")) {
                    modificarSQL.setString(1, _e.getCodigo());
                    modificarSQL.setString(2, _e.getObservaciones());
                    modificarSQL.setString(3, _e.getEstado());
                    modificarSQL.setString(4, _e.getIdEjemplar());
                    modificarSQL.executeUpdate();
                }
                _db.close();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Ejemplar_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return false;
            }
        }else{
            _db.close();
            return false;
        } 
    }
    
    public static Ejemplar obtenerEjemplar(String idEjemplar){
       _db.open();
       if(_db.isOpen()){
           try {
               Ejemplar _e;
               try (PreparedStatement obtenerEjemplar = _db.getStatement("SELECT * FROM ejemplar WHERE idEjemplar= ?;")) {
                   obtenerEjemplar.setString(1, idEjemplar);
                   try (ResultSet DataEjemplar = obtenerEjemplar.executeQuery()) {
                       if(DataEjemplar.next()){
                           _e = new Ejemplar(
                                DataEjemplar.getString(1),
                                DataEjemplar.getString(2),
                                DataEjemplar.getString(3),
                                DataEjemplar.getString(4)
                           );
                       }else{
                           _e = null;
                       }
                   }
               }
               
               _db.close();
               return _e;
           } catch (SQLException ex) {
               Logger.getLogger(Ejemplar_Controller.class.getName()).log(Level.SEVERE, null, ex);
               _db.close();
               return null;
           }
       }else{
           _db.close();
           return null;
       }
    }
    
    public static List<Ejemplar> obtenerEjemplares(){
        _db.open();
        if(_db.isOpen()){
            try {
                List<Ejemplar> _e = new ArrayList<>();
                try (PreparedStatement obtenerEjemplar = _db.getStatement("SELECT * FROM ejemplar;"); ResultSet DataEjemplar = obtenerEjemplar.executeQuery()) {
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
                
                _db.close();
                return _e;
            } catch (SQLException ex) {
                Logger.getLogger(Ejemplar_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return null;
            }
        }else{
            _db.close();
            return null;
        }
    }
}
