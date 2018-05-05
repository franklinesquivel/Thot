<%-- 
    Document   : reservas
    Created on : 04-25-2018, 08:05:25 PM
    Author     : Frank
--%>

<%@ include file="/WEB-INF/jspf/control_sesion.jspf" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@page import="sv.edu.udb.controladores.Reserva_Controller"%>
<%@page import="sv.edu.udb.libreria.Reserva"%>
<%@page import="java.util.List"%>

<c:set scope="page" var="path" value="/Thot/Bibliotecario/" />
<% pageContext.setAttribute("reservas", Reserva_Controller.obtenerReservas(true));%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <link rel="stylesheet" href="/Thot/css/bibliotecario.css">
        <script src="/Thot/js/Bibliotecario/reservas.js"></script>

        <title>[Thot] - Bibliotecario</title>
    </head>
    <body>
        <form action="/Thot/Logout" name="frmLogout" method="POST"></form>
        <header>
            <nav class="grey darken-4">
                <div class="container">
                    <a href="#" data-target="user_nav" class="sidenav-trigger "><i class="material-icons">menu</i></a>
                    <div class="nav-wrapper"><a class="brand-logo center">Reservas</a></div>
                </div>
            </nav>

            <ul id="user_nav" class="sidenav sidenav-fixed">
                <li>
                    <div class="user-view">
                        <div class="background grey darken-4">
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
                <li class="nav-item waves-effect"><a href="${path}">Inicio <i class="material-icons">home</i></a></li>
                <li class="nav-item waves-effect"><a href="${path}prestamos.jsp">Préstamos <i class="material-icons">assignment</i></a></li>
                <li class="active nav-item waves-effect"><a href="${path}reservas.jsp">Reservas <i class="material-icons">https</i></a></li>

                <li class="no-padding">
                    <ul class="collapsible collapsible-accordion">
                        <li class="">
                            <a class="collapsible-header waves-effect">Libros <i class="material-icons">book</i></a>
                            <div class="collapsible-body">
                                <ul>
                                    <li class="waves-effect"><a href="${path}libros.jsp" class="">Listar <i class="material-icons">remove_red_eye</i></a></li>
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
            <c:if test="${sessionScope.msg != null}">
                <div class="alert center ${sessionScope.msg_type} lighten-4 ${sessionScope.msg_type}-text text-darken-4">
                    ${sessionScope.msg}
                </div><br>
                <c:remove scope="session" var="msg"/>
                <c:remove scope="session" var="msg_type"/>
            </c:if>
            <c:if test="${reservas.size() > 0}">
                <table class="center" id="tblReservas">
                    <thead>
                        <th>idReserva</th>
                        <th>Usuario</th>
                        <th>Libro</th>
                        <th>Fecha de reserva</th>
                        <th>Fecha de vencimiento</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </thead>
                    <tbody>
                    <c:forEach items="${reservas}" var="_r">
                        <tr data="${_r.getIdReserva()}" class="lighten-5  text-darken-5 ${_r.getEstado().equals("VO") ? 'red red-text' : _r.getEstado().equals("EO") ? 'green green-text' : ''}">
                            <td>${_r.getIdReserva()}</td>
                            <td idUsuario="${_r.getUsuario().getIdUsuario()}">${_r.getUsuario().getDisplayName()}</td>
                            <td idLibro="${_r.getEjemplar().getLibro().getIdLibro()}">${_r.getEjemplar().getLibro().getTitulo()}</td>
                            <td>${_r.getFechaReservaFormato()}</td>
                            <td>${_r.getFechaVencimientoFormato()}</td>
                            <td>${_r.getDisplayEstado()}</td>
                            <td>
                                <a title="Ver reserva" href="${path}verReserva.jsp?idReserva=${_r.getIdReserva()}" class="btn waves-effect grey darken-3 waves-light"><i class="material-icons">visibility</i></a>
                                <a ${_r.getEstado().equals("EO") || _r.getEstado().equals("VO") ? 'disabled' : ''} dataID="${_r.getIdReserva()}" title="Efectuar préstamo" href="#mdlPrestamo" class="modal-trigger btnMdlPrestamo btn waves-effect grey darken-3 waves-light"><i class="material-icons">assignment_turned_in</i></a>
                            </td>
                        </tr>
                    </c:forEach >
                    </tbody>
                </table>
                    
                <form action="/Thot/Reservas/Reporte" method="POST">
                    <button class="btn btn grey darken-4 waves-effect waves-light">Reporte de reservas <i class="material-icons right">file_download</i></button>
                </form>
                    
                <div class="modal" id="mdlPrestamo">
                    <div class="modal-content">
                        <h5 class="center grey-text text-darken-4">¿Estás seguro de efectuar esta reserva a préstamo?</h5>

                        <form name="frmConfirm" class="row">
                            <input type="hidden" name="idReserva">
                            <div class="input-field col s8 offset-s2">
                                <select name="cmbDays" id="cmbDays">
                                    <option value="1">1 día</option>
                                    <option value="3">3 días</option>
                                    <option value="5">5 días</option>
                                </select>
                                <label for="cmdDays">Selecciona la duración del préstamo</label>
                            </div>
                        </form>

                        <div id="info-cont" class="container section" style="display: flex; justify-content: center; flex-direction: column;"></div>
                        <br>
                        <div class="btn-cont">
                            <a class="modal-action modal-close btn waves-effect red">Cancelar <i class="material-icons right">cancel</i></a>
                            <a id="btnConfirm" class="btn waves-effect green">Confirmar <i class="material-icons right">check</i></a>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${reservas.size() == 0}">
                <div class="alert center red lighten-4 red-text text-darken-4">
                    No hay reservas registradas para mostrar!
                </div><br>
            </c:if>
        </main>
    </body>
</html>
