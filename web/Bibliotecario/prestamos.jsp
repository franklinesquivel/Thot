<%-- 
    Document   : prestamos.jsp
    Created on : 04-24-2018, 07:28:39 PM
    Author     : Frank
--%>

<%@ include file="/WEB-INF/jspf/control_sesion.jspf" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>

<%@page import="sv.edu.udb.controladores.Prestamo_Controller"%>
<%@page import="sv.edu.udb.libreria.Prestamo"%>
<%@page import="java.util.List"%>

<c:set scope="page" var="path" value="/Thot/Bibliotecario/"></c:set>
<% pageContext.setAttribute("prestamos", Prestamo_Controller.obtenerPrestamos(true)); %>

<fmt:message key="loans.genBill" var="genBillVar"/>
<fmt:message key="loans.showLoan" var="showLoanVar"/>
<fmt:message key="loans.endLoan" var="endLoanVar"/>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <link rel="stylesheet" href="/Thot/css/bibliotecario.css">
        <script src="/Thot/js/Bibliotecario/prestamos.js"></script>

        <title><fmt:message key="librarian.header.title"/></title>
    </head>
    <body>
        <form action="/Thot/Logout" name="frmLogout" method="POST"></form>
        <header>
                <nav class="grey darken-4">
                    <div class="container">
                        <a href="#" data-target="user_nav" class="sidenav-trigger "><i class="material-icons">menu</i></a>
                        <div class="nav-wrapper"><a class="brand-logo center"><fmt:message key="loans"/></a></div>
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
                    <li class="active nav-item waves-effect"><a href="${path}prestamos.jsp"><fmt:message key="loans"/> <i class="material-icons">assignment</i></a></li>
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
            <c:if test="${sessionScope.msg != null}">
                <div class="alert center ${sessionScope.msg_type} lighten-4 ${sessionScope.msg_type}-text text-darken-4">
                    <fmt:message key="loans.error.${sessionScope.msg}"/>
                </div><br>
                <c:remove scope="session" var="msg"/>
                <c:remove scope="session" var="msg_type"/>
            </c:if>
            <c:if test="${prestamos.size() > 0}">
                <form name="frmFactura" action="/Thot/Prestamos/Factura" method="POST">
                    <input type="hidden" name="idPrestamo" />
                </form>
                <table class="center" id="tblPrestamos">
                    <thead>
                        <th>idPrestamo</th>
                        <th><fmt:message key="user"/></th>
                        <th><fmt:message key="expireDate"/></th>
                        <th><fmt:message key="state"/></th>
                        <th><fmt:message key="amount"/></th>
                        <th><fmt:message key="actions"/></th>
                    </thead>
                    <tbody>
                        <c:forEach items="${prestamos}" var="_p">
                            <tr data="${_p.getIdPrestamo()}" class="lighten-5  text-darken-5 ${_p.getEstado().equals("VO") ? 'red red-text' : _p.getEstado().equals("FO") ? 'grey grey-text' : ''}">
                                <td>${_p.getIdPrestamo()}</td>
                                <td>${_p.getUsuario().getDisplayName()}</td>
                                <td>${_p.getFechaDevolucionFormato()}</td>
                                <td><fmt:message key="state.${_p.getEstado()}"/></td>
                                <td>$${_p.getMoraDecimales(2)}</td>
                                <td>
                                    <a title="${showLoanVar}" href="${path}verPrestamo.jsp?idPrestamo=${_p.getIdPrestamo()}" class="btn waves-effect grey darken-3 waves-light"><i class="material-icons">visibility</i></a>
                                    <c:if test="${_p.getEstado() == 'FO'}">
                                        <a idPrestamo="${_p.getIdPrestamo()}" title="${genBillVar}" class="btnFactura btn waves-effect grey darken-3 waves-light"><i class="material-icons">receipt</i></a>
                                    </c:if>
                                    <c:if test="${_p.getEstado() != 'FO'}">
                                        <a idPrestamo="${_p.getIdPrestamo()}" title="${endLoanVar}" href="#mdlFinalizar" class="modal-trigger btnFinalizar btn waves-effect grey darken-3 waves-light"><i class="material-icons">local_library</i></a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach >
                    </tbody>
                </table>
                <form action="/Thot/Prestamos/Reporte" method="POST">
                    <button class="btn btn grey darken-4 waves-effect waves-light"><fmt:message key="loans.report"/> <i class="material-icons right">file_download</i></button>
                    <br><br>
                </form>
            </c:if>
            <c:if test="${prestamos.size() == 0}">
                <div class="alert center red lighten-4 red-text text-darken-4">
                        <fmt:message key="loans.noFound"/>
                </div><br>
            </c:if>
        </main>
                                
        <div id="mdlFinalizar" class="modal">
            <div class="modal-content section">
                <h5 class="grey-text text-darken-4 center"><fmt:message key="loans.finish.title"/></h5>
                <div id="info-cont"></div><br>
                <div class="btn-cont">
                    <a class="modal-action modal-close btn waves-effect waves-light red"><fmt:message key="cancel"/> <i class="material-icons right">cancel</i></a>
                    <a id="btnConfirmFinish" class="btn waves-effect waves-light green"><fmt:message key="confirm"/> <i class="material-icons right">check</i></a>
                </div>
            </div>
        </div>
    </body>
</html>