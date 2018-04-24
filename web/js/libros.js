/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
           $.ajax({
                type: 'POST',
                url: `${location.origin}/Thot/Libro`,
                data: {id: 1},
                success: function(r){
                    let cards = ``;
                    let libros = JSON.parse(r);
                    libros.forEach(function(_l){
                        cards = `
                            <div class="card col m4 12">
                              <div class="card-image waves-effect waves-block waves-light">
                                <img class="activator" height="200px" src="/Thot/images/libros/${_l["imagen"]}">
                              </div>
                              <div class="card-content">
                                <span class="card-title activator grey-text text-darken-4">
                                    ${_l["titulo"]}<i class="material-icons right">more_vert</i>
                                </span>
                                <p><a href="#">This is a link</a></p>
                              </div>
                              <div class="card-reveal">
                                <span class="card-title grey-text text-darken-4">Card Title<i class="material-icons right">close</i></span>
                                <p>${_l["descripcion"]}</p>
                              </div>
                            </div>
                        `;
                        $(".row").append(cards);
                    });
//                    console.log(cards);
                    //$(".row").html(cards);
                }
           }); 
        });

