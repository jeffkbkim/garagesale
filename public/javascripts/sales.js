(function() {
  $(function() {
  return $.get("/sales", function(sales) {
  return $.each(sale, function(index, sale) {
  return $('#sale').append($("<li>").text(sale.salename));
});
});
});

}).call(this);