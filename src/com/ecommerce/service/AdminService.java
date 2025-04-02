package com.ecommerce.service;

import com.ecommerce.model.Admin;
import com.ecommerce.repository.AdminRepository;
import com.ecommerce.utils.InputUtil;

import java.util.Scanner;

public class AdminService {
    private AdminRepository adminRepository;
    private Scanner scanner;

    public AdminService(Scanner scanner) {
        this.adminRepository = new AdminRepository();
        this.scanner = scanner;
    }

    public Admin adminLogin() {
        System.out.print("Enter Admin ID: ");
        int adminId = InputUtil.getIntInput(scanner);

        System.out.print("Enter Admin Password: ");
        String adminPassword = scanner.nextLine();

        Admin admin = adminRepository.findAdminById(adminId);
        if (admin != null && admin.getAdminPassword().equals(adminPassword)) {
            System.out.println("Admin login successful. Welcome, Admin #" + adminId + "!");
            return admin;
        } else {
            System.out.println("Invalid admin credentials.");
            return null;
        }
    }
}
