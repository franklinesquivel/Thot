(function(){

    $(document).ready(function(){
        if(typeof $("#tblReservas") !== 'undefined'){
            
            $("#tblReservas").DataTable();
            
            $("#btnMdlPrestamo").click(function(){
               let idReserva = $(this).attr("dataID"),
               dataContent = $(`#tblReservas tr[data=${idReserva}] td`),
               data = {
                   idReserva,
                   Usuario: {
                       idUsuario: dataContent.eq(0).attr("idUsuario"),
                       nombre: dataContent.eq(0).html()
                   },
                   Libro: dataContent.eq(1).html(),
                   fechaPrestamo: new Date(),
                   fechaDevolucion: new Date()
               }
               
               
            });
            
            $("#btnConfirm").click(function(){
                
            });
        }
    })

})
();