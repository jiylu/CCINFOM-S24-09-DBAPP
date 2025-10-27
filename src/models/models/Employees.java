package models;

public class Employees {
    private int emp_id;
    private String last_name;
    private String first_name;
    private int dept_id;
    private String role;

    public Employees(int emp_id, String last_name, String first_name, int dept_id, String role) {
        this.emp_id = emp_id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.dept_id = dept_id;
        this.role = role;
    }

// getters
    public int getEmpID() {
        return emp_id;
    }

    public String getLastName() {
        return last_name;
    }

    public String getFirstName() {
        return first_name;
    }

    public int getDeptID() {
        return dept_id;
    }

    public String getRole() {
        return role;
    }

// setters for update

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public void setDeptID(int dept_id) {
        this.dept_id = dept_id;
    }

    public void setRole(String role) {
        this.role = role;
    }

}