/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.connection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.libreria.Encriptar;

/**
 *
 * @author Frank //pq4ktkXx
 */
public class DBConnection {
    private static final String HOST = "127.0.0.1";
    private static final String BD = "thot";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + BD, USER, PASS);
            return cn;
        } catch (ClassNotFoundException cE) {
            System.out.println("ERROR: Clase no encontrada!: " + cE.getMessage());
            return null;
        } catch (SQLException sE) {
            System.out.println("ERROR: Fallo en SQL: " + sE.getMessage());
            return null;
        }
    }
    
    public static ResultSet getData(String sql, Connection cn){
        try{
            Statement s = cn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            return rs;
        }catch(SQLException sE){
            System.out.println("ERROR: Fallo en SQL: " + sE.getMessage());
            return null;
        }
    }
    
    public static PreparedStatement getStatement(String sql, Connection cn){
        try{
            return cn.prepareStatement(sql);
        }catch(SQLException sE){
            System.out.println("ERROR: Fallo en SQL: " + sE.getMessage());
            return null;
        }
    }
    
    public static CallableStatement getProcedure(String sql, Connection cn){
        try{
            return cn.prepareCall(sql);
        }catch(SQLException sE){
            System.out.println("ERROR: Fallo en SQL: " + sE.getMessage());
            return null;
        }
    }
    
    public static int login(String user, String pass, Connection cn){
        try {
            int r = -2;
            
            try (Connection _d = DBConnection.getConnection()) {
                CallableStatement query = DBConnection.getProcedure("{CALL login(?, ?, ?)}", cn);
                query.setString(1, user);
                query.registerOutParameter(2, java.sql.Types.VARCHAR);
                query.registerOutParameter(3, java.sql.Types.INTEGER);

                query.execute();

                r = pass.equals(Encriptar.desencriptar(query.getString(2))) ? 1 : 0;
                r = query.getInt(3) != -1 ? r : -1;
            }
            return r;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -2;
    }
}
