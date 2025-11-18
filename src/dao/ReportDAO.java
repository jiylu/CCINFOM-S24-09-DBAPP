package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import reports.*;

public class ReportDAO {
    private Connection conn;

    public ReportDAO(Connection conn){
        this.conn = conn;
    }
    // Technician Workload Report
    public List<TechWorkloadReport> generateTechWorkloadReport(){
        List<TechWorkloadReport> list = new ArrayList<>();
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append("CONCAT(t.tech_firstName, ' ', t.tech_lastName) AS technician_name, ");
        query.append("YEAR(tk.creation_date) AS year, ");
        query.append("COUNT(tk.ticket_id) AS assigned_tickets, ");
        query.append("SUM(CASE WHEN tk.status = 'Resolved' THEN 1 ELSE 0 END) AS resolved_tickets, ");
        query.append("AVG(CASE WHEN tk.status = 'Resolved' THEN TIMESTAMPDIFF(HOUR, tk.creation_date, rt.resolve_date) ELSE NULL END) AS average_resolution_time ");
        query.append("FROM Technicians t ");
        query.append("JOIN Tickets tk ON t.technician_id = tk.tech_id ");
        query.append("JOIN ResolvedTickets rt ON tk.ticket_id = rt.ticket_id ");
        query.append("GROUP BY t.technician_id, technician_name, year ");
        query.append("ORDER BY year, t.technician_id; ");

        
        try (java.sql.PreparedStatement ps = conn.prepareStatement(query.toString())){ 
        
            try (java.sql.ResultSet rs = ps.executeQuery()){

                while (rs.next()){
                    TechWorkloadReport TechWR = new TechWorkloadReport(
                        rs.getInt("year"), 
                        rs.getString("technician_name"),
                        rs.getInt("assigned_tickets"), 
                        rs.getInt("resolved_tickets"), 
                        rs.getInt("average_resolution_time"));

                    list.add(TechWR);
                }
            }
            return list;

        } catch (SQLException e) {
            System.out.println("Error generating technician report.");
            e.printStackTrace();
            return null;
        }
    }

