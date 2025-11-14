package reports;

public class TechWorkloadReport {
    private String technician;
    private int assignedTickets;
    private int resolvedTickets;
    private int averageTime;

    public TechWorkloadReport(String technician, int assignedTickets, int resolvedTickets, int averageTime){
        this.technician = technician;
        this.assignedTickets = assignedTickets;
        this.resolvedTickets = resolvedTickets;
        this.averageTime = averageTime;
    }

    public String getTechnician() {
        return technician;
    }

    public int getAssignedTickets() {
        return assignedTickets;
    }

    public int getResolvedTickets() {
        return resolvedTickets;
    }

    public int averageTime() {
        return averageTime;
    }
}