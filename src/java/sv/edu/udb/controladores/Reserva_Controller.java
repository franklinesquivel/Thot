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
import sv.edu.udb.libreria.Reserva;

/**
 *
 * @author Frank
 */
public class Reserva_Controller {
    
    
    public static boolean insertar(Reserva _r){
        try(Connection _cn = DBConnection.getConnection()){
            try {
                int res;
                try (CallableStatement reservar = DBConnection.getProcedure("{CALL reserva_libro(?, ?, ?, ?, ?)}", _cn)) {
                    reservar.setString(1, _r.getEjemplar().getIdEjemplar());
                    reservar.setString(2, _r.getUsuario().getIdUsuario());
                    reservar.registerOutParameter(3, java.sql.Types.INTEGER);
                    reservar.setTimestamp(4, new java.sql.Timestamp(_r.getFecha_reserva().getTime()));
                    reservar.setTimestamp(5, new java.sql.Timestamp(_r.getFecha_vencimiento().getTime()));
                    reservar.executeQuery();
                    res = reservar.getInt(3);
                }
                return res == 1;
            } catch (SQLException ex) {

                Logger.getLogger(Reserva_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reserva_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean efectuarReserva(Reserva _r){
        try (Connection _cn = DBConnection.getConnection()) {
            try {
                int res;
                try (CallableStatement reservar = DBConnection.getProcedure("{CALL efectuar_reserva(?, ?)}", _cn)) {
                    reservar.setString(1, _r.getIdReserva());
                    reservar.registerOutParameter(2, java.sql.Types.INTEGER);
                    reservar.executeQuery();
                    res = reservar.getInt(2);
                }
                return res == 1;
            } catch (SQLException ex) {

                Logger.getLogger(Reserva_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reserva_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static Reserva obtenerReserva(String idReserva, boolean relaciones){
        try(Connection _cn = DBConnection.getConnection()){
            try {
                Reserva _r;
                try (PreparedStatement obtener = DBConnection.getStatement("SELECT * FROM reserva WHERE idReserva= ?;", _cn)) {
                    obtener.setString(1, idReserva);
                    try (ResultSet data = obtener.executeQuery()) {
                        if (data.next()) {
                            _r = new Reserva(
                                data.getString(1),
                                data.getDate(2),
                                data.getDate(3),
                                data.getString(4),
                                new Ejemplar(data.getString(5), relaciones),
                                Usuario_Controller.obtenerUsuario(data.getString(6))
                            );
                        } else {
                            _r = null;
                        }
                    }
                }
                return _r;
            } catch (SQLException ex) {
                Logger.getLogger(Reserva_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reserva_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Reserva> obtenerReservas(boolean relaciones){
        try(Connection _cn = DBConnection.getConnection()){
            List<Reserva> _r = new ArrayList<>();

            try {
                try (PreparedStatement obtener = DBConnection.getStatement("SELECT * FROM reserva;", _cn); ResultSet data = obtener.executeQuery()) {
                    while (data.next()) {
                        _r.add(
                            new Reserva(
                                data.getString(1),
                                data.getDate(2),
                                data.getDate(3),
                                data.getString(4),
                                new Ejemplar(data.getString(5), relaciones),
                                Usuario_Controller.obtenerUsuario(data.getString(6))
                            )
                        );
                    }
                }
                return _r;
            } catch (SQLException ex) {
                Logger.getLogger(Reserva_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reserva_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
