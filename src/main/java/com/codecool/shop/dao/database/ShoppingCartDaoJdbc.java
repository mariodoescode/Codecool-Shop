package com.codecool.shop.dao.database;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Product;

import java.util.List;

public class ShoppingCartDaoJdbc implements ShoppingCartDao {
    @Override
    public void add(Product product) {
        int productId = product.getId();


    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public String getTotalPrice() {
        return null;
    }
}
