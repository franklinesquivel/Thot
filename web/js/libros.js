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
        //var search = new RegExp($(this).val(), 'i');
        searchBook($(this).val(), false);
        /* 
        libros.forEach(function(_l, i){
            var cards = $('.grid-item');
            if(cards.length > 0){
                for(var j = 0; j < cards.length; j++){
                    let remove = true;
                    let index = cards.eq(j).attr('search');

                    if(search.test(libros[index]["titulo"])
                    || search.test(libros[index]["isbn"]) || search.test(libros[index]["descripcion"])){
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
        */
    });
    
    $("#btnSearch").click(function(e){ //Búsqueda Avanzada
        //var inputs = $("#mdlSearch .txtSearch"); //Todos los inputs de entrada id = [category, printing, author, subject];
        var loader = new Loader();
        loader.in();
        if($("#category").val().trim().length > 0){
            searchBook($("#category").val(), true);
        }
        
        if($("#printing").val().trim().length > 0){
            searchBook($("#printing").val(), true);
        }
        
        if($("#author").val().trim().length > 0){
            searchBook($("#author").val(), true);
        }
        
        if($("#subject").val().trim().length > 0){
            searchBook($("#subject").val(), true);
        }
        $("#mdlSearch").modal('close');
        loader.out();
    });
});
        
function loadBook(){
    var loader = new Loader();
    loader.in();
    $.ajax({
        type: 'POST',
        url: `${location.origin}/Thot/Libro`,
        data: {id: 1},
        success: function(r){
            let cards = ``;
            libros = JSON.parse(r);
            
            libros.forEach(function(_l, i){
                /*console.log();
                console.log("Libro "+i);
                console.log("Titulo: "+_l["titulo"]);
                console.log("Descripcion: "+_l["descripcion"]);
                console.log("Notas: "+_l["notas"]);
                console.log("Categoria: "+_l["categoria"]["nombre"]);
                console.log("Categoria descripcion: "+_l["categoria"]["descripcion"]);
                console.log("Imprenta nombre: "+_l["imprenta"]["nombre"]);
                _l["autores"].forEach(function(_autor, x){
                    console.log("Nombre Autor: "+_autor["nombres"]);
                    console.log("Apellido Autor: "+_autor["apellidos"]);
                });
                _l["temas"].forEach(function(_tema, x){
                    console.log("Nombre Tema: "+_tema["descripcion"]);
                });*/
                
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
                                <p>
                                    <ul>
                                        <li>${_l["isbn"]}</li>
                                        <li>Descripción: ${_l["descripcion"]}</li>
                                        <li>Categoría: ${_l["categoria"]["nombre"]}</li>
                                        <li>Imprenta: ${_l["imprenta"]["nombre"]}</li>
                                        <li>Notas: ${_l["notas"]}</li>
                                    </ul>
                                </p>
                            </div>
                        </div>
                    </div>
                `;   
            });    
            var $c = $(cards);
            $msnry.append( $c ).masonry( 'appended', $c );
        }
    });
    loader.out();
}

function searchBook(valor, busquedaAvanzada){ //Buscador
    var search = new RegExp(valor, 'i');
         
   libros.forEach(function(_l, i){
        var cards = $('.grid-item');
        if(cards.length > 0){
            for(var j = 0; j < cards.length; j++){
                let remove = true;
                let index = cards.eq(j).attr('search');

                if(busquedaAvanzada){
                    //Autores y temas pueden ser n
                    if(search.test(libros[index]["categoria"]["nombre"]) || search.test(libros[index]["imprenta"]["nombre"])){
                        remove = false;
                    }
                    
                    if(remove){
                        libros[index]["autores"].forEach(function(_author){
                            if(search.test(_author["nombres"]) || search.test(_author["apellidos"])){
                                remove = false;
                            }
                        });
                    }
                        
                    if(remove){
                        libros[index]["temas"].forEach(function(_subject, x){
                            if(search.test(_subject["descripcion"])){
                                remove = false;
                            }
                        });
                    }
                }else{
                    if(search.test(libros[index]["titulo"])
                        || search.test(libros[index]["isbn"]) || search.test(libros[index]["descripcion"])){
                        remove = false;
                    }
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
                        addCard(_l, i); //Se agrega Tarjeta
                        cards = $('.grid-item');
                    }
                }
            }//Fin for de cards
        }else{
            addCard(_l, i); //Se agrega Tarjeta
        }
    });//Fin foreach de libros
}

function addCard(_l, i){
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
                    <p>
                        <ul>
                            <li>${_l["isbn"]}</li>
                            <li>Descripción: ${_l["descripcion"]}</li>
                            <li>Categoría: ${_l["categoria"]["nombre"]}</li>
                            <li>Imprenta: ${_l["imprenta"]["nombre"]}</li>
                            <li>Notas: ${_l["notas"]}</li>
                        </ul>
                    </p>
                </div>
            </div>
        </div>
    `; 
    var $c = $(card);
    $msnry.append( $c ).masonry( 'appended', $c );
}
