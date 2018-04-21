<%-- 
    Document   : index
    Created on : 04-19-2018, 03:21:39 PM
    Author     : Frank
--%>

<%@ include file="/WEB-INF/jspf/control_sesion.jspf" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <title>[Thot] - Usuario</title>
    </head>
    <body>
            <header>

                <nav class="teal darken-1">
                    <div class="container">
                        <a href="#" data-target="user_nav" class="sidenav-trigger "><i class="material-icons">menu</i></a>
                        <div class="nav-wrapper"><a class="brand-logo center">Biblotecario</a></div>
                    </div>
                </nav>

                <ul id="slide-out" class="sidenav sidenav-fixed">
                    <li>
                        <div class="user-view">
                            <div class="background teal darken-1">
                            </div>
                            <a>
                                <img class="circle" src="{{ asset('favicon.png')  }}">
                            </a>
                            <a>
                                <span class="white-text name">Jasson</span>
                            </a>
                            <a>
                                <span style="font-weight: bold;" class="white-text email">Biblotecario</span>
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
                                            <a href="">Mostrar</a>
                                        </li>
                                        <li>
                                            <a href="">Registrar</a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <li class="no-padding">
                        <ul class="collapsible collapsible-accordion">
                            <li>
                                <a class="collapsible-header"><i class="material-icons"></i>Autores</a>
                                <div class="collapsible-body">
                                    <ul>
                                        <li>
                                            <a href="">Mostrar</a>
                                        </li>
                                        <li>
                                            <a href="">Registrar</a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <li class="no-padding">
                        <ul class="collapsible collapsible-accordion">
                            <li>
                                <a class="collapsible-header"><i class="material-icons"></i>Temas</a>
                                <div class="collapsible-body">
                                    <ul>
                                        <li>
                                            <a href="">Mostrar</a>
                                        </li>
                                        <li>
                                            <a href="">Registrar</a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <li class="no-padding">
                        <ul class="collapsible collapsible-accordion">
                            <li>
                                <a class="collapsible-header"><i class="material-icons"></i>Categorias</a>
                                <div class="collapsible-body">
                                    <ul>
                                        <li>
                                            <a href="">Mostrar</a>
                                        </li>
                                        <li>
                                            <a href="">Registrar</a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <li class="no-padding">
                        <ul class="collapsible collapsible-accordion">
                            <li>
                                <a class="collapsible-header"><i class="material-icons"></i>Imprentas</a>
                                <div class="collapsible-body">
                                    <ul>
                                        <li>

                                            <a href="">Mostrar</a>
                                        </li>
                                        <li>
                                            <a href="">Registrar</a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <li class="no-padding">
                        <ul class="collapsible collapsible-accordion">
                            <li>
                                <a class="collapsible-header"><i class="material-icons"></i>Usuarios</a>
                                <div class="collapsible-body">
                                    <ul>
                                        <li>

                                            <a href="">Mostrar</a>
                                        </li>
                                        <li>
                                            <a href="">Registrar</a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <div class="divider"></div>
                    </li>
                    <li>
                        <a class="subheader"></a>
                    </li>

                </ul>
            </header>
            <div class="Contenido">
                
            </div>
    </body>
</html>
