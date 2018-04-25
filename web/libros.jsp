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

        <script src="/Thot/js/jquery.js"></script>
        <script src="/Thot/js/jquery.validate.min.js"></script>
        <script src="/Thot/js/materialize.min.js"></script>
        <script src="/Thot/js/Loader.js"></script>
        <script src="/Thot/js/init.js"></script>
        <script src="/Thot/js/pagination.js"></script>
        
        <script src="/Thot/js/libros.js"></script>
        <script src="https://unpkg.com/imagesloaded@4/imagesloaded.pkgd.min.js"></script>
        <script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>   
        
        <title>Thot - Libros</title>
        <style>
            .grid-sizer,
.grid-item {
    width: 33.333%;
}

.grid-item{
    padding: 10px;
}

.grid-item.g2 {
    width: 66.666%;
}

.grid-item img {
	display: block;
	max-width: 100%;
}
@media(max-width: 70em) and (min-width: 30em){
	.grid-sizer,
	.grid-item {
		width: 50%;
    }
    
    .grid-item.g2{
        width: 100%;
    }
}

@media(max-width: 30em){
	.grid-sizer,
	.grid-item, .grid-item.g2 {
		width: 100%;
	}
}
        </style>
    </head>
    <body>
        <main class="container">
            <div class="col s6 offset-s3">
                <div class="input-field">
                    <input id="txtSearch" type="text">
                    <label for="txtSearch">Buscar</label>
                </div>
            </div>

            <div class="grid">
                <div class="grid-sizer"></div>
            </div>
        </main>
    </body>
</html>
