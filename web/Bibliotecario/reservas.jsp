<%-- 
    Document   : reservas
    Created on : 04-25-2018, 08:05:25 PM
    Author     : Frank
--%>

<%@ include file="/WEB-INF/jspf/control_sesion.jspf" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>

<%@page import="sv.edu.udb.controladores.Reserva_Controller"%>
<%@page import="sv.edu.udb.libreria.Reserva"%>
<%@page import="java.util.List"%>

<c:set scope="page" var="path" value="/Thot/Bibliotecario/" />
<% pageContext.setAttribute("reservas", Reserva_Controller.obtenerReservas(true));%>

<fmt:message key="reserves.execute" var="executeVar"/>
<fmt:message key="reserves.showReserve" var="showReserveVar"/>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <link rel="stylesheet" href="/Thot/css/bibliotecario.css">
        <script src="/Thot/js/Bibliotecario/reservas.js"></script>

        <title><fmt:message key="librarian.header.title"/></title>
    </head>
    <body>
        <form action="/Thot/Logout" name="frmLogout" method="POST"></form>
        <header>
            <nav class="grey darken-4">
                <div class="container">
                    <a href="#" data-target="user_nav" class="sidenav-trigger "><i class="material-icons">menu</i></a>
                    <div class="nav-wrapper"><a class="brand-logo center"><fmt:message key="reserves"/></a></div>
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
                                <span style="font-weight: bold;" class="white-text email"><fmt:message key="${user.getTipoUsuario()}"/></span>
                            </a>
                            <a>
                                <span class="white-text email"><c:out value="${user.getCorreo()}"></c:out></span>
                            </a>
                        </div>
                </li>
                <li class="nav-item waves-effect"><a href="${path}"><fmt:message key="home"/> <i class="material-icons">home</i></a></li>
                <li class="nav-item waves-effect"><a href="${path}prestamos.jsp"><fmt:message key="loans"/> <i class="material-icons">assignment</i></a></li>
                <li class="active nav-item waves-effect"><a href="${path}reservas.jsp"><fmt:message key="reserves"/> <i class="material-icons">https</i></a></li>
                <li class="nav-item waves-effect"><a href="${path}libros.jsp"><fmt:message key="books"/> <i class="material-icons">book</i></a></li>
                <li>
                    <div class="divider"></div>
                </li>
                <li class="nav-item waves-effect">
                    <a onclick="frmLogout.submit();"><fmt:message key="logout"/> <i class="material-icons">exit_to_app</i></a>
                </li>
            </ul>
        </header>

        <main class="">
            <c:if test="${sessionScope.msg != null}">
                <div class="alert center ${sessionScope.msg_type} lighten-4 ${sessionScope.msg_type}-text text-darken-4">
                    <fmt:message key="reserves.error.${sessionScope.msg}"/>
                </div><br>
                <c:remove scope="session" var="msg"/>
                <c:remove scope="session" var="msg_type"/>
            </c:if>
            <c:if test="${reservas.size() > 0}">
                <table class="center" id="tblReservas">
                    <thead>
                        <th>idReserva</th>
                        <th><fmt:message key="user"/></th>
                        <th><fmt:message key="book"/></th>
                        <th><fmt:message key="reserveDate"/></th>
                        <th><fmt:message key="expireDate"/></th>
                        <th><fmt:message key="state"/></th>
                        <th><fmt:message key="actions"/></th>
                    </thead>
                    <tbody>
                    <c:forEach items="${reservas}" var="_r">
                        <tr data="${_r.getIdReserva()}" class="lighten-5  text-darken-5 ${_r.getEstado().equals("VO") ? 'red red-text' : _r.getEstado().equals("EO") ? 'green green-text' : ''}">
                            <td>${_r.getIdReserva()}</td>
                            <td idUsuario="${_r.getUsuario().getIdUsuario()}">${_r.getUsuario().getDisplayName()}</td>
                            <td idLibro="${_r.getEjemplar().getLibro().getIdLibro()}">${_r.getEjemplar().getLibro().getTitulo()}</td>
                            <td>${_r.getFechaReservaFormato()}</td>
                            <td>${_r.getFechaVencimientoFormato()}</td>
                            <td><fmt:message key="state.${_r.getEstado()}"/></td>
                            <td>
                                <a title="${showReserveVar}" href="${path}verReserva.jsp?idReserva=${_r.getIdReserva()}" class="btn waves-effect grey darken-3 waves-light"><i class="material-icons">visibility</i></a>
                                <a ${_r.getEstado().equals("EO") || _r.getEstado().equals("VO") ? 'disabled' : ''} dataID="${_r.getIdReserva()}" title="${executeVar}" href="#mdlPrestamo" class="modal-trigger btnMdlPrestamo btn waves-effect grey darken-3 waves-light"><i class="material-icons">assignment_turned_in</i></a>
                            </td>
                        </tr>
                    </c:forEach >
                    </tbody>
                </table>
                    
                <form action="/Thot/Reservas/Reporte" method="POST">
                    <button class="btn btn grey darken-4 waves-effect waves-light"><fmt:message key="reserves.report"/> <i class="material-icons right">file_download</i></button>
                </form>
                    
                <div class="modal" id="mdlPrestamo">
                    <div class="modal-content">
                        <h5 class="center grey-text text-darken-4"><fmt:message key="reserves.execute.title"/></h5>

                        <form name="frmConfirm" class="row">
                            <input type="hidden" name="idReserva">
                            <div class="input-field col s8 offset-s2">
                                <select name="cmbDays" id="cmbDays">
                                    <option value="1">1 <fmt:message key="day"/></option>
                                    <option value="3">3 <fmt:message key="days"/></option>
                                    <option value="5">5 <fmt:message key="days"/></option>
                                </select>
                                <label for="cmdDays"><fmt:message key="reserves.cmbDays"/></label>
                            </div>
                        </form>

                        <div id="info-cont" class="container section" style="display: flex; justify-content: center; flex-direction: column;"></div>
                        <br>
                        <div class="btn-cont">
                            <a class="modal-action modal-close btn waves-effect red"><fmt:message key="cancel"/> <i class="material-icons right">cancel</i></a>
                            <a id="btnConfirm" class="btn waves-effect green"><fmt:message key="confirm"/> <i class="material-icons right">check</i></a>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${reservas.size() == 0}">
                <div class="alert center red lighten-4 red-text text-darken-4">
                    <fmt:message key="reserves.notFound"/>
                </div><br>
            </c:if>
        </main>
    </body>
</html>
