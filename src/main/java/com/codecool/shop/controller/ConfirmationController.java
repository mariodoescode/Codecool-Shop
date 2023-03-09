package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Order;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        context.setVariable("products",order.find(Integer.parseInt(request.getParameter("orderID"))));

        if(request.getParameter("expyear")!=null) {
            if (request.getParameter("expyear").equals("2018")) {
                engine.process("product/confirmation.html", context, response.getWriter());
                System.out.println("confirmation route");
            } else {
                int status = response.getStatus();
                response.sendError(status, "Error while processing the payment");
                System.out.println("else route");
            }
        }
    }
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

}

