/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nicolaualfredo.contact.dao;

import com.nicolaualfredo.contact.model.Contact;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for managing Contact data in the database.
 * Implements the GenericDAO interface for basic CRUD operations.
 *
 * Author: nicolaualfredo
 */
public class ContactDAO implements GenericDAO<Contact> {

    private static final String SAVE = "INSERT INTO contact (name_contact, phone_contact, email_contact, photo_path) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE contact SET name_contact = ?, phone_contact = ?, email_contact = ?, photo_path = ? WHERE id_contact = ?";
    private static final String DELETE = "DELETE FROM contact WHERE id_contact = ?";
    private static final String FINDBYID = "SELECT * FROM contact WHERE id_contact = ?";
    private static final String FINDALL = "SELECT * FROM contact";
    private static final String FINDBYNAME = "SELECT * FROM contact WHERE name_contact = ?";

    /**
     * Saves a new contact to the database.
     *
     * @param contact The contact object to be saved.
     */
    @Override
    public void save(Contact contact) {
        if (contact == null) {
            System.out.println("[ContactDAO] -> Cannot save null contact.");
            return;
        }

        DBConnection db = new DBConnection();

        try {
            PreparedStatement ps = db.open().prepareStatement(SAVE);
            ps.setString(1, contact.getNameContact());
            ps.setString(2, contact.getPhoneContact());
            ps.setString(3, contact.getEmailContact());
            ps.setString(4, contact.getPhotoPath());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("[ContactDAO] -> Error saving contact: " + e.getLocalizedMessage());
        } finally {
            db.close();
        }
    }

    /**
     * Updates an existing contact in the database.
     *
     * @param contact The contact object with updated data.
     */
    @Override
    public void update(Contact contact) {
        if (contact == null) {
            System.out.println("[ContactDAO] -> Cannot update null contact.");
            return;
        }

        DBConnection db = new DBConnection();

        try {
            PreparedStatement ps = db.open().prepareStatement(UPDATE);
            ps.setString(1, contact.getNameContact());
            ps.setString(2, contact.getPhoneContact());
            ps.setString(3, contact.getEmailContact());
            ps.setString(4, contact.getPhotoPath());
            ps.setInt(5, contact.getIdContact());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("[ContactDAO] -> Error updating contact: " + e.getLocalizedMessage());
        } finally {
            db.close();
        }
    }

    /**
     * Deletes a contact from the database.
     *
     * @param contact The contact to be deleted.
     */
    @Override
    public void delete(Contact contact) {
        if (contact == null || contact.getIdContact() == null) {
            System.out.println("[ContactDAO] -> Cannot delete contact without valid ID.");
            return;
        }

        DBConnection db = new DBConnection();

        try {
            PreparedStatement ps = db.open().prepareStatement(DELETE);
            ps.setInt(1, contact.getIdContact());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("[ContactDAO] -> Error deleting contact: " + e.getLocalizedMessage());
        } finally {
            db.close();
        }
    }

    /**
     * Finds a contact by its ID.
     *
     * @param id The contact ID to search.
     * @return The contact found, or null if not found.
     */
    @Override
    public Contact findById(Integer id) {
        DBConnection db = new DBConnection();
        Contact contact = null;

        try {
            PreparedStatement ps = db.open().prepareStatement(FINDBYID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                contact = new Contact();
                contact.setIdContact(rs.getInt("id_contact"));
                contact.setNameContact(rs.getString("name_contact"));
                contact.setPhoneContact(rs.getString("phone_contact"));
                contact.setEmailContact(rs.getString("email_contact"));
                contact.setPhotoPath(rs.getString("photo_path"));
            }
        } catch (SQLException e) {
            System.err.println("[ContactDAO] -> Error finding contact by ID: " + e.getLocalizedMessage());
        } finally {
            db.close();
        }

        return contact;
    }

    /**
     * Retrieves all contacts from the database.
     *
     * @return A list of contacts.
     */
    @Override
    public List<Contact> findAll() {
        DBConnection db = new DBConnection();
        List<Contact> contacts = new ArrayList<>();

        try {
            PreparedStatement ps = db.open().prepareStatement(FINDALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Contact contact = new Contact();
                contact.setIdContact(rs.getInt("id_contact"));
                contact.setNameContact(rs.getString("name_contact"));
                contact.setPhoneContact(rs.getString("phone_contact"));
                contact.setEmailContact(rs.getString("email_contact"));
                contact.setPhotoPath(rs.getString("photo_path"));
                contacts.add(contact);
            }
        } catch (SQLException e) {
            System.err.println("[ContactDAO] -> Error retrieving all contacts: " + e.getLocalizedMessage());
        } finally {
            db.close();
        }

        return contacts;
    }

    public Contact findByName(String name) {
        DBConnection db = new DBConnection();
        Contact contact = null;

        try {
            PreparedStatement ps = db.open().prepareStatement(FINDBYNAME);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                contact = new Contact();
                contact.setIdContact(rs.getInt("id_contact"));
                contact.setNameContact(rs.getString("name_contact"));
                contact.setPhoneContact(rs.getString("phone_contact"));
                contact.setEmailContact(rs.getString("email_contact"));
                contact.setPhotoPath(rs.getString("photo_path"));
            }
        } catch (SQLException e) {
            System.err.println("[ContactDAO] -> Error finding contacts by name: " + e.getLocalizedMessage());
        } finally {
            db.close();
        }

        return contact;
    }

}
