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