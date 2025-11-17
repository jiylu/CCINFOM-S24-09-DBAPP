package models;

public class ResolvedTickets {

    private int resolve_id;
    private int ticket_id;
    private String resolve_date;

    public ResolvedTickets() {}

    public ResolvedTickets(int resolve_id, int ticket_id, String resolve_date) {
        this.resolve_id = resolve_id;
        this.ticket_id = ticket_id;
        this.resolve_date = resolve_date;
    }

    public int getResolve_id() { return resolve_id; }
    public int getTicket_id() { return ticket_id; }
    public String getResolve_date() { return resolve_date; }

    public void setResolve_date(String resolve_date) { this.resolve_date = resolve_date; }
}