    public List<TechWorkloadReport> generateTechYearsSummary(int technicianId){ //list of years of a chosen technician
        List<TechWorkloadReport> list = new ArrayList<>();
        StringBuilder query = new StringBuilder();
        
        query.append("SELECT ");
        query.append("YEAR(tk.creation_date) AS year, ");
        query.append("COUNT(tk.ticket_id) AS assigned_tickets, ");        
        query.append("SUM(CASE WHEN tk.status = 'Resolved' THEN 1 ELSE 0 END) AS resolved_tickets,");
        query.append("AVG(TIMESTAMPDIFF(HOUR, tk.creation_date, rt.resolve_date)) AS average_resolution_time");
        query.append(" FROM Tickets tk");
        query.append(" JOIN ResolvedTickets rt ON tk.ticket_id = rt.ticket_id");
        query.append(" WHERE tk.tech_id = ? ");
        query.append(" GROUP BY year");
        query.append(" ORDER BY year ASC");
        
        try (PreparedStatement ps = conn.prepareStatement(query.toString())){
            ps.setInt(1, technicianId);
            ResultSet rs = ps.executeQuery();

            String technician = "ID: " + technicianId ;

            while (rs.next()){
                int year = rs.getInt("year");
                int assignedTickets = rs.getInt("assigned_tickets");
                int resolvedTickets = rs.getInt("resolved_tickets");
                int average_resolution_time = rs.getInt("average_resolution_time");
                
                list.add(new TechWorkloadReport(
                year,
                technician, 
                assignedTickets,
                resolvedTickets,
                average_resolution_time
            ));
            }
            
            return list;
        } catch (SQLException e) {
            System.out.println("Error generating technician report.");
            e.printStackTrace();
            return null;
        }
    }

    
    public List<TechWorkloadReport> generateYearTechsSummary(int year) { //list of technicians in a chosen year
        List<TechWorkloadReport> list = new ArrayList<>();
        StringBuilder query = new StringBuilder();
        
        query.append("SELECT ");
        query.append("CONCAT(t.tech_firstName, ' ', t.tech_lastName) AS technician_name, ");
        query.append("COUNT(tk.ticket_id) AS assigned_tickets, ");
        query.append("SUM(CASE WHEN tk.status = 'Resolved' THEN 1 ELSE 0 END) AS resolved_tickets, ");
        query.append("AVG(TIMESTAMPDIFF(HOUR, tk.creation_date, rt.resolve_date)) AS average_time");
        query.append(" FROM Tickets tk");
        query.append(" JOIN Technicians t ON t.technician_id = tk.tech_id");
        query.append(" JOIN ResolvedTickets rt ON tk.ticket_id = rt.ticket_id ");
        query.append(" WHERE YEAR(tk.creation_date) = ?"); // Use placeholder for parameterized execution
        query.append(" GROUP BY t.technician_id, technician_name");
        query.append(" ORDER BY technician_name");
        
        try (PreparedStatement ps = conn.prepareStatement(query.toString())){
                ps.setInt(1, year);
                ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String technician = rs.getString("technician_name");
                int assignedTickets = rs.getInt("assigned_tickets");
                int resolvedTickets = rs.getInt("resolved_tickets");
                int averageTime = rs.getInt("average_time");

                list.add(new TechWorkloadReport(
                    year,
                    technician,
                    assignedTickets,
                    resolvedTickets,
                    averageTime
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Employee Ticket Resolution Report
    public List<EmployeeTicketResolutionReport> generateEmpTicketResReport(){
        List<EmployeeTicketResolutionReport> list = new ArrayList<>();

        StringBuilder query = new StringBuilder();

        query.append("SELECT ");
        query.append("CONCAT(e.first_name, ' ', e.last_name) AS employee_name, ");
        query.append("YEAR(tk.creation_date) AS year, ");
        query.append("COUNT(tk.ticket_id) AS submitted_tickets, ");
        query.append("SUM(CASE WHEN tk.status = 'Resolved' THEN 1 ELSE 0 END) AS resolved_tickets, ");
        query.append("SUM(CASE WHEN tk.status = 'Cancelled' THEN 1 ELSE 0 END) AS cancelled_tickets ");
        query.append("FROM employees e ");
        query.append("JOIN Tickets tk ON e.emp_id = tk.emp_id ");
        query.append("GROUP BY e.emp_id, employee_name, year ");
        query.append("ORDER BY year, e.emp_id;");

        try (java.sql.PreparedStatement ps = conn.prepareStatement(query.toString())) {
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    EmployeeTicketResolutionReport report = new EmployeeTicketResolutionReport(
                            rs.getInt("year"),
                            rs.getString("employee_name"),
                            rs.getInt("submitted_tickets"),
                            rs.getInt("resolved_tickets"),
                            rs.getInt("cancelled_tickets")
                    );

                    list.add(report);
                }
            }

            return list;
        } catch (SQLException e) {
            System.out.println("Error generating employee ticket resolution report.");
            e.printStackTrace();
            return null;
        }
    }

    public List<EmployeeTicketResolutionReport> generateEmpYearsSummary(int employeeId) {
        List<EmployeeTicketResolutionReport> list = new ArrayList<>();

        StringBuilder query = new StringBuilder();

        query.append("SELECT ");
        query.append("YEAR(tk.creation_date) AS year, ");
        query.append("COUNT(tk.ticket_id) AS submitted_tickets, ");
        query.append("SUM(CASE WHEN tk.status = 'Resolved' THEN 1 ELSE 0 END) AS resolved_tickets, ");
        query.append("SUM(CASE WHEN tk.status = 'Cancelled' THEN 1 ELSE 0 END) AS cancelled_tickets ");
        query.append("FROM Tickets tk ");
        query.append("WHERE tk.emp_id = ? ");
        query.append("GROUP BY year ");
        query.append("ORDER BY year ASC");

        try(PreparedStatement ps = conn.prepareStatement(query.toString())){
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();

            String employee = "ID: " + employeeId;

            while(rs.next()) {
                int year = rs.getInt("year");
                int submittedTickets = rs.getInt("submitted_tickets");
                int resolvedTickets = rs.getInt("resolved_tickets");
                int cancelledTickets = rs.getInt("cancelled_tickets");

                list.add(new EmployeeTicketResolutionReport(
                    year,
                    employee,
                    submittedTickets,
                    resolvedTickets,
                    cancelledTickets
                ));
            }

            return list;
        } catch (SQLException e) {
            System.out.println("Error generating employee yearly summary report.");
            e.printStackTrace();
            return null;
        }
    }

    public List<EmployeeTicketResolutionReport> generateYearEmpsSummary(int year) {
        List<EmployeeTicketResolutionReport> list = new ArrayList<>();
        StringBuilder query = new StringBuilder();

        query.append("SELECT ");
        query.append("CONCAT(e.first_name, ' ', e.last_name) AS employee_name, ");
        query.append("COUNT(tk.ticket_id) AS submitted_tickets, ");
        query.append("SUM(CASE WHEN tk.status = 'Resolved' THEN 1 ELSE 0 END) AS resolved_tickets, ");
        query.append("SUM(CASE WHEN tk.status = 'Cancelled' THEN 1 ELSE 0 END) AS cancelled_tickets ");
        query.append("FROM Tickets tk ");
        query.append("JOIN Employees e ON e.emp_id = tk.emp_id ");
        query.append("WHERE YEAR(tk.creation_date) = ? ");
        query.append("GROUP BY e.emp_id, employee_name ");
        query.append("ORDER BY employee_name ASC");

        try (PreparedStatement ps = conn.prepareStatement(query.toString())) {
            ps.setInt(1, year);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                String employee = rs.getString("employee_name");
                int submittedTickets = rs.getInt("submitted_tickets");
                int resolvedTickets = rs.getInt("resolved_tickets");
                int cancelledTickets = rs.getInt("cancelled_tickets");

                list.add(new EmployeeTicketResolutionReport(
                        year,
                        employee,
                        submittedTickets,
                        resolvedTickets,
                        cancelledTickets
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Category Report DAOs
    public List<CategoryReport> generateCategoryReport(){
        List<CategoryReport> list = new ArrayList<>();
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append("c.category_name, ");
        query.append("YEAR(t.creation_date) AS year, ");
        query.append("COUNT(t.ticket_id) AS num_tickets, ");
        query.append("SUM(CASE WHEN t.status = 'Resolved' THEN 1 ELSE 0 END) AS resolved_tickets ");
        query.append("FROM categories c ");
        query.append("JOIN Tickets t ");
        query.append("ON t.category_id = c.category_id ");
        query.append("GROUP BY c.category_name, YEAR(t.creation_date) ");
        query.append("ORDER BY year, num_tickets DESC, resolved_tickets DESC;");
        
        try (Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(query.toString());

            while (rs.next()){
                CategoryReport cr = new CategoryReport(
                    rs.getString("category_name"), 
                    rs.getInt("year"), 
                    rs.getInt("num_tickets"), 
                    rs.getInt("resolved_tickets"));

                list.add(cr);
            }

            rs.close();
            stmt.close();
            
            return list;
        } catch (SQLException e) {
            System.out.println("Error generating category report.");
            return null;
        }
    }

    public List<CategoryReport> generateCategoryReportByYear(int year){
        List<CategoryReport> list = new ArrayList<>();
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append("c.category_name, ");
        query.append("YEAR(t.creation_date) AS year, ");
        query.append("COUNT(t.ticket_id) AS num_tickets, ");
        query.append("SUM(CASE WHEN t.status = 'Resolved' THEN 1 ELSE 0 END) AS resolved_tickets ");
        query.append("FROM categories c ");
        query.append("JOIN Tickets t ");
        query.append("ON t.category_id = c.category_id ");
        query.append("WHERE YEAR(t.creation_date) = ? ");
        query.append("GROUP BY c.category_name, YEAR(t.creation_date) ");
        query.append("ORDER BY year, num_tickets DESC, resolved_tickets DESC;");
        
        try (PreparedStatement ps = conn.prepareStatement(query.toString())){
            ps.setInt(1, year);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                CategoryReport cr = new CategoryReport(
                    rs.getString("category_name"), 
                    rs.getInt("year"), 
                    rs.getInt("num_tickets"), 
                    rs.getInt("resolved_tickets")
                    );
                    
                list.add(cr);
            }

            rs.close();
            ps.close();
            
            return list;
        } catch (SQLException e) {
            System.out.println("Error generating category report.");
            return null;
        }
    }

    public List<CategoryReport> generateCategoryReportByCategory(int categoryID){
        List<CategoryReport> list = new ArrayList<>();
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append("c.category_name, ");
        query.append("YEAR(t.creation_date) AS year, ");
        query.append("COUNT(t.ticket_id) AS num_tickets, ");
        query.append("SUM(CASE WHEN t.status = 'Resolved' THEN 1 ELSE 0 END) AS resolved_tickets ");
        query.append("FROM categories c ");
        query.append("JOIN Tickets t ");
        query.append("ON t.category_id = c.category_id ");
        query.append("WHERE c.category_id = ? ");
        query.append("GROUP BY c.category_name, YEAR(t.creation_date) ");
        query.append("ORDER BY year, num_tickets DESC, resolved_tickets DESC;");
        
        try (PreparedStatement ps = conn.prepareStatement(query.toString())){
            ps.setInt(1, categoryID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                CategoryReport cr = new CategoryReport(
                    rs.getString("category_name"), 
                    rs.getInt("year"), 
                    rs.getInt("num_tickets"), 
                    rs.getInt("resolved_tickets")
                    );
                    
                list.add(cr);
            }

            rs.close();
            ps.close();
            
            return list;
        } catch (SQLException e) {
            System.out.println("Error generating category report.");
            return null;
        }
    }
    
    // Department Report DAOs
   public List<DepartmentReport> generateDepartmentReport() {
        List<DepartmentReport> list = new ArrayList<>();
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append("d.department_name, ");
        query.append("YEAR(t.creation_date) AS year, ");
        query.append("c.category_name, ");
        query.append("COUNT(t.ticket_id) AS total_tickets ");
        query.append("FROM Departments d ");
        query.append("JOIN employees e ON d.department_id = e.dept_id ");
        query.append("JOIN Tickets t ON e.emp_id = t.emp_id ");
        query.append("JOIN categories c ON t.category_id = c.category_id ");
        query.append("GROUP BY d.department_name, YEAR(t.creation_date), c.category_name ");
        query.append("ORDER BY year, d.department_name;");
        
        try (Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(query.toString());

            while (rs.next()){
                DepartmentReport dr = new DepartmentReport(
                    rs.getString("department_name"), 
                    rs.getInt("year"), 
                    rs.getString("category_name"), 
                    rs.getInt("total_tickets"));

                list.add(dr);
            }

            rs.close();
            stmt.close();
            
            return list;
        } catch (SQLException e) {
            System.out.println("Error generating category report.");
            return null;
        }
    }
    
    public List<DepartmentReport> generateDeptReportByDept(int deptID) {
        List<DepartmentReport> list = new ArrayList<>();
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append("d.department_name, ");
        query.append("YEAR(t.creation_date) AS year, ");
        query.append("c.category_name, ");
        query.append("COUNT(t.ticket_id) AS total_tickets ");
        query.append("FROM departments d ");
        query.append("JOIN employees e ON d.department_id = e.dept_id ");
        query.append("JOIN tickets t ON e.emp_id = t.emp_id ");
        query.append("JOIN categories c ON t.category_id = c.category_id ");
        query.append("WHERE d.department_id = ? ");
        query.append("GROUP BY d.department_name, YEAR(t.creation_date), c.category_name ");
        query.append("ORDER BY d.department_name, year;");
        
        try (PreparedStatement ps = conn.prepareStatement(query.toString())){
            ps.setInt(1, deptID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                DepartmentReport dr = new DepartmentReport(
                    rs.getString("department_name"), 
                    rs.getInt("year"), 
                    rs.getString("category_name"), 
                    rs.getInt("total_tickets"));

                list.add(dr);
            }

            rs.close();
            ps.close();
            
            return list;
        } catch (SQLException e) {
            System.out.println("Error generating department report.");
            return null;
        }
    }

    public List<DepartmentReport> generateDepartmentReportByYear(int year) {
        List<DepartmentReport> list = new ArrayList<>();
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append("d.department_name, ");
        query.append("YEAR(t.creation_date) AS year, ");
        query.append("c.category_name, ");
        query.append("COUNT(t.ticket_id) AS total_tickets ");
        query.append("FROM departments d ");
        query.append("JOIN employees e ON d.department_id = e.dept_id ");
        query.append("JOIN tickets t ON e.emp_id = t.emp_id ");
        query.append("JOIN categories c ON t.category_id = c.category_id ");
        query.append("WHERE YEAR(t.creation_date) = ? ");
        query.append("GROUP BY d.department_name, YEAR(t.creation_date), c.category_name ");
        query.append("ORDER BY d.department_name, year;");
        
        try (PreparedStatement ps = conn.prepareStatement(query.toString())){
            ps.setInt(1, year);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                DepartmentReport dr = new DepartmentReport(
                    rs.getString("department_name"), 
                    rs.getInt("year"), 
                    rs.getString("category_name"), 
                    rs.getInt("total_tickets"));

                list.add(dr);
            }

            rs.close();
            ps.close();
            
            return list;
        } catch (SQLException e) {
            System.out.println("Error generating department by year report.");
            return null;
        }       
    }
}
