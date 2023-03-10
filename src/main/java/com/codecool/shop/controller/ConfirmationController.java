package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Order;
import com.google.gson.Gson;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;


@WebServlet(urlPatterns = {"/confirmation"})
public class ConfirmationController extends HttpServlet {

    ProductDao productDao = ProductDaoMem.getInstance();
    ShoppingCartDao shoppingCart = ShoppingCartDaoMem.getInstance();

    OrderDao order = OrderDaoMem.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());

        //  response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        context.setVariable("order",order.find(order.getLastOrderID()));
        System.out.println(order.find(order.getLastOrderID()));

        Gson gson = new Gson();
        String jsonObject = gson.toJson(order.find(order.getLastOrderID()));

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

