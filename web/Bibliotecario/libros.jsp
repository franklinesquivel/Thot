<%-- 
    Document   : libros
    Created on : 04-22-2018, 02:06:37 PM
    Author     : Frank
--%>

<%@ include file="/WEB-INF/jspf/control_sesion.jspf" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>

<%@page import="sv.edu.udb.libreria.Libro"%>
<%@page import="java.util.List"%>
<%@page import="sv.edu.udb.controladores.Libro_Controller"%>

<c:set scope="page" var="path" value="/Thot/Bibliotecario/"></c:set>
<% pageContext.setAttribute("libros", Libro_Controller.obtenerLibros()); %>

<fmt:message key="books.showBook" var="showBookVar"/>
<fmt:message key="books.administerCopies" var="administerCopiesVar"/>
<fmt:message key="books.addLoan" var="addLoanVar"/>

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
                    <div class="nav-wrapper"><a class="brand-logo center"><fmt:message key="books"/></a></div>
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
                <li class="nav-item waves-effect"><a href="${path}reservas.jsp"><fmt:message key="reserves"/> <i class="material-icons">https</i></a></li>
                <li class="active nav-item waves-effect"><a href="${path}libros.jsp"><fmt:message key="books"/> <i class="material-icons">book</i></a></li>
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
            <c:if test="${sessionScope.msg != null}">
                <div class="alert center ${sessionScope.msg_type} lighten-4 ${sessionScope.msg_type}-text text-darken-4">
                    <fmt:message key="book.error.${sessionScope.msg}"/>
                </div><br>
                <c:remove scope="session" var="msg"/>
                <c:remove scope="session" var="msg_type"/>
            </c:if>
            <c:if test="${libros.size() > 0}">
                <table class="center" id="tblLibros">
                    <thead>
                        <th>idLibro</th>
                        <th><fmt:message key="book.title"/></th>
                        <th>ISBN</th>
                        <th><fmt:message key="edition"/></th>
                        <th><fmt:message key="actions"/></th>
                    </thead>
                    <tbody>
                        <c:forEach items="${libros}" var="_l">
                            <tr>
                                <td>${_l.getIdLibro()}</td>
                                <td>${_l.getTitulo()}</td>
                                <td>${_l.getIsbn()}</td>
                                <td>${_l.getEdicion()}</td>
                                <td>
                                    <a title="${showBookVar}" href="${path}verLibro.jsp?idLibro=${_l.getIdLibro()}" class="btn waves-effect grey darken-3 waves-light"><i class="material-icons">visibility</i></a>
                                    <a title="${administerCopiesVar}" href="${path}gestionEjemplares.jsp?idLibro=${_l.getIdLibro()}" class="btn waves-effect grey darken-3 waves-light"><i class="material-icons">chrome_reader_mode</i></a>
                                    <a ${_l.esProcesable() ? 'disabled' : ''} title="${addLoanVar}" href="${path}registrarPrestamo.jsp?idLibro=${_l.getIdLibro()}" class="btn waves-effect grey darken-3 waves-light"><i class="material-icons">assignment</i></a>
                                </td>
                            </tr>
                        </c:forEach >
                    </tbody>
                </table><br><br>
            </c:if>
            <c:if test="${libros.size() == 0}">
                <div class="alert center red lighten-4 red-text text-darken-4">
                        <fmt:message key="books.notFound"/>
                </div><br>
            </c:if>
        </main>
    </body>
</html>