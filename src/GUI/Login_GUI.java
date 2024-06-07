package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BUS.AccountBUS;
import DTO.entities.Account;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Label;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Login_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	
    private Account user;
    private AccountBUS userBUS;
    
    private ArrayList<Account> accList;
    private JLabel lbl_tk;
    private JLabel lblNewLabel;
    private JPanel panel;
    private JLabel imageview;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_GUI frame = new Login_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login_GUI() throws ClassNotFoundException, SQLException, IOException{
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login_GUI.class.getResource("/Images/logo.png")));
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setTitle("Quản lý thư viện");
		setResizable(false);
		
        this.user = new Account();
        userBUS = new AccountBUS();
        accList = userBUS.getList();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int x = ((int)screenSize.getWidth()- this.getWidth())/2;
		int y = ((int)screenSize.getHeight() - this.getHeight())/2;
		
		setLocation(x,y);

		setContentPane(contentPane);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(323, 123, 233, 28);
		txtUserName.setBorder(new LineBorder(new Color(0, 141, 218)));
		txtUserName.setBackground(new Color(255, 255, 255));
		txtUserName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		
		txtPassword = new JPasswordField();
		txtPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLoginActionPerformed();
			}
		});
		txtPassword.setBounds(323, 193, 233, 30);
		txtPassword.setBorder(new LineBorder(new Color(0, 141, 218)));
		txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		
		JButton btnLogin = new JButton("ĐĂNG NHẬP");
		btnLogin.setBounds(323, 250, 232, 29);
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnLogin.setBorderPainted(false);
		btnLogin.setBackground(new Color(0, 141, 218));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLoginActionPerformed();
			}
		});
		
		JLabel lbl_dn = new JLabel("Đăng nhập");
		lbl_dn.setBounds(366, 43, 145, 37);
		lbl_dn.setForeground(new Color(0, 141, 218));
		lbl_dn.setFont(new Font("Segoe UI", Font.BOLD, 28));
		
		lbl_tk = new JLabel("Tài khoản");
		lbl_tk.setBounds(323, 92, 76, 20);
		lbl_tk.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		
		lblNewLabel = new JLabel("Mật khẩu");
		lblNewLabel.setBounds(323, 162, 76, 20);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		contentPane.setLayout(null);
		
		ImageIcon image = new ImageIcon("src/Images/login.png");
		Image resizedImage = image.getImage();
		resizedImage = resizedImage.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
		image = new ImageIcon(resizedImage);
		
		contentPane.add(lbl_dn);
		contentPane.add(lbl_tk);
		contentPane.add(txtUserName);
		contentPane.add(lblNewLabel);
		contentPane.add(txtPassword);
		contentPane.add(btnLogin);
		
		panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(0, 141, 218));
		panel.setBounds(0, 0, 292, 361);
		contentPane.add(panel);
		panel.setLayout(null);
		
		imageview = new JLabel(image);
		imageview.setBounds(20, 50, 250, 250);
		panel.add(imageview);
		
	}
	
	 private void btnLoginActionPerformed() {
	        String username = txtUserName.getText().trim();
	        String pwd = String.valueOf(txtPassword.getPassword());
	        if (username.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên người dùng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        

	        if (pwd.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        
	        boolean checkUsername = false;        
	        boolean checkPwd = false;
	        
	        for(Account acc : accList){
	            if(acc.getUsername().equals(username)){
	                checkUsername = true;
	                try {
	                    String checkPwdHash = Account.hashPassword(pwd);
	                    if(acc.getPwd().equals(checkPwdHash)){
	                        checkPwd = true;
	                    }
	                        
	                } catch (NoSuchAlgorithmException ex) {
	                    Logger.getLogger(Login_GUI.class.getName()).log(Level.SEVERE, null, ex);
	                }  
	            }
	        }
	        
	        if (checkUsername == false) {
	            JOptionPane.showMessageDialog(this, "Tài khoản không tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        if (checkPwd == false) {
	            JOptionPane.showMessageDialog(this, "Mật khẩu không đúng", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
	        HomePage_GUI homePage = null;
	        try {
	            homePage = new HomePage_GUI(user);
	        } catch (ClassNotFoundException ex) {
	            Logger.getLogger(Login_GUI.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (SQLException ex) {
	            Logger.getLogger(Login_GUI.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (IOException ex) {
	            Logger.getLogger(Login_GUI.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        homePage.setVisible(true);
	        dispose();
	    }
}
