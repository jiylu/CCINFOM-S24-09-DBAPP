package models;

public class TechUser{
    private int userID;
    private int techID;

    public TechUser(int userID, int techID) {
        this.userID = userID;
        this.techID = techID;
    }

    public int getUserID(){
        return userID;
    }

    public int getTechID() {
        return techID;
    }
}

