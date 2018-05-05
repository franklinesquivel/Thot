<%-- 
    Document   : prestamos.jsp
    Created on : 04-24-2018, 07:28:39 PM
    Author     : Frank
--%>

<%@ include file="/WEB-INF/jspf/control_sesion.jspf" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@page import="sv.edu.udb.controladores.Prestamo_Controller"%>
<%@page import="sv.edu.udb.libreria.Prestamo"%>
<%@page import="java.util.List"%>

<c:set scope="page" var="path" value="/Thot/Bibliotecario/"></c:set>
<% pageContext.setAttribute("prestamos", Prestamo_Controller.obtenerPrestamos(true)); %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <link rel="stylesheet" href="/Thot/css/bibliotecario.css">
        <script src="/Thot/js/Bibliotecario/prestamos.js"></script>

        <title>[Thot] - Bibliotecario</title>
    </head>
    <body>
        <form action="/Thot/Logout" name="frmLogout" method="POST"></form>
        <header>
            <nav class="grey darken-4">
                <div class="container">
                    <a href="#" data-target="user_nav" class="sidenav-trigger "><i class="material-icons">menu</i></a>
                    <div class="nav-wrapper"><a class="brand-logo center">Préstamos</a></div>
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
                <li class="active nav-item waves-effect"><a href="${path}prestamos.jsp">Préstamos <i class="material-icons">assignment</i></a></li>
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
            <c:if test="${sessionScope.msg != null}">
                <div class="alert center ${sessionScope.msg_type} lighten-4 ${sessionScope.msg_type}-text text-darken-4">
                    ${sessionScope.msg}
                </div><br>
                <c:remove scope="session" var="msg"/>
                <c:remove scope="session" var="msg_type"/>
            </c:if>
            <c:if test="${prestamos.size() > 0}">
                <form name="frmFactura" action="/Thot/Prestamos/Factura" method="POST">
                    <input type="hidden" name="idPrestamo" />
                </form>
                <table class="center" id="tblPrestamos">
                    <thead>
                        <th>idPrestamo</th>
                        <th>Usuario</th>
                        <th>Fecha de devolución</th>
                        <th>Estado</th>
                        <th>Mora acumulada</th>
                        <th>Acciones</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${prestamos}" var="_p">
                            <tr data="${_p.getIdPrestamo()}" class="lighten-5  text-darken-5 ${_p.getEstado().equals("VO") ? 'red red-text' : _p.getEstado().equals("FO") ? 'grey grey-text' : ''}">
                                <td>${_p.getIdPrestamo()}</td>
                                <td>${_p.getUsuario().getDisplayName()}</td>
                                <td>${_p.getFechaDevolucionFormato()}</td>
                                <td>${_p.getDisplayEstado()}</td>
                                <td>$${_p.getMoraDecimales(2)}</td>
                                <td>
                                    <a title="Ver préstamo" href="${path}verPrestamo.jsp?idPrestamo=${_p.getIdPrestamo()}" class="btn waves-effect grey darken-3 waves-light"><i class="material-icons">visibility</i></a>
                                    <c:if test="${_p.getEstado() == 'FO'}">
                                        <a idPrestamo="${_p.getIdPrestamo()}" title="Generar factura" class="btnFactura btn waves-effect grey darken-3 waves-light"><i class="material-icons">receipt</i></a>
                                    </c:if>
                                    <c:if test="${_p.getEstado() != 'FO'}">
                                        <a idPrestamo="${_p.getIdPrestamo()}" title="Finalizar préstamo" href="#mdlFinalizar" class="modal-trigger btnFinalizar btn waves-effect grey darken-3 waves-light"><i class="material-icons">local_library</i></a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach >
                    </tbody>
                </table>
                <form action="/Thot/Prestamos/Reporte" method="POST">
                    <button class="btn btn grey darken-4 waves-effect waves-light">Reporte de préstamos <i class="material-icons right">file_download</i></button>
                </form>
            </c:if>
            <c:if test="${prestamos.size() == 0}">
                <div class="alert center red lighten-4 red-text text-darken-4">
                    No hay préstamos registrados para mostrar!
                </div><br>
            </c:if>
        </main>
                                
        <div id="mdlFinalizar" class="modal">
            <div class="modal-content section">
                <h5 class="grey-text text-darken-4 center">¿Estás seguro que deseas dar por finalizado este préstamo?</h5>
                <div id="info-cont"></div><br>
                <div class="btn-cont">
                    <a class="modal-action modal-close btn waves-effect waves-light red">Cancelar <i class="material-icons right">cancel</i></a>
                    <a id="btnConfirmFinish" class="btn waves-effect waves-light green">Confirmar <i class="material-icons right">check</i></a>
                </div>
            </div>
        </div>
    </body>
</html>