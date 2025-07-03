/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nicolaualfredo.contact.dao;

import com.nicolaualfredo.contact.model.Admin;
import java.util.List;

/**
 *
 * @author nicolaualfredo
 */
public class AdminDAO implements GenericDAO<Admin> {

    private static final String SAVE = "INSERT INTO admin (name, password_hash, full_name, email, created_at) VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE admin SET name = ?, password_hash = ?, full_name = ?, email = ?, created_at = ? WHERE id_amministratore = ?";
    private static final String DELETE = "DELETE FROM admin WHERE id_admin = ?";
    private static final String FINDBYID = "SELECT * FROM admin WHERE id_admin = ?";
    private static final String FINDALL = "SELECT * FROM admin";
    private static final String ACCEDI = "SELECT * FROM admin WHERE name = ? AND password_hash = ?";

    @Override
    public void save(Admin t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Admin t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Admin t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Admin findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Admin> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
