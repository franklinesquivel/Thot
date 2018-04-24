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
        
        <title>Thot - Libros</title>
    </head>
    <body>
        <div class="row">
            
        </div>
    </body>
    <script>
        $(document).ready(function(){
           $.ajax({
                type: 'POST',
                url: `${location.origin}Libro`,
                data: {id: 1},
                success: function(r){
                    var cards = ``;
                    var libros = JSON.parse(r);
                    console.log(libros);
                    for(var i = 0; i < libros.length; i++){
                        cards += `
                            <div class='card'>
                              <div class='card-image waves-effect waves-block waves-light'>
                                <img class='activator' src='/Thot/images/libros/${libros[i]["imagen"]}'>
                              </div>
                              <div class='card-content'>
                                <span class='card-title activator grey-text text-darken-4'>
                                    ${libros[i]["titulo"]}<i class='material-icons right'>more_vert</i>
                                </span>
                                <p><a href="#">This is a link</a></p>
                              </div>
                              <div class='card-reveal'>
                                <span class='card-title grey-text text-darken-4'>Card Title<i class='material-icons right'>close</i></span>
                                <p>${libros[i]["descripcion"]}</p>
                              </div>
                            </div>
                        `;
                    }
                    $(".row").html(cards);
                }
           }); 
        });
    </script>
</html>
