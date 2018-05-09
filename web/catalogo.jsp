<%-- 
    Document   : libros
    Created on : 04-23-2018, 10:59:22 PM
    Author     : Leonardo
--%>
<%@page import="sv.edu.udb.libreria.Usuario"%>
<%@page import="java.util.Arrays"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>

<c:if test="${sessionScope.logged == true}">
    <c:if test="${sessionScope.user == null}">
        <c:set var="user" scope="session" value="${userData}"></c:set>
    </c:if>
    
    <% 
        Usuario _u = (Usuario) session.getAttribute("userData");
        String[] actualView = request.getRequestURI().split("/");
        
        if(_u != null){
            if (!Arrays.asList(actualView).contains(_u.getTipoUsuario().equals("B") ? "Bibliotecario" : "Usuario")) {
                response.sendRedirect("/Thot/" + (_u.getTipoUsuario().equals("B") ? "Bibliotecario" : "Usuario") + "/");
            }
        }else{
            session.setAttribute("logged", false);
            session.setAttribute("userData", false);
            response.sendRedirect("/Thot/login.jsp");
        }
        
    %>
</c:if>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />

        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        
        <link rel="stylesheet" href="/Thot/css/materialize.min.css">
        <link rel="stylesheet" href="/Thot/css/main.css">
        
        <script src="/Thot/js/jquery.js"></script>
        <script src="/Thot/js/jquery.validate.min.js"></script>
        <script src="/Thot/js/materialize.min.js"></script>
        <script src="/Thot/js/Loader.js"></script>
        <script src="/Thot/js/init.js"></script>
        
        <script src="/Thot/js/libros.js"></script>
        <script src="https://unpkg.com/imagesloaded@4/imagesloaded.pkgd.min.js"></script>
        <script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>   
        
        <title><fmt:message key="catalog.header.title"/></title>
    </head>
    <body>
        
        <nav class="blue darken-2" id="">
            <div class="nav-wrapper">
                
                <ul class="right hide-on-med-and-down" id="opc">    
                    <li><a href="/Thot/"><fmt:message key="catalog.index"/> <i class="material-icons right">arrow_back</i></a></li> 
                    <li><a href="login.jsp"><fmt:message key="login"/> <i class="material-icons right">person_pin</i></a></li>
                </ul>
            </div>
        </nav>
        <br>
        <div class="container section">
            <div class="col s6 offset-s3">
                <div class="input-field col s8">
                    <input id="txtSearch" type="text">
                    <label for="txtSearch"><fmt:message key="catalog.search"/></label>
                </div>
                <div class="input-field col s2 center-align">
                    <a class="blue darken-2 waves-effect waves-light btn modal-trigger" href="#mdlSearch"><fmt:message key="catalog.advanceSearch"/></a>
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
                            <button id="btnSearch" class="blue darken-2 waves-effect waves-light btn"><fmt:message key="search"/></button>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <a href="#!" class="modal-action modal-close waves-effect waves-red btn-flat "><fmt:message key="close"/></a>
                </div>
            </div>
        </div>
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
                        <button  class="blue darken-2 waves-effect waves-light btn"><fmt:message key="reserve"/></button>
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
