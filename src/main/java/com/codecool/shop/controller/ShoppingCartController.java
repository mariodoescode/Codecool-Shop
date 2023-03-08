package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;


@WebServlet(urlPatterns = {"/shopping-cart"})
public class ShoppingCartController extends HttpServlet {

    ProductDao productDao = ProductDaoMem.getInstance();
    ShoppingCartDao shoppingCart = ShoppingCartDaoMem.getInstance();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());

        //  response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (request.getParameter("id") != null) {
            int productId = Integer.parseInt(request.getParameter("id"));
            Product product = productDao.find(productId);
            shoppingCart.add(product);
        }
        HashMap<String, String> order = new HashMap<>();
        Enumeration paramNames = request.getParameterNames();
        while(paramNames.hasMoreElements()) {
            String nextName = (String) paramNames.nextElement();
            order.put(nextName,request.getParameter(nextName));
            System.out.println(order);
        } if(order.containsKey("country")) {
            String site = "http://localhost:8888/payment" ;
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", site);
        }
        context.setVariable("products", shoppingCart.getAllProducts());
        context.setVariable("totalPrice", shoppingCart.getTotalPrice());
        engine.process("product/cart.html", context, response.getWriter());
    }

    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }


}