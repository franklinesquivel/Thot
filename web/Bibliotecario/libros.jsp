<%-- 
    Document   : libros
    Created on : 04-22-2018, 02:06:37 PM
    Author     : Frank
--%>

<%@ include file="/WEB-INF/jspf/control_sesion.jspf" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="sv.edu.udb.libreria.Libro"%>
<%@page import="java.util.List"%>
<%@page import="sv.edu.udb.controladores.Libro_Controller"%>

<c:set scope="page" var="path" value="/Thot/Bibliotecario/"></c:set>
<% pageContext.setAttribute("libros", Libro_Controller.obtenerLibros()); %>

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
                    <div class="nav-wrapper"><a class="brand-logo center">Libros</a></div>
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
                <li class="nav-item waves-effect"><a href="${path}reservas.jsp">Reservas <i class="material-icons">https</i></a></li>

                <li class="no-padding">
                    <ul class="collapsible collapsible-accordion">
                        <li class="active">
                            <a class="collapsible-header waves-effect">Libros <i class="material-icons">book</i></a>
                            <div class="collapsible-body">
                                <ul>
                                    <li class="active waves-effect"><a href="${path}libros.jsp" class="">Listar <i class="material-icons">remove_red_eye</i></a></li>
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
            <c:if test="${libros.size() > 0}">
                <table class="center" id="tblLibros">
                    <thead>
                        <th>idLibro</th>
                        <th>Título</th>
                        <th>ISBN</th>
                        <th>Edición</th>
                        <th>Acciones</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${libros}" var="_l">
                            <tr>
                                <td>${_l.getIdLibro()}</td>
                                <td>${_l.getTitulo()}</td>
                                <td>${_l.getIsbn()}</td>
                                <td>${_l.getEdicion()}</td>
                                <td>
                                    <a title="Ver libro" href="${path}verLibro.jsp?idLibro=${_l.getIdLibro()}" class="btn waves-effect grey darken-3 waves-light"><i class="material-icons">visibility</i></a>
                                    <a title="Administrar ejemplares" href="${path}gestionEjemplares.jsp?idLibro=${_l.getIdLibro()}" class="btn waves-effect grey darken-3 waves-light"><i class="material-icons">chrome_reader_mode</i></a>
                                    <a ${_l.esProcesable() ? 'disabled' : ''} title="Registrar Préstamo" href="${path}registrarPrestamo.jsp?idLibro=${_l.getIdLibro()}" class="btn waves-effect grey darken-3 waves-light"><i class="material-icons">assignment</i></a>
                                </td>
                            </tr>
                        </c:forEach >
                    </tbody>
                </table>
            </c:if>
            <c:if test="${libros.size() == 0}">
                <div class="alert center red lighten-4 red-text text-darken-4">
                    No hay libros registrados para mostrar!
                </div><br>
            </c:if>
        </main>
    </body>
</html>