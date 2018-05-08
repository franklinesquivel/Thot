(function(){
    $(document).ready(function(){
       $("#tblPrestamos").DataTable();
       $('.btnRenovar').click(function(){
            $('#mdlRenovar #idPrestamo').val($(this).attr('idprestamo'));
       }); 
       
       $(document).on('click', '#mdlRenovar', function (e) {
            if ($(e.target).attr("id") == $("#btnRenovar").attr("id")) {
                renovar();
            }
        });
    });
    
    
    function renovar(){
        if($('#mdlRenovar #idPrestamo').val() != null){
            let _loader = new Loader();
            _loader.in();
            $.ajax({
               type: 'POST',
                url: `${location.origin}/Thot/Prestamos/Renovar`,
                data: {
                    idPrestamo: $('#mdlRenovar #idPrestamo').val()
                },
                success: function(data){
                    let _loader = new Loader();
                    _loader.in();
                    data = parseInt(data);
                    if(data === 1){
                        M.toast({html: 'Renovación exitosa!', completeCallback: function(){
                            _loader.out();
                            location.reload();
                        }});
                    }
                    _loader.out();
                }
            });
            
        }
    }
})()
