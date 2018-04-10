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
import sv.edu.udb.libreria.Autor;
import sv.edu.udb.libreria.Categoria;
import sv.edu.udb.libreria.Imprenta;
import sv.edu.udb.libreria.Libro;
import sv.edu.udb.libreria.Tema;

/**
 *
 * @author Frank
 */
public class Libro_Model {
    
    public static String nuevoId(){
        String _id;
        int _c;
        try {
            ResultSet _r = DBConection.getData("SELECT MAX(CAST(SUBSTRING(idLibro, 3, 5) AS UNSIGNED)) AS newId FROM Libro;");
            _r.next();
            if(_r.getString("newId") != null){
                _c = Integer.parseInt(_r.getString("newId")) + 1;

                if (_c < 10) {
                    _id = "L000" + _c;
                } else if (_c >= 10 && _c < 100) {
                    _id = "L00" + _c;
                } else if (_c >= 100 && _c < 1000) {
                    _id = "L0" + _c;
                } else {
                    _id = "L" + _c;
                }
            }else{
                _id = "L0001";
            }
            _r.close();
            return _id;
        } catch (SQLException ex) {
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static boolean validarIsbn(String _isbn){
        ResultSet _r = DBConection.getData("SELECT COUNT(*) AS c FROM Libro WHERE isbn = '" + _isbn + "';");
        try {
            if(_r.next()){
                int _c = Integer.parseInt(_r.getString("c"));
                _r.close();
                return _c > 0;
            }else{
                _r.close();
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static Libro obtenerLibro(String id, boolean relaciones){
        PreparedStatement obtenerLibro = DBConection.getStatement("SELECT * FROM Libro WHERE idLibro = ?;");
        try {
            obtenerLibro.setString(1, id);
            try (ResultSet dataLibro = obtenerLibro.executeQuery()) {
                while(dataLibro.next()){
                    
                    Libro _l = new Libro(
                            dataLibro.getString(1),
                            dataLibro.getString(2),
                            dataLibro.getString(3),
                            dataLibro.getString(4),
                            dataLibro.getString(5),
                            dataLibro.getString(6),
                            dataLibro.getString(7),
                            new Imprenta(dataLibro.getString(8), false),
                            new Categoria(dataLibro.getInt(9), false)
                    );
                    
                    if(relaciones){
                        PreparedStatement obtenerLibroAutor = DBConection.getStatement("SELECT dAL.idAutor FROM Libro l INNER JOIN Detalle_AutorLibro dAL ON l.idLibro = dAL.idLibro WHERE l.idLibro = ?;");
                        PreparedStatement obtenerLibroTema = DBConection.getStatement("SELECT dLT.idTema FROM Libro l INNER JOIN Detalle_LibroTema dLT ON l.idLibro = dLT.idLibro WHERE l.idLibro = ?;");
                        
                        obtenerLibroAutor.setString(1, id);
                        obtenerLibroTema.setString(1, id);
                        
                        ResultSet dataAL = obtenerLibroAutor.executeQuery();
                        ResultSet dataLT = obtenerLibroTema.executeQuery();
                        
                        List<Autor> autores = new ArrayList();
                        List<Tema> temas = new ArrayList();
                        
                        if (dataAL != null) {
                            while (dataAL.next()) {
                                autores.add(new Autor(dataAL.getString(1), false));
                            }
                        }
                        
                        if (dataLT != null) {
                            while (dataLT.next()) {
                                temas.add(new Tema(dataLT.getInt(1), false));
                            }
                        }
                        
                        _l.setAutores(autores);
                        _l.setTemas(temas);
                    }
                    
                    return _l;
                }
            }
            return null;
        } catch (SQLException ex) {    
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Libro> obtenerLibros(){
        List<Libro> _lList = new ArrayList();
        PreparedStatement insertarLibro = DBConection.getStatement("SELECT * FROM Libro;");
        try {
            ResultSet data = insertarLibro.executeQuery();
            if(data != null){
                while (data.next()) {
                    _lList.add(new Libro(data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5), data.getString(6), data.getString(7)));
                }
            }else{
                return null;
            }
            
            return _lList;
        } catch (SQLException ex) {    
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Libro> BuscarLibros(String campo, String buscar){
        List<Libro> _l = new ArrayList();
        PreparedStatement obtenerSQL = DBConection.getStatement("SELECT idLibro FROM libro WHERE " + campo + " LIKE '%" + buscar + "%'");
        
        try {
            ResultSet data = obtenerSQL.executeQuery();
            while(data.next()){
                
                _l.add(new Libro(data.getString("idLibro"), false));
            }
            data.close();
            return _l;
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static boolean insertar(Libro _l){
        PreparedStatement insertarSQL = DBConection.getStatement("INSERT INTO Libro VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
        try {
            String _id = nuevoId();
            _l.setIdLibro(_id);
            insertarSQL.setString(1, _id);
            insertarSQL.setString(2, _l.getTitulo());
            insertarSQL.setString(3, _l.getIsbn());
            insertarSQL.setString(4, _l.getEdicion());
            insertarSQL.setString(5, _l.getDescripcion());
            insertarSQL.setString(6, _l.getNotas());
            insertarSQL.setString(7, _l.getImagen());
            insertarSQL.setString(8, _l.getImprenta().getIdImprenta());
            insertarSQL.setInt(9, _l.getCategoria().getIdCategoria());

            insertarSQL.executeUpdate();

            return insertarAutores(_l) && insertarTemas(_l);
        } catch (SQLException ex) {    
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean modificar(Libro _l){
        try {
            Libro _l2 = obtenerLibro(_l.getIdLibro(), true);
            PreparedStatement modificarSQL = DBConection.getStatement("UPDATE Libro SET titulo = ?, isbn = ?, edicion = ?, descripcion = ?, notas = ?, imagen = ?, idImprenta = ?, idCategoria = ? WHERE idLibro = ?;");
            
            modificarSQL.setString(1, _l.getTitulo());
            modificarSQL.setString(2, _l.getIsbn());
            modificarSQL.setString(3, _l.getEdicion());
            modificarSQL.setString(4, _l.getDescripcion());
            modificarSQL.setString(5, _l.getNotas());
            modificarSQL.setString(6, _l.getImagen());
            modificarSQL.setString(7, _l.getImprenta().getIdImprenta());
            modificarSQL.setInt(8, _l.getCategoria().getIdCategoria());
            modificarSQL.setString(9, _l.getIdLibro());
            modificarSQL.executeUpdate();
            
            if(_l.getAutores() != null && _l2.getAutores() != null){
                if(!_l.getAutores().equals(_l2.getAutores())){
                    evaluarDetalles_Autor(_l.getAutores(), _l2.getAutores(), _l.getIdLibro());
                }
            }else if(_l2.getAutores() == null && _l.getAutores() != null){
                if(!insertarAutores(_l)) {return false;}
            }else if(_l.getAutores() == null){
                PreparedStatement modificar_DELETE = DBConection.getStatement("DELETE FROM Detalle_AutorLibro WHERE idLibro = ?;");
                modificar_DELETE.setString(1, _l.getIdLibro());
                modificar_DELETE.executeUpdate();
            }

            if(_l.getTemas() != null && _l2.getTemas() != null){
                if(!_l.getTemas().equals(_l2.getTemas())){
                    evaluarDetalles_Tema(_l.getTemas(), _l2.getTemas(), _l.getIdLibro());
                }
            }else if(_l2.getTemas() == null && _l.getTemas() != null){
                if(!insertarTemas(_l)) {return false;}
            }else if(_l.getTemas() == null){
                PreparedStatement modificar_DELETE = DBConection.getStatement("DELETE FROM Detalle_LibroTema WHERE idLibro = ?;");
                modificar_DELETE.setString(1, _l.getIdLibro());
                modificar_DELETE.executeUpdate();
            }
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean evaluarDetalles_Autor(List<Autor> _n, List<Autor> _v, String idLibro) throws SQLException{
        PreparedStatement modificar_ADD = DBConection.getStatement("INSERT INTO detalle_autorlibro VALUES (NULL, ?, ?);");
        PreparedStatement modificar_DELETE = DBConection.getStatement("DELETE FROM detalle_autorlibro WHERE idLibro = ? AND idAutor = ?;");
        modificar_ADD.setString(2, idLibro);
        modificar_DELETE.setString(1, idLibro);

        boolean f = true;
        
        for(Autor a1 : _n){
            if (!existeEn(_v, a1.getIdAutor())) {
                try {
                    modificar_ADD.setString(1, a1.getIdAutor());
                    modificar_ADD.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
                    f = false;
                }
            }
        }
        
        for(Autor a1 : _v){
            if (!existeEn(_n, a1.getIdAutor())) {
                try {
                    modificar_DELETE.setString(2, a1.getIdAutor());
                    modificar_DELETE.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
                    f = false;
                }
            }
        }
        
        return f;
    }
    
    public static boolean evaluarDetalles_Tema(List<Tema> _n, List<Tema> _v, String idLibro) throws SQLException{
        PreparedStatement modificar_ADD = DBConection.getStatement("INSERT INTO Detalle_LibroTema VALUES (NULL, ?, ?);");
        PreparedStatement modificar_DELETE = DBConection.getStatement("DELETE FROM Detalle_LibroTema WHERE idLibro = ? AND idTema = ?;");
        modificar_ADD.setString(2, idLibro);
        modificar_DELETE.setString(1, idLibro);

        boolean f = true;
        
        for(Tema a1 : _n){
            if (!existeEn(_v, a1.getIdTema())) {
                try {
                    modificar_ADD.setInt(1, a1.getIdTema());
                    modificar_ADD.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
                    f = false;
                }
            }
        }
        
        for(Tema a1 : _v){
            if (!existeEn(_n, a1.getIdTema())) {
                try {
                    modificar_DELETE.setInt(2, a1.getIdTema());
                    modificar_DELETE.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
                    f = false;
                }
            }
        }
        
        return f;
    }
    
    public static boolean existeEn(List<Autor> _a, String idAutor){
        for (Autor a : _a) {
            if (a.getIdAutor().equals(idAutor)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean existeEn(List<Tema> _t, int idTema){
        for (Tema t : _t) {
            if (t.getIdTema() == idTema) {
                return true;
            }
        }
        return false;
    } 
    
    public static boolean eliminar(Libro _l){
        try {
            PreparedStatement eliminarSQL = DBConection.getStatement("DELETE FROM Libro WHERE idLibro = ?;");
            eliminarSQL.setString(1, _l.getIdLibro());

            eliminarSQL.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean insertarAutores(Libro _l){
        try {
            PreparedStatement insertarAutorLibro = DBConection.getStatement("INSERT INTO detalle_autorlibro VALUES (NULL, ?, ?);");
            insertarAutorLibro.setString(2, _l.getIdLibro());
            if(_l.getAutores() != null){
                if(_l.getAutores().size() > 0){
                    for(Autor _a : _l.getAutores()){
                        insertarAutorLibro.setString(1, _a.getIdAutor());
                        insertarAutorLibro.executeUpdate();
                    }
                }
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean insertarTemas(Libro _l){
        try {
            PreparedStatement insertarTemaLibro = DBConection.getStatement("INSERT INTO detalle_librotema VALUES (NULL, ?, ?);");
            insertarTemaLibro.setString(1, _l.getIdLibro());
            if(_l.getTemas() != null){
                if(_l.getTemas().size() > 0){
                    for(Tema _t : _l.getTemas()){
                        insertarTemaLibro.setInt(2, _t.getIdTema());
                        insertarTemaLibro.executeUpdate();
                    }
                }
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
