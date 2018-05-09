/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sv.edu.udb.connection.DBConnection;
import sv.edu.udb.controladores.Imprenta_Controller;
import sv.edu.udb.libreria.Historial;

/**
 *
 * @author Frank
 */
@WebServlet(name = "Historial", urlPatterns = {"/Historial"})
public class HistorialProceso extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String res;
        List<Historial> _h = new ArrayList();
        Gson gson = new Gson();
        
        Locale _l = Locale.getDefault(); //Assign the correct language either by page or user-selected or browser language etc.
        ResourceBundle RB = ResourceBundle.getBundle("AplicationResource", new Locale(request.getLocale().getLanguage()));
        
        if(request.getParameter("idUsuario") != null && request.getParameter("tipoProceso") != null){
            try (Connection _cn = DBConnection.getConnection()) {
                try {
                    try (PreparedStatement query = DBConnection.getStatement("SELECT * FROM historial WHERE idUsuario = ? && tipoProceso = ?;", _cn)) {
                        query.setString(1, request.getParameter("idUsuario"));
                        query.setString(2, request.getParameter("tipoProceso"));
                        try (ResultSet data = query.executeQuery()) {
                            while(data.next()){
                                _h.add(new Historial(data.getString(1), data.getString(2), data.getString(3), RB.getString("state." + data.getString(4)), data.getString(5), data.getString(6)));
                            }
                        }
                    }
                    
                    res = "{\"data\": ";
                    res += gson.toJson(_h);
                    res += "}";
                } catch (SQLException ex) {
                    Logger.getLogger(Imprenta_Controller.class.getName()).log(Level.SEVERE, null, ex);
                    res = "[]";
                }
            } catch (SQLException ex) {
                Logger.getLogger(HistorialProceso.class.getName()).log(Level.SEVERE, null, ex);
                res = "[]";
            }
        }else{
            res = "[]";
        }
        
        try (PrintWriter out = response.getWriter()) {
            out.println(res);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
