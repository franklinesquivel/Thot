(function(){

    Date.prototype.addDays = function (days) {
        var dat = new Date(this.valueOf());
        dat.setDate(dat.getDate() + days);
        return dat;
    }

    $(document).ready(function () {
        if (typeof $("#tblReservas") !== 'undefined') {
            let data;

            $("#tblReservas").DataTable();

            $("#btnMdlPrestamo").click(function () {
                let idReserva = $(this).attr("dataID"),
                    dataContent = $(`#tblReservas tr[data=${idReserva}] td`);
                
                data = {
                    idReserva,
                    usuario: {
                        idUsuario: dataContent.eq(0).attr("idUsuario"),
                        nombre: dataContent.eq(0).html()
                    },
                    libro: {
                        idLibro: dataContent.eq(1).attr("idLibro"),
                        titulo: dataContent.eq(1).html()
                    },
                    fechaPrestamo: dataContent.eq(2).html(),
                    fechaDevolucion: dataContent.eq(3).html()
                }
                
                let _d = new Date();
                $("#info-cont").html(`
                    <h6 class='center'><b>Libro:</b> ${data.libro.idLibro} - ${data.libro.titulo}</h6>
                    <h6 class='center'><b>Usuario:</b> ${data.usuario.idUsuario} - ${data.usuario.nombre}</h6>
                    <h6 class='center'><b>Fecha de inicio:</b> ${_d.toLocaleDateString().split('/').join('-')}</h6>
                    <h6 class='center'><b>Fecha de devolución:</b> ${_d.addDays(1).toLocaleDateString().split('/').join('-')}</h6>                    
                `);

            });
            
            $("#btnConfirm").click(function () {
                if(typeof data !== 'undefined'){
                    let _loader = new Loader();
                    _loader.in();
                    $.ajax({
                        type: 'POST',
                        url: `${location.origin}/Thot/Prestamos/Efectuar`,
                        data: {idReserva: data.idReserva},
                        success: function (r) {
                            r = parseInt(r);
                            let _o = {};
                            if (r === 1) {
                                _o = {
                                    html: `La reserva ha sido procesada éxitosamente! <i class="material-icons right">check_circle</i>`,
                                    classes: "green darken-1", displayLength: 1500,
                                    completeCallback: function () {
                                        location.href = `${location.origin}/Thot/Bibliotecario/prestamos.jsp`;
                                    }
                                };
                            } else if (r === 0) {
                                _loader.out();
                                _o = {
                                    html: `Ha ocurrido un error al efectuar la reserva! Inténtelo mas tarde... <i class="material-icons right">error</i>`,
                                    classes: "red darken-1", displayLength: 2000
                                };
                            } else if (r === -1) {
                                _loader.out();
                                _o = {
                                    html: `La reserva que deseas procesar no existe...<i class="material-icons right">find_replace</i>`,
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
                                    html: `La reserva que deseas efectuar a préstamo no se encuentra en estado óptimo para ser procesada...<i class="material-icons right">thumb_down</i>`,
                                    classes: "red darken-3", displayLength: 2000
                                };
                            }
        
                            M.toast(_o);
                        },
                        error: function (err) {
                            _loader.out();
                            M.toast({html: "En este momento no se puede establecer la conexión con el servidor. Inténtelo más tarde... <i class='material-icons right'>error</i>", classes: "red darken-5"});
                        }
                    })
                }
                
            });
        }
    })

})
();