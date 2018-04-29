(function(){
    
    $(document).ready(function(){

        if(typeof $("#tblUsers") !== 'undefined'){

            function twoDigits(d) {
                if(0 <= d && d < 10) return "0" + d.toString();
                if(-10 < d && d < 0) return "-0" + (-1*d).toString();
                return d.toString();
            }
            
            Date.prototype.toMySQLFormat = function() {
                return this.getFullYear() + "-" + twoDigits(this.getMonth()) + "-" + twoDigits(this.getDate()) + " " + twoDigits(this.getHours()) + ":" + twoDigits(this.getMinutes()) + ":" + twoDigits(this.getSeconds());
            };

            $.validator.addMethod("actualDate", function(value, element) {
                let now = new Date().setHours(0, 0, 0, 0), end = new Date(value).setHours(0, 0, 0, 0);
                return end >= now;
            }, "Ingresa una fecha mayor a la actual!");

            $(frmPrestamo).validate({
                rules: {
                    txtFin: {
                        required: true,
                        actualDate: true
                    }
                },
                messages: {
                    txtFin: {
                        required: 'Debes ingresar una fecha de inicio para el préstamo'
                    }
                },
                submitHandler: function(form){
                    let _loader = new Loader();
                    _loader.in();
                    let _a = frmPrestamo.txtFin.value.split('-'), now = new Date(),
                        _d = new Date(_a[0], _a[1], _a[2], now.getHours(), now.getMinutes(), now.getSeconds(), now.getMilliseconds());

                    $.ajax({
                        type: 'POST',
                        url: `${location.origin}/Thot/Prestamos/Registrar`,
                        data: {
                            idUsuario: frmPrestamo.idUsuario.value,
                            idEjemplar: frmPrestamo.idEjemplar.value,
                            fecha_devolucion: _d.toMySQLFormat()
                        },
                        success: function (r) {
                            r = parseInt(r);
                            let _o = {};
                            if (r === 1) {
                                _o = {
                                    html: `El préstamo ha sido registrado éxitosamente! <i class="material-icons right">check_circle</i>`,
                                    classes: "green darken-1", displayLength: 1500,
                                    completeCallback: function () {
                                        // location.href = `${location.origin}/Thot/Bibliotecario/prestamos.jsp`;
                                        _loader.out();
                                    }
                                };
                            } else if (r === 0) {
                                _loader.out();
                                _o = {
                                    html: `Ha ocurrido un error al registrar el préstamo! Inténtelo mas tarde... <i class="material-icons right">error</i>`,
                                    classes: "red darken-1", displayLength: 2000
                                };
                            } else if (r === -1) {
                                _loader.out();
                                _o = {
                                    html: `El ejemplar o el usuario que quiere asignar al préstamo no existe...<i class="material-icons right">find_replace</i>`,
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
                                    html: `El ejemplar que deseas prestar no se encuentra disponible o el usuario al que deseas asignar el préstamo no se encuentra activo en la plataforma...<i class="material-icons right">thumb_down</i>`,
                                    classes: "red darken-3", displayLength: 2000,
                                    completeCallback: `${location.origin}/Thot/Bibliotecario/libros.jsp`
                                };
                            }

                            M.toast(_o);
                        },
                        error: function (err) {
                            _loader.out();
                            M.toast({html: "En este momento no se puede establecer la conexión con el servidor. Inténtelo más tarde... <i class='material-icons right'>error</i>", classes: "red darken-5"});
                        }
                    });
                }
            });

            $("#tblUsers").DataTable();

            $('input[type=radio][name=rdbUser]').change(function(){
                $("#btnConfirm").removeAttr('disabled');

                let idUser = $(this).val(),
                    user = {
                        correo: $(`table tbody tr[data=${idUser}] td`).eq(1).html(),
                        nombre: $(`table tbody tr[data=${idUser}] td`).eq(0).html()                       
                    };
                    
                frmPrestamo.idUsuario.value = (idUser);                
                $("#mdlConfirm #userDataCont").html(`${user.nombre} <b>|</b> ${user.correo}`);
            });
        }

    });

})
();