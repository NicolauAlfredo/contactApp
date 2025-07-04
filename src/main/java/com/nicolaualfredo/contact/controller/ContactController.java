/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nicolaualfredo.contact.controller;

import com.nicolaualfredo.contact.dao.ContactDAO;
import com.nicolaualfredo.contact.model.Contact;
import java.util.List;

/**
 *
 * @author nicolaualfredo
 */
public class ContactController {

    private final ContactDAO contactDAO;

    public ContactController() {
        this.contactDAO = new ContactDAO();
    }

    public boolean createContact(Contact contact) {
        if (contact == null) {
            System.out.println("[ContactController] -> Cannot create a null contact.");
            return false;
        }
        contactDAO.save(contact);
        return true;
    }

    public boolean updateContact(Contact contact) {
        if (contact == null || contact.getIdContact() == null) {
            System.out.println("[ContactController] -> Cannot update a null or unidentified contact.");
            return false;
        }
        contactDAO.update(contact);
        return true;
    }

    public boolean deleteContact(Contact contact) {
        if (contact == null || contact.getIdContact() == null) {
            System.out.println("[ContactController] -> Cannot delete a null or unidentified contact.");
            return false;
        }
        contactDAO.delete(contact);
        return true;
    }

    public Contact getContactById(Integer id) {
        return contactDAO.findById(id);
    }

    public List<Contact> getAllContacts() {
        return contactDAO.findAll();
    }
}
