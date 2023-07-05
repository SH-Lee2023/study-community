const host = 'http://' + window.location.host; // http://localhost:8080
function logout() {
    Cookies.remove('Authorization', { path: '/' });
    window.location.href = host + "";
}

function goHome() {
    window.location.href = host + "/home";
}

function editProfile(id) {
    let nickname = $('#edit-nickname').val()
    let data = {'username': nickname};
    $.ajax({
        type: "PUT",
        url: `/users/${id}`,
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            window.location.reload();
        },
        error: function(response) {
            alert("서버 오류")
            window.location.reload();
        }
    });
}