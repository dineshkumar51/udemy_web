package com.servlets;


import com.dao.UserDao;
import com.dao.UserDaoImplementation;
import com.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class InstructorServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        UserDao UserDao = new UserDaoImplementation();

        List<User> instructors = UserDao.getUsers("instructor");

        req.setAttribute("instructors",instructors);

        req.getRequestDispatcher("/WEB-INF/jsp/instructors.jsp").forward(req,res);
        out.close();
    }
}
