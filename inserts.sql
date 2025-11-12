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
    ('fayewebster', 'password123', 'Technician'),
    ('elena_morales', 'password123', 'Employee'),
    ('daniel_hernandez', 'password123', 'Employee'),
    ('isabel_lopez', 'password123', 'Employee'),
    ('victor_reyes', 'password123', 'Employee'),
    ('sofia_ramos', 'password123', 'Employee'),
    ('miguel_flores', 'password123', 'Employee'),
    ('camila_diaz', 'password123', 'Employee'),
    ('javier_silva', 'password123', 'Employee'),
    ('adriana_torres', 'password123', 'Employee'),
    ('ricardo_mendoza', 'password123', 'Employee'),
    ('danielle_ng', 'password123', 'Technician'),
    ('leo_chang', 'password123', 'Technician'),
    ('maya_fernandez', 'password123', 'Technician'),
    ('kevin_garcia', 'password123', 'Technician'),
    ('nina_choi', 'password123', 'Technician'),
    ('omar_johnson', 'password123', 'Technician'),
    ('lara_huang', 'password123', 'Technician'),
    ('samuel_lee', 'password123', 'Technician'),
    ('emma_kim', 'password123', 'Technician'),
    ('dante_rivera', 'password123', 'Technician'),
    ('dante_gulapa', 'password123', 'Technician');

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
    (10005, 'Smith', 'Antonio', 7, 'Admin'),
    (10006, 'Lopez', 'Luis', 5, 'Project Manager'),
    (10007, 'Torres', 'Amanda', 1, 'Employee Relations'),
    (10008, 'Dela Cruz', 'Chaewon', 7, 'Admin'),
    (10012, 'Morales', 'Elena', 1, 'HR Assistant'),
    (10013, 'Hernandez', 'Daniel', 2, 'Accountant'),
    (10014, 'Lopez', 'Isabel', 3, 'Customer Service Rep'),
    (10015, 'Reyes', 'Victor', 4, 'Sales Associate'),
    (10016, 'Ramos', 'Sofia', 5, 'Operations Coordinator'),
    (10017, 'Flores', 'Miguel', 6, 'Marketing Specialist'),
    (10018, 'Diaz', 'Camila', 1, 'HR Coordinator'),
    (10019, 'Silva', 'Javier', 2, 'Finance Analyst'),
    (10020, 'Torres', 'Adriana', 3, 'Customer Support Specialist'),
    (10021, 'Mendoza', 'Ricardo', 5, 'Operations Manager');


INSERT IGNORE INTO Technicians(user_id, tech_lastName, tech_firstName)
VALUES 
	(10009, 'Roberto', 'Nobita'),
    (10010, 'Barring', 'Carlos'),
    (10011, 'Webster', 'Faye'),
    (10022, 'Ng', 'Danielle'),
    (10023, 'Chang', 'Leo'),
    (10024, 'Fernandez', 'Maya'),
    (10025, 'Garcia', 'Kevin'),
    (10026, 'Choi', 'Nina'),
    (10027, 'Johnson', 'Omar'),
    (10028, 'Huang', 'Lara'),
    (10029, 'Lee', 'Samuel'),
    (10030, 'Kim', 'Emma'),
    (10031, 'Rivera', 'Dante'),
    (10031, 'Gulapa', 'Dante');

INSERT IGNORE INTO Categories (category_name)
VALUES
	('Network Issue'),
    ('Software Issue'),
    ('Hardware Issue'),
    ('Account Access Issue');
    
INSERT INTO Tickets (ticket_subject, category_id, employee_id, technician_id, creation_date, resolve_date, status)
VALUES
('Computer won’t boot', 1, 90000, 30000, '2025-11-01 08:30:00', NULL, 'Active'),
('Email not syncing', 2, 90001, 30001, '2025-11-01 09:00:00', NULL, 'Active'),
('Printer not responding', 1, 90002, 30002, '2025-11-02 10:20:00', NULL, 'Active'),
('Wi-Fi disconnected', 3, 90003, 30000, '2025-11-02 11:15:00', NULL, 'Enqueued'),
('VPN not connecting', 3, 90004, 30001, '2025-11-03 13:40:00', NULL, 'Enqueued'),
('Monitor flickering', 1, 90005, 30002, '2025-11-03 15:00:00', NULL, 'Enqueued'),
('Slow computer performance', 1, 90006, 30000, '2025-11-04 08:50:00', NULL, 'Enqueued'),
('Cannot print PDF files', 2, 90007, 30001, '2025-11-04 09:30:00', NULL, 'Enqueued'),
('Antivirus expired', 2, 90008, 30002, '2025-11-05 10:10:00', NULL, 'Enqueued'),
('System update failed', 4, 90000, 30000, '2025-11-05 14:45:00', NULL, 'Enqueued'),
('Mouse not detected', 1, 90001, 30001, '2025-11-06 08:20:00', NULL, 'Enqueued'),
('Projector not displaying', 4, 90002, 30002, '2025-11-06 09:30:00', NULL, 'Enqueued'),
('Software installation request', 2, 90003, 30000, '2025-11-07 13:00:00', NULL, 'Enqueued'),
('Keyboard malfunctioning', 1, 90004, 30001, '2025-11-08 09:15:00', NULL, 'Enqueued'),
('Forgot Windows password', 2, 90005, 30002, '2025-11-08 10:00:00', NULL, 'Enqueued');
