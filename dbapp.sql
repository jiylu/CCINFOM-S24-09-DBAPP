CREATE SCHEMA IF NOT EXISTS ticketing_system;

USE ticketing_system;



-- Table for centralized login credentials (Technician, Employee, Admin) 
-- UID starts at 10000.
DROP TABLE IF EXISTS Users;

CREATE TABLE IF NOT EXISTS Users(
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(15) NOT NULL,
    role ENUM('Technician', 'Employee', 'Admin') NOT NULL,
    active BOOLEAN DEFAULT TRUE 
) AUTO_INCREMENT = 10000;

INSERT IGNORE INTO Users(username, password, role)
VALUES
    ('montano_rivera', 'password123', 'Employee'), -- 10000
    ('thea_delacruz', 'password123', 'Employee'), -- 10001
    ('maria_cortado', 'password123', 'Employee'), -- 10002
    ('thomas_lee', 'password123', 'Employee'), -- 10003
    ('antonio_smith', 'password123', 'Employee'), -- 10004
    ('luis_lopez', 'password123', 'Employee'), -- 10005
    ('amanda_torres', 'password123', 'Employee'), -- 10006
    ('chaewon_dc', 'password123', 'Employee'), -- 10007
    ('nobitabrto', 'password123', 'Technician'), -- 10008
    ('carlosbrrng', 'password123', 'Technician'), -- 10009
    ('fayewebster', 'password123', 'Technician'); -- 10010



-- Departments table
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



DROP TABLE IF EXISTS Employees;

CREATE TABLE IF NOT EXISTS Employees (
	emp_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL, 
    last_name VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    dept_id INT NOT NULL,
    role VARCHAR(50) NOT NULL,
	active BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_employee_user FOREIGN KEY (user_id) REFERENCES Users(user_id),
	CONSTRAINT fk_employee_department FOREIGN KEY (dept_id) REFERENCES Departments(department_id).
) AUTO_INCREMENT = 90000;

INSERT IGNORE INTO Employees(user_id, last_name, first_name, dept_id, role) 
VALUES 
	(10000, 'Rivera', 'Montano', 4, 'Senior Coordinator'),
    (10001, 'Dela Cruz', 'Thea', 2, 'Department Secretary'),
    (10002, 'Cortado', 'Maria', 1, 'Training Manager'),
    (10003, 'Garcia', 'Karina', 3, 'Front Desk Associate'),
    (10004, 'Lee', 'Thomas', 6, 'Marketing Assistant'),
    (10005, 'Smith', 'Antonio', 7, 'Accounting Clerk'),
    (10006, 'Lopez', 'Luis', 5, 'Project Manager'),
    (10007, 'Torres', 'Amanda', 1, 'Employee Relations'),
    (10008, 'dela Cruz', 'Chaewon', 7, 'Admin');




DROP TABLE IF EXISTS Technicians;

CREATE TABLE IF NOT EXISTS Technicians ( 
	technician_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    tech_lastName VARCHAR(50) NOT NULL,
    tech_firstName VARCHAR(50) NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    has_active_ticket BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_technician_user FOREIGN KEY (user_id) REFERENCES Users(user_id)
)AUTO_INCREMENT = 30000;


INSERT IGNORE INTO Technicians(user_id, tech_lastName, tech_firstName, tech_username)
VALUES 
	(10008, 'Roberto', 'Nobita', 'nobitarbrto'),
    (10009, 'Barring', 'Carlos', 'carlosbrrng'),
    (10010, 'Webster', 'Faye', 'fayewebster');


-- CREATE TABLE IF NOT EXISTS Categories ();


-- Inserts (dummy data) dito
