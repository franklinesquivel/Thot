/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.modelos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.connection.DBConection;
import sv.edu.udb.libreria.Ejemplar;
import sv.edu.udb.libreria.Libro;

/**
 *
 * @author Frank
 */
public class Ejemplar_Model {
    
    public static boolean insertar(Ejemplar _e){
        PreparedStatement insertarSQL = DBConection.getStatement("INSERT INTO ejemplar VALUES(?, ?, ?)");
        try {
            insertarSQL.setString(1, _e.getCodigo());
            insertarSQL.setString(2, _e.getObservaciones());
            insertarSQL.setString(3, _e.getLibro().getIdLibro());

           insertarSQL.executeUpdate();
           return true;
        } catch (SQLException ex) {    
            Logger.getLogger(Ejemplar_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean modificar(Ejemplar _e){
        PreparedStatement modificarSQL = DBConection.getStatement("UPDATE ejemplar SET codigo = ?, observaciones = ?, estado = ?;");
        try {
            modificarSQL.setString(1, _e.getCodigo());
            modificarSQL.setString(2, _e.getObservaciones());
            modificarSQL.setString(3, _e.getEstado());

           modificarSQL.executeUpdate();
           return true;
        } catch (SQLException ex) {    
            Logger.getLogger(Ejemplar_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static Ejemplar obtenerEjemplar(String idEjemplar){
        PreparedStatement obtenerEjemplar = DBConection.getStatement("SELECT * FROM ejemplar WHERE idEjemplar= ?;");
        
        try {
            obtenerEjemplar.setString(1, idEjemplar);
            try (ResultSet DataEjemplar = obtenerEjemplar.executeQuery()) {
                while (DataEjemplar.next()) {

                    Ejemplar _e = new Ejemplar(
                        DataEjemplar.getString(1),
                        DataEjemplar.getString(2),
                        DataEjemplar.getString(3),
                        DataEjemplar.getString(4),
                        new Libro(DataEjemplar.getString(5), false)
                    );

                    return _e;
                }
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(Ejemplar_Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Ejemplar> obtenerEjemplares(){
        PreparedStatement obtenerEjemplar = DBConection.getStatement("SELECT * FROM ejemplar;");
        
        List<Ejemplar> _e = new ArrayList<>();
        
        try {
            try (ResultSet DataEjemplar = obtenerEjemplar.executeQuery()) {
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
                
                return _e;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ejemplar_Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
