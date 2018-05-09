(function(){

    $(document).ready(function(){
        $("#tblEjemplares").DataTable();
        
        $(frmAdd).validate({
            rules: {
                txtCant: {
                    required: true,
                    number: true,
                    min: 1,
                    max: 25
                }
            },
            messages: {
                txtCant: {
                    required: 'Ingrese una cantidad de ejemplares',
                    number: 'Ingrese una cifra numérica',
                    min: 'Debe ingresar por lo menos un ejemplar',
                    max: 'La cantidad máxima a agregar en una transacción es de 25 ejemplares'
                }
            },
            submitHandler: function(form){
                let _loader = new Loader();
                _loader.in();
                $.ajax({
                    type: 'POST',
                    url: `${location.origin}/Thot/Ejemplares/Aumentar`,
                    data: {idLibro: form.idLibro.value, add: form.txtCant.value},
                    success: function (res) {
                        r = parseInt(res);
                        let _o = {};
                        if (r === 1) {
                            _o = {
                                html: `Los ejemplares han sido ingresados éxitosamente! <i class="material-icons right">check_circle</i>`,
                                classes: "green darken-1", displayLength: 1500,
                                completeCallback: location.reload()
                            };
                        } else if (r === 0) {
                            _o = {
                                html: `Ha ocurrido un error al insertar los ejemplares! Inténtelo mas tarde... <i class="material-icons right">error</i>`,
                                classes: "red darken-1", displayLength: 2000
                            };
                        } else if (r === -1) {
                            _o = {
                                html: `El cuerpo de la petición no es válido para ser procesado...<i class="material-icons right">block</i>`,
                                classes: "red darken-4", displayLength: 2000
                            };
                        } else if (r === -2) {
                            _loader.out();
                            _o = {
                                html: `No te encuentras autenticado para efectuar esta operación<i class="material-icons right">lock</i>`,
                                classes: "red darken-4", displayLength: 2000, completeCallback: location.reload()
                            };
                        } else if (r === -3) {
                            _loader.out();
                            _o = {
                                html: `El libro al que quieres añadir ejemplares no existe...<i class="material-icons right">find_replace</i>`,
                                classes: "red darken-4", displayLength: 2000, completeCallback: location.reload()
                            };
                        }

                        M.toast(_o);
                    },
                    error: function (err) {
                        M.toast({html: "En este momento no se puede establecer la conexión con el servidor. Inténtelo más tarde... <i class='material-icons right'>error</i>", classes: "red darken-5"});
                    },
                    finally: _loader.out()
                })
            }
        });
        
        $("#btnAdd").click(function(){
            $(frmAdd).submit();
        })

        let _e;

        $(".btnEdit").click(function(){
            let idEjemplar = $(this).attr('idEjemplar'), 
                dataCont = $(`#tblEjemplares tr[data=${idEjemplar}] td`);

            _e = {
                idEjemplar,
                observaciones: dataCont.eq(2).html()
            }

            frmEdit.idEjemplar.value = _e.idEjemplar;
            frmEdit.txtObservaciones.value = _e.observaciones;

            M.updateTextFields();
        });

        $(frmEdit).validate({
            rules: {
                txtObservaciones: 'required'
            },
            messages: {
                txtObservaciones: 'Debes ingresar una observación...'
            },
            submitHandler: function (form) {
                let loader = new Loader();
                loader.in();

                $.ajax({
                    type: 'POST',
                    url: `${location.origin}/Thot/Ejemplares/Modificar`,
                    data: {
                        idEjemplar: frmEdit.idEjemplar.value.trim(),
                        observaciones: frmEdit.txtObservaciones.value.trim()
                    },
                    success: function (r) {
                        r = parseInt(r);
                        let _o = {};
                        if (r === 1) {
                            _o = {
                                html: `El ejemplar ha sido modificado éxitosamente! <i class="material-icons right">check_circle</i>`,
                                classes: "green darken-1", displayLength: 1500,
                                completeCallback: location.reload()
                            };
                        } else if (r === 0) {
                            _loader.out();
                            _o = {
                                html: `Ha ocurrido un error al modificar el ejemplar! Inténtelo mas tarde... <i class="material-icons right">error</i>`,
                                classes: "red darken-1", displayLength: 2000
                            };
                        } else if (r === -1) {
                            _loader.out();
                            _o = {
                                html: `El ejemplar que deseas modificar no existe...<i class="material-icons right">find_replace</i>`,
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

        $("#btnSubmitEdit").click(function(){
            // console.log(':p');
            $(frmEdit).submit();
        })
    });
    
    
})
();