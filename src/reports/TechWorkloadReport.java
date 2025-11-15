package reports;

public class TechWorkloadReport {
    private String technician;
    private int year;
    private int assignedTickets;
    private int resolvedTickets;
    private int averageTime;

    public TechWorkloadReport(int year, String technician, int assignedTickets, int resolvedTickets, int averageTime){
        this.year = year;
        this.technician = technician;
        this.assignedTickets = assignedTickets;
        this.resolvedTickets = resolvedTickets;
        this.averageTime = averageTime;
    }

    public String getTechnician() {
        return technician;
    }

    public int getYear() {
        return year;
    }
    public int getAssignedTickets() {
        return assignedTickets;
    }

    public int getResolvedTickets() {
        return resolvedTickets;
    }

    public int getAverageTime() {
        return averageTime;
    }
}