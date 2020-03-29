$("button.add-to-bucket").click(function (event) {
    event.preventDefault();

    var productId = $("button.add-to-bucket").attr("product-id");

    $.post("api/buckets", {productId})
        .done(function () {
            alert("Product is being successfully added to bucket");
        })
        .fail(function () {
            alert("error");
        });
});
