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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.connection.DBConnection;
import sv.edu.udb.libreria.Usuario;

/**
 *
 * @author Leonardo
 */
public class Usuario_Controller{
    
    
    public static boolean insertar(Usuario _u){
        try(Connection _cn = DBConnection.getConnection()){
            try {
                try (PreparedStatement insertarUsuario = DBConnection.getStatement("INSERT INTO usuario(idUsuario, nombre, apellido, correo, fechaNacimiento, username, password, estado, tipoUsuario) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)", _cn)) {
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
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static Usuario obtenerUsuarioCorreo(String _e){
        try(Connection _cn = DBConnection.getConnection()){
            try {
                Usuario _u;
                try (PreparedStatement obtenerSQL = DBConnection.getStatement("SELECT * FROM usuario WHERE correo = ?;", _cn)) {
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

                return _u;
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static Usuario buscarUsuario(String correo, String password){
        try(Connection _cn = DBConnection.getConnection()){
            try {
                Usuario _u;
                try (PreparedStatement consulta = DBConnection.getStatement("SELECT * FROM Usuario WHERE correo = ? OR username = ?", _cn)) {
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
                
                return _u;
            } catch (SQLException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static Usuario obtenerUsuario(String id){
        try(Connection _cn = DBConnection.getConnection()){
            try {
                Usuario _u;
                try (PreparedStatement obtenerSQL = DBConnection.getStatement("SELECT * FROM usuario WHERE idUsuario = ?;", _cn)) {
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
                
                return _u;
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Usuario> obtenerUsuarios(){
        try(Connection _cn = DBConnection.getConnection()){
            try {
                List<Usuario> _Ulist = new ArrayList();
                try (PreparedStatement obtenerSQL = DBConnection.getStatement("SELECT idUsuario, nombre, apellido, correo, fechaNacimiento, username, password, estado, tipoUsuario FROM usuario;", _cn); ResultSet data = obtenerSQL.executeQuery()) {
                    while (data.next()) {
                        boolean estado = (data.getInt("estado") == 1);
                        DateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                        Date fechaNacimiento = ft.parse(data.getString("fechaNacimiento"));

                        _Ulist.add(new Usuario(data.getString("idUsuario"), data.getString("nombre"), data.getString("apellido"), data.getString("correo"), fechaNacimiento, data.getString("username"), data.getString("password"), estado, data.getString("tipoUsuario")));
                    }
                }
                
                return _Ulist;
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Usuario> BuscarUsuarios(String campo, String buscar){
        try(Connection _cn = DBConnection.getConnection()){
            try {
                List<Usuario> _Ulist = new ArrayList();
                try (PreparedStatement obtenerSQL = DBConnection.getStatement("SELECT idUsuario, nombre, apellido, correo, fechaNacimiento, username, password, estado, tipoUsuario FROM usuario WHERE " + campo + " LIKE '%" + buscar + "%'", _cn); ResultSet data = obtenerSQL.executeQuery()) {
                    while (data.next()) {
                        boolean estado = (data.getInt("estado") == 1);
                        DateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                        Date fechaNacimiento = ft.parse(data.getString("fechaNacimiento"));

                        _Ulist.add(new Usuario(data.getString("idUsuario"), data.getString("nombre"), data.getString("apellido"), data.getString("correo"), fechaNacimiento, data.getString("username"), data.getString("password"), estado, data.getString("tipoUsuario")));
                    }
                }
                return _Ulist;
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static boolean modificarUsuario(Usuario _u){
        try(Connection _cn = DBConnection.getConnection()){
            try {
                try (PreparedStatement modificarSQL = DBConnection.getStatement("UPDATE usuario SET nombre = ?, apellido = ?, correo = ?, fechaNacimiento = ?, estado = ? WHERE idUsuario = ?;", _cn)) {
                    modificarSQL.setString(1, _u.getNombre());
                    modificarSQL.setString(2, _u.getApellido());
                    modificarSQL.setString(3, _u.getCorreo());
                    modificarSQL.setDate(4, new java.sql.Date(_u.getFechaNacimiento().getTime()));
                    modificarSQL.setBoolean(5, _u.isEstado());
                    modificarSQL.setString(6, _u.getIdUsuario());
                    modificarSQL.executeUpdate();
                }
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean modificarContrasenna(Usuario _u){
        try(Connection _cn = DBConnection.getConnection()){
            try {
                try (PreparedStatement modificarSQL = DBConnection.getStatement("UPDATE usuario SET password = ? WHERE idUsuario = ?;", _cn)) {
                    modificarSQL.setString(1, _u.getPassword());
                    modificarSQL.setString(2, _u.getIdUsuario());
                    modificarSQL.executeUpdate();
                }
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    
    public static boolean eliminarUsuario(String id){
        try(Connection _cn = DBConnection.getConnection()){
            try {
                try (PreparedStatement modificarSQL = DBConnection.getStatement("DELETE FROM usuario WHERE idUsuario = ?;", _cn)) {
                    modificarSQL.setString(1, id);
                    modificarSQL.executeUpdate();
                }
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    
    public static int obtenerNumUsuario(String tipo){ //Obtiene el numero de usuarios registrados según el tipo
        try(Connection _cn = DBConnection.getConnection()){
            try {
                int num;
                try (PreparedStatement query = DBConnection.getStatement("SELECT COUNT(*) FROM usuario WHERE tipoUsuario = ?;", _cn)) {
                    query.setString(1, tipo);
                    try (ResultSet data = query.executeQuery()) {
                        if(data.next()){
                            num = data.getInt(1);
                        }else{
                            num = -1;
                        }
                    }
                }
                return num;
            } catch (SQLException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    
    public static int obtenerNumUsuario(){//Todos los usuarios
        try(Connection _cn = DBConnection.getConnection()){
            try {
                int num;
                try (PreparedStatement query = DBConnection.getStatement("SELECT COUNT(*) FROM usuario;", _cn)) {
                    try (ResultSet data = query.executeQuery()) {
                        if(data.next()){
                            num = data.getInt(1);
                        }else{
                            num = -1;
                        }
                    }
                }
                return num;
            } catch (SQLException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    
    public static boolean verificarUsuario(String username){ //Verifica si un username ya existe
        try(Connection _cn = DBConnection.getConnection()){
            try {
                int num;
                try (PreparedStatement query = DBConnection.getStatement("SELECT COUNT(*) FROM usuario WHERE username = ?;", _cn)) {
                    query.setString(1, username);
                    try (ResultSet data = query.executeQuery()) {
                        if(data.next()){
                            num = data.getInt(1);
                        }else{
                            num = 1;
                        }
                    }
                }
                
                return !(num > 0);
            }catch (SQLException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean verificarCorreo(String correo){
        try(Connection _cn = DBConnection.getConnection()){
            try {
                int num;
                try (PreparedStatement query = DBConnection.getStatement("SELECT COUNT(*) FROM usuario WHERE correo = ?", _cn)) {
                    query.setString(1, correo);
                    try (ResultSet data = query.executeQuery()) {
                        if(data.next()){
                            num = data.getInt(1);
                        }else{
                            num = 1;
                        }
                    }
                }
                
                return !(num > 0);
            } catch (SQLException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean verificarCorreo(String correo, String id){
        try(Connection _cn = DBConnection.getConnection()){
            try {
                int num;
                try (PreparedStatement query = DBConnection.getStatement("SELECT COUNT(*) FROM usuario WHERE correo = ? AND idUsuario != ?", _cn)) {
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
                return !(num > 0);
            } catch (SQLException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
