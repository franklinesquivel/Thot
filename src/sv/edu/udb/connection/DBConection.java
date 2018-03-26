/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Frank
 */
public class DBConection {
    private static final String BD = "thot";
    private static final String USER = "root";
    private static final String PASS = "";
    private static Connection cn;
    private static ResultSet rs;
    private static Statement s;
    
    public static ResultSet getData(String sql){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost/" + BD, USER, PASS);
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
            cn = DriverManager.getConnection("jdbc:mysql://localhost/" + BD, USER, PASS);
            return cn.prepareStatement(sql);
        }catch(ClassNotFoundException cE){
            System.out.println("ERROR: Clase no encontrada!: " + cE.getMessage());
            return null;
        }catch(SQLException sE){
            System.out.println("ERROR: Fallo en SQL: " + sE.getMessage());
            return null;
        }
    }
}
