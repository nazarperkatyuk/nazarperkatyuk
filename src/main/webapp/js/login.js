$('.message a').click(function () {
    loginRegisterSwitch();
});

function loginRegisterSwitch() {
    $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
}

$("button.register")
    .click(
        function (event) {
            event.preventDefault();
            var firstName = $("form.register-form input.firstName").val();
            var lastName = $("form.register-form input.lastName").val();
            var email = $("form.register-form input.email").val();
            var password = $("form.register-form input.registerPass").val();
            if (firstName == '' || lastName == '' || email == '' || password == '') {
                alert("Please fill all fields...!!!!!!");
            } else if ((password.length) < 8) {
                alert("Password should atleast 8 character in length...!!!!!!");
            } else {
                var userRegistration = {
                    firstName,
                    lastName,
                    email,
                    password
                };

                $.post("register", userRegistration,)
                    .done(function (data, textStatus, xhr) {
                        if (xhr.status === 201) {
                            $("form")[0].reset();
                            $("form")[1].reset();
                            loginRegisterSwitch();
                        } else {
                            alert("error while creating a user");
                        }
                    })
                    .fail(function () {
                        alert("error while creating a user");
                    });
                ;
            }
        });

$("button.login").click(function (event) {
    event.preventDefault();
    var email = $("form.login-form input.logEmail").val();
    var password = $("form.login-form input.loginPass").val();

    if (email == '' || password == '') {
        alert("Please fill login form!");
    } else {
        var userLogin = {
            email,
            password
        };
        $.post("login", userLogin)
            .done(function (data, textStatus, xhr) {
                if (xhr.status === 200) {
                    window.location = "http://localhost:8081/IShop/cabinet.jsp";
                } else {
                    alert("error while authorizing the user");
                }
            })
            .fail(function () {
                alert("error while authorizing the user");
            });
    }
});