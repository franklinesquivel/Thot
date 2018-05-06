
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <link rel="stylesheet" href="css/index.css"/>
        <title>Thot - Librería</title>
    </head>
    <body>

        <header>
            <div class="header">


                <div class="container">
                    <nav class="transparent z-depth-0" id="nav">
                        <div class="nav-wrapper">
                            <a href="/Thot/" class="brand-logo">
                                Thot
                                <img src="/Thot/images/thot.jpg" width="50px" alt="">
                            </a>
                            <ul class="right hide-on-med-and-down" id="opc">    
                                <li><a href="libros.jsp">Ver catálogo de libros <i class="material-icons right">library_books</i></a></li> 
                                <li><a href="login.jsp" class="waves-effect waves-light btn-large blue darken-1">Iniciar Sesión <i class="material-icons right">person_pin</i></a></li>
                            </ul>
                        </div>
                    </nav>
                    <br>
                    <br>
                    <br>
                    <br>
                    <div class="col s12 m4">
                        <h1 class="center-align white-text">Bienvenido a Thot.</h1>
                        <h5 class="center-align white-text">Una plataforma cómoda, accesible y fácil de utilizar.
                            Creada para todo tipo de lectores.</h5>
                    </div>
                </div>
            </div>

        </header>
        <div class="principal">
            <div class="container">
                <div class="section">
                    <div class="row white-text">
                        <div class="col s4 center-align columna z-depth-5">
                            <h2>Thot</h2>
                            <p class="flow-text">
                                Thot es una biblioteca con un sistema online en el que podrás reservar los libros desde la comodidad de tu casa.
                            </p>
                        </div>
                        <div class="col s4 center-align columna z-depth-5">
                            <h2>Sencillez</h2>
                            <p class="flow-text white-text">
                                Tan sencillo como registrarse y dar un par de clics dentro del sistema para realizar sus operaciones.
                            </p>
                        </div>
                        <div class="col s4 center-align columna z-depth-5">
                            <h2>Siempre en linea</h2>
                            <p class="flow-text white-text">
                               Servicio 24/7, al ser un sistema en linea, puedes reservar tus libros 
                            </p>
                        </div>
                    </div>                
                </div>
            </div>
            <br>
            <br>
            <br>
            <br>
            <br>
            <div class="container botones center-align">
                <div class="row">
                    <div class="col s3"></div>
                    <div class="col s3">
                        <a href="login.jsp" class="waves-effect waves-light btn-large blue darken-1">Iniciar Sesión <i class="material-icons right">person_pin</i></a>
                    </div>
                    <div class="col s3">
                        <a href="login.jsp" class="waves-effect waves-light btn-large blue darken-1">Registrarse <i class="material-icons right">person_pin</i></a>
                    </div>
                    <div class="col s3"></div>
                </div>
            </div>
            
        </div>

        <footer class="page-footer">
            <div class="footer-copyright">
                <div class="container white-text center-align">
                    © 2018 Copyright Thot
                </div>
            </div>
        </footer>



        <script>
            $(document).ready(function () {
                $('.carousel').carousel();
            });
        </script>
    </body>
</html>
