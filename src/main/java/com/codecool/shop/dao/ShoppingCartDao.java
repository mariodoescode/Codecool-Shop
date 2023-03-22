package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ShoppingCartDao {
    void add(Product product);
    Product find(int id);
    void remove(int id);
    List<Product> getAllProducts();
    String getTotalPrice();

    int countShoppingCarts() throws SQLException;

    void addUser(int lastUserID) throws SQLException;
}