/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.nicolaualfredo.contact.view;

/**
 *
 * @author Nicolau Alfredo
 */
public class Login extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Login.class.getName());

    /**
     * Creates new form LogInc
     */
    public Login() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelFom = new javax.swing.JPanel();
        subPanelWelcome = new javax.swing.JPanel();
        lblWelcomeBack = new javax.swing.JLabel();
        subPanelLoginGoogle = new javax.swing.JPanel();
        lblLoginGoogle = new javax.swing.JLabel();
        subPanelForm = new javax.swing.JPanel();
        PanelUsername = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        PanelPassword = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        PanelRemember = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        subPanelLogin = new javax.swing.JPanel();
        btnLogin = new javax.swing.JButton();
        subPanelSignUp = new javax.swing.JPanel();
        lblCreateAccount = new javax.swing.JLabel();
        lblSignUp = new javax.swing.JLabel();
        PanelWelcomeBack = new javax.swing.JPanel();
        lblWelcome = new javax.swing.JLabel();
        lblText1 = new javax.swing.JLabel();
        lblText2 = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOG IN");

        PanelFom.setBackground(new java.awt.Color(255, 255, 255));
        PanelFom.setPreferredSize(new java.awt.Dimension(400, 500));

        subPanelWelcome.setBackground(new java.awt.Color(255, 255, 255));
        subPanelWelcome.setPreferredSize(new java.awt.Dimension(400, 100));

        lblWelcomeBack.setBackground(new java.awt.Color(255, 255, 255));
        lblWelcomeBack.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblWelcomeBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWelcomeBack.setText("Welcome Back");

        javax.swing.GroupLayout subPanelWelcomeLayout = new javax.swing.GroupLayout(subPanelWelcome);
        subPanelWelcome.setLayout(subPanelWelcomeLayout);
        subPanelWelcomeLayout.setHorizontalGroup(
            subPanelWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblWelcomeBack, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        subPanelWelcomeLayout.setVerticalGroup(
            subPanelWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, subPanelWelcomeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblWelcomeBack)
                .addGap(35, 35, 35))
        );

        PanelFom.add(subPanelWelcome);

        subPanelLoginGoogle.setBackground(new java.awt.Color(255, 255, 255));
        subPanelLoginGoogle.setPreferredSize(new java.awt.Dimension(400, 50));

        lblLoginGoogle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/google_.png"))); // NOI18N
        lblLoginGoogle.setText("Log in with Google");
        lblLoginGoogle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblLoginGoogle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLoginGoogleMouseClicked(evt);
            }
        });
        subPanelLoginGoogle.add(lblLoginGoogle);

        PanelFom.add(subPanelLoginGoogle);

        subPanelForm.setBackground(new java.awt.Color(255, 255, 255));
        subPanelForm.setPreferredSize(new java.awt.Dimension(400, 150));

        PanelUsername.setBackground(new java.awt.Color(255, 255, 255));
        PanelUsername.setPreferredSize(new java.awt.Dimension(250, 50));
        PanelUsername.setLayout(new java.awt.BorderLayout(4, 4));
        PanelUsername.add(jTextField1, java.awt.BorderLayout.CENTER);

        jLabel2.setText("Username");
        PanelUsername.add(jLabel2, java.awt.BorderLayout.PAGE_START);

        subPanelForm.add(PanelUsername);

        PanelPassword.setBackground(new java.awt.Color(255, 255, 255));
        PanelPassword.setPreferredSize(new java.awt.Dimension(250, 50));
        PanelPassword.setLayout(new java.awt.BorderLayout(4, 4));

        jLabel3.setText("Password");
        PanelPassword.add(jLabel3, java.awt.BorderLayout.PAGE_START);
        PanelPassword.add(jPasswordField1, java.awt.BorderLayout.CENTER);

        subPanelForm.add(PanelPassword);

        PanelRemember.setBackground(new java.awt.Color(255, 255, 255));
        PanelRemember.setPreferredSize(new java.awt.Dimension(250, 25));
        PanelRemember.setLayout(new java.awt.BorderLayout());

        jCheckBox1.setText("Remember me");
        jCheckBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelRemember.add(jCheckBox1, java.awt.BorderLayout.WEST);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(53, 123, 244));
        jLabel4.setText("Forgot your password?");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelRemember.add(jLabel4, java.awt.BorderLayout.EAST);

        subPanelForm.add(PanelRemember);

        PanelFom.add(subPanelForm);

        subPanelLogin.setBackground(new java.awt.Color(255, 255, 255));
        subPanelLogin.setPreferredSize(new java.awt.Dimension(400, 50));

        btnLogin.setBackground(new java.awt.Color(53, 123, 244));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("LOGIN");
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.setPreferredSize(new java.awt.Dimension(250, 30));
        subPanelLogin.add(btnLogin);

        PanelFom.add(subPanelLogin);

        subPanelSignUp.setBackground(new java.awt.Color(255, 255, 255));
        subPanelSignUp.setPreferredSize(new java.awt.Dimension(400, 50));

        lblCreateAccount.setBackground(new java.awt.Color(84, 86, 255));
        lblCreateAccount.setText("Don't have an account yet?");
        subPanelSignUp.add(lblCreateAccount);

        lblSignUp.setBackground(new java.awt.Color(255, 255, 255));
        lblSignUp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSignUp.setForeground(new java.awt.Color(53, 123, 244));
        lblSignUp.setText("Sign up.");
        lblSignUp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        subPanelSignUp.add(lblSignUp);

        PanelFom.add(subPanelSignUp);

        getContentPane().add(PanelFom, java.awt.BorderLayout.WEST);

        PanelWelcomeBack.setBackground(new java.awt.Color(53, 123, 244));
        PanelWelcomeBack.setPreferredSize(new java.awt.Dimension(400, 500));

        lblWelcome.setBackground(new java.awt.Color(255, 255, 255));
        lblWelcome.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblWelcome.setForeground(new java.awt.Color(255, 255, 255));
        lblWelcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWelcome.setText("Welcome Back");

        lblText1.setBackground(new java.awt.Color(255, 255, 255));
        lblText1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblText1.setForeground(new java.awt.Color(255, 255, 255));
        lblText1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblText1.setText("Welcome back! We are so happy to have you here. It's great to ");

        lblText2.setBackground(new java.awt.Color(255, 255, 255));
        lblText2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblText2.setForeground(new java.awt.Color(255, 255, 255));
        lblText2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblText2.setText("see you again. We hope you had a safe and enjoyable time away.");

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/programmer.png"))); // NOI18N

        javax.swing.GroupLayout PanelWelcomeBackLayout = new javax.swing.GroupLayout(PanelWelcomeBack);
        PanelWelcomeBack.setLayout(PanelWelcomeBackLayout);
        PanelWelcomeBackLayout.setHorizontalGroup(
            PanelWelcomeBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelWelcomeBackLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelWelcomeBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblWelcome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblText1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblText2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelWelcomeBackLayout.setVerticalGroup(
            PanelWelcomeBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelWelcomeBackLayout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(lblWelcome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblText1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblText2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLogo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(PanelWelcomeBack, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblLoginGoogleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLoginGoogleMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblLoginGoogleMouseClicked

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
        java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelFom;
    private javax.swing.JPanel PanelPassword;
    private javax.swing.JPanel PanelRemember;
    private javax.swing.JPanel PanelUsername;
    private javax.swing.JPanel PanelWelcomeBack;
    private javax.swing.JButton btnLogin;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblCreateAccount;
    private javax.swing.JLabel lblLoginGoogle;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblSignUp;
    private javax.swing.JLabel lblText1;
    private javax.swing.JLabel lblText2;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JLabel lblWelcomeBack;
    private javax.swing.JPanel subPanelForm;
    private javax.swing.JPanel subPanelLogin;
    private javax.swing.JPanel subPanelLoginGoogle;
    private javax.swing.JPanel subPanelSignUp;
    private javax.swing.JPanel subPanelWelcome;
    // End of variables declaration//GEN-END:variables
}
