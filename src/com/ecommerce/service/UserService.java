package com.ecommerce.service;

import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
import java.util.Scanner;

public class UserService {
    private UserRepository userRepository;
    private Scanner scanner;

    public UserService(Scanner scanner) {
        this.userRepository = new UserRepository();
        this.scanner = scanner;
    }

    public User login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful. Welcome, " + user.getUsername() + "!");
            return user;
        } else {
            System.out.println("Invalid username or password.");
            return null;
        }
    }

    public User signup() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (userRepository.findByUsername(username) != null) {
            System.out.println("Username already exists.");
            return null;
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        User user = new User(username, password, email);
        userRepository.addUser(user);
        System.out.println("Signup successful. Welcome, " + user.getUsername() + "!");
        return user;
    }
}
