/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.modelos;

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
import sv.edu.udb.connection.DBConection;
import sv.edu.udb.libreria.Autor;
import sv.edu.udb.libreria.Libro;

/**
 *
 * @author Frank
 */
public class Autor_Model {

    public static List<Autor> obtenerAutores() {
        List<Autor> _aList = new ArrayList();
        try {
            try (ResultSet data = DBConection.getData("SELECT * FROM Autor;")) {
                while (data.next()) {
                    Date date = null;
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

                    try {
                        date = df.parse(data.getDate(4).toString());
                    } catch (ParseException ex) {
                        Logger.getLogger(Autor_Model.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    _aList.add(new Autor(data.getString(1), data.getString(2), data.getString(3), date, data.getString(5)));
                }
            }
            return _aList;
        } catch (SQLException ex) {
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Autor> obtenerAutores(String filtros) {
        List<Autor> _aList = new ArrayList();
        try {
            try (ResultSet data = DBConection.getData("SELECT * FROM Autor " + filtros + " ;")) {
                while (data.next()) {
                    Date date = null;
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

                    try {
                        date = df.parse(data.getString(4));
                    } catch (ParseException ex) {
                        Logger.getLogger(Autor_Model.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    _aList.add(new Autor(data.getString(1), data.getString(2), data.getString(3), date, data.getString(5)));
                }
            }
            return _aList;
        } catch (SQLException ex) {
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static boolean verificarA(String nombre,String apellido) {
        PreparedStatement query = DBConection.getStatement("SELECT COUNT(*) FROM Autor WHERE LOWER(nombres) = ? AND LOWER(apellidos) = ? ;");
        try {
            query.setString(1, nombre.toLowerCase());
            query.setString(2, apellido.toLowerCase());
            ResultSet data = query.executeQuery();
            data.next();
            int cuenta = data.getInt(1);
            data.close();
            return ((cuenta > 0) ? false : true);
        }catch (SQLException ex) {
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public static Autor obtenerAutor(String idAutor, boolean relaciones) {
        PreparedStatement obtenerAutor = DBConection.getStatement("SELECT * FROM autor WHERE idAutor = ?;");
        try {
            obtenerAutor.setString(1, idAutor);
            try (ResultSet data = obtenerAutor.executeQuery()) {
                while (data.next()) {
                    Autor _a = new Autor(data.getString(1), data.getString(2), data.getString(3), data.getDate(4));

                    if (relaciones) {
                        PreparedStatement obtenerCategoriaLibro = DBConection.getStatement("SELECT dAL.idLibro FROM Autor a INNER JOIN Detalle_LibroTema dAL ON a.idAutor = dAL.idAutor WHERE a.idAutor = ?;");
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
        PreparedStatement insertarSQL = DBConection.getStatement("INSERT INTO Autor VALUES(?, ?, ?, ?, ?);");
        try {
            insertarSQL.setString(1, _a.getIdAutor());
            insertarSQL.setString(2, _a.getNombres());
            insertarSQL.setString(3, _a.getApellidos());
            insertarSQL.setDate(4, new java.sql.Date(_a.getFechaNac().getTime()));
            insertarSQL.setString(5, _a.getPais());
            insertarSQL.executeUpdate();
            return true; //wii te ganaste un beso gracias, una sandía mejor :p Oral exddd o mejor un beso con brillo de sandia <3
        } catch (SQLException ex) {
            Logger.getLogger(Autor_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean modificar(Autor _a) {
        PreparedStatement modificarSQL = DBConection.getStatement("UPDATE Autor SET nombres = ?, apellidos = ?, fechaNac = ?, idPais = ? WHERE idAutor = ?;");
        try {

            modificarSQL.setString(1, _a.getNombres());
            modificarSQL.setString(2, _a.getApellidos());
            modificarSQL.setString(3, new java.sql.Date(_a.getFechaNac().getTime()).toString());
            modificarSQL.setString(4, _a.getPais());
            modificarSQL.setString(5, _a.getIdAutor());
            modificarSQL.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean eliminar(Autor _a) {
        PreparedStatement eliminarSQL = DBConection.getStatement("DELETE FROM Autor WHERE idAutor = ?;");
        try {
            eliminarSQL.setString(1, _a.getIdAutor());
            eliminarSQL.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Libro_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static int obtenerNumAutor() { //Obtiene el numero de usuarios registrados según el tipo
        PreparedStatement query = DBConection.getStatement("SELECT COUNT(*) FROM autor");
        try {
            ResultSet data = query.executeQuery();
            data.next();
            int num = data.getInt(1);
            data.close();
            return num;
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_Model.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
}
