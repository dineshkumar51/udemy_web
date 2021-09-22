package com.servlets;

import com.dao.CourseDao;
import com.dao.CourseDaoImplementation;
import com.dao.UserDao;
import com.dao.UserDaoImplementation;
import com.model.Course;
import com.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EnrolledCourseServlet extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String id = req.getParameter("courseId");
        int courseId = Integer.parseInt(id);

        CourseDao courseDao = new CourseDaoImplementation();

        Course course = courseDao.getCourse(courseId);
        UserDao userDao = new UserDaoImplementation();

        User instructor = userDao.getUser(course.getInstructorId());

        req.setAttribute("course",course);
        req.setAttribute("instructor",instructor);

        req.getRequestDispatcher("/WEB-INF/jsp/enrolled-course.jsp").forward(req,res);
        out.close();


    }
}
