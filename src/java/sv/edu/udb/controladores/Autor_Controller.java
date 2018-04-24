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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.connection.DBConnection;
import sv.edu.udb.libreria.Autor;
import sv.edu.udb.libreria.Libro;

/**
 *
 * @author Frank
 */
public class Autor_Controller {
    
    public static List<Autor> obtenerAutores() {
        try(Connection _cn = DBConnection.getConnection()){
            List<Autor> _aList = new ArrayList();
            try {
                try (ResultSet data = DBConnection.getData("SELECT * FROM Autor;", _cn)) {
                    while (data.next()) {
                        Date date = null;
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

                        try {
                            date = df.parse(data.getDate(4).toString());
                        } catch (ParseException ex) {
                            Logger.getLogger(Autor_Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        _aList.add(new Autor(data.getString(1), data.getString(2), data.getString(3), date, data.getString(5)));
                    }
                }
                return _aList;
            } catch (SQLException ex) {
                Logger.getLogger(Autor_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Autor_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Autor> obtenerAutores(String filtros) {
        try(Connection _cn = DBConnection.getConnection()){
            List<Autor> _aList = new ArrayList();
            try {
                try (ResultSet data = DBConnection.getData("SELECT * FROM Autor " + filtros + " ;", _cn)) {
                    while (data.next()) {
                        Date date = null;
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

                        try {
                            date = df.parse(data.getString(4));
                        } catch (ParseException ex) {
                            Logger.getLogger(Autor_Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        _aList.add(new Autor(data.getString(1), data.getString(2), data.getString(3), date, data.getString(5)));
                    }
                }
                return _aList;
            } catch (SQLException ex) {
                Logger.getLogger(Autor_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Autor_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static boolean verificarA(String nombre,String apellido) {
        try(Connection _cn = DBConnection.getConnection()){
            try {
                int cuenta;
                try (PreparedStatement query = DBConnection.getStatement("SELECT COUNT(*) FROM Autor WHERE LOWER(nombres) = ? AND LOWER(apellidos) = ? ;", _cn)) {
                    query.setString(1, nombre.toLowerCase());
                    query.setString(2, apellido.toLowerCase());

                    try (ResultSet data = query.executeQuery()) {
                        if (data != null) {
                            if (data.next()) {
                                cuenta = data.getInt(1);
                            } else {
                                cuenta = 1;
                            }
                        } else {
                            cuenta = 1;
                        }
                    }
                }
                return !(cuenta > 0);
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Autor_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public static Autor obtenerAutor(String idAutor, boolean relaciones) {
        try(Connection _cn = DBConnection.getConnection()){
            try {
                Autor _a;
                try (PreparedStatement obtenerAutor = DBConnection.getStatement("SELECT * FROM autor WHERE idAutor = ?;", _cn)) {
                    obtenerAutor.setString(1, idAutor);
                    try (ResultSet data = obtenerAutor.executeQuery()) {
                        if (data == null) {
                            _a = null;
                        } else {
                            if (data.next()) {
                                _a = new Autor(data.getString(1), data.getString(2), data.getString(3), data.getDate(4));

                                if (relaciones) {
                                    List<Libro> libros;
                                    try (PreparedStatement obtenerCategoriaLibro = DBConnection.getStatement("SELECT dAL.idLibro FROM Autor a INNER JOIN Detalle_LibroTema dAL ON a.idAutor = dAL.idAutor WHERE a.idAutor = ?;", _cn)) {
                                        obtenerCategoriaLibro.setString(1, idAutor);

                                        ResultSet dataAL = obtenerCategoriaLibro.executeQuery();
                                        libros = new ArrayList();

                                        if (dataAL != null) {
                                            while (dataAL.next()) {
                                                libros.add(new Libro(dataAL.getString(1), dataAL.getString(2), dataAL.getString(3), dataAL.getString(4), dataAL.getString(5), dataAL.getString(6), dataAL.getString(7)));
                                            }
                                            dataAL.close();
                                        }
                                    }

                                    _a.setLibros(libros);
                                }
                            } else {
                                _a = null;
                            }
                        }
                    }
                }
                return _a;
            } catch (SQLException ex) {
                Logger.getLogger(Autor_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Autor_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static boolean insertar(Autor _a) {
        try(Connection _cn = DBConnection.getConnection()){
            try {
                try (PreparedStatement insertarSQL = DBConnection.getStatement("INSERT INTO Autor VALUES(?, ?, ?, ?, ?);", _cn)) {
                    insertarSQL.setString(1, _a.getIdAutor());
                    insertarSQL.setString(2, _a.getNombres());
                    insertarSQL.setString(3, _a.getApellidos());
                    insertarSQL.setDate(4, new java.sql.Date(_a.getFechaNac().getTime()));
                    insertarSQL.setString(5, _a.getPais());
                    insertarSQL.executeUpdate();
                }
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Autor_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Autor_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean modificar(Autor _a) {
        try(Connection _cn = DBConnection.getConnection()){
            try {
                try (PreparedStatement modificarSQL = DBConnection.getStatement("UPDATE Autor SET nombres = ?, apellidos = ?, fechaNac = ?, idPais = ? WHERE idAutor = ?;", _cn)) {
                    modificarSQL.setString(1, _a.getNombres());
                    modificarSQL.setString(2, _a.getApellidos());
                    modificarSQL.setString(3, new java.sql.Date(_a.getFechaNac().getTime()).toString());
                    modificarSQL.setString(4, _a.getPais());
                    modificarSQL.setString(5, _a.getIdAutor());
                    modificarSQL.executeUpdate();
                }
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Autor_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean eliminar(Autor _a) {
        try(Connection _cn = DBConnection.getConnection()){
            try {
                try (PreparedStatement eliminarSQL = DBConnection.getStatement("DELETE FROM Autor WHERE idAutor = ?;", _cn)) {
                    eliminarSQL.setString(1, _a.getIdAutor());
                    eliminarSQL.executeUpdate();
                }
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Autor_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static int obtenerNumAutor() {
        try(Connection _cn = DBConnection.getConnection()){
            try {
                int num;
                try (PreparedStatement query = DBConnection.getStatement("SELECT COUNT(*) FROM autor", _cn); ResultSet data = query.executeQuery()) {
                    if (data != null) {
                        if (data.next()) {
                            num = data.getInt(1);
                        } else {
                            num = -1;
                        }
                    } else {
                        num = -1;
                    }
                }
                return num;
            } catch (SQLException ex) {
                Logger.getLogger(Usuario_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Autor_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
}