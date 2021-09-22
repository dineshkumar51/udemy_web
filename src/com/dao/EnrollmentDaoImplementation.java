package com.dao;

import com.model.Course;
import com.util.MysqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDaoImplementation implements EnrollmentDao
{

    @Override
    public List<Course> getEnrolledCourses(int learner_id)
    {
        Connection con = MysqlConnection.getConnection();
        String query = "SELECT c.* FROM enrollments e INNER JOIN course c on e.course_id = c.course_id WHERE e.learner_id = ?";
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
    public int addEnrollment(int course_id, int learner_id) {

        Connection con = MysqlConnection.getConnection();
        Statement statement = null;
        int n = 0;
        try {
            con.setAutoCommit(false);
            String insertquery = String.format("INSERT INTO enrollments(learner_id, course_id, enrolled_date) VALUES (%d,%d,CURRENT_TIMESTAMP)", learner_id, course_id);
            String updatequery = String.format("UPDATE course SET no_of_enrolled_students = no_of_enrolled_students+1 WHERE course_id = %d;", course_id);
            statement = con.createStatement();
            n = statement.executeUpdate(insertquery);
            n = statement.executeUpdate(updatequery);
            con.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
            try{ con.rollback();}catch (SQLException e1){e1.printStackTrace();}
        }
        finally {
            try{statement.close();}catch (SQLException e1){e1.printStackTrace();}
            try{con.close();}catch (SQLException e1){e1.printStackTrace();}
        }

        return n;
    }

    @Override
    public boolean checkForEnrollment(int course_id, int learner_id)
    {
        Connection con = MysqlConnection.getConnection();
        String query = "SELECT * FROM enrollments WHERE course_id = ? AND learner_id = ?";
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

