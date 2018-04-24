(function(){

    $(document).ready(function(){
        $.validator.addMethod('email', function(value, element) {
            return this.optional(element) || /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(value);
        }, 'Ingrese un correo válido.');
        
        $.validator.addMethod('codigo', function(value, element) {
            return this.optional(element) || /^[BU]\d{4}$/.test(value);
        }, 'Ingrese un código válido.');
        
        $.validator.addMethod('correo_codigo', function(value, element) {
            return this.optional(element) || /^((([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))|[BU]\d{4})$/.test(value);
	}, 'Ingrese un correo o un nombre de usuario válido!');

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
                let _loader = new Loader();
                _loader.in();
                $.ajax({
                    type: 'POST',
                    url: `${location.origin + location.pathname}RecuperarContra`,
                    data: {email: frmRecoverPass.txtUser.value.trim()},
                    success: function(r){
                        _loader.out();
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
    })
})
();