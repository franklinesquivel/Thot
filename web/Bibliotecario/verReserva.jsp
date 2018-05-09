<%-- 
    Document   : verReserva
    Created on : 08-may-2018, 22:13:52
    Author     : Diego Lemus
--%>

<%@page import="sv.edu.udb.controladores.Reserva_Controller"%>
<%@ include file="/WEB-INF/jspf/control_sesion.jspf" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>

<%@page import="sv.edu.udb.controladores.Prestamo_Controller"%>
<%@page import="sv.edu.udb.libreria.Prestamo"%>
<%@page import="java.util.List"%>

<c:set scope="page" var="path" value="/Thot/Bibliotecario/"></c:set>

<c:if test="${param.idReserva == null}">
    <c:redirect url="reservas.jsp">
        <c:set scope="session"  var="msg" value="Selecciona una reserva para visualizar.." />
        <c:set scope="session" var="msg_type" value="yellow" />
    </c:redirect>
</c:if>
   
<% pageContext.setAttribute("_p", Reserva_Controller.obtenerReserva(request.getParameter("idReserva"), true)); %>

<c:if test="${_p == null}">
    <c:redirect url="reservas.jsp">
        <c:set scope="session"  var="msg" value="La reserva que deseas visualizar no existe..." />
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

        <title><fmt:message key="librarian.header.title"/></title>
    </head>
    <body>
        <form action="/Thot/Logout" name="frmLogout" method="POST"></form>
        <header>
            <nav class="grey darken-4">
                <div class="container">
                    <a href="#" data-target="user_nav" class="sidenav-trigger "><i class="material-icons">menu</i></a>
                    <div class="nav-wrapper"><a class="brand-logo center"><fmt:message key="loan"/></a></div>
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
                <li class="nav-item waves-effect"><a href="${path}historial.jsp"><fmt:message key="record"/> <i class="material-icons">history</i></a></li>
                <li>
                    <div class="divider"></div>
                </li>
                <li class="nav-item waves-effect">
                    <a onclick="frmLogout.submit();"><fmt:message key="logout"/> <i class="material-icons">exit_to_app</i></a>
                </li>
            </ul>
        </header>
        
        <main class="">
            <div class="row">
                <div class="col s12 m8 offset-m2">
                    <h5><b><fmt:message key="user"/>:</b> ${_p.getUsuario().getDisplayName() }</h5>
                    <h5><b><fmt:message key="book"/>:</b> ${_p.getEjemplar().getLibro().getTitulo()}</h5>
                    <h5><b><fmt:message key="reserveDate"/>:</b> ${_p.getFechaReservaFormato()}</h5>
                    <h5><b><fmt:message key="expireDate"/>:</b> ${_p.getFechaVencimientoFormato()}</h5>    
                    <h5><b><fmt:message key="state"/>:</b> <span class="${_p.getEstado().equals("Vencido") ? 'red-text' : ''}"><fmt:message key="state.${_p.getDisplayEstado()}"/></span></h5>    
                </div>
            </div>
            <div class="btn-cont">
                <a class="btn grey darken-4 waves-effect waves-light" href="${path}verLibro.jsp?idLibro=${_p.getEjemplar().getLibro().getIdLibro()}"><fmt:message key="books.showBook"/> <i class="material-icons right">book</i></a>
            </div>
            <br>
            <a href="${path}reservas.jsp"><fmt:message key="reserves"/></a>
        </main>
    </body>
</html>
