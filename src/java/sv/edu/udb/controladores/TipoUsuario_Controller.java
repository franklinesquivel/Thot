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
import sv.edu.udb.libreria.TipoUsuario;

/**
 *
 * @author Leonardo
 */
public class TipoUsuario_Controller {
    
    private final static DB _db = new DB();
    
    /*public static boolean insertar(TipoUsuario _t){
        PreparedStatement insertarUsuario = LibraryConnection.getStatement("INSERT INTO usuarios(nombre, apellido, correo, fechaNacimiento, nombreUsuario, contrasenna, estado, tipoUsuario) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
        try {
            insertarUsuario.setString(1, _u.getNombre());
            insertarUsuario.setString(2, _u.getApellido());
            insertarUsuario.setString(3, _u.getCorreo());
            insertarUsuario.setDate(4, _u.getFechaNacimiento());
            insertarUsuario.setString(5, _u.getNombreUsuario());
            insertarUsuario.setString(6, _u.getContrasenna());
            insertarUsuario.setInt(7, _u.getEstado());
            insertarUsuario.setString(8, String.valueOf(_u.getTipoUsuario()));
            insertarUsuario.executeUpdate();
            LibraryConnection.cn.close(); //Se cierra conexion
            return true;
        } catch (SQLException ex) {    
            Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }*/
    
    public static TipoUsuario obtenerTipoUsuario(String id){
        _db.open();
        if(_db.isOpen()){
            try {
                TipoUsuario _t = null;
                try (PreparedStatement obtenerSQL = _db.getStatement("SELECT * FROM tipos_usuario WHERE idTipoUsuario = ?;")) {
                    obtenerSQL.setString(1, id);
                    try (ResultSet data = obtenerSQL.executeQuery()) {
                        if (data.next()) {
                            _t = new TipoUsuario(data.getString("idTipoUsuario").charAt(0), data.getString("nombre"), data.getString("descripcion"));
                        }else{
                            _t = null;
                        }
                    }
                }
                _db.close();
                return _t;
            } catch (SQLException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return null;
            }
        }else{
            _db.close();
            return null;
        }
        
    }
    
    public static List<TipoUsuario> obtenerTiposUsuarios(){
        _db.open();
        if(_db.isOpen()){
            List<TipoUsuario> _Ulist = new ArrayList();

            try {
                try (PreparedStatement obtenerSQL = _db.getStatement("SELECT * FROM tipos_usuario;"); ResultSet data = obtenerSQL.executeQuery()) {
                    while (data.next()) {
                        _Ulist.add(new TipoUsuario(data.getString("idTipoUsuario").charAt(0), data.getString("nombre"), data.getString("descripcion")));
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
    
    /*public static boolean modificarTipoUsuario(TipoUsuario _t){
        PreparedStatement modificarSQL = LibraryConnection.getStatement("UPDATE tipos_usuarios SET nombre = ?, descripcion = ? WHERE id = ?");
        try{
            modificarSQL.setString(1, _t.getNombre());
            modificarSQL.setString(2, _t.getDescripcion());
            modificarSQL.setString(3, String.valueOf(_t.getId()));
            modificarSQL.executeUpdate();
            LibraryConnection.cn.close(); //Se cierra conexion
            return true;
        }catch(SQLException ex){
            Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean eliminarTipoUsuario(String id){
        PreparedStatement modificarSQL = LibraryConnection.getStatement("DELETE FROM tipos_usuarios WHERE id = ?;");
        try{
            modificarSQL.setString(1, id);
            modificarSQL.executeUpdate();
            LibraryConnection.cn.close(); //Se cierra conexion
            return true;
        }catch(SQLException ex){
            Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }*/
}
