$(".register").click(function() {
    $(".registration").slideDown("fast");
    $(".mdl-card__title").slideDown("fast");
    $(".login-elements").slideUp("fast");
});

$(".cancel-button").click(function() {
    $(".registration").slideUp("fast");
    $(".mdl-card__title").slideUp("fast");
    $(".login-elements").slideDown("fast");
});

$(".login").click(function() {
    $(".login-form").submit();
    $(".incorrectError").slideDown("fast");
});

$(".register-button").click(function() {
    if ($("#usernameReg").val() == '' || $("#passwordnameReg").val() == ''
        || $("#fname").val() == '' || $("#lname").val() == ''
        || $("#email").val() == ''|| $("#phone").val() == '') {
        $(".registrationError").slideDown("fast");
    } else {
        $(".register-form").submit();
    }

});