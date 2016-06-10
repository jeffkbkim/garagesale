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

$(".cancel").click(function() {
    $(".login-input").val("");
});

$(".register-button").click(function() {
    $(".register-form").submit();
});

$(".login-input").keyup(function(event){
    if(event.keyCode == 13) { // ENTER
        $(".login").click();
    }
});
