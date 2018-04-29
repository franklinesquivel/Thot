/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.servlets.prestamos;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sv.edu.udb.controladores.Prestamo_Controller;
import sv.edu.udb.libreria.Prestamo;
import sv.edu.udb.libreria.Usuario;

/**
 *
 * @author Frank
 */
@WebServlet(name = "Prestamos.Finalizar", urlPatterns = {"/Prestamos/Finalizar"})
public class Finalizar extends HttpServlet {

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
            String res;
            HttpSession _s = request.getSession(true);
            
            if((Boolean) _s.getAttribute("logged")){
                Usuario _u = (Usuario) _s.getAttribute("userData");

                if (_u.getTipoUsuario().equals("B")) {
                    if (request.getParameter("idPrestamo") != null) {
                        Prestamo _p = Prestamo_Controller.obtenerPrestamo(request.getParameter("idPrestamo"), false);

                        if (_p != null) {
                            if (!_p.getEstado().equals("FO")) {
                                res = Prestamo_Controller.finalizarPrestamo(_p) ? "1" : "0";
                            } else {
                                res = "-3"; //El prestamo ya está finalizado...
                            }
                        } else {
                            res = "-1"; //Cuerpo incorrecto o prestamo no encontrado
                        }
                    } else {
                        res = "-1"; //Cuerpo incorrecto o prestamo no encontrado
                    }
                } else {
                    res = "-2"; //No autenticado
                }
            }else{
                res = "-2"; //No autenticado
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
