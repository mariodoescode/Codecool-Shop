package com.codecool.shop.config;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Objects;


@WebListener
public class Initializer implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        DatabaseManager databaseManager = null;
        try {
            databaseManager = DatabaseManager.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ProductDao productDataStore;
        SupplierDao supplierDataStore;
        ProductCategoryDao productCategoryDataStore;
        OrderDao orderDataStore;


        assert databaseManager != null;
        String daoProperty = databaseManager.getProperties().getProperty("dao");

        if (Objects.equals(daoProperty, "jdbc")){
            try {
                databaseManager.setup();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e) {
                System.out.println("Input/Output error");
            } catch (SQLException e) {
                System.out.println("Can't connect to the database.");
            }
//            orderDataStore = databaseManager.getOrderDataStore();
//            int orderId = orderDataStore.getAll().size() + 1;
//            Order newOrder = new Order(orderId, 1, new Date(), "checked");
//            orderDataStore.add(newOrder);
        }
        else {
            databaseManager.setupMem();
//            orderDataStore = databaseManager.getOrderDataStore();
//            int orderId = orderDataStore.getAll().size() + 1;
//            Order newOrder = new Order(orderId);
//            orderDataStore.add(newOrder);
        }

        productDataStore = databaseManager.getProductDataStore();
        supplierDataStore = databaseManager.getSupplierDataStore();
        productCategoryDataStore = databaseManager.getProductCategoryDataStore();
        System.out.println(productDataStore);
        System.out.println(supplierDataStore);
        System.out.println(productCategoryDataStore);
        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier acer = new Supplier("Acer", "Computers");
        supplierDataStore.add(acer);
        Supplier leia = new Supplier("Leia", "");
        supplierDataStore.add(leia);
        Supplier iPhone = new Supplier("iPhone", "");
        supplierDataStore.add(iPhone);
        Supplier samsung = new Supplier("Samsung", "");
        supplierDataStore.add(samsung);
        Supplier gameStation = new Supplier("Game Station", "");
        supplierDataStore.add(gameStation);
        Supplier nintendo = new Supplier("Nintendo", "");
        supplierDataStore.add(nintendo);
        Supplier chuWi = new Supplier("ChuWi", "");
        supplierDataStore.add(chuWi);
        Supplier fujiyama = new Supplier("Fujiyama", "");
        supplierDataStore.add(fujiyama);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory laptop = new ProductCategory("Laptop", "Hardware", "A laptop, sometimes called a notebook computer by manufacturers, is a battery- or AC-powered personal computer (PC) smaller than a briefcase.");
        productCategoryDataStore.add(laptop);
        ProductCategory console = new ProductCategory("Console", "Hardware", "");
        productCategoryDataStore.add(console);
        ProductCategory cellPhone = new ProductCategory("Cell Phone", "Hardware", "");
        productCategoryDataStore.add(cellPhone);
        ProductCategory smartWatch = new ProductCategory("Smart Watch", "Hardware", "");
        productCategoryDataStore.add(smartWatch);
        ProductCategory computer = new ProductCategory("Computer", "Hardware", "");
        productCategoryDataStore.add(computer);
        ProductCategory camera = new ProductCategory("Camera", "Hardware", "");
        productCategoryDataStore.add(camera);
        ProductCategory television = new ProductCategory("Television", "Hardware", "");
        productCategoryDataStore.add(television);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Acer Nitro 5", new BigDecimal("1199"), "USD", "Overall, the Acer Nitro 5 is a smartly focused machine and a superb value.", laptop, acer));
        productDataStore.add(new Product("Acer Aspire 5", new BigDecimal("519"), "USD", "The Acer Aspire 5 is adequate for school use.", laptop, acer));
        productDataStore.add(new Product("Acer Aspire Vero", new BigDecimal("573"), "USD", "A unique eco-friendly laptop.", laptop, acer));


        System.out.println(productDataStore);
        System.out.println(supplierDataStore);
        System.out.println(productCategoryDataStore);
    }
}
