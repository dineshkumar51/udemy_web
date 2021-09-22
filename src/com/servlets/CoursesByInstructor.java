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

public class CoursesByInstructor extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String id = req.getParameter("instructorId");
        int instructorId = Integer.parseInt(id);

        CourseDao courseDao = new CourseDaoImplementation();

        List<Course> courses = courseDao.getCoursesCreatedBy(instructorId);

        req.setAttribute("courses",courses);

        req.getRequestDispatcher("/WEB-INF/jsp/courses.jsp").forward(req,res);
        out.close();


    }
}
