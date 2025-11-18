package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.EmpUser;
import models.TechUser;
import models.UserAccount;

public class UserDAO {
    private Connection conn;
    
    public UserDAO(Connection conn){
        this.conn = conn;
    }

    public List<UserAccount> getAllUserAccounts(){
        String query = "SELECT * FROM UserAccounts";
        List<UserAccount> list = new ArrayList<>();

        try (Statement st = conn.createStatement()){
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()){
                UserAccount u = new UserAccount(
                    rs.getInt(1), 
                    rs.getString(2), 
                    rs.getString(3), 
                    rs.getString(4)
                );


                list.add(u);
            }

            rs.close();
            st.close();
            return list;
        } catch (SQLException e){
            System.out.println("getAllUserAccounts error");
            return null;
        }
    }

    public List<EmpUser> getAllEmpUsers(){
        String query = "SELECT * FROM EmployeeUsers";
        List<EmpUser> list = new ArrayList<>();

        try (Statement st = conn.createStatement()){
            ResultSet rs = st.executeQuery(query);

            while (rs.next()){
                EmpUser e = new EmpUser(
                    rs.getInt(1),
                    rs.getInt(2)
                );

                list.add(e);
            }

            rs.close();
            st.close();

            return list;
        } catch (SQLException e){
            System.out.println("getAllEmpUsers error");
            return null;
        }
    }

    public EmpUser getEmpUserByID(int userID){
        String query = "SELECT * FROM EmployeeUsers WHERE user_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                EmpUser empUser = new EmpUser(
                    rs.getInt(1),
                    rs.getInt(2)
                );
                rs.close();
                ps.close();
                return empUser;
            }

            rs.close();
            ps.close();
            return null;
        } catch (SQLException e){
            System.out.println("getEmpUserByID error.");
            return null;
        }
    }

    public TechUser getTechUserByID(int userID){
        String query = "SELECT * FROM TechnicianUsers WHERE user_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                TechUser techUser = new TechUser(
                    rs.getInt(1),
                    rs.getInt(2)
                );
                rs.close();
                ps.close();
                return techUser;
            }

            rs.close();
            ps.close();
            return null;
        } catch (SQLException e){
            System.out.println("getTechUserByID error.");
            return null;
        }
    }

    public List<TechUser> getAllTechUsers(){
        String query = "SELECT * FROM TechnicianUsers";
        List<TechUser> list = new ArrayList<>();

        try (Statement st = conn.createStatement()){
            ResultSet rs = st.executeQuery(query);

            while (rs.next()){
                TechUser t = new TechUser(
                    rs.getInt(1),
                    rs.getInt(2)
                );

                list.add(t);
            }

            rs.close();
            st.close();

            return list;
        } catch (SQLException e){
            System.out.println("getAllEmpUsers error");
            return null;
        }
    }

    
    public UserAccount getUserAccountByID(int userID){
        String query = "SELECT * FROM UserAccounts WHERE user_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                UserAccount user = new UserAccount(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)

                );
                rs.close();
                ps.close();
                return user;
            }

            rs.close();
            ps.close();
            return null;
        } catch (SQLException e){
            System.out.println("getUserAccountByID error.");
            return null;
        }
    }

    public UserAccount insertUserAccount(String role, String username, String password){
        String query = "INSERT INTO UserAccounts (role, username, password) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, role);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()){
                int userID = rs.getInt(1);
                System.out.println("Inserted new user account: " + userID + " " + username + " " + password);
                return new UserAccount(userID, username, password, role);
            }

            System.out.println("Failed to insert a new user account.");
            return null;

        } catch (SQLException e){
            System.out.println("insertUserAccount error.");
            return null;
        }
    }


    public void insertEmpUser(EmpUser empUser){
        String query = "INSERT INTO EmployeeUsers (user_id, emp_id) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, empUser.getUserID());
            ps.setInt(2, empUser.getEmpID());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0){
                System.out.println("Successfully inserted EMP_ID " + empUser.getEmpID() + " to EmpUser table.");
            } else {
                System.out.println("Unsuccessfully inserted EMP_ID " + empUser.getEmpID() + " to EmpUser table.");
            }

        } catch (SQLException e){
            System.out.println("insertEmpUser error.");
            e.printStackTrace();
        }
    }

    public void insertTechUser(TechUser techUser){
        String query = "INSERT INTO TechnicianUsers (user_id, technician_id) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, techUser.getUserID());
            ps.setInt(2, techUser.getTechID());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0){
                System.out.println("Successfully inserted TECH_ID " + techUser.getTechID() + " to TechUser table.");
            } else {
                System.out.println("Unsuccessfully inserted TECH_ID " + techUser.getTechID() + " to TechUser table.");
            }

        } catch (SQLException e){
            System.out.println("insertTechUser error.");
            e.printStackTrace();
        }
    }

    public UserAccount getUserByLogin(String username, String password){
        String query = "SELECT * FROM UserAccounts WHERE username = ? AND password = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                return new UserAccount(
                    rs.getInt(1), 
                    rs.getString(2), 
                    username, 
                    password
                );
            }

            rs.close();
            ps.close();
            return null;
        } catch (SQLException e){
            System.out.println("getUserByLogin error.");
            return null;
        }
    }

    public void editUser(UserAccount user){
        String query = "UPDATE UserAccounts SET username = ?, password = ?, role = ? WHERE user_id = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            ps.setInt(4, user.getUserID());    

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User " + user.getUserID() + " updated successfully.");
            } else {
                System.out.println("No user found with ID " + user.getUserID());
            }

            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserAccount getUserByUsername(String username){
        String query = "SELECT * FROM UserAccounts WHERE username = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                return new UserAccount(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)
                );
            }

            rs.close();
            ps.close();
            return null;
        } catch (SQLException e){
            System.out.println("getUserByUsername error.");
            return null;
        }
    }
}