package com.dao;

import com.model.Course;
import com.util.MysqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WishListDaoImplementation implements WishListDao {

    @Override
    public List<Course> getCourses(int learner_id) {
        Connection con = MysqlConnection.getConnection();
        String query = "SELECT c.* FROM wishlist e INNER JOIN course c on e.course_id = c.course_id WHERE e.learner_id = ?";
        List<Course> courses = null;
        try(PreparedStatement ps = con.prepareStatement(query))
        {
            ps.setInt(1,learner_id);
            try(ResultSet rs = ps.executeQuery()) {
                courses = new ArrayList<>();
                while (rs.next()) {
                    Course course = new Course(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getFloat(5), rs.getInt(6), rs.getInt(7), rs.getString(8));
                    courses.add(course);
                }
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try{con.close();}catch (SQLException e){e.printStackTrace();}
        }
        return courses;
    }

    @Override
    public int addToWishList(int course_id, int learner_id) {
        Connection con = MysqlConnection.getConnection();
        String query = "INSERT INTO wishlist(learner_id, course_id) VALUES (?,?)";
        int n = 0;
        try( PreparedStatement ps = con.prepareStatement(query))
        {
            ps.setInt(1, learner_id);
            ps.setInt(2, course_id);
            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try{con.close();}catch (SQLException e){e.printStackTrace();}
        }
        return n;
    }

    @Override
    public boolean checkFromWishList(int course_id, int learner_id) {
        Connection con = MysqlConnection.getConnection();
        String query = "SELECT * FROM wishlist WHERE course_id = ? AND learner_id = ?";
        boolean check = false;
        try(PreparedStatement ps = con.prepareStatement(query))
        {
            ps.setInt(1,course_id);
            ps.setInt(2,learner_id);
            try(ResultSet rs = ps.executeQuery()) {
                check = rs.next();
            }

        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try{con.close();}catch (SQLException e){e.printStackTrace();}
        }
        return check;
    }
}
