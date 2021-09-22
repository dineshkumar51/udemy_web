package com.servlets;

import com.dao.*;
import com.model.Category;
import com.model.Course;
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

public class AddCourseServlet extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        CourseDao courseDao = new CourseDaoImplementation();

        String topic = req.getParameter("topicId");
        int topicId = Integer.parseInt(topic);
        String courseName = req.getParameter("courseName");
        String user = req.getParameter("instructorId");
        String description = req.getParameter("description");
        int instructorId = Integer.parseInt(user);

        

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

        Course newCourse = new Course(courseName,instructorId,topicId,0.0f,0,0,description);
        courseDao.add(newCourse);


        req.getRequestDispatcher("/WEB-INF/jsp/create-course.jsp").include(req,res);
        out.print("<script>\n" +
                "$(document).ready(function(){\n" +
                "    $('.message').show();\n" +
                "  $('.message').append('Course created successfully !!!');\n" +
                "  setTimeout(function() {\n" +
                "                    $('.message').fadeOut('slow');\n" +
                "                }, 2000);\n" +
                "  });\n" +
                "</script>");

        out.close();

    }
}
