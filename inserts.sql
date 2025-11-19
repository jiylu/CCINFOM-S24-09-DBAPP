USE ticketing_system;

INSERT IGNORE INTO UserAccounts(username, password, role)
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

INSERT IGNORE INTO Employees(last_name, first_name, dept_id, job_title) 
VALUES 
	('Rivera', 'Montano', 4, 'Senior Coordinator'),
    ('Dela Cruz', 'Thea', 2, 'Department Secretary'),
    ('Cortado', 'Maria', 1, 'Training Manager'),
    ('Garcia', 'Karina', 3, 'Front Desk Associate'),
    ('Lee', 'Thomas', 6, 'Marketing Assistant'),
    ('Smith', 'Antonio', 7, 'Admin'),
    ('Lopez', 'Luis', 5, 'Project Manager'),
    ('Torres', 'Amanda', 1, 'Employee Relations'),
    ('Dela Cruz', 'Chaewon', 7, 'Admin'),
    ('Morales', 'Elena', 1, 'HR Assistant'),
    ('Hernandez', 'Daniel', 2, 'Accountant'),
    ('Lopez', 'Isabel', 3, 'Customer Service Rep'),
    ('Reyes', 'Victor', 4, 'Sales Associate'),
    ('Ramos', 'Sofia', 5, 'Operations Coordinator'),
    ('Flores', 'Miguel', 6, 'Marketing Specialist'),
    ('Diaz', 'Camila', 1, 'HR Coordinator'),
    ('Silva', 'Javier', 2, 'Finance Analyst'),
    ('Torres', 'Adriana', 3, 'Customer Support Specialist'),
    ('Mendoza', 'Ricardo', 5, 'Operations Manager');

INSERT IGNORE INTO EmployeeUsers(user_id, emp_id)
VALUES
    (50000, 90000),
    (50001, 90001),
    (50002, 90002),
    (50003, 90003),
    (50004, 90004),
    (50005, 90005),
    (50006, 90006),
    (50007, 90007),
    (50008, 90008),
    (50012, 90009),
    (50013, 90010),
    (50014, 90011),
    (50015, 90012),
    (50016, 90013),
    (50017, 90014),
    (50018, 90015),
    (50019, 90016),
    (50020, 90017),
    (50021, 90018),
    (50033, 90019),
    (50035, 90020);

INSERT IGNORE INTO Technicians(tech_lastName, tech_firstName)
VALUES 
	('Roberto', 'Nobita'),
    ('Barring', 'Carlos'),
    ('Webster', 'Faye'),
    ('Ng', 'Danielle'),
    ('Chang', 'Leo'),
    ('Fernandez', 'Maya'),
    ('Garcia', 'Kevin'),
    ('Choi', 'Nina'),
    ('Johnson', 'Omar'),
    ('Huang', 'Lara'),
    ('Lee', 'Samuel'),
    ('Kim', 'Emma'),
    ('Rivera', 'Dante'),
    ('Gulapa', 'Dante');

INSERT IGNORE INTO TechnicianUsers(user_id, technician_id)
VALUES
    (50009, 30000),
    (50010, 30001),
    (50011, 30002),
    (50022, 30003),
    (50023, 30004),
    (50024, 30005),
    (50025, 30006),
    (50026, 30007),
    (50027, 30008),
    (50028, 30009),
    (50029, 30010),
    (50030, 30011),
    (50031, 30012),
    (50032, 30013);

INSERT IGNORE INTO Categories (category_name)
VALUES
	('Network Issue'),
    ('Software Issue'),
    ('Hardware Issue'),
    ('Account Access Issue');
    
