<%-- 
    Document   : index
    Created on : 04-16-2018, 04:51:14 PM
    Author     : Frank
--%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>


<%@ include file="/WEB-INF/jspf/control_sesion.jspf" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        
        <script src="/Thot/js/login.js"></script>
        <link rel="stylesheet" href="/Thot/css/login.css">

        <title>Thot - Sistema Bibliotecario</title>
    </head>
    <body>
        <main class="container">
            <div class="row" id="cont">
                <form action="/Thot/Login" method="POST" name="frmLogin" id="frmLogin" class="col md-10 col-md-offset-1 col-xs-10 col-xs-offset-1">
                    <h1 class="text-info center">Thot <span class="glyphicon glyphicon-book" aria-hidden="true"></span></h1>
                    <h3 class="text-muted center">[Inicio de Sesión]</h3>
                    
                    <br><br>

                    <div id="mainAlertCont">
                        <c:if test="${sessionScope.msg != null}">
                            <div class="alert alert-${sessionScope.msg_type} alert-dismissible center" role="alert">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <strong>${sessionScope.msg}</strong>
                            </div>
                            
                            <c:remove var="msg_type" scope="session" />
                            <c:remove var="msg" scope="session" />
                        </c:if>
                    </div>


                    <div class="form-group col-md-8 col-md-offset-2 col-md-xs-12">
                        <label for="txtUser">Correo / Nombre de usuario</label>
                        <div class="input-group input-group-lg">
                            <span class="input-group-addon">@</span>
                            <input type="text" name="txtUser" class="form-control">
                        </div>
                    </div>
                    <br>
                    <div class="form-group col-md-8 col-md-offset-2 col-md-xs-12">
                        <label for="txtPassword">Contraseña</label>
                        <div class="input-group input-group-lg">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span></span>
                            <input type="password" name="txtPassword" class="form-control">
                        </div>
                    </div>
                    
                    <div class="col-md-8 col-md-offset-2 col-md-xs-12">
                        <a data-toggle="modal" data-target="#mdlRecover">He olvidado mi contraseña</a>
                    </div>

                    <br><br>
                    <div class="col-md-12" style="display: flex; justify-content: center;">
                        <button type="submit" class="btn btn-info btn-lg">Iniciar Sesión</button>
                    </div>
                </form>
            </div>
        </main>
    
        <div class="modal fade" id="mdlRecover" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h2 class="modal-title" id="myLargeModalLabel">Recuperar Contraseña</h2>
                        <p class="text-muted">
                            Ingresa tu correo o nombre de usuario para enviarte tu contraseña a tu correo electrónico!
                        </p>
                    </div>
                    <div class="modal-body container-fluid">
                        <div id="frmAlertCont"></div>
                        <form action="" class="row" name="frmRecoverPass" id="frmRecoverPass">
                            <div class="form-group col-md-8 col-md-offset-2 col-md-xs-12">
                                <div class="input-group input-group-lg">
                                    <span class="input-group-addon">@</span>
                                    <input type="text" name="txtUser" class="form-control" placeholder="Correo / Nombre de usuario">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" id="btnRecover" class="btn btn-warning">Recuperar contraseña</button>    
                    </div>
                </div>
            </div>
        </div>
        
        <div id="main-back">
            <img src="images/log-wall.jpg">
        </div>
    </body>
</html>
