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
import sv.edu.udb.libreria.Libro;
import sv.edu.udb.libreria.Tema;

/**
 *
 * @author Frank
 */
public class Tema_Controller {
    
    private final static DB _db = new DB();
    
    public static List<Tema> obtenerTemas(){
        _db.open();
        if(_db.isOpen()){
            List<Tema> _tList = new ArrayList();
            try {
                try (ResultSet data = _db.getData("SELECT * FROM Tema;")) {
                    while (data.next()) {
                        _tList.add(new Tema(data.getInt(1), data.getString(2)));
                    }
                }
                _db.close();
                return _tList;
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
    
    public static List<Tema> obtenerTemas(String filtros){
        _db.open();
        if(_db.isOpen()){
            List<Tema> _tList = new ArrayList();
            try {
                try (ResultSet data = _db.getData("SELECT * FROM Tema " + filtros + ";")) {
                    while (data.next()) {
                        _tList.add(new Tema(data.getInt(1), data.getString(2)));
                    }
                }
                _db.close();
                return _tList;
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
    
    public static List<Tema> buscarTemas(String cadena){
        _db.open();
        if(_db.isOpen()){
            List<Tema> _t = new ArrayList();

            try {
                try (PreparedStatement consultaSQL = _db.getStatement("SELECT idTema,descripcion FROM tema WHERE descripcion LIKE '%" + cadena + "%'"); ResultSet data = consultaSQL.executeQuery()) {
                    while (data.next()) {
                        _t.add(new Tema(Integer.parseInt(data.getString("idTema")), data.getString("descripcion")));
                    }
                }

                _db.close();
                return _t;
            } catch (SQLException ex) {
                Logger.getLogger(Tema_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return null;
            }
        }else{
            _db.close();
            return null;
        }
    }
    
    public static Tema obtenerTema(int idTema, boolean relaciones){
        _db.open();
        if(_db.isOpen()){
            try {
                Tema _t = null;
                try (PreparedStatement obtenerTema = _db.getStatement("SELECT * FROM tema WHERE idTema = ?;")) {
                    obtenerTema.setInt(1, idTema);
                    try (ResultSet data = obtenerTema.executeQuery()) {
                        if (data.next()) {
                            _t = new Tema(Integer.parseInt(data.getString(1)), data.getString(2));

                            if (relaciones) {
                                try (PreparedStatement obtenerTemaLibro = _db.getStatement("SELECT dLT.idLibro FROM Tema t INNER JOIN Detalle_LibroTema dLT ON t.idTema = dLT.idTema WHERE t.idTema = ?;")) {
                                    obtenerTemaLibro.setInt(1, idTema);

                                    ResultSet dataTL = obtenerTemaLibro.executeQuery();

                                    List<Libro> libros = new ArrayList();

                                    if (dataTL != null) {
                                        while (dataTL.next()) {
                                            libros.add(new Libro(dataTL.getString(1), dataTL.getString(2), dataTL.getString(3), dataTL.getString(4), dataTL.getString(5), dataTL.getString(6), dataTL.getString(7)));
                                        }
                                        dataTL.close();
                                    }

                                    _t.setLibros(libros);
                                }
                            }
                        }else{
                            _t = null;
                        }
                    }
                }
                
                _db.close();
                return _t;
            } catch (SQLException ex) {
                Logger.getLogger(Tema_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return null;
            }
        }else{
            _db.close();
            return null;
        }
        
    }
    
    public static boolean insertar(Tema _t){
        _db.open();
        if(_db.isOpen()){
            try {
                try (PreparedStatement insertarSQL = _db.getStatement("INSERT INTO Tema(descripcion) VALUES(?);")) {
                    insertarSQL.setString(1, _t.getDescripcion());
                    insertarSQL.executeUpdate();
                }
                
                _db.close();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Tema_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return false;
            }
        }else{
            _db.close();
            return false;
        }
        
    }
    
    public static boolean modificar(Tema _t){
        _db.open();
        if(_db.isOpen()){
            try {
                try (PreparedStatement modificarSQL = _db.getStatement("UPDATE Tema SET descripcion = ? WHERE idTema = ?;")) {
                    modificarSQL.setString(1, _t.getDescripcion());
                    modificarSQL.setInt(2, _t.getIdTema());
                    modificarSQL.executeUpdate();
                }
                _db.close();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Tema_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return false;
            }
        }else{
            _db.close();
            return false;
        }
        
    }
    public static boolean verificarT(String descripcion){
        _db.open();
        if(_db.isOpen()){
            try {
                int cuenta;
                try (PreparedStatement query = _db.getStatement("SELECT COUNT(*) FROM Tema WHERE LOWER(descripcion)=?")) {
                    query.setString(1, descripcion.toLowerCase());
                    try (ResultSet data = query.executeQuery()) {
                        if(data.next()){
                            cuenta = data.getInt(1);
                        }else{
                            cuenta = 1;
                        }
                        
                    }
                }

                _db.close();
                return !(cuenta > 0);
            } catch (SQLException ex) {
                Logger.getLogger(Tema_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return false;
            }
        }else{
            _db.close();
            return false;
        }
    }
    
    public static boolean eliminar(Tema _t){
        _db.open();
        if(_db.isOpen()){
            try {
                try (PreparedStatement eliminarSQL = _db.getStatement("DELETE FROM Tema WHERE idTema = ?;")) {
                    eliminarSQL.setInt(1, _t.getIdTema());
                    eliminarSQL.executeUpdate();
                }
                _db.close();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Tema_Controller.class.getName()).log(Level.SEVERE, null, ex);
                _db.close();
                return false;
            }
        }else{
            _db.close();
            return false;
        }
    }
}
