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

public class AddtoWishlistServlet extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String id = req.getParameter("courseId");
        int courseId = Integer.parseInt(id);

        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");

        WishListDao wishListDao = new WishListDaoImplementation();
        CourseDao courseDao = new CourseDaoImplementation();
        UserDao userDao = new UserDaoImplementation();

        Course course = courseDao.getCourse(courseId);
        User instructor = userDao.getUser(course.getInstructorId());

        boolean check = wishListDao.checkFromWishList(courseId,user.getId());

        req.setAttribute("course",course);
        req.setAttribute("instructor",instructor);

        req.getRequestDispatcher("/WEB-INF/jsp/course-detail.jsp").include(req,res);

        if(check == false)
        {
            wishListDao.addToWishList(courseId, user.getId());
            out.print("<script>\n" +
                    "$(document).ready(function(){\n" +
                    "    $('.message').show();\n" +
                    "  $('.message').append('Successfully added to Wishlist!!');\n" +
                    "  setTimeout(function() {\n" +
                    "                    $('.message').fadeOut('slow');\n" +
                    "                }, 2000);\n" +
                    "  });\n" +
                    "</script>");
        }
        else
        {
            out.print("<script>\n" +
                    "$(document).ready(function(){\n" +
                    "    $('.message').show();\n" +
                    "  $('.message').append('Already added to Wishlist!!');\n" +
                    "  setTimeout(function() {\n" +
                    "                    $('.message').fadeOut('slow');\n" +
                    "                }, 2000);\n" +
                    "  });\n" +
                    "</script>");
        }
        out.close();


    }
}
