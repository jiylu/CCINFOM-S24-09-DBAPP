package models;

public class Technicians {
    private int technician_id;
    private String tech_lastName;
    private String tech_firstName;
    private String tech_username;
    private boolean activeTechnician;
    private boolean hasActiveTicket;

    public Technicians(int technician_id, String tech_lastName, String tech_firstName, String tech_username, boolean activeTechnician, boolean hasActiveTicket){
        this.technician_id = technician_id;
        this.tech_lastName = tech_lastName;
        this.tech_firstName = tech_firstName;
        this.tech_username = tech_username;
        this.activeTechnician = activeTechnician;
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

    public String getTech_username(){
        return tech_username;
    }

    public boolean getActiveTechnician(){
        return activeTechnician;
    }

    public boolean isHasActiveTicket(){
        return isHasActiveTicket();
    }

    public void setActiveTechnician(boolean activeTechnician){
        this.activeTechnician = activeTechnician;
    }

    public void setHasActiveTicket(boolean hasActiveTicket){
        this.hasActiveTicket = hasActiveTicket;
    }
}
