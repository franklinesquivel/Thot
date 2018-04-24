(function(){
    $(document).ready(function(){
        M.AutoInit();

        if($('.modal').length > 0){
            $('.modal').modal();
        }
        
        jQuery.validator.setDefaults({
            // debug: true,
            // success: "valid"
        });

        $.validator.setDefaults({
            errorClass: 'invalid',
            validClass: "none",
            errorPlacement: function (error, element) {
                $(element).parent().find('span.helper-text').remove();
                $(element).parent()
                        .append(`<span class='helper-text' data-error='${error.text()}'></span>`);
            }
        });
    });

})
();