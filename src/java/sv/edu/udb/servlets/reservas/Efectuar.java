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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sv.edu.udb.controladores.Reserva_Controller;
import sv.edu.udb.libreria.Reserva;
import sv.edu.udb.libreria.Usuario;

/**
 *
 * @author Frank
 */
@WebServlet(name = "Reservas.Efectuar", urlPatterns = {"/Reservas/Efectuar"})
public class Efectuar extends HttpServlet {


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
                
                if(_u.getTipoUsuario().equals("B")){
                    if (request.getParameter("idReserva") != null && request.getParameter("fecha_prestamo") != null && request.getParameter("fecha_devolucion") != null) {
                        Reserva _r = Reserva_Controller.obtenerReserva(request.getParameter("idReserva"), true);
                        DateFormat _f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        
                        if (_r != null) {
                            if (_r.getEstado().equals("VE")) {
                                
                                try {
                                    Date _prestamo = (_f.parse(request.getParameter("fecha_prestamo"))),
                                        _devolucion = (_f.parse(request.getParameter("fecha_devolucion")));
                                    
                                    if(_prestamo.before(_devolucion)){
                                        res = Reserva_Controller.efectuarReserva(_r, _devolucion) ? "1" : "0";
                                    }else{
                                        res = "-4"; //Fechas erróneas
                                    }
                                } catch (ParseException ex) {
                                    Logger.getLogger(Efectuar.class.getName()).log(Level.SEVERE, null, ex);
                                    res = "-4"; //Fechas erróneas
                                }
                                
                            } else {
                                res = "-3"; //La reserva no se encuentra en el estado óptimo (En proceso)
                            }

                        } else {
                            res = "-1"; //Cuerpo incorrecto o reserva no encontrada 
                        }
                    } else {
                        res = "-1"; //Cuerpo incorrecto o reserva no encontrada
                    } 
                }else{
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
