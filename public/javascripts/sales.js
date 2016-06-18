$.get("/getsales", function(data) {

    var obj = data;
    $('#testSalesJSON').append(data);
    });

    var createButton = document.querySelector(".createButton");
              createButton.addEventListener('click', function() {
                $.post('/createsale',
                        {
                          name: $('#salename').val(),
                          location: $('#location').val(),
                          date: $('#date').val(),
                        });
              });
