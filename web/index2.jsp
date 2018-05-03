
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/css/materialize.min.css">
        <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/js/materialize.min.js"></script>
        <link rel="stylesheet" href="css/index.css"/>
    </head>
    <body>

        <header>

            <div class="container">
                <nav class="transparent z-depth-0" id="nav">
                    <div class="nav-wrapper">
                        <a href="#" class="brand-logo">
                            Thot
                        </a>
                        <ul class="right hide-on-med-and-down" id="opc">    
                            <li><a href="#">Iniciar Sesión</a></li>
                            <li><a href="#">Registrarme</a></li> 
                        </ul>
                    </div>
                </nav>
            </div>
        </header>

        <div class="container">
            <div class="col s12 m4 18">
                <h1 class="center-align white-text">Bienvenido a Thot</h1>
                <h5 class="center-align white-text">Tu biblioteca digital</h5>
            </div> 
            <br><br>

            <br>
            <br>
            <br>
            <div class="row">
                <div class="col s12 m4">
                    <div class="card">
                        <div class="card-image">
                            <img src="https://www.recreoviral.com/wp-content/uploads/2015/06/30-asombrosas-portadas-de-libros-28.jpg">
                            
                        </div>
                        <div class="card-content">
                            <p>Hermann Kermit Warm va a morir. El hombre enigmático y poderoso conocido como Commodore lo ha ordenado, y sus secuaces, 
                                Eli y Charlie Sisters, se asegurarán de ello. Aunque Eli no comparte el apetito de su hermano por el whisky y la muerte, 
                                nunca ha sabido nada más. Pero su presa no es una marca fácil, y en el camino de Oregon City 
                                a la demanda de extracción de oro de Warm fuera de Sacramento, Eli comienza a cuestionar qué hace para ganarse la vida, 
                                y para quién lo hace.</p>
                        </div>
                        <div class="card-action">
                            <a href="#">This is a link</a>
                        </div>
                    </div>
                </div>

                <div class="col s12 m4">
                    <div class="card">
                        <div class="card-image">
                            <img src="https://www.recreoviral.com/wp-content/uploads/2015/06/30-asombrosas-portadas-de-libros-20.jpg">
                            
                        </div>
                        <div class="card-content">
                            <p>arie, una joven madre soltera, consigue un trabajo en un exclusivo asador de Dallas. 
                                Ella está sobrenaturalmente sintonizada con los apetitos de sus clientes, pero rápidamente aprende a esconder 
                                su lucha privada detrás de una sonrisa fácil y un delantal blanco crujiente. En un mundo de largas horas y noches 
                                nocturnas, donde todo funciona en una moneda de favores, efectivo y prestigio, Marie cede ante impulsos brutalmente 
                                autodestructivos. Ella se pierde en una maraña de cuerpos y el tipo de coca que "napalm sus sinapsis emocionales". 
                                Pero la destrucción, no el placer, es su objetivo. Pulsando con una energía feroz, casi salvaje, Love Me Back es un retrato 
                                sin disculpas de una mujer que corta un camino precario a través de la adultez temprana.</p>
                        </div>
                        <div class="card-action">
                            <a href="#">This is a link</a>
                        </div>
                    </div>
                </div>

                <div class="col s12 m4">
                    <div class="card">
                        <div class="card-image">
                            <img src="https://www.recreoviral.com/wp-content/uploads/2015/06/30-asombrosas-portadas-de-libros-24.jpg">
                            
                        </div>
                        <div class="card-content">
                            <p>Prisión de Fukuoka, 1944. Más allá de los muros de la prisión, la guerra se recrudece. En el interior, 
                                un hombre es encontrado brutalmente asesinado. Lo que sigue es un retrato abrasador de Corea antes de su guerra civil, 
                                y un testimonio del poder redentor de la poesía.</p>
                        </div>
                        <div class="card-action">
                            <a href="#">This is a link</a>
                        </div>
                    </div>
                </div>

            </div>

        </div>

        <footer class="page-footer amber darken-3">
            <div class="footer-copyright">
                <div class="container black-text center-align">
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
