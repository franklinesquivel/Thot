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
import sv.edu.udb.libreria.Pais;

/**
 *
 * @author Jasson
 */
public class Pais_Controller{
    
    public static List<Pais> obtenerPaises() {
        try (Connection _cn = DBConnection.getConnection()) {
            List<Pais> _Ulist = new ArrayList();

            try {
                try (PreparedStatement obtenerSQL = DBConnection.getStatement("SELECT * FROM pais;", _cn)) {
                    ResultSet data = obtenerSQL.executeQuery();
                    while (data.next()) {
                        _Ulist.add(new Pais(data.getString("idPais"), data.getString("nombre")));
                    }
                }

                return _Ulist;
            } catch (SQLException ex) {
                Logger.getLogger(Pais_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pais_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static String getPais(String idPais) {
        try (Connection _cn = DBConnection.getConnection()) {
            String paisReturn;
            try {
                try (PreparedStatement obtenerSQL = DBConnection.getStatement("SELECT * FROM pais WHERE idPais = ?", _cn)) {
                    obtenerSQL.setString(1, idPais);
                    try (ResultSet data = obtenerSQL.executeQuery()) {
                        if (data.next()) {
                            paisReturn = data.getString("nombre");
                        } else {
                            paisReturn = null;
                        }
                    }
                }
                return paisReturn;
            } catch (SQLException ex) {
                Logger.getLogger(Pais_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pais_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
