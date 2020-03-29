$("button.createProduct")
    .click(
        function (event) {
            event.preventDefault();
            var name = $("form.createProduct input.productName").val();
            var description = $("form.createProduct input.productDescription").val();
            var price = $("form.createProduct input.productPrice").val();

            var product = {
                name: name,
                description: description,
                price: price
            };
            $.post("api/products", product)
                .done(function (data, textStatus, xhr) {
                    alert('Success');
                    $("form")[0].reset();
                })
                .fail(function (data, textStatus, xhr) {
                    alert(data.responseText);
                });
        });
