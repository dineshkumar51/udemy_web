package com.dao;

import com.model.Course;
import com.util.MysqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImplementation implements CourseDao
{
    @Override
    public int add(Course course)
    {
        Connection con = MysqlConnection.getConnection();
        String query = "INSERT INTO course(name, instructor_id, topic_id, description) VALUES (?,?,?,?)";
        int n = 0;
        try(PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, course.getName());
            ps.setInt(2, course.getInstructorId());
            ps.setInt(3, course.getTopicId());
            ps.setString(4, course.getDescription());
            n = ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try{con.close();}catch (SQLException e){e.printStackTrace();}
        }
        return n;

    }

    @Override
    public void update(Course course) {

        Connection con = MysqlConnection.getConnection();
        String query = "UPDATE course"+
                " SET name = ?, instructor_id = ?, topic_id = ?,rating = ?,no_of_ratings = ?, no_of_enrolled_students = ?"+
                " WHERE course_id = ?";
        try(PreparedStatement ps = con.prepareStatement(query))
        {
            ps.setString(1, course.getName());
            ps.setInt(2, course.getInstructorId());
            ps.setInt(3, course.getTopicId());
            ps.setFloat(4, course.getRating());
            ps.setInt(5, course.getNoOfRatings());
            ps.setInt(6, course.getNoOfEnrolledStudents());
            ps.setInt(7, course.getId());
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try{con.close();}catch (SQLException e){e.printStackTrace();}
        }
    }

    @Override
    public Course getCourse(int course_id)
    {
        Connection con = MysqlConnection.getConnection();
        String query = "SELECT * FROM course WHERE course_id = ?";
        Course course = null;
        try(PreparedStatement ps = con.prepareStatement(query)){
            ps.setInt(1, course_id);
            try(ResultSet rs = ps.executeQuery())
            {
                if (!rs.next()) {
                    return null;
                }
                course = new Course(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getFloat(5), rs.getInt(6), rs.getInt(7), rs.getString(8));
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try{con.close();}catch (SQLException e){e.printStackTrace();}
        }
        return course;
    }

    @Override
    public List<Course> getCourses(String searchString) {

        Connection con = MysqlConnection.getConnection();
        String query = String.format("SELECT * FROM course WHERE name LIKE '%%%s%%'",searchString);
        List<Course> courses = null;
        try(PreparedStatement ps = con.prepareStatement(query))
        {
            try(ResultSet rs = ps.executeQuery())
            {
                courses = new ArrayList<>();
                while (rs.next())
                {
                    Course course = new Course(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getFloat(5), rs.getInt(6), rs.getInt(7), rs.getString(8));
                    courses.add(course);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try{con.close();}catch (SQLException e){e.printStackTrace();}
        }
        return courses;

    }


    @Override
    public List<Course> getCourses() {
        Connection con = MysqlConnection.getConnection();
        String query = "SELECT * FROM course";
        List<Course> courses = null;
        try(PreparedStatement ps = con.prepareStatement(query))
        {
            try(ResultSet rs = ps.executeQuery())
            {
                courses = new ArrayList<>();
                while (rs.next())
                {
                    Course course = new Course(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getFloat(5), rs.getInt(6), rs.getInt(7), rs.getString(8));
                    courses.add(course);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try{con.close();}catch (SQLException e){e.printStackTrace();}
        }
        return courses;
    }


    @Override
    public List<Course> getCoursesCreatedBy(int instructor_id) {
        Connection con = MysqlConnection.getConnection();
        String query = "SELECT * FROM course WHERE instructor_id = ?";
        List<Course> courses = null;
        try( PreparedStatement ps = con.prepareStatement(query))
        {
            ps.setInt(1, instructor_id);
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
    public List<Course> getCoursesUnder(int topic_id) {
        Connection con = MysqlConnection.getConnection();
        String query = "SELECT * FROM course WHERE topic_id = ?";
        List<Course> courses = null;
        try( PreparedStatement ps = con.prepareStatement(query))
        {
            ps.setInt(1, topic_id);
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
}
