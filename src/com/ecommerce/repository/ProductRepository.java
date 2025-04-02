package com.ecommerce.repository;

import com.ecommerce.model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private static List<Product> products = new ArrayList<>();

    static {
        // Sample products
        products.add(new Product(1, "Laptop", "High performance laptop", 1200.00));
        products.add(new Product(2, "Smartphone", "Latest model smartphone", 800.00));
        products.add(new Product(3, "Headphones", "Noise-cancelling headphones", 150.00));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public boolean addProduct(Product product) {
        // Check if product with same ID already exists
        if (getProductById(product.getId()) != null) {
            return false;
        }
        products.add(product);
        return true;
    }

    public boolean removeProduct(int id) {
        Product p = getProductById(id);
        if (p != null) {
            products.remove(p);
            return true;
        }
        return false;
    }

    public boolean updateProduct(int id, String newName, String newDesc, double newPrice) {
        Product p = getProductById(id);
        if (p != null) {
            if (!newName.trim().isEmpty()) {
                p.setName(newName);
            }
            if (!newDesc.trim().isEmpty()) {
                p.setDescription(newDesc);
            }
            if (newPrice >= 0) {
                p.setPrice(newPrice);
            }
            return true;
        }
        return false;
    }
}
