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
import sv.edu.udb.libreria.TipoUsuario;

/**
 *
 * @author Leonardo
 */
public class TipoUsuario_Controller {
    
    public static TipoUsuario obtenerTipoUsuario(String id){
        try(Connection _cn = DBConnection.getConnection()){
            try {
                TipoUsuario _t;
                try (PreparedStatement obtenerSQL = DBConnection.getStatement("SELECT * FROM tipos_usuario WHERE idTipoUsuario = ?;", _cn)) {
                    obtenerSQL.setString(1, id);
                    try (ResultSet data = obtenerSQL.executeQuery()) {
                        if (data.next()) {
                            _t = new TipoUsuario(data.getString("idTipoUsuario").charAt(0), data.getString("nombre"), data.getString("descripcion"));
                        } else {
                            _t = null;
                        }
                    }
                }
                return _t;
            } catch (SQLException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoUsuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<TipoUsuario> obtenerTiposUsuarios(){
        try (Connection _cn = DBConnection.getConnection()) {
            List<TipoUsuario> _Ulist = new ArrayList();

            try {
                try (PreparedStatement obtenerSQL = DBConnection.getStatement("SELECT * FROM tipos_usuario;", _cn); ResultSet data = obtenerSQL.executeQuery()) {
                    while (data.next()) {
                        _Ulist.add(new TipoUsuario(data.getString("idTipoUsuario").charAt(0), data.getString("nombre"), data.getString("descripcion")));
                    }
                }

                return _Ulist;
            } catch (SQLException ex) {
                Logger.getLogger(TipoUsuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoUsuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }   
}
