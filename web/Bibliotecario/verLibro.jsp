<%-- 
    Document   : verLibro
    Created on : 04-22-2018, 04:55:52 PM
    Author     : Frank
--%>

<%@ include file="/WEB-INF/jspf/control_sesion.jspf" %>

<%@page import="sv.edu.udb.libreria.Libro"%>
<%@page import="sv.edu.udb.controladores.Libro_Controller"%>
<c:set scope="page" var="path" value="/Thot/Bibliotecario/"></c:set>

<c:if test="${param.idLibro == null}">
    <c:redirect url="libros.jsp">
        <c:set scope="session"  var="msg" value="Selecciona un libro para visualizar.." />
        <c:set scope="session" var="msg_type" value="yellow" />
    </c:redirect>
</c:if>
    
<%
    pageContext.setAttribute("_l", Libro_Controller.obtenerLibro(request.getParameter("idLibro"), true));
%>

<c:if test="${_l == null}">
    <c:redirect url="libros.jsp">
        <c:set scope="session"  var="msg" value="El libro que deseas visualizar no existe..." />
        <c:set scope="session" var="msg_type" value="yellow" />
    </c:redirect>
</c:if>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <link rel="stylesheet" href="/Thot/css/bibliotecario.css">
        <title>[Thot] - Bibliotecario</title>
    </head>
    <body>
        <form action="/Thot/Logout" name="frmLogout" method="POST"></form>        
        <header>
            <nav class="grey darken-4">
                <div class="container">
                    <a href="#" data-target="user_nav" class="sidenav-trigger "><i class="material-icons">menu</i></a>
                    <div class="nav-wrapper"><a class="brand-logo center">Libro</a></div>
                    </div>
                </nav>

                <ul id="user_nav" class="sidenav sidenav-fixed">
                    <li>
                        <div class="user-view">
                            <div class="background grey darken-4">
                            </div>
                            <a>
                                <img class="circle" src="/favicon.png">
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
                        <li>
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
            <div class="row">
                <div class="img col s12 m6 l6">
                    <img id="_img" src="/Thot/images/libros/${_l.getImagen()}" class="responsive-img materialboxed" data-caption="${_l.getTitulo()}">
                </div>
                <div class="img col s12 m6 l6">
                    <h5>Título</h5>
                    <p>${_l.getTitulo()}</p>
                    <hr>
                    <h5>ISBN</h5>
                    <p>${_l.getIsbn()}</p>
                    <hr>
                    <div class="row">
                        <div class="col m6 s12">
                            <h5>Edición</h5>
                            <span>${_l.getEdicion()}</span>
                        </div>
                        <div class="col m6 s12">
                            <h5>Categoría</h5>
                            <span>${_l.getCategoria().getNombre()}</span>
                        </div>                        
                    </div>
                    <hr>
                    <h5>Imprenta</h5>
                    <p>${_l.getImprenta().getNombre()}</p>
                    <hr>
                    <h5>Autores</h5>
                    <p style="text-align: justify;">
                    <c:forEach items="${_l.getAutores()}" var="_a">
                        <li>${_a.getDisplayName()}</li>
                    </c:forEach>
                    </p>
                    <hr>
                    <h5>Cantidad de ejemplares</h5>
                    <p>${_l.getCant_ejemplares()}</p>
                    <hr>
                    <h5>Temas</h5>
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
                    <h5>Descripción</h5>
                    <p id="descripcion" style="text-align: justify;">${_l.getDescripcion()}</p>
                    <a id="btnLeer" class="deep-purple darken-4 btn waves-effect waves-light"><i class="material-icons left">graphic_eq</i>Leer</a>
                    <hr>
                </div>
                <div class="col s12">
                    <h5>Notas</h5>
                    <p id="descripcion" style="text-align: justify;">${_l.getNotas()}</p>
                    <hr>
                    <br><br>
                    <a href="${path}libros.jsp">Regresar</a> | 
                    <a href="${path}editarLibro.jsp?idLibro=${_l.getIdLibro()}">Editar</a> | 
                    <a href="${path}gestionEjemplares.jsp?idLibro=${_l.getIdLibro()}">Administrar ejemplares</a>
                </div>
            </div>
        </main>
    </body>
</html>