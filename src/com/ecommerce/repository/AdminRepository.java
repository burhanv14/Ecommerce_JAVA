package com.ecommerce.repository;

import com.ecommerce.model.Admin;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository {
    private static List<Admin> admins = new ArrayList<>();

    // Pre-load an example Admin (adminId=1, adminPassword="admin123")
    static {
        admins.add(new Admin(1, "admin123"));
    }

    public Admin findAdminById(int adminId) {
        for (Admin admin : admins) {
            if (admin.getAdminId() == adminId) {
                return admin;
            }
        }
        return null;
    }

    public void addAdmin(Admin admin) {
        admins.add(admin);
    }
}
