<%-- 
    Document   : registrarPrestamo
    Created on : 04-24-2018, 10:24:14 AM
    Author     : Leonardo
--%>

<%@page import="sv.edu.udb.utilidades.Fechas"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="sv.edu.udb.controladores.Usuario_Controller"%>
<%@page import="sv.edu.udb.controladores.Ejemplar_Controller"%>
<%@ include file="/WEB-INF/jspf/control_sesion.jspf" %>

<%@page import="sv.edu.udb.libreria.Libro"%>
<%@page import="sv.edu.udb.controladores.Libro_Controller"%>
<c:set scope="page" var="path" value="/Thot/Bibliotecario/"></c:set>

<c:if test="${param.idLibro == null}">
    <c:redirect url="libros.jsp">
        <c:set scope="session"  var="msg" value="Selecciona un libro para registrar un préstamo" />
        <c:set scope="session" var="msg_type" value="yellow" />
    </c:redirect>
</c:if>

<%
    Libro __l = Libro_Controller.obtenerLibro(request.getParameter("idLibro"), true);
    List<Usuario> __u = Usuario_Controller.BuscarUsuarios("tipoUsuario", "U");
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    pageContext.setAttribute("_l", __l);
    pageContext.setAttribute("_e", Ejemplar_Controller.obtenerEjemplarParaProceso(__l));
    pageContext.setAttribute("_u", __u);
%>

<c:if test="${_l == null}">
    <c:redirect url="libros.jsp">
        <c:set scope="session"  var="msg" value="El libro que solicitas prestar no existe..." />
        <c:set scope="session" var="msg_type" value="yellow" />
    </c:redirect>
</c:if>

<c:if test="${_l != null}">
    <c:if test="${_e == null}">
        <c:redirect url="libros.jsp">
            <c:set scope="session"  var="msg" value="El libro que solicitas prestar no posee ejemplares en el estado óptimo (Disponible) para realizar un préstamo..." />
            <c:set scope="session" var="msg_type" value="yellow" />
        </c:redirect>
    </c:if>
</c:if>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <link rel="stylesheet" href="/Thot/css/bibliotecario.css">
        <script src="/Thot/js/Bibliotecario/registrarPrestamo.js"></script>
        <title>[Thot] - Bibliotecario</title>
    </head>
    <body>
        <form action="/Thot/Logout" name="frmLogout" method="POST"></form>        
        <header>
            <nav class="grey darken-4">
                <div class="container">
                    <a href="#" data-target="user_nav" class="sidenav-trigger "><i class="material-icons">menu</i></a>
                    <div class="nav-wrapper"><a class="brand-logo center">Registrar Préstamo</a></div>
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

        <main class="row">
            <div class="col s12">
                <h6><b>Libro a prestar: </b>${_l.getTitulo()}</h6>
                <h6><b>Ejemplar asignado: </b>${_e.getIdEjemplar()}</h6>
                <h6><b>Observaciones del ejemplar: </b></h6>
                <p style="text-align: justify;">${_e.getObservaciones()}</p>
                <hr><br><br>
            </div>
            <h5 class="center"><b>Selecciona el usuario al que se le asignará el préstamo</b></h5><br>
            <div class="col s10 offset-s1 ">
                <c:if test="${_u.size() > 0}">
                    <table class="center" id="tblUsers">
                        <thead>
                        <th>Nombre completo</th>
                        <th>Correo electrónico</th>
                        <th>Seleccionar</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${_u}" var="_user">
                                <tr data="${_user.getIdUsuario()}">
                                    <td>${_user.getNombre()} ${_user.getApellido()}</td>
                                    <td>${_user.getCorreo()}</td>
                                    <td>
                                        <label>
                                            <input class="with-gap" value="${_user.getIdUsuario()}" name="rdbUser" type="radio"  />
                                            <span></span>
                                        </label>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="col s12 btn-cont">
                        <a href="#mdlConfirm" disabled class="modal-trigger btn-large waves-effect waves-light grey darken-4" id="btnConfirm">Siguiente <i class="material-icons right">check</i></a>
                    </div>
                </c:if>
                <c:if test="${_u.size() == 0}">
                    <div class="alert yellow yellow-text text-darken-4 center">
                        No hay usuarios disponibles para registrar un préstamo
                    </div>
                </c:if>
            </div>
        </main>
                
        <div class="modal" id="mdlConfirm">
            <div class="modal-content row">
                <h5 class="center modal-title">Confirmar préstamo</h5>
                <form name="frmPrestamo" class="col s10 offset-s1">
                    <input type="hidden" name="idUsuario" id="idUsuario">
                    <input type="hidden" name="idEjemplar" value="${_e.getIdEjemplar()}">
                    <div><b>Usuario</b>: <span id="userDataCont"></span></div>
                    <div><b>Libro</b>: <span>${_l.getTitulo()}</span></div>
                    <div><b>Ejemplar</b>: <span>${_e.getIdEjemplar()}</span></div><br>
                    <div class="input-field">
                        <input type="date" name="txtFin" id="txtFin" value="<%= format.format(Fechas.sumarDias(new Date(), 1)) %>" class="">
                        <label for="txtFin">Fecha de fin del préstamo</label>
                    </div>
                    <br>
                    <div class="btn-cont">
                        <a class="modal-action modal-close waves-effect waves-light red btn">Cancelar <i class="material-icons right">cancel</i></a>
                        <button class="waves-effect waves-light green btn">Confirmar <i class="material-icons right">done</i></button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>