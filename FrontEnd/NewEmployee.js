const url = "http://localhost:8090/"

document.getElementById("submitForm").addEventListener("click", addUserFunc);

//Function for creating a new user/employee/account --------------------------------
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
    let user = {
        username: usern,
        password: userp,
        first_name: fName,
        last_name: lName,
        email: usere,
        user_role_fk: {
            user_role_id: userRoleId //wont need to have user_role title because DB will do that for us, just like with the user_id
        }
    };

    console.log(user);

    //Fetch request to the server
    let response = await fetch(url + "users", {

        method: "POST",
        body: JSON.stringify(user)

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
        }, 2000); //redirects to main page in 2 seconds
    } else {
        let res = document.getElementById("form-response");
        //Show fail message
        res.setAttribute("style", "color:red;");
        res.innerHTML = "Account Creation Failed! Please try again.";
    }
}