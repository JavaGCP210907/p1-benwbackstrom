--Need 5 tables for this employee reimbursement project
--one for the users' roles

CREATE TABLE userroles (
	user_role_id serial PRIMARY KEY,
	user_role VARCHAR(10)
);

--table of all users

CREATE TABLE users (
	user_id serial PRIMARY KEY,
	username VARCHAR(50) UNIQUE,
	"password" VARCHAR(50),
	user_first_name VARCHAR(100),
	user_last_name VARCHAR(100),
	user_email VARCHAR(150) UNIQUE,
	user_role_fk int REFERENCES userroles(user_role_id)
);

--the types of reimbursements

CREATE TABLE reimbursementtypes (
	reimb_type_id serial PRIMARY KEY,
	reimb_type VARCHAR(10)
);

--the statuses a reimbursement can have

CREATE TABLE reimbursementsstatuses (
	reimb_status_id serial PRIMARY KEY,
	reimb_status VARCHAR(10)
);

--table of all reimbursements

CREATE TABLE reimbursements (
	reimb_id serial PRIMARY KEY,
	reimb_amount int,
	reimb_submitted TIMESTAMP,
	reimb_resolved TIMESTAMP,
	reimb_description VARCHAR(250),
	reimb_receipt BYTEA,
	reimb_author int REFERENCES users(user_id),
	reimb_resolver int REFERENCES users(user_id),
	reimb_status_fk int REFERENCES reimbursementsstatuses(reimb_status_id),
	reimb_type_fk int REFERENCES reimbursementtypes(reimb_type_id)
);


--Roles of our "employees" are general employees and financial managers
INSERT INTO userroles (user_role)
VALUES ('Employee'),
	   ('Manager');
	  
--Types of reimbursement are LODGING, TRAVEL, FOOD, and OTHER
INSERT INTO reimbursementtypes (reimb_type)
VALUES ('Lodging'),
	   ('Travel'),
	   ('Food'),
	   ('Other');
	   
--Statuses are Pending, Approved, and Denied
INSERT INTO reimbursementsstatuses (reimb_status)
VALUES ('Pending'),
	   ('Approved'),
	   ('Denied');
	  

