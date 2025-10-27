package dao;

import models.Employees;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmployeesDAO {
    private Connection conn;

    public EmployeesDAO(Connection conn){
        this.conn = conn;
    }

    // CREATE 
    public void insertEmployee(Employees emp) throws SQLException {
        String query = "INSERT INTO employees (user_id, last_name, first_name, dept_id, role, is_active) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, emp.getUserID());
        pstmt.setString(2, emp.getLastName());
        pstmt.setString(3, emp.getFirstName());
        pstmt.setInt(4, emp.getDeptID());
        pstmt.setString(5, emp.getRole());
        pstmt.setBoolean(6, emp.isActive());

        pstmt.executeUpdate();
        pstmt.close();
    }


    // READ (onlu active employees)
    public List<Employees> getAllEmployees() throws SQLException {
        List<Employees> list = new ArrayList<>();
        String query = "SELECT * FROM employees WHERE is_active = TRUE"; 
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()){
            list.add(new Employees(
               rs.getInt("emp_id"),
               rs.getInt("user_id"),
               rs.getString("last_name"),
               rs.getString("first_name"),
               rs.getInt("dept_id"),
               rs.getString("role"),
                rs.getBoolean("is_active")
            ));
        }

        rs.close();
        stmt.close();

        return list;
    }

    // UPDATE (set is_active to false)
    public void deactivateEmployee(int emp_id) throws SQLException {
        String query = "UPDATE employees SET is_active = FALSE WHERE emp_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, emp_id);
        pstmt.executeUpdate();
        pstmt.close();
    }

    // DELETE


}
