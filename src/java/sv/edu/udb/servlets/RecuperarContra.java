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
import sv.edu.udb.connection.Email;
import sv.edu.udb.libreria.Encriptar;
import sv.edu.udb.libreria.Usuario;
import sv.edu.udb.controladores.Usuario_Controller;

/**
 *
 * @author Frank
 */
@WebServlet(name = "RecuperarContra", urlPatterns = {"/RecuperarContra"})
public class RecuperarContra extends HttpServlet {

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
        try(PrintWriter out = response.getWriter()) {
            if(request.getParameter("email") == null){
                out.println("0");
            }else{
                String email = request.getParameter("email"), res;
                Usuario u = Usuario_Controller.obtenerUsuarioCorreo(email);
                if (u != null) {
                    String pass = Encriptar.desencriptar(u.getPassword());
                    if (enviarCorreo(email, pass)) {
                        //Éxito
                        res = "1";
                    } else {
                        //Error
                        res = "0";
                    }
                } else {
                    //No existe
                    res = "-1";
                }
                
                out.println(res);
            }
            
        }
    }
    
    private boolean enviarCorreo(String correo, String contrasenna) {
        String mensaje = "<h5>Contraseña: </h5>" + contrasenna;
        Email email = new Email(correo);
        return email.enviar(mensaje, "[Thot] - Recuperación de Contraseña");
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
