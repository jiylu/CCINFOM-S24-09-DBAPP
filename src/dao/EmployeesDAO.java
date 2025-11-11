package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Employees;


public class EmployeesDAO {
    private Connection conn;

    public EmployeesDAO(Connection conn){
        this.conn = conn;
    }

    // CREATE 
    public void insertEmployee(Employees emp) {
        String query = "INSERT INTO employees (user_id, last_name, first_name, dept_id, job_title) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            pstmt.setInt(1, emp.getUserID());
            pstmt.setString(2, emp.getLastName());
            pstmt.setString(3, emp.getFirstName());
            pstmt.setInt(4, emp.getDeptID());
            pstmt.setString(5, emp.getJobTitle());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            if (rs.next()){
                emp.setEmpID(rs.getInt(1));
            }

            pstmt.close();
            rs.close();

            System.out.println("Successfully inserted " + emp.getLastName() + ", " + emp.getFirstName() + " to the employees table.");
        } catch (Exception e) {
            System.out.println("Failed to insert " + emp.getLastName() + ", " + emp.getFirstName() + " to the employees table.");
            e.printStackTrace();
        }
    }


    // READ (onlu active employees)
    public List<Employees> getAllEmployees() {
        List<Employees> list = new ArrayList<>();
        String query = "SELECT * FROM employees"; 

        try (Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                list.add(new Employees(
                    rs.getInt("emp_id"),
                    rs.getInt("user_id"),
                    rs.getString("last_name"),
                    rs.getString("first_name"),
                    rs.getInt("dept_id"),
                    rs.getString("job_title")
                ));
            }

            rs.close();
            stmt.close();
            return list;
        } catch (SQLException e) {
            System.out.println("Error retrieving all active employees.");
            return null;
        }
    }

    public List<Employees> getEmployeesByDepartment(int departmentID){
        List<Employees> list = new ArrayList<>();
        String query = "SELECT * FROM employees e WHERE e.dept_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, departmentID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                list.add(new Employees(
                    rs.getInt("emp_id"),
                    rs.getInt("user_id"),
                    rs.getString("last_name"),
                    rs.getString("first_name"),
                    rs.getInt("dept_id"),
                    rs.getString("role")
                ));
            }

            rs.close();
            pstmt.close();
            return list;
        } catch (SQLException e){
            System.out.println("Error executing getEmployeesByDepartment().");
            return null;
        }
    }

    public void updateEmployee(Employees emp){
        String query = "UPDATE employees SET last_name = ?, first_name = ?, dept_id = ?, job_title = ? WHERE emp_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, emp.getLastName());
            pstmt.setString(2, emp.getFirstName());
            pstmt.setInt(3, emp.getDeptID());
            pstmt.setString(4, emp.getJobTitle());
            pstmt.setInt(5, emp.getEmpID());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Emp " + emp.getEmpID() + " deactivated successfully.");
            } else {
                System.out.println("No emp found with ID " + emp.getEmpID());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE


}
