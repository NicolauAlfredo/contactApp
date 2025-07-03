/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nicolaualfredo.contact.model;

import java.util.Objects;

/**
 *
 * @author nicolaualfredo
 */
public class Contact {

    private Integer idContact;
    private String nameContact;
    private String phoneContact;
    private String emailContact;
    private String photoPath;

    public Contact() {
    }

    public Contact(Integer idContact, String nameContact, String phoneContact, String emailContact, String photoPath) {
        this.idContact = idContact;
        this.nameContact = nameContact;
        this.phoneContact = phoneContact;
        this.emailContact = emailContact;
        this.photoPath = photoPath;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Integer getIdContact() {
        return idContact;
    }

    public void setIdContact(Integer idContact) {
        this.idContact = idContact;
    }

    public String getNameContact() {
        return nameContact;
    }

    public void setNameContact(String nameContact) {
        this.nameContact = nameContact;
    }

    public String getPhoneContact() {
        return phoneContact;
    }

    public void setPhoneContact(String phoneContact) {
        this.phoneContact = phoneContact;
    }

    public String getEmailContact() {
        return emailContact;
    }

    public void setEmailContact(String emailContact) {
        this.emailContact = emailContact;
    }

    @Override
    public String toString() {
        return "Contact{" + "idContact=" + idContact + ", nameContact=" + nameContact + ", phoneContact=" + phoneContact + ", emailContact=" + emailContact + ", photoPath=" + photoPath + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.idContact);
        hash = 59 * hash + Objects.hashCode(this.nameContact);
        hash = 59 * hash + Objects.hashCode(this.phoneContact);
        hash = 59 * hash + Objects.hashCode(this.emailContact);
        hash = 59 * hash + Objects.hashCode(this.photoPath);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contact other = (Contact) obj;
        if (!Objects.equals(this.nameContact, other.nameContact)) {
            return false;
        }
        if (!Objects.equals(this.phoneContact, other.phoneContact)) {
            return false;
        }
        if (!Objects.equals(this.emailContact, other.emailContact)) {
            return false;
        }
        if (!Objects.equals(this.photoPath, other.photoPath)) {
            return false;
        }
        return Objects.equals(this.idContact, other.idContact);
    }

}
