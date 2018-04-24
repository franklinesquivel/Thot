<%-- 
    Document   : gestionEjemplares
    Created on : 04-22-2018, 06:45:08 PM
    Author     : Frank
--%>

<%@page import="sv.edu.udb.libreria.Libro"%>
<%@page import="sv.edu.udb.controladores.Libro_Controller"%>
<%@ include file="/WEB-INF/jspf/control_sesion.jspf" %>
<c:set scope="page" var="path" value="/Thot/Bibliotecario/"></c:set>

<c:if test="${param.idLibro == null}">
    <c:redirect url="libros.jsp">
        <c:set scope="session"  var="msg" value="Selecciona un libro para ver sus ejemplares.." />
        <c:set scope="session" var="msg_type" value="yellow" />
    </c:redirect>
</c:if>

<% pageContext.setAttribute("_l", Libro_Controller.obtenerLibro(request.getParameter("idLibro"), true));%>

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
        <script src="/Thot/js/Bibliotecario/gestionEjemplares.js"></script>

        <title>[Thot] - Bibliotecario</title>
    </head>
    <body>
        <form action="/Thot/Logout" name="frmLogout" method="POST"></form>        
        <header>
            <nav class="grey darken-4">
                <div class="container">
                    <a href="#" data-target="user_nav" class="sidenav-trigger "><i class="material-icons">menu</i></a>
                    <div class="nav-wrapper"><a class="brand-logo center">Ejemplares</a></div>
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
            <h5 class="center grey-text text-darken-4"><b>Libro:</b> ${_l.getTitulo()}</h5><br>
            <div class="btn-cont">
                <a href="#mdlAumentarEjemplares" class="modal-trigger btn grey darken-4 waves-effect waves-light"><i class="material-icons right">add_circle</i>Añadir ejemplares</a>
                <a href="${path}habilitarEjemplares.jsp?idLibro=${_l.getIdLibro()}" class="btn grey darken-4 waves-effect waves-light"><i class="material-icons right">thumb_up</i> Habilitar Ejemplares</a>
            </div>
            <table name="tblEjemplares" id="tblEjemplares" class="center" style="margin-top: 2%;">
                <thead>
                    <th>Código</th>
                    <th>Estado</th>
                    <th>Observaciones</th>
                    <th>Acciones</th>
                </thead>
                <tbody>
                <c:forEach items="${_l.getEjemplares()}" var="_e">
                    <tr>
                        <td>${_e.getIdEjemplar()}</td>
                        <td>
                            <c:choose>
                                <c:when test="${_e.getEstado() == 'PM'}">Modificación pendiente</c:when>
                                <c:when test="${_e.getEstado() == 'D'}">Disponible</c:when>
                                <c:when test="${_e.getEstado() == 'R'}">Reservado</c:when>
                                <c:when test="${_e.getEstado() == 'P'}">Prestado</c:when>
                            </c:choose>
                        </td>
                        <td>${_e.getObservaciones()}</td>
                        <td>
                            <a href="">Hola</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br>
            <a href="${path}libros.jsp">Libros</a> | 
            <a href="${path}verLibro.jsp?idLibro=${_l.getIdLibro()}">Ver libro</a> |
            <a href="${path}editarLibro.jsp?idLibro=${_l.getIdLibro()}">Editar libro</a>
        </main>
        
        
        <div id="mdlAumentarEjemplares" class="modal">
            <div class="modal-content row">
                <h4>Ingrese la cantidad de existencias que desea añadir</h4>
                <form name="frmAdd">
                    <div>
                        <input type="text" name="txtCant" id="txtCant" />
                        <label for="txtCant">Cantidad a agregar</label>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a class="modal-action modal-close waves-effect waves-light red btn">Cancelar <i class="material-icons right">cancel</i></a>
                <a id="btnAdd" class="waves-effect waves-light green btn">Agregar <i class="material-icons right">done</i></a>
            </div>
        </div>
    </body>
</html>
