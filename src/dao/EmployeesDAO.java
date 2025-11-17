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
        String query = "INSERT INTO employees (last_name, first_name, dept_id, job_title) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            pstmt.setString(1, emp.getLastName());
            pstmt.setString(2, emp.getFirstName());
            pstmt.setInt(3, emp.getDeptID());
            pstmt.setString(4, emp.getJobTitle());
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
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getString(5),
                    rs.getBoolean(6)
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

        public Employees getEmployeeByUserId(int userId) {
            StringBuilder query = new StringBuilder();
            query.append("SELECT e.emp_id, e.last_name, e.first_name, e.dept_id, e.job_title, e.active ");
            query.append("FROM Employees e ");
            query.append("JOIN EmployeeUsers eu ON e.emp_id = eu.emp_id ");
            query.append("WHERE eu.user_id = ?");

            try (PreparedStatement ps = conn.prepareStatement(query.toString())) {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    return new Employees(
                        rs.getInt("emp_id"),
                        rs.getString("last_name"),
                        rs.getString("first_name"),
                        rs.getInt("dept_id"),
                        rs.getString("job_title"),
                        rs.getBoolean("active")
                    );
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return null;
        }

    public List<Employees> getEmployeesByDepartment(int departmentID){
        List<Employees> list = new ArrayList<>();
        String query = "SELECT * FROM employees e WHERE e.dept_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, departmentID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                list.add(new Employees(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getString(5),
                    rs.getBoolean(6)
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

    //DELETE
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
                System.out.println("Emp " + emp.getEmpID() + " activated successfully.");
            } else {
                System.out.println("No emp found with ID " + emp.getEmpID());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllEmployeeNames() {
        List<String> employeesNames = new ArrayList<>();

        String query = "SELECT CONCAT(first_name, ' ', last_name) AS full_name FROM employees";

        try (Statement statement = conn.createStatement()){
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()){
                employeesNames.add(rs.getString("full_name"));
            }
            return employeesNames;
        } catch (SQLException e) {
            System.out.println("Error retrieving all employee names.");
            e.printStackTrace();
            return null;
        }
    }

    public int getEmployeeIDByName(String name) {
        String sql = "SELECT emp_id FROM employees" +
                " WHERE CONCAT(first_name, ' ', last_name) = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("emp_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public void deactivateEmployee(int empID) {
        String query = "UPDATE employees SET active = FALSE WHERE emp_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, empID);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Employee " + empID + " deactivated successfully.");
            } else {
                System.out.println("No employee found with ID " + empID);
            }
        } catch (SQLException e) {
            System.out.println("Error deactivating employee.");
            e.printStackTrace();
        }
    }

}