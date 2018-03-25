/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.connection.DBConecction;
import sv.edu.udb.library.Imprenta;
import sv.edu.udb.library.Libro;

/**
 *
 * @author Frank
 */
public class Imprenta_Model {
    
    public static List<Imprenta> obtenerImprentas(){
        List<Imprenta> _iList = new ArrayList();
        try {
            try (ResultSet data = DBConecction.getData("SELECT * FROM Imprenta;")) {
                while(data.next()){
                    _iList.add(new Imprenta(data.getString(1), data.getString(2), data.getString(3)));
                }
            }
            return _iList;
        } catch (SQLException ex) {    
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static Imprenta obtenerImprenta(String idImprenta, boolean relaciones){
        PreparedStatement insertarCategoria = DBConecction.getStatement("SELECT * FROM Imprenta WHERE idImprenta = ?;");
        try {
            insertarCategoria.setString(1, idImprenta);
            try (ResultSet data = insertarCategoria.executeQuery()) {
                while(data.next()){
                    Imprenta _i = new Imprenta(data.getString(1), data.getString(2), data.getString(3));
                    
                    if(relaciones){
                        PreparedStatement obtenerCategoriaLibro = DBConecction.getStatement("SELECT l.idLibro FROM Imprenta i INNER JOIN Libro l ON i.idImprenta = l.idImprenta WHERE i.idImprenta = ?;");
                        obtenerCategoriaLibro.setString(1, idImprenta);
                        
                        ResultSet dataIL = obtenerCategoriaLibro.executeQuery();
                        
                        List<Libro> libros = new ArrayList();
                        
                        if (dataIL != null) {
                            while (dataIL.next()) {
                                libros.add(new Libro(dataIL.getString(1), dataIL.getString(2), dataIL.getString(3), dataIL.getString(4), dataIL.getString(5), dataIL.getString(6), dataIL.getString(7)));
                            }
                        }
                        
                        _i.setLibros(libros);
                    }
                    
                    return _i;
                }
            }
            return null;
        } catch (SQLException ex) {    
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static boolean insertar(Imprenta _i){
        PreparedStatement insertarSQL = DBConecction.getStatement("INSERT INTO Imprenta VALUES(?, ?, ?);");
        try{
            insertarSQL.setString(1, _i.getIdImprenta());
            insertarSQL.setString(2, _i.getNombre());
            insertarSQL.setString(3, _i.getDireccion());
            insertarSQL.executeUpdate();
            return true;
        }catch(SQLException ex){
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean modificar(Imprenta _i){
        PreparedStatement modificarSQL = DBConecction.getStatement("UPDATE Imprenta SET nombre = ?, direccion = ? WHERE idImprenta = ?;");
        try{
            modificarSQL.setString(1, _i.getNombre());
            modificarSQL.setString(2, _i.getDireccion());
            modificarSQL.setString(3, _i.getIdImprenta());
            modificarSQL.executeUpdate();
            return true;
        }catch(SQLException ex){
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean eliminar(Imprenta _i){
        PreparedStatement eliminarSQL = DBConecction.getStatement("DELETE FROM Tema WHERE idImprenta = ?;");
        try{
            eliminarSQL.setString(1, _i.getIdImprenta());
            eliminarSQL.executeUpdate();
            return true;
        }catch(SQLException ex){
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
