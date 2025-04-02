package com.ecommerce.model;

public class User {
    private static int idCounter = 1;
    private int id;
    private String username;
    private String password;
    private String email;
    private ShoppingCart cart;

    public User(String username, String password, String email) {
        this.id = idCounter++;
        this.username = username;
        this.password = password;
        this.email = email;
        this.cart = new ShoppingCart();
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public ShoppingCart getCart() {
        return cart;
    }
}
