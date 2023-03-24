package com.codecool.shop.controller;

import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.dao.OrderDao;

import com.codecool.shop.dao.database.DatabaseManager;
import com.codecool.shop.model.LineItem;

import com.codecool.shop.model.Order;
import com.codecool.shop.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/order"})
public class OrderController extends HttpServlet {

    DatabaseManager databaseManager = DatabaseManager.getInstance();
    OrderDao orderDataStore = databaseManager.getOrderDataStore();
    LineItemDao lineItemDataStore = databaseManager.getLineItemDataStore();
    OrderService orderservice = new OrderService(orderDataStore, lineItemDataStore);

    public OrderController() throws IOException {
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String strProductPrice = request.getParameter("prod_price");
        System.out.println(strProductPrice);
        strProductPrice = strProductPrice.replaceAll("[^\\d.]", "");
        System.out.println("after modification " + strProductPrice);
        BigDecimal productPrice = new BigDecimal(strProductPrice);
        String productDescription = request.getParameter("desc");
        String productName = request.getParameter("prod_name");
        String supplier = request.getParameter("supplier");
        int productId = Integer.parseInt(request.getParameter("prod_id"));
        int userId = (int) request.getSession().getAttribute("id");

        if (orderservice.userHasCheckedOrder(userId)) {
            int orderId = 0;
            try {
                orderId = orderservice.getOrderIdByUserId(userId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            orderservice.addLineItem(productPrice,productName,productDescription,orderId,supplier,productId);
        } else {
            Order order = new Order(orderservice.getAllOrders().size()+1,userId,new Date(),"checked");
            orderservice.createNewOrder(order);
            int orderId = 0;
            try {
                orderId = orderservice.getOrderIdByUserId(userId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            orderservice.addLineItem(productPrice,productName,productDescription,orderId,supplier,productId);
        }
        //if userid has an order checked create a new order
        //create an order with an userid from session
        //if order is checked add product to line item with order id
    }


}