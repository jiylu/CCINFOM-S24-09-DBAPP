package dao;

import models.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    private Connection conn;

    public DepartmentDAO(Connection conn){
        this.conn = conn;
    }

    public List<Department> getAllDepartments() throws SQLException {
        List<Department> list = new ArrayList<>();
        String query = "SELECT * FROM departments";
        Statement stmt = conn.createStatement();
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
    }
}