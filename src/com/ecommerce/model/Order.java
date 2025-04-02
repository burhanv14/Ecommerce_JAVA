package com.ecommerce.model;

import java.util.Date;
import java.util.List;

public class Order {
    private static int orderCounter = 1;
    private int orderId;
    private User user;
    private List<Product> products;
    private Date orderDate;
    private double totalAmount;

    public Order(User user, List<Product> products) {
        this.orderId = orderCounter++;
        this.user = user;
        this.products = products;
        this.orderDate = new Date();
        this.totalAmount = calculateTotal();
    }

    private double calculateTotal() {
        double total = 0;
        for (Product p : products) {
            total += p.getPrice();
        }
        return total;
    }

    public int getOrderId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
