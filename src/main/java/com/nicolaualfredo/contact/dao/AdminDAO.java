/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nicolaualfredo.contact.dao;

import com.nicolaualfredo.contact.model.Admin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class responsible for performing all CRUD operations
 * for the Admin entity in the database.
 *
 * This class implements the GenericDAO interface using JDBC. It also includes a
 * login method for authentication.
 *
 * Author: nicolaualfredo
 */
public class AdminDAO implements GenericDAO<Admin> {

    // SQL statements used for Admin operations
    private static final String SAVE = "INSERT INTO admin (username, password_hash, full_name, email, created_at) VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE admin SET username = ?, password_hash = ?, full_name = ?, email = ?, created_at = ? WHERE id_admin = ?";
    private static final String DELETE = "DELETE FROM admin WHERE id_admin = ?";
    private static final String FINDBYID = "SELECT * FROM admin WHERE id_admin = ?";
    private static final String FINDALL = "SELECT * FROM admin";
    private static final String LOGIN = "SELECT * FROM admin WHERE username = ? AND password_hash = ?";

    /**
     * Saves a new Admin to the database.
     */
    @Override
    public void save(Admin admin) {
        if (admin == null) {
            System.out.println("[AdminDAO] -> Cannot save null admin.");
            return;
        }

        DBConnection db = new DBConnection();

        try {
            PreparedStatement ps = db.open().prepareStatement(SAVE);
            ps.setString(1, admin.getUsernameAdmin());
            ps.setString(2, admin.getPassword_has());
            ps.setString(3, admin.getFullName());
            ps.setString(4, admin.getEmail());
            ps.setTimestamp(5, admin.getCreated_at());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("[AdminDAO] -> Error saving admin: " + e.getLocalizedMessage());
        } finally {
            db.close();
        }
    }

    /**
     * Updates an existing Admin in the database.
     */
    @Override
    public void update(Admin admin) {
        if (admin == null) {
            System.out.println("[AdminDAO] -> Cannot update null admin.");
            return;
        }

        DBConnection db = new DBConnection();

        try {
            PreparedStatement ps = db.open().prepareStatement(UPDATE);
            ps.setString(1, admin.getUsernameAdmin());
            ps.setString(2, admin.getPassword_has());
            ps.setString(3, admin.getFullName());
            ps.setString(4, admin.getEmail());
            ps.setTimestamp(5, admin.getCreated_at());
            ps.setInt(6, admin.getIdAdmin());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("[AdminDAO] -> Error updating admin: " + e.getLocalizedMessage());
        } finally {
            db.close();
        }
    }

    /**
     * Deletes an Admin from the database using their ID.
     */
    @Override
    public void delete(Admin admin) {
        if (admin == null || admin.getIdAdmin() == null) {
            System.out.println("[AdminDAO] -> Cannot delete admin without valid ID.");
            return;
        }

        DBConnection db = new DBConnection();

        try {
            PreparedStatement ps = db.open().prepareStatement(DELETE);
            ps.setInt(1, admin.getIdAdmin());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("[AdminDAO] -> Error deleting admin: " + e.getLocalizedMessage());
        } finally {
            db.close();
        }
    }

    /**
     * Finds an Admin by ID.
     *
     * @param id The ID of the admin to find.
     * @return The Admin object if found, or null.
     */
    @Override
    public Admin findById(Integer id) {
        DBConnection db = new DBConnection();
        Admin admin = null;

        try {
            PreparedStatement ps = db.open().prepareStatement(FINDBYID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setIdAdmin(rs.getInt("id_admin"));
                admin.setUsernameAdmin(rs.getString("username"));
                admin.setPassword_has(rs.getString("password_hash"));
                admin.setFullName(rs.getString("full_name"));
                admin.setEmail(rs.getString("email"));
                admin.setCreated_at(rs.getTimestamp("created_at"));
            }
        } catch (SQLException e) {
            System.err.println("[AdminDAO] -> Error finding admin by ID: " + e.getLocalizedMessage());
        } finally {
            db.close();
        }

        return admin;
    }

    /**
     * Retrieves all Admins from the database.
     *
     * @return A list of Admin objects.
     */
    @Override
    public List<Admin> findAll() {
        DBConnection db = new DBConnection();
        List<Admin> admins = new ArrayList<>();

        try {
            PreparedStatement ps = db.open().prepareStatement(FINDALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Admin admin = new Admin();
                admin.setIdAdmin(rs.getInt("id_admin"));
                admin.setUsernameAdmin(rs.getString("username"));
                admin.setPassword_has(rs.getString("password_hash"));
                admin.setFullName(rs.getString("full_name"));
                admin.setEmail(rs.getString("email"));
                admin.setCreated_at(rs.getTimestamp("created_at"));
                admins.add(admin);
            }
        } catch (SQLException e) {
            System.err.println("[AdminDAO] -> Error retrieving all admins: " + e.getLocalizedMessage());
        } finally {
            db.close();
        }

        return admins;
    }

    /**
     * Authenticates an admin using username and password hash.
     *
     * @param admin Admin object containing login credentials.
     * @return true if the credentials match a database record, false otherwise.
     */
    public boolean login(Admin admin) {
        if (admin.getUsernameAdmin() == null || admin.getPassword_has() == null) {
            System.out.println("[AdminDAO] -> Username or password is null.");
            return false;
        }

        DBConnection db = new DBConnection();
        boolean loginSuccessful = false;

        try {
            PreparedStatement ps = db.open().prepareStatement(LOGIN);
            ps.setString(1, admin.getUsernameAdmin());
            ps.setString(2, admin.getPassword_has());

            ResultSet rs = ps.executeQuery();
            loginSuccessful = rs.next();
        } catch (SQLException e) {
            System.err.println("[AdminDAO] -> Error during login: " + e.getLocalizedMessage());
        } finally {
            db.close();
        }

        return loginSuccessful;
    }
}
