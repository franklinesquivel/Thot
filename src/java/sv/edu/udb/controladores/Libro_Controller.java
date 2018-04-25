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
import sv.edu.udb.libreria.Autor;
import sv.edu.udb.libreria.Categoria;
import sv.edu.udb.libreria.Ejemplar;
import sv.edu.udb.libreria.Imprenta;
import sv.edu.udb.libreria.Libro;
import sv.edu.udb.libreria.Tema;

/**
 *
 * @author Frank
 */
public class Libro_Controller {
    
    public static String nuevoId(){
        String _id;
        int _c;
        
        try(Connection _cn = DBConnection.getConnection()){
            try {
                try (ResultSet _r = DBConnection.getData("SELECT MAX(CAST(SUBSTRING(idLibro, 3, 5) AS UNSIGNED)) AS newId FROM Libro;", _cn)) {
                    if (_r != null) {
                        if (_r.next()) {
                            if (_r.getString("newId") != null) {
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
                            } else {
                                _id = "L0001";
                            }
                        } else {
                            _id = null;
                        }
                    } else {
                        _id = null;
                    }
                }
                return _id;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static boolean validarIsbn(String _isbn){
        try(Connection _cn = DBConnection.getConnection()){
            boolean f;
            try {
                ResultSet _r = DBConnection.getData("SELECT COUNT(*) AS c FROM Libro WHERE isbn = '" + _isbn + "';", _cn);
                if (_r != null) {
                    if (_r.next()) {
                        f = Integer.parseInt(_r.getString("c")) > 0;
                    } else {
                        f = false;
                    }
                } else {
                    f = false;
                }

                return f;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    
    public static Libro obtenerLibro(String id, boolean relaciones){
        try(Connection _cn = DBConnection.getConnection()){
            try {
                Libro _l;
                try (PreparedStatement obtenerLibro = DBConnection.getStatement("SELECT * FROM Libro WHERE idLibro = ?;", _cn)) {
                    obtenerLibro.setString(1, id);
                    try (ResultSet dataLibro = obtenerLibro.executeQuery()) {
                        if (dataLibro.next()) {
                            _l = new Libro(
                                    dataLibro.getString(1), //idLibro
                                    dataLibro.getString(2), //Título
                                    dataLibro.getString(3), //ISBN
                                    dataLibro.getString(4), //Edición
                                    dataLibro.getString(5), //Descripción
                                    dataLibro.getString(6), //Notas
                                    dataLibro.getString(7), //Imagen
                                    dataLibro.getInt(8), //Ejemplares
                                    new Imprenta(dataLibro.getString(9), false),
                                    new Categoria(dataLibro.getInt(10), false)
                            );

                            if (relaciones) {
                                PreparedStatement obtenerLibroAutor = DBConnection.getStatement("SELECT dAL.idAutor FROM Libro l INNER JOIN Detalle_AutorLibro dAL ON l.idLibro = dAL.idLibro WHERE l.idLibro = ?;", _cn);
                                PreparedStatement obtenerLibroTema = DBConnection.getStatement("SELECT dLT.idTema FROM Libro l INNER JOIN Detalle_LibroTema dLT ON l.idLibro = dLT.idLibro WHERE l.idLibro = ?;", _cn);
                                PreparedStatement obtenerLibroEjemplares = DBConnection.getStatement("SELECT e.idEjemplar FROM Ejemplar e WHERE e.idLibro = ?;",_cn);

                                obtenerLibroAutor.setString(1, id);
                                obtenerLibroTema.setString(1, id);
                                obtenerLibroEjemplares.setString(1, id);

                                ResultSet dataAL = obtenerLibroAutor.executeQuery();
                                ResultSet dataLT = obtenerLibroTema.executeQuery();
                                ResultSet dataLE = obtenerLibroEjemplares.executeQuery();

                                List<Autor> autores = new ArrayList();
                                List<Tema> temas = new ArrayList();
                                List<Ejemplar> ejemplares = new ArrayList();

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

                                if (dataLE != null) {
                                    while (dataLE.next()) {
                                        ejemplares.add(new Ejemplar(dataLE.getString(1), false));
                                    }
                                }

                                _l.setAutores(autores);
                                _l.setTemas(temas);
                                _l.setEjemplares(ejemplares);

                                if (dataAL != null) {
                                    dataAL.close();
                                }
                                if (dataLT != null) {
                                    dataLT.close();
                                }
                                if (dataLE != null) {
                                    dataLE.close();
                                }

                                obtenerLibroAutor.close();
                                obtenerLibroTema.close();
                                obtenerLibroEjemplares.close();
                            }
                        } else {
                            _l = null;
                        }
                    }
                }

                return _l;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Libro> obtenerLibros(){
        try(Connection _cn = DBConnection.getConnection()){
            List<Libro> _lList = new ArrayList();

            try {
                try (PreparedStatement obtenerLibros = DBConnection.getStatement("SELECT * FROM Libro;", _cn); ResultSet data = obtenerLibros.executeQuery()) {
                    if (data != null) {
                        while (data.next()) {
                            _lList.add(
                                new Libro(
                                    data.getString(1),
                                    data.getString(2),
                                    data.getString(3),
                                    data.getString(4),
                                    data.getString(5),
                                    data.getString(6),
                                    data.getString(7),
                                    data.getInt(8)
                                )
                            );
                        }
                    } else {
                        data.close();
                        obtenerLibros.close();
                        return null;
                    }
                }

                return _lList;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Libro> obtenerLibros(boolean relaciones){
        try(Connection _cn = DBConnection.getConnection()){
            List<Libro> _lList = new ArrayList();

            try {
                try (PreparedStatement obtenerLibros = DBConnection.getStatement("SELECT * FROM Libro;", _cn); ResultSet data = obtenerLibros.executeQuery()) {
                    if (data != null) {
                        while (data.next()) {
                            PreparedStatement obtenerLibroAutor = DBConnection.getStatement("SELECT dAL.idAutor FROM Libro l INNER JOIN Detalle_AutorLibro dAL ON l.idLibro = dAL.idLibro WHERE l.idLibro = ?;", _cn);
                            PreparedStatement obtenerLibroTema = DBConnection.getStatement("SELECT dLT.idTema FROM Libro l INNER JOIN Detalle_LibroTema dLT ON l.idLibro = dLT.idLibro WHERE l.idLibro = ?;", _cn);
                            obtenerLibroAutor.setString(1, data.getString(1));
                            obtenerLibroTema.setString(1, data.getString(1));

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

                            if (dataAL != null) {
                                dataAL.close();
                            }
                            if (dataLT != null) {
                                dataLT.close();
                            }


                            obtenerLibroAutor.close();
                            obtenerLibroTema.close();
                            _lList.add(
                                new Libro(
                                    data.getString(1), //idLibro
                                    data.getString(2), //Titulo
                                    data.getString(3), //ISBN
                                    data.getString(4), //Edición
                                    data.getString(5), //Descripción
                                    data.getString(6), //Notas
                                    data.getString(7), //Imagen
                                    new Imprenta(data.getString(9), false), //Imprenta
                                    new Categoria(data.getInt(10), false), //Categoria
                                    autores,//Autores
                                    temas//Temas
                                )
                            );
                        }
                    } else {
                        data.close();
                        obtenerLibros.close();
                        return null;
                    }
                }

                return _lList;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Libro> BuscarLibros(String campo, String buscar){
        try(Connection _cn = DBConnection.getConnection()){
            List<Libro> _l = new ArrayList();

            try {
                try (PreparedStatement obtenerSQL = DBConnection.getStatement("SELECT idLibro FROM libro WHERE " + campo + " LIKE '%" + buscar + "%';", _cn); ResultSet data = obtenerSQL.executeQuery()) {
                    while (data.next()) {
                        _l.add(new Libro(data.getString("idLibro"), false));
                    }
                }
                return _l;
            } catch (SQLException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static boolean insertar(Libro _l){
        try(Connection _cn = DBConnection.getConnection()){
            try {
                String _id = nuevoId();
                try (PreparedStatement insertarSQL = DBConnection.getStatement("INSERT INTO Libro VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", _cn)) {
                    _l.setIdLibro(_id);
                    insertarSQL.setString(1, _id);
                    insertarSQL.setString(2, _l.getTitulo());
                    insertarSQL.setString(3, _l.getIsbn());
                    insertarSQL.setString(4, _l.getEdicion());
                    insertarSQL.setString(5, _l.getDescripcion());
                    insertarSQL.setString(6, _l.getNotas());
                    insertarSQL.setString(7, _l.getImagen());
                    insertarSQL.setInt(8, _l.getCant_ejemplares());
                    insertarSQL.setString(9, _l.getImprenta().getIdImprenta());
                    insertarSQL.setInt(10, _l.getCategoria().getIdCategoria());
                    insertarSQL.executeUpdate();
                }
                return insertarAutores(_l) && insertarTemas(_l);
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean modificar(Libro _l){
        try(Connection _cn = DBConnection.getConnection()){
            try {
                Libro _l2 = obtenerLibro(_l.getIdLibro(), true);
                try (PreparedStatement modificarSQL = DBConnection.getStatement("UPDATE Libro SET titulo = ?, isbn = ?, edicion = ?, descripcion = ?, notas = ?, imagen = ?, ejemplares = ?, idImprenta = ?, idCategoria = ? WHERE idLibro = ?;", _cn)) {
                    modificarSQL.setString(1, _l.getTitulo());
                    modificarSQL.setString(2, _l.getIsbn());
                    modificarSQL.setString(3, _l.getEdicion());
                    modificarSQL.setString(4, _l.getDescripcion());
                    modificarSQL.setString(5, _l.getNotas());
                    modificarSQL.setString(6, _l.getImagen());
                    modificarSQL.setInt(7, _l.getCant_ejemplares());
                    modificarSQL.setString(8, _l.getImprenta().getIdImprenta());
                    modificarSQL.setInt(9, _l.getCategoria().getIdCategoria());
                    modificarSQL.setString(10, _l.getIdLibro());
                    modificarSQL.executeUpdate();
                }

                if (_l.getAutores() != null && _l2.getAutores() != null) {
                    if (!_l.getAutores().equals(_l2.getAutores())) {
                        evaluarDetalles_Autor(_l.getAutores(), _l2.getAutores(), _l.getIdLibro());
                    }
                } else if (_l2.getAutores() == null && _l.getAutores() != null) {
                    if (!insertarAutores(_l)) {
                        return false;
                    }
                } else if (_l.getAutores() == null) {
                    try (PreparedStatement modificar_DELETE = DBConnection.getStatement("DELETE FROM Detalle_AutorLibro WHERE idLibro = ?;", _cn)) {
                        modificar_DELETE.setString(1, _l.getIdLibro());
                        modificar_DELETE.executeUpdate();
                    }
                }

                if (_l.getTemas() != null && _l2.getTemas() != null) {
                    if (!_l.getTemas().equals(_l2.getTemas())) {
                        evaluarDetalles_Tema(_l.getTemas(), _l2.getTemas(), _l.getIdLibro());
                    }
                } else if (_l2.getTemas() == null && _l.getTemas() != null) {
                    if (!insertarTemas(_l)) {
                        return false;
                    }
                } else if (_l.getTemas() == null) {
                    try (PreparedStatement modificar_DELETE = DBConnection.getStatement("DELETE FROM Detalle_LibroTema WHERE idLibro = ?;", _cn)) {
                        modificar_DELETE.setString(1, _l.getIdLibro());
                        modificar_DELETE.executeUpdate();
                    }
                }

                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public static boolean evaluarDetalles_Autor(List<Autor> _n, List<Autor> _v, String idLibro){
        try(Connection _cn = DBConnection.getConnection()){
            boolean f = true;

            for (Autor a1 : _n) {
                if (!existeEn(_v, a1.getIdAutor())) {
                    try {
                        try (PreparedStatement modificar_ADD = DBConnection.getStatement("INSERT INTO detalle_autorlibro VALUES (NULL, ?, ?);,", _cn)) {
                            modificar_ADD.setString(1, a1.getIdAutor());
                            modificar_ADD.setString(2, idLibro);
                            modificar_ADD.executeUpdate();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                        f = false;
                    }
                }
            }

            for (Autor a1 : _v) {
                if (!existeEn(_n, a1.getIdAutor())) {
                    try {
                        try (PreparedStatement modificar_DELETE = DBConnection.getStatement("DELETE FROM detalle_autorlibro WHERE idLibro = ? AND idAutor = ?;", _cn)) {
                            modificar_DELETE.setString(1, idLibro);
                            modificar_DELETE.setString(2, a1.getIdAutor());
                            modificar_DELETE.executeUpdate();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                        f = false;
                    }
                }
            }

            return f;
        } catch (SQLException ex) {
            Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean evaluarDetalles_Tema(List<Tema> _n, List<Tema> _v, String idLibro){
        try(Connection _cn = DBConnection.getConnection()){
            boolean f = true;

            for (Tema a1 : _n) {
                if (!existeEn(_v, a1.getIdTema())) {
                    try {
                        try (PreparedStatement modificar_ADD = DBConnection.getStatement("INSERT INTO Detalle_LibroTema VALUES (NULL, ?, ?);", _cn)) {
                            modificar_ADD.setInt(1, a1.getIdTema());
                            modificar_ADD.setString(2, idLibro);
                            modificar_ADD.executeUpdate();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                        f = false;
                    }
                }
            }

            for (Tema a1 : _v) {
                if (!existeEn(_n, a1.getIdTema())) {
                    try {
                        try (PreparedStatement modificar_DELETE = DBConnection.getStatement("DELETE FROM Detalle_LibroTema WHERE idLibro = ? AND idTema = ?;", _cn)) {
                            modificar_DELETE.setString(1, idLibro);
                            modificar_DELETE.setInt(2, a1.getIdTema());
                            modificar_DELETE.executeUpdate();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                        f = false;
                    }
                }
            }

            return f;
        } catch (SQLException ex) {
            Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean existeEn(List<Autor> _a, String idAutor){
        return _a.stream().anyMatch((a) -> (a.getIdAutor().equals(idAutor)));
    }
    
    public static boolean existeEn(List<Tema> _t, int idTema){
        return _t.stream().anyMatch((t) -> (t.getIdTema() == idTema));
    } 
    
    public static boolean eliminar(Libro _l){
        try(Connection _cn = DBConnection.getConnection()){
            try {
                try (PreparedStatement eliminarSQL = DBConnection.getStatement("DELETE FROM Libro WHERE idLibro = ?;", _cn)) {
                    eliminarSQL.setString(1, _l.getIdLibro());
                    eliminarSQL.executeUpdate();
                }
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    
    public static boolean insertarAutores(Libro _l){
        try(Connection _cn = DBConnection.getConnection()){
            try {
                if (_l.getAutores() != null) {
                    if (_l.getAutores().size() > 0) {
                        for (Autor _a : _l.getAutores()) {
                            try (PreparedStatement insertarAutorLibro = DBConnection.getStatement("INSERT INTO detalle_autorlibro VALUES (NULL, ?, ?);", _cn)) {
                                insertarAutorLibro.setString(1, _a.getIdAutor());
                                insertarAutorLibro.setString(2, _l.getIdLibro());
                                insertarAutorLibro.executeUpdate();
                            }
                        }
                    }
                }
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean insertarTemas(Libro _l){
        try(Connection _cn = DBConnection.getConnection()){
            try {
                if (_l.getTemas() != null) {
                    if (_l.getTemas().size() > 0) {
                        for (Tema _t : _l.getTemas()) {
                            try (PreparedStatement insertarTemaLibro = DBConnection.getStatement("INSERT INTO detalle_librotema VALUES (NULL, ?, ?);", _cn)) {
                                insertarTemaLibro.setString(1, _l.getIdLibro());
                                insertarTemaLibro.setInt(2, _t.getIdTema());
                                insertarTemaLibro.executeUpdate();
                            }
                        }
                    }
                }
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
