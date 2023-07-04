const host = 'http://' + window.location.host;
function signup() {
    let username = $('.signup-name-box').val();
    let password = $('.signup-pw-box').val();
    let data = {'username': username, 'password': password};
    $.ajax({
        type: "POST",
        url: "/users/signup",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function(response) {
            alert("회원가입이 완료되었습니다.");
            window.location.href = host + "";
        }
    });
}