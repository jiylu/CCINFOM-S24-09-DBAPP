package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Technicians;

public class TechniciansDAO {
    private Connection conn;

    public TechniciansDAO(Connection conn){
        this.conn = conn;
    }

    public List<Technicians> getAllTechnicians() {
        List<Technicians> list = new ArrayList<>();
        String query = "SELECT * FROM technicians";

        try (Statement statement = conn.createStatement()){
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
        } catch (SQLException e) {
            System.out.println("Error retrieving all technicians");
            return null;
        }
    }
}
