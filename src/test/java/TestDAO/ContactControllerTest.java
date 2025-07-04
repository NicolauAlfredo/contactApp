/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestDAO;

import com.nicolaualfredo.contact.controller.ContactController;
import com.nicolaualfredo.contact.model.Contact;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Nicolau Alfredo
 */
public class ContactControllerTest {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ContactController CONTROLLER = new ContactController();

    public static void main(String[] args) {
        int option;

        do {
            showMenu();
            option = SCANNER.nextInt();
            SCANNER.nextLine();

            switch (option) {
                case 1 ->
                    createContact();
                case 2 ->
                    updateContact();
                case 3 ->
                    deleteContact();
                case 4 ->
                    listAllContacts();
                case 5 ->
                    findContactById();
                case 0 ->
                    System.out.println("Exiting Contact Menu...");
                default ->
                    System.out.println("Invalid option. Try again.");
            }
        } while (option != 0);

    }

    private static void showMenu() {
        System.out.println("\n===== Contact Management Menu =====");
        System.out.println("1. Create Contact");
        System.out.println("2. Update Contact");
        System.out.println("3. Delete Contact");
        System.out.println("4. List All Contacts");
        System.out.println("5. Find Contact by ID");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private static void createContact() {
        Contact contact = new Contact();

        System.out.print("Name: ");
        contact.setNameContact(SCANNER.nextLine());

        System.out.print("Phone: ");
        contact.setPhoneContact(SCANNER.nextLine());

        System.out.print("Email: ");
        contact.setEmailContact(SCANNER.nextLine());

        System.out.print("Photo file name (e.g., maria.jpg): ");
        String imageName = SCANNER.nextLine();

        contact.setPhotoPath("images/" + imageName);

        CONTROLLER.createContact(contact);
        System.out.println("Contact created successfully!");
    }

    private static void updateContact() {
        System.out.print("Enter Contact ID to update: ");
        int id = SCANNER.nextInt();
        SCANNER.nextLine();

        Contact contact = CONTROLLER.getContactById(id);
        if (contact == null) {
            System.out.println("Contact not found.");
            return;
        }

        System.out.print("New Name (" + contact.getNameContact() + "): ");
        String name = SCANNER.nextLine();
        if (!name.isBlank()) {
            contact.setNameContact(name);
        }

        System.out.print("New Phone (" + contact.getPhoneContact() + "): ");
        String phone = SCANNER.nextLine();
        if (!phone.isBlank()) {
            contact.setPhoneContact(phone);
        }

        System.out.print("New Email (" + contact.getEmailContact() + "): ");
        String email = SCANNER.nextLine();
        if (!email.isBlank()) {
            contact.setEmailContact(email);
        }

        System.out.print("New Photo file name [" + contact.getPhotoPath() + "] (leave blank to keep current): ");
        String imageName = SCANNER.nextLine();
        if (!imageName.isBlank()) {
            contact.setPhotoPath("images/" + imageName);
        }

        CONTROLLER.updateContact(contact);
        System.out.println("Contact updated successfully!");
    }

    private static void deleteContact() {
        System.out.print("Enter Contact ID to delete: ");
        int id = SCANNER.nextInt();
        SCANNER.nextLine();

        Contact contact = CONTROLLER.getContactById(id);
        if (contact == null) {
            System.out.println("Contact not found.");
            return;
        }

        CONTROLLER.deleteContact(contact);
        System.out.println("Contact deleted successfully!");
    }

    private static void listAllContacts() {
        List<Contact> contacts = CONTROLLER.getAllContacts();
        System.out.println("\nAll Contacts:");
        contacts.forEach(c -> System.out.println(c.getIdContact() + " | " + c.getNameContact() + " | " + c.getEmailContact()));
    }

    private static void findContactById() {
        System.out.print("Enter Contact ID: ");
        int id = SCANNER.nextInt();
        SCANNER.nextLine();

        Contact contact = CONTROLLER.getContactById(id);
        if (contact == null) {
            System.out.println("Contact not found.");
        } else {
            System.out.println("ID: " + contact.getIdContact());
            System.out.println("Name: " + contact.getNameContact());
            System.out.println("Phone: " + contact.getPhoneContact());
            System.out.println("Email: " + contact.getEmailContact());
            System.out.println("Photo Path: " + contact.getPhotoPath());
        }
    }
}
