/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.servlets.prestamos;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import sv.edu.udb.controladores.Prestamo_Controller;
import sv.edu.udb.libreria.Prestamo;
import sv.edu.udb.libreria.Usuario;

/**
 *
 * @author Frank
 */
@WebServlet(name = "Prestamos.Reporte", urlPatterns = {"/Prestamos/Reporte"})
public class Reporte extends HttpServlet {
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession _s = request.getSession(true);

        if (_s.getAttribute("logged") != null) {
            if ((Boolean) _s.getAttribute("logged")) {
                Usuario _u = (Usuario) _s.getAttribute("userData");
                if (_u != null && _u.getTipoUsuario().equals("B")) {
                    try (ServletOutputStream out = response.getOutputStream()) {
                        response.setContentType("application/pdf");
                        //response.setHeader("Content-Disposition", "attachment; filename = ReporteMascotas.pdf;");

                        Context init = new InitialContext();
                        Context context = (Context) init.lookup("java:comp/env");
                        DataSource dataSource = (DataSource) context.lookup("jdbc/mysql");
                        Connection conexion = dataSource.getConnection();

                        JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(getServletContext().getRealPath("reportes/reporte_prestamos.jasper"));
                        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, conexion);
                        JasperExportManager.exportReportToPdfStream(jasperPrint, out);
                    } catch (Exception e) {
                        Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, e);
                    }
                } else {
                    request.getSession().setAttribute("msg", "Debes estar autenticado para efectuar esta acción...");
                    request.getSession().setAttribute("msg_type", "red");
                    response.sendRedirect("/Thot/");
                }
            } else {
                request.getSession().setAttribute("msg", "Debes estar autenticado para efectuar esta acción...");
                request.getSession().setAttribute("msg_type", "red");
                response.sendRedirect("/Thot/");
            }
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
