package com.codecool.shop.config;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.database.DatabaseManager;
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
import java.util.Date;
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
//            databaseManager.setupMem();
//            orderDataStore = databaseManager.getOrderDataStore();
//            int orderId = orderDataStore.getAll().size() + 1;
//            Order newOrder = new Order(orderId);
//            orderDataStore.add(newOrder);
        }

        productDataStore = databaseManager.getProductDataStore();
        supplierDataStore = databaseManager.getSupplierDataStore();
        productCategoryDataStore = databaseManager.getProductCategoryDataStore();

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
        productDataStore.add(new Product("Amazon Fire", new BigDecimal("49"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Acer Nitro 5", new BigDecimal("1199"), "USD", "Overall, the Acer Nitro 5 is a smartly focused machine and a superb value.", laptop, acer));
        productDataStore.add(new Product("Acer Aspire 5", new BigDecimal("519"), "USD", "The Acer Aspire 5 is adequate for school use.", laptop, acer));
        productDataStore.add(new Product("Acer Aspire Vero", new BigDecimal("573"), "USD", "A unique eco-friendly laptop.", laptop, acer));
        productDataStore.add(new Product("ChuWi HeroBook Por+", new BigDecimal("799"), "USD", "CHUWI latest 13.3 inch laptop HeroBook Pro + 3200*1800 screen Intel J3455 processor Win10 OS 8 GB RAM + 128 GB ROM Super PC", computer, chuWi));
        productDataStore.add(new Product("iPhone Smart Watch", new BigDecimal("699"), "USD", "New Men and Women Smart Watch IOS Smart Watch Electronic Smart Watch Fitness Tracker Silicone Strap Smart Watch ", smartWatch, iPhone));
        productDataStore.add(new Product("Samsung Smart Watch", new BigDecimal("599"), "USD", "Hot selling new product Q18S smart watch, compatible with Samsung, Xiaomi, Huawei, IPHONE. Android, iOS smartphones iPhone", smartWatch, samsung));
        productDataStore.add(new Product("iPhone Fit Smart Watch", new BigDecimal("499"), "USD", "Color screen smart wristwatch 115 Plus blood pressure monitor heart rate monitor smart wristband", smartWatch, iPhone));
        productDataStore.add(new Product("Samsung Tablet", new BigDecimal("399"), "USD", "2022 Netbook PC Tablets Support NETFLIX!!! Newest Upgrade RAM 12G+ROM 1024GB+ 10Core WiFi Tablet PC Android 12.0 2560*1600 IPS Screen Dual SIM Dual Camera Rear 13.0 MP IPS Tablet Call Phone Tablet Gift with Earphone+256GB memory card 11.6inch Tablet with Keyboard", tablet, samsung));
        productDataStore.add(new Product("Leia Tablet", new BigDecimal("399"), "USD", "Android Tablet 10 inch,Tablet with Keyboard Mouse 3GB RAM 32GB ROM/128GB, Android 9.0 Pie, Dual SIM 4G, 8MP Camera, 8000mAh, Quad Core, OTG, GPS (Gray)", tablet, leia));
        productDataStore.add(new Product("iPhone Tablet", new BigDecimal("599"), "USD", "2021 Tablet Newest Upgrade RAM 12G+ROM 640GB+ 12 Core WiFi Tablet PC Android 12.0 2560*1600 IPS Screen Dual SIM Dual Camera Rear 13.0 MP IPS Tablet Call Phone Tablet Tablet-PC Tablet 9.6-inch tablet computer", tablet, iPhone));
        productDataStore.add(new Product("Game Station PXP", new BigDecimal("199"), "USD", "Best quality PXP 3 handheld game console 150+ games gift games for kids", console, gameStation));
        productDataStore.add(new Product("Nintendo Retro Console", new BigDecimal("299"), "USD", "Retro Classic Game Console, Mini Classic Game System Built-in 620 Classic Handheld Games and 2X 4 Classic Edition Controller Av Output Video Games", console, nintendo));
        productDataStore.add(new Product("Game Station PS1 Retro Console", new BigDecimal("99"), "USD", "Classic 8-bit games console for PS1 Mini Home 620 action game enthusiast entertainment system Retro dual buckle console", console, gameStation));
        productDataStore.add(new Product("Nintendo Retro Hand Console", new BigDecimal("99"), "USD", "New style retro game console Built-in 268/400/800 games Retro game console Pocket handheld video game console 2.0/3.0 LCD 8-bit mini portable game player", console, nintendo));
        productDataStore.add(new Product("Nintendo Switch", new BigDecimal("499"), "USD", "2020 new update 4.3/5.1 inch handheld game player with 8GB ROM PSP console handheld game machine Built-in 20000+ games PK Nintendo Switch X7/X7Plus Options", console, nintendo));
        productDataStore.add(new Product("iPhone 13 Pro Max", new BigDecimal("899"), "USD", "Unlocked Cell Phones I13pro Max Cell Phones 4G 5G Dual SIM Cards Support T Card (12GB RAM+512GB ROM) Android 10.0 Smartphone", cellPhone, iPhone));
        productDataStore.add(new Product("Samsung S30", new BigDecimal("799"), "USD", "In 2021, a newly launched 6.7-inch 3K HD screen 16+512GB fingerprint smartphone (champagne gold, psychedelic blue, dark night green)", cellPhone, samsung));
        productDataStore.add(new Product("Leia P60 Pro", new BigDecimal("699"), "USD", "2021 Fashion New Handy Smartphone P60 Pro 5G with 16+768GB Large Memory 48+64MP HD camara Dual Sim Cards Bluetooth Wifi 6000mA/h battery Mobile Phone Android 10.0 Ten Core", cellPhone, leia));
        productDataStore.add(new Product("Samsung S21 U+", new BigDecimal("699"), "USD", "2021 Brand New S21+ Ultra 5G 6.1Inch HD Screen 16+512GB Unlocked Global Version Smartphone (Black/Blue/Green)", cellPhone, samsung));
        productDataStore.add(new Product("Samsung Note30 Plus", new BigDecimal("583"), "USD", "New Note30 Plus Smartphone the thinnest 6.1 Inches Large Memory 12GB+512GB Android 10.0 Face Unlock Dual Card Phone Supports T-card Smartphone", cellPhone, samsung));
        productDataStore.add(new Product("Game Station 4 pro", new BigDecimal("185"), "USD", "Challenge yourself in a variety of games! With the Game Station 4 Pro TV-connected game machine, you can play with two friends at the same time, so you can compete against each other in the game of your choice.", console, gameStation));
        productDataStore.add(new Product("Fujiyama TPT 16X Pro", new BigDecimal("1233"), "USD", "Digital Camera 16X Focus Zoom Design Camera1280x720 Supported 32GB Card Portable Digital Camera for Travel Photo Taking", camera, fujiyama));
        productDataStore.add(new Product("Fujiyama ZMP 24X", new BigDecimal("1299"), "USD", "Full HD Digital Camera, 33MP Digital DSLR Camera 0.5X Auto Focus Wide Angle Lens Kit, Rechargeable 24X Optical Zoom Multifunctional Portable Camera, Best Gift For Photography Lovers", camera, fujiyama));
        productDataStore.add(new Product("Samsung UHD Television 65'", new BigDecimal("999"), "USD", "Dive into the picture with a wider range of colours! Crystal Display ensures optimised colour reproduction so you can see every detail.", television, samsung));
        productDataStore.add(new Product("Samsung FULL HD Television 45'", new BigDecimal("799"), "USD", "A narrow and elegant design that gives you the clearest picture. Minimalist in every element and angle, it is frameless, setting new standards. A captivating sight is revealed.", television, samsung));
    }
}
