package com.codecool.shop.dao.database;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;

import javax.sql.DataSource;
import java.util.List;


public class OrderDaoJdbc implements OrderDao {
    DataSource dataSource;
    public OrderDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Order order) {

    }

    @Override
    public Order find(int id) {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public int getLastOrderID() {
        return 0;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }
}
