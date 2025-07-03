/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nicolaualfredo.contact.model;

import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author nicolaualfredo
 */
public class Admin {

    private Integer idAdmin;
    private String usernameAdmin;
    private String password_has;
    private String fullName;
    private String email;
    private Timestamp created_at;

    public Admin() {
    }

    public Admin(Integer idAdmin, String usernameAdmin, String password_has, String fullName, String email, Timestamp created_at) {
        this.idAdmin = idAdmin;
        this.usernameAdmin = usernameAdmin;
        this.password_has = password_has;
        this.fullName = fullName;
        this.email = email;
        this.created_at = created_at;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getUsernameAdmin() {
        return usernameAdmin;
    }

    public void setUsernameAdmin(String usernameAdmin) {
        this.usernameAdmin = usernameAdmin;
    }

    public String getPassword_has() {
        return password_has;
    }

    public void setPassword_has(String password_has) {
        this.password_has = password_has;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Admin{" + "idAdmin=" + idAdmin + ", usernameAdmin=" + usernameAdmin + ", password_has=" + password_has + ", fullName=" + fullName + ", email=" + email + ", created_at=" + created_at + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.idAdmin);
        hash = 43 * hash + Objects.hashCode(this.usernameAdmin);
        hash = 43 * hash + Objects.hashCode(this.password_has);
        hash = 43 * hash + Objects.hashCode(this.fullName);
        hash = 43 * hash + Objects.hashCode(this.email);
        hash = 43 * hash + Objects.hashCode(this.created_at);
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
        final Admin other = (Admin) obj;
        if (!Objects.equals(this.usernameAdmin, other.usernameAdmin)) {
            return false;
        }
        if (!Objects.equals(this.password_has, other.password_has)) {
            return false;
        }
        if (!Objects.equals(this.fullName, other.fullName)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.idAdmin, other.idAdmin)) {
            return false;
        }
        return Objects.equals(this.created_at, other.created_at);
    }

}
