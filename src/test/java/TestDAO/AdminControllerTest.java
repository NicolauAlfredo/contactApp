package TestDAO;

import com.nicolaualfredo.contact.controller.AdminController;
import com.nicolaualfredo.contact.model.Admin;
import java.awt.BorderLayout;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Nicolau Alfredo
 */
public class AdminControllerTest {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final AdminController CONTROLLER = new AdminController();

    public static void main(String[] args) {
        int option;

        do {
            showMenu();
            option = SCANNER.nextInt();
            SCANNER.nextLine();

            switch (option) {
                case 1 ->
                    createAdmin();
                case 2 ->
                    updateAdmin();
                case 3 ->
                    deleteAdmin();
                case 4 ->
                    listAllAdmins();
                case 5 ->
                    findAdminById();
                case 0 ->
                    System.out.println("Exiting Admin Menu..");
                default ->
                    System.out.println("Invalid option. Try again.");
            }
        } while (option != 0);
    }

    private static void showMenu() {
        System.out.println("\n===== Admin Management Menu =====");
        System.out.println("1. Create Admin");
        System.out.println("2. Update Admin");
        System.out.println("3. Delete Admin");
        System.out.println("4. List All Admins");
        System.out.println("5. Find Admin by ID");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private static void createAdmin() {
        Admin admin = new Admin();

        System.out.println("Username: ");
        admin.setUsernameAdmin(SCANNER.nextLine());

        System.out.println("Password: ");
        admin.setPassword_has(SCANNER.nextLine());

        System.out.println("Full Name: ");
        admin.setFullName(SCANNER.nextLine());

        System.out.println("Email: ");
        admin.setEmail(SCANNER.nextLine());

        admin.setCreated_at(new Timestamp(System.currentTimeMillis()));

        CONTROLLER.createAdmin(admin);
        System.out.println("Admin created successfully!");

    }

    private static void updateAdmin() {
        System.out.print("Enter Admin ID to update: ");
        int id = SCANNER.nextInt();
        SCANNER.nextLine();

        Admin admin = CONTROLLER.getAdminById(id);
        if (admin == null) {
            System.out.println("❌ Admin not found.");
            return;
        }

        System.out.print("New Username (" + admin.getUsernameAdmin() + "): ");
        admin.setUsernameAdmin(SCANNER.nextLine());

        System.out.print("New Password: ");
        admin.setPassword_has(SCANNER.nextLine());

        System.out.print("New Full Name (" + admin.getFullName() + "): ");
        admin.setFullName(SCANNER.nextLine());

        System.out.print("New Email (" + admin.getEmail() + "): ");
        admin.setEmail(SCANNER.nextLine());

        admin.setCreated_at(new Timestamp(System.currentTimeMillis()));

        CONTROLLER.updateAdmin(admin);
        System.out.println("Admin updated successfully!");
    }

    private static void deleteAdmin() {
        System.out.print("Enter Admin ID to delete: ");
        int id = SCANNER.nextInt();
        SCANNER.nextLine();

        Admin admin = CONTROLLER.getAdminById(id);
        if (admin == null) {
            System.out.println("dmin not found.");
            return;
        }

        CONTROLLER.deleteAdmin(admin);
        System.out.println("Admin deleted successfully!");
    }

    private static void listAllAdmins() {
        List<Admin> admins = CONTROLLER.getAllAdmins();
        System.out.println("\nAll Admins:");
        admins.forEach(a -> System.out.println(a.getIdAdmin() + " | " + a.getUsernameAdmin() + " | " + a.getEmail()));
    }

    private static void findAdminById() {
        System.out.print("Enter Admin ID: ");
        int id = SCANNER.nextInt();
        SCANNER.nextLine();

        Admin admin = CONTROLLER.getAdminById(id);
        if (admin == null) {
            System.out.println("Admin not found.");
        } else {
            System.out.println("ID: " + admin.getIdAdmin());
            System.out.println("Username: " + admin.getUsernameAdmin());
            System.out.println("Email: " + admin.getEmail());
            System.out.println("Full Name: " + admin.getFullName());
            System.out.println("Created At: " + admin.getCreated_at());
        }
    }

}
