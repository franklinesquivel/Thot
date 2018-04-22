(function(){

    $(document).ready(function(){

        jQuery.validator.setDefaults({
			// debug: true,
			// success: "valid"
        });

        $.validator.setDefaults({
            errorClass: 'invalid',
            // validClass: "valid",
            errorPlacement: function(error, element) {
                $(element).parent().find('span.helper-text').remove();
                $(element).parent()
                .append(`<span class='helper-text' data-error='${error.text()}'></span>`);
            }
          });
        
        $.validator.addMethod('email', function(value, element) {
	        return this.optional(element) || /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(value);
        }, 'Ingrese un correo válido.');
        
        $.validator.addMethod('codigo', function(value, element) {
	        return this.optional(element) || /^[BU]\d{4}$/.test(value);
        }, 'Ingrese un código válido.');
        
        $.validator.addMethod('correo_codigo', function(value, element) {
	        return this.optional(element) || /^((([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))|[BU]\d{4})$/.test(value);
	    }, 'Ingrese un correo o un nombre de usuario válido!');
        
        $.validator.addMethod('nombre', function(value, element) {
	    return this.optional(element) || /^([A-Z]|[a-z]|[ñÑ])[a-zA-Z ñÑáéíóú]*$/.test(value);
        }, 'Ingrese un nombre/apellido válido.');

        $(frmLogin).validate({
            rules: {
                txtUser: {
                    required: true,
                    correo_codigo: true
                },
                txtPassword: 'required'
            },
            messages: {
                txtUser: {
                    required: 'Este campo es requerido!',
                },
                txtPassword: 'Este campo es requerido!'
            },
            // errorElement : 'div',
            submitHandler: function(form) {
                form.submit();
            }
        });

        $(frmRecoverPass).validate({
            rules: {
                txtUser: {
                    required: true,
                    correo_codigo: true
                }
            },
            messages: {
                txtUser: {
                    required: 'Este campo es requerido!',
                }
            },
            submitHandler: function(form) { 
                // console.log(`${location.origin + location.pathname}RecuperarContra`);
                $.ajax({
                    type: 'POST',
                    url: `${location.origin + location.pathname}RecuperarContra`,
                    data: {email: frmRecoverPass.txtUser.value.trim()},
                    success: function(r){
                        r = parseInt(r);
                        let alertCont;

                        if(r == 1){
                            alertCont = $(mainAlertCont);
                            msg = "El correo ha sido mandado éxitosamente!";
                            $(frmAlertCont).html("");
                            $('#mdlRecover').close();
                            frmLogin.txtUser.value = frmRecoverPass.txtUser.value.trim();
                            frmRecoverPass.txtUser.value = "";
                        }else if(r == -1){
                            alertCont = $(frmAlertCont);
                            msg = "El usuario con las credenciales ingresadas no ha sido encontrado...";
                            $(mainAlertCont).html("");
                            frmRecoverPass.txtUser.focus();
                        }

                        alertCont.html("");

                        alertCont.append(`
                            <div class="alert center ${r == 1 ? 'green' : 'yellow'} ${r == 1 ? 'green' : 'yellow'}-text text-darken-3" >
                                <strong>${msg}</strong>
                            </div>
                        `);
                    },
                    error: function(err){
                        console.log(err);
                    }
                })
            }
        });

        $("#btnRecover").click(function(){
            $(frmRecoverPass).submit();
        })
        
        //Registro
        $(frmRegister).validate({
            rules: {
                txtName: {
                    required: true,
                    nombre: true
                },
                txtLastName: {
                    required: true,
                    nombre: true
                },
                txtEmail: {
                    required: true,
                    email: true
                },
                txtBirthdate: {
                    required: true
                }
            },
            messages: {
                txtName: 'Este campo es requerido!',
                txtLastName: 'Este campo es requerido!',
                txtEmail: 'Este campo es requerido!',
                txtBirthdate: 'Este campo es requerido!'
            },
            submitHandler: function(form) {
                $.ajax({
                    type: 'POST',
                    url: `${location.origin + location.pathname}Registro`,
                    data: {
                        name: frmRegister.txtName.value.trim(),
                        lastName: frmRegister.txtLastName.value.trim(),
                        email: frmRegister.txtEmail.value.trim(),
                        birthdate: frmRegister.txtBirthdate.value.trim()
                    },
                    success: function(r){
                        r = parseInt(r);
                        if(r == 1){
                            frmLogin.txtUser.value = frmRegister.txtEmail.value.trim();
                            $('#mdlRegister').modal('close');
                            $(frmRegister)[0].reset();
                            M.toast({html: 'Credenciales enviada al email!'})
                        }else if(r == -1){
                            M.toast({html: 'Correo existente en el sitio!'})
                        }else if(r == -2){
                            M.toast({html: 'Algunos datos no son válidos!'})
                        }
                    },
                    error: function(err){
                        console.log(err);
                    }
                })//Fin ajax
            }
        });
    })
})
();