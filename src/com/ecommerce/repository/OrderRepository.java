package com.ecommerce.repository;

import com.ecommerce.model.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private static List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrdersByUserId(int userId) {
        List<Order> userOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getUser().getId() == userId) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }
}
