<%-- 
    Document   : prestamos
    Created on : 04-29-2018, 06:45:57 PM
    Author     : Leonardo
--%>
<%@ include file="/WEB-INF/jspf/control_sesion.jspf" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@page import="sv.edu.udb.controladores.Prestamo_Controller"%>

<c:set scope="page" var="path" value="/Thot/Usuario/"></c:set>
<%  
    Usuario _e = (Usuario) session.getAttribute("userData");
    pageContext.setAttribute("prestamos", Prestamo_Controller.obtenerPrestamos(true, _e.getIdUsuario()));
%>
  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <script src="../js/Usuario/prestamo.js"></script>
        <title>[Thot] - Usuario</title>
    </head>
    <body>
        <form action="/Thot/Logout" name="frmLogout" method="POST"></form>        
        <header>

            <nav class="teal darken-1">
                <div class="container">
                    <a href="#" data-target="user_nav" class="sidenav-trigger "><i class="material-icons">menu</i></a>
                    <div class="nav-wrapper"><a class="brand-logo center">Prestamos</a></div>
                </div>
            </nav>

            <ul id="slide-out" class="sidenav sidenav-fixed">
                <li>
                    <div class="user-view">
                        <div class="background teal darken-1">
                        </div>
                        <a>
                            <img class="circle" src="/Thot/images/thot.jpg">
                        </a>
                        <a>
                            <span class="white-text name"><c:out value="${user.getDisplayName()}"></c:out></span>
                        </a>    
                        <a>
                            <span style="font-weight: bold;" class="white-text email">${user.getTipoUsuario().equals("B") ? "Bibliotecario" : "Usuario"}</span>
                        </a>
                        <a>
                            <span class="white-text email"><c:out value="${user.getCorreo()}"></c:out></span>
                        </a>
                    </div>
                </li>

                <li class="nav-item waves-effect"><a href="${path}">Libros <i class="material-icons">book</i></a></li>
                <li class="nav-item active waves-effect"><a href="${path}prestamos.jsp">Préstamos <i class="material-icons">assignment</i></a></li>
                <li class="nav-item waves-effect"><a href="${path}reservas.jsp">Reservas <i class="material-icons">https</i></a></li>
                <li class="nav-item waves-effect">
                    <a onclick="frmLogout.submit();">Cerrar Sesión <i class="material-icons">exit_to_app</i></a>
                </li>
            </ul>
        </header>
                        
        <main class="">
            <br>
            <c:if test="${prestamos.size() > 0}">
                <table class="center" id="tblPrestamos">
                    <thead>
                        <th>Libro</th>
                        <th>Fecha de Prestamo</th>
                        <th>Fecha de Devolución</th>
                        <th>Mora</th>
                        <th>Estado</th>
                        <th>
                            Renovar
                        </th>
                    </thead>
                    <tbody>
                        <c:forEach items="${prestamos}" var="_p">
                            <tr  class="lighten-5  text-darken-5 ${_p.getEstado().equals("VO") ? 'red red-text' : _p.getEstado().equals("FO") ? 'grey grey-text' : ''}">
                                <td>${_p.getEjemplar().getLibro().getTitulo()}</td>
                                <td>${_p.getFechaPrestamoFormato()}</td>
                                <td>${_p.getFechaDevolucionFormato()}</td>
                                <td>$ ${_p.getMoraDecimales(2)}</td>
                                <td>${_p.getDisplayEstado()}</td>
                                <td>
                                    <c:if test="${_p.getEstado() != 'FO'}">
                                        <a class="waves-effect waves-light btn modal-trigger btnRenovar" href="#mdlRenovar" idprestamo="${_p.getIdPrestamo()}">Renovar</a>
                                    </c:if>
                                    <c:if test="${_p.getEstado() == 'FO'}">
                                        Finalizado
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach >
                    </tbody>
                </table>
            </c:if>
            <c:if test="${prestamos.size() == 0}">
                <div class="alert center red lighten-4 red-text text-darken-4">
                    No hay préstamos registrados para mostrar!
                </div><br>
            </c:if>
        </main>
        <div id="mdlRenovar" class="modal">
            <div class="modal-content center-align">
                <h4>Renovar Libro</h4>
                <p>¿Seguro deseas renovar (renovación por 1 día)?</p>
                <input type="hidden" name="idPrestamo" value="null" id="idPrestamo">
            </div>
            <div class="modal-footer">
                <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat" id="btnRenovar">Renovar</a>
                <a href="#!" class="modal-action modal-close waves-effect waves-red btn-flat">Cerrar</a>
            </div>
        </div>
    </body>
</html>
