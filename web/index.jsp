<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <link rel="stylesheet" href="css/index.css"/>
        <title><fmt:message key="index.header.title"/></title>
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
                                <li><a href="libros.jsp"><fmt:message key="index.menu.catalog"/> <i class="material-icons right">library_books</i></a></li> 
                                <li><a href="login.jsp" class="waves-effect waves-light btn-large blue darken-1"><fmt:message key="login"/> <i class="material-icons right">person_pin</i></a></li>
                            </ul>
                        </div>
                    </nav>
                    <br>
                    <br>
                    <br>
                    <br>
                    <div class="col s12 m4">
                        <h1 class="center-align white-text"><fmt:message key="index.welcome.salute"/></h1>
                        <h5 class="center-align white-text"><fmt:message key="index.welcome.message"/></h5>
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
                                <fmt:message key="index.description.p1"/>
                            </p>
                        </div>
                        <div class="col s4 center-align columna z-depth-5">
                            <h2><fmt:message key="index.description.p2.title"/></h2>
                            <p class="flow-text white-text">
                                <fmt:message key="index.description.p2.message"/>
                            </p>
                        </div>
                        <div class="col s4 center-align columna z-depth-5">
                            <h2><fmt:message key="index.description.p3.title"/></h2>
                            <p class="flow-text white-text">
                               <fmt:message key="index.description.p3.message"/>
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
                        <a href="login.jsp" class="waves-effect waves-light btn-large blue darken-1"><fmt:message key="login"/> <i class="material-icons right">person_pin</i></a>
                    </div>
                    <div class="col s3">
                        <a href="login.jsp" class="waves-effect waves-light btn-large blue darken-1"><fmt:message key="register"/> <i class="material-icons right">person_pin</i></a>
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
