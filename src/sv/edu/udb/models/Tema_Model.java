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
import sv.edu.udb.connection.DBConection;
import sv.edu.udb.library.Libro;
import sv.edu.udb.library.Tema;

/**
 *
 * @author Frank
 */
public class Tema_Model {
    public static List<Tema> obtenerTemas(){
        List<Tema> _tList = new ArrayList();
        try {
            try (ResultSet data = DBConection.getData("SELECT * FROM Tema;")) {
                while(data.next()){
                    _tList.add(new Tema(data.getInt(1), data.getString(2)));
                }
            }
            return _tList;
        } catch (SQLException ex) {    
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Tema> obtenerTemas(String filtros){
        List<Tema> _tList = new ArrayList();
        try {
            try (ResultSet data = DBConection.getData("SELECT * FROM Tema " + filtros + ";")) {
                while(data.next()){
                    _tList.add(new Tema(data.getInt(1), data.getString(2)));
                }
            }
            return _tList;
        } catch (SQLException ex) {    
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Tema> buscarTemas(String cadena){
        List<Tema> _t = new ArrayList();
        PreparedStatement consultaSQL = DBConection.getStatement("SELECT idTema,descripcion FROM tema WHERE descripcion LIKE '%"+cadena +"%'");
        try{
            ResultSet data = consultaSQL.executeQuery();
            while(data.next()){
                _t.add(new Tema(Integer.parseInt(data.getString("idTema")),data.getString("descripcion")));
            }
            data.close();
            return _t;
        }catch(SQLException ex){
            Logger.getLogger(Tema_Model.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }
    }
    
    public static Tema obtenerTema(int idTema, boolean relaciones){
        PreparedStatement insertarCategoria = DBConection.getStatement("SELECT * FROM tema WHERE idTema = ?;");
        try {
            insertarCategoria.setInt(1, idTema);
            try (ResultSet data = insertarCategoria.executeQuery()) {
                while(data.next()){
                    Tema _t = new Tema(Integer.parseInt(data.getString(1)), data.getString(2));
                    
                    if(relaciones){
                        PreparedStatement obtenerCategoriaLibro = DBConection.getStatement("SELECT dLT.idLibro FROM Tema t INNER JOIN Detalle_LibroTema dLT ON t.idTema = dLT.idTema WHERE t.idTema = ?;");
                        obtenerCategoriaLibro.setInt(1, idTema);
                        
                        ResultSet dataTL = obtenerCategoriaLibro.executeQuery();
                        
                        List<Libro> libros = new ArrayList();
                        
                        if (dataTL != null) {
                            while (dataTL.next()) {
                                libros.add(new Libro(dataTL.getString(1), dataTL.getString(2), dataTL.getString(3), dataTL.getString(4), dataTL.getString(5), dataTL.getString(6), dataTL.getString(7)));
                            }
                        }
                        
                        _t.setLibros(libros);
                    }
                    return _t;
                }
            }
            return null;
        } catch (SQLException ex) {    
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static boolean insertar(Tema _t){
        PreparedStatement insertarSQL = DBConection.getStatement("INSERT INTO Tema(descripcion) VALUES(?);");
        try{
            insertarSQL.setString(1, _t.getDescripcion());
            insertarSQL.executeUpdate();
            return true;
        }catch(SQLException ex){
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean modificar(Tema _t){
        PreparedStatement modificarSQL = DBConection.getStatement("UPDATE Tema SET descripcion = ? WHERE idTema = ?;");
        try{
            modificarSQL.setString(1, _t.getDescripcion());
            modificarSQL.setInt(2, _t.getIdTema());
            modificarSQL.executeUpdate();
            return true;
        }catch(SQLException ex){
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean eliminar(Tema _t){
        PreparedStatement eliminarSQL = DBConection.getStatement("DELETE FROM Tema WHERE idTema = ?;");
        try{
            eliminarSQL.setInt(1, _t.getIdTema());
            eliminarSQL.executeUpdate();
            return true;
        }catch(SQLException ex){
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
