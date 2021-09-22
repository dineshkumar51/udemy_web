package com.servlets;

import com.dao.*;
import com.model.Category;
import com.model.Topic;
import com.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateCourse extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String id = req.getParameter("userId");
        int instructorId = Integer.parseInt(id);

        UserDao userDao = new UserDaoImplementation();
        CategoryDao categoryDao = new CategoryDaoImplementation();
        TopicDao topicDao = new TopicDaoImplementation();

        User instructor = userDao.getUser(instructorId);

        List<Category> categories = categoryDao.getCategories();

        Map<Integer, List<Topic>> allTopics = new HashMap<>();

        for (Category c :categories)
        {
            List<Topic> topics = topicDao.getTopics(c.getId());
            allTopics.put(c.getId(),topics);
        }


        req.setAttribute("topics",allTopics);
        req.setAttribute("categories",categories);
        req.setAttribute("instructor",instructor);

        req.getRequestDispatcher("/WEB-INF/jsp/create-course.jsp").forward(req,res);
        out.close();
    }
}
