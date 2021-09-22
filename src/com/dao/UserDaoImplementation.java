package com.dao;

import com.model.User;
import com.util.MysqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImplementation implements UserDao
{

    @Override
    public int add(User user)
    {
        Connection con = MysqlConnection.getConnection();
        String query = "INSERT INTO user (username, password, full_name, user_type, created_date) "+
                    "VALUES (?,?,?,?,CURRENT_TIMESTAMP())";

        int n = 0;
        try( PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getUser_type());
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
    public void delete(int id)
    {
        Connection con = MysqlConnection.getConnection();
        String query = String.format("DELETE FROM user WHERE id = ?");

        try(PreparedStatement ps = con.prepareStatement(query))
        {
            ps.setInt(1, id);
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
    public User getUser(int id)
    {
        Connection con = MysqlConnection.getConnection();
        String query = String.format("SELECT * FROM user WHERE id = ?");
        User user = null;
        try ( PreparedStatement ps = con.prepareStatement(query))
        {

            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery())
            {
                if (!rs.next()) {
                    return null;
                }
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try{con.close();}catch (SQLException e){e.printStackTrace();}
        }
        return user;
    }

    @Override
    public User getUser(String userName){
        Connection con = MysqlConnection.getConnection();
        String query = "SELECT * FROM user WHERE username = ?";
        User user = null;
        try(PreparedStatement ps = con.prepareStatement(query))
        {
            ps.setString(1, userName);

            try(ResultSet rs = ps.executeQuery())
            {
                if (!rs.next()) {
                    return null;
                }
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try{con.close();}catch (SQLException e){e.printStackTrace();}
        }
        return user;
    }

    @Override
    public List<User> getUsers(String type)
    {
        Connection con = MysqlConnection.getConnection();
        String query;
        if(type.equals("instructor"))
        {
            query = String.format("SELECT * FROM user WHERE user_type = 'I'");
        }
        else
        {
            query = String.format("SELECT * FROM user WHERE user_type = 'L'");
        }
        List<User> userList = new ArrayList<>();
        try(PreparedStatement ps = con.prepareStatement(query))
        {
            try( ResultSet rs = ps.executeQuery())
            {
                while (rs.next())
                {
                    User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                    userList.add(user);
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
        return userList;


    }

    @Override
    public void update(User user, String type)
    {
        Connection con = MysqlConnection.getConnection();
        String query = "UPDATE user "+
                "SET username = ?,password = ?,full_name = ? "+
                "WHERE id = ?";
        try(PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFullName());
            ps.setInt(4, user.getId());
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
}
