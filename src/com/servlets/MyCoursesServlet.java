package com.servlets;

import com.dao.*;
import com.model.Course;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class MyCoursesServlet extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String id = req.getParameter("userId");
        int learnerId = Integer.parseInt(id);

        EnrollmentDao enrollmentDao =  new EnrollmentDaoImplementation();
        List<Course> mycourses = enrollmentDao.getEnrolledCourses(learnerId);
        req.setAttribute("mycourses",mycourses);
        req.getRequestDispatcher("/WEB-INF/jsp/mycourses.jsp").forward(req,res);
        out.close();
    }
}
