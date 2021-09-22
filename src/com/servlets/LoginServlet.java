package com.servlets;

import com.dao.UserDaoImplementation;
import com.model.User;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;


public class LoginServlet extends HttpServlet
{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String userName = req.getParameter("userName");
        String password = req.getParameter("password");

        UserDaoImplementation userDao = new UserDaoImplementation();

        User user = userDao.getUser(userName);

        if(user != null)
        {
            if(user.getPassword().equals(password))
            {
                HttpSession session = req.getSession();
                session.setAttribute("user",user);
                req.setAttribute("user",user);
                RequestDispatcher rd;
                if(user.getUser_type().equals("I"))
                {
                    rd=req.getRequestDispatcher("WEB-INF/jsp/instructor-homepage.jsp");
                }
                else
                {
                    rd=req.getRequestDispatcher("WEB-INF/jsp/learner-homepage.jsp");
                }

                rd.forward(req, res);
            }
            else
            {

                RequestDispatcher rd = req.getRequestDispatcher("/index.html");
                rd.include(req,res);
                out.print("<script> $(document).ready(function(){ $('#password-error').show(); $('.login-form').css( 'height', '+=20px' ); }); </script>");
            }


        }
        else
        {

            RequestDispatcher rd = req.getRequestDispatcher("/index.html");
            rd.include(req,res);
            out.print("<script> $(document).ready(function(){ $('#username-error').show(); $('.login-form').css( 'height', '+=20px' ); }); </script>");
        }

    }
}
