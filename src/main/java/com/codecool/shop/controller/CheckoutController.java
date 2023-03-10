package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    OrderDao order = OrderDaoMem.getInstance();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());

        //  response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        //check @ in email
        //check if hasNumbers
        //check if hasLetters


        // if data is valid create order
        order.add(new Order(
                request.getParameter("name"),request.getParameter("phone-number"),
                request.getParameter("email"), request.getParameter("Baddress"),
                request.getParameter("country"), request.getParameter("city"),
                request.getParameter("zipCodeBA"), request.getParameter("Saddress"),
                request.getParameter("Scountry"), request.getParameter("Scity"),
                request.getParameter("Szip"))
        );

        context.setVariable("orderID", order.getLastOrderID());
        //and if data is valid load payment page
        engine.process("product/payment.html", context, response.getWriter());





    }
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

}