INSERT INTO Tickets (ticket_subject, ticket_description, category_id, creation_date, emp_id, tech_id, status)
VALUES
-- Emp IDs 90000 to 90019 (20 IDs total)
('Computer won’t boot', 'The computer does not start when the power button is pressed. User urgently needs access to files for ongoing project.', 1, '2025-11-01 08:30:00', 90007, 30000, 'Active'), -- 30000 1 Active
('Email not syncing', 'User emails are not updating in the desktop client. Attempts to refresh or restart have not resolved the issue.', 2, '2025-11-01 09:00:00', 90015, 30001, 'Active'), -- 30001 1 Active
('Printer not responding', 'The office printer does not print any documents. User tried different cables but problem persists.', 1, '2025-11-02 10:20:00', 90002, 30002, 'Active'), -- 30002 1 Active
('Wi-Fi disconnected', 'User cannot connect to the office Wi-Fi network. Other devices are connecting fine, so issue seems specific to this computer.', 3, '2025-11-02 11:15:00', 90011, 30003, 'Active'), -- 30003 1 Active
('VPN not connecting', 'Remote access VPN fails to connect using standard credentials. Error message indicates authentication problem.', 3, '2025-11-03 13:40:00', 90007, 30004, 'Active'), -- 30004 1 Active
('Monitor flickering', 'The monitor flickers intermittently during normal use. This is causing strain to the user’s eyes.', 1, '2025-11-03 15:00:00', 90016, 30005, 'Active'), -- 30005 1 Active
('Slow computer performance', 'The computer runs very slowly even with minimal programs open. User reports delays in completing regular tasks.', 1, '2025-11-04 08:50:00', 90003, 30006, 'Active'), -- 30006 1 Active
('Cannot print PDF files', 'Printing PDF files does not work on the office printer. Other document types print fine, but PDFs fail every time.', 2, '2025-11-04 09:30:00', 90014, 30007, 'Active'), -- 30007 1 Active
('Antivirus expired', 'User antivirus software subscription has expired. System is now unprotected and requires immediate renewal.', 2, '2025-11-05 10:10:00', 90001, 30008, 'Active'), -- 30008 1 Active
('System update failed', 'Automatic system update did not complete successfully. Error logs indicate multiple failed attempts.', 4, '2025-11-05 14:45:00', 90013, 30009, 'Active'), -- 30009 1 Active
('Mouse not detected', 'The mouse is not recognized by the computer. User has tried multiple USB ports without success.', 1, '2025-11-06 08:20:00', 90006, 30010, 'Active'), -- 30010 1 Active
('Projector not displaying', 'The conference room projector does not display any image. User verified connections but still shows black screen.', 4, '2025-11-06 09:30:00', 90018, 30011, 'Active'), -- 30011 1 Active
('Software installation request', 'User needs new software installed for upcoming project. License and installer files have been provided.', 2, '2025-11-07 13:00:00', 90004, 30012, 'Active'), -- 30012 1 Active
('Keyboard malfunctioning', 'Keyboard keys are sticking and not responding properly. User reports frequent errors when typing.', 1, '2025-11-08 09:15:00', 90017, 30013, 'Active'), -- Changed 90020 -> 90017 -- 30013 1 Active
-- Cycle 1 complete. Start Cycle 2 (90000-90019)
('Forgot Windows password', 'User has forgotten Windows login password. Cannot access the computer and needs password reset.', 2, '2025-11-08 10:00:00', 90009, 30000, 'Enqueued'), -- 30000 1 Enqueued
('Email password reset request', 'User requests reset of corporate email password. Cannot send or receive important emails.', 4, '2021-03-15 09:25:00', 90000, 30001, 'Resolved'), -- 30001 1 Resolved
('Network cable replacement', 'The network cable in the office is damaged. User cannot connect to the internet and requests replacement.', 1, '2021-07-10 14:10:00', 90010, 30002, 'Resolved'), -- 30002 1 Resolved
('Software update installation', 'User requests the latest software updates be installed. Updates are critical for security and functionality.', 2, '2022-02-12 08:30:00', 90006, 30003, 'Resolved'), -- 30003 1 Resolved
('Monitor display issue', 'Monitor shows distorted colors and lines on the screen. User reports difficulty in reading documents.', 3, '2022-09-05 10:50:00', 90014, 30004, 'Resolved'), -- 30004 1 Resolved
('Unable to connect to VPN', 'VPN connection fails for remote work. User receives repeated authentication errors.', 3, '2023-01-18 13:40:00', 90017, 30005, 'Resolved'), -- Changed 90021 -> 90017 -- 30005 1 Resolved
('Keyboard replacement request', 'Keyboard keys are missing and typing is difficult. User requests a replacement keyboard.', 1, '2023-05-09 09:00:00', 90007, 30006, 'Resolved'), -- 30006 1 Resolved
('Outlook not opening', 'Microsoft Outlook crashes immediately after opening. User cannot access emails and calendar events.', 2, '2023-09-22 08:45:00', 90015, 30007, 'Resolved'), -- 30007 1 Resolved
('Printer driver installation', 'User needs a printer driver installed to print documents. Installation files are already available.', 2, '2024-02-14 10:30:00', 90002, 30008, 'Resolved'), -- 30008 1 Resolved
('Forgotten laptop PIN', 'User cannot log in because the laptop PIN was forgotten. Needs reset to access system.', 4, '2024-05-06 09:40:00', 90011, 30009, 'Resolved'), -- 30009 1 Resolved 
('Wi-Fi signal instability', 'Wi-Fi connection drops intermittently. User reports difficulty maintaining online sessions.', 1, '2024-10-11 15:15:00', 90007, 30010, 'Resolved'), -- 30010 1 Resolved
('System crash recovery', 'System crashed during work, causing loss of unsaved files. User needs recovery and troubleshooting.', 1, '2021-04-22 09:00:00', 90016, 30011, 'Resolved'), -- 30011 1 Resolved
('Display calibration request', 'Monitor colors are not accurate. User requests calibration for professional work.', 1, '2022-03-19 08:40:00', 90003, 30012, 'Resolved'), -- 30012 1 Resolved
('Email spam filtering issue', 'User receives too many spam emails. Requests proper spam filter configuration.', 2, '2022-07-22 10:15:00', 90016, 30013, 'Resolved'), -- 30013 1 Resolved
-- Cycle 2 complete. Start Cycle 3 (90000-90019)
('VPN access configuration', 'User needs VPN configured for remote work. Access must be secure and reliable.', 3, '2022-11-18 09:35:00', 90001, 30000, 'Resolved'), -- 30000 2 Resolved
('PC performance optimization', 'Computer performance is slow with multiple apps running. User requests optimization for faster workflow.', 1, '2023-04-25 14:00:00', 90013, 30001, 'Resolved'), -- 30001 2 Resolved
('Software license renewal', 'Software license has expired. User requires renewal to continue working.', 2, '2023-07-19 09:30:00', 90006, 30002, 'Resolved'), -- 30002 2 Resolved
('Projector bulb replacement', 'Projector bulb has burnt out and needs replacement. User requests installation before next meeting.', 4, '2024-01-15 08:50:00', 90018, 30003, 'Resolved'), -- 30003 2 Resolved
('Network speed issue', 'Internet speed is very slow for tasks requiring high bandwidth. User requests network troubleshooting.', 3, '2024-06-05 14:10:00', 90004, 30004, 'Resolved'), -- 30004 2 Resolved

