package models;

public class UserAccount {
    private  int userID;
    private  String role;
    private  String username;
    private  String password;

    public static final String ADMIN_ROLE = "Admin"; 
    public static final String EMP_ROLE = "Employee"; 
    public static final String TECH_ROLE = "Technician";

    public UserAccount(int userID, String role, String username, String password){
        this.userID = userID;
        this.role = role;
        this.username = username;
        this.password = password;
    }
    
    public int getUserID() { 
        return userID; 
    }

    public String getRole(){
        return role;
    }

    public String getUsername() { 
        return username; 
    }

    public String getPassword() { 
        return password; 
    }
}
