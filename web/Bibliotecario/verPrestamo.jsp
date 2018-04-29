<%-- 
    Document   : verPrestamo
    Created on : 04-24-2018, 10:24:54 PM
    Author     : Frank

--%>
<%@ include file="/WEB-INF/jspf/control_sesion.jspf" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@page import="sv.edu.udb.controladores.Prestamo_Controller"%>
<%@page import="sv.edu.udb.libreria.Prestamo"%>
<%@page import="java.util.List"%>

<c:set scope="page" var="path" value="/Thot/Bibliotecario/"></c:set>

<c:if test="${param.idPrestamo == null}">
    <c:redirect url="prestamos.jsp">
        <c:set scope="session"  var="msg" value="Selecciona un préstamo para visualizar.." />
        <c:set scope="session" var="msg_type" value="yellow" />
    </c:redirect>
</c:if>
    
<% pageContext.setAttribute("_p", Prestamo_Controller.obtenerPrestamo(request.getParameter("idPrestamo"), true)); %>

<c:if test="${_p == null}">
    <c:redirect url="prestamos.jsp">
        <c:set scope="session"  var="msg" value="El préstamo que deseas visualizar no existe..." />
        <c:set scope="session" var="msg_type" value="yellow" />
    </c:redirect>
</c:if>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <link rel="stylesheet" href="/Thot/css/bibliotecario.css">
        <script src="/Thot/js/Bibliotecario/libros.js"></script>

        <title>[Thot] - Bibliotecario</title>
    </head>
    <body>
        <form action="/Thot/Logout" name="frmLogout" method="POST"></form>
        <header>
            <nav class="grey darken-4">
                <div class="container">
                    <a href="#" data-target="user_nav" class="sidenav-trigger "><i class="material-icons">menu</i></a>
                    <div class="nav-wrapper"><a class="brand-logo center">Préstamo</a></div>
                    </div>
                </nav>

                <ul id="user_nav" class="sidenav sidenav-fixed">
                    <li>
                        <div class="user-view">
                            <div class="background grey darken-4">
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
                <li class="nav-item waves-effect"><a href="${path}">Inicio <i class="material-icons">home</i></a></li>
                <li class="nav-item waves-effect"><a href="${path}prestamos.jsp">Préstamos <i class="material-icons">assignment</i></a></li>
                <li class="nav-item waves-effect"><a href="${path}reservas.jsp">Reservas <i class="material-icons">https</i></a></li>

                <li class="no-padding">
                    <ul class="collapsible collapsible-accordion">
                        <li class="">
                            <a class="collapsible-header waves-effect">Libros <i class="material-icons">book</i></a>
                            <div class="collapsible-body">
                                <ul>
                                    <li class=" waves-effect"><a href="${path}libros.jsp" class="">Listar <i class="material-icons">remove_red_eye</i></a></li>
                                    <li class="waves-effect"><a href="${path}registrarLibro.jsp" class="">Registrar <i class="material-icons">add</i></a></li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </li>
                <li class="no-padding">
                    <ul class="collapsible collapsible-accordion">
                        <li>
                            <a class="collapsible-header waves-effect">Autores <i class="material-icons">brush</i></a>
                            <div class="collapsible-body">
                                <ul>
                                    <li class="waves-effect"><a href="${path}autores.jsp" class="">Listar <i class="material-icons">remove_red_eye</i></a></li>
                                    <li class="waves-effect"><a href="${patch}registrarAutor.jsp" class="">Registrar <i class="material-icons">add</i></a></li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </li>
                <li>
                    <div class="divider"></div>
                </li>
                <li class="nav-item waves-effect">
                    <a onclick="frmLogout.submit();">Cerrar Sesión <i class="material-icons">exit_to_app</i></a>
                </li>

            </ul>
        </header>
        
        <main class="">
            <div class="row">
                <div class="col s12 m8 offset-m2">
                    <h5><b>Libro:</b> ${_p.getEjemplar().getLibro().getTitulo()}</h5>
                    <h5><b>Usuario:</b> ${_p.getUsuario().getDisplayName()}</h5>
                    <h5><b>Fecha de préstamo:</b> ${_p.getFechaPrestamoFormato()}</h5>
                    <h5><b>Fecha de devolución:</b> ${_p.getFechaDevolucionFormato()}</h5>
                    <h5><b>Mora acumulada:</b> $${_p.getMora()}</h5>    
                    <h5><b>Estado:</b> <span class="${_p.getEstado().equals("VO") ? 'red-text' : ''}">${_p.getDisplayEstado()}</span></h5>    
                </div>
            </div>
            <div class="btn-cont">
                <a class="btn grey darken-4 waves-effect waves-light" href="${path}verLibro.jsp?idLibro=${_p.getEjemplar().getLibro().getIdLibro()}">Ver libro <i class="material-icons right">book</i></a>
                <a class="btn grey darken-4 waves-effect waves-light" href="${path}verUsuario.jsp?idUsuario=${_p.getUsuario().getIdUsuario()}">Ver usuario <i class="material-icons right">person</i></a>
            </div>
            <br>
            <a href="${path}prestamos.jsp">Préstamos</a>
        </main>
    </body>
</html>
