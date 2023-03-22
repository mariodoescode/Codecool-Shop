package com.codecool.shop.service;

import com.codecool.shop.dao.ShoppingCartDao;

import java.sql.SQLException;


public class ShoppingCartService {
    private final ShoppingCartDao shoppingCartDao;

    public ShoppingCartService(ShoppingCartDao shoppingCartDao) {
        this.shoppingCartDao = shoppingCartDao;
    }


    public void addUser(int lastUserID) throws SQLException {
        shoppingCartDao.addUser(lastUserID);
    }

    public int getShoppingCartID() throws SQLException {
        return shoppingCartDao.countShoppingCarts();
    }
}
