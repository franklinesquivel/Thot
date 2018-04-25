/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var libros = [];
var $msnry;

$(document).ready(function(){
    $msnry = $('.grid').masonry({
        // options
        itemSelector: '.grid-item',
        columnWidth: '.grid-sizer',
        percentPosition: true
    });
    loadBook();
    
    $('#txtSearch').on('keyup', function(){
        var search = new RegExp($(this).val(), 'i');
        
        libros.forEach(function(_l, i){
            var cards = $('.grid-item');
            if(cards.length > 0){
                for(var j = 0; j < cards.length; j++){
                    let remove = true;
                    let index = cards.eq(j).attr('search');

                    if( search.test(libros[index]["idLibro"]) || search.test(libros[index]["titulo"])
                    || search.test(libros[index]["notas"]) || search.test(libros[index]["descripcion"])){
                        remove = false;
                    }

                    if(remove){
                        $msnry.masonry('remove', cards.eq(j));
                        $msnry = $('.grid').masonry({
                            // options
                            itemSelector: '.grid-item',
                            columnWidth: '.grid-sizer',
                            percentPosition: true
                        });
                    }else{
                        let add = true, existe = 0;

                        for(var x = 0; x < cards.length; x++){
                            if(parseInt(cards.eq(x).attr('search'))=== i){
                                add = false;
                                break;
                            }
                        }

                        if(add){
                            let card = `
                                <div class="grid-item" search="${i}">
                                    <div class="card">
                                        <div class="card-image waves-effect waves-block waves-light">
                                            <img class="activator" src="/Thot/images/libros/${_l["imagen"]}">
                                        </div>
                                        <div class="card-content">
                                            <span class="card-title activator grey-text text-darken-4">
                                                ${_l["titulo"]}<i class="material-icons right">more_vert</i>
                                            </span>
                                            <p></p>
                                        </div>
                                        <div class="card-reveal">
                                            <span class="card-title grey-text text-darken-4">${_l["titulo"]}<i class="material-icons right">close</i></span>
                                            <p>${_l["descripcion"]}</p>
                                        </div>
                                    </div>
                                </div>
                            `; 
                            var $c = $(card);
                            $msnry.append( $c ).masonry( 'appended', $c );
                            cards = $('.grid-item');
                        }
                    }
                }//Fin for de cards
            }else{
                let card = `
                    <div class="grid-item" search="${i}">
                        <div class="card">
                            <div class="card-image waves-effect waves-block waves-light">
                                <img class="activator" src="/Thot/images/libros/${_l["imagen"]}">
                            </div>
                            <div class="card-content">
                                <span class="card-title activator grey-text text-darken-4">
                                    ${_l["titulo"]}<i class="material-icons right">more_vert</i>
                                </span>
                                <p></p>
                            </div>
                            <div class="card-reveal">
                                <span class="card-title grey-text text-darken-4">${_l["titulo"]}<i class="material-icons right">close</i></span>
                                    <p>${_l["descripcion"]}</p>
                            </div>
                        </div>
                    </div>
                `; 
                var $c = $(card);
                $msnry.append( $c ).masonry( 'appended', $c );
            }
        });//Fin foreach de libros
    });
});
        
function loadBook(){
    $.ajax({
        type: 'POST',
        url: `${location.origin}/Thot/Libro`,
        data: {id: 1},
        success: function(r){
            let cards = ``;
            libros = JSON.parse(r);
            libros.forEach(function(_l, i){
                cards += `
                    <div class="grid-item" search="${i}">
                        <div class="card">
                            <div class="card-image waves-effect waves-block waves-light">
                                <img class="activator" src="/Thot/images/libros/${_l["imagen"]}">
                            </div>
                            <div class="card-content">
                                <span class="card-title activator grey-text text-darken-4">
                                    ${_l["titulo"]}<i class="material-icons right">more_vert</i>
                                </span>
                                <p></p>
                            </div>
                            <div class="card-reveal">
                                <span class="card-title grey-text text-darken-4">${_l["titulo"]}<i class="material-icons right">close</i></span>
                                <p>${_l["descripcion"]}</p>
                            </div>
                        </div>
                    </div>
                `;   
            });    
            var $c = $(cards);
            $msnry.append( $c ).masonry( 'appended', $c );
        }
    }); 
}


