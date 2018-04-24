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
import sv.edu.udb.libreria.Reserva;

/**
 *
 * @author Frank
 */
public class Reserva_Controller {
    
    private final static DB _db = new DB();
    
    public static boolean insertar(Reserva _r){
        _db.open();
        if(_db.isOpen()){
            try {
                int res;
                try (CallableStatement reservar = _db.getProcedure("{CALL reserva_libro(?, ?, ?, ?)}")) {
                    res = 0;
                    reservar.setString(1, _r.getEjemplar().getIdEjemplar());
                    reservar.setString(2, _r.getUsuario().getIdUsuario());
                    reservar.registerOutParameter(3, java.sql.Types.INTEGER);
                    reservar.setDate(4, (Date) _r.getFecha_vencimiento());
                    reservar.executeQuery();
                    res = reservar.getInt(3);
                }
                _db.close();
                return res == 1;
            } catch (SQLException ex) {
                Logger.getLogger(Reserva_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return false;
            }
        }else{
            _db.close();
            return false;
        }
        
    }
    
    public static Reserva obtenerReserva(String idReserva, boolean relaciones){
        _db.open();
        if(_db.isOpen()){
            try {
                Reserva _r = null;
                try (PreparedStatement obtener = _db.getStatement("SELECT * FROM reserva WHERE idReserva= ?;")) {
                    obtener.setString(1, idReserva);
                    try (ResultSet data = obtener.executeQuery()) {
                        if (data.next()) {
                            _r = new Reserva(
                                data.getString(1),
                                data.getDate(2),
                                data.getDate(3),
                                new Ejemplar(data.getString(4), relaciones),
                                Usuario_Controller.obtenerUsuario(data.getString(5))
                            );
                        }else{
                            _r = null;
                        }
                    }
                }
                _db.close();
                return _r;
            } catch (SQLException ex) {
                Logger.getLogger(Reserva_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return null;
            }
        }else{
            _db.close();
            return null;
        }
    }
    
    public static List<Reserva> obtenerReservas(boolean relaciones){
        _db.open();
        if(_db.isOpen()){
            List<Reserva> _r = new ArrayList<>();

            try {
                try (PreparedStatement obtener = _db.getStatement("SELECT * FROM reserva;"); ResultSet data = obtener.executeQuery()) {
                    while (data.next()) {
                        _r.add(
                            new Reserva(
                                data.getString(1),
                                data.getDate(2),
                                data.getDate(3),
                                new Ejemplar(data.getString(4), relaciones),
                                Usuario_Controller.obtenerUsuario(data.getString(5))
                            )
                        );
                    }
                }
                return _r;
            } catch (SQLException ex) {
                Logger.getLogger(Reserva_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return null;
            }
        }else{
            _db.close();
            return null;
        }
    }
}
