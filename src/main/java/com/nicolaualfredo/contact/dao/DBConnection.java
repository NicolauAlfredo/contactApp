/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nicolaualfredo.contact.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Handles the database connection using credentials from a config file.
 * Provides methods to open and close the connection.
 *
 * Config file path: config/config.properties
 *
 * Author: nicolaualfredo
 */
public class DBConnection {

    private Connection con = null;

    /**
     * Opens a connection to the database using MySQL JDBC driver. Loads
     * credentials from config/config.properties.
     *
     * @return Connection object if successful, null otherwise.
     */
    public Connection open() {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Load database credentials from properties file
            Properties props = new Properties();
            FileInputStream file = new FileInputStream("config/config.properties");
            props.load(file);
            file.close();

            // Set database URL (adjust the database name if needed)
            String url = "jdbc:mysql://localhost:3306/contactapp";
            con = DriverManager.getConnection(url, props);

        } catch (ClassNotFoundException ex) {
            System.err.println("Driver not found!");
        } catch (SQLException ex) {
            System.err.println("Failed to connect to the database!");
        } catch (IOException ex) {
            System.err.println("Failed to load database credentials!");
        }
        return con;
    }

    /**
     * Closes the connection to the database, if open.
     */
    public void close() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            System.err.println("Failed to disconnect from the database!");
        }
    }
}
