/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.models;

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
import sv.edu.udb.connection.DBConecction;
import sv.edu.udb.library.Autor;
import sv.edu.udb.library.Libro;

/**
 *
 * @author Frank
 */
public class Autor_Model {
    public static List<Autor> obtenerAutores(){
        List<Autor> _aList = new ArrayList();
        try {
            try (ResultSet data = DBConecction.getData("SELECT * FROM Autor;")) {
                while(data.next()){
                    Date date = null;
                    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    
                    try {
                        date = df.parse(data.getString(4));
                    } catch (ParseException ex) {
                        Logger.getLogger(Autor_Model.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    _aList.add(new Autor(data.getString(1), data.getString(2), data.getString(3), date));
                }
            }
            return _aList;
        } catch (SQLException ex) {    
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static Autor obtenerAutor(String idAutor, boolean relaciones){
        PreparedStatement insertarCategoria = DBConecction.getStatement("SELECT * FROM tema WHERE idTema = ?;");
        try {
            insertarCategoria.setString(1, idAutor);
            try (ResultSet data = insertarCategoria.executeQuery()) {
                while(data.next()){
                    Autor _a = new Autor(data.getString(1), data.getString(2), data.getString(3), data.getDate(4));
                    
                    if(relaciones){
                        PreparedStatement obtenerCategoriaLibro = DBConecction.getStatement("SELECT dAL.idLibro FROM Autor a INNER JOIN Detalle_LibroTema dAL ON a.idAutor = dAL.idAutor WHERE a.idAutor = ?;");
                        obtenerCategoriaLibro.setString(1, idAutor);
                        
                        ResultSet dataAL = obtenerCategoriaLibro.executeQuery();
                        
                        List<Libro> libros = new ArrayList();
                        
                        if (dataAL != null) {
                            while (dataAL.next()) {
                                libros.add(new Libro(dataAL.getString(1), dataAL.getString(2), dataAL.getString(3), dataAL.getString(4), dataAL.getString(5), dataAL.getString(6), dataAL.getString(7)));
                            }
                        }
                        
                        _a.setLibros(libros);
                    }
                    
                    return _a;
                }
            }
            return null;
        } catch (SQLException ex) {    
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static boolean insertar(Autor _a) {
        PreparedStatement insertarSQL = DBConecction.getStatement("INSERT INTO Autor VALUES(?, ?, ?, ?, ?);");
        try {
            insertarSQL.setString(1, _a.getIdAutor());
            insertarSQL.setString(2, _a.getNombres());
            insertarSQL.setString(3, _a.getApellidos());
            insertarSQL.setString(4, _a.getFechaNac().toString());
            insertarSQL.setString(5, _a.getPais().getIdPais());
            insertarSQL.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean modificar(Autor _a) {
        PreparedStatement modificarSQL = DBConecction.getStatement("UPDATE Autor SET nombres = ?, apellidos = ?, fechaNac = ?, idPais = ? WHERE idAutor = ?;");
        try {
            
            modificarSQL.setString(1, _a.getNombres());
            modificarSQL.setString(2, _a.getApellidos());
            modificarSQL.setString(3, _a.getFechaNac().toString());
            modificarSQL.setString(4, _a.getPais().getIdPais());
            modificarSQL.setString(5, _a.getIdAutor());
            modificarSQL.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean eliminar(Autor _a) {
        PreparedStatement eliminarSQL = DBConecction.getStatement("DELETE FROM Autor WHERE idAutor = ?;");
        try {
            eliminarSQL.setString(1, _a.getIdAutor());
            eliminarSQL.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
