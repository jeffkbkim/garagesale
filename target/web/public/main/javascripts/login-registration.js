var registering = false;
$(".register").click(function() {
    if (!registering) {
        registering = true;
        $(".registration").slideDown("fast");
        $(".login-button").empty();
        $(".login-button").append('Register');
    }
});