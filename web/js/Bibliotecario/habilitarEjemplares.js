(function(){
   
    $(document).ready(function(){
        if(typeof frmEjemplar !== 'undefined'){
            let frmCant = $(frmEjemplar).length, validCountAux = 0;
            $(frmEjemplar).each(function(i, frm){
                $(frm).validate({
                    rules: {
                        txtObservaciones: 'required'
                    },
                    messages: {
                        txtObservaciones: 'Debes ingresar una observación...'
                    },
                    submitHandler: function (form) {
                        validCountAux++;
                    }
                })
            });
            
            $("#btnGuardar").click(function(){
                validCountAux = 0;
                $(frmEjemplar).submit();
                
                if(frmCant === validCountAux){
                    let ejemplares = [];
                    $(frmEjemplar).each((i, frm) => ejemplares.push({idEjemplar: frm.idEjemplar.value, observaciones: frm.txtObservaciones.value}));
                    
                    let _loader = new Loader();
                    _loader.in();
                    
                    $.ajax({
                        type: 'POST',
                        url: `${location.origin}/Thot/Ejemplares/Habilitar`,
                        data: {ejemplares: JSON.stringify(ejemplares)},
                        success: function(r){
                            r = parseInt(r);
                            let _o = {};
                            if(r === 1){
                                _o = {
                                    html: `Los ejemplares han sido habilitados éxitosamente! <i class="material-icons right">check_circle</i>`,
                                    classes: "green darken-1", displayLength: 1500,
                                    completeCallback: function(){
                                        let idLibro = new URL(location.href).searchParams.get("idLibro");
                                        location.href = `${location.origin}/Thot/Bibliotecario/gestionEjemplares.jsp?idLibro=${idLibro}`;
                                    }
                                };
                            }else if(r === 0){
                                _loader.out();
                                _o = {
                                    html: `Ha ocurrido un error al modificar los ejemplares! Inténtelo mas tarde... <i class="material-icons right">error</i>`,
                                    classes: "red darken-1", displayLength: 2000
                                };
                            }else if (r === -1) {
                                _loader.out();
                                _o = {
                                    html: `El cuerpo de la petición no es válido para ser procesado...<i class="material-icons right">block</i>`,
                                    classes: "red darken-4", displayLength: 2000
                                };
                            }
                            
                            M.toast(_o);
                        },
                        error: function(err){
                            _loader.out();
                            M.toast({html: "En este momento no se puede establecer la conexión con el servidor. Inténtelo más tarde... <i class='material-icons right'>error</i>", classes: "red darken-5"});
                        },
                        // finally: _loader.out()
                    });
                
                }else{
                    M.toast({html: "Ingrese todos los datos solicitados <i class='material-icons right'>warning</i>", classes: "yellow darken-3"});
                }
            })
        }
    });
    
})
();