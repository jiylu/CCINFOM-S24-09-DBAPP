package models;

public class User {
    private int userID;
    private String username;
    private String password;
    private Role role;
    private Boolean isActive;

    public enum Role{
        ADMIN,
        TECHNICIAN,
        EMPLOYEE
    }

    public User(int userID, String username, String password, Role role){
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.role = role;
        this.isActive = true;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setActive(Boolean isActive){
        this.isActive = isActive;
    }
}
