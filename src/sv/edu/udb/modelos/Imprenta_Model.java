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
import javax.swing.JOptionPane;
import sv.edu.udb.connection.DBConection;
import sv.edu.udb.libreria.Imprenta;
import sv.edu.udb.libreria.Libro;

/**
 *
 * @author Frank
 */
public class Imprenta_Model {
    
    public static List<Imprenta> obtenerImprentas(){
        List<Imprenta> _iList = new ArrayList();
        try {
            try (ResultSet data = DBConection.getData("SELECT * FROM Imprenta;")) {
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
    
    public static List<Imprenta> obtenerImprentas(String filtros){
        List<Imprenta> _iList = new ArrayList();
        try {
            try (ResultSet data = DBConection.getData("SELECT * FROM Imprenta " + filtros + ";")) {
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
    public static boolean verificarI(String nombre){
        PreparedStatement query = DBConection.getStatement("SELECT COUNT(*) FROM Imprenta WHERE LOWER(nombre)=?");
        try {
            query.setString(1, nombre.toLowerCase());
            ResultSet data = query.executeQuery();
            data.next();
            int cuenta = data.getInt(1);
            data.close();
            return ((cuenta> 0) ? false : true);
        } catch (SQLException ex) {
            Logger.getLogger(Imprenta_Model.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public static List<Imprenta> BuscarImprenta(String campo,String busqueda){
        List<Imprenta> _Ilist = new ArrayList();
        PreparedStatement consultaSQL = DBConection.getStatement("SELECT idImprenta,nombre,direccion FROM imprenta WHERE "+ campo +" LIKE '%"+busqueda +"%'");
        
        try{
            ResultSet data = consultaSQL.executeQuery();
            while(data.next()){
                _Ilist.add(new Imprenta(data.getString("idImprenta"),data.getString("nombre"),data.getString("direccion")));
            }
            data.close();
            return _Ilist;
        }catch(SQLException ex){
            Logger.getLogger(Imprenta_Model.class.getName()).log(Level.SEVERE,null, ex);
            return null;
        }
    }
    public static Imprenta obtenerImprenta(String idImprenta, boolean relaciones){
        PreparedStatement insertarCategoria = DBConection.getStatement("SELECT * FROM Imprenta WHERE idImprenta = ?;");
        try {
            insertarCategoria.setString(1, idImprenta);
            try (ResultSet data = insertarCategoria.executeQuery()) {
                while(data.next()){
                    Imprenta _i = new Imprenta(data.getString(1), data.getString(2), data.getString(3));
                    
                    if(relaciones){
                        PreparedStatement obtenerCategoriaLibro = DBConection.getStatement("SELECT l.idLibro FROM Imprenta i INNER JOIN Libro l ON i.idImprenta = l.idImprenta WHERE i.idImprenta = ?;");
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
        PreparedStatement insertarSQL = DBConection.getStatement("INSERT INTO Imprenta VALUES(?, ?, ?);");
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
    public static String generarId(){
        String _id;
        int _c;
        try{
            ResultSet _r = DBConection.getData("SELECT MAX(CAST(SUBSTRING(idImprenta,5,4) AS UNSIGNED)) AS newId FROM imprenta");
            _r.next();
            
            if(_r.getString("newId") != null){
                _c = Integer.parseInt(_r.getString("newId")) + 1;

            if(_c < 10){
                _id = "IMTA000" + _c;
            }else if(_c >=10 && _c < 100){
                _id = "IMTA00" + _c;
            }else if(_c >= 100 && _c < 1000){
                _id = "IMTA0" + _c;
            }else{
                _id = "IMTA" + _c;
            }
            }else{
                _id = "IMTA0001";
            }
            _r.close();
            return _id;
        }catch(SQLException ex){
            Logger.getLogger(Imprenta_Model.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }
    }
    public static boolean modificar(Imprenta _i){
        PreparedStatement modificarSQL = DBConection.getStatement("UPDATE Imprenta SET nombre = ?, direccion = ? WHERE idImprenta = ?;");
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
    public static boolean verificarRegistros(String idImprenta){
        int cuenta;
        try{
            ResultSet _rs = DBConection.getData("SELECT COUNT(idImprenta) AS cuentaID FROM libro WHERE idImprenta = '"+ idImprenta +"';");
            if(_rs != null){
                if(_rs.next()){
                    cuenta = Integer.parseInt(_rs.getString("cuentaID"));
                    if (cuenta > 0) {
                        return false;
                    } else {
                        return true;
                    }
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }catch(SQLException ex){
            Logger.getLogger(Imprenta_Model.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
    }
    
    public static boolean eliminar(Imprenta _i){
        PreparedStatement eliminarSQL = DBConection.getStatement("DELETE FROM Imprenta WHERE idImprenta = ?;");
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
