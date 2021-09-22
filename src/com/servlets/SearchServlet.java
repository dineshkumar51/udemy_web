package com.servlets;

import com.dao.CourseDao;
import com.dao.CourseDaoImplementation;
import com.model.Course;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SearchServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String searchString = req.getParameter("searchString");

        CourseDao courseDao = new CourseDaoImplementation();

        List<Course> courses = courseDao.getCourses(searchString);

        req.setAttribute("courses",courses);

        req.getRequestDispatcher("/WEB-INF/jsp/courses.jsp").forward(req,res);
        out.close();


    }
}
