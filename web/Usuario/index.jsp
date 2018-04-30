<%-- 
    Document   : index
    Created on : 04-19-2018, 03:21:15 PM
    Author     : Frank
--%>

<%@ include file="/WEB-INF/jspf/control_sesion.jspf" %>
<%@page import="sv.edu.udb.libreria.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <title>[Thot] - Usuario</title>
        <script src="/Thot/js/libros.js"></script>
        <script src="https://unpkg.com/imagesloaded@4/imagesloaded.pkgd.min.js"></script>
        <script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>  
    </head>
    <body>

            <header>

                <nav class="teal darken-1">
                    <div class="container">
                        <a href="#" data-target="user_nav" class="sidenav-trigger "><i class="material-icons">menu</i></a>
                        <div class="nav-wrapper"><a class="brand-logo center">Usuario</a></div>
                    </div>
                </nav>

                <ul id="slide-out" class="sidenav sidenav-fixed">
                    <li>
                        <div class="user-view">
                            <div class="background teal darken-1">
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

                    <li class="no-padding">
                        <ul class="collapsible collapsible-accordion">
                            <li>
                                <a class="collapsible-header"> <i class="material-icons"></i>Libros</a>
                                <div class="collapsible-body">
                                    <ul>
                                        <li>
                                            <a href="prestamos.jsp">Mis Prestamos</a>
                                        </li>
                                        <li>
                                            <a href="reservas.jsp">Mis Reservaciones</a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
            </header>
            
            <main class="container">
                <div class="Contenido">
                    <div class="col s6 offset-s3">
                        <div class="input-field col s8">
                            <input id="txtSearch" type="text">
                            <label for="txtSearch">Buscar [Título, Descripción, ISBN]</label>
                        </div>
                        <div class="input-field col s2 center-align">
                            <a class="waves-effect waves-light btn modal-trigger" href="#mdlSearch">Búsqueda Avanzada</a>
                        </div>
                    </div>

                    <div class="row col s6 offset-s3 red darken-4" id="result">

                    </div>

                    <div class="grid">
                        <div class="grid-sizer"></div>
                    </div>

                    <div id="mdlSearch" class="modal">
                        <div class="modal-content">
                            <h4 class="center-align">Búsqueda avanzada</h4>
                            <div class="row ">
                                <div class="input-field col s10 offset-s1">
                                    <input id="category" class="txtSearch" type="text">
                                    <label for="category">Categoria</label>
                                </div>
                                <div class="input-field col s10 offset-s1">
                                    <input id="printing" class="txtSearch" type="text">
                                    <label for="printing">Imprenta</label>
                                </div>
                                <div class="input-field col s10 offset-s1">
                                    <input id="author" class="txtSearch" type="text">
                                    <label for="author">Autor</label>
                                </div>
                                <div class="input-field col s10 offset-s1">
                                    <input id="subject" class="txtSearch" type="text">
                                    <label for="subject">Tema</label>
                                </div>
                                <div class="input-field col s10 offset-s1 center-align">
                                    <button id="btnSearch" class="waves-effect waves-light btn">Buscar</button>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <a href="#!" class="modal-action modal-close waves-effect waves-red btn-flat ">Cerrar</a>
                        </div>
                    </div>
                    
                    <div id="mdlReserve" class="modal">
                        <div class="modal-content">
                            <h4 class="center-align">Reserva de Libro</h4>
                            <form id="frmReserve" name="frmReserve" class="row" action="">
                                <div class="input-field col s8 offset-s2">
                                    <input id="fecha_vencimiento" name="fecha_vencimiento" type="date">
                                    <label for="fecha_vencimiento">Fecha de Vencimiento</label>
                                </div>
                                <input type="hidden" name="idLibro" id="idLibro">
                                <div class="input-field col s8 center-align offset-s2">
                                    <button  class="waves-effect waves-light btn">Reservar</button>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <a href="#!" class="modal-action modal-close waves-effect waves-red btn-flat ">Cerrar</a>
                        </div>
                    </div>
                </div>
            </main>
    </body>
</html>


