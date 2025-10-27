package models;

public class Technicians {
    private int technician_id;
    private String tech_lastName;
    private String tech_firstName;
    private int user_id;
    private boolean hasActiveTicket;

    public Technicians(int technician_id, int user_id, String tech_lastName, String tech_firstName, boolean hasActiveTicket){
        this.technician_id = technician_id;
        this.user_id = user_id;
        this.tech_lastName = tech_lastName;
        this.tech_firstName = tech_firstName;
        this.hasActiveTicket = hasActiveTicket;
    }

    public int getTechnician_id(){
        return technician_id;
    }

    public String getTech_lastName(){
        return tech_lastName;
    }

    public String getTech_firstName(){
        return tech_firstName;
    }

    public int getUser_ID(){
        return user_id;
    }

    public boolean isHasActiveTicket(){
        return isHasActiveTicket();
    }

    public void setHasActiveTicket(boolean hasActiveTicket){
        this.hasActiveTicket = hasActiveTicket;
    }
}
