function pageLoad() {//loads when the page opens

    if(window.location.search === '?logout') {
        document.getElementById('content').innerHTML = '<h1>Logging out, please wait...</h1>';
        logout();
    } else {
        document.getElementById("loginButton").addEventListener("click", login);
    }

}
function login(event) {

    event.preventDefault();

    const form = document.getElementById("loginForm");//loads the form to login
    const formData = new FormData(form);

    fetch("/user/login", {method: 'post', body: formData}//calls API login in the user controller
    ).then(response => response.json()
    ).then(responseData => {

        if (responseData.hasOwnProperty('error')) {
            alert(responseData.error);
        } else {
            Cookies.set("username", responseData.username);
            Cookies.set("token", responseData.token);

            window.location.href = '/client/index.html';
        }
    });
}
function logout() {

    fetch("/user/logout", {method: 'post'}//calls API logout in the user controller
    ).then(response => response.json()
    ).then(responseData => {
        if (responseData.hasOwnProperty('error')) {

            alert(responseData.error);

        } else {

            Cookies.remove("username");
            Cookies.remove("token");

            window.location.href = '/client/index.html';

        }
    });

}
