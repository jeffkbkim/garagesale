$(document).ready(function() {
    $('#share').on('click', function() {
         console.log(window.location.href);
         FB.ui(
               {
                 method: 'feed',
                 link: window.location.href,
                 caption: window.location.href,
               },
               function(response) {
                 console.log(response);
               }
             );
    });
})

