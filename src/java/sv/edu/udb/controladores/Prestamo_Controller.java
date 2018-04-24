/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controladores;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.connection.DB;
import sv.edu.udb.libreria.Ejemplar;
import sv.edu.udb.libreria.Prestamo;

/**
 *
 * @author Frank
 */
public class Prestamo_Controller {
    
    private final static DB _db = new DB();
    
    public static boolean insertar(Prestamo _p){
        _db.open();
        if(_db.isOpen()){
            try {
                int res = 0;
                try (CallableStatement prestamo = _db.getProcedure("{CALL prestamo_libro(?, ?, ?, ?)}")) {
                    prestamo.setString(1, _p.getEjemplar().getIdEjemplar());
                    prestamo.setString(2, _p.getUsuario().getIdUsuario());
                    prestamo.registerOutParameter(3, java.sql.Types.INTEGER);
                    prestamo.setDate(4, (Date) _p.getFecha_devolucion());
                    prestamo.executeQuery();
                    res = prestamo.getInt(3);
                }
                _db.close();
                return res == 1;
            } catch (SQLException ex) {
                Logger.getLogger(Prestamo_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return false;
            }
        }else{
            _db.close();
            return false;
        }
        
    }
    
    public static Prestamo obtenerPrestamo(String idPrestamo, boolean relaciones){
        _db.open();
        if(_db.isOpen()){
            try {
                Prestamo _p = null;
                try (PreparedStatement obtener = _db.getStatement("SELECT * FROM prestamo WHERE idPrestamo = ?;")) {
                    obtener.setString(1, idPrestamo);
                    try (ResultSet data = obtener.executeQuery()) {
                        if (data.next()) {
                            _p = new Prestamo(
                                data.getString(1),
                                data.getDate(2),
                                data.getDate(3),
                                data.getFloat(4),
                                data.getInt(5) == 1,
                                new Ejemplar(data.getString(4), relaciones),
                                Usuario_Controller.obtenerUsuario(data.getString(5))
                            );
                        }else{
                            _p = null;
                        }
                    }
                }
                
                _db.close();
                return _p;
            } catch (SQLException e) {
                Logger.getLogger(Prestamo_Controller.class.getName()).log(Level.SEVERE, null, e);
                _db.close();
                return null;
            }
        }else{
            _db.close();
            return null;
        }
    }
    
    public static List<Prestamo> obtenerPrestamos(boolean relaciones){
        _db.open();
        if(_db.isOpen()){
            try {
                List<Prestamo> _p = new ArrayList<>();
                try (PreparedStatement obtener = _db.getStatement("SELECT * FROM prestamo"); ResultSet data = obtener.executeQuery()) {
                    while (data.next()) {
                        _p.add(
                            new Prestamo(
                                data.getString(1),
                                data.getDate(2),
                                data.getDate(3),
                                data.getFloat(4),
                                data.getInt(5) == 1,
                                new Ejemplar(data.getString(4), relaciones),
                                Usuario_Controller.obtenerUsuario(data.getString(5))
                            )
                        );
                    }
                }
                _db.close();
                return _p;
            } catch (SQLException e) {
                Logger.getLogger(Prestamo_Controller.class.getName()).log(Level.SEVERE, null, e);
                _db.close();
                return null;
            }
        }else{
            _db.close();
            return null;
        }
    }
}
