var transArray = [];
var dialog = document.querySelector('dialog');
if (! dialog.showModal) {
  dialogPolyfill.registerDialog(dialog);
}

function bindAddItem(selector) {
    $('#' + selector).click(function() {
        dialog.showModal();
        $('.submit').click(function() {
            var itemID = parseInt(selector);
            var itemName = document.getElementById('name' + selector).innerHTML;
            var quantity = parseInt($('#quantity').val());
            if ($('#minimum' + itemID).val() == 0) {
                var minimum = document.getElementById('price' + selector).innerHTML;
            } else {
                var minimum = parseFloat($('#minimum' + itemID).val());
            }
            var itemPrice = document.getElementById('price' + selector).innerHTML;
            if (parseInt($('#priceAdjust').val()) > 0) {
                if (parseInt($('#priceAdjust').val()) >= minimum) {
                    var itemPrice = "$" + parseFloat($('#priceAdjust').val());
                } else if ($('#minimum' + itemID).val() != 0) {
                    alert("Price Adjust is above set minimum price");
                } else {
                    alert("Minimum is not set");
                }
            }


            var buyer = $('#buyerName').val();
            var payment = $('input[name=payment]:checked').val();
            var conglomerate = '<li><h4>' + quantity + 'x '+ itemName + '</h4><br /><p>' + buyer + ' (' + payment + ')</p></li>';
            if (quantity > 0) {
                $('.transactions').append(conglomerate);
                $('.price').append('<li><h5>' + itemPrice + '</h5></li>');
                transArray.push({
                    "id": itemID,
                    "quantity": quantity,
                    "buyer": buyer,
                    "payment": payment
                });
            } else {
                alert('Please fill out the quantity and buyer name.');
            }
            dialog.close();
            $(".submit").unbind();
        });

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