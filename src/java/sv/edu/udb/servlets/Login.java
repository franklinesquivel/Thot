/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sv.edu.udb.connection.DBConnection;
import sv.edu.udb.libreria.Usuario;
import sv.edu.udb.controladores.Usuario_Controller;

/**
 *
 * @author Frank
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {
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
        
        String user = request.getParameter("txtUser"), pass = request.getParameter("txtPassword");
        HttpSession _s = request.getSession(true);
        
        if (user == null || pass == null) {
            _s.setAttribute("msg_type", "danger");
            _s.setAttribute("msg", "Ingrese datos válidos!");

            response.sendRedirect("/Thot/");
        } else {
            String redirect = "";
            switch (DBConnection.login(user, pass)) {
                //Encontrado
                case 1:
                    Usuario u = Usuario_Controller.buscarUsuario(user, pass);
                    _s.setAttribute("logged", true);
                    _s.setAttribute("userData", u);
                    redirect = ("/Thot/" + (u.getTipoUsuario().equals("B") ? "Bibliotecario" : "Usuario") + "/");
                    break;
                //No encontrado : Contraseña incorrecta
                case -1: case 0:
                    _s.setAttribute("msg_type", "yellow");
                    _s.setAttribute("msg", "El usuario ingresado no ha sido encontrado... ");
                    redirect = ("/Thot/");
                    break;
                //Error
                case -2:
                    _s.setAttribute("msg_type", "red");
                    _s.setAttribute("msg", "En este momento no se puede conectar a la plataforma. Intente más tarde...");
                    redirect = ("/Thot/");
                    break;
            }
            
            response.sendRedirect(redirect);
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
