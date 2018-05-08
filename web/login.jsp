<%-- 
    Document   : index
    Created on : 04-16-2018, 04:51:14 PM
    Author     : Frank
--%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>

<%@ include file="/WEB-INF/jspf/control_sesion.jspf" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />

        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="/Thot/css/materialize.min.css">

        <script src="/Thot/js/jquery.js"></script>
        <script src="/Thot/js/jquery.validate.min.js"></script>
        <script src="/Thot/js/materialize.min.js"></script>
        <script src="/Thot/js/Loader.js"></script>
        <script src="/Thot/js/init.js"></script>
        
        <script src="/Thot/js/login.js"></script>
        <link rel="stylesheet" href="/Thot/css/login.css">

        <title><fmt:message key="login.header.title"/></title>
    </head>
    <body>
        <main class="row">
            <form action="/Thot/Login" method="POST" name="frmLogin" id="frmLogin" class="col l10 offset-l1 m10 offset-m1 s10 offset-s1">
                <h3 class="blue-text text-lighten-1 center" style="display: flex; justify-content: center;">Thot <i class="material-icons medium">book</i></h3>
                <h5 class="grey-text center"><fmt:message key="login.title"/></h5>
                
                <br><br>

                <div id="mainAlertCont" class="col m10 offset-m1 s12">
                    <c:if test="${sessionScope.msg != null}">
                        <div class="${sessionScope.msg_type} ${sessionScope.msg_type}-text text-darken-3 center alert">
                            <strong><fmt:message key="login.error.${sessionScope.msg}"/></strong>
                        </div>
                        
                        <c:remove var="msg_type" scope="session" />
                        <c:remove var="msg" scope="session" />
                    </c:if>
                </div>

                <div class="input-field col m10 offset-m1 s12">
                    <i class="material-icons prefix">account_circle</i>
                    <input type="text" name="txtUser" id="txtUser" class="">
                    <label for="txtUser"><fmt:message key="login.user"/></label>
                </div>
                <br>
                <div class="input-field col m10 offset-m1 s12">
                    <i class="material-icons prefix">vpn_key</i>
                    <input type="password" name="txtPassword" id="txtPassword" class="">
                    <label for="txtPassword"><fmt:message key="login.password"/></label>
                </div>
                
                <div class="input-field col m10 offset-m1 s12">
                    <a class="modal-trigger" href="#mdlRecover"><fmt:message key="login.password.forgot"/></a>
                </div>
                <div class="input-field col m10 offset-m1 s12">
                    <a class="modal-trigger" href="#mdlRegister"><fmt:message key="register"/></a>
                </div>      
                <br><br>
                <div class="input-field col m10 offset-m1 s12" style="display: flex; justify-content: center;">
                    <button type="submit" class="btn indigo waves-effect waves-light"><fmt:message key="login"/></button>
                </div>
            </form>
        </main>

        <div id="mdlRecover" class="modal">
            <div class="modal-content">
                <h4 class="center modal-title purple-text text-accent-2"><fmt:message key="login.password.recover"/></h4>
                <p class="center grey-text">
                    <fmt:message key="login.password.recover.message"/>
                </p>
                <div id="frmAlertCont" class="col m8 offset-m2 s10 offset-s1"></div>
                <form class="row" name="frmRecoverPass" id="frmRecoverPass">
                    <div class="input-field col m10 offset-m1 s12">
                        <i class="material-icons prefix">account_circle</i>
                        <fmt:message key="login.user" var="userVar"/>
                        <input type="text" name="txtUser" class="form-control" placeholder="${userVar}">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="modal-action modal-close waves-effect waves-light red darken-1 white-text btn"><fmt:message key="close"/></button>    
                <button id="btnRecover" class="waves-effect waves-light amber white-text btn"><fmt:message key="login.password.recover"/></button>  
            </div>
        </div>
        
        <div id="mdlRegister" class="modal modal-fixed-footer">
            <div class="modal-content">
                <h4 class="center modal-title purple-text text-accent-2"><fmt:message key="login.register.title"/></h4>
                <p class="center grey-text">
                    <fmt:message key="login.register.body"/>
                </p>
                <div id="_frmAlertCont" class="col m8 offset-m2 s10 offset-s1"></div>
                <form class="row" name="frmRegister" id="frmRegister">
                    <div class="input-field col m10 offset-m1 s12">
                        <input type="text" name="txtName" id="txtName" class="form-control">
                        <label for="txtUser"><fmt:message key="login.register.name"/></label>
                    </div>
                    <div class="input-field col m10 offset-m1 s12">
                        <input type="text" name="txtLastName" id="txtLastName" class="form-control">
                        <label for="txtLastName"><fmt:message key="login.register.lastname"/></label>
                    </div>
                    <div class="input-field col m10 offset-m1 s12">
                        <input type="date" name="txtBirthdate" id="txtBirthdate" class="form-control">
                        <label for="txtBirthdate" class="active"><fmt:message key="login.register.birthdate"/></label>
                    </div>
                    <div class="input-field col m10 offset-m1 s12">
                        <input type="text" name="txtEmail" id="txtEmail" class="form-control">
                        <label for="txtEmail" class="active"><fmt:message key="login.register.email"/></label>
                    </div>
                    
                    <div class="input-field col m10 offset-m1 s12 center-align">
                        <button id="btnRegister" class="waves-effect waves-light amber white-text btn"><fmt:message key="login.register.btn"/></button> 
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="modal-action modal-close waves-effect waves-light red darken-1 white-text btn"><fmt:message key="close"/></button>    
            </div>
        </div>
        
        <div id="main-back">
            <img src="images/log-wall.jpg">
        </div>
    </body>
</html>
