package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

@WebServlet(urlPatterns = {"/shopping-cart"})
public class ShoppingCartController extends HttpServlet {

    ProductDao productDao = ProductDaoMem.getInstance();
    ShoppingCartDao shoppingCart = ShoppingCartDaoMem.getInstance();

    private BigDecimal totalPrice;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());

        //  response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if(request.getParameter("id") != null){
            int productId = Integer.parseInt(request.getParameter("id"));
            Product product = productDao.find(productId);
            shoppingCart.add(product);
        } else if (request.getParameter("plus") != null) {
            int productId = Integer.parseInt(request.getParameter("plus"));
            Product product = productDao.find(productId);
            product.setQuantity(product.getQuantity() + 1);
        }
        else if (request.getParameter("minus") != null) {
            int productId = Integer.parseInt(request.getParameter("minus"));
            Product product = productDao.find(productId);
            if(product.getQuantity() <= 1){
                shoppingCart.remove(productId);
            }else{
                product.setQuantity(product.getQuantity() - 1);
            }
        }
        context.setVariable("products",shoppingCart.getAllProducts());
        context.setVariable("totalPrice",shoppingCart.getTotalPrice());

        PrintWriter out = response.getWriter();

        System.out.println(shoppingCart.getAllProducts());

        engine.process("product/cart.html", context, response.getWriter());
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}