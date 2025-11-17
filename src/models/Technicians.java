package models;

public class Technicians {
    private int technician_id;
    private String tech_lastName;
    private String tech_firstName;
    private boolean active;

    public Technicians(int technician_id, String tech_lastName, String tech_firstName, boolean active){
        this.technician_id = technician_id;
        this.tech_lastName = tech_lastName;
        this.tech_firstName = tech_firstName;
        this.active = active;
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

    public boolean isActive(){
        return active;
    }

    public void setTechID(int techID){
        this.technician_id = techID;
    }
}
