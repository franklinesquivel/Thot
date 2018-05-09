(function(){

    $(document).ready(function(){
        let idUsuario = null, idProceso = $('#chkTipoProceso').prop('checked') ? 'R' : 'P',
        states = {
            'EP': 'En proceso',
            'FO': 'Finalizado',
            'VO': 'Vencido',
            'EO': 'Efectuado',
            'VO': 'Vencido',
            'VE': 'Vigente'
        };

        let tblUsers = $("#tblUsers").DataTable({
            "columnDefs": [
                {
                    "targets": [ 0 ],
                    "visible": false
                }
            ],
        });

        $("#tblUsers").css('width', '90%');
    
        let tblRecord = $("#tblRecord").DataTable({
            "columnDefs": [
                {
                    "targets": [ 0 ],
                    "visible": false
                }
            ],
            "ajax": `${location.origin}/Thot/Historial?idUsuario=${idUsuario}&tipoProceso=${idProceso}`,
            "columns": [
                {"data": "idProceso"},
                {"data": "inicio"},
                {"data": "limite"},
                {"data": "estado"},
            ]
        });

        $('.btnSelectUser').click(function(){
            idUsuario = $(this).attr('idUsuario');
            tblRecord.ajax.url(`${location.origin}/Thot/Historial?idUsuario=${idUsuario}&tipoProceso=${idProceso}`).load();
        });

        $('#chkTipoProceso').change(function(){
            idProceso = $(this).prop('checked') ? 'R' : 'P';
            tblRecord.ajax.url(`${location.origin}/Thot/Historial?idUsuario=${idUsuario}&tipoProceso=${idProceso}`).load();
        });

    });

})
();