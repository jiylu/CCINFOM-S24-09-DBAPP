package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Department;

public class DepartmentDAO {
    private Connection conn;

    public DepartmentDAO(Connection conn){
        this.conn = conn;
    }

    public List<Department> getAllDepartments(){
        List<Department> list = new ArrayList<>();
        String query = "SELECT * FROM departments ORDER BY active DESC, department_id";

        try (Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                list.add(new Department(
                    rs.getInt("department_id"),
                    rs.getString("department_name"),
                    rs.getBoolean("active")
                ));
            }

            rs.close();
            stmt.close();
            return list;
        } catch (Exception e) {
            System.out.println("Error retrieving all departments.");
            return null;
        }
    }

    public List<String> getAllDepartmentNames(boolean excludeAdministration){
        List<String> list = new ArrayList<>();
        String query = null;
        if (excludeAdministration){
            query = "SELECT department_name FROM departments WHERE department_name != 'Administration'";
        } else {
            query = "SELECT department_name FROM departments";
        }

        try (Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                list.add(rs.getString("department_name"));
            }

            rs.close();
            stmt.close();
            return list;
            
        } catch (SQLException e){
            System.out.println("Error retrieving dept names");
            return null;
        }
    }

    public Department getDepartmentByID(int departmentID) {
        String query = "SELECT department_id, department_name FROM departments WHERE department_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, departmentID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                return new Department(
                    rs.getInt(1), 
                    rs.getString(2),
                    rs.getBoolean(3));
            }

            System.out.println("Department not found.");
            
            rs.close();
            ps.close();
            return null;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public Integer getDepartmentIDByName(String department){
        String query = "SELECT department_id FROM departments WHERE department_name= ?";
        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, department);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                return rs.getInt(1);
            }

            System.out.println("Department not found.");
            
            rs.close();
            ps.close();
            return null;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void insertDepartment(String departmentName){
        String query = "INSERT INTO departments (department_name) VALUES (?)";

        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, departmentName);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error inserting department.");
        }
    }

    public void editDepartment(int deptID, String departmentName){
        String query = "UPDATE departments SET department_name = ? WHERE department_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, departmentName);
            ps.setInt(2, deptID);
            
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0){
                System.out.println("Successful editDepartment");
            } else {
                System.out.println("No rows returned");
            }
        } catch (SQLException e) {
            System.out.println("editDepartment Error.");
        }
    }

    public void deactivateDepartment(int deptID){
        String query = "UPDATE departments SET active = 0 WHERE department_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, deptID);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0){
                System.out.println("Sucessfully deactivated dept_id: " + deptID);
            } else {
                System.out.println("Unsucessfuly deactivated dept_id: " +  deptID);
            }

        } catch (SQLException e){
            System.out.println("deactivateDepartment error");
        }
    }
}