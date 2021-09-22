package com.servlets;

import com.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig arg0) {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");

        if(user != null)
        {
            req.setAttribute("user",user);
            filterChain.doFilter(req,res);
        }
        else
        {
            req.getRequestDispatcher("/index.html").include(req,res);
            out.print("<script>\n" +
                    "$(document).ready(function(){\n" +
                    "    $('.message').show();\n" +
                    "  $('.message').append('Please Login to continue!');\n" +
                    "  setTimeout(function() {\n" +
                    "                    $('.message').fadeOut('slow');\n" +
                    "                }, 2000);\n" +
                    "  });\n" +
                    "</script>");
        }

        out.close();
    }

    @Override
    public void destroy() {}
}