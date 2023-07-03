const host = 'http://' + window.location.host;
function login() {
    let username = $('.login-name-box').val()
    let password = $('.login-pw-box').val()
    let data = {'username': username, 'password': password};
    $.ajax({
        type: "POST",
        url: "/users/login",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function(response) {
            alert("로그인이 완료되었습니다.");
            window.location.href = host + "/home";
        }
    });

}