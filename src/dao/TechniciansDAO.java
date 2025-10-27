package dao;

import models.Technicians;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TechniciansDAO {
    private Connection conn;

    public TechniciansDAO(Connection conn){
        this.conn = conn;
    }

    public List<Technicians> getAllTechnicians() throws SQLException {
        List<Technicians> list = new ArrayList<>();
        String query = "SELECT * FROM technicians";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()){
            list.add(new Technicians(
                rs.getInt("technician_id"),
                rs.getInt("user_id"),
                rs.getString("tech_lastName"),
                rs.getString("tech_firstName"),
                rs.getBoolean("has_active_ticket")
            ));
        }

        rs.close();
        statement.close();

        return list;
    }
}
