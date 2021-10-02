const url = "http://localhost:8090/";

document.getElementById("getReimbsButton").addEventListener("click", manReimbsFunc);

//GET Request functionality -----------------------------------------------------
async function manReimbsFunc() {

    let type = document.getElementById("reimbs-filter").value;
    console.log(type); //I'd like to log this for debugging

    //if user Selects all => get all reimbursements
    if(type === "All"){

        let response = await fetch(url + "tickets", {credentials: "include"});

        console.log(response);

        if(response.status === 200){ //if successful response

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

                //Next two columns, id like to make buttons
                let cell10 = document.createElement("td");
                if(stat === "Pending"){
                    let abutt = document.createElement("button");
                    abutt.setAttribute("class", "btn btn-success");
                    abutt.setAttribute("id", "abutt-" + reimb.reimb_id)
                    abutt.setAttribute("onclick", "approveFunc(this.id)");
                    //This makes the button's click event do what we need it to
                    abutt.innerHTML = "Approve";
                    cell10.appendChild(abutt); //append the button to the row
                }
                row.appendChild(cell10);

                let cell11 = document.createElement("td");
                if(stat === "Pending"){
                    let dbutt = document.createElement("button");
                    dbutt.setAttribute("class", "btn btn-danger");
                    dbutt.innerHTML = "Deny";
                    dbutt.setAttribute("onclick", "denyFunc(this.id)");
                    dbutt.setAttribute("id", "dbutt-" + reimb.reimb_id);
                    cell11.appendChild(dbutt); //append the button to the row
                }
                row.appendChild(cell11);

                //tr gets appended to the table body
                //a new row will be appended for every Reimbursement object that comes in
                document.getElementById("reimbBody").appendChild(row);

            }

        }

    } else {

        let response = await fetch(url + "tickets/filter/" + type, {credentials: "include"});

        console.log(response);

        if(response.status === 200){ //if successful response

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

                //Next two columns, id like to make buttons
                let cell10 = document.createElement("td");
                if(stat === "Pending"){
                    let abutt = document.createElement("button");
                    abutt.setAttribute("class", "btn btn-success");
                    abutt.setAttribute("id", "abutt-" + reimb.reimb_id)
                    abutt.setAttribute("onclick", "approveFunc(this.id)");
                    abutt.innerHTML = "Approve";
                    cell10.appendChild(abutt); //append the button to the row
                }
                row.appendChild(cell10);

                let cell11 = document.createElement("td");
                if(stat === "Pending"){
                    let dbutt = document.createElement("button");
                    dbutt.setAttribute("class", "btn btn-danger");
                    dbutt.innerHTML = "Deny";
                    dbutt.setAttribute("onclick", "denyFunc(this.id)");
                    dbutt.setAttribute("id", "dbutt-" + reimb.reimb_id);
                    cell11.appendChild(dbutt); //append the button to the row
                }
                row.appendChild(cell11);

                //tr gets appended to the table body
                //a new row will be appended for every Reimbursement object that comes in
                document.getElementById("reimbBody").appendChild(row);

            }

        }
    }

}

//PATCH Request Functionality ---------------------------------------------------------------------
async function approveFunc(id){
    let rId = id.replace('abutt-',''); //gives us the reimb_id we need for the request

}

async function denyFunc(id){
    let rId = id.replace('dbutt-','');
    
}