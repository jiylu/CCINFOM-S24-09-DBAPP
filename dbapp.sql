CREATE SCHEMA IF NOT EXISTS ticketing_system;
USE ticketing_system;

DROP TABLE IF EXISTS EmployeeCreatesTicket;
DROP TABLE IF EXISTS TechnicianResolvesTicket;
DROP TABLE IF EXISTS TechnicianCancelsTicket;
DROP TABLE IF EXISTS Tickets;
DROP TABLE IF EXISTS EmployeeUsers;
DROP TABLE IF EXISTS TechnicianUsers;
DROP TABLE IF EXISTS Technicians;
DROP TABLE IF EXISTS Employees;
DROP TABLE IF EXISTS Categories;
DROP TABLE IF EXISTS UserAccounts;
DROP TABLE IF EXISTS Departments;

CREATE TABLE IF NOT EXISTS Departments (
    department_id INT AUTO_INCREMENT PRIMARY KEY,
    department_name VARCHAR(40) NOT NULL UNIQUE,
    active BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS UserAccounts(
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    role ENUM('Admin', 'Employee', 'Technician') NOT NULL,
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(15) NOT NULL
) AUTO_INCREMENT = 50000;

CREATE TABLE IF NOT EXISTS Employees (
	emp_id INT AUTO_INCREMENT PRIMARY KEY,
    last_name VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    dept_id INT NOT NULL,
    job_title VARCHAR(50) NOT NULL,
    ACTIVE BOOLEAN DEFAULT TRUE,
	CONSTRAINT fk_employee_department FOREIGN KEY (dept_id) REFERENCES Departments(department_id)
) AUTO_INCREMENT = 90000;

CREATE TABLE IF NOT EXISTS EmployeeUsers (
    user_id INT NOT NULL UNIQUE,
    emp_id INT NOT NULL UNIQUE,
    CONSTRAINT fk_emp_user_id FOREIGN KEY (user_id) REFERENCES UserAccounts(user_id),
    CONSTRAINT fk_emp_id FOREIGN KEY (emp_id) REFERENCES Employees(emp_id)
) AUTO_INCREMENT = 100000;

CREATE TABLE IF NOT EXISTS Technicians ( 
	technician_id INT AUTO_INCREMENT PRIMARY KEY,
    tech_lastName VARCHAR(50) NOT NULL,
    tech_firstName VARCHAR(50) NOT NULL,
    ACTIVE BOOLEAN DEFAULT TRUE
) AUTO_INCREMENT = 30000;

CREATE TABLE IF NOT EXISTS TechnicianUsers (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    technician_id INT NOT NULL UNIQUE,
    CONSTRAINT fk_tech_user_id FOREIGN KEY (user_id) REFERENCES UserAccounts(user_id),
    CONSTRAINT fk_technician_id FOREIGN KEY (technician_id) REFERENCES Technicians(technician_id)
);

CREATE TABLE IF NOT EXISTS Categories (
	category_id INT AUTO_INCREMENT PRIMARY KEY,  
    category_name VARCHAR(50) NOT NULL UNIQUE,
    active BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS Tickets (
    ticket_id INT AUTO_INCREMENT PRIMARY KEY,
    ticket_subject VARCHAR(100) NOT NULL,
    ticket_description VARCHAR(250) NOT NULL,
    category_id INT NOT NULL,
    creation_date DATETIME NOT NULL,
    emp_id INT NOT NULL,
    tech_id INT NOT NULL,
    status ENUM('Enqueued', 'Active', 'Resolved', 'Cancelled') NOT NULL,
    CONSTRAINT fk_emp_ticket FOREIGN KEY (emp_id) REFERENCES Employees(emp_id),
    CONSTRAINT fk_tech_ticket FOREIGN KEY (tech_id) REFERENCES Technicians(technician_id), 
    CONSTRAINT fk_category_ticket FOREIGN KEY (category_id) REFERENCES Categories(category_id)
) AUTO_INCREMENT = 40000;

CREATE TABLE IF NOT EXISTS ResolvedTickets (
	resolve_id INT AUTO_INCREMENT PRIMARY KEY,
    ticket_id INT NOT NULL UNIQUE,
    resolve_date DATETIME,
    CONSTRAINT fk_ticket_resolve_id FOREIGN KEY (ticket_id) REFERENCES Tickets(ticket_id)
) AUTO_INCREMENT = 160000; 

CREATE TABLE IF NOT EXISTS CancelledTickets (
	cancel_id INT AUTO_INCREMENT PRIMARY KEY, 
    ticket_id INT NOT NULL UNIQUE,
    cancel_date DATETIME, 
    CONSTRAINT fk_ticket_cancel_id FOREIGN KEY (ticket_id) REFERENCES Tickets(ticket_id)
) AUTO_INCREMENT = 170000;