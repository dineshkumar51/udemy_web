package com.servlets;

import com.dao.WishListDao;
import com.dao.WishListDaoImplementation;
import com.model.Course;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class WishlistServlet extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String id = req.getParameter("userId");

        int learnerId = Integer.parseInt(id);

        WishListDao wishListDao =  new WishListDaoImplementation();

        List<Course> courses = wishListDao.getCourses(learnerId);

        req.setAttribute("courses",courses);

        req.getRequestDispatcher("/WEB-INF/jsp/wishlist.jsp").forward(req,res);
        out.close();
    }
}
