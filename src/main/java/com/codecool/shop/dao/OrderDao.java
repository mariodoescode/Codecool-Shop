package com.codecool.shop.dao;

import com.codecool.shop.model.Order;

import java.util.Collection;
import java.util.List;

public interface OrderDao {

    void add(Order order);

    Order find(int id);

    int getLastOrderID();

    List<Order> getAll();
}
