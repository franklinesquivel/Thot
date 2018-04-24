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
public class DB {
    public static final String HOST = "127.0.0.1";
    public static final String BD = "thot";
    public static final String USER = "root";
    public static final String PASS = "";
    //pq4ktkXx
    private Connection cn;
    private ResultSet rs;
    private Statement s;
    
    public void open(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if(this.cn == null){
                this.cn = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + BD, USER, PASS);
            }else{
                if (this.cn.isClosed()) {
                    this.cn = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + BD, USER, PASS);
                }
            }
        } catch (ClassNotFoundException cE) {
            System.out.println("ERROR: Clase no encontrada!: " + cE.getMessage());
        } catch (SQLException sE) {
            System.out.println("ERROR: Fallo en SQL: " + sE.getMessage());
        }
    }
    
    public void close(){
        try {
            if(this.cn != null){
                if(!this.cn.isClosed()){
                    this.cn.close();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet getData(String sql) {
        try {
            this.open();
            s = cn.createStatement();
            rs = s.executeQuery(sql);
            //s.close();
            return this.rs;
        } catch (SQLException sE) {
            System.out.println("ERROR: Fallo en SQL: " + sE.getMessage());
            return null;
        }
    }

    public PreparedStatement getStatement(String sql) {
        try {
            return cn.prepareStatement(sql);
        } catch (SQLException sE) {
            System.out.println("ERROR: Fallo en SQL: " + sE.getMessage());
            return null;
        }
    }

    public CallableStatement getProcedure(String sql) {
        try {
            return cn.prepareCall(sql);
        } catch (SQLException sE) {
            System.out.println("ERROR: Fallo en SQL: " + sE.getMessage());
            return null;
        }
    }

    public int login(String user, String pass) {
        this.open();
        if(this.isOpen()){
            try {
                int r = -2;

                try (CallableStatement query = this.getProcedure("{CALL login(?, ?, ?)}")) {
                    query.setString(1, user);
                    query.registerOutParameter(2, java.sql.Types.VARCHAR);
                    query.registerOutParameter(3, java.sql.Types.INTEGER);

                    query.execute();
                    r = pass.equals(Encriptar.desencriptar(query.getString(2))) ? 1 : 0;
                    r = query.getInt(3) != -1 ? r : -1;
                }
                
                this.close();
                return r;
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            this.close();
            return -2;
        }else{
            this.close();
            return -2;
        }
    }
    
    public boolean isOpen(){
        try {
            return !this.cn.isClosed();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public DB() {
        this.cn = null;
        this.rs = null;
        this.s = null;
    }
}
