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
import sv.edu.udb.libreria.Imprenta;
import sv.edu.udb.libreria.Libro;

/**
 *
 * @author Frank
 */
public class Imprenta_Controller {
    
    public static List<Imprenta> obtenerImprentas(){
        try(Connection _cn = DBConnection.getConnection()){
            try {
                List<Imprenta> _iList = new ArrayList();
                try (ResultSet data = DBConnection.getData("SELECT * FROM Imprenta;", _cn)) {
                    while (data.next()) {
                        _iList.add(new Imprenta(data.getString(1), data.getString(2), data.getString(3)));
                    }
                }

                return _iList;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Imprenta_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static List<Imprenta> obtenerImprentas(String filtros){
        try (Connection _cn = DBConnection.getConnection()) {
            List<Imprenta> _iList = new ArrayList();
            try {
                try (ResultSet data = DBConnection.getData("SELECT * FROM Imprenta " + filtros + ";", _cn)) {
                    while (data.next()) {
                        _iList.add(new Imprenta(data.getString(1), data.getString(2), data.getString(3)));
                    }
                }
                return _iList;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Imprenta_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static boolean verificarI(String nombre){
        try (Connection _cn = DBConnection.getConnection()) {
            try {
                int cuenta;
                try (PreparedStatement query = DBConnection.getStatement("SELECT COUNT(*) FROM Imprenta WHERE LOWER(nombre)=?", _cn)) {
                    query.setString(1, nombre.toLowerCase());
                    try (ResultSet data = query.executeQuery()) {
                        if(data.next()){
                            cuenta = data.getInt(1);
                        }else{
                            cuenta = 1;
                        }
                        
                    }
                }
                return !(cuenta > 0);
            } catch (SQLException ex) {
                Logger.getLogger(Imprenta_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Imprenta_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static List<Imprenta> BuscarImprenta(String campo,String busqueda){
        try (Connection _cn = DBConnection.getConnection()) {
            List<Imprenta> _Ilist = new ArrayList();
            try {
                try (PreparedStatement consultaSQL = DBConnection.getStatement("SELECT idImprenta,nombre,direccion FROM imprenta WHERE " + campo + " LIKE '%" + busqueda + "%'", _cn); ResultSet data = consultaSQL.executeQuery()) {
                    while (data.next()) {
                        _Ilist.add(new Imprenta(data.getString("idImprenta"), data.getString("nombre"), data.getString("direccion")));
                    }
                }
                return _Ilist;
            } catch (SQLException ex) {
                Logger.getLogger(Imprenta_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Imprenta_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }    
    }
    
    public static Imprenta obtenerImprenta(String idImprenta, boolean relaciones){
        try (Connection _cn = DBConnection.getConnection()) {
            try {
                Imprenta _i;
                try (PreparedStatement obtenerImprenta = DBConnection.getStatement("SELECT * FROM Imprenta WHERE idImprenta = ?;", _cn)) {
                    obtenerImprenta.setString(1, idImprenta);
                    try (ResultSet data = obtenerImprenta.executeQuery()) {
                        if (data != null) {
                            if(data.next()){
                                _i = new Imprenta(data.getString(1), data.getString(2), data.getString(3));

                                if (relaciones) {
                                    try (PreparedStatement obtenerCategoriaLibro = DBConnection.getStatement("SELECT l.idLibro FROM Imprenta i INNER JOIN Libro l ON i.idImprenta = l.idImprenta WHERE i.idImprenta = ?;", _cn)) {
                                        obtenerCategoriaLibro.setString(1, idImprenta);

                                        ResultSet dataIL = obtenerCategoriaLibro.executeQuery();

                                        List<Libro> libros = new ArrayList();

                                        if (dataIL != null) {
                                            while (dataIL.next()) {
                                                libros.add(new Libro(dataIL.getString(1), dataIL.getString(2), dataIL.getString(3), dataIL.getString(4), dataIL.getString(5), dataIL.getString(6), dataIL.getString(7)));
                                            }
                                            dataIL.close();
                                        }

                                        _i.setLibros(libros);
                                    }
                                }
                            }else{
                                _i = null;
                            }
                        } else {
                            _i = null;
                        }
                    }
                }
                
                return _i;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Imprenta_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static boolean insertar(Imprenta _i){
        try (Connection _cn = DBConnection.getConnection()) {
            try {
                try (PreparedStatement insertarSQL = DBConnection.getStatement("INSERT INTO Imprenta VALUES(?, ?, ?);", _cn)) {
                    insertarSQL.setString(1, _i.getIdImprenta());
                    insertarSQL.setString(2, _i.getNombre());
                    insertarSQL.setString(3, _i.getDireccion());
                    insertarSQL.executeUpdate();
                }
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Imprenta_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static String generarId(){
        try (Connection _cn = DBConnection.getConnection()) {
            String _id = "";
            int _c;
            try {
                try (ResultSet _r = DBConnection.getData("SELECT MAX(CAST(SUBSTRING(idImprenta,5,4) AS UNSIGNED)) AS newId FROM imprenta", _cn)) {
                    if (_r.next()) {
                        if (_r.getString("newId") != null) {
                            _c = Integer.parseInt(_r.getString("newId")) + 1;

                            if (_c < 10) {
                                _id = "IMTA000" + _c;
                            } else if (_c >= 10 && _c < 100) {
                                _id = "IMTA00" + _c;
                            } else if (_c >= 100 && _c < 1000) {
                                _id = "IMTA0" + _c;
                            } else {
                                _id = "IMTA" + _c;
                            }
                        } else {
                            _id = "IMTA0001";
                        }
                    }else{
                        _id = null;
                    }
                }
                return _id;
            } catch (SQLException ex) {
                Logger.getLogger(Imprenta_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Imprenta_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    public static boolean modificar(Imprenta _i){
        try (Connection _cn = DBConnection.getConnection()) {
            try {
                try (PreparedStatement modificarSQL = DBConnection.getStatement("UPDATE Imprenta SET nombre = ?, direccion = ? WHERE idImprenta = ?;", _cn)) {
                    modificarSQL.setString(1, _i.getNombre());
                    modificarSQL.setString(2, _i.getDireccion());
                    modificarSQL.setString(3, _i.getIdImprenta());
                    modificarSQL.executeUpdate();
                }
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Imprenta_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean verificarRegistros(String idImprenta){
        try (Connection _cn = DBConnection.getConnection()) {
            int cuenta;
            try {
                try (ResultSet _rs = DBConnection.getData("SELECT COUNT(idImprenta) AS cuentaID FROM libro WHERE idImprenta = '" + idImprenta + "';", _cn)) {
                    if (_rs != null) {
                        boolean f;
                        if (_rs.next()) {
                            cuenta = Integer.parseInt(_rs.getString("cuentaID"));
                            f = !(cuenta > 0);
                        } else {
                            f = false;
                        }
                        
                        _rs.close();
                        return f;
                    } else {
                        return false;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Imprenta_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Imprenta_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static boolean eliminar(Imprenta _i){
        try (Connection _cn = DBConnection.getConnection()) {
            try {
                try (PreparedStatement eliminarSQL = DBConnection.getStatement("DELETE FROM Imprenta WHERE idImprenta = ?;", _cn)) {
                    eliminarSQL.setString(1, _i.getIdImprenta());
                    eliminarSQL.executeUpdate();
                }
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Libro_Controller.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Imprenta_Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
