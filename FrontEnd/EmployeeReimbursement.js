const url = "http://localhost:8090/" //putting base URL in a variable for cleaner code

//add functionality to our buttons using eventlisteners
//when button gets clicked, appropriate function is called
document.getElementById("emp-loginButton").addEventListener("click", empLoginFunc);
document.getElementById("man-loginButton").addEventListener("click", manLoginFunc);

//login functionality ------------------------------------------------

//first for general employees
async function empLoginFunc(){

    //gather user input from login inputs
    let usern = document.getElementById("emp-username").value;
    let userp = document.getElementById("emp-password").value;

    //make JS object to send as JSON
    let user = {
        username: usern,
        password: userp
    };

    console.log(user); //debugging statement to make sure its all good

    //Fetch request to the server
    let response = await fetch(url + "emplogin", {

        method: "POST", //send a POST request
        body: JSON.stringify(user), //turn our JavaScript into JSON
        credentials: "include"

    });

    console.log(response.status); //debugging statement

    //control flow based on login success/fail
    if(response.status === 200){
        localStorage.setItem("secretUserCredentials", user); //place user credentials into local storage for later
        window.location.href = "EmployeeMenu.html"
    } else{
        document.getElementById("login-error").innerText = "Login Failed! Please try again."
    }

}

//now for financial managers
async function manLoginFunc(){

    //gather user input from login inputs
    let usern = document.getElementById("man-username").value;
    let userp = document.getElementById("man-password").value;

    //make JS object to send as JSON
    let user = {
        username: usern,
        password: userp
    };

    console.log(user); //debugging statement to make sure its all good

    //Fetch request to the server
    let response = await fetch(url + "manlogin", {

        method: "POST", //send a POST request
        body: JSON.stringify(user), //turn our JavaScript into JSON
        credentials: "include"

    });

    console.log(response.status); //debugging statement

    //control flow based on login success/fail
    if(response.status === 200){
        localStorage.setItem("secretUserCredentials", user);
        window.location.href = "ManagerMenu.html"
    } else{
        document.getElementById("login-error").innerText = "Login Failed! Please try again."
    }
}
