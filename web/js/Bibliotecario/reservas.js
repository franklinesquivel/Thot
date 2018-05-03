(function(){

    $(document).ready(function () {
        if (typeof $("#tblReservas") !== 'undefined') {

            function twoDigits(d) {
                if(0 <= d && d < 10) return "0" + d.toString();
                if(-10 < d && d < 0) return "-0" + (-1*d).toString();
                return d.toString();
            }
            
            Date.prototype.toMySQLFormat = function() {
                return this.getFullYear() + "-" + (twoDigits(this.getMonth() + 1)) + "-" + twoDigits(this.getDate()) + " " + twoDigits(this.getHours()) + ":" + twoDigits(this.getMinutes()) + ":" + twoDigits(this.getSeconds());
            };

            Date.prototype.addDays = function (days) {
                var returnDate = new Date(
                    this.getFullYear(),
                    this.getMonth(),
                    this.getDate() + days,
                    this.getHours(),
                    this.getMinutes(),
                    this.getSeconds()
                );
        
                return returnDate;
            }

            let data;

            $("#tblReservas").DataTable({
                "columnDefs": [
                    {
                        "targets": [ 0 ],
                        "visible": false
                    }
                ],
                "order": [[ 5, "desc" ], [ 1, "desc" ], [ 3, "desc" ]]
            });

            $(".btnMdlPrestamo").click(function () {
                let idReserva = $(this).attr("dataID"),
                    dataContent = $(`#tblReservas tr[data=${idReserva}] td`),
                    _d = new Date();

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
                    fechaPrestamo: _d,
                    fechaDevolucion: _d.addDays(1)
                };

                $('#cmbDays').val(1);
                $('select').formSelect();
                
                $("#info-cont").html(`
                    <h6 class='center'><b>Libro:</b> ${data.libro.idLibro} - ${data.libro.titulo}</h6>
                    <h6 class='center'><b>Usuario:</b> ${data.usuario.idUsuario} - ${data.usuario.nombre}</h6>
                    <h6 class='center'><b>Fecha de inicio:</b> ${_d.toLocaleDateString().split('/').join('-')}</h6>
                    <h6 class='center'><b>Fecha de devolución:</b> ${_d.addDays(1).toLocaleDateString().split('/').join('-')}</h6>                    
                `);

            });

            $('#cmbDays').change(function(){
                let _d = new Date(), _auxDate = _d.addDays(Number.parseInt($(this).val()));
                $("#info-cont h6").eq(3).html(`<b>Fecha de devolución:</b> ${_auxDate.toLocaleDateString().split('/').join('-')}`)
                data.fechaPrestamo = new Date();
                data.fechaDevolucion = _auxDate;
            });
            
            $("#btnConfirm").click(function () {
                if(typeof data !== 'undefined'){
                    let _loader = new Loader();
                    _loader.in();
                    $.ajax({
                        type: 'POST',
                        url: `${location.origin}/Thot/Reservas/Efectuar`,
                        data: {
                            idReserva: data.idReserva,
                            fecha_prestamo: data.fechaPrestamo.toMySQLFormat(),
                            fecha_devolucion: data.fechaDevolucion.toMySQLFormat()
                        },
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
                            } else if (r === -4) {
                                _loader.out();
                                _o = {
                                    html: `Ha ocurrido un error con las fechas ingresadas! Verifica sus valores...<i class="material-icons right">thumb_down</i>`,
                                    classes: "red darken-2", displayLength: 2000
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