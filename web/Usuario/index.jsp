<%-- 
    Document   : index
    Created on : 04-19-2018, 03:21:15 PM
    Author     : Frank
--%>

<%@ include file="/WEB-INF/jspf/control_sesion.jspf" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <title>[Thot] - Bibliotecario</title>
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
                                <img class="circle" src="">
                            </a>
                            <a>
                                <span class="white-text name">Jasson</span>
                            </a>
                            <a>
                                <span style="font-weight: bold;" class="white-text email">Usuario</span>
                            </a>
                            <a>
                                <span class="white-text email">micorreo@algo.com</span>
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
                                            <a href="">Ver Libros</a>
                                        </li>
                                        <li>
                                            <a href="">Ver Autores</a>
                                        </li>
                                        <li>
                                            <a href="">PAdministrar perfil</a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
            </header>
            
            <div class="Contenido">
                
            </div>
    </body>
</html>


