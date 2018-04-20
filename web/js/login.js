(function(){

    $(document).ready(function(){

        jQuery.validator.setDefaults({
			// debug: true,
			// success: "valid"
        });
        
        $.validator.addMethod('email', function(value, element) {
	        return this.optional(element) || /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(value);
	    }, 'Ingrese un valor válido.');

        $(frmLogin).validate({
            rules: {
                txtUser: 'required',
                txtPassword: 'required'
            },
            messages: {
                txtUser: 'Este campo es requerido!',
                txtPassword: 'Este campo es requerido!'
            },
            errorElement: "em",
			errorPlacement: function ( error, element ) {
                error.addClass("text-danger frm-error");

                error.insertAfter(element.parent());
                // if ( element.prop( "type" ) === "checkbox" ) {
                //     error.insertAfter( element.parent( "label" ) );
                // } else {
                //     error.insertAfter(element);
                // }
            },
            submitHandler: function(form) {
                form.submit();
            }
        });

        $(frmRecoverPass).validate({
            rules: {
                txtUser: 'required'
            },
            messages: {
                txtUser: 'Este campo es requerido!',
            },
            errorElement : 'em',
            errorPlacement: function(error, element) {
                error.addClass("text-danger frm-error");
                error.insertAfter(element.parent());
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
                            $('#mdlRecover').modal('hide');
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
                            <div class="alert alert-${r == 1 ? 'success' : 'warning'} alert-dismissible" role="alert">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
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

    })

})
();