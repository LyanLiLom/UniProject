$(document).ready(function(){
    alert("àqwfqwdqw")

    $('#btn-login').click(function(){
        var username = $('#email').val()
        var password = $('#password').val()

        $.ajax({
            method: "POST",
            url: "http://localhost:8080/login",
            data: { 
                //tenthamso: giatri
                username: username, 
                password: password 
            }
          })
            .done(function( result ) {
              localStorage.setItem('token', result.data)
            });

        console.log(username,password)
    })
    

})