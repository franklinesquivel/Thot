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
    });
    
    
})
();