<%-- 
    Document   : reservas
    Created on : 04-29-2018, 06:46:10 PM
    Author     : Leonardo
--%>
<%@page import="sv.edu.udb.controladores.Reserva_Controller"%>
<%@ include file="/WEB-INF/jspf/control_sesion.jspf" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set scope="page" var="path" value="/Thot/Usuario/"></c:set>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<!DOCTYPE html>

<% 
    Usuario _e = (Usuario) session.getAttribute("userData");
    pageContext.setAttribute("reservas", Reserva_Controller.obtenerReservas(true, _e.getIdUsuario())); 
%>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <script src="../js/Usuario/reserva.js"></script>
        <link rel="stylesheet" href="/Thot/css/usuario.css">
        <title><fmt:message key="user.header.title"/></title>
    </head>
    <body>
        <form action="/Thot/Logout" name="frmLogout" method="POST"></form>        
        <header>

            <nav class="blue darken-1">
                <div class="container">
                    <a href="#" data-target="user_nav" class="sidenav-trigger "><i class="material-icons">menu</i></a>
                    <div class="nav-wrapper"><a class="brand-logo center"><fmt:message key="reserves"/></a></div>
                </div>
            </nav>

            <ul id="slide-out" class="sidenav sidenav-fixed">
                <li>
                    <div class="user-view">
                        <div class="background blue darken-1">
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

                <li class="nav-item waves-effect"><a href="${path}"><fmt:message key="books"/>  <i class="material-icons">book</i></a></li>
                <li class="nav-item waves-effect"><a href="${path}prestamos.jsp"><fmt:message key="loans"/> <i class="material-icons">assignment</i></a></li>
                <li class="nav-item active waves-effect"><a href="${path}reservas.jsp"><fmt:message key="reserves"/> <i class="material-icons">https</i></a></li>
                <li class="nav-item waves-effect">
                    <a onclick="frmLogout.submit();"><fmt:message key="logout"/>  <i class="material-icons">exit_to_app</i></a>
                </li>
            </ul>
        </header>
                        
        <main class="">
            <br>
            <c:if test="${reservas.size() > 0}">
                <table class="center" id="tblReservas">
                    <thead>
                        <th><fmt:message key="book"/></th>
                        <th><fmt:message key="reserveDate"/></th>
                        <th><fmt:message key="expireDate"/></th>
                        <th><fmt:message key="state"/></th>
                    </thead>
                    <tbody>
                        <c:forEach items="${reservas}" var="_r">
                            <tr class="lighten-5 text-darken-5 ${_r.getEstado().equals("VO") ? 'red red-text' : _r.getEstado().equals("EO") ? 'green green-text' : ''}">
                                <td>${_r.getEjemplar().getLibro().getTitulo()}</td>
                                <td>${_r.getFechaReservaFormato()}</td>
                                <td>${_r.getFechaVencimientoFormato()}</td>
                                <td><fmt:message key="state.${_r.getEstado()}"/></td>
                            </tr>
                        </c:forEach >
                    </tbody>
                </table>
            </c:if>
            <c:if test="${reservas.size() == 0}">
                <div class="alert center red lighten-4 red-text text-darken-4">
                    <fmt:message key="reserves.notFound"/>
                </div><br>
            </c:if>
        </main>
    </body>
</html>