function pageLoad() {

    let now = new Date();

    let myHTML = '<div style="text-align:center;">'
        + '<h1>Welcome to my API powered website!</h1>'
        + '<img src="/client/img/Oranges.jpg/"  alt="Logo"/>'
        + '<div style="font-style: italic;">'
        + 'Generated at ' + now.toLocaleTimeString()
        + '</div>'
        + '</div>';

    document.getElementById("testDiv").innerHTML = myHTML;

}

function checkLogin() {

    let Username = Cookies.get("Username");
    let logInHTML = '';
    if (Username === undefined) {
        logInHTML = "Not logged in. <a href='/client/login.html'>Log in</a>";
    } else {
        logInHTML = "Logged in as " + username + ". <a href='/client/login.html?logout'>Log out</a>";
    }
    document.getElementById("loggedInDetails").innerHTML = logInHTML;
}





