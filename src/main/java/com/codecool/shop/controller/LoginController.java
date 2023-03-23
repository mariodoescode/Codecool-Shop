package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.DatabaseManager;
import com.codecool.shop.model.User;
import com.codecool.shop.service.UserService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        engine.process("product/loginpage.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = new User(req.getParameter("login_email"), req.getParameter("login_password"));
        UserDao userDao = DatabaseManager.getInstance().getUserDao();
        UserService userService = new UserService(userDao);
        String email = req.getParameter("login_email");
        PrintWriter out = resp.getWriter();

        try {
            if (userDao.findByEmail(email) == null){
                resp.sendRedirect(req.getContextPath()+"/login");
            }
            else {
                HttpSession session = req.getSession();
                session.setAttribute("user", email);
                out.print("You are succesfully logged in " + email);
                out.print(req.getContextPath());
                resp.sendRedirect(req.getContextPath()+ "/");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

}