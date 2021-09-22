package com.dao;

import com.model.Category;
import com.util.MysqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImplementation implements CategoryDao
{
    @Override
    public List<Category> getCategories()
    {
        Connection con = MysqlConnection.getConnection();
        String query = "SELECT * FROM category";
        List<Category> categories = null;
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            categories = new ArrayList<>();
            while (rs.next()) {
                Category category = new Category(rs.getInt(1), rs.getString(2));
                categories.add(category);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try{con.close();}catch (SQLException e){e.printStackTrace();}
        }


        return categories;
    }

    @Override
    public Category getCategory(int category_id)
    {
        Connection con = MysqlConnection.getConnection();
        String query = "SELECT * FROM category WHERE category_id = ?";
        Category category = null;
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,category_id);
            ResultSet rs = ps.executeQuery();
            if(!rs.next()) {
                return null;
            }
            category = new Category(rs.getInt(1), rs.getString(2));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try{con.close();}catch (SQLException e){e.printStackTrace();}
        }
        return category;
    }
}
