package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {
    ProductDao productDataStore = ProductDaoMem.getInstance();
    ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
    ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDataStore);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());


        if (req.getParameter("category") != null) {
            String IdFromURL = req.getParameter("category");
            int convertedIDFromURL = Integer.parseInt(IdFromURL);
            context.setVariable("products", productService.getProductsForCategory(convertedIDFromURL));
        } else if (req.getParameter("supplier") != null){
            String IdFromURL = req.getParameter("supplier");
            int convertedIDFromURL = Integer.parseInt(IdFromURL);
            context.setVariable("products", productService.getProductsForSupplier(convertedIDFromURL));
        } else {
            context.setVariable("products", productService.getAllProducts());
        }

        context.setVariable("categories", productService.getAllProductCategory());
        context.setVariable("suppliers", productService.getAllProductSupplier());

        engine.process("product/index.html", context, resp.getWriter());

    }
}