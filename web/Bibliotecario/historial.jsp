<%-- 
    Document   : historial
    Created on : 05-08-2018, 05:34:09 PM
    Author     : Frank
--%>

<%@ include file="/WEB-INF/jspf/control_sesion.jspf" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>

<%@page import="sv.edu.udb.libreria.Libro"%>
<%@page import="java.util.List"%>
<%@page import="sv.edu.udb.controladores.Usuario_Controller"%>

<c:set scope="page" var="path" value="/Thot/Bibliotecario/"></c:set>
<% pageContext.setAttribute("_u", Usuario_Controller.obtenerUsuarios()); %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <link rel="stylesheet" href="/Thot/css/bibliotecario.css">
        <script src="/Thot/js/Bibliotecario/historial.js"></script>

        <title><fmt:message key="librarian.header.title"/></title>
    </head>
    <body>
        <form action="/Thot/Logout" name="frmLogout" method="POST"></form>
        <header>
            <nav class="grey darken-4">
                <div class="container">
                    <a href="#" data-target="user_nav" class="sidenav-trigger "><i class="material-icons">menu</i></a>
                    <div class="nav-wrapper"><a class="brand-logo center"><fmt:message key="record"/></a></div>
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
                <li class="active nav-item waves-effect"><a href="${path}historial.jsp"><fmt:message key="record"/> <i class="material-icons">history</i></a></li>
                <li>
                    <div class="divider"></div>
                </li>
                <li class="nav-item waves-effect">
                    <a onclick="frmLogout.submit();"><fmt:message key="logout"/> <i class="material-icons">exit_to_app</i></a>
                </li>
            </ul>
        </header>
        
        <main class="">
            <center>
                <div class="switch">
                    <label>
                        <fmt:message key="loans"/>
                        <input id="chkTipoProceso" type="checkbox">
                        <span class="lever"></span>
                        <fmt:message key="reserves"/>
                    </label>
                </div><br>
            </center>
            <div class="btn-cont">
                <a href="#mdlUsers" class="modal-trigger btn waves-effect waves-light grey darken-4"><fmt:message key="record.select.user"/> <i class="material-icons right">person_pin</i></a>
            </div>
            <table id="tblRecord" class="center">
                <thead>
                    <th>idProceso</th>
                    <th><fmt:message key="record.initDate"/></th>
                    <th><fmt:message key="record.limitDate"/></th>
                    <th><fmt:message key="state"/></th>
                </thead>
            </table>
        </main>

        <div id="mdlUsers" class="modal bottom-sheet">
            <div class="modal-content">
                <table class="center" id="tblUsers">
                    <thead>
                        <th>idUsuario</th>
                        <th><fmt:message key="user.fullname"/></th>
                        <th><fmt:message key="user.email"/></th>
                        <th><fmt:message key="user.select"/></th>
                    </thead>
                    <tbody>
                        <c:forEach items="${_u}" var="_user">
                            <c:if test="${_user.isEstado()}">
                                <c:set scope="page" var="_i" value="${_i + 1}"/>
                                <tr data="${_user.getIdUsuario()}">
                                    <td>${_user.getIdUsuario()}</td>
                                    <td>${_user.getNombre()} ${_user.getApellido()}</td>
                                    <td>${_user.getCorreo()}</td>
                                    <td>
                                        <a class="modal-close btn waves-effect waves-light grey darken-4 btnSelectUser" idUsuario="${_user.getIdUsuario()}"><fmt:message key="record.showRecord"/> <i class="material-icons right">history</i></a>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <a href="#!" class="modal-close waves-effect waves-red btn-flat"><fmt:message key="close"/></a>
            </div>
        </div>
    </body>
</html>
