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

public class AddRatingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String id = req.getParameter("courseId");
        int courseId = Integer.parseInt(id);

        CourseDao courseDao = new CourseDaoImplementation();

        Course course = courseDao.getCourse(courseId);
        req.setAttribute("course",course);

        String ratingString = req.getParameter("rating");
        float rating = Float.parseFloat(ratingString);

        float newRating = (course.getRating()*course.getNoOfRatings()+rating)/(course.getNoOfRatings()+1);
        newRating = Math.round(newRating*10.0f)/10.0f;
        course.setRating(newRating);
        course.setNoOfRatings(course.getNoOfRatings()+1);
        courseDao.update(course);

        req.getRequestDispatcher("/WEB-INF/jsp/enrolled-course.jsp").include(req,res);
        out.print("<script>\n" +
                "$(document).ready(function(){\n" +
                "    $('.message').show();\n" +
                "  $('.message').append('Successfully rated !!!');\n" +
                "  setTimeout(function() {\n" +
                "                    $('.message').fadeOut('slow');\n" +
                "                }, 2000);\n" +
                "  });\n" +
                "</script>");
        out.close();


    }
}
