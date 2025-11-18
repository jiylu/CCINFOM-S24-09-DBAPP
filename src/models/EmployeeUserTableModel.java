package models;

public class EmployeeUserTableModel {
    private int userID;
    private int empID;
    private String username;
    private String password;
    private String name;
    private String department;
    private String jobTitle;
    private boolean status;

    public EmployeeUserTableModel(int userID, int empID, String username, String password, String name, String department, String jobTitle, boolean status) {
        this.userID = userID;
        this.empID = empID;
        this.username = username;
        this.password = password;
        this.name = name;
        this.department = department;
        this.jobTitle = jobTitle;
        this.status = status;
    }

    public int getUserID() {
        return userID;
    }

    public int getEmpID() {
        return empID;
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

    public String getDepartment() {
        return department;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public boolean isActive() {
        return status;
    }
}