-- additional resolved tickets
('Email sync failure', 'User reports that email is not syncing across devices and requests immediate fix', 2, '2021-03-11 10:20:00', 90009, 30005, 'Resolved'), 
('Printer not responding', 'Printer stops working during document processing and needs troubleshooting', 3, '2024-02-18 15:45:00', 90002, 30006, 'Resolved'), 
('WiFi intermittent connection', 'Device keeps disconnecting from the wireless network and requires diagnostics', 1, '2020-09-22 08:50:00', 90014, 30007, 'Resolved'), 
('Password reset request', 'User cannot access the system and needs a manual password reset', 4, '2025-05-19 09:00:00', 90001, 30008, 'Resolved'), 
('Application crash issue', 'Work application repeatedly crashes during use and needs investigation', 2, '2022-12-04 13:10:00', 90018, 30009, 'Resolved'), 
('Monitor display flickering', 'Monitor flickers randomly which affects user productivity', 3, '2023-08-29 11:27:00', 90006, 30010, 'Resolved'), 
('VPN connection timeout', 'VPN disconnects after a few minutes and requires stable configuration', 1, '2021-06-17 16:30:00', 90011, 30011, 'Resolved'), 
('Account lockout', 'User account becomes locked after multiple login attempts and needs unlocking', 4, '2025-03-03 07:55:00', 90004, 30012, 'Resolved'), 
('Software update failure', 'System fails to complete software update and needs manual patching', 2, '2020-11-09 13:40:00', 90017, 30013, 'Resolved'), 
('Overheating laptop', 'Laptop overheats during normal usage and requires hardware cleaning', 3, '2023-02-14 10:05:00', 90002, 30000, 'Resolved'), 
('Slow network authentication', 'Network login takes too long before granting access', 1, '2024-07-26 12:20:00', 90003, 30001, 'Resolved'), 
('Missing application permissions', 'User cannot use specific application features due to missing permissions', 4, '2025-01-06 14:55:00', 90010, 30002, 'Resolved'), 
('Loose keyboard keys', 'Keyboard keys are loose and sometimes unresponsive and need replacement', 3, '2022-04-08 09:30:00', 90000, 30003, 'Resolved'), 
('System slow boot', 'System takes too long to start and requires optimization', 1, '2023-09-13 08:10:00', 90004, 30004, 'Resolved'), 
('Firewall restriction issue', 'User reports that certain websites cannot be accessed and needs review of firewall rules', 1, '2021-02-12 09:10:00', 90007, 30005, 'Resolved'), 
('Spreadsheet tool malfunction', 'User reports that a spreadsheet tool stops responding during data entry and needs fixing', 2, '2024-03-21 11:25:00', 90003, 30006, 'Resolved'), 
('Laptop battery draining fast', 'Battery depletes quickly during normal use and needs diagnostics', 3, '2020-06-30 14:45:00', 90012, 30007, 'Resolved'), 
('Account permission update needed', 'User requests additional permissions to perform required tasks', 4, '2025-02-10 08:30:00', 90016, 30008, 'Resolved'), 
('Network printer mapping issue', 'User cannot map the network printer and needs assistance', 1, '2023-10-14 10:05:00', 90004, 30009, 'Resolved'), 
('Software installation request', 'User needs assistance installing a new application for work use', 2, '2022-09-19 13:20:00', 90010, 30010, 'Resolved'), 
('Overheating desktop unit', 'Desktop heats up under light use and needs cleaning and thermal check', 3, '2021-05-04 15:00:00', 90000, 30011, 'Resolved'), 
('Email access restriction', 'User is unable to access email due to permission settings and needs updating', 4, '2024-08-07 09:55:00', 90011, 30012, 'Resolved'), 
('Router authentication loop', 'User is stuck in a repeated login loop when connecting to router', 1, '2025-07-12 16:30:00', 90002, 30013, 'Resolved'), 
('CPU fan making noise', 'CPU fan is producing unusual noise and needs inspection', 3, '2020-10-03 08:40:00', 90014, 30000, 'Resolved');

