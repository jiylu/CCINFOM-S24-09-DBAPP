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

}