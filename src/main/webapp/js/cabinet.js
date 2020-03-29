$.get("api/products")
    .done(function (products) {
        var cardsContent = "";
        jQuery.each(products, function (i, product) {
            cardsContent +=
                "<div class=\"card\">" +
                "<div class=\"card-body\">" +
                "<h5 class=\"card-title\">" + product.name + "</h5>" +
                "<p class=\"card-text\">" + product.description + "</p>" +
                "<p class=\"card-text\">" + product.price + " грн </p>" +
                "<a href='products?id=" + product.id + "' class='card-link'>Product details</a>"+
                "</div>" +
                "</div>"
        });
        $('.product-card').html(cardsContent);

    })
    .fail(function () {
        alert("Can't get products");
    });
