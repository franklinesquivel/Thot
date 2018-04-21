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
 * @author Frank
 */
public class DBConection {
    private static final String HOST = "127.0.0.1";
    private static final String BD = "thot";
    private static final String USER = "root";
    private static final String PASS = "";
    private static Connection cn;
    private static ResultSet rs;
    private static Statement s;
    
    public static ResultSet getData(String sql){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + BD, USER, PASS);
            s = cn.createStatement();
            rs = s.executeQuery(sql);
            return DBConection.rs;
        }catch(ClassNotFoundException cE){
            System.out.println("ERROR: Clase no encontrada!: " + cE.getMessage());
            return null;
        }catch(SQLException sE){
            System.out.println("ERROR: Fallo en SQL: " + sE.getMessage());
            return null;
        }
    }
    
    public static PreparedStatement getStatement(String sql){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + BD, USER, PASS);
            return cn.prepareStatement(sql);
        }catch(ClassNotFoundException cE){
            System.out.println("ERROR: Clase no encontrada!: " + cE.getMessage());
            return null;
        }catch(SQLException sE){
            System.out.println("ERROR: Fallo en SQL: " + sE.getMessage());
            return null;
        }
    }
    
    public static CallableStatement getProcedure(String sql){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + BD, USER, PASS);
            return cn.prepareCall(sql);
        }catch(ClassNotFoundException cE){
            System.out.println("ERROR: Clase no encontrada!: " + cE.getMessage());
            return null;
        }catch(SQLException sE){
            System.out.println("ERROR: Fallo en SQL: " + sE.getMessage());
            return null;
        }
    }
    
    public static int login(String user, String pass){
        try {
            int r = -2;
            
            CallableStatement query = DBConection.getProcedure("{CALL login(?, ?, ?)}");
            query.setString(1, user);
            query.registerOutParameter(2, java.sql.Types.VARCHAR);
            query.registerOutParameter(3, java.sql.Types.INTEGER);
            
            query.execute();
            
            r = pass.equals(Encriptar.desencriptar(query.getString(2))) ? 1 : 0;
            r = query.getInt(3) != -1 ? r : -1;
            
            return r;
        } catch (SQLException ex) {
            Logger.getLogger(DBConection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -2;
    }
}
