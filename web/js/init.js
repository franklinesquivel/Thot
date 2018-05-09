(function(){
    $(document).ready(function(){
        M.AutoInit();
        
        if($('.modal').length > 0){
            $('.modal').modal();
        }

        $.validator.setDefaults({
            errorClass: 'invalid',
            validClass: "none",
            errorPlacement: function (error, element) {
                $(element).parent().find('span.helper-text').remove();
                $(element).parent()
                        .append(`<span class='helper-text' data-error='${error.text()}'></span>`);
            }
        });
        
        

        $.extend(true, $.fn.dataTable.defaults, {
            "language": {
                "sProcessing": "Procesando...",
                "sLengthMenu": "Mostrar _MENU_ registros",
                "sZeroRecords": "No se encontraron resultados",
                "sEmptyTable": "Ningún dato disponible en esta tabla",
                "sInfo": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
                "sInfoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
                "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
                "sInfoPostFix": "",
                "sSearch": "Buscar:",
                "sUrl": "",
                "sInfoThousands": ",",
                "sLoadingRecords": "Cargando...",
                "oPaginate": {
                    "sFirst": "Primero",
                    "sLast": "Último",
                    "sNext": "Siguiente",
                    "sPrevious": "Anterior"
                },
                "oAria": {
                    "sSortAscending": ": Activar para ordenar la columna de manera ascendente",
                    "sSortDescending": ": Activar para ordenar la columna de manera descendente"
                }
            },
            // "language": {
            //     "url": "dataTables.spanish.lang"
            // },
            "dom": '<"toolbar">frtip',
            "initComplete": function(settings, json) {
                $($.fn.dataTableExt.oStdClasses.sPageButton = "button").addClass("btn");
            }
        });
    });
})
();
