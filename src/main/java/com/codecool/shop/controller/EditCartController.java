package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.database.DatabaseManager;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.User;
import com.codecool.shop.service.OrderService;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.service.SupplierService;
import com.codecool.shop.service.UserService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/shopping_cart"})
public class EditCartController extends HttpServlet {
    DatabaseManager databaseManager = DatabaseManager.getInstance();
    OrderDao orderDataStore = databaseManager.getOrderDataStore();
    LineItemDao lineItemDataStore = databaseManager.getLineItemDataStore();
    OrderService orderservice = new OrderService(orderDataStore, lineItemDataStore);
    UserDao userDao = databaseManager.getUserDao();
    UserService userService = new UserService(userDao);

    public EditCartController() throws IOException {
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = databaseManager.getProductDataStore();
        ProductCategoryDao productCategoryDataStore = databaseManager.getProductCategoryDataStore();
        SupplierDao supplierDataStore = databaseManager.getSupplierDataStore();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDataStore);
        SupplierService supplierService = new SupplierService(supplierDataStore);
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        System.out.println("user id : " + (int) req.getSession().getAttribute("id"));
        int userID = (int) req.getSession().getAttribute("id");
        int orderID = 0;
        User user = null;
        try {
            orderID = orderservice.getOrderIdByUserId(userID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            user = userService.findUserByEmail(String.valueOf(req.getSession().getAttribute("user")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        List<LineItem> items = orderservice.getLineItemsByOrder(orderID);


        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("loggedUser", user);
        context.setVariable("all_categories", productService.getAllCategories());
        context.setVariable("all_suppliers", supplierService.getAllSuppliers());
        context.setVariable("items", items);
        context.setVariable("totalPrice", orderservice.getFullPrice(orderID));
        engine.process("product/cart.html", context, resp.getWriter());

    }
}
