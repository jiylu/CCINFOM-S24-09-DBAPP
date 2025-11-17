package models;

public class Tickets {
    private int ticket_id;
    private String ticket_subject;
    private String ticket_description; 
    private int category_id;
    private String creationDate;
    private int employee_id;
    private int technician_id;
    private String status;

    public Tickets(int ticketID, String ticketSubject, String ticketDescription, int categoryID, String creationDate, int empID, int techID, String status){
        this.ticket_id = ticketID;
        this.ticket_subject = ticketSubject;
        this.ticket_description = ticketDescription;
        this.category_id = categoryID;
        this.creationDate = creationDate;
        this.employee_id = empID;
        this.technician_id = techID;
        this.status = status;
    }

    public Tickets() {
    }
    
    //getters
    public int getTicket_id() {
        return ticket_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getTicket_subject() {
        return ticket_subject;
    }

    public String getTicket_description() {
        return ticket_description;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public int getTechnician_id() {
        return technician_id;
    }

    public String getCreation_date() {
        return creationDate;
    }

    public String getStatus(){
        return status;
    }

    //setters
    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public void setDescription(String desc) {
        this.ticket_description = desc; 
    }

    public void setCreation_date(String date) {
        this.creationDate = date;
    }

    public void setTechnician_id(int technician_id) {
        this.technician_id = technician_id;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setCategory_id(int category_id){
        this.category_id = category_id;
    }

    public void setSubject(String subject) {
        this.ticket_subject = subject;
    }
}
