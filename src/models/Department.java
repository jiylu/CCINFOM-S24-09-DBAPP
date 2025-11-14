package models;

public class Department {
    private int departmentID;
    private String departmentName;
    private boolean active;

    public Department(int departmentID, String departmentName, boolean active) {
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.active = active;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public boolean getActive(){
        return active;
    }
}
