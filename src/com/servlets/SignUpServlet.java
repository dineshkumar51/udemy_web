package com.servlets;


import com.dao.UserDaoImplementation;
import com.model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SignUpServlet extends HttpServlet
{
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String userName = req.getParameter("userName");
        String fullName = req.getParameter("fullName");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String userType = req.getParameter("userType");

        UserDaoImplementation userDao = new UserDaoImplementation();

        User user = userDao.getUser(userName);

        if(user != null) {


            req.getRequestDispatcher("/signup.html").include(req,res);
            out.print("<script> $(document).ready(function(){ $('#username-error').show(); $('.main').css( 'height', '+=20px' ); }); </script>");


        }
        else if(!password.equals(confirmPassword))
        {

            req.getRequestDispatcher("/signup.html").include(req,res);
            out.print("<script> $(document).ready(function(){ $('#password-error').show(); $('.login-form').css( 'height', '+=20px' ); }); </script>");

        }
        else
        {
            User newUser = new User(userName,password,fullName,userType);
            int n = userDao.add(newUser);
            if(n==1)
            {

                req.getRequestDispatcher("/index.html").include(req,res);
                out.print("<script>\n" +
                        "$(document).ready(function(){\n" +
                        "    $('.message').show();\n" +
                        "  $('.message').append('Account Created Successfully !');\n" +
                        "  setTimeout(function() {\n" +
                        "                    $('.message').fadeOut('slow');\n" +
                        "                }, 2000);\n" +
                        "  });\n" +
                        "</script>");
            }
        }
        out.close();


    }
}
