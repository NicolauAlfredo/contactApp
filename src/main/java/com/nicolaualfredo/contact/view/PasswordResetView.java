/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.nicolaualfredo.contact.view;

import com.nicolaualfredo.contact.dao.DBConnection;
import com.nicolaualfredo.contact.util.PasswordUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Nicolau Alfredo
 */
public class PasswordResetView extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PasswordResetView.class.getName());

    /**
     * Creates new form PasswordReset
     */
    public PasswordResetView() {
        initComponents();
    }

    public void showTemporaryMessage(JLabel label, String message, int durationMillis) {
        label.setText(message);

        Timer timer = new Timer(durationMillis, e -> label.setText(""));
        timer.setRepeats(false);
        timer.start();
    }

    private void resetPassword() {
        String username = txtUsername.getText().trim();
        String masterKey = txtResetMasterKey.getText().trim();

        if (username.isEmpty() || masterKey.isEmpty()) {
            showTemporaryMessage(lblMessage, "Please fill in both fields.", 5000);
            return;
        }

        final String MASTER_KEY = "PapaiCode";

        if (!MASTER_KEY.equals(masterKey)) {
            showTemporaryMessage(lblMessage, "Invalid reset key. Access denied.", 5000);
            return;
        }

        String newPassword = JOptionPane.showInputDialog(this, "Enter new password:");
        if (newPassword == null || newPassword.trim().isEmpty()) {
            showTemporaryMessage(lblMessage, "Password reset cancelled.", 5000);
            return;
        }

        String hashedPassword = PasswordUtil.hash(newPassword.trim());

        DBConnection db = new DBConnection();

        try {
            Connection con = db.open();
 
            PreparedStatement checkStmt = con.prepareStatement("SELECT COUNT(*) FROM admin WHERE username = ?");
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count == 0) {
                showTemporaryMessage(lblMessage, "No admin found with that username.", 5000);
                return;
            }
 
            PreparedStatement ps = con.prepareStatement("UPDATE admin SET password_hash = ? WHERE username = ?");
            ps.setString(1, hashedPassword);
            ps.setString(2, username);

            int updated = ps.executeUpdate();

            if (updated > 0) {
                showTemporaryMessage(lblMessage, "Password updated successfully!", 5000);
            } else {
                showTemporaryMessage(lblMessage, "Unexpected error. Please try again.", 5000);
            }

        } catch (SQLException e) {
            showTemporaryMessage(lblMessage, "Database error: " + e.getMessage(), 5000);
        } finally {
            db.close();
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

        PanelLogo = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        PanelPasswordReset = new javax.swing.JPanel();
        subPanelHeader = new javax.swing.JPanel();
        lblPasswordReset = new javax.swing.JLabel();
        lblText1 = new javax.swing.JLabel();
        lblText2 = new javax.swing.JLabel();
        subPanelForm = new javax.swing.JPanel();
        PanelUsername = new javax.swing.JPanel();
        txtUsername = new javax.swing.JTextField();
        lblUsername = new javax.swing.JLabel();
        PanelMasterKey = new javax.swing.JPanel();
        lblMasterKey = new javax.swing.JLabel();
        txtResetMasterKey = new javax.swing.JPasswordField();
        PanelLogin = new javax.swing.JPanel();
        lblLogin = new javax.swing.JLabel();
        subPanelSignUp = new javax.swing.JPanel();
        lblText3 = new javax.swing.JLabel();
        lblSignUp = new javax.swing.JLabel();
        subPanelResetPassword = new javax.swing.JPanel();
        btnResetPassword = new javax.swing.JButton();
        lblMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Password Reset");
        setResizable(false);

        PanelLogo.setBackground(new java.awt.Color(53, 123, 244));
        PanelLogo.setPreferredSize(new java.awt.Dimension(200, 500));

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/password.png"))); // NOI18N

        javax.swing.GroupLayout PanelLogoLayout = new javax.swing.GroupLayout(PanelLogo);
        PanelLogo.setLayout(PanelLogoLayout);
        PanelLogoLayout.setHorizontalGroup(
            PanelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLogoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelLogoLayout.setVerticalGroup(
            PanelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLogoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(PanelLogo, java.awt.BorderLayout.WEST);

        PanelPasswordReset.setBackground(new java.awt.Color(255, 255, 255));
        PanelPasswordReset.setPreferredSize(new java.awt.Dimension(400, 500));

        subPanelHeader.setBackground(new java.awt.Color(255, 255, 255));
        subPanelHeader.setPreferredSize(new java.awt.Dimension(400, 170));

        lblPasswordReset.setBackground(new java.awt.Color(255, 255, 255));
        lblPasswordReset.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblPasswordReset.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPasswordReset.setText("Password Reset");
        lblPasswordReset.setPreferredSize(new java.awt.Dimension(147, 50));

        lblText1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblText1.setText("Provide the username and the reset master key");
        lblText1.setPreferredSize(new java.awt.Dimension(275, 50));

        lblText2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblText2.setText("to recover your password.");

        javax.swing.GroupLayout subPanelHeaderLayout = new javax.swing.GroupLayout(subPanelHeader);
        subPanelHeader.setLayout(subPanelHeaderLayout);
        subPanelHeaderLayout.setHorizontalGroup(
            subPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subPanelHeaderLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(subPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPasswordReset, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblText2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        subPanelHeaderLayout.setVerticalGroup(
            subPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, subPanelHeaderLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(lblPasswordReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblText1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblText2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelPasswordReset.add(subPanelHeader);

        subPanelForm.setBackground(new java.awt.Color(255, 255, 255));
        subPanelForm.setPreferredSize(new java.awt.Dimension(400, 140));

        PanelUsername.setBackground(new java.awt.Color(255, 255, 255));
        PanelUsername.setPreferredSize(new java.awt.Dimension(350, 50));
        PanelUsername.setLayout(new java.awt.BorderLayout(4, 4));
        PanelUsername.add(txtUsername, java.awt.BorderLayout.CENTER);

        lblUsername.setText("Username");
        PanelUsername.add(lblUsername, java.awt.BorderLayout.PAGE_START);

        subPanelForm.add(PanelUsername);

        PanelMasterKey.setBackground(new java.awt.Color(255, 255, 255));
        PanelMasterKey.setPreferredSize(new java.awt.Dimension(350, 50));
        PanelMasterKey.setLayout(new java.awt.BorderLayout(4, 4));

        lblMasterKey.setText("Reset Master Key");
        PanelMasterKey.add(lblMasterKey, java.awt.BorderLayout.PAGE_START);
        PanelMasterKey.add(txtResetMasterKey, java.awt.BorderLayout.CENTER);

        subPanelForm.add(PanelMasterKey);

        PanelLogin.setBackground(new java.awt.Color(255, 255, 255));
        PanelLogin.setPreferredSize(new java.awt.Dimension(350, 20));

        lblLogin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblLogin.setForeground(new java.awt.Color(53, 123, 244));
        lblLogin.setText("Log In");
        lblLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblLogin.setPreferredSize(new java.awt.Dimension(34, 10));
        lblLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLoginMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PanelLoginLayout = new javax.swing.GroupLayout(PanelLogin);
        PanelLogin.setLayout(PanelLoginLayout);
        PanelLoginLayout.setHorizontalGroup(
            PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        PanelLoginLayout.setVerticalGroup(
            PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLoginLayout.createSequentialGroup()
                .addComponent(lblLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        subPanelForm.add(PanelLogin);

        PanelPasswordReset.add(subPanelForm);

        subPanelSignUp.setBackground(new java.awt.Color(255, 255, 255));
        subPanelSignUp.setPreferredSize(new java.awt.Dimension(400, 30));

        lblText3.setBackground(new java.awt.Color(84, 86, 255));
        lblText3.setText("Don't have an account yet?");
        subPanelSignUp.add(lblText3);

        lblSignUp.setBackground(new java.awt.Color(255, 255, 255));
        lblSignUp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSignUp.setForeground(new java.awt.Color(53, 123, 244));
        lblSignUp.setText("Sign up.");
        lblSignUp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSignUpMouseClicked(evt);
            }
        });
        subPanelSignUp.add(lblSignUp);

        PanelPasswordReset.add(subPanelSignUp);

        subPanelResetPassword.setBackground(new java.awt.Color(255, 255, 255));
        subPanelResetPassword.setPreferredSize(new java.awt.Dimension(400, 50));

        btnResetPassword.setBackground(new java.awt.Color(53, 123, 244));
        btnResetPassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnResetPassword.setForeground(new java.awt.Color(255, 255, 255));
        btnResetPassword.setText("Reset Password");
        btnResetPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnResetPassword.setPreferredSize(new java.awt.Dimension(250, 30));
        btnResetPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetPasswordMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout subPanelResetPasswordLayout = new javax.swing.GroupLayout(subPanelResetPassword);
        subPanelResetPassword.setLayout(subPanelResetPasswordLayout);
        subPanelResetPasswordLayout.setHorizontalGroup(
            subPanelResetPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subPanelResetPasswordLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(btnResetPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        subPanelResetPasswordLayout.setVerticalGroup(
            subPanelResetPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subPanelResetPasswordLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(btnResetPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelPasswordReset.add(subPanelResetPassword);

        lblMessage.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblMessage.setForeground(new java.awt.Color(53, 123, 244));
        PanelPasswordReset.add(lblMessage);

        getContentPane().add(PanelPasswordReset, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLoginMouseClicked
        // TODO add your handling code here:
        new LoginView().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblLoginMouseClicked

    private void lblSignUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSignUpMouseClicked
        // TODO add your handling code here:
        new SignupView().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblSignUpMouseClicked

    private void btnResetPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetPasswordMouseClicked
        // TODO add your handling code here:
        resetPassword();
    }//GEN-LAST:event_btnResetPasswordMouseClicked

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
        java.awt.EventQueue.invokeLater(() -> new PasswordResetView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelLogin;
    private javax.swing.JPanel PanelLogo;
    private javax.swing.JPanel PanelMasterKey;
    private javax.swing.JPanel PanelPasswordReset;
    private javax.swing.JPanel PanelUsername;
    private javax.swing.JButton btnResetPassword;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMasterKey;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblPasswordReset;
    private javax.swing.JLabel lblSignUp;
    private javax.swing.JLabel lblText1;
    private javax.swing.JLabel lblText2;
    private javax.swing.JLabel lblText3;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPanel subPanelForm;
    private javax.swing.JPanel subPanelHeader;
    private javax.swing.JPanel subPanelResetPassword;
    private javax.swing.JPanel subPanelSignUp;
    private javax.swing.JPasswordField txtResetMasterKey;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
