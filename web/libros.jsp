<%-- 
    Document   : libros
    Created on : 04-23-2018, 10:59:22 PM
    Author     : Leonardo
--%>

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
        
        <title>Thot - Libros</title>
    </head>
    <body>
        <div class="container">
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
    </body>
</html>
