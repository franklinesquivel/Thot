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
    
    
    $.validator.setDefaults({
        errorClass: 'invalid',
        validClass: 'none',
        errorPlacement: function(error, element) {
            $(element).parent().find('span.helper-text').remove();
            $(element).parent()
                .append(`<span class='helper-text' data-error='${error.text()}'></span>`);
        }
    });
    $.validator.addMethod('validDate', function(value, element) {
        let actualDate = new Date(), fecha = new Date(value);
        return this.optional(element) || (fecha > actualDate);
    }, 'Ingrese una fecha válida (no menor a la actual).');
    
    function twoDigits(d) {
        if(0 <= d && d < 10) return "0" + d.toString();
        if(-10 < d && d < 0) return "-0" + (-1*d).toString();
        return d.toString();
    }
    Date.prototype.toMySQLFormat = function() {
        return this.getFullYear() + "-" + twoDigits(this.getMonth()) + "-" + twoDigits(this.getDate()) + " " + twoDigits(this.getHours()) + ":" + twoDigits(this.getMinutes()) + ":" + twoDigits(this.getSeconds());
    };

    
    $("#frmReserve").validate({
        rules:{
            fecha_vencimiento:{
                required: true,
                validDate: true
            }
        }, 
        messages:{
            fecha_vencimiento: {
                required: 'Debes ingresar una fecha'
            }
        },
        submitHandler: function(form) {
            let _a = frmReserve.fecha_vencimiento.value.split('-'), now = new Date(),
            _d = new Date(_a[0], _a[1], _a[2], now.getHours(), now.getMinutes(), now.getSeconds(), now.getMilliseconds());
            fechaActual = new Date(now.getFullYear(), (now.getMonth()+1), now.getDate(), now.getHours(), now.getMinutes(), now.getSeconds(), now.getMilliseconds());
            let _loader = new Loader();
            _loader.in();
            $.ajax({
                type: 'POST',
                url: `${location.origin}/Thot/Reservas/Registar`,
                data: {
                    idLibro: $('#frmReserve #idLibro').val(),
                    fecha_vencimiento: _d.toMySQLFormat(),
                    fecha_reserva: fechaActual.toMySQLFormat()
                },
                success: function (data) {
                    data = parseInt(data);
                    if(data === -3){
                        M.toast({html: 'El libro no posee ejemplares disponibles para reservar'});
                    }else if(data === -1){
                        M.toast({html: 'Libro no encontrado'});
                    }else if(data === 1){
                        M.toast({html: 'Reserva Exitosa'});
                        $("#mdlReserve").modal('close')
                        $("#frmReserve")[0].reset();
                    }else if(data === 0){
                        M.toast({html: 'Error en el proceso de reserva'});
                    }
                    _loader.out();
                },
                error : function ( jqXhr, json, errorThrown ) {
                    console.log('Error en ajax');
                }
            });
        }
    });
    
    function formatDate(date) {
        var now = new Date() , _a = date.split('-');
        var _d = new Date(_a[0], _a[1], _a[2], now.getHours(), now.getMinutes(), now.getSeconds());
        return _d;
    }
    
    $('#txtSearch').on('keyup', function(){
        searchBook($(this).val(), false);
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

$(document).on("click", ".btnReserve", function(){
    $("#mdlReserve #idLibro").val($(this).attr("idlibro"));
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
                
                                <p class='center-align'>
                                    <a href="#mdlReserve" class="waves-effect waves-light btn btnReserve modal-trigger" idlibro="${_l["idLibro"]}">Reservar</a>
                                </p>
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
                    <p class='center-align'>
                        <a href="#mdlReserve" class="waves-effect waves-light btn btnReserve modal-trigger" idsearch="${i}">Reservar</a>
                    </p>
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
