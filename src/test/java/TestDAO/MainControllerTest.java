/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestDAO;

import com.nicolaualfredo.contact.dao.AdminDAO;
import com.nicolaualfredo.contact.model.Admin;
import com.nicolaualfredo.contact.util.PasswordUtil;
import java.util.Scanner;

/**
 *
 * @author Nicolau Alfredo
 */
public class MainControllerTest {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final AdminDAO ADMINDAO = new AdminDAO();

    public static void main(String[] args) {
        System.out.println("===== Welcome to ContactApp CLI =====");

        if (doLogin()) {
            showMainMenu();
        } else {
            System.out.println("Login failed. Exiting...");
        }
    }

    private static boolean doLogin() {
        System.out.println("Admin Login");
        System.out.print("Username: ");
        String username = SCANNER.nextLine();

        System.out.print("Password: ");
        String password = SCANNER.nextLine();
        String hashedPassword = PasswordUtil.hash(password);;

        Admin admin = new Admin();
        admin.setUsernameAdmin(username);
        admin.setPassword_has(hashedPassword);

        return ADMINDAO.login(admin);
    }

    private static void showMainMenu() {
        int option;

        do {
            System.out.println("\n===== Main Menu =====");
            System.out.println("1. Manage Contacts");
            System.out.println("2. Manage Admins");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            option = SCANNER.nextInt();
            SCANNER.nextLine();

            switch (option) {
                case 1 ->
                    ContactControllerTest.main(null);
                case 2 ->
                    AdminControllerTest.main(null);
                case 0 ->
                    System.out.println("Goodbye!");
                default ->
                    System.out.println("Invalid option. Try again.");
            }
        } while (option != 0);
    }
}
