package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Categories;

public class CategoriesDAO {
    private Connection conn;
    
    public CategoriesDAO(Connection conn){
        this.conn = conn;
    }

    public List<Categories> getAllCategories() {
        List<Categories> list = new ArrayList<>();
        String query = "SELECT * FROM categories";

        try (Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                list.add(new Categories(
                    rs.getInt("category_id"),
                    rs.getString("category_name")
                ));
            }

            rs.close();
            stmt.close();
            return list;
            
        } catch (SQLException e) {
            System.out.println("Error retrieving all categories.");
            return null;
        }
    }

    public List<String> getAllCategoryNames(){
        List<String> list = new ArrayList<>();
        String query = "SELECT category_name FROM categories";

        try (Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                list.add(rs.getString("category_name"));
            }

            rs.close();
            stmt.close();
            return list;

        } catch (SQLException e){
            System.out.println("Error retrieving category names");
            return null;
        }
    }
}
