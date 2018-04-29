/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.servlets.reservas;

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
import sv.edu.udb.controladores.Libro_Controller;
import sv.edu.udb.controladores.Reserva_Controller;
import sv.edu.udb.libreria.Ejemplar;
import sv.edu.udb.libreria.Libro;
import sv.edu.udb.libreria.Reserva;
import sv.edu.udb.libreria.Usuario;

/**
 *
 * @author Frank
 */
@WebServlet(name = "Reservas.Registar", urlPatterns = {"/Reservas/Registar"})
public class Registar extends HttpServlet {
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
                if(request.getParameter("idLibro") != null && request.getParameter("fecha_reserva") != null && request.getParameter("fecha_vencimiento") != null){
                    Libro _l = Libro_Controller.obtenerLibro(request.getParameter("idLibro"), true);
                    Usuario _u = (Usuario) _s.getAttribute("userData");
                    
                    DateFormat _f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    
                    if(_l != null){
                        Ejemplar _e = Ejemplar_Controller.obtenerEjemplarParaProceso(_l);
                        
                        if(_e != null){
                            try {
                                Reserva _r = new Reserva(_f.parse(request.getParameter("fecha_reserva")), _f.parse(request.getParameter("fecha_vencimiento")), _e, _u);
                                res = Reserva_Controller.insertar(_r) ? "1" : "0";
                            } catch (ParseException ex) {
                                Logger.getLogger(Registar.class.getName()).log(Level.SEVERE, null, ex);
                                res = "0";
                            }
                        }else{
                            res = "-3"; //El libro no posee ejemplares disponibles para ser reservados
                        }
                    }else{
                        res = "-1"; //Cuerpo incorrecto o préstamo no encontrado
                    }
                }else{
                    res = "-1"; //Cuerpo incorrecto o libro no encontrado
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
