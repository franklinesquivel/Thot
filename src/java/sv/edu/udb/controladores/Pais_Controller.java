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
import sv.edu.udb.libreria.Pais;

/**
 *
 * @author Jasson
 */
public class Pais_Controller{

    private final static DB _db = new DB();
    
    public static List<Pais> obtenerPaises() {
        _db.open();
        if(_db.isOpen()){
            List<Pais> _Ulist = new ArrayList();
            
            try {
                try (PreparedStatement obtenerSQL = _db.getStatement("SELECT * FROM pais;")) {
                    ResultSet data = obtenerSQL.executeQuery();
                    while (data.next()) {
                        _Ulist.add(new Pais(data.getString("idPais"), data.getString("nombre")));
                    }
                }
                
                _db.close();
                return _Ulist;
            } catch (SQLException ex) {
                Logger.getLogger(TipoUsuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return null;
            }
        }else{
            _db.close();
            return null;
        }
    }

    public static String getPais(String idPais) {
        _db.open();
        if(_db.isOpen()){
            String paisReturn = "";
            try {
                try (PreparedStatement obtenerSQL = _db.getStatement("SELECT * FROM pais WHERE idPais = ?")) {
                    obtenerSQL.setString(1, idPais);
                    try (ResultSet data = obtenerSQL.executeQuery()) {
                        if (data.next()) {
                            paisReturn = data.getString("nombre");
                        }else{
                            paisReturn = null;
                        }
                    }
                }
                _db.close();
                return paisReturn;
            } catch (SQLException ex) {
                Logger.getLogger(Pais_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return null;
            }
        }else{
            _db.close();
            return null;
        }
        
    }

}
