package com.servlets;

import com.dao.*;
import com.model.Course;
import com.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class EnrollServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String id = req.getParameter("courseId");
        int courseId = Integer.parseInt(id);

        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");

        EnrollmentDao enrollmentDao = new EnrollmentDaoImplementation();
        CourseDao courseDao = new CourseDaoImplementation();
        UserDao userDao = new UserDaoImplementation();

        Course course = courseDao.getCourse(courseId);
        User instructor = userDao.getUser(course.getInstructorId());

        Boolean check = enrollmentDao.checkForEnrollment(courseId, user.getId());

        if(check == false) {
            int n = enrollmentDao.addEnrollment(courseId, user.getId());
            out.print("Enrolled successfully!!!!");
        }
        else
        {
            out.print("Already enrolled !!!");
        }
        req.setAttribute("course",course);
        req.setAttribute("instructor",instructor);

        req.getRequestDispatcher("/WEB-INF/jsp/enrolled-course.jsp").include(req,res);

        out.close();


    }

}
