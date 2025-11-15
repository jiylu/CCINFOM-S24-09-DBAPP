package models;

public class User {
    private int userID;
    private String username;
    private String password;
    private Role role;
    private boolean isActive;

    public enum Role{
        ADMIN,
        TECHNICIAN,
        EMPLOYEE
    }

    public User(int userID, String username, String password, Role role, boolean isActive){
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setUserID(int userID){
        this.userID = userID;
    }

    public void setActive(Boolean isActive){
        this.isActive = isActive;
    }
}
