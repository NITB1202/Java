package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BUS.RolePermissionBUS;
import DTO.entities.Account;
import DTO.entities.RolePermission;
import MyDesign.EventMenuSelected;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class HomePage_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private GUI.Menu Menu;
    private MyDesign.PanelBorder_Basic mainPanel;
    private JPanel panel;
    private RolePermissionBUS homePageBUS;
    private ArrayList<RolePermission> rolePermissions = new ArrayList<>();
    private Account user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                    new HomePage_GUI(new Account()).setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
		});
	}

	/**
	 * Create the frame.
	 */
	public HomePage_GUI(Account user) throws ClassNotFoundException, SQLException, IOException {
		this.user = user;
        System.out.println(user.getRoleID());
        homePageBUS = new RolePermissionBUS();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(HomePage_GUI.class.getResource("/Images/logo.png")));
		setTitle("Quản lý thư viện");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 660);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int x = ((int)screenSize.getWidth()- this.getWidth())/2;
		int y = ((int)screenSize.getHeight() - this.getHeight())/2;
		
		setLocation(x,y);
		
		contentPane = new JPanel();
        contentPane.setLayout(null);
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

        mainPanel = new MyDesign.PanelBorder_Basic();
        mainPanel.setBounds(162, 0, 822, 620);
        mainPanel.setLayout(new java.awt.BorderLayout());
        contentPane.add(mainPanel);
        
        Menu = new GUI.Menu();
        Menu.setBounds(0, 49, 160, 572);
        
        panel = new JPanel();
        panel.setBackground(new Color(172, 226, 255));
        panel.setBounds(0, 0, 160, 632);
        contentPane.add(panel);
        panel.setLayout(null);
        panel.add(Menu);
        
        contentPane.add(panel);
        
        JLabel lblNewLabel = new JLabel("Library");
        lblNewLabel.setForeground(new Color(22, 113, 221));
        lblNewLabel.setIcon(new ImageIcon(HomePage_GUI.class.getResource("/Images/logo.png")));
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblNewLabel.setBounds(15, 5, 127, 39);
        panel.add(lblNewLabel);
        
        setMenu();
	}
	
    private void setForm(JComponent com) {
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }
    
    private void setMenu() throws ClassNotFoundException, SQLException, IOException
    {
    	  Menu.addEventMenuSelected(new EventMenuSelected() {
              @Override
              public void selected(int index) {
              	System.out.println("Select index: "+index);
                  if (index == 0 && homePageBUS.hasPerAccess(user.getRoleID(), 1)) {
                      try {
                          setForm(new Statistic_GUI(user));
                      } catch (ClassNotFoundException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (SQLException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (IOException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  } else if (index == 1 && homePageBUS.hasPerAccess(user.getRoleID(), 2)) {
                      try {
                          setForm(new Borrow_GUI(user));
                      } catch (ClassNotFoundException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (SQLException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (IOException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  } 
                  else if (index == 2 && homePageBUS.hasPerAccess(user.getRoleID(), 3)) {
                      try {
                          setForm(new Pay_GUI(user));
                      } catch (ClassNotFoundException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (SQLException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (IOException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                  } 
                  else if (index == 3 && homePageBUS.hasPerAccess(user.getRoleID(), 4)) {
                      try {
                          setForm(new WareHouse_GUI(user));
                      } catch (SQLException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (IOException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (ClassNotFoundException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  }
                  else if (index == 4 && homePageBUS.hasPerAccess(user.getRoleID(), 7)) {
                      try {
                          setForm(new Ticket_GUI(user));
                      } catch (ClassNotFoundException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (SQLException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (IOException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  } 
                  else if (index == 6 && homePageBUS.hasPerAccess(user.getRoleID(), 6)) {
                      try {
                          setForm(new Reader_GUI(user));
                      } catch (Exception ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  } 
                  else if (index == 5 && homePageBUS.hasPerAccess(user.getRoleID(), 5)) {
                      try {
                          setForm(new Book_GUI(user));
                      } catch (SQLException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (IOException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (ClassNotFoundException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (Exception ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  }
                  else if (index == 7 && homePageBUS.hasPerAccess(user.getRoleID(), 8)) {
                      try {
                          setForm(new Staff_GUI(user));
                      } catch (Exception ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  } 
                  else if (index == 8 && homePageBUS.hasPerAccess(user.getRoleID(), 9)) {
                      try {
                          setForm(new More_GUI(user));
                      } catch (SQLException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (IOException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (ClassNotFoundException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  } 
                  else if (index == 9 && homePageBUS.hasPerAccess(user.getRoleID(), 10)) {
                      try {
                          setForm(new Admin_GUI(user));
                      } catch (ClassNotFoundException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (SQLException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (IOException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (NoSuchAlgorithmException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  } 
                  else if (index == 12) {
                	  try {
                		  HomePage_GUI.this.setVisible(false);
                          Login_GUI login = new Login_GUI();
                          login.setVisible(true);
                          dispose();
                      } catch (ClassNotFoundException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (SQLException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (IOException ex) {
                          Logger.getLogger(HomePage_GUI.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  }
                  else JOptionPane.showMessageDialog(HomePage_GUI.this, "Bạn không được phép truy cập vào chức năng này.", "Thông báo", JOptionPane.INFORMATION_MESSAGE); 
              }
          });
    	  setForm(new Statistic_GUI(user));
    }
}
