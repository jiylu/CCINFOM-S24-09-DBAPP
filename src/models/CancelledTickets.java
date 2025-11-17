package models;

public class CancelledTickets {

    private int cancel_id;
    private int ticket_id;
    private String cancel_date;
    private String cancel_reason;

    public CancelledTickets() {}

    public CancelledTickets(int cancel_id, int ticket_id, String cancel_date, String cancel_reason) {
        this.cancel_id = cancel_id;
        this.ticket_id = ticket_id;
        this.cancel_date = cancel_date;
        this.cancel_reason = cancel_reason;
    }

    public int getCancel_id() { return cancel_id; }
    public int getTicket_id() { return ticket_id; }
    public String getCancel_date() { return cancel_date; }
    public String getCancel_reason() { return cancel_reason; }

    public void setCancel_date(String cancel_date) { this.cancel_date = cancel_date; }
    public void setCancel_reason(String cancel_reason) { this.cancel_reason = cancel_reason; }
}
