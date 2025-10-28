package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import models.User;

public class UserDAO {
    private Connection conn;
    
    public UserDAO(Connection conn){
        this.conn = conn;
    }

    public void insertUser(User user){
        String query = "INSERT INTO Users (username, password, role) VALUES (?, ?, ?)";
        
        try (PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole().name());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            
            if (rs.next()){
                user.setUserID(rs.getInt(1));
            } 

            rs.close();
            ps.close();

            System.out.println("Inserted " + user.getUsername() + " successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to insert " + user.getUsername() + " to users table.");
            e.printStackTrace();
        }
    }
}
