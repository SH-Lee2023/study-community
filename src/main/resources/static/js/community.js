const host = 'http://' + window.location.host; // http://localhost:8080
function logout() {
    Cookies.remove('Authorization', { path: '/' });
    window.location.href = host + "";
}