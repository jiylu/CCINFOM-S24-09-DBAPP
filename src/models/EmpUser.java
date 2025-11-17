package models;

public class EmpUser{
    private int userID;
    private int empID;

    public EmpUser(int userID, int empID) {
        this.userID = userID;
        this.empID = empID;
    }

    public int getUserID(){
        return userID;
    }

    public int getEmpID() {
        return empID;
    }
}