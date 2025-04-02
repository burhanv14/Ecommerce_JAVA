package com.ecommerce.model;

import com.ecommerce.service.ProductService;
import com.ecommerce.utils.InputUtil;

import java.util.Scanner;

public class Admin {
    private int adminId;
    private String adminPassword;

    // We could auto-generate adminId or manage a static counter, but
    // for simplicity let's accept it as a constructor parameter
    public Admin(int adminId, String adminPassword) {
        this.adminId = adminId;
        this.adminPassword = adminPassword;
    }

    public int getAdminId() {
        return adminId;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    /**
     * updateCatalog() method allows the Admin to add, remove, or update products
     * in the product catalog. It presents a small sub-menu to handle these operations.
     */
    public void updateCatalog(ProductService productService, Scanner scanner) {
        boolean backToMenu = false;
        while (!backToMenu) {
            System.out.println("\n=== Update Catalog ===");
            System.out.println("1. Add New Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Update Product");
            System.out.println("4. List All Products");
            System.out.println("5. Return to Admin Dashboard");
            System.out.print("Enter your choice: ");

            int choice = InputUtil.getIntInput(scanner);

            switch (choice) {
                case 1:
                    addNewProduct(productService, scanner);
                    break;
                case 2:
                    removeProduct(productService, scanner);
                    break;
                case 3:
                    updateProduct(productService, scanner);
                    break;
                case 4:
                    productService.listProducts();
                    break;
                case 5:
                    backToMenu = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addNewProduct(ProductService productService, Scanner scanner) {
        System.out.print("Enter new product ID: ");
        int newProductId = InputUtil.getIntInput(scanner);

        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter product description: ");
        String productDesc = scanner.nextLine();

        System.out.print("Enter product price: ");
        double productPrice = 0.0;
        try {
            productPrice = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid price. Operation cancelled.");
            return;
        }

        boolean success = productService.addProduct(newProductId, productName, productDesc, productPrice);
        if (success) {
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Product with the given ID already exists or invalid input.");
        }
    }

    private void removeProduct(ProductService productService, Scanner scanner) {
        System.out.print("Enter Product ID to remove: ");
        int productId = InputUtil.getIntInput(scanner);

        boolean success = productService.removeProduct(productId);
        if (success) {
            System.out.println("Product removed successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private void updateProduct(ProductService productService, Scanner scanner) {
        System.out.print("Enter Product ID to update: ");
        int productId = InputUtil.getIntInput(scanner);

        System.out.print("Enter new product name (leave blank to skip): ");
        String newName = scanner.nextLine();

        System.out.print("Enter new product description (leave blank to skip): ");
        String newDesc = scanner.nextLine();

        System.out.print("Enter new product price (enter -1 to skip): ");
        double newPrice = -1.0;
        try {
            newPrice = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid price input. Skipping price update.");
        }

        boolean success = productService.updateProduct(productId, newName, newDesc, newPrice);
        if (success) {
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product not found or invalid update.");
        }
    }
}
