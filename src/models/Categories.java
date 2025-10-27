package models;

public class Categories {
    private int category_id;
    private String category_name;

    public Categories(int category_id, String category_name) {
        this.category_id = category_id;
        this.category_name = category_name;
    }

    // getters
    public int getCategoryID() {
        return category_id;
    }
    public String getCategoryName() {
        return category_name;
    }
    
    // setters for update
    public void setCategoryName(String category_name) {
        this.category_name = category_name;
    }
}
