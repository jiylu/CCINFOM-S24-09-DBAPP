USE ticketing_system;

INSERT IGNORE INTO Users(username, password, role)
VALUES
    ('montano_rivera', 'password123', 'Employee'),
    ('thea_delacruz', 'password123', 'Employee'),
    ('maria_cortado', 'password123', 'Employee'),
    ('karina_garcia', 'password123', 'Employee'),
    ('thomas_lee', 'password123', 'Employee'),
    ('antonio_smith', 'password123', 'Employee'),
    ('luis_lopez', 'password123', 'Employee'),
    ('amanda_torres', 'password123', 'Employee'),
    ('chaewon_dc', 'password123', 'Admin'),
    ('nobitabrto', 'password123', 'Technician'),
    ('carlosbrrng', 'password123', 'Technician'),
    ('fayewebster', 'password123', 'Technician');

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

INSERT IGNORE INTO Employees(user_id, last_name, first_name, dept_id, job_title) 
VALUES 
	(10000, 'Rivera', 'Montano', 4, 'Senior Coordinator'),
    (10001, 'Dela Cruz', 'Thea', 2, 'Department Secretary'),
    (10002, 'Cortado', 'Maria', 1, 'Training Manager'),
    (10003, 'Garcia', 'Karina', 3, 'Front Desk Associate'),
    (10004, 'Lee', 'Thomas', 6, 'Marketing Assistant'),
    (10005, 'Smith', 'Antonio', 7, 'Accounting Clerk'),
    (10006, 'Lopez', 'Luis', 5, 'Project Manager'),
    (10007, 'Torres', 'Amanda', 1, 'Employee Relations'),
    (10008, 'Dela Cruz', 'Chaewon', 7, 'Admin');

INSERT IGNORE INTO Technicians(user_id, tech_lastName, tech_firstName)
VALUES 
	(10009, 'Roberto', 'Nobita'),
    (10010, 'Barring', 'Carlos'),
    (10011, 'Webster', 'Faye');

INSERT IGNORE INTO Categories (category_name)
VALUES
	('Network Issue'),
    ('Software Issue'),
    ('Hardware Issue'),
    ('Account Access Issue');
