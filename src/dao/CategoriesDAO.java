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

    public List<Integer> getAllCategoryIds() throws SQLException {
        List<Integer> ids = new ArrayList<>();
        String query = "SELECT category_id FROM Categories";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            ids.add(rs.getInt("category_id"));
        }
        return ids;
    }

    public String getCategoryNameById(int id) throws SQLException {
        String query = "SELECT category_name FROM Categories WHERE category_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getString("category_name");
        }
        return null;
    }

    public Integer getCategoryIDByName(String name){
        String query = "SELECT category_id FROM Categories WHERE category_name = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                return rs.getInt(1);
            }

            return null;
        } catch (SQLException e) {
            System.out.println("Error getCategoryIDByName()");
            return null;
        }
    }
}
