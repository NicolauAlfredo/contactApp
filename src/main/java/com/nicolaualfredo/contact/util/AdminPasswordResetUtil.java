/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nicolaualfredo.contact.util;

import com.nicolaualfredo.contact.dao.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Nicolau Alfredo
 */
public class AdminPasswordResetUtil {
    // This code acts as a master key – do NOT use this in production like this

    private static final String MASTER_KEY = "PapaiCode";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Admin Password Reset Tool");

        System.out.print("Enter username to reset: ");
        String username = scanner.nextLine();

        System.out.print("Enter reset master key: ");
        String key = scanner.nextLine();

        if (!MASTER_KEY.equals(key)) {
            System.out.println("Invalid reset key. Access denied.");
            return;
        }

        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        String hashedPassword = PasswordUtil.hash(newPassword);

        DBConnection db = new DBConnection();

        try {
            Connection con = db.open();
            PreparedStatement ps = con.prepareStatement("UPDATE admin SET password_hash = ? WHERE username = ?");
            ps.setString(1, hashedPassword);
            ps.setString(2, username);

            int updated = ps.executeUpdate();
            if (updated > 0) {
                System.out.println("Password updated successfully.");
            } else {
                System.out.println("No admin found with that username.");
            }
        } catch (SQLException e) {
            System.err.println("Error resetting password: " + e.getMessage());
        } finally {
            db.close();
        }
    }
}
