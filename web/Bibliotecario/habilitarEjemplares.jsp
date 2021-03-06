<%-- 
    Document   : habilitarEjemplares
    Created on : 04-22-2018, 07:56:25 PM
    Author     : Frank
--%>

<%@page import="sv.edu.udb.libreria.Libro"%>
<%@page import="sv.edu.udb.controladores.Libro_Controller"%>
<%@ include file="/WEB-INF/jspf/control_sesion.jspf" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<c:set scope="page" var="path" value="/Thot/Bibliotecario/"></c:set>

<c:if test="${param.idLibro == null}">
    <c:redirect url="libros.jsp">
        <c:set scope="session"  var="msg" value="Selecciona un libro para habilitar sus ejemplares.." />
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
        <script src="/Thot/js/Bibliotecario/habilitarEjemplares.js"></script>

        <title><fmt:message key="librarian.header.title"/></title>
    </head>
    <body>
        <form action="/Thot/Logout" name="frmLogout" method="POST"></form>        
        <header>
            <nav class="grey darken-4">
                <div class="container">
                    <a href="#" data-target="user_nav" class="sidenav-trigger "><i class="material-icons">menu</i></a>
                    <div class="nav-wrapper"><a class="brand-logo center"><fmt:message key="book.show.title"/></a></div>
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
                <li class="nav-item waves-effect"><a href="${path}historial.jsp"><fmt:message key="record"/> <i class="material-icons">history</i></a></li><li class="nav-item waves-effect"><a href="${path}libros.jsp"><fmt:message key="books"/> <i class="material-icons">book</i></a></li>
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
            
            <div class="section row">
            <c:set scope="page" value="${0}" var="_i"/>    
            <c:forEach items="${_l.getEjemplares()}" var="_e">
                <c:if test="${_e.getEstado() == 'PM'}">
                    <div class="row col s6">
                        <form action="" name="frmEjemplar" class="col s12">
                            <input type="hidden" name="idEjemplar" value="${_e.getIdEjemplar()}"/>
                            <h6 class="grey-text text-darken-4"><b><fmt:message key="copy"/>:</b> ${_e.getIdEjemplar()}</h6><br>
                            <div class="input-field">
                                <textarea name="txtObservaciones" id="txtObservaciones_${_i}" class="materialize-textarea" data-length="120"></textarea>
                                <label for="txtObservaciones_${_i}"><fmt:message key="copies.observations"/></label>
                            </div>
                        </form>
                    </div>
                    <c:set scope="page" value="${_i + 1}" var="_i"/>  
                </c:if>
            </c:forEach>
            <c:if test="${_i == 0}">
                <div class="alert center yellow yellow-text text-darken-4">
                    <fmt:message key="copies.notFound"/>
                </div>
            </c:if>

            <div class="fixed-action-btn">
                <a class="btn-floating btn-large grey darken-4 ${_i == 0 ? 'disabled' : ''}">
                    <i class="large material-icons">menu</i>
                </a>
                    
                <fmt:message key="copies.save" var="saveVar"/>
                <fmt:message key="copies.clean" var="cleanVar"/>
                    
                <ul>
                    <li><a id="btnLimpiar" ${_i == 0 ? 'disabled' : ''} title="${cleanVar}" class="btn-floating red"><i class="material-icons">cached</i></a></li>
                    <li><a id="btnGuardar" ${_i == 0 ? 'disabled' : ''} title="${saveVar}" class="btn-floating green"><i class="material-icons">done_all</i></a></li>
                </ul>
            </div>

            </div>
            <a href="${path}gestionEjemplares.jsp?idLibro=${_l.getIdLibro()}"><fmt:message key="return"/></a> |
            <a href="${path}verLibro.jsp?idLibro=${_l.getIdLibro()}"><fmt:message key="books.showBook"/></a>
            <br><br>
        </main>
    </body>
</html>

