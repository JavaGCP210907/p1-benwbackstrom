const url = "http://localhost:8090/";

document.getElementById("getReimbsButton").addEventListener("click", empReimbsFunc);
document.getElementById("submitForm").addEventListener("click", addReimbFunc);

//GET Request Functionality ----------------------------------------------------------
async function empReimbsFunc(){

    let authoru = localStorage.getItem("useru"); //get user's username from the local storage
    
    let response = await fetch(url + "tickets/past/" + authoru, {credentials: "include"});

    if(response.status === 200){

        let data = await response.json();

        //For every Reimbursement object we get back, put it in the table
        for(let reimb of data){

            let row = document.createElement("tr"); //create a table row

            let cell = document.createElement("td"); //create a cell for the field
            cell.innerHTML = reimb.reimb_id; //fill the cell with appropriate data
            row.appendChild(cell); //add the td element to the tr element

            //do this for every field in the table
            let cell2 = document.createElement("td");
            cell2.innerHTML = reimb.reimb_amount;
            row.appendChild(cell2);

            let cell3 = document.createElement("td");
            cell3.innerHTML = reimb.reimb_submitted;
            row.appendChild(cell3);

            let cell4 = document.createElement("td");
            if(reimb.reimb_resolved !== undefined){
                cell4.innerHTML = reimb.reimb_resolved;
            }
            row.appendChild(cell4);

            let cell5 = document.createElement("td");
            cell5.innerHTML = reimb.reimb_description;
            row.appendChild(cell5);

            let cell6 = document.createElement("td");
            cell6.innerHTML = reimb.reimb_author.username;
            row.appendChild(cell6);

            let cell7 = document.createElement("td");
            //need to check if there is an author first

            let cell8 = document.createElement("td");
            let stat = reimb.reimb_status_fk.reimb_status; //putting this field in a variable for cleaner code
            cell8.innerHTML = stat
            
            if(stat !== "Pending"){ //if there is an author (Not Pending)
                cell7.innerHTML = reimb.reimb_resolver.username;
            }
            row.appendChild(cell7);
            row.appendChild(cell8);

            let cell9 = document.createElement("td");
            cell9.innerHTML = reimb.reimb_type_fk.reimb_type;
            row.appendChild(cell9);

            //tr gets appended to the table body
            //a new row will be appended for every Reimbursement object that comes in
            document.getElementById("reimbBody").appendChild(row);

        }

    }

}

//POST Request Functionality -------------------------------------------------------------
async function addReimbFunc(){

    let author = {
        username: localStorage.getItem("useru"),
        password: localStorage.getItem("userp")
    };

    //get inputs from form fields
    let ramount = Number(document.getElementById("amount").value);
    let rdesc = document.getElementById("description").value;
    let rtype = Number(document.getElementById("type-filter").value);

    let reimb = {
        reimb_amount: ramount,
        reimb_description: rdesc,
        reimb_author: author,
        reimb_type_fk: {
            reimb_type_id: rtype
        }
    };

    console.log(reimb); //debug statement

    let response = await fetch(url + "tickets", {

        method: "POST",
        body: JSON.stringify(reimb),
        credentials: "include"

    })

    if(response.status === 201){
        let res = document.getElementById("form-response");
        //Show success message
        res.setAttribute("style", "color:green;");
        res.innerHTML = "Ticket Created! Refresh to see changes.";
    } else{
        let res = document.getElementById("form-response");
        //Show fail message
        res.setAttribute("style", "color:red;");
        res.innerHTML = "Ticket Creation Failed! Please try again.";
    }

}