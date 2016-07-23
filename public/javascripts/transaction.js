var transArray = [];

function bindAddItem(selector) {
    $('#' + selector).click(function() {
        var itemID = selector;
        var itemName = document.getElementById('name' + selector).innerHTML;
        var quantity = 1;
        var conglomerate = '<li><h4>' + quantity + 'x '+ itemName + '</h4><br /><p>' + itemName + ' (' + quantity + ')</p></li>';
        var itemPrice = document.getElementById('price' + selector).innerHTML;
        var buyer = "test";
        var payment = "test2";
        if (quantity > 0) {
            $('.transactions').append(conglomerate);
            $('.price').append('<li><h5>' + itemPrice + '</h5></li>');
            document.getElementById("totalCost").innerHTML = itemPrice;
            transArray.push({
                "id": itemID,
                "quantity": quantity,
                "buyer": buyer,
                "payment": payment
            });
        } else {
            alert('Please fill out the quantity and buyer name.');
        }
    });
}

/*

$('.add').click(function () {
    var itemID = parseInt($('#itemSelection').val());
    var itemName = $('#itemSelection option:selected').text();
    var quantity = parseInt($('#quantity').val());
    var buyer = $('#buyer').val();
    var payment = $('#paymentSelection option:selected').text();
    var conglomerate = '<li><h4>' + quantity + 'x '+ itemName + '</h4><br /><p>' + itemName + ' (' + quantity + ') to ' + buyer + '</p></li>';
    var itemPrice = "$@Item.fetchItemById(1).getPrice";
    if (quantity > 0 && buyer.length > 0) {
        $('.transactions').append(conglomerate);
        $('.price').append('<li><h5>' + itemPrice + '</h5></li>');
        document.getElementById("totalCost").innerHTML = itemPrice;
        transArray.push({
            "id": itemID,
            "quantity": quantity,
            "buyer": buyer,
            "payment": payment
        });
    } else {
        alert('Please fill out the quantity and buyer name.');
    }
});


$("#search").on("keyup", function () {
    var query = $(this).val().toLowerCase().trim();

    $(".item-row").each(function () {
        var text = $(this).children().text().toLowerCase().trim();

        if (text.indexOf(query) != -1) {
            $(this).show();
        } else {
            $(this).hide();
        }
    });
});
*/