package models;

public class Tickets {
    private int ticket_id;
    private int category_id;
    private int department_id;
    private int employee_id;
    private int technician_id;
    private String creation_date;
    private String resolve_date;

    public Tickets(int ticket_id, int category_id, int department_id, int employee_id, int technician_id, String creation_date, String resolve_date) {
        this.ticket_id = ticket_id;
        this.category_id = category_id;
        this.department_id = department_id;
        this.employee_id = employee_id;
        this.technician_id = technician_id;
        this.creation_date = creation_date;
        this.resolve_date = resolve_date;
    }
    
    //getters
    public int getTicket_id() {
        return ticket_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public int getTechnician_id() {
        return technician_id;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public String getResolve_date() {
        return resolve_date;
    }

    //setters
    public void setTechnician_id(int technician_id) {
        this.technician_id = technician_id;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public void setResolve_date(String resolve_date) {
        this.resolve_date = resolve_date;
    }
}
