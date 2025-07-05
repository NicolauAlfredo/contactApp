/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nicolaualfredo.contact.controller;

import com.nicolaualfredo.contact.dao.AdminDAO;
import com.nicolaualfredo.contact.model.Admin;
import java.util.List;

/**
 *
 * @author nicolaualfredo
 */
public class AdminController {

    private final AdminDAO adminDAO;

    public AdminController() {
        this.adminDAO = new AdminDAO();
    }

    public boolean createAdmin(Admin admin) {
        if (admin == null) {
            System.out.println("[AdminController] -> Cannot create a null admin.");
            return false;
        }
        adminDAO.save(admin);
        return true;
    }

    public boolean updateAdmin(Admin admin) {
        if (admin == null || admin.getIdAdmin() == null) {
            System.out.println("[AdminController] -> Cannot update a null or unidentified admin.");
            return false;
        }
        adminDAO.update(admin);
        return true;
    }

    public boolean deleteAdmin(Admin admin) {
        if (admin == null || admin.getIdAdmin() == null) {
            System.out.println("[AdminController] -> Cannot delete a null or unidentified admin.");
            return false;
        }
        adminDAO.delete(admin);
        return true;
    }

    public Admin getAdminByUsername(String username) {
        return adminDAO.findByUsername(username);
    }

    public Admin getAdminById(Integer id) {
        return adminDAO.findById(id);
    }

    public List<Admin> getAllAdmins() {
        return adminDAO.findAll();
    }

    public boolean authenticateAdmin(String username, String passwordHash) {
        Admin admin = new Admin();
        admin.setUsernameAdmin(username);
        admin.setPassword_has(passwordHash);
        return adminDAO.login(admin);
    }
}
