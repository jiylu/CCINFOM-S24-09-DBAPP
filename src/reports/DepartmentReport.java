package reports;

public class DepartmentReport {
    private String department;
    private int year;
    private String category;
    private int totalTickets;

    public DepartmentReport(String department, int year, String category, int totalTickets){
        this.department = department;
        this.year = year;
        this.category = category;
        this.totalTickets = totalTickets;
    }

    public String getDepartment() {
        return department;
    }

    public int getYear() {
        return year;
    }

    public String getCategory() {
        return category;
    }

    public int getTotalTickets() {
        return totalTickets;
    }
}