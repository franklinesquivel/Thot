<%-- 
    Document   : gestionEjemplares
    Created on : 04-22-2018, 06:45:08 PM
    Author     : Frank
--%>

<%@page import="sv.edu.udb.libreria.Libro"%>
<%@page import="sv.edu.udb.controladores.Libro_Controller"%>
<%@ include file="/WEB-INF/jspf/control_sesion.jspf" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<c:set scope="page" var="path" value="/Thot/Bibliotecario/"></c:set>

<c:if test="${param.idLibro == null}">
    <c:redirect url="libros.jsp">
        <c:set scope="session"  var="msg" value="Selecciona un libro para ver sus ejemplares..." />
        <c:set scope="session" var="msg_type" value="yellow" />
    </c:redirect>
</c:if>

<% pageContext.setAttribute("_l", Libro_Controller.obtenerLibro(request.getParameter("idLibro"), true));%>

<c:if test="${_l == null}">
    <c:redirect url="libros.jsp">
        <c:set scope="session"  var="msg" value="notFound" />
        <c:set scope="session" var="msg_type" value="yellow" />
    </c:redirect>
</c:if>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <link rel="stylesheet" href="/Thot/css/bibliotecario.css">
        <script src="/Thot/js/Bibliotecario/gestionEjemplares.js"></script>

        <title><fmt:message key="librarian.header.title"/></title>
    </head>
    <body>
        <form action="/Thot/Logout" name="frmLogout" method="POST"></form>        
        <header>
            <nav class="grey darken-4">
                <div class="container">
                    <a href="#" data-target="user_nav" class="sidenav-trigger "><i class="material-icons">menu</i></a>
                    <div class="nav-wrapper"><a class="brand-logo center"><fmt:message key="books.administerCopies"/></a></div>
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
            <h5 class="center grey-text text-darken-4"><b><fmt:message key="book"/>:</b> ${_l.getTitulo()}</h5><br>
            <div class="btn-cont">
                <a href="#mdlAumentarEjemplares" class="modal-trigger btn grey darken-4 waves-effect waves-light"><i class="material-icons right">add_circle</i> <fmt:message key="copies.add"/></a>
                <a href="${path}habilitarEjemplares.jsp?idLibro=${_l.getIdLibro()}" class="btn grey darken-4 waves-effect waves-light"><i class="material-icons right">thumb_up</i> <fmt:message key="copies.enable"/></a>
            </div>
            <table name="tblEjemplares" id="tblEjemplares" class="center" style="margin-top: 2%;">
                <thead>
                    <th><fmt:message key="code"/></th>
                    <th><fmt:message key="state"/></th>
                    <th><fmt:message key="copies.observations"/></th>
                </thead>
                <tbody>
                <c:forEach items="${_l.getEjemplares()}" var="_e">
                    <tr>
                        <td>${_e.getIdEjemplar()}</td>
                        <td><fmt:message key="state.${_e.getEstado()}"/></td>
                        <td>${_e.getObservaciones()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br>
            <a href="${path}libros.jsp"><fmt:message key="books"/></a> | 
            <a href="${path}verLibro.jsp?idLibro=${_l.getIdLibro()}"><fmt:message key="books.showBook"/></a> |
        </main>
        
        
        <div id="mdlAumentarEjemplares" class="modal">
            <div class="modal-content section">
                <h5 class="grey-text text-darken-4 center"><fmt:message key="copies.add.title"/></h5>
                <form name="frmAdd" class="row section">
                    <input type="hidden" name="idLibro" value="${_l.getIdLibro()}">
                    <div class="input-field col m8 offset-m2 s10 offset-s1">
                        <input type="text" name="txtCant" id="txtCant" />
                        <label for="txtCant"><fmt:message key="copies.add.cant"/></label>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a class="modal-action modal-close waves-effect waves-light red btn"><fmt:message key="cancel"/> <i class="material-icons right">cancel</i></a>
                <a id="btnAdd" class="waves-effect waves-light green btn"><fmt:message key="confirm"/> <i class="material-icons right">done</i></a>
            </div>
        </div>
    </body>
</html>
