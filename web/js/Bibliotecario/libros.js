(function(){
    $(document).ready(function(){
        $("#tblLibros").DataTable({
            "columnDefs": [
                {
                    "targets": [ 0 ],
                    "visible": false
                }
            ],
            "order": [[ 1, "asc" ]]
        });
    })

})
();