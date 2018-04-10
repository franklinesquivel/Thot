/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.modelos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.connection.DBConection;
import sv.edu.udb.libreria.Categoria;
import sv.edu.udb.libreria.Libro;

/**
 *
 * @author Frank
 */
public class Categoria_Model {
    
    public static Categoria obtenerCategoria(int idCategoria, boolean relaciones){
        PreparedStatement insertarCategoria = DBConection.getStatement("SELECT * FROM categoria WHERE idCategoria = ?;");
        try {
            insertarCategoria.setInt(1, idCategoria);
            try (ResultSet data = insertarCategoria.executeQuery()) {
                while(data.next()){
                    Categoria _c = new Categoria(Integer.parseInt(data.getString("idCategoria")), data.getString("nombre"), data.getString("descripcion"));
                    
                    if(relaciones){
                        PreparedStatement obtenerCategoriaLibro = DBConection.getStatement("SELECT l.idLibro FROM Categoria c INNER JOIN Libro l ON c.idCategoria = l.idCategoria WHERE c.idCategoria = ?;");
                        obtenerCategoriaLibro.setInt(1, idCategoria);
                        
                        ResultSet dataCL = obtenerCategoriaLibro.executeQuery();
                        
                        List<Libro> libros = new ArrayList();
                        
                        if (dataCL != null) {
                            while (dataCL.next()) {
                                libros.add(new Libro(dataCL.getString(1), dataCL.getString(2), dataCL.getString(3), dataCL.getString(4), dataCL.getString(5), dataCL.getString(6), dataCL.getString(7)));
                            }
                        }
                        
                        _c.setLibros(libros);
                    }
                    
                    return _c;
                }
            }
            return null;
        } catch (SQLException ex) {    
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Categoria> obtenerCategorias(){
        List<Categoria> _lList = new ArrayList();
        PreparedStatement insertarLibro = DBConection.getStatement("SELECT idCategoria, nombre, descripcion FROM categoria;");
        try {
            try (ResultSet data = insertarLibro.executeQuery()) {
                while(data.next()){
                    _lList.add(new Categoria(Integer.parseInt(data.getString("idCategoria")), data.getString("nombre"), data.getString("descripcion")));
                }
            }
            return _lList;
        } catch (SQLException ex) {    
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Categoria> obtenerCategorias(String filtros){
        List<Categoria> _lList = new ArrayList();
        PreparedStatement insertarLibro = DBConection.getStatement("SELECT idCategoria, nombre, descripcion FROM categoria " + filtros + ";");
        try {
            try (ResultSet data = insertarLibro.executeQuery()) {
                while(data.next()){
                    _lList.add(new Categoria(Integer.parseInt(data.getString("idCategoria")), data.getString("nombre"), data.getString("descripcion")));
                }
            }
            return _lList;
        } catch (SQLException ex) {    
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
     public static boolean insertar(Categoria _c){
        PreparedStatement insertarSQL = DBConection.getStatement("INSERT INTO categoria(nombre, descripcion) VALUES(?, ?);");
        try {
            insertarSQL.setString(1, _c.getNombre());
            insertarSQL.setString(2, _c.getDescripcion());
            insertarSQL.executeUpdate();
            return true;
        } catch (SQLException ex) {    
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean modificarCategoria(Categoria c){
        PreparedStatement modificarSQL = DBConection.getStatement("UPDATE categoria SET nombre = ?, descripcion = ? WHERE idCategoria = ?;");
        try{
            modificarSQL.setString(1, c.getNombre());
            modificarSQL.setString(2, c.getDescripcion());
            modificarSQL.setInt(3, c.getIdCategoria());
            modificarSQL.executeUpdate();
            return true;
        }catch(SQLException ex){
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean eliminarCategoria(int id){
        PreparedStatement eliminarSQL = DBConection.getStatement("DELETE FROM categoria WHERE idCategoria = ?;");
        try{
            eliminarSQL.setInt(1, id);
            eliminarSQL.executeUpdate();
            return true;
        }catch(SQLException ex){
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
