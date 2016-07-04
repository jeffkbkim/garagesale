$ ->
    $.get "/createsales", (sales) ->
        $.each sales, (index, sale) ->
            $('#sale').append $("<li>").text sale.saleID