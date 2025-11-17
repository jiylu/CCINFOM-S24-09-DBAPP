package models;

public class EmployeeCreatesTicket {
    private int create_id;
    private int ticket_id;
    private int emp_id;
    private String creation_date;

    public EmployeeCreatesTicket() {}

    public EmployeeCreatesTicket(int create_id, int ticket_id, int emp_id, String creation_date) {
        this.create_id = create_id;
        this.ticket_id = ticket_id;
        this.emp_id = emp_id;
        this.creation_date = creation_date;
    }

    public int getCreate_id() { return create_id; }
    public int getTicket_id() { return ticket_id; }
    public int getEmp_id() { return emp_id; }
    public String getCreation_date() { return creation_date; }

    public void setEmp_id(int emp_id) { this.emp_id = emp_id; }
    public void setCreation_date(String creation_date) { this.creation_date = creation_date; }
}
