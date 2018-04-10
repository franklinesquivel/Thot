/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.modelos;

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
import sv.edu.udb.connection.DBConection;
import sv.edu.udb.libreria.Usuario;

/**
 *
 * @author Leonardo
 */
public class Usuario_Model{
    public static boolean insertar(Usuario _u){
        PreparedStatement insertarUsuario = DBConection.getStatement("INSERT INTO usuario(nombre, apellido, correo, fechaNacimiento, username, password, estado, tipoUsuario) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
        try {
            insertarUsuario.setString(1, _u.getNombre());
            insertarUsuario.setString(2, _u.getApellido());
            insertarUsuario.setString(3, _u.getCorreo());
            insertarUsuario.setDate(4, new java.sql.Date(_u.getFechaNacimiento().getTime()));
            insertarUsuario.setString(5, _u.getUsername());
            insertarUsuario.setString(6, _u.getPassword());
            insertarUsuario.setBoolean(7, _u.isEstado());
            char tipo = _u.getTipoUsuario().charAt(0);
            insertarUsuario.setString(8, String.valueOf(tipo));
            insertarUsuario.executeUpdate();
            return true;
        } catch (SQLException ex) {    
            Logger.getLogger(Usuario_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static Usuario obtenerUsuario(String id){
        PreparedStatement obtenerSQL = DBConection.getStatement("SELECT * FROM usuario WHERE idUsuario = ?;");
        try {
            obtenerSQL.setString(1, id);
            ResultSet data = obtenerSQL.executeQuery();
            Usuario _u = null;
            data.next();
            boolean estado = ((data.getInt("estado") == 1) ? true : false);
            DateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacimiento = ft.parse(data.getString("fechaNacimiento"));    
            _u = new Usuario(Integer.parseInt(data.getString("idUsuario")), data.getString("nombre"), data.getString("apellido"), data.getString("correo"), fechaNacimiento , data.getString("username"), data.getString("password"), estado, data.getString("tipoUsuario"));
            data.close();
            return ((_u != null) ? _u : null);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(Usuario_Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Usuario> obtenerUsuarios(){
        List<Usuario> _Ulist = new ArrayList();
        PreparedStatement obtenerSQL = DBConection.getStatement("SELECT idUsuario, nombre, apellido, correo, fechaNacimiento, username, password, estado, tipoUsuario FROM usuario;");
        
        try {
            ResultSet data = obtenerSQL.executeQuery();
            while(data.next()){
                boolean estado = ((data.getInt("estado") == 1) ? true : false);
                DateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaNacimiento = ft.parse(data.getString("fechaNacimiento"));
                
                _Ulist.add(new Usuario(Integer.parseInt(data.getString("idUsuario")), data.getString("nombre"), data.getString("apellido"), data.getString("correo"), fechaNacimiento, data.getString("username"), data.getString("password"), estado, data.getString("tipoUsuario")));
            }
            data.close();
            return _Ulist;
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(Usuario_Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Usuario> BuscarUsuarios(String campo, String buscar){
        List<Usuario> _Ulist = new ArrayList();
        PreparedStatement obtenerSQL = DBConection.getStatement("SELECT idUsuario, nombre, apellido, correo, fechaNacimiento, username, password, estado, tipoUsuario FROM usuario WHERE "+campo+" LIKE '%"+ buscar +"%'");
        
        try {
            ResultSet data = obtenerSQL.executeQuery();
            while(data.next()){
                boolean estado = ((data.getInt("estado") == 1) ? true : false);
                DateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaNacimiento = ft.parse(data.getString("fechaNacimiento"));
                
                _Ulist.add(new Usuario(Integer.parseInt(data.getString("idUsuario")), data.getString("nombre"), data.getString("apellido"), data.getString("correo"), fechaNacimiento, data.getString("username"), data.getString("password"), estado, data.getString("tipoUsuario")));
            }
            data.close();
            return _Ulist;
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(Usuario_Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static boolean modificarUsuario(Usuario _u){
        PreparedStatement modificarSQL = DBConection.getStatement("UPDATE usuario SET nombre = ?, apellido = ?, correo = ?, fechaNacimiento = ?, estado = ? WHERE idUsuario = ?");
        try{
            modificarSQL.setString(1, _u.getNombre());
            modificarSQL.setString(2, _u.getApellido());
            modificarSQL.setString(3, _u.getCorreo());
            modificarSQL.setDate(4, new java.sql.Date(_u.getFechaNacimiento().getTime()));
            modificarSQL.setBoolean(5, _u.isEstado());
            modificarSQL.setInt(6, _u.getIdUsuario());
            modificarSQL.executeUpdate();
            return true;
        }catch(SQLException ex){
            Logger.getLogger(Usuario_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean modificarContrasenna(Usuario _u){
        PreparedStatement modificarSQL = DBConection.getStatement("UPDATE usuario SET password = ? WHERE idUsuario = ?");
        try{
            modificarSQL.setString(1, _u.getPassword());
            modificarSQL.setInt(2, _u.getIdUsuario());
            modificarSQL.executeUpdate();
            return true;
        }catch(SQLException ex){
            Logger.getLogger(Usuario_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean eliminarUsuario(String id){
        PreparedStatement modificarSQL = DBConection.getStatement("DELETE FROM usuario WHERE idUsuario = ?;");
        try{
            modificarSQL.setString(1, id);
            modificarSQL.executeUpdate();
            return true;
        }catch(SQLException ex){
            Logger.getLogger(Usuario_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static int obtenerNumUsuario(String tipo){ //Obtiene el numero de usuarios registrados según el tipo
        PreparedStatement query = DBConection.getStatement("SELECT COUNT(*) FROM usuario WHERE tipoUsuario = ?");
        try{
            query.setString(1, tipo);
            ResultSet data = query.executeQuery();
            data.next();
            int num = data.getInt(1);
            data.close();
            return num;
        }catch(SQLException ex){
            Logger.getLogger(Usuario_Model.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    
    public static boolean verificarUsuario(String username){ //Verifica si un username ya existe
        PreparedStatement query = DBConection.getStatement("SELECT COUNT(*) FROM usuario WHERE username = ?");
        try{
            query.setString(1, username);
            ResultSet data = query.executeQuery();
            data.next();
            int num = data.getInt(1);
            data.close();
            return ((num > 0) ? false : true);
        }catch(SQLException ex){
            Logger.getLogger(Usuario_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean verificarCorreo(String correo){
        PreparedStatement query = DBConection.getStatement("SELECT COUNT(*) FROM usuario WHERE correo = ?");
        try{
            query.setString(1, correo);
            ResultSet data = query.executeQuery();
            data.next();
            int num = data.getInt(1);
            data.close();
            return ((num > 0) ? false : true);
        }catch(SQLException ex){
            Logger.getLogger(Usuario_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean verificarCorreo(String correo, int id){
        PreparedStatement query = DBConection.getStatement("SELECT COUNT(*) FROM usuario WHERE correo = ? AND idUsuario != ?");
        try{
            query.setString(1, correo);
            query.setInt(2, id);
            ResultSet data = query.executeQuery();
            data.next();
            int num = data.getInt(1);
            data.close();
            return ((num > 0) ? false : true);
        }catch(SQLException ex){
            Logger.getLogger(Usuario_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
