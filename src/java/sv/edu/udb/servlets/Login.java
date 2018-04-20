/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sv.edu.udb.libreria.Usuario;
import sv.edu.udb.modelos.Usuario_Model;

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
            Usuario u = Usuario_Model.buscarUsuario(user, pass);
            if (u != null) {
                _s.setAttribute("logged", true);
                _s.setAttribute("userData", u);

                response.sendRedirect("/Thot/" + (u.getTipoUsuario().equals("B") ? "Bibliotecario" : "Usuario") + "/");
            } else {
                _s.setAttribute("msg_type", "warning");
                _s.setAttribute("msg", "Las credenciales ingresadas no son correctas...");

                response.sendRedirect("/Thot/");
            }
        }
        
//        try(PrintWriter out = response.getWriter()) {
//        }
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
