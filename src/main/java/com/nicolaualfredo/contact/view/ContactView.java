/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.nicolaualfredo.contact.view;

import com.nicolaualfredo.contact.controller.ContactController;
import com.nicolaualfredo.contact.model.Contact;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nicolaualfredo
 */
public class ContactView extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ContactView.class.getName());

    /**
     * Creates new form ContactApp
     */
    public ContactView() {
        initComponents();
        loadContacts();
    }

    private String selectedPhotoPath = null;

    private void loadContacts() {
        ContactController controller = new ContactController();
        DefaultTableModel model = (DefaultTableModel) tableContacts.getModel();
        model.setRowCount(0);

        List<Contact> contacts = controller.getAllContacts();

        for (Contact contact : contacts) {
            model.addRow(new Object[]{
                contact.getIdContact(),
                contact.getNameContact(),
                contact.getPhoneContact(),
                contact.getEmailContact()
            });
        }

        checkIdField();
    }

    private void loadContactByName() {
        ContactController controller = new ContactController();

        DefaultTableModel model = (DefaultTableModel) tableContacts.getModel();
        model.setRowCount(0);

        lblMessageSearch.setText("");

        String name = txtSearch.getText().trim();

        if (name.isEmpty()) {
            showTemporaryMessage(lblMessageSearch, "Please enter a name to search.", 5000);
            loadContacts();
            return;
        }

        Contact contact = controller.getContactByName(name);

        if (contact != null) {
            model.addRow(new Object[]{
                contact.getIdContact(),
                contact.getNameContact(),
                contact.getPhoneContact(),
                contact.getEmailContact()
            });
        } else {
            showTemporaryMessage(lblMessageSearch, "No Contact found.", 5000);
            loadContacts();
        }
    }

    private void saveContact() {
        if (!validateFields()) {
            return;
        }

        Contact contact = new Contact();
        ContactController controller = new ContactController();

        contact.setNameContact(txtName.getText());
        contact.setPhoneContact(txtPhone.getText());
        contact.setEmailContact(txtEmail.getText());
        contact.setPhotoPath(selectedPhotoPath);

        if (!txtId.getText().isEmpty()) {
            showTemporaryMessage(lblMessage, "Contact created successfully.", 5000);
        }

        controller.createContact(contact);
        showTemporaryMessage(lblMessage, "Contact created successfully.", 5000);
        clearFields();
        loadContacts();
    }

    private void getDataFromTable() {
        int row = tableContacts.getSelectedRow();

        if (row != -1) {
            String id = tableContacts.getValueAt(row, 0).toString();
            String name = tableContacts.getValueAt(row, 1).toString();
            String phone = tableContacts.getValueAt(row, 2).toString();
            String email = tableContacts.getValueAt(row, 3).toString();

            txtId.setText(id);
            txtName.setText(name);
            txtPhone.setText(phone);
            txtEmail.setText(email);
        }

        checkIdField();
    }

    private void updateContact() {
        if (txtId.getText().isEmpty()) {
            showTemporaryMessage(lblMessage, "Select an admin to update.", 5000);
            return;
        }

        Contact contact = new Contact();
        ContactController controller = new ContactController();

        if (!validateFields()) {
            return;
        }

        contact.setIdContact(Integer.parseInt(txtId.getText()));
        contact.setNameContact(txtName.getText());
        contact.setPhoneContact(txtPhone.getText());
        contact.setEmailContact(txtEmail.getText());
        contact.setPhotoPath(selectedPhotoPath);

        controller.updateContact(contact);
        showTemporaryMessage(lblMessage, "Contact updated successfully.", 5000);
        clearFields();
        loadContacts();
    }

    private void deleteContact() {
        if (txtId.getText().isEmpty()) {
            showTemporaryMessage(lblMessage, "Select a contact to delete.", 5000);
            return;
        }

        Contact contact = new Contact();
        ContactController controller = new ContactController();

        int id = Integer.parseInt(txtId.getText());

        contact = controller.getContactById(id);
        if (contact == null) {
            showTemporaryMessage(lblMessage, "Contact not found.", 5000);
            return;
        }

        controller.deleteContact(contact);
        clearFields();
        loadContacts();
        showTemporaryMessage(lblMessage, "Contact deleted successfully.", 5000);

    }

    private void selectPhoto() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a photo");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            File destDir = new File("images");
            if (!destDir.exists()) {
                destDir.mkdirs();
            }

            String fileName = System.currentTimeMillis() + "_" + selectedFile.getName();
            File destFile = new File(destDir, fileName);

            try {
                Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                selectedPhotoPath = "images/" + fileName;
                showTemporaryMessage(lblMessage, "Photo selected successfully!", 5000);
            } catch (IOException ex) {
                showTemporaryMessage(lblMessage, "Error copying photo!", 5000);
            }
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
    }

    public void showTemporaryMessage(JLabel label, String message, int durationMillis) {
        label.setText(message);

        Timer timer = new Timer(durationMillis, e -> label.setText(""));
        timer.setRepeats(false);
        timer.start();
    }

    private void checkIdField() {
        if (!txtId.getText().trim().isEmpty()) {
            btnSave.setEnabled(false);
        } else {
            btnSave.setEnabled(true);
        }
    }

    private void showContactDetails() {
        int selectedRow = tableContacts.getSelectedRow();

        if (selectedRow != -1) {
            int contactId = (int) tableContacts.getValueAt(selectedRow, 0);

            ContactController controller = new ContactController();
            Contact contact = controller.getContactById(contactId);

            if (contact != null) {
                ContactDetailsView view = new ContactDetailsView(this, true);
                view.setContact(contact);
                view.setVisible(true);
            } else {
                showTemporaryMessage(lblMessage, "Contact not found!", 5000);
            }
        } else {
            showTemporaryMessage(lblMessage, "Select a contact from the table.", 5000);
        }
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validateFields() {
        if (txtName.getText().trim().isEmpty()) {
            showTemporaryMessage(lblMessage, "Full Name is required.", 5000);
            return false;
        }

        if (txtPhone.getText().trim().isEmpty()) {
            showTemporaryMessage(lblMessage, "Phone is required.", 5000);
            return false;
        }

        if (!isValidEmail(txtEmail.getText().trim())) {
            showTemporaryMessage(lblMessage, "Invalid email format.", 5000);
            return false;
        }

        checkIdField();
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelHeader = new javax.swing.JPanel();
        subPanelMenu = new javax.swing.JPanel();
        lblAdmin = new javax.swing.JLabel();
        Separator = new javax.swing.JSeparator();
        lblContact = new javax.swing.JLabel();
        lblAboutUS = new javax.swing.JLabel();
        lblLogout = new javax.swing.JLabel();
        PanelForm = new javax.swing.JPanel();
        subPanelHeader = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblAdministraction = new javax.swing.JLabel();
        subPanelForm = new javax.swing.JPanel();
        PanelId = new javax.swing.JPanel();
        lblEmail1 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        PanelUsername = new javax.swing.JPanel();
        lblUsername = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        PanelPassword = new javax.swing.JPanel();
        lblPassword = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        PanelFullName = new javax.swing.JPanel();
        lblFullName = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        PanelEmail = new javax.swing.JPanel();
        lblEmail = new javax.swing.JLabel();
        btnSelectPhoto = new javax.swing.JButton();
        subPanelButtons = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        subPanelMessage = new javax.swing.JPanel();
        lblMessage = new javax.swing.JLabel();
        PanelTable = new javax.swing.JPanel();
        sPTableAdmins = new javax.swing.JScrollPane();
        tableContacts = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        lblSearch = new javax.swing.JLabel();
        lblReload = new javax.swing.JLabel();
        lblView = new javax.swing.JLabel();
        lblMessageSearch = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CONTACT");
        setResizable(false);

        PanelHeader.setBackground(new java.awt.Color(255, 255, 255));
        PanelHeader.setPreferredSize(new java.awt.Dimension(800, 50));

        subPanelMenu.setBackground(new java.awt.Color(255, 255, 255));
        subPanelMenu.setPreferredSize(new java.awt.Dimension(200, 50));

        lblAdmin.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        lblAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/admin_menu.png"))); // NOI18N
        lblAdmin.setText("ADMIN");
        lblAdmin.setToolTipText("Go to Admin page");
        lblAdmin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAdminMouseClicked(evt);
            }
        });

        Separator.setBackground(new java.awt.Color(53, 123, 244));
        Separator.setForeground(new java.awt.Color(53, 123, 244));
        Separator.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblContact.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        lblContact.setForeground(new java.awt.Color(53, 123, 244));
        lblContact.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContact.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/contact_menu.png"))); // NOI18N
        lblContact.setText("CONTACT");
        lblContact.setToolTipText("Go to Contact page");
        lblContact.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblContact.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblContactMouseClicked(evt);
            }
        });

        lblAboutUS.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        lblAboutUS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAboutUS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/about.png"))); // NOI18N
        lblAboutUS.setText("ABOUT US");
        lblAboutUS.setToolTipText("Find out more about us");
        lblAboutUS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAboutUS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAboutUSMouseClicked(evt);
            }
        });

        lblLogout.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        lblLogout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logout.png"))); // NOI18N
        lblLogout.setToolTipText("Logout");
        lblLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLogoutMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout subPanelMenuLayout = new javax.swing.GroupLayout(subPanelMenu);
        subPanelMenu.setLayout(subPanelMenuLayout);
        subPanelMenuLayout.setHorizontalGroup(
            subPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subPanelMenuLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Separator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblContact, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblAboutUS, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        subPanelMenuLayout.setVerticalGroup(
            subPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subPanelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(subPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogout, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(lblAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Separator)
                    .addComponent(lblAboutUS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblContact, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout PanelHeaderLayout = new javax.swing.GroupLayout(PanelHeader);
        PanelHeader.setLayout(PanelHeaderLayout);
        PanelHeaderLayout.setHorizontalGroup(
            PanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(subPanelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        PanelHeaderLayout.setVerticalGroup(
            PanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHeaderLayout.createSequentialGroup()
                .addComponent(subPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(PanelHeader, java.awt.BorderLayout.NORTH);

        PanelForm.setBackground(new java.awt.Color(53, 123, 244));
        PanelForm.setPreferredSize(new java.awt.Dimension(350, 450));

        subPanelHeader.setBackground(new java.awt.Color(53, 123, 244));
        subPanelHeader.setPreferredSize(new java.awt.Dimension(300, 65));

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/contact_.png"))); // NOI18N
        subPanelHeader.add(lblLogo);

        lblAdministraction.setBackground(new java.awt.Color(255, 255, 255));
        lblAdministraction.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAdministraction.setForeground(new java.awt.Color(255, 255, 255));
        lblAdministraction.setText("CONTACTS");
        subPanelHeader.add(lblAdministraction);

        PanelForm.add(subPanelHeader);

        subPanelForm.setBackground(new java.awt.Color(53, 123, 244));
        subPanelForm.setPreferredSize(new java.awt.Dimension(300, 275));

        PanelId.setBackground(new java.awt.Color(53, 123, 244));
        PanelId.setPreferredSize(new java.awt.Dimension(295, 48));
        PanelId.setLayout(new java.awt.BorderLayout());

        lblEmail1.setBackground(new java.awt.Color(53, 123, 244));
        lblEmail1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblEmail1.setForeground(new java.awt.Color(255, 255, 255));
        lblEmail1.setText("ID");
        PanelId.add(lblEmail1, java.awt.BorderLayout.PAGE_START);

        txtId.setEditable(false);
        txtId.setToolTipText("This field is automatically populated");
        txtId.setEnabled(false);
        PanelId.add(txtId, java.awt.BorderLayout.CENTER);

        subPanelForm.add(PanelId);

        PanelUsername.setBackground(new java.awt.Color(53, 123, 244));
        PanelUsername.setPreferredSize(new java.awt.Dimension(295, 48));
        PanelUsername.setLayout(new java.awt.BorderLayout());

        lblUsername.setBackground(new java.awt.Color(53, 123, 244));
        lblUsername.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername.setText("Name");
        PanelUsername.add(lblUsername, java.awt.BorderLayout.PAGE_START);

        txtName.setToolTipText("Full Name is required.");
        PanelUsername.add(txtName, java.awt.BorderLayout.CENTER);

        subPanelForm.add(PanelUsername);

        PanelPassword.setBackground(new java.awt.Color(53, 123, 244));
        PanelPassword.setPreferredSize(new java.awt.Dimension(295, 48));
        PanelPassword.setLayout(new java.awt.BorderLayout());

        lblPassword.setBackground(new java.awt.Color(53, 123, 244));
        lblPassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(255, 255, 255));
        lblPassword.setText("Phone");
        PanelPassword.add(lblPassword, java.awt.BorderLayout.PAGE_START);

        txtPhone.setToolTipText("Phone is required.");
        PanelPassword.add(txtPhone, java.awt.BorderLayout.CENTER);

        subPanelForm.add(PanelPassword);

        PanelFullName.setBackground(new java.awt.Color(53, 123, 244));
        PanelFullName.setPreferredSize(new java.awt.Dimension(295, 48));
        PanelFullName.setLayout(new java.awt.BorderLayout());

        lblFullName.setBackground(new java.awt.Color(53, 123, 244));
        lblFullName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFullName.setForeground(new java.awt.Color(255, 255, 255));
        lblFullName.setText("Email");
        PanelFullName.add(lblFullName, java.awt.BorderLayout.PAGE_START);

        txtEmail.setToolTipText("Invalid email format.");
        PanelFullName.add(txtEmail, java.awt.BorderLayout.CENTER);

        subPanelForm.add(PanelFullName);

        PanelEmail.setBackground(new java.awt.Color(53, 123, 244));
        PanelEmail.setPreferredSize(new java.awt.Dimension(295, 48));
        PanelEmail.setLayout(new java.awt.BorderLayout());

        lblEmail.setBackground(new java.awt.Color(53, 123, 244));
        lblEmail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(255, 255, 255));
        lblEmail.setText("Photo");
        PanelEmail.add(lblEmail, java.awt.BorderLayout.PAGE_START);

        btnSelectPhoto.setText("Select Photo");
        btnSelectPhoto.setToolTipText("");
        btnSelectPhoto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSelectPhoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSelectPhotoMouseClicked(evt);
            }
        });
        PanelEmail.add(btnSelectPhoto, java.awt.BorderLayout.PAGE_END);

        subPanelForm.add(PanelEmail);

        PanelForm.add(subPanelForm);

        subPanelButtons.setBackground(new java.awt.Color(53, 123, 244));
        subPanelButtons.setPreferredSize(new java.awt.Dimension(350, 50));

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.setToolTipText("Save button");
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveMouseClicked(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setToolTipText("Delete button");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/update.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.setToolTipText("Update button");
        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdateMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout subPanelButtonsLayout = new javax.swing.GroupLayout(subPanelButtons);
        subPanelButtons.setLayout(subPanelButtonsLayout);
        subPanelButtonsLayout.setHorizontalGroup(
            subPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, subPanelButtonsLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        subPanelButtonsLayout.setVerticalGroup(
            subPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subPanelButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(subPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelForm.add(subPanelButtons);

        subPanelMessage.setBackground(new java.awt.Color(53, 123, 244));
        subPanelMessage.setPreferredSize(new java.awt.Dimension(350, 30));

        lblMessage.setFont(new java.awt.Font("Sitka Text", 0, 13)); // NOI18N
        lblMessage.setForeground(new java.awt.Color(255, 255, 255));
        lblMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout subPanelMessageLayout = new javax.swing.GroupLayout(subPanelMessage);
        subPanelMessage.setLayout(subPanelMessageLayout);
        subPanelMessageLayout.setHorizontalGroup(
            subPanelMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subPanelMessageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );
        subPanelMessageLayout.setVerticalGroup(
            subPanelMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        PanelForm.add(subPanelMessage);

        getContentPane().add(PanelForm, java.awt.BorderLayout.WEST);

        PanelTable.setBackground(new java.awt.Color(255, 255, 255));
        PanelTable.setPreferredSize(new java.awt.Dimension(450, 450));

        tableContacts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "NAME", "PHONE", "EMAIL"
            }
        ));
        tableContacts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableContactsMouseClicked(evt);
            }
        });
        sPTableAdmins.setViewportView(tableContacts);

        txtSearch.setToolTipText("Enter a name to search");

        lblSearch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        lblSearch.setToolTipText("Search button");
        lblSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSearchMouseClicked(evt);
            }
        });

        lblReload.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/reload.png"))); // NOI18N
        lblReload.setToolTipText("Cancel all operations");
        lblReload.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblReload.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblReloadMouseClicked(evt);
            }
        });

        lblView.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/view.png"))); // NOI18N
        lblView.setToolTipText("View contact details");
        lblView.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblViewMouseClicked(evt);
            }
        });

        lblMessageSearch.setFont(new java.awt.Font("Sitka Text", 0, 12)); // NOI18N
        lblMessageSearch.setForeground(new java.awt.Color(53, 123, 244));
        lblMessageSearch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout PanelTableLayout = new javax.swing.GroupLayout(PanelTable);
        PanelTable.setLayout(PanelTableLayout);
        PanelTableLayout.setHorizontalGroup(
            PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelTableLayout.createSequentialGroup()
                        .addComponent(lblReload, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblView, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMessageSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sPTableAdmins, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelTableLayout.setVerticalGroup(
            PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelTableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblReload, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelTableLayout.createSequentialGroup()
                        .addGroup(PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblMessageSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(4, 4, 4))
                    .addComponent(lblView, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sPTableAdmins, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(PanelTable, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAdminMouseClicked
        // TODO add your handling code here:
        new AdminView().setVisible(true);
        ContactView.this.dispose();
    }//GEN-LAST:event_lblAdminMouseClicked

    private void lblContactMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblContactMouseClicked
        // TODO add your handling code here:
        showTemporaryMessage(lblMessage, "You are on the Contact page.", 5000);
    }//GEN-LAST:event_lblContactMouseClicked

    private void lblAboutUSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAboutUSMouseClicked
        // TODO add your handling code here:
        showTemporaryMessage(lblMessage, "Come soon!!", 5000);
    }//GEN-LAST:event_lblAboutUSMouseClicked

    private void lblReloadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblReloadMouseClicked
        // TODO add your handling code here:
        clearFields();
        loadContacts();
        btnSave.setEnabled(true);
    }//GEN-LAST:event_lblReloadMouseClicked

    private void lblSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSearchMouseClicked
        // TODO add your handling code here:
        loadContactByName();
    }//GEN-LAST:event_lblSearchMouseClicked

    private void tableContactsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableContactsMouseClicked
        // TODO add your handling code here:
        getDataFromTable();
    }//GEN-LAST:event_tableContactsMouseClicked

    private void btnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseClicked
        // TODO add your handling code here:
        saveContact();
    }//GEN-LAST:event_btnSaveMouseClicked

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        // TODO add your handling code here:
        deleteContact();
    }//GEN-LAST:event_btnDeleteMouseClicked

    private void btnUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseClicked
        // TODO add your handling code here:
        updateContact();
    }//GEN-LAST:event_btnUpdateMouseClicked

    private void btnSelectPhotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelectPhotoMouseClicked
        // TODO add your handling code here:
        selectPhoto();
    }//GEN-LAST:event_btnSelectPhotoMouseClicked

    private void lblViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblViewMouseClicked
        // TODO add your handling code here:
        showContactDetails();
    }//GEN-LAST:event_lblViewMouseClicked

    private void lblLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoutMouseClicked
        // TODO add your handling code here:
        new LoginView().setVisible(true);
        ContactView.this.dispose();
    }//GEN-LAST:event_lblLogoutMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ContactView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelEmail;
    private javax.swing.JPanel PanelForm;
    private javax.swing.JPanel PanelFullName;
    private javax.swing.JPanel PanelHeader;
    private javax.swing.JPanel PanelId;
    private javax.swing.JPanel PanelPassword;
    private javax.swing.JPanel PanelTable;
    private javax.swing.JPanel PanelUsername;
    private javax.swing.JSeparator Separator;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSelectPhoto;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel lblAboutUS;
    private javax.swing.JLabel lblAdmin;
    private javax.swing.JLabel lblAdministraction;
    private javax.swing.JLabel lblContact;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEmail1;
    private javax.swing.JLabel lblFullName;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogout;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblMessageSearch;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblReload;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblView;
    private javax.swing.JScrollPane sPTableAdmins;
    private javax.swing.JPanel subPanelButtons;
    private javax.swing.JPanel subPanelForm;
    private javax.swing.JPanel subPanelHeader;
    private javax.swing.JPanel subPanelMenu;
    private javax.swing.JPanel subPanelMessage;
    private javax.swing.JTable tableContacts;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

}
