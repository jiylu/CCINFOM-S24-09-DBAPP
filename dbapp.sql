CREATE SCHEMA IF NOT EXISTS ticketing_system;

USE ticketing_system;

-- Table creation 

-- CREATE TABLE IF NOT EXISTS Employees ();

-- CREATE TABLE IF NOT EXISTS Technicians ();

CREATE TABLE IF NOT EXISTS Departments (
	department_id INT AUTO_INCREMENT PRIMARY KEY,
	department_name VARCHAR(50) NOT NULL
);

INSERT INTO Departments (department_name)
VALUES
	('Human Resources'),
	('Finance'),
	('Customer Support'),
	('Sales'),
	('Operations'),
	('Marketing'),
	('Administration'),
	('Information Technology');


-- CREATE TABLE IF NOT EXISTS Categories ();


-- Inserts (dummy data) dito
--