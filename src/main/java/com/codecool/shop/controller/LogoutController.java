package com.codecool.shop.controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

    @WebServlet(urlPatterns = {"/logout"})
    public class LogoutController extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
            resp.setContentType("text/html");
            PrintWriter out=resp.getWriter();



            HttpSession session=req.getSession();
            session.invalidate();
            resp.sendRedirect(req.getContextPath()+ "/");


        }

}
