/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.connection.DB;
import sv.edu.udb.libreria.Usuario;

/**
 *
 * @author Leonardo
 */
public class Usuario_Controller{
    
    private final static DB _db = new DB();
    
    public static boolean insertar(Usuario _u){
        _db.open();
        if(_db.isOpen()){
            try {
                try (PreparedStatement insertarUsuario = _db.getStatement("INSERT INTO usuario(idUsuario, nombre, apellido, correo, fechaNacimiento, username, password, estado, tipoUsuario) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                    insertarUsuario.setString(1, _u.getIdUsuario());
                    insertarUsuario.setString(2, _u.getNombre());
                    insertarUsuario.setString(3, _u.getApellido());
                    insertarUsuario.setString(4, _u.getCorreo());
                    insertarUsuario.setDate(5, new java.sql.Date(_u.getFechaNacimiento().getTime()));
                    insertarUsuario.setString(6, _u.getUsername());
                    insertarUsuario.setString(7, _u.getPassword());
                    insertarUsuario.setBoolean(8, _u.isEstado());
                    char tipo = _u.getTipoUsuario().charAt(0);
                    insertarUsuario.setString(9, String.valueOf(tipo));
                    insertarUsuario.executeUpdate();
                }
                _db.close();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return false;
            }
        }else{
            _db.close();
            return false;
        }
    }
    public static Usuario obtenerUsuarioCorreo(String _e){
        _db.open();
        if(_db.isOpen()){
            try {
                Usuario _u;
                try (PreparedStatement obtenerSQL = _db.getStatement("SELECT * FROM usuario WHERE correo = ?;")) {
                    obtenerSQL.setString(1, _e);
                    try (ResultSet data = obtenerSQL.executeQuery()) {
                        if (data != null) {
                            if (data.next()) {
                                boolean estado = (data.getInt("estado") == 1);
                                DateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                                Date fechaNacimiento = ft.parse(data.getString("fechaNacimiento"));
                                _u = new Usuario(data.getString("idUsuario"), data.getString("nombre"), data.getString("apellido"), data.getString("correo"), fechaNacimiento, data.getString("username"), data.getString("password"), estado, data.getString("tipoUsuario"));
                            }else{
                                _u = null;
                            }
                        } else {
                            _u = null;
                        }
                    }
                }

                _db.close();
                return _u;
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return null;
            }
        }else{
            _db.close();
            return null;
        }
        
    }
    public static Usuario buscarUsuario(String correo, String password){
        _db.open();
        if(_db.isOpen()){
            try {
                Usuario _u = null;
                try (PreparedStatement consulta = _db.getStatement("SELECT * FROM Usuario WHERE correo = ? OR username = ?")) {
                    consulta.setString(1, correo);
                    consulta.setString(2, correo);
                    try (ResultSet data = consulta.executeQuery()) {
                        if (data != null) {
                            if(data.next()){
                                _u = Usuario_Controller.obtenerUsuario(data.getString("idUsuario"));
                            }else{
                                _u = null;
                            }
                            
                        } else {
                            _u = null;
                        }
                    }
                }
                
                _db.close();
                return _u;
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
    
    public static Usuario obtenerUsuario(String id){
        _db.open();
        if(_db.isOpen()){
            try {
                Usuario _u;
                try (PreparedStatement obtenerSQL = _db.getStatement("SELECT * FROM usuario WHERE idUsuario = ?;")) {
                    obtenerSQL.setString(1, id);
                    try (ResultSet data = obtenerSQL.executeQuery()) {
                        if (data != null) {
                            if(data.next()){
                                boolean estado = (data.getInt("estado") == 1);
                                DateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                                Date fechaNacimiento = ft.parse(data.getString("fechaNacimiento"));
                                _u = new Usuario(data.getString("idUsuario"), data.getString("nombre"), data.getString("apellido"), data.getString("correo"), fechaNacimiento, data.getString("username"), data.getString("password"), estado, data.getString("tipoUsuario"));
                            }else{
                                _u = null;
                            }
                        } else {
                            _u = null;
                        }
                    }
                }
                _db.close();
                return _u;
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return null;
            }
        }else{
            _db.close();
            return null;
        }
    }
    
    public static List<Usuario> obtenerUsuarios(){
        _db.open();
        if(_db.isOpen()){
            try {
                List<Usuario> _Ulist = new ArrayList();
                try (PreparedStatement obtenerSQL = _db.getStatement("SELECT idUsuario, nombre, apellido, correo, fechaNacimiento, username, password, estado, tipoUsuario FROM usuario;"); ResultSet data = obtenerSQL.executeQuery()) {
                    while (data.next()) {
                        boolean estado = (data.getInt("estado") == 1);
                        DateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                        Date fechaNacimiento = ft.parse(data.getString("fechaNacimiento"));

                        _Ulist.add(new Usuario(data.getString("idUsuario"), data.getString("nombre"), data.getString("apellido"), data.getString("correo"), fechaNacimiento, data.getString("username"), data.getString("password"), estado, data.getString("tipoUsuario")));
                    }
                }
                
                _db.close();
                return _Ulist;
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return null;
            }
        }else{
            _db.close();
            return null;
        }
        
    }
    
    public static List<Usuario> BuscarUsuarios(String campo, String buscar){
        _db.open();
        if(_db.isOpen()){
            try {
                List<Usuario> _Ulist = new ArrayList();
                try (PreparedStatement obtenerSQL = _db.getStatement("SELECT idUsuario, nombre, apellido, correo, fechaNacimiento, username, password, estado, tipoUsuario FROM usuario WHERE " + campo + " LIKE '%" + buscar + "%'"); ResultSet data = obtenerSQL.executeQuery()) {
                    while (data.next()) {
                        boolean estado = (data.getInt("estado") == 1);
                        DateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                        Date fechaNacimiento = ft.parse(data.getString("fechaNacimiento"));

                        _Ulist.add(new Usuario(data.getString("idUsuario"), data.getString("nombre"), data.getString("apellido"), data.getString("correo"), fechaNacimiento, data.getString("username"), data.getString("password"), estado, data.getString("tipoUsuario")));
                    }
                }
                _db.close();
                return _Ulist;
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return null;
            }
        }else{
            _db.close();
            return null;
        }
        
    }
    
    public static boolean modificarUsuario(Usuario _u){
        _db.open();
        if(_db.isOpen()){
            try {
                try (PreparedStatement modificarSQL = _db.getStatement("UPDATE usuario SET nombre = ?, apellido = ?, correo = ?, fechaNacimiento = ?, estado = ? WHERE idUsuario = ?")) {
                    modificarSQL.setString(1, _u.getNombre());
                    modificarSQL.setString(2, _u.getApellido());
                    modificarSQL.setString(3, _u.getCorreo());
                    modificarSQL.setDate(4, new java.sql.Date(_u.getFechaNacimiento().getTime()));
                    modificarSQL.setBoolean(5, _u.isEstado());
                    modificarSQL.setString(6, _u.getIdUsuario());
                    modificarSQL.executeUpdate();
                }
                _db.close();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return false;
            }
        }else{
            _db.close();
            return false;
        }
    }
    
    public static boolean modificarContrasenna(Usuario _u){
        _db.open();
        if(_db.isOpen()){
            try {
                try (PreparedStatement modificarSQL = _db.getStatement("UPDATE usuario SET password = ? WHERE idUsuario = ?")) {
                    modificarSQL.setString(1, _u.getPassword());
                    modificarSQL.setString(2, _u.getIdUsuario());
                    modificarSQL.executeUpdate();
                }
                _db.close();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return false;
            }
        }else{
            _db.close();
            return false;
        }
    }
    
    public static boolean eliminarUsuario(String id){
        _db.open();
        if(_db.isOpen()){
            try {
                try (PreparedStatement modificarSQL = _db.getStatement("DELETE FROM usuario WHERE idUsuario = ?;")) {
                    modificarSQL.setString(1, id);
                    modificarSQL.executeUpdate();
                }
                _db.close();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return false;
            }
        }else{
            _db.close();
            return false;
        }
    }
    
    public static int obtenerNumUsuario(String tipo){ //Obtiene el numero de usuarios registrados según el tipo
        _db.open();
        if(_db.isOpen()){
            try {
                int num;
                try (PreparedStatement query = _db.getStatement("SELECT COUNT(*) FROM usuario WHERE tipoUsuario = ?")) {
                    query.setString(1, tipo);
                    try (ResultSet data = query.executeQuery()) {
                        if(data.next()){
                            num = data.getInt(1);
                        }else{
                            num = -1;
                        }
                    }
                }
                _db.close();
                return num;
            } catch (SQLException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return -1;
            }
        }else{
            _db.close();
            return -1;
        }
    }
    
    public static int obtenerNumUsuario(){//Todos los usuarios
        _db.open();
        if(_db.isOpen()){
            try {
                int num;
                try (PreparedStatement query = _db.getStatement("SELECT COUNT(*) FROM usuario")) {
                    try (ResultSet data = query.executeQuery()) {
                        if(data.next()){
                            num = data.getInt(1);
                        }else{
                            num = -1;
                        }
                    }
                }
                _db.close();
                return num;
            } catch (SQLException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return -1;
            }
        }else{
            _db.close();
            return -1;
        }
    }
    
    public static boolean verificarUsuario(String username){ //Verifica si un username ya existe
        _db.open();
        if(_db.isOpen()){
            try {
                int num;
                try (PreparedStatement query = _db.getStatement("SELECT COUNT(*) FROM usuario WHERE username = ?")) {
                    query.setString(1, username);
                    try (ResultSet data = query.executeQuery()) {
                        if(data.next()){
                            num = data.getInt(1);
                        }else{
                            num = 1;
                        }
                    }
                }
                
                _db.close();
                return !(num > 0);
            }catch (SQLException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return false;
            }
        }else{
            _db.close();
            return false;
        }
    }
    
    public static boolean verificarCorreo(String correo){
        _db.open();
        if(_db.isOpen()){
            try {
                int num;
                try (PreparedStatement query = _db.getStatement("SELECT COUNT(*) FROM usuario WHERE correo = ?")) {
                    query.setString(1, correo);
                    try (ResultSet data = query.executeQuery()) {
                        if(data.next()){
                            num = data.getInt(1);
                        }else{
                            num = 1;
                        }
                    }
                }
                
                _db.close();
                return !(num > 0);
            } catch (SQLException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return false;
            }
        }else{
            _db.close();
            return false;
        }
    }
    
    public static boolean verificarCorreo(String correo, String id){
        _db.open();
        if(_db.isOpen()){
            try {
                int num;
                try (PreparedStatement query = _db.getStatement("SELECT COUNT(*) FROM usuario WHERE correo = ? AND idUsuario != ?")) {
                    query.setString(1, correo);
                    query.setString(2, id);
                    try (ResultSet data = query.executeQuery()) {
                        if(data.next()){
                            num = data.getInt(1);
                        }else{
                            num = 1;
                        }
                    }
                }
                _db.close();
                return !(num > 0);
            } catch (SQLException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return false;
            }
        }else{
            _db.close();
            return false;
        }
    }
}
