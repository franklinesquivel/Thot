/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.servlets;

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
import sv.edu.udb.connection.Email;
import sv.edu.udb.libreria.Encriptar;
import sv.edu.udb.libreria.Usuario;
import sv.edu.udb.controladores.Usuario_Controller;

/**
 *
 * @author Leonardo
 */
@WebServlet(name = "Registro", urlPatterns = {"/Registro"})
public class Registro extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String res = "";
        try (PrintWriter out = response.getWriter()) {
            DateFormat ft = new SimpleDateFormat("yyyy-MM-dd");  
            Date birthdate = ft.parse(request.getParameter("birthdate"));
            if((request.getParameter("email") == null) || (request.getParameter("name") == null) || 
                (request.getParameter("lastName") == null) || (!compararFecha(birthdate))){
                res = "-2";
            }else{
                String name = request.getParameter("name"),
                    lastName = request.getParameter("lastName"),
                    email = request.getParameter("email");
                
                if(Usuario_Controller.verificarCorreo(email)){
                    String pass = Encriptar.encriptar(Usuario.crearContransenna());
                    String idUsuario = Usuario.crearIdUsuario("U", Usuario_Controller.obtenerNumUsuario("U"));

                    if(Usuario_Controller.insertar(new Usuario(idUsuario, name, lastName, email, birthdate, idUsuario, pass, true, "U"))){
                        if(enviarCorreo(email, Encriptar.desencriptar(pass))){
                            res = "1"; //Exito
                        }else{
                            res = "0"; //Error
                        }
                    }else{
                        res = idUsuario; //Error
                    }
                }else{//Existe un correo igual
                    res = "-1";
                }
            }
            out.println(res);
        } catch (ParseException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean enviarCorreo(String correo, String contrasenna){
        String mensaje  = "<h5>Contraseña: </h5>"+contrasenna;
        Email email = new Email(correo);
        return email.enviar(mensaje, "Registro de Usuario");
    }
    
    private boolean compararFecha(Date fecha){
        Date fechaActual = new Date();
        if(fecha.compareTo(fechaActual) < 0){
            return true;
        }
        return false;
    }

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
        processRequest(request, response);
    }

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
        processRequest(request, response);
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
