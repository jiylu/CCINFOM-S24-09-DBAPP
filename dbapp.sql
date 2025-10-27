CREATE SCHEMA IF NOT EXISTS ticketing_system;

USE ticketing_system;

-- Table creation 

CREATE TABLE IF NOT EXISTS Departments (
	department_id INT AUTO_INCREMENT PRIMARY KEY,
	department_name VARCHAR(50) NOT NULL
);

INSERT IGNORE INTO Departments (department_name)
VALUES
	('Human Resources'),
	('Finance'),
	('Customer Support'),
	('Sales'),
	('Operations'),
	('Marketing'),
	('Administration'),
	('Information Technology');

CREATE TABLE IF NOT EXISTS Employees (
	emp_id INT PRIMARY KEY, 
    last_name VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    dept_id INT NOT NULL,
    role VARCHAR(50) NOT NULL,
	active BOOLEAN DEFAULT TRUE,
	CONSTRAINT fk_employee_department FOREIGN KEY (dept_id) REFERENCES Departments(department_id)
);

-- delete ko na to mamaya inayos ko lang forrmat
-- dapat pala last_name, first_name

DELETE FROM Employees
WHERE emp_id = 90070;

INSERT IGNORE INTO Employees(emp_id, last_name, first_name, dept_id, role) 
VALUES 
	(90122, 'Rivera', 'Montano', 4, 'Senior Coordinator'),
    (90756, 'Dela Cruz', 'Thea', 2, 'Department Secretary'),
    (90329, 'Cortado', 'Maria', 1, 'Training Manager'),
    (90024, 'Garcia', 'Karina', 3, 'Front Desk Associate'),
    (90541, 'Lee', 'Thomas', 6, 'Marketing Assistant'),
    (90930, 'Smith', 'Antonio', 7, 'Accounting Clerk'),
    (90356, 'Lopez', 'Luis', 5, 'Project Manager'),
    (90075, 'Torres', 'Amanda', 1, 'Employee Relations'),
    (90070, 'dela Cruz', 'Chaewon', 7, 'Admin');
	
    
-- CREATE TABLE IF NOT EXISTS Technicians ();



-- CREATE TABLE IF NOT EXISTS Categories ();


-- Inserts (dummy data) dito
