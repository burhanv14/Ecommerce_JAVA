package com.ecommerce.service;

import com.ecommerce.model.Order;
import com.ecommerce.model.User;
import com.ecommerce.repository.OrderRepository;

public class OrderService {
    private OrderRepository orderRepository;

    public OrderService() {
        this.orderRepository = new OrderRepository();
    }

    public Order placeOrder(User user) {
        Order order = new Order(user, user.getCart().getProducts());
        orderRepository.addOrder(order);
        user.getCart().clearCart();
        return order;
    }
}
