<%-- 
    Document   : reservas
    Created on : 04-29-2018, 06:46:10 PM
    Author     : Leonardo
--%>
<%@page import="sv.edu.udb.controladores.Reserva_Controller"%>
<%@ include file="/WEB-INF/jspf/control_sesion.jspf" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% 
    Usuario _e = (Usuario) session.getAttribute("userData");
    pageContext.setAttribute("reservas", Reserva_Controller.obtenerReservas(true, _e.getIdUsuario())); 
%>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <script src="../js/Usuario/reserva.js"></script>
        <title>[Thot] - Usuario</title>
    </head>
    <body>
        <header>

            <nav class="teal darken-1">
                <div class="container">
                    <a href="#" data-target="user_nav" class="sidenav-trigger "><i class="material-icons">menu</i></a>
                    <div class="nav-wrapper"><a class="brand-logo center">Reservas</a></div>
                </div>
            </nav>

            <ul id="slide-out" class="sidenav sidenav-fixed">
                <li>
                    <div class="user-view">
                        <div class="background teal darken-1">
                        </div>
                        <a>
                            <img class="circle" src="/favicon.png">
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

                <li class="no-padding">
                    <ul class="collapsible collapsible-accordion">
                        <li>
                            <a class="collapsible-header"> <i class="material-icons"></i>Libros</a>
                            <div class="collapsible-body">
                                <ul>
                                    <li>
                                        <a href="prestamos.jsp">Mis Prestamos</a>
                                    </li>
                                    <li>
                                        <a href="reservas.jsp">Mis Reservaciones</a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
        </header>
                        
        <main class="container">
            <br>
            <c:if test="${reservas.size() > 0}">
                <table class="center" id="tblReservas">
                    <thead>
                        <th>Libro</th>
                        <th>Fecha de reserva</th>
                        <th>Fecha de vencimiento</th>
                        <th>Estado</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${reservas}" var="_r">
                            <tr class="lighten-5  text-darken-5">
                                <td>${_r.getEjemplar().getLibro().getTitulo()}</td>
                                <td>${_r.getFechaReservaFormato()}</td>
                                <td>${_r.getFechaVencimientoFormato()}</td>
                                <td>${_r.getDisplayEstado()}</td>
                            </tr>
                        </c:forEach >
                    </tbody>
                </table>
            </c:if>
            <c:if test="${reservas.size() == 0}">
                <div class="alert center red lighten-4 red-text text-darken-4">
                    No hay reservas registradas para mostrar!
                </div><br>
            </c:if>
        </main>
    </body>
</html>