/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.servlets.ejemplares;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sv.edu.udb.controladores.Ejemplar_Controller;
import sv.edu.udb.controladores.Libro_Controller;
import sv.edu.udb.libreria.Ejemplar;
import sv.edu.udb.libreria.Libro;

/**
 *
 * @author Frank
 */
@WebServlet(name = "Ejemplares.Aumentar", urlPatterns = {"/Ejemplares/Aumentar"})
public class Aumentar extends HttpServlet {
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String res = "1";
            if (request.getParameter("add") != null && request.getParameter("idLibro") != null) {
                Libro _l = Libro_Controller.obtenerLibro(request.getParameter("idLibro"), true);
                int cant = Integer.parseInt(request.getParameter("add"));
                
                for (int i = 0; i < cant; i++) {
                    if(!Ejemplar_Controller.insertar(new Ejemplar("", "", "PENDIENTE", "PM", _l))){
                        res = "0";
                        break;
                    }
                }
                
                _l.setCant_ejemplares(_l.getCant_ejemplares() + cant);
                if(!Libro_Controller.modificar(_l)){
                    res = "0";
                }
            } else {
                res = "-1";
            }
            
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
