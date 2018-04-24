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
import sv.edu.udb.libreria.Categoria;
import sv.edu.udb.libreria.Libro;

/**
 *
 * @author Frank
 */
public class Categoria_Controller {
    
    private final static DB _db = new DB();
    
    public static Categoria obtenerCategoria(int idCategoria, boolean relaciones){
        _db.open();
        if(_db.isOpen()){
            try {
                Categoria _c;
                try (PreparedStatement insertarCategoria = _db.getStatement("SELECT * FROM categoria WHERE idCategoria = ?;")) {
                    insertarCategoria.setInt(1, idCategoria);
                    try (ResultSet data = insertarCategoria.executeQuery()) {
                        if(data != null){
                            if(data.next()){
                                _c = new Categoria(Integer.parseInt(data.getString("idCategoria")), data.getString("nombre"), data.getString("descripcion"));

                                if (relaciones) {
                                    List<Libro> libros;
                                    try (PreparedStatement obtenerCategoriaLibro = _db.getStatement("SELECT l.idLibro FROM Categoria c INNER JOIN Libro l ON c.idCategoria = l.idCategoria WHERE c.idCategoria = ?;")) {
                                        obtenerCategoriaLibro.setInt(1, idCategoria);
                                        ResultSet dataCL = obtenerCategoriaLibro.executeQuery();

                                        libros = new ArrayList();

                                        if (dataCL != null) {
                                            while (dataCL.next()) {
                                                libros.add(new Libro(dataCL.getString(1), dataCL.getString(2), dataCL.getString(3), dataCL.getString(4), dataCL.getString(5), dataCL.getString(6), dataCL.getString(7)));
                                            }
                                            dataCL.close();
                                        }
                                    }

                                    _c.setLibros(libros);
                                }
                            }else{
                                _c = null;
                            }
                        }else{
                            _c = null;
                        }
                    }
                }
                
                _db.close();
                return _c;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return null;
            }
        }else{
            _db.close();
            return null;
        }
    }
    
    public static List<Categoria> obtenerCategorias(){
        
        _db.open();
        if(_db.isOpen()){
            try {
                List<Categoria> _lList = new ArrayList();
                try (PreparedStatement insertarLibro = _db.getStatement("SELECT idCategoria, nombre, descripcion FROM categoria;"); ResultSet data = insertarLibro.executeQuery()) {
                    while (data.next()) {
                        _lList.add(new Categoria(Integer.parseInt(data.getString("idCategoria")), data.getString("nombre"), data.getString("descripcion")));
                    }
                }
                _db.close();
                return _lList;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return null;
            }
        }else{
            _db.close();
            return null;
        }
    }
    
    public static List<Categoria> obtenerCategorias(String filtros){
        _db.open();
        if(_db.isOpen()){
            List<Categoria> _lList = new ArrayList();
            try {
                try (PreparedStatement obtenerCategorias = _db.getStatement("SELECT idCategoria, nombre, descripcion FROM categoria " + filtros + ";");ResultSet data = obtenerCategorias.executeQuery()) {
                    while (data.next()) {
                        _lList.add(new Categoria(Integer.parseInt(data.getString("idCategoria")), data.getString("nombre"), data.getString("descripcion")));
                    }
                }
                
                _db.close();
                return _lList;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return null;
            }  
        }else{
            _db.close();
            return null;
        }
        
    }
    
     public static boolean insertar(Categoria _c){
        _db.open();
        if(_db.isOpen()){
            try {
                try (PreparedStatement insertarSQL = _db.getStatement("INSERT INTO categoria(nombre, descripcion) VALUES(?, ?);")) {
                    insertarSQL.setString(1, _c.getNombre());
                    insertarSQL.setString(2, _c.getDescripcion());
                    insertarSQL.executeUpdate();
                }
                _db.close();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return false;
            }   
        }else{
            _db.close();
            return false;
        }
    }
    
    public static boolean modificarCategoria(Categoria c){
        _db.open();
        if(_db.isOpen()){
            try {
                try (PreparedStatement modificarSQL = _db.getStatement("UPDATE categoria SET nombre = ?, descripcion = ? WHERE idCategoria = ?;")) {
                    modificarSQL.setString(1, c.getNombre());
                    modificarSQL.setString(2, c.getDescripcion());
                    modificarSQL.setInt(3, c.getIdCategoria());
                    modificarSQL.executeUpdate();
                }
                _db.close();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return false;
            }
        }else{
            _db.close();
            return false;
        }
    }
    
    public static boolean eliminarCategoria(int id){
        _db.open();
        if(_db.isOpen()){
            try {
                try (PreparedStatement eliminarSQL = _db.getStatement("DELETE FROM categoria WHERE idCategoria = ?;")) {
                    eliminarSQL.setInt(1, id);
                    eliminarSQL.executeUpdate();
                }
                _db.close();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return false;
            }
        }else{
            _db.close();
            return false;
        }
        
    }
    
    public static boolean verificar(String nombre){ //Verifica la existencia
        _db.open();
        if(_db.isOpen()){
            try {
                int num;
                try (PreparedStatement query = _db.getStatement("SELECT COUNT(*) FROM categoria WHERE LOWER(nombre) = ?")) {
                    query.setString(1, nombre.toLowerCase());
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