-- Mapped Accordingly
INSERT INTO ResolvedTickets (ticket_id, resolve_date) VALUES
(40015, '2021-03-15 11:12:00'),
(40016, '2021-07-10 17:45:33'),
(40017, '2022-02-12 10:55:12'),
(40018, '2022-09-05 11:40:25'),
(40019, '2023-01-18 15:22:19'),
(40020, '2023-05-09 10:48:00'),
(40021, '2023-09-22 09:51:44'),
(40022, '2024-02-14 13:20:00'),
(40023, '2024-05-06 11:09:31'),
(40024, '2024-10-11 16:44:58'),
(40025, '2021-04-22 11:32:17'),
(40026, '2022-03-19 09:55:48'),
(40027, '2022-07-22 12:05:11'),
(40028, '2022-11-18 10:22:54'),
(40029, '2023-04-25 16:33:08'),
(40030, '2023-07-19 11:44:22'),
(40031, '2024-01-15 10:05:39'),
(40032, '2024-06-05 16:12:27'),

-- additional resolved tickets
(40033, '2021-03-11 15:20:00'),
(40034, '2024-02-18 21:45:00'),
(40035, '2020-09-22 15:50:00'),
(40036, '2025-05-19 13:00:00'),
(40037, '2022-12-04 21:10:00'),
(40038, '2023-08-29 17:27:00'),
(40039, '2021-06-17 21:30:00'),
(40040, '2025-03-03 14:55:00'),
(40041, '2020-11-09 19:40:00'),
(40042, '2023-02-14 15:05:00'),
(40043, '2024-07-26 20:20:00'),
(40044, '2025-01-06 18:55:00'),
(40045, '2022-04-08 16:30:00'),
(40046, '2023-09-13 14:10:00'),
(40047, '2021-02-12 14:10:00'),
(40048, '2024-03-21 18:25:00'),
(40049, '2020-06-30 18:45:00'),
(40050, '2025-02-10 16:30:00'),
(40051, '2023-10-14 16:05:00'),
(40052, '2022-09-19 20:20:00'),
(40053, '2021-05-04 20:00:00'),
(40054, '2024-08-07 15:55:00'),
(40055, '2025-07-12 20:30:00'),
(40056, '2020-10-03 16:40:00');