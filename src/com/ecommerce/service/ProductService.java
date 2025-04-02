package com.ecommerce.service;

import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;

import java.util.List;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService() {
        this.productRepository = new ProductRepository();
    }

    public void listProducts() {
        List<Product> products = productRepository.getAllProducts();
        System.out.println("=== Product Catalog ===");
        for (Product p : products) {
            System.out.println(p);
        }
    }

    public Product getProductById(int id) {
        return productRepository.getProductById(id);
    }

    public boolean addProduct(int id, String name, String desc, double price) {
        Product product = new Product(id, name, desc, price);
        return productRepository.addProduct(product);
    }

    public boolean removeProduct(int id) {
        return productRepository.removeProduct(id);
    }

    public boolean updateProduct(int id, String newName, String newDesc, double newPrice) {
        return productRepository.updateProduct(id, newName, newDesc, newPrice);
    }
}
