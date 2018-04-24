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
import sv.edu.udb.libreria.Libro;
import sv.edu.udb.libreria.Tema;

/**
 *
 * @author Frank
 */
public class Tema_Controller {
    
    public static List<Tema> obtenerTemas(){
        try(Connection _cn = DBConnection.getConnection()){
            List<Tema> _tList = new ArrayList();
            try {
                try (ResultSet data = DBConnection.getData("SELECT * FROM Tema;", _cn)) {
                    while (data.next()) {
                        _tList.add(new Tema(data.getInt(1), data.getString(2)));
                    }
                }
                return _tList;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tema_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Tema> obtenerTemas(String filtros){
        try(Connection _cn = DBConnection.getConnection()){
            List<Tema> _tList = new ArrayList();
            try {
                try (ResultSet data = DBConnection.getData("SELECT * FROM Tema " + filtros + ";", _cn)) {
                    while (data.next()) {
                        _tList.add(new Tema(data.getInt(1), data.getString(2)));
                    }
                }
                return _tList;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tema_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Tema> buscarTemas(String cadena){
        try (Connection _cn = DBConnection.getConnection()) {
            List<Tema> _t = new ArrayList();

            try {
                try (PreparedStatement consultaSQL = DBConnection.getStatement("SELECT idTema,descripcion FROM tema WHERE descripcion LIKE '%" + cadena + "%'", _cn); ResultSet data = consultaSQL.executeQuery()) {
                    while (data.next()) {
                        _t.add(new Tema(Integer.parseInt(data.getString("idTema")), data.getString("descripcion")));
                    }
                }

                return _t;
            } catch (SQLException ex) {
                Logger.getLogger(Tema_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tema_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static Tema obtenerTema(int idTema, boolean relaciones){
        try (Connection _cn = DBConnection.getConnection()) {
            try {
                Tema _t;
                try (PreparedStatement obtenerTema = DBConnection.getStatement("SELECT * FROM tema WHERE idTema = ?;", _cn)) {
                    obtenerTema.setInt(1, idTema);
                    try (ResultSet data = obtenerTema.executeQuery()) {
                        if (data.next()) {
                            _t = new Tema(Integer.parseInt(data.getString(1)), data.getString(2));

                            if (relaciones) {
                                try (PreparedStatement obtenerTemaLibro = DBConnection.getStatement("SELECT dLT.idLibro FROM Tema t INNER JOIN Detalle_LibroTema dLT ON t.idTema = dLT.idTema WHERE t.idTema = ?;", _cn)) {
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
                        } else {
                            _t = null;
                        }
                    }
                }

                return _t;
            } catch (SQLException ex) {
                Logger.getLogger(Tema_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tema_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static boolean insertar(Tema _t){
        try (Connection _cn = DBConnection.getConnection()) {
            try {
                try (PreparedStatement insertarSQL = DBConnection.getStatement("INSERT INTO Tema(descripcion) VALUES(?);", _cn)) {
                    insertarSQL.setString(1, _t.getDescripcion());
                    insertarSQL.executeUpdate();
                }

                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Tema_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tema_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean modificar(Tema _t){
        try (Connection _cn = DBConnection.getConnection()) {
            try {
                try (PreparedStatement modificarSQL = DBConnection.getStatement("UPDATE Tema SET descripcion = ? WHERE idTema = ?;", _cn)) {
                    modificarSQL.setString(1, _t.getDescripcion());
                    modificarSQL.setInt(2, _t.getIdTema());
                    modificarSQL.executeUpdate();
                }
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Tema_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tema_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean verificarT(String descripcion){
        try (Connection _cn = DBConnection.getConnection()) {
            try {
                int cuenta;
                try (PreparedStatement query = DBConnection.getStatement("SELECT COUNT(*) FROM Tema WHERE LOWER(descripcion)=?", _cn)) {
                    query.setString(1, descripcion.toLowerCase());
                    try (ResultSet data = query.executeQuery()) {
                        if (data.next()) {
                            cuenta = data.getInt(1);
                        } else {
                            cuenta = 1;
                        }

                    }
                }

                return !(cuenta > 0);
            } catch (SQLException ex) {
                Logger.getLogger(Tema_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tema_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean eliminar(Tema _t){
        try (Connection _cn = DBConnection.getConnection()) {
            try {
                try (PreparedStatement eliminarSQL = DBConnection.getStatement("DELETE FROM Tema WHERE idTema = ?;", _cn)) {
                    eliminarSQL.setInt(1, _t.getIdTema());
                    eliminarSQL.executeUpdate();
                }
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Tema_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tema_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
