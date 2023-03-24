package com.codecool.shop.dao;

import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {

    void add(Order order);
    Order find(int id);
    void remove(int id);

    List<Order> getAll();


    int getOrderIdByUserId(int id);

    boolean userHasCheckedOrder(int userId);
}
