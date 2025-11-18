package models;

public class TechnicianUserTableModel {
    private int userID;
    private int techID;
    private String username;
    private String password;
    private String name;
    private boolean status;

    public TechnicianUserTableModel(int userID, int techID, String username, String password, String name, boolean status) {
        this.userID = userID;
        this.techID = techID;
        this.username = username;
        this.password = password;
        this.name = name;
        this.status = status;
    }

    public int getUserID() {
        return userID;
    }

    public int getTechID() {
        return techID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return status;
    }
}
