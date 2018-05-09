(function(){

    $(document).ready(function(){
        if(typeof $("#tblPrestamos") !== 'undefined'){
            let idPrestamo;
            
            $('.btnFactura').click(function(){
                frmFactura.idPrestamo.value = $(this).attr('idPrestamo');
                frmFactura.submit();
            });
    
            $("#tblPrestamos").DataTable({
                "columnDefs": [
                    {
                        "targets": [ 0 ],
                        "visible": false
                    }
                ],
                "order": [[ 3, "asc" ], [ 2, "desc" ], [ 1, "desc" ]]
            });
    
            $(".btnFinalizar").click(function(){
                idPrestamo = $(this).attr('idPrestamo');
                $("#info-cont").html(`
                    <br><h6 class='center'><b>[${idPrestamo}]</b></h6>                    
                `);
            });
    
            $("#btnConfirmFinish").click(function(){
                let _loader = new Loader();
                _loader.in();
    
                $.ajax({
                    type: 'POST',
                    url: `${location.origin}/Thot/Prestamos/Finalizar`,
                    data: {idPrestamo},
                    success: function (r) {
                        r = parseInt(r);
                        let _o = {};
                        if (r === 1) {
                            _o = {
                                html: `El préstamo ha sido finalizado éxitosamente! <i class="material-icons right">check_circle</i>`,
                                classes: "green darken-1", displayLength: 1500,
                                completeCallback: location.reload()
                            };
                        } else if (r === 0) {
                            _loader.out();
                            _o = {
                                html: `Ha ocurrido un error al finalizar el préstamo! Inténtelo mas tarde... <i class="material-icons right">error</i>`,
                                classes: "red darken-1", displayLength: 2000
                            };
                        } else if (r === -1) {
                            _loader.out();
                            _o = {
                                html: `El préstamo que deseas finalizar no existe...<i class="material-icons right">find_replace</i>`,
                                classes: "red darken-2", displayLength: 2000
                            };
                        } else if (r === -2) {
                            _loader.out();
                            _o = {
                                html: `No te encuentras autenticado para efectuar esta operación<i class="material-icons right">block</i>`,
                                classes: "red darken-4", displayLength: 2000, completeCallback: location.reload()
                            };
                        } else if (r === -3) {
                            _loader.out();
                            _o = {
                                html: `El préstamo ya se encontraba finalizado...<i class="material-icons right">warning</i>`,
                                classes: "yellow darken-1", displayLength: 2000
                            };
                        }
    
                        M.toast(_o);
                    },
                    error: function (err) {
                        _loader.out();
                        M.toast({html: "En este momento no se puede establecer la conexión con el servidor. Inténtelo más tarde... <i class='material-icons right'>error</i>", classes: "red darken-5"});
                    }
                })
            });
        }
    })
})
();