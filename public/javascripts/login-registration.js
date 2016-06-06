$(".register").click(function() {
        $(".registration").slideDown("fast");
        $(".login-elements").slideUp("fast");
});

$(".cancel-button").click(function() {
    $(".registration").slideUp("fast");
    $(".login-elements").slideDown("fast");
});

$(".login").click(function() {
    $(".login-form").submit();
});

$(".register-button").click(function() {
    $(".register-form").submit();
});
