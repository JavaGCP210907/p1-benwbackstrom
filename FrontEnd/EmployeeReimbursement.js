const url = "http://localhost:8090/" //putting base URL in a variable for cleaner code

//add functionality to our buttons using eventlisteners
//when button gets clicked, appropriate function is called
document.getElementById("emp-loginButton").addEventListener("click", empLoginFunc);
document.getElementById("man-loginButton").addEventListener("click", manLoginFunc);
document.getElementById("submitForm").addEventListener("click", addUserFunc);

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
        window.location.href = "ManagerMenu.html"
    } else{
        document.getElementById("login-error").innerText = "Login Failed! Please try again."
    }
}

async function addUserFunc(){
    let usern = document.getElementById("username").value;
    let userp = document.getElementById("userp").value;
    let fName = document.getElementById("firstname").value;
    let lName = document.getElementById("lastname").value;
    let usere = document.getElementById("useremail").value;

    //in order to get the value of the radio point
    let ele = document.getElementsByName("radio");
    let userRoleId; //value to make the value
    for(i = 0; i < ele.length; i++){
        if(ele[i].checked){
            console.log(ele[i].value); //Debug purposes
            userRoleId = i+1;
        }
    }

    //make our new user JavaScript Object
    user = {
        username: usern,
        password: userp,
        first_name: fName,
        last_name: lName,
        email: usere,
        user_role_fk: {
            user_role_id: userRoleId //wont need to have user_role title because DB will do that for us, just like with the user_id
        }
    }

    console.log(user);

    //Fetch request to the server
    let response = await fetch(url + "users", {

        method: "POST",
        body: JSON.stringify(user),

    });

    console.log(response.status);

    if(response.status === 201){
        let res = document.getElementById("form-response");
        //Show success message
        res.setAttribute("style", "color:green;");
        res.innerHTML = "Account Created! Will be redirected back to main page.";
        //Send them back to the main page
        let tID = setTimeout(function () {
            window.location.href = "EmployeeReimbursement.html";
            window.clearTimeout(tID);
        }, 5000); //redirects to main page in 5 seconds
    } else {
        let res = document.getElementById("form-response");
        //Show fail message
        res.setAttribute("style", "color:red;");
        res.innerHTML = "Account Creation Failed! Please try again.";
    }
}
