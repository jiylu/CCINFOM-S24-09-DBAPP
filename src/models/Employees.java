package models;

public class Employees {
    private int emp_id;
    private int user_id;
    private String last_name;
    private String first_name;
    private int dept_id;
    private String jobTitle;

    public Employees(int emp_id, int user_id, String last_name, String first_name, int dept_id, String jobTitle) {
        this.emp_id = emp_id;
        this.user_id = user_id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.dept_id = dept_id;
        this.jobTitle = jobTitle;
    }

    public int getEmpID() {
        return emp_id;
    }

    public int getUserID() {
        return user_id;
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

    public String getJobTitle() {
        return jobTitle;
    }


// setters for update

    public void setEmpID(int emp_id){
        this.emp_id = emp_id;
    }

    public void setUserID(int user_id) {
        this.user_id = user_id;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public void setDeptID(int dept_id) {
        this.dept_id = dept_id;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

}