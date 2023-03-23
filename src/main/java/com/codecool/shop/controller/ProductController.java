package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.service.SupplierService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import com.codecool.shop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {
    DatabaseManager databaseManager = DatabaseManager.getInstance();
    ProductDao productDataStore = databaseManager.getProductDataStore();
    ProductCategoryDao productCategoryDataStore = databaseManager.getProductCategoryDataStore();
    SupplierDao supplierDataStore = databaseManager.getSupplierDataStore();
    ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDataStore);
    SupplierService supplierService = new SupplierService(supplierDataStore);
    UserDao userDao = databaseManager.getUserDao();
    UserService userService = new UserService(userDao);

    public ProductController() throws IOException {
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        if (req.getParameter("category") != null) {
            String IdFromURL = req.getParameter("category");
            int convertedIDFromURL = Integer.parseInt(IdFromURL);
            context.setVariable("products", productService.getProductsForCategory(convertedIDFromURL));
        } else if (req.getParameter("supplier") != null) {
            String IdFromURL = req.getParameter("supplier");
            int convertedIDFromURL = Integer.parseInt(IdFromURL);
            context.setVariable("products", productService.getProductsForSupplier(convertedIDFromURL));
        } else {
            context.setVariable("products", productService.getAllProducts());
        }
        if (req.getSession().getAttribute("user") != null) {
            context.setVariable("user", req.getSession().getAttribute("user") );
        }
        context.setVariable("all_categories", productService.getAllCategories());
        context.setVariable("all_suppliers", supplierService.getAllSuppliers());

        engine.process("product/index.html", context, resp.getWriter());

    }
}