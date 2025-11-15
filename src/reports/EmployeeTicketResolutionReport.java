package reports;

public class EmployeeTicketResolutionReport {
    private String employee;
    private int year;
    private int submittedTickets;
    private int resolvedTickets;
    private int cancelledTickets;

    public EmployeeTicketResolutionReport(int year, String employee, int submittedTickets, int resolvedTickets, int cancelledTickets){
        this.year = year;
        this.employee = employee;
        this.submittedTickets = submittedTickets;
        this.resolvedTickets = resolvedTickets;
        this.cancelledTickets = cancelledTickets;
    }

    public String getEmployee() {
        return employee;
    }

    public int getYear() {
        return year;
    }

    public int getSubmittedTickets() {
        return submittedTickets;
    }

    public int getResolvedTickets() {
        return resolvedTickets;
    }

    public int getCancelledTickets(){
        return cancelledTickets;
    }
}
