/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.servlets.ejemplares;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import javax.servlet.http.HttpSession;
import sv.edu.udb.controladores.Ejemplar_Controller;
import sv.edu.udb.libreria.Ejemplar;
import sv.edu.udb.libreria.Usuario;

/**
 *
 * @author Frank
 */
@WebServlet(name = "Ejemplares.Habilitar", urlPatterns = {"/Ejemplares/Habilitar"})
public class Habilitar extends HttpServlet {
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
            Gson gson = new GsonBuilder().create();
            String res = "";
            HttpSession _s = request.getSession(true);
            
            if((Boolean) _s.getAttribute("logged")){
                Usuario _u = (Usuario) _s.getAttribute("userData");
                if(_u.getTipoUsuario().equals("B")){
                    if (request.getParameter("ejemplares") != null) {
                        try {
                            Type listType = new TypeToken<ArrayList<Ejemplar>>() {}.getType();
                            List<Ejemplar> ejemplares = gson.fromJson(request.getParameter("ejemplares"), listType);

                            if (ejemplares.size() > 0) {
                                for (Ejemplar _e : ejemplares) {
                                    _e.setEstado("D");
                                    if (!Ejemplar_Controller.modificar(_e)) {
                                        res = "0"; //Error al insertar la iteración
                                    }
                                }
                                res = res.equals("0") ? "0" : "1";
                            }else{
                                res = "-3"; //No hay ejemplares
                            }
                        } catch (JsonSyntaxException e) {
                            res = "-1"; //Cuerpo incorrecto
                        }
                    } else {
                        res = "-1"; //Cuerpo incorrecto
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
