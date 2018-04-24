/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
           $.ajax({
                type: 'POST',
                url: `${location.origin + location.pathname}/Libro`,
                data: {id: 1},
                success: function(r){
                    var cards = ``;
                    var libros = JSON.parse(r);
                    console.log(libros);
                    for(var i = 0; i < libros.length; i++){
                        cards = `
                            <div class="card">
                              <div class="card-image waves-effect waves-block waves-light">
                                <img class="activator" src="/Thot/images/libros/${libros[i]["imagen"]}">
                              </div>
                              <div class="card-content">
                                <span class="card-title activator grey-text text-darken-4">
                                    ${libros[i]["titulo"]}<i class="material-icons right">more_vert</i>
                                </span>
                                <p><a href="#">This is a link</a></p>
                              </div>
                              <div class="card-reveal">
                                <span class="card-title grey-text text-darken-4">Card Title<i class="material-icons right">close</i></span>
                                <p>${libros[i]["descripcion"]}</p>
                              </div>
                            </div>
                        `;
                        $(".row").append(cards);
                    }
                    console.log(cards);
                    //$(".row").html(cards);
                }
           }); 
        });

