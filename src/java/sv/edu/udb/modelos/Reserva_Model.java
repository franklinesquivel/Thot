/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.modelos;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.connection.DBConection;
import sv.edu.udb.libreria.Ejemplar;
import sv.edu.udb.libreria.Reserva;

/**
 *
 * @author Frank
 */
public class Reserva_Model {
    public static boolean insertar(Reserva _r){
        CallableStatement reservar = DBConection.setProcedure("{CALL reserva_libro(?, ?, ?, ?)}");
        try {
            int res = 0;
            reservar.setString(1, _r.getEjemplar().getIdEjemplar());
            reservar.setString(2, _r.getUsuario().getIdUsuario());
            reservar.setInt(3, res);
            reservar.setDate(4, (Date) _r.getFecha_vencimiento());
            
            reservar.executeQuery();
            
            return res == 1;
        } catch (SQLException ex) {
            Logger.getLogger(Reserva_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static Reserva obtenerReserva(String idReserva, boolean relaciones){
        PreparedStatement obtener = DBConection.getStatement("SELECT * FROM reserva WHERE idReserva= ?;");
        
        try {
            obtener.setString(1, idReserva);
            
            try (ResultSet data = obtener.executeQuery()) {
                while(data.next()){
                    return new Reserva(
                        data.getString(1),
                        data.getDate(2),
                        data.getDate(3),
                        new Ejemplar(data.getString(4), relaciones),
                        Usuario_Model.obtenerUsuario(data.getString(5))
                    );
                }
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(Reserva_Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Reserva> obtenerReservas(boolean relaciones){
        PreparedStatement obtener = DBConection.getStatement("SELECT * FROM reserva;");
        
        List<Reserva> _r = new ArrayList<>();
        
        try {
            
            try (ResultSet data = obtener.executeQuery()) {
                while(data.next()){
                    _r.add(
                        new Reserva(
                            data.getString(1),
                            data.getDate(2),
                            data.getDate(3),
                            new Ejemplar(data.getString(4), relaciones),
                            Usuario_Model.obtenerUsuario(data.getString(5))
                        )
                    ); 
                }
                
                return _r;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Reserva_Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
