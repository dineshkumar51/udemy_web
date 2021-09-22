package com.servlets;

import com.dao.CategoryDao;
import com.dao.CategoryDaoImplementation;
import com.dao.TopicDao;
import com.dao.TopicDaoImplementation;
import com.model.Category;
import com.model.Topic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        CategoryDao categoryDao = new CategoryDaoImplementation();
        TopicDao topicDao = new TopicDaoImplementation();

        List<Category> categories = categoryDao.getCategories();

        Map<Integer, List<Topic>> allTopics = new HashMap<>();

        for (Category c :categories)
        {
            List<Topic> topics = topicDao.getTopics(c.getId());
            allTopics.put(c.getId(),topics);
        }


        req.setAttribute("topics",allTopics);
        req.setAttribute("categories",categories);

        req.getRequestDispatcher("/WEB-INF/jsp/categories.jsp").forward(req,res);
        out.close();
    }
}
