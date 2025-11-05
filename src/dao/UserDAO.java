package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.User;
import models.User.Role;

public class UserDAO {
    private Connection conn;
    
    public UserDAO(Connection conn){
        this.conn = conn;
    }


    public List<User> getAllUsers(){
        List<User> list = new ArrayList<>();
        String query = "SELECT * FROM Users";

        try (Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                list.add(new User(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    User.Role.valueOf(rs.getString(4).toUpperCase())
                ));
            }

            rs.close();
            stmt.close();
            return list;
        } catch (Exception e) {
            System.out.println("Error retrieving all users");
            return null;
        }
    }

    public Integer insertUser(User user){
        String query = "INSERT INTO Users (username, password, role) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole().name());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            
            if (rs.next()){
                int userID = rs.getInt(1);
                user.setUserID(userID);
                return userID;       
            } 

            rs.close();
            ps.close();

            System.out.println("Inserted " + user.getUsername() + " successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to insert " + user.getUsername() + " to users table.");
            e.printStackTrace();
        }

        return null;
    }

    public User getUserByLogin(String username, String password){
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                
                if (!rs.getBoolean(5)){
                    return null;
                }

                return new User(
                    rs.getInt(1), 
                    username, 
                    password, 
                    Role.valueOf(rs.getString(4).toUpperCase()));
            }

            rs.close();
            ps.close();
            return null;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public User getUserByUsername(String username){
        String query = "SELECT * FROM Users WHERE username = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                
                if (!rs.getBoolean(5)){
                    return null;
                }

                return new User(
                    rs.getInt(1), 
                    username, 
                    rs.getString(3), 
                    Role.valueOf(rs.getString(4).toUpperCase()));
            }

            rs.close();
            ps.close();
            return null;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void deleteUser(int userID) {
        String query = "DELETE FROM Users WHERE user_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userID);
            int rowsAffected = ps.executeUpdate(); // ✅ use executeUpdate()
            
            if (rowsAffected > 0) {
                System.out.println("User with ID " + userID + " deleted successfully.");
            } else {
                System.out.println("No user found with ID " + userID + ".");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
