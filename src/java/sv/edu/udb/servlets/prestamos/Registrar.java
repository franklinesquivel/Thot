/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.servlets.prestamos;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sv.edu.udb.controladores.Ejemplar_Controller;
import sv.edu.udb.controladores.Prestamo_Controller;
import sv.edu.udb.controladores.Usuario_Controller;
import sv.edu.udb.libreria.Ejemplar;
import sv.edu.udb.libreria.Prestamo;
import sv.edu.udb.libreria.Usuario;

/**
 *
 * @author Frank
 */
@WebServlet(name = "Prestamos.Registrar", urlPatterns = {"/Prestamos/Registrar"})
public class Registrar extends HttpServlet {
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
                Usuario _au = (Usuario) _s.getAttribute("userData");

                if (_au.getTipoUsuario().equals("B")) {
                    if (request.getParameter("fecha_devolucion") != null && request.getParameter("idEjemplar") != null && request.getParameter("idUsuario") != null) {
                        Ejemplar _e = Ejemplar_Controller.obtenerEjemplar(request.getParameter("idEjemplar"));
                        Usuario _u = Usuario_Controller.obtenerUsuario(request.getParameter("idUsuario"));

                        DateFormat _f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        Prestamo _p;
                        try {
                            if (_e != null && _u != null) {
                                if (_e.getEstado().equals("D") && _u.isEstado()) {
                                    _p = new Prestamo(_f.parse(request.getParameter("fecha_devolucion")), _e, _u);
                                    res = Prestamo_Controller.insertar(_p) ? "1" : "0";
                                } else {
                                    res = "-3"; //El ejemplar seleccionado o el usuario no se encuentran en estado óptimo para ejecutar un préstamo...
                                }
                            } else {
                                res = "-1"; //Cuerpo incorrecto o (ejemplar | usuario) no encontrado
                            }
                        } catch (ParseException ex) {
                            Logger.getLogger(Registrar.class.getName()).log(Level.SEVERE, null, ex);
                            res = "0"; //Error de servidor
                        }
                    } else {
                        res = "-1"; //Cuerpo incorrecto o (ejemplar | usuario) no encontrado
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
