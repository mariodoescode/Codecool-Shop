package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.database.DatabaseManager;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.LineItemDaoMem;
import com.codecool.shop.model.User;
import com.codecool.shop.service.UserService;
import com.google.gson.Gson;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(urlPatterns = {"/confirmation"})
public class ConfirmationController extends HttpServlet {
    DatabaseManager databaseManager = DatabaseManager.getInstance();

    ProductDao productDao = ProductDaoMem.getInstance();
    LineItemDao shoppingCart = LineItemDaoMem.getInstance();

    OrderDao order = databaseManager.getOrderDataStore();
    UserDao userDao = databaseManager.getUserDao();


    public ConfirmationController() throws IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        UserService userService = new UserService(userDao);
        //  response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        int userId = (int) request.getSession().getAttribute("id");
        User user = null;



        try {
            user = userService.findUserByEmail((String) request.getSession().getAttribute("user"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        context.setVariable("order",user);
        Gson gson = new Gson();
        String jsonObject = gson.toJson(user);

        String file = ".\\/src\\/order_detail.json";
        FileWriter fw = null;

        try {
            fw = new FileWriter(file);
            gson.toJson(jsonObject, fw);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

            engine.process("product/confirmation.html", context, response.getWriter());
    }
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

}

