package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Product;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDaoMem implements ShoppingCartDao {
    private final List<Product> data = new ArrayList<>();
    private static ShoppingCartDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ShoppingCartDaoMem() {
    }

    public static ShoppingCartDaoMem getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoMem();
        }
        return instance;
    }


    @Override
    public void add(Product product) {
        if(data.contains(product)){
            product.setQuantity(product.getQuantity() + 1);
        }else{
            data.add(product);
        }
    }

    @Override
    public Product find(int id) {
        return data.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<Product> getAllProducts() {
        return data;
    }

    @Override
    public String getTotalPrice() {
        BigDecimal price = new BigDecimal(0);
        String totalPrice = "";
        for (Product product : data) {
            price = price.add(product.getTotalPrice());
        }
        return totalPrice + price + "USD";
    }

    @Override
    public int countShoppingCarts() {
        return 0;
    }

    @Override
    public void addUser(int lastUserID) throws SQLException {
    }

}