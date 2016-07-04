$('#newtrans').click(function() {
  console.log('new trans clicked');
  $('.transaction-window').fadeIn();
});

$('.cancel-button').click(function() {
  $('.transaction-window').fadeOut();
});

$('.delete').click(function() {
  console.log('delete clicked');
  $(this).closest('tr').remove();
});


$("#search").on("keyup", function() {
  var query = $(this).val().toLowerCase().trim();

  $(".item-row").each( function() {
    var text = $(this).children().text().toLowerCase().trim();

    if (text.indexOf(query) != -1) {
      $(this).show();
    } else {
      $(this).hide();
    }
  });
});
