	USE ticketing_system;

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

INSERT IGNORE INTO UserAccounts (role, username, password)
VALUES
    ('Employee', 'montano_rivera', 'password123'), -- 50000
    ('Employee', 'thea_delacruz', 'password123'), -- 50001
    ('Employee', 'maria_cortado', 'password123'), -- 50002
    ('Employee', 'karina_garcia', 'password123'), -- 50003
    ('Employee', 'thomas_lee', 'password123'), -- 50004
    ('Employee', 'antonio_smith', 'password123'), -- 50005
    ('Employee', 'luis_lopez', 'password123'), -- 50006
    ('Employee', 'amanda_torres', 'password123'), -- 50007
    ('Admin', 'chaewon_dc', 'password123'), -- 50008
    ('Technician', 'nobitabrto', 'password123'), -- 50009
    ('Technician', 'carlosbrrng', 'password123'), -- 50010
    ('Technician', 'fayewebster', 'password123'), -- 50011
    ('Employee', 'elena_morales', 'password123'), -- 50012
    ('Employee', 'daniel_hernandez', 'password123'), -- 50013
    ('Employee', 'isabel_lopez', 'password123'), -- 50014
    ('Employee', 'victor_reyes', 'password123'), -- 50015
    ('Employee', 'sofia_ramos', 'password123'), -- 50016
    ('Employee', 'miguel_flores', 'password123'), -- 50017
    ('Employee', 'camila_diaz', 'password123'), -- 50018
    ('Employee', 'javier_silva', 'password123'), -- 50019
    ('Employee', 'adriana_torres', 'password123'), -- 50020
    ('Employee', 'ricardo_mendoza', 'password123'), -- 50021
    ('Technician', 'danielle_ng', 'password123'), -- 50022
    ('Technician', 'leo_chang', 'password123'), -- 50023
    ('Technician', 'maya_fernandez', 'password123'), -- 50024
    ('Technician', 'kevin_garcia', 'password123'), -- 50025
    ('Technician', 'nina_choi', 'password123'), -- 50026
    ('Technician', 'omar_johnson', 'password123'), -- 50027
    ('Technician', 'lara_huang', 'password123'), -- 50028
    ('Technician', 'samuel_lee', 'password123'), -- 50029
    ('Technician', 'emma_kim', 'password123'), -- 50030
    ('Technician', 'dante_rivera', 'password123'), -- 50031
    ('Technician', 'dante_gulapa', 'password123'), -- 50032
    ('Employee', 'fiona_apple', 'password123'), -- 50033
    ('Technician', 'hev_abi', 'password123'), -- 50034
    ('Admin', 'admin', 'password123'); -- 50035

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
    ('Mendoza', 'Ricardo', 5, 'Operations Manager'),
    ('Apple', 'Fiona', 3, 'Customer Support Specialist'),
    ('Admin', 'Admin', 7, 'Admin');

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
    ('Gulapa', 'Dante'),
    ('Abi', 'Hev');

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
    (50032, 30013),
    (50034, 30014);

INSERT IGNORE INTO Categories (category_name)
VALUES
	('Network Issue'),
    ('Software Issue'),
    ('Hardware Issue'),
    ('Account Access Issue');
    
