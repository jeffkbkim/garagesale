$(".register").click(function() {
        $(".registration").slideDown("fast");
        $(".login-elements").slideUp("fast");
});

$(".cancel-button").click(function() {
    $(".registration").slideUp("fast");
    $(".login-elements").slideDown("fast");
})
