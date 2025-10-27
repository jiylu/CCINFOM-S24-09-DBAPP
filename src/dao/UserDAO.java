package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import models.User.Role;

public class UserDAO {
    private Connection conn;
    
    public UserDAO(Connection conn){
        this.conn = conn;
    }

    public void insertUser(String username, String password, Role role){
        String query = "INSERT INTO Users (username, password, role) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role.name());
            ps.executeUpdate();
            System.out.println("Inserted " + username + " successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to insert " + username + " to users table.");
            e.printStackTrace();
        }
    }
}
