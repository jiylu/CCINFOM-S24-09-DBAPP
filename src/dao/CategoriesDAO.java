package dao;

import models.Categories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDAO {
    private Connection conn;
    
    public CategoriesDAO(Connection conn){
        this.conn = conn;
    }

    public List<Categories> getAllCategories() throws SQLException {
        List<Categories> list = new ArrayList<>();
        String query = "SELECT * FROM categories";
        Statement stmt = conn.createStatement();
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
    }
}
