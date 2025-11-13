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
        query.append("JOIN tickets t ");
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
        query.append("JOIN tickets t ");
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
        query.append("JOIN tickets t ");
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
        query.append("FROM departments d ");
        query.append("JOIN employees e ON d.department_id = e.dept_id ");
        query.append("JOIN tickets t ON e.emp_id = t.employee_id ");
        query.append("JOIN categories c ON t.category_id = c.category_id ");
        query.append("GROUP BY d.department_name, YEAR(t.creation_date), c.category_name ");
        query.append("ORDER BY d.department_name, year;");
        
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
        query.append("JOIN tickets t ON e.emp_id = t.employee_id ");
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
        query.append("JOIN tickets t ON e.emp_id = t.employee_id ");
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
