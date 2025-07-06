/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.nicolaualfredo.contact.view;

import com.nicolaualfredo.contact.controller.AdminController;
import com.nicolaualfredo.contact.model.Admin;
import com.nicolaualfredo.contact.util.PasswordUtil;
import java.sql.Timestamp;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nicolaualfredo
 */
public class AdminView extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdminView.class.getName());

    /**
     * Creates new form ContactApp
     */
    public AdminView() {
        initComponents();
        loadAdmins();
    }

    private void loadAdmins() {
        AdminController adminController = new AdminController();
        DefaultTableModel model = (DefaultTableModel) tableAdmins.getModel();
        model.setRowCount(0);

        List<Admin> admins = adminController.getAllAdmins();

        for (Admin admin : admins) {
            model.addRow(new Object[]{
                admin.getIdAdmin(),
                admin.getUsernameAdmin(),
                admin.getFullName(),
                admin.getEmail()
            });
        }

        checkIdField();
    }

    private void loadAdminByUsername() {
        AdminController adminController = new AdminController();
        DefaultTableModel model = (DefaultTableModel) tableAdmins.getModel();
        model.setRowCount(0);

        lblMessageSearch.setText("");

        String username = txtSearch.getText().trim();

        if (username.isEmpty()) {
            showTemporaryMessage(lblMessageSearch, "Please enter a username to search.", 5000);
            return;
        }

        Admin admin = adminController.getAdminByUsername(username);

        if (admin != null) {
            model.addRow(new Object[]{
                admin.getIdAdmin(),
                admin.getUsernameAdmin(),
                admin.getFullName(),
                admin.getEmail()
            });
        } else {
            showTemporaryMessage(lblMessageSearch, "No admin found.", 5000);
        }
    }

    private void saveAdmin() {
        if (!validateFields()) {
            return;
        }

        Admin admin = new Admin();
        AdminController controller = new AdminController();

        admin.setUsernameAdmin(txtUsername.getText().trim());
        admin.setPassword_has(PasswordUtil.hash(txtPassword.getText().trim()));
        admin.setFullName(txtFullName.getText().trim());
        admin.setEmail(txtEmail.getText());
        admin.setCreated_at(new Timestamp(System.currentTimeMillis()));

        if (!txtId.getText().isEmpty()) {
            showTemporaryMessage(lblMessage, "Admin created successfully.", 5000);
        }

        controller.createAdmin(admin);
        showTemporaryMessage(lblMessage, "Admin created successfully.", 5000);
        clearFields();
        loadAdmins();
    }

    private void updateAdmin() {
        if (txtId.getText().isEmpty()) {
            showTemporaryMessage(lblMessage, "Select an admin to update.", 5000);
            return;
        }

        Admin admin = new Admin();
        AdminController controller = new AdminController();

        if (!validateFields()) {
            return;
        }

        admin.setIdAdmin(Integer.parseInt(txtId.getText()));
        admin.setUsernameAdmin(txtUsername.getText());
        admin.setPassword_has(PasswordUtil.hash(txtPassword.getText()));
        admin.setFullName(txtFullName.getText());
        admin.setEmail(txtEmail.getText());

        controller.updateAdmin(admin);
        showTemporaryMessage(lblMessage, "Admin updated successfully.", 5000);
        clearFields();
        loadAdmins();
    }

    private void deleteAdmin() {
        if (txtId.getText().isEmpty()) {
            showTemporaryMessage(lblMessage, "Select an admin to delete.", 5000);
            return;
        }

        Admin admin = new Admin();
        AdminController controller = new AdminController();

        int id = Integer.parseInt(txtId.getText());

        admin = controller.getAdminById(id);
        if (admin == null) {
            showTemporaryMessage(lblMessage, "Admin not found.", 5000);
            return;
        }

        controller.deleteAdmin(admin);
        clearFields();
        loadAdmins();
        showTemporaryMessage(lblMessage, "Admin deleted successfully.", 5000);

    }

    private void clearFields() {
        // Form
        txtId.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtFullName.setText("");
        txtEmail.setText("");
    }

    private void getDataFromTable() {
        int row = tableAdmins.getSelectedRow();

        if (row != -1) {
            String id = tableAdmins.getValueAt(row, 0).toString();
            String username = tableAdmins.getValueAt(row, 1).toString();
            String fullName = tableAdmins.getValueAt(row, 2).toString();
            String email = tableAdmins.getValueAt(row, 3).toString();

            txtId.setText(id);
            txtUsername.setText(username);
            txtFullName.setText(fullName);
            txtEmail.setText(email);
        }

        checkIdField();
    }

    private boolean validateFields() {
        if (txtUsername.getText().trim().isEmpty()
                || txtPassword.getText().trim().isEmpty()
                || txtFullName.getText().trim().isEmpty()
                || txtEmail.getText().trim().isEmpty()) {

            showTemporaryMessage(lblMessage, "Please fill in all required fields.", 5000);
            return false;
        }
        checkIdField();
        return true;
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
        txtUsername = new javax.swing.JTextField();
        PanelPassword = new javax.swing.JPanel();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        PanelFullName = new javax.swing.JPanel();
        lblFullName = new javax.swing.JLabel();
        txtFullName = new javax.swing.JTextField();
        PanelEmail = new javax.swing.JPanel();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        subPanelButtons = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        subPanelMessage = new javax.swing.JPanel();
        lblMessage = new javax.swing.JLabel();
        PanelTable = new javax.swing.JPanel();
        sPTableAdmins = new javax.swing.JScrollPane();
        tableAdmins = new javax.swing.JTable();
        lblSearch = new javax.swing.JLabel();
        lblMessageSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        PanelHeader.setBackground(new java.awt.Color(255, 255, 255));
        PanelHeader.setPreferredSize(new java.awt.Dimension(800, 50));

        subPanelMenu.setBackground(new java.awt.Color(255, 255, 255));
        subPanelMenu.setPreferredSize(new java.awt.Dimension(200, 50));

        lblAdmin.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        lblAdmin.setForeground(new java.awt.Color(53, 123, 244));
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
                .addContainerGap())
        );
        subPanelMenuLayout.setVerticalGroup(
            subPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, subPanelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(subPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblAdmin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(Separator, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblContact, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAboutUS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/admin.png"))); // NOI18N
        subPanelHeader.add(lblLogo);

        lblAdministraction.setBackground(new java.awt.Color(255, 255, 255));
        lblAdministraction.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAdministraction.setForeground(new java.awt.Color(255, 255, 255));
        lblAdministraction.setText("Administration");
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
        lblUsername.setText("Username");
        PanelUsername.add(lblUsername, java.awt.BorderLayout.PAGE_START);

        txtUsername.setToolTipText("Enter your username");
        PanelUsername.add(txtUsername, java.awt.BorderLayout.CENTER);

        subPanelForm.add(PanelUsername);

        PanelPassword.setBackground(new java.awt.Color(53, 123, 244));
        PanelPassword.setPreferredSize(new java.awt.Dimension(295, 48));
        PanelPassword.setLayout(new java.awt.BorderLayout());

        lblPassword.setBackground(new java.awt.Color(53, 123, 244));
        lblPassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(255, 255, 255));
        lblPassword.setText("Password");
        PanelPassword.add(lblPassword, java.awt.BorderLayout.PAGE_START);

        txtPassword.setToolTipText("Enter your password");
        PanelPassword.add(txtPassword, java.awt.BorderLayout.CENTER);

        subPanelForm.add(PanelPassword);

        PanelFullName.setBackground(new java.awt.Color(53, 123, 244));
        PanelFullName.setPreferredSize(new java.awt.Dimension(295, 48));
        PanelFullName.setLayout(new java.awt.BorderLayout());

        lblFullName.setBackground(new java.awt.Color(53, 123, 244));
        lblFullName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFullName.setForeground(new java.awt.Color(255, 255, 255));
        lblFullName.setText("Full Name");
        PanelFullName.add(lblFullName, java.awt.BorderLayout.PAGE_START);

        txtFullName.setToolTipText("Enter your full name");
        PanelFullName.add(txtFullName, java.awt.BorderLayout.CENTER);

        subPanelForm.add(PanelFullName);

        PanelEmail.setBackground(new java.awt.Color(53, 123, 244));
        PanelEmail.setPreferredSize(new java.awt.Dimension(295, 48));
        PanelEmail.setLayout(new java.awt.BorderLayout());

        lblEmail.setBackground(new java.awt.Color(53, 123, 244));
        lblEmail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(255, 255, 255));
        lblEmail.setText("Email");
        PanelEmail.add(lblEmail, java.awt.BorderLayout.PAGE_START);

        txtEmail.setToolTipText("Enter your email");
        PanelEmail.add(txtEmail, java.awt.BorderLayout.CENTER);

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

        tableAdmins.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "USERNAME", "FULL NAME", "EMAIL"
            }
        ));
        tableAdmins.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableAdminsMouseClicked(evt);
            }
        });
        sPTableAdmins.setViewportView(tableAdmins);

        lblSearch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        lblSearch.setToolTipText("Search button");
        lblSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSearchMouseClicked(evt);
            }
        });

        lblMessageSearch.setFont(new java.awt.Font("Sitka Text", 0, 12)); // NOI18N
        lblMessageSearch.setForeground(new java.awt.Color(53, 123, 244));
        lblMessageSearch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        txtSearch.setToolTipText("Enter a username to search");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/reload.png"))); // NOI18N
        jLabel1.setToolTipText("Cancel all operations");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PanelTableLayout = new javax.swing.GroupLayout(PanelTable);
        PanelTable.setLayout(PanelTableLayout);
        PanelTableLayout.setHorizontalGroup(
            PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelTableLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMessageSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sPTableAdmins, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelTableLayout.setVerticalGroup(
            PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelTableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMessageSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sPTableAdmins, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(PanelTable, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAdminMouseClicked
        // TODO add your handling code here:
        showTemporaryMessage(lblMessage, "You are on the Admin page.", 5000);
    }//GEN-LAST:event_lblAdminMouseClicked

    private void lblContactMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblContactMouseClicked
        // TODO add your handling code here:
        new ContactView().setVisible(true);
        AdminView.this.dispose();
    }//GEN-LAST:event_lblContactMouseClicked

    private void lblAboutUSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAboutUSMouseClicked
        // TODO add your handling code here:
        showTemporaryMessage(lblMessage, "Come soon!!", 5000);
    }//GEN-LAST:event_lblAboutUSMouseClicked

    private void lblSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSearchMouseClicked
        // TODO add your handling code here:
        loadAdminByUsername();
    }//GEN-LAST:event_lblSearchMouseClicked

    private void btnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseClicked
        // TODO add your handling code here:
        saveAdmin();
    }//GEN-LAST:event_btnSaveMouseClicked

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        // TODO add your handling code here:
        deleteAdmin();
    }//GEN-LAST:event_btnDeleteMouseClicked

    private void btnUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseClicked
        // TODO add your handling code here:
        updateAdmin();
    }//GEN-LAST:event_btnUpdateMouseClicked

    private void tableAdminsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAdminsMouseClicked
        // TODO add your handling code here:
        getDataFromTable();
    }//GEN-LAST:event_tableAdminsMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        clearFields();
        loadAdmins();
        btnSave.setEnabled(true);
    }//GEN-LAST:event_jLabel1MouseClicked

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
        java.awt.EventQueue.invokeLater(() -> new AdminView().setVisible(true));
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
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblAboutUS;
    private javax.swing.JLabel lblAdmin;
    private javax.swing.JLabel lblAdministraction;
    private javax.swing.JLabel lblContact;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEmail1;
    private javax.swing.JLabel lblFullName;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblMessageSearch;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JScrollPane sPTableAdmins;
    private javax.swing.JPanel subPanelButtons;
    private javax.swing.JPanel subPanelForm;
    private javax.swing.JPanel subPanelHeader;
    private javax.swing.JPanel subPanelMenu;
    private javax.swing.JPanel subPanelMessage;
    private javax.swing.JTable tableAdmins;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
