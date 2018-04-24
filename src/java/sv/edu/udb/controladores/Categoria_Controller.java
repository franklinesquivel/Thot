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
import sv.edu.udb.libreria.Categoria;
import sv.edu.udb.libreria.Libro;

/**
 *
 * @author Frank
 */
public class Categoria_Controller {
    
    public static Categoria obtenerCategoria(int idCategoria, boolean relaciones){        
        try(Connection _cn = DBConnection.getConnection()){
            try {
                Categoria _c;
                try (PreparedStatement insertarCategoria = DBConnection.getStatement("SELECT * FROM categoria WHERE idCategoria = ?;", _cn)) {
                    insertarCategoria.setInt(1, idCategoria);
                    try (ResultSet data = insertarCategoria.executeQuery()) {
                        if (data != null) {
                            if (data.next()) {
                                _c = new Categoria(Integer.parseInt(data.getString("idCategoria")), data.getString("nombre"), data.getString("descripcion"));

                                if (relaciones) {
                                    List<Libro> libros;
                                    try (PreparedStatement obtenerCategoriaLibro = DBConnection.getStatement("SELECT l.idLibro FROM Categoria c INNER JOIN Libro l ON c.idCategoria = l.idCategoria WHERE c.idCategoria = ?;", _cn)) {
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
                            } else {
                                _c = null;
                            }
                        } else {
                            _c = null;
                        }
                    }
                }
                return _c;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Categoria_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Categoria> obtenerCategorias(){
        try(Connection _cn = DBConnection.getConnection()){
            try {
                List<Categoria> _lList = new ArrayList();
                try (PreparedStatement insertarLibro = DBConnection.getStatement("SELECT idCategoria, nombre, descripcion FROM categoria;", _cn); ResultSet data = insertarLibro.executeQuery()) {
                    while (data.next()) {
                        _lList.add(new Categoria(Integer.parseInt(data.getString("idCategoria")), data.getString("nombre"), data.getString("descripcion")));
                    }
                }
                return _lList;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Categoria_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Categoria> obtenerCategorias(String filtros){
        try (Connection _cn = DBConnection.getConnection()) {
            List<Categoria> _lList = new ArrayList();
            try {
                try (PreparedStatement obtenerCategorias = DBConnection.getStatement("SELECT idCategoria, nombre, descripcion FROM categoria " + filtros + ";",_cn); ResultSet data = obtenerCategorias.executeQuery()) {
                    while (data.next()) {
                        _lList.add(new Categoria(Integer.parseInt(data.getString("idCategoria")), data.getString("nombre"), data.getString("descripcion")));
                    }
                }
                return _lList;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Categoria_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
     public static boolean insertar(Categoria _c){
         try (Connection _cn = DBConnection.getConnection()) {
             try {
                 try (PreparedStatement insertarSQL = DBConnection.getStatement("INSERT INTO categoria(nombre, descripcion) VALUES(?, ?);", _cn)) {
                     insertarSQL.setString(1, _c.getNombre());
                     insertarSQL.setString(2, _c.getDescripcion());
                     insertarSQL.executeUpdate();
                 }
                 return true;
             } catch (SQLException ex) {
                 Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                 return false;
             }
         } catch (SQLException ex) {
             Logger.getLogger(Categoria_Controller.class.getName()).log(Level.SEVERE, null, ex);
             return false;
         }
    }
    
    public static boolean modificarCategoria(Categoria c){
        try (Connection _cn = DBConnection.getConnection()) {
            try {
                try (PreparedStatement modificarSQL = DBConnection.getStatement("UPDATE categoria SET nombre = ?, descripcion = ? WHERE idCategoria = ?;", _cn)) {
                    modificarSQL.setString(1, c.getNombre());
                    modificarSQL.setString(2, c.getDescripcion());
                    modificarSQL.setInt(3, c.getIdCategoria());
                    modificarSQL.executeUpdate();
                }
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Categoria_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean eliminarCategoria(int id){
        try (Connection _cn = DBConnection.getConnection()) {
            try {
                try (PreparedStatement eliminarSQL = DBConnection.getStatement("DELETE FROM categoria WHERE idCategoria = ?;", _cn)) {
                    eliminarSQL.setInt(1, id);
                    eliminarSQL.executeUpdate();
                }
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Categoria_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean verificar(String nombre){ //Verifica la existencia
        try (Connection _cn = DBConnection.getConnection()) {
            try {
                int num;
                try (PreparedStatement query = DBConnection.getStatement("SELECT COUNT(*) FROM categoria WHERE LOWER(nombre) = ?", _cn)) {
                    query.setString(1, nombre.toLowerCase());
                    try (ResultSet data = query.executeQuery()) {
                        if (data.next()) {
                            num = data.getInt(1);
                        } else {
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
            Logger.getLogger(Categoria_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
