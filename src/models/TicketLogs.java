package models;

public class TicketLogs {
    private int log_id;
    private int ticket_id;
    private int technician_id;
    private String log_date;   
    private String log_activity;

    public TicketLogs(int log_id, int ticket_id, int technician_id, String log_date, String log_activity) {
        this.log_id = log_id;
        this.ticket_id = ticket_id;
        this.technician_id = technician_id;
        this.log_date = log_date;
        this.log_activity = log_activity;
    }

    //getters
    public int getLog_id() {
        return log_id;
    }

    public int getTicket_id() {
        return ticket_id;
    }

    public int getTechnician_id() {
        return technician_id;
    }

    public String getLog_date() {
        return log_date;
    }

    public String getLog_activity() {
        return log_activity;
    }

    //setters
    public void setLog_date(String log_date) {
        this.log_date = log_date;
    }

    public void setLog_activity(String log_activity) {
        this.log_activity = log_activity;
    }
}
