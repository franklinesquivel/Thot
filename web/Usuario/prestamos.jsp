<%-- 
    Document   : prestamos
    Created on : 04-29-2018, 06:45:57 PM
    Author     : Leonardo
--%>
<%@ include file="/WEB-INF/jspf/control_sesion.jspf" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@page import="sv.edu.udb.controladores.Prestamo_Controller"%>

<c:set scope="page" var="path" value="/Thot/Usuario/"></c:set>
<%  
    Usuario _e = (Usuario) session.getAttribute("userData");
    pageContext.setAttribute("prestamos", Prestamo_Controller.obtenerPrestamos(true, _e.getIdUsuario()));
%>
  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <script src="../js/Usuario/prestamo.js"></script>
        <title><fmt:message key="user.header.title"/></title>
    </head>
    <body>
        <form action="/Thot/Logout" name="frmLogout" method="POST"></form>        
        <header>

            <nav class="blue darken-1">
                <div class="container">
                    <a href="#" data-target="user_nav" class="sidenav-trigger "><i class="material-icons">menu</i></a>
                    <div class="nav-wrapper"><a class="brand-logo center"><fmt:message key="loans"/></a></div>
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

                <li class="nav-item waves-effect"><a href="${path}"><fmt:message key="books"/> <i class="material-icons">book</i></a></li>
                <li class="nav-item active waves-effect"><a href="${path}prestamos.jsp"><fmt:message key="loans"/> <i class="material-icons">assignment</i></a></li>
                <li class="nav-item waves-effect"><a href="${path}reservas.jsp"><fmt:message key="reserves"/> <i class="material-icons">https</i></a></li>
                <li class="nav-item waves-effect">
                    <a onclick="frmLogout.submit();"><fmt:message key="logout"/> <i class="material-icons">exit_to_app</i></a>
                </li>
            </ul>
        </header>
                        
        <main class="">
            <br>
            <c:if test="${prestamos.size() > 0}">
                <table class="center" id="tblPrestamos">
                    <thead>
                        <th><fmt:message key="book"/></th>
                        <th><fmt:message key="loans.loanDate"/></th>
                        <th><fmt:message key="loans.finishDate"/></th>
                        <th><fmt:message key="amount"/></th>
                        <th><fmt:message key="state"/></th>
                        <th>
                            <fmt:message key="actions"/>
                        </th>
                    </thead>
                    <tbody>
                        <c:forEach items="${prestamos}" var="_p">
                            <tr  class="lighten-5  text-darken-5 ${_p.getEstado().equals("VO") ? 'red red-text' : _p.getEstado().equals("FO") ? 'grey grey-text' : ''}">
                                <td>${_p.getEjemplar().getLibro().getTitulo()}</td>
                                <td>${_p.getFechaPrestamoFormato()}</td>
                                <td>${_p.getFechaDevolucionFormato()}</td>
                                <td>$ ${_p.getMoraDecimales(2)}</td>
                                <td>${_p.getDisplayEstado()}</td>
                                <td>
                                    <c:if test="${_p.getEstado() != 'FO'}">
                                        <a class="waves-effect waves-light btn modal-trigger btnRenovar" href="#mdlRenovar" idprestamo="${_p.getIdPrestamo()}"><fmt:message key="renovate"/></a>
                                    </c:if>
                                    <c:if test="${_p.getEstado() == 'FO'}">
                                        <fmt:message key="state.FO"/>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach >
                    </tbody>
                </table>
            </c:if>
            <c:if test="${prestamos.size() == 0}">
                <div class="alert center red lighten-4 red-text text-darken-4">
                    <fmt:message key="loans.notFound"/>
                </div><br>
            </c:if>
        </main>
        <div id="mdlRenovar" class="modal">
            <div class="modal-content center-align">
                <h4><fmt:message key="renovate"/></h4>
                <p><fmt:message key="loans.renovate.title"/></p>
                <input type="hidden" name="idPrestamo" value="null" id="idPrestamo">
            </div>
            <div class="modal-footer">
                <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat" id="btnRenovar"><fmt:message key="renovate"/></a>
                <a href="#!" class="modal-action modal-close waves-effect waves-red btn-flat"><fmt:message key="close"/></a>
            </div>
        </div>
    </body>
</html>
