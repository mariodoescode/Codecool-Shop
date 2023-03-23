package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.DatabaseManager;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;
import com.codecool.shop.service.UserService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

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

            req.getRequestDispatcher("index.html").include(req, resp);

            HttpSession session=req.getSession();
            session.invalidate();

            out.print("You are successfully logged out!");

            out.close();

        }

}