INSERT INTO Tickets (ticket_subject, ticket_description, category_id, emp_id, tech_id, creation_date, status)
VALUES
('Computer won’t boot', 'The computer does not start when the power button is pressed. User urgently needs access to files for ongoing project.', 1, 90000, 30000, '2025-11-01 08:30:00', 'Active'),
('Email not syncing', 'User emails are not updating in the desktop client. Attempts to refresh or restart have not resolved the issue.', 2, 90001, 30001, '2025-11-01 09:00:00', 'Active'),
('Printer not responding', 'The office printer does not print any documents. User tried different cables but problem persists.', 1, 90002, 30002, '2025-11-02 10:20:00', 'Active'),
('Wi-Fi disconnected', 'User cannot connect to the office Wi-Fi network. Other devices are connecting fine, so issue seems specific to this computer.', 3, 90003, 30000, '2025-11-02 11:15:00', 'Enqueued'),
('VPN not connecting', 'Remote access VPN fails to connect using standard credentials. Error message indicates authentication problem.', 3, 90004, 30004, '2025-11-03 13:40:00', 'Enqueued'),
('Monitor flickering', 'The monitor flickers intermittently during normal use. This is causing strain to the user’s eyes.', 1, 90005, 30005, '2025-11-03 15:00:00', 'Enqueued'),
('Slow computer performance', 'The computer runs very slowly even with minimal programs open. User reports delays in completing regular tasks.', 1, 90006, 30006, '2025-11-04 08:50:00', 'Enqueued'),
('Cannot print PDF files', 'Printing PDF files does not work on the office printer. Other document types print fine, but PDFs fail every time.', 2, 90007, 30007, '2025-11-04 09:30:00', 'Enqueued'),
('Antivirus expired', 'User antivirus software subscription has expired. System is now unprotected and requires immediate renewal.', 2, 90008, 30008, '2025-11-05 10:10:00', 'Enqueued'),
('System update failed', 'Automatic system update did not complete successfully. Error logs indicate multiple failed attempts.', 4, 90000, 30009, '2025-11-05 14:45:00', 'Enqueued'),
('Mouse not detected', 'The mouse is not recognized by the computer. User has tried multiple USB ports without success.', 1, 90001, 30010, '2025-11-06 08:20:00', 'Enqueued'),
('Projector not displaying', 'The conference room projector does not display any image. User verified connections but still shows black screen.', 4, 90002, 30011, '2025-11-06 09:30:00', 'Enqueued'),
('Software installation request', 'User needs new software installed for upcoming project. License and installer files have been provided.', 2, 90003, 30012, '2025-11-07 13:00:00', 'Enqueued'),
('Keyboard malfunctioning', 'Keyboard keys are sticking and not responding properly. User reports frequent errors when typing.', 1, 90004, 30013, '2025-11-08 09:15:00', 'Enqueued'),
('Forgot Windows password', 'User has forgotten Windows login password. Cannot access the computer and needs password reset.', 2, 90005, 30014, '2025-11-08 10:00:00', 'Enqueued'),
('Email password reset request', 'User requests reset of corporate email password. Cannot send or receive important emails.', 4, 90000, 30000, '2021-03-15 09:25:00', 'Resolved'),
('Network cable replacement', 'The network cable in the office is damaged. User cannot connect to the internet and requests replacement.', 1, 90001, 30001, '2021-07-10 14:10:00', 'Resolved'),
('Software update installation', 'User requests the latest software updates be installed. Updates are critical for security and functionality.', 2, 90002, 30002, '2022-02-12 08:30:00', 'Resolved'),
('Monitor display issue', 'Monitor shows distorted colors and lines on the screen. User reports difficulty in reading documents.', 3, 90003, 30003, '2022-09-05 10:50:00', 'Resolved'),
('Unable to connect to VPN', 'VPN connection fails for remote work. User receives repeated authentication errors.', 3, 90004, 30004, '2023-01-18 13:40:00', 'Resolved'),
('Keyboard replacement request', 'Keyboard keys are missing and typing is difficult. User requests a replacement keyboard.', 1, 90005, 30005, '2023-05-09 09:00:00', 'Resolved'),
('Outlook not opening', 'Microsoft Outlook crashes immediately after opening. User cannot access emails and calendar events.', 2, 90006, 30006, '2023-09-22 08:45:00', 'Resolved'),
('Printer driver installation', 'User needs a printer driver installed to print documents. Installation files are already available.', 2, 90007, 30007, '2024-02-14 10:30:00', 'Resolved'),
('Forgotten laptop PIN', 'User cannot log in because the laptop PIN was forgotten. Needs reset to access system.', 4, 90008, 30008, '2023-05-06 09:40:00', 'Resolved'),
('Wi-Fi signal instability', 'Wi-Fi connection drops intermittently. User reports difficulty maintaining online sessions.', 1, 90000, 30009, '2024-10-11 15:15:00', 'Resolved'),
('System crash recovery', 'System crashed during work, causing loss of unsaved files. User needs recovery and troubleshooting.', 1, 90009, 30010, '2021-04-22 09:00:00', 'Resolved'),
('Display calibration request', 'Monitor colors are not accurate. User requests calibration for professional work.', 1, 90012, 30012, '2022-03-19 08:40:00', 'Resolved'),
('Email spam filtering issue', 'User receives too many spam emails. Requests proper spam filter configuration.', 2, 90013, 30013, '2022-07-22 10:15:00', 'Resolved'),
('VPN access configuration', 'User needs VPN configured for remote work. Access must be secure and reliable.', 3, 90014, 30014, '2022-11-18 09:35:00', 'Resolved'),
('PC performance optimization', 'Computer performance is slow with multiple apps running. User requests optimization for faster workflow.', 1, 90015, 30000, '2023-04-25 14:00:00', 'Resolved'),
('Software license renewal', 'Software license has expired. User requires renewal to continue working.', 2, 90016, 30001, '2023-07-19 09:30:00', 'Resolved'),
('Projector bulb replacement', 'Projector bulb has burnt out and needs replacement. User requests installation before next meeting.', 4, 90017, 30002, '2020-01-15 08:50:00', 'Resolved'),
('Network speed issue', 'Internet speed is very slow for tasks requiring high bandwidth. User requests network troubleshooting.', 3, 90018, 30003, '2024-06-05 14:10:00', 'Resolved'),
('Laptop won’t boot', 'User’s work laptop fails to power on. Needs immediate hardware diagnostics and repair.', 3, 90012, 30003, '2021-08-01 11:00:00', 'Resolved'),
('Printer driver failure', 'Network printer is inaccessible due to an outdated or corrupt driver on the user’s PC.', 2, 90005, 30004, '2024-08-05 09:15:00', 'Resolved'),
('VPN connection drops', 'User is experiencing frequent disconnections from the company VPN, hindering remote work.', 1, 90019, 30005, '2022-08-10 13:40:00', 'Resolved'),
('Password reset required', 'User has been locked out of their primary work account and needs an urgent password reset.', 4, 90008, 30006, '2024-08-15 08:30:00', 'Resolved'),
('Monitor flickering', 'The main monitor screen flickers intermittently, making it difficult to read and work.', 3, 90001, 30007, '2024-08-20 10:50:00', 'Resolved'),
('Email client sync error', 'The corporate email application is failing to synchronize folders and send receive new mail.', 2, 90014, 30008, '2023-08-25 12:00:00', 'Resolved'),
('New user access setup', 'A new employee requires setup for all system accounts and necessary software access.', 4, 90009, 30009, '2021-09-01 07:45:00', 'Resolved'),
('Desk phone malfunction', 'VoIP desk phone is not registering on the network and cannot make or receive calls.', 3, 90006, 30010, '2022-09-05 14:00:00', 'Resolved'),
('Firewall blocks internal server', 'User cannot access an internal development server, suspect firewall rule is blocking traffic.', 1, 90003, 30011, '2024-09-10 09:30:00', 'Resolved'),
('Operating System update failed', 'Recent OS update failed and caused stability issues, user needs rollback or fix.', 2, 90018, 30012, '2020-09-15 11:30:00', 'Resolved'),
('Hard drive running out of space', 'User’s local drive is critically low on space and needs cleaning and possible upgrade consultation.', 3, 90004, 30013, '2021-09-20 13:00:00', 'Resolved'),
('Two-Factor Auth failure', 'User cannot log in due to issues with the two-factor authentication token generation.', 4, 90011, 30014, '2023-09-25 08:00:00', 'Resolved'),
('Slow Wi-Fi in conference room', 'Conference room Wi-Fi is experiencing extremely slow speeds during presentations.', 1, 90016, 30001, '2024-10-01 10:10:00', 'Resolved'),
('Malware scan detected threat', 'Antivirus software detected a threat and user requires IT to isolate and clean the infected machine.', 2, 90020, 30002, '2020-10-05 15:20:00', 'Resolved'),
('Missing permissions for shared folder', 'User is unable to access a specific shared network folder necessary for their work tasks.', 4, 90007, 30003, '2021-10-10 09:00:00', 'Resolved'),
('Laptop Screen Crack', 'User dropped their laptop and the screen is cracked. Requires display replacement and data integrity check.', 3, 90017, 30006, '2023-08-01 15:30:00', 'Resolved'),
('Missing Shared Drive', 'A shared departmental drive has disappeared from the user’s file explorer view.', 4, 90002, 30007, '2023-08-05 09:50:00', 'Resolved'),
('External Network Block', 'User cannot access a necessary third-party vendor portal and firewall or proxy suspected.', 1, 90013, 30008, '2024-05-10 11:10:00', 'Resolved'),
('Legacy Application Failure', 'An old but critical piece of software crashes upon opening on the updated OS.', 2, 90008, 30009, '2024-05-15 13:00:00', 'Resolved'),
('Hard Drive Noise', 'Desktop PC is making loud clicking/grinding noises. It indicates potential drive failure.', 3, 90010, 30010, '2020-11-20 08:45:00', 'Resolved'),
('Expired Certificate Warning', 'Getting frequent security warnings about an expired internal server certificate.', 2, 90005, 30011, '2020-11-25 10:30:00', 'Resolved'),
('Printer Connectivity Loss', 'Local workgroup printer is no longer visible on the network for several users.', 1, 90003, 30012, '2021-02-14 12:00:00', 'Resolved'),
('Multi-factor Enrollment Issue', 'User is unable to enroll a new device for multi-factor authentication (MFA).', 4, 90016, 30013, '2021-02-19 14:10:00', 'Resolved'),
('Frozen Computer', 'User’s desktop is completely frozen and unresponsive and requires hard reboot and diagnostics.', 3, 90000, 30014, '2022-07-07 09:00:00', 'Resolved'),
('Missing OneDrive Folder', 'User cannot locate their main OneDrive sync folder after a system update.', 2, 90019, 30001, '2022-07-12 11:40:00', 'Resolved'),
('IP Conflict Detected', 'System alert triggered indicating an IP address conflict on the corporate network segment.', 1, 90011, 30002, '2025-04-03 07:30:00', 'Resolved'),
('Keyboard Keys Sticking', 'Several keys on the user’s keyboard are sticking or repeating input.', 3, 90004, 30003, '2025-04-08 16:00:00', 'Resolved'),
('HR System Permissions', 'User requires specific elevated read or write permissions for a module in the HR database.', 4, 90007, 30004, '2020-01-20 10:00:00', 'Resolved'),
('Deployment Tool Failure', 'Internal software deployment tool is failing to push updates to multiple client machines.', 2, 90020, 30005, '2021-09-01 13:50:00', 'Resolved'),
('Router Overheating', 'Main office router is hot to the touch and causing intermittent network outages.', 1, 90015, 30006, '2024-10-10 14:30:00', 'Resolved');
