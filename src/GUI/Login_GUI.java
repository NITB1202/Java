/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.AccountBUS;
import BUS.RoleBUS;
import DTO.entities.Account;
import DTO.entities.Person;
import DTO.entities.Role;
import java.awt.Color;
import MyDesign.Login;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.ArrayList;

import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author QUANG DIEN
 */
public class Login_GUI extends javax.swing.JFrame {
    private AccountBUS userBUS;
    private RoleBUS roleBUS;
    private Account user;
    
    private ArrayList<Person> accList;    
    private ArrayList<Role> roles;

    private String username = "";
    private String pwd = "";
    /**
     * Creates new form Login_GUI
     */
    public Login_GUI() throws ClassNotFoundException, SQLException, IOException{
        this.user = new Account();
        this.userBUS = new AccountBUS();
        this.roleBUS = new RoleBUS();
        accList = userBUS.getList();
        roles = roleBUS.getList();
        initComponents();
        setBackground(new Color(0,0,0,0));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundLogin = new MyDesign.Login();
        panel = new javax.swing.JPanel();
        txtUserName = new MyDesign.TextField_Login();
        txtPassword = new MyDesign.PasswordField_Login();
        jLabel1 = new javax.swing.JLabel();
        btnLogin = new MyDesign.Button_Login();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panel.setOpaque(false);

        txtUserName.setHint("User Name");

        txtPassword.setHint("Password");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SIGN IN");

        btnLogin.setForeground(new java.awt.Color(218, 218, 218));
        btnLogin.setText("SIGN IN");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout backgroundLoginLayout = new javax.swing.GroupLayout(backgroundLogin);
        backgroundLogin.setLayout(backgroundLoginLayout);
        backgroundLoginLayout.setHorizontalGroup(
            backgroundLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLoginLayout.createSequentialGroup()
                .addContainerGap(248, Short.MAX_VALUE)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(247, Short.MAX_VALUE))
        );
        backgroundLoginLayout.setVerticalGroup(
            backgroundLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLoginLayout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String username = txtUserName.getText().trim();
        String pwd = String.valueOf(txtPassword.getPassword());
        if (username.isEmpty()) {
            // Tên người dùng không được để trống
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên người dùng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Kiểm tra xác thực mật khẩu (password)
        if (pwd.isEmpty()) {
            // Mật khẩu không được để trống
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean checkUsername = false;        
        boolean checkPwd = false;
        boolean checkRole = false;
        String roleLogin = "";
        String checkPwdHash;
        for(Person per : accList){
            Account acc = (Account) per;
            if(acc.getUsername().equals(username)){
                checkUsername = true;
                try {
                    checkPwdHash = Account.hashPassword(pwd);
                    if(acc.getPwd().equals(checkPwdHash)){
                        checkPwd = true;
                        roleLogin = acc.getRoleID();
                    }
                        
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(Login_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }  
            }
        }
        
        if(checkUsername == true && checkPwd == true){
            for(Role role : roles){
                if(role.getRoleID().equals(roleLogin) && role.getIsDeleted() == 1){
                    checkRole = true;
                    break;
                }
            }
        }
        if (checkUsername == false) {
            // Mật khẩu không được để trống
            JOptionPane.showMessageDialog(this, "Tài khoản không tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (checkPwd == false) {
            // Mật khẩu không được để trống
            JOptionPane.showMessageDialog(this, "Mật khẩu không đúng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (checkPwd == false) {
            // Mật khẩu không được để trống
            JOptionPane.showMessageDialog(this, "Bạn không quyền đăng nhập vào ứng dụng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        user = new Account();
        user.setUsername(username);
        user.setPwd(pwd);
        System.out.println(user.getUsername());        
        System.out.println(user.getPwd());

        try {   
            user = userBUS.signIn(user);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Login_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.setVisible(false);
        HomePage homePage = null;
        try {
            homePage = new HomePage(user);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login_GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login_GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        homePage.setVisible(true);
    }//GEN-LAST:event_btnLoginActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Login_GUI().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Login_GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Login_GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Login_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private MyDesign.Login backgroundLogin;
    private MyDesign.Button_Login btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panel;
    private MyDesign.PasswordField_Login txtPassword;
    private MyDesign.TextField_Login txtUserName;
    // End of variables declaration//GEN-END:variables
}
