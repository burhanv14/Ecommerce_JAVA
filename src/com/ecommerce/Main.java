/*
SOLID:
Single Responsibility Principle
Open/Close Principle
Liskov Substitution Principle
Interface Segregration Principle
Dependency INversion Principle
 */



package com.ecommerce;
import com.ecommerce.model.User;
import com.ecommerce.model.Admin;
import com.ecommerce.model.Product;
import com.ecommerce.model.Order;
import com.ecommerce.service.AdminService;
import com.ecommerce.service.UserService;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.OrderService;
import com.ecommerce.utils.InputUtil;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Services
        AdminService adminService = new AdminService(scanner);
        UserService userService = new UserService(scanner);
        ProductService productService = new ProductService();
        OrderService orderService = new OrderService();

        User currentUser = null;
        Admin currentAdmin = null;

        boolean exit = false;
        while (!exit) {
            // If neither a user nor admin is logged in, show the main menu.
            if (currentUser == null && currentAdmin == null) {
                System.out.println("=== Welcome to the E-Commerce System ===");
                System.out.println("1. User Login");
                System.out.println("2. User Signup");
                System.out.println("3. Admin Login");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = InputUtil.getIntInput(scanner);

                switch (choice) {
                    case 1:
                        currentUser = userService.login();
                        break;
                    case 2:
                        currentUser = userService.signup();
                        break;
                    case 3:
                        currentAdmin = adminService.adminLogin();
                        break;
                    case 4:
                        exit = true;
                        System.out.println("Thank you for visiting. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
            // If a regular user is logged in, show the user dashboard.
            else if (currentUser != null) {
                System.out.println("\n=== User Dashboard ===");
                System.out.println("1. View Products");
                System.out.println("2. Add Product to Cart");
                System.out.println("3. View Cart");
                System.out.println("4. Place Order");
                System.out.println("5. Logout");
                System.out.print("Enter your choice: ");
                int choice = InputUtil.getIntInput(scanner);

                switch (choice) {
                    case 1:
                        productService.listProducts();
                        break;
                    case 2:
                        productService.listProducts();
                        System.out.print("Enter Product ID to add to cart: ");
                        int productId = InputUtil.getIntInput(scanner);
                        Product product = productService.getProductById(productId);
                        if (product != null) {
                            currentUser.getCart().addProduct(product);
                            System.out.println("Product added to cart.");
                        } else {
                            System.out.println("Invalid Product ID.");
                        }
                        break;
                    case 3:
                        currentUser.getCart().viewCart();
                        break;
                    case 4:
                        if (currentUser.getCart().isEmpty()) {
                            System.out.println("Your cart is empty.");
                        } else {
                            Order order = orderService.placeOrder(currentUser);
                            System.out.println("Order placed successfully. Order ID: " + order.getOrderId());
                        }
                        break;
                    case 5:
                        System.out.println("Logging out...");
                        currentUser = null;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
            // If an admin is logged in, show the admin menu.
            else if (currentAdmin != null) {
                System.out.println("\n=== Admin Dashboard ===");
                System.out.println("1. Update Catalog (Add/Remove/Update Products)");
                System.out.println("2. Logout");
                System.out.print("Enter your choice: ");
                int choice = InputUtil.getIntInput(scanner);

                switch (choice) {
                    case 1:
                        // The Admin class provides updateCatalog() which
                        // internally manages the sub-menu for add/remove/update.
                        currentAdmin.updateCatalog(productService, scanner);
                        break;
                    case 2:
                        System.out.println("Logging out Admin...");
                        currentAdmin = null;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }

        scanner.close();
    }
}
