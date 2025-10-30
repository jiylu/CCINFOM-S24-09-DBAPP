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
        String query = "SELECT * FROM departments";

        try (Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                list.add(new Department(
                    rs.getInt("department_id"),
                    rs.getString("department_name")
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

    public List<String> getAllDepartmentNames(){
        List<String> list = new ArrayList<>();
        String query = "SELECT department_name FROM departments";

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
                return new Department(rs.getInt(1), rs.getString(2));
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
}