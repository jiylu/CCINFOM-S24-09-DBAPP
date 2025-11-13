package reports;

public class CategoryReport {
    private String category;
    private int year;
    private int totalSubmitted;
    private int totalResolved;

    public CategoryReport(String category, int year, int totalSubmitted, int totalResolved){
        this.category = category;
        this.year = year;
        this.totalSubmitted = totalSubmitted;
        this.totalResolved = totalResolved;
    }

    public String getCategory() {
        return category;
    }

    public int getYear() {
        return year;
    }

    public int getTotalSubmitted() {
        return totalSubmitted;
    }

    public int getTotalResolved() {
        return totalResolved;
    }
}