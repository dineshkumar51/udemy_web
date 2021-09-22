package com.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LogOutServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        HttpSession session = req.getSession();
        session.invalidate();


        req.getRequestDispatcher("/index.html").include(req,res);
        out.print("<script>\n" +
                "$(document).ready(function(){\n" +
                "    $('.message').show();\n" +
                "  $('.message').append('Logged Out Successfully !');\n" +
                "  setTimeout(function() {\n" +
                "                    $('.message').fadeOut('slow');\n" +
                "                }, 2000);\n" +
                "  });\n" +
                "</script>");
        out.close();
    }

    }
