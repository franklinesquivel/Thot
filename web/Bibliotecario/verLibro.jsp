<%-- 
    Document   : verLibro
    Created on : 04-22-2018, 04:55:52 PM
    Author     : Frank
--%>

<%@ include file="/WEB-INF/jspf/control_sesion.jspf" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>

<%@page import="sv.edu.udb.libreria.Libro"%>
<%@page import="sv.edu.udb.controladores.Libro_Controller"%>
<c:set scope="page" var="path" value="/Thot/Bibliotecario/"></c:set>

<c:if test="${param.idLibro == null}">
    <c:redirect url="libros.jsp">
        <c:set scope="session"  var="msg" value="showSelect" />
        <c:set scope="session" var="msg_type" value="yellow" />
    </c:redirect>
</c:if>
    
<% pageContext.setAttribute("_l", Libro_Controller.obtenerLibro(request.getParameter("idLibro"), true)); %>

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
                <div class="img col s12 m6 l6">
                    <img id="_img" src="/Thot/images/libros/${_l.getImagen()}" class="responsive-img materialboxed" data-caption="${_l.getTitulo()}">
                </div>
                <div class="img col s12 m6 l6">
                    <h5><fmt:message key="book.title"/></h5>
                    <p>${_l.getTitulo()}</p>
                    <hr>
                    <h5>ISBN</h5>
                    <p>${_l.getIsbn()}</p>
                    <hr>
                    <div class="row">
                        <div class="col m6 s12">
                            <h5><fmt:message key="edition"/></h5>
                            <span>${_l.getEdicion()}</span>
                        </div>
                        <div class="col m6 s12">
                            <h5><fmt:message key="categorie"/></h5>
                            <span>${_l.getCategoria().getNombre()}</span>
                        </div>                        
                    </div>
                    <hr>
                    <h5><fmt:message key="press"/></h5>
                    <p>${_l.getImprenta().getNombre()}</p>
                    <hr>
                    <h5><fmt:message key="authors"/></h5>
                    <p style="text-align: justify;">
                    <c:forEach items="${_l.getAutores()}" var="_a">
                        <li>${_a.getDisplayName()}</li>
                    </c:forEach>
                    </p>
                    <hr>
                    <h5><fmt:message key="books.copiesCant"/></h5>
                    <p>${_l.getCant_ejemplares()}</p>
                    <hr>
                    <h5><fmt:message key="themes"/></h5>
                    <p>
                    <c:forEach items="${_l.getTemas()}" var="_t">
                        <div class="chip">
                            ${_t.getDescripcion()}
                        </div>
                    </c:forEach>
                    </p>
                    <hr>
                </div>
                <div class="col s12">
                    <h5><fmt:message key="description"/></h5>
                    <p id="descripcion" style="text-align: justify;">${_l.getDescripcion()}</p>
                    <a id="btnLeer" class="deep-purple darken-4 btn waves-effect waves-light"><i class="material-icons left">graphic_eq</i>Leer</a>
                    <hr>
                </div>
                <div class="col s12">
                    <h5><fmt:message key="notes"/></h5>
                    <p id="descripcion" style="text-align: justify;">${_l.getNotas()}</p>
                    <hr>
                    <br><br>
                    <a href="${path}libros.jsp"><fmt:message key="return"/></a> | 
                    <a href="${path}gestionEjemplares.jsp?idLibro=${_l.getIdLibro()}"><fmt:message key="books.administerCopies"/></a>
                </div>
            </div>
        </main>
    </body>
</html>