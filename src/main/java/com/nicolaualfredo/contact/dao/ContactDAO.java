/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nicolaualfredo.contact.dao;

import com.nicolaualfredo.contact.model.Contact;
import java.util.List;

/**
 *
 * @author nicolaualfredo
 */
public class ContactDAO implements GenericDAO<Contact> {

    private static final String SAVE = "INSERT INTO contact (name_contact, phone_contact, email_contact, photo_path) VALUES(?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE contact SET name_contact = ?, phone_contact = ?, email_contact = ?, photo_path = ? WHERE id_contact = ?";
    private static final String DELETE = "DELETE FROM contact WHERE id_contact = ?";
    private static final String FINDBYID = "SELECT * FROM contact WHERE id_contact = ?";
    private static final String FINDALL = "SELECT * FROM contact"; 
    @Override
    public void save(Contact t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Contact t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Contact t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Contact findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Contact> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
