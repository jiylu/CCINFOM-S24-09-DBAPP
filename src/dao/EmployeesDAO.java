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
        String query = "INSERT INTO employees (user_id, last_name, first_name, dept_id, role) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            pstmt.setInt(1, emp.getUserID());
            pstmt.setString(2, emp.getLastName());
            pstmt.setString(3, emp.getFirstName());
            pstmt.setInt(4, emp.getDeptID());
            pstmt.setString(5, emp.getRole());
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
        String query = "SELECT * FROM employees WHERE is_active = TRUE"; 

        try (Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(query);

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
            stmt.close();
            return list;
        } catch (SQLException e) {
            System.out.println("Error retrieving all active employees.");
            return null;
        }
    }

    // UPDATE (set is_active to false)
    public void deactivateEmployee(int emp_id) {
        String query = "UPDATE employees SET is_active = FALSE WHERE emp_id = ?";
    
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, emp_id);
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("Deactivated emp_id: " + emp_id);        
        } catch (SQLException e) {
            System.out.println("Unsucessfully deactivated emp_id: " + emp_id);
        }
    }

    // DELETE


}
