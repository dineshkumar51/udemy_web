package com.dao;

import com.model.Topic;
import com.util.MysqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicDaoImplementation implements TopicDao
{
    @Override
    public List<Topic> getTopics()
    {
        Connection con = MysqlConnection.getConnection();
        String query = "SELECT * FROM topic";
        List<Topic> topics = null;
        try(PreparedStatement ps = con.prepareStatement(query))
        {
            try(ResultSet rs = ps.executeQuery())
            {
                topics = new ArrayList<>();
                while (rs.next()) {
                    Topic topic = new Topic(rs.getInt(1), rs.getString(2), rs.getInt(3));
                    topics.add(topic);
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
        return topics;
    }

    @Override
    public List<Topic> getTopics(int category_id)
    {
        Connection con = MysqlConnection.getConnection();
        String query = "SELECT * FROM topic WHERE category_id = ?";
        List<Topic> topics = null;
        try(PreparedStatement ps = con.prepareStatement(query))
        {
            ps.setInt(1, category_id);
            try(ResultSet rs = ps.executeQuery()) {
                topics = new ArrayList<>();
                while (rs.next()) {
                    Topic topic = new Topic(rs.getInt(1), rs.getString(2), rs.getInt(3));
                    topics.add(topic);
                }
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try{con.close();}catch (SQLException e){e.printStackTrace();}
        }
        return topics;
    }

    @Override
    public Topic getTopic(int topic_id) {
        Connection con = MysqlConnection.getConnection();
        String query = "SELECT * FROM topic WHERE topic_id = ?";
        Topic topic = null;
        try( PreparedStatement ps = con.prepareStatement(query))
        {
            ps.setInt(1, topic_id);
            try(ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                topic = new Topic(rs.getInt(1), rs.getString(2), rs.getInt(3));
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try{con.close();}catch (SQLException e){e.printStackTrace();}
        }
        return topic;
    }
}
