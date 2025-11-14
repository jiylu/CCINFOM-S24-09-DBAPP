CREATE SCHEMA IF NOT EXISTS ticketing_system;
USE ticketing_system;

DROP TABLE IF EXISTS TicketLogs;
DROP TABLE IF EXISTS Tickets;
DROP TABLE IF EXISTS Technicians;
DROP TABLE IF EXISTS Employees;
DROP TABLE IF EXISTS Departments;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Categories;

CREATE TABLE IF NOT EXISTS Users(
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(15) NOT NULL,
    role ENUM('Technician', 'Employee', 'Admin') NOT NULL,
    active BOOLEAN DEFAULT TRUE 
) AUTO_INCREMENT = 10000;

CREATE TABLE IF NOT EXISTS Departments (
    department_id INT AUTO_INCREMENT PRIMARY KEY,
    department_name VARCHAR(40) NOT NULL UNIQUE,
    active BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS Employees (
	emp_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL UNIQUE, 
    last_name VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    dept_id INT NOT NULL,
    job_title VARCHAR(50) NOT NULL,
    CONSTRAINT fk_employee_user FOREIGN KEY (user_id) REFERENCES Users(user_id),
	CONSTRAINT fk_employee_department FOREIGN KEY (dept_id) REFERENCES Departments(department_id)
) AUTO_INCREMENT = 90000;

CREATE TABLE IF NOT EXISTS Technicians ( 
	technician_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL UNIQUE,
    tech_lastName VARCHAR(50) NOT NULL,
    tech_firstName VARCHAR(50) NOT NULL,
    CONSTRAINT fk_technician_user FOREIGN KEY (user_id) REFERENCES Users(user_id)
) AUTO_INCREMENT = 30000;

CREATE TABLE IF NOT EXISTS Categories (
	category_id INT AUTO_INCREMENT PRIMARY KEY,  
    category_name VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS Tickets (
	ticket_id INT AUTO_INCREMENT PRIMARY KEY,
    ticket_subject VARCHAR(100) NOT NULL,
    ticket_description VARCHAR(250) NOT NULL, 
    category_id INT NOT NULL,
    employee_id INT NOT NULL,
    technician_id INT NOT NULL,
    creation_date DATETIME, 
    resolve_date DATETIME,
    status ENUM('Enqueued', 'Active', 'Resolved', 'Cancelled') NOT NULL,
    CONSTRAINT fk_category_ticket FOREIGN KEY (category_id) REFERENCES Categories(category_id),
    CONSTRAINT fk_employee_ticket FOREIGN KEY (employee_id) REFERENCES Employees(emp_id),
    CONSTRAINT fk_technician_ticket FOREIGN KEY (technician_id) REFERENCES Technicians(technician_id)
) AUTO_INCREMENT = 40000;

SELECT
	d.department_name,
    YEAR(t.creation_date) AS year,
    c.category_name,
    COUNT(t.ticket_id) AS total_tickets
FROM departments d
JOIN employees e ON d.department_id = e.dept_id
JOIN tickets t ON e.emp_id = t.employee_id
JOIN categories c ON t.category_id = c.category_id
GROUP BY d.department_name, YEAR(t.creation_date), c.category_name
ORDER BY d.department_name, year;