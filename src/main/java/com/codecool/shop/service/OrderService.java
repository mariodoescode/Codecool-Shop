package com.codecool.shop.service;


import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.LineItem;


import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class OrderService{

    private OrderDao orderDao;
    private LineItemDao lineItemDao;
    private int currentOrderId = 1;

    public OrderService(OrderDao orderDao, LineItemDao lineItemDao) {
        this.orderDao = orderDao;
        this.lineItemDao = lineItemDao;
    }

    public List<Order> getAllOrders() {
        return orderDao.getAll();
    }

    public void addLineItem(BigDecimal productPrice, String productName, String productDescription, int orderId, String supplier, int productId){
        int defaultQuantity = 1;
        setCurrentOrderId(orderId);
        int itemQuantity = isProductAlreadyInOrder(productName,orderId);
        if (itemQuantity >= 1) {
            lineItemDao.updateQuantity(itemQuantity+1,orderId,productId);
        } else {
            LineItem item = new LineItem(defaultQuantity, productPrice, productName, productDescription, orderId, supplier, productId);
            lineItemDao.add(item);
        }

    }


    public List<LineItem> getLineItemsByOrder(int orderId){
        return lineItemDao.getLineItems(orderId);
    }

    private int isProductAlreadyInOrder(String productName, int orderId) {
        for (LineItem item : getLineItemsByOrder(orderId)) {
            if(item.getProductName().equals(productName)) {
                return item.getQuantity();
            }
        }return 0;
    }

    public void addLineItemOrUpdateQuantity(String productName, BigDecimal productPrice, String productDescription, String supplier, int productId, int orderId) {
//        LineItem itemWithSameProduct = isProductAlreadyInOrder(productName, orderId);
//        if (itemWithSameProduct == null) {
//            addLineItem(productPrice, productName, productDescription, orderId, supplier, productId);
//        } else {
//            itemWithSameProduct.setQuantity(1); // adds one to quantity
//        }

    }

    public BigDecimal getFullPrice(int orderId){
        List<LineItem> items = getLineItemsByOrder(orderId);
        BigDecimal fullPrice = new BigDecimal(0);
        for (LineItem item : items){
            BigDecimal linePrice = item.getProductPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            fullPrice = fullPrice.add(linePrice);
        }

        return fullPrice;
    }

    public int getCurrentOrderId() {
        return currentOrderId;
    }

    public void setCurrentOrderId(int id) {
        currentOrderId = id;
    }


    public Order getOrderById(int id) {
        return orderDao.find(id);
    }

    public int getOrderIdByUserId(int id) throws SQLException {
        return orderDao.getOrderIdByUserId(id);
    }

    public boolean userHasCheckedOrder(int userId) {
        return orderDao.userHasCheckedOrder(userId);
    }

    public void createNewOrder(Order order) {
        orderDao.add(order);
    }
}