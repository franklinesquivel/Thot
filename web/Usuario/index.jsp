<%-- 
    Document   : index
    Created on : 04-19-2018, 03:21:15 PM
    Author     : Frank
--%>

<%@ include file="/WEB-INF/jspf/control_sesion.jspf" %>
<%@page import="sv.edu.udb.libreria.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set scope="page" var="path" value="/Thot/Usuario/"></c:set>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <title><fmt:message key="user.header.title"/></title>
        <script src="/Thot/js/libros.js"></script>
        <script src="https://unpkg.com/imagesloaded@4/imagesloaded.pkgd.min.js"></script>
        <script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>  
    </head>
    <body>
        <form action="/Thot/Logout" name="frmLogout" method="POST"></form>        
        <header>

            <nav class="blue darken-1">
                <div class="container">
                    <a href="#" data-target="user_nav" class="sidenav-trigger "><i class="material-icons">menu</i></a>
                    <div class="nav-wrapper"><a class="brand-logo center"><fmt:message key="books"/></a></div>
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

                <li class="nav-item active waves-effect"><a href="${path}"><fmt:message key="books"/> <i class="material-icons">book</i></a></li>
                <li class="nav-item waves-effect"><a href="${path}prestamos.jsp"><fmt:message key="loans"/> <i class="material-icons">assignment</i></a></li>
                <li class="nav-item waves-effect"><a href="${path}reservas.jsp"><fmt:message key="reserves"/> <i class="material-icons">https</i></a></li>
                <li class="nav-item waves-effect">
                    <a onclick="frmLogout.submit();"><fmt:message key="logout"/><i class="material-icons">exit_to_app</i></a>
                </li>
            </ul>
        </header>

        <main class="container section">
            <div class="Contenido">
                <div class="col s6 offset-s3">
                    <div class="input-field col s8">
                        <input id="txtSearch" type="text">
                        <label for="txtSearch"><fmt:message key="catalog.search"/></label>
                    </div>
                    <div class="input-field col s2 center-align">
                        <a class="waves-effect  blue darken-1 btn modal-trigger" href="#mdlSearch"><fmt:message key="catalog.advanceSearch"/></a>
                    </div>
                </div>

                <div class="row col s6 offset-s3 red darken-4" id="result">

                </div>

                <div class="grid">
                    <div class="grid-sizer"></div>
                </div>

                <div id="mdlSearch" class="modal">
                    <div class="modal-content">
                        <h4 class="center-align"><fmt:message key="catalog.advanceSearch"/></h4>
                        <div class="row ">
                            <div class="input-field col s10 offset-s1">
                                <input id="category" class="txtSearch" type="text">
                                <label for="category"><fmt:message key="categorie"/></label>
                            </div>
                            <div class="input-field col s10 offset-s1">
                                <input id="printing" class="txtSearch" type="text">
                                <label for="printing"><fmt:message key="press"/></label>
                            </div>
                            <div class="input-field col s10 offset-s1">
                                <input id="author" class="txtSearch" type="text">
                                <label for="author"><fmt:message key="author"/></label>
                            </div>
                            <div class="input-field col s10 offset-s1">
                                <input id="subject" class="txtSearch" type="text">
                                <label for="subject"><fmt:message key="theme"/></label>
                            </div>
                            <div class="input-field col s10 offset-s1 center-align">
                                <button id="btnSearch" class="waves-effect  blue darken-1 btn"><fmt:message key="search"/></button>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <a href="#!" class="modal-action modal-close waves-effect waves-red btn-flat "><fmt:message key="close"/></a>
                    </div>
                </div>
            </div>
        </main>

        <div id="mdlReserve" class="modal">
            <div class="modal-content">
                <h4 class="center-align"><fmt:message key="catalog.advanceSearch.title"/></h4>
                <form id="frmReserve" name="frmReserve" class="row" action="">
                    <div class="input-field col s8 offset-s2">
                        <input id="fecha_vencimiento" name="fecha_vencimiento" type="date">
                        <label for="fecha_vencimiento"><fmt:message key="expireDate"/></label>
                    </div>
                    <input type="hidden" name="idLibro" id="idLibro">
                    <div class="input-field col s8 center-align offset-s2">
                        <button  class="waves-effect  blue darken-1 btn"><fmt:message key="reserve"/></button>
                    </div>
                </form>
                <div class="row" id="messageModal">
                    <h6 class="center-align"><fmt:message key="catalog.modal"/></h6>
                </div>
            </div>
            <div class="modal-footer">
                <a href="#!" class="modal-action modal-close waves-effect waves-red btn-flat "><fmt:message key="close"/></a>
            </div>
        </div>
    </body>
</html>


