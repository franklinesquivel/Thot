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
import sv.edu.udb.libreria.Prestamo;

/**
 *
 * @author Frank
 */
public class Prestamo_Model {
    public static boolean insertar(Prestamo _p){
        CallableStatement prestamo = DBConection.getProcedure("{CALL prestamo_libro(?, ?, ?, ?)}");
        try {
            int res = 0;
            prestamo.setString(1, _p.getEjemplar().getIdEjemplar());
            prestamo.setString(2, _p.getUsuario().getIdUsuario());
            prestamo.setInt(3, res);
            prestamo.setDate(4, (Date) _p.getFecha_devolucion());
            
            prestamo.executeQuery();
            
            return res == 1;
        } catch (SQLException ex) {
            Logger.getLogger(Prestamo_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static Prestamo obtenerPrestamo(String idPrestamo, boolean relaciones){
        PreparedStatement obtener = DBConection.getStatement("SELECT * FROM prestamo WHERE idPrestamo = ?;");
        
        try {
            obtener.setString(1, idPrestamo);
            
            try (ResultSet data = obtener.executeQuery()) {
                while (data.next()) {
                    return new Prestamo(
                        data.getString(1),
                        data.getDate(2),
                        data.getDate(3),
                        data.getFloat(4),
                        data.getInt(5) == 1,
                        new Ejemplar(data.getString(4), relaciones),
                        Usuario_Model.obtenerUsuario(data.getString(5))
                    );
                }
            }
            return null;
        } catch (SQLException e) {
            Logger.getLogger(Prestamo_Model.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    
    public static List<Prestamo> obtenerPrestamos(boolean relaciones){
        PreparedStatement obtener = DBConection.getStatement("SELECT * FROM prestamo");

        List<Prestamo> _p = new ArrayList<>();
        
        try {

            try (ResultSet data = obtener.executeQuery()) {
                while (data.next()) {
                    _p.add(
                        new Prestamo(
                            data.getString(1),
                            data.getDate(2),
                            data.getDate(3),
                            data.getFloat(4),
                            data.getInt(5) == 1,
                            new Ejemplar(data.getString(4), relaciones),
                            Usuario_Model.obtenerUsuario(data.getString(5))
                        )
                    );
                }
                
                return _p;
            }
        } catch (SQLException e) {
            Logger.getLogger(Prestamo_Model.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
}
