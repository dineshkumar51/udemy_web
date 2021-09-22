package com.dao;

import com.model.User;


import java.util.List;

public interface UserDao
{
     int add(User user);

     void delete(int id);

     User getUser(int id);

     User getUser(String userName);

     List<User> getUsers(String type);

     void update(User user,String type);

}
