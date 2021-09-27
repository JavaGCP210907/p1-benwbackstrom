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
--reimb_receipt BYTEA, for when I add back in BLOBs

CREATE TABLE reimbursements (
	reimb_id serial PRIMARY KEY,
	reimb_amount int,
	reimb_submitted TIMESTAMP,
	reimb_resolved TIMESTAMP,
	reimb_description VARCHAR(250),
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
	  
--SELECT statements to help me 
	  
SELECT * FROM userroles;

SELECT * FROM reimbursementsstatuses;

SELECT * FROM reimbursementtypes;

SELECT * FROM reimbursements;


--Testing to see what I can do
INSERT INTO reimbursements (reimb_amount, reimb_submitted, reimb_description, reimb_status_fk, reimb_type_fk)
VALUES (100, '2021-09-25 4:54:35', 'Test', 1, 2);

UPDATE reimbursements SET (reimb_resolved, reimb_status_fk) = ('2021-10-25 4:23:35', 2) WHERE reimb_id = 3;

TRUNCATE TABLE reimbursements;

--Insert data for users and reimbursements
INSERT INTO users (username, "password", user_first_name, user_last_name, user_email, user_role_fk)
VALUES ('bwb', 'password', 'Benjamin', 'Backstrom', 'benwbackstrom@gmail.com', 2),
	   ('joestat', 'password1', 'Joe', 'Stat', 'jstat@yahoo.com', 1),
	   ('starlord', 'open', 'Peter', 'Quill', 'pqlord@gmail.com', 1),
	   ('finman', 'letmein', 'Timothy', 'Black', 'timblack@hotmail.com', 2),
	   ('coolweb12', 'password', 'Gwen', 'Stacy', 'gstacy@oxford.edu', 1);
	  
SELECT * FROM users;

INSERT INTO reimbursements (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_author, reimb_resolver, reimb_status_fk, reimb_type_fk)
VALUES (1000, '2019-09-25 16:54:35', '2019-09-29 10:23:10', 'Took plane to HQ', 2, 1, 2, 2),
	   (250, '2017-03-11 12:34:05', '2017-03-20 13:23:49', 'Had dinner for family', 5, 1, 3, 3),
	   (135, '2020-11-05 13:50:45', '2020-11-14 09:10:35', 'Company Lunch', 3, 4, 2, 3),
	   (45, '2021-03-29 15:45:00', '2021-04-01 10:17:01', 'Gas for Company Car', 5, 1, 2, 2);

SELECT * FROM reimbursements;

--also add some pending ticket requests
INSERT INTO reimbursements (reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_status_fk, reimb_type_fk)
VALUES (150, '2021-09-27 15:07:23', 'Hotel for Client Meeting', 3, 1, 1),
	   (500, '2021-09-28 09:59:13', 'Office Repairs', 2, 1, 4);
	  
	  
DROP TABLE reimbursements;


