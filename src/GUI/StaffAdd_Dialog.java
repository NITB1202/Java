/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import BUS.RoleBUS;
import BUS.RolePermissionBUS;
import BUS.StaffBUS;
import DTO.entities.Account;
import DTO.entities.Role;
import DTO.entities.Staff;
import MyDesign.MyTable;
import com.aspose.pdf.internal.l68v.l4h;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.GroupLayout;
import java.awt.Toolkit;
import java.awt.Color;
/**
 *
 * @author QUANG DIEN
 */
public class StaffAdd_Dialog extends javax.swing.JDialog {
    private StaffBUS staffBUS;
    private RoleBUS roleBUS;
    private Role role;
    private MyDesign.MyTable tab;
    private DefaultComboBoxModel model;    
    private int personID;
    private String roleID;
    private Account user;
    private RolePermissionBUS rolePermissionBUS;
    /**
     * Creates new form StaffAdd_Dialog
     */
    public StaffAdd_Dialog(Account user, Frame parent, boolean modal,MyDesign.MyTable tab) throws IOException, ClassNotFoundException, SQLException {
        super(parent, modal);
        setIconImage(Toolkit.getDefaultToolkit().getImage(StaffAdd_Dialog.class.getResource("/Images/logo.png")));
        setTitle("Thêm nhân viên");
        this.user = user;
        this.tab = tab;
        personID = user.getPersonID();
        roleID = user.getRoleID();
        this.rolePermissionBUS = new RolePermissionBUS();
        try {
            staffBUS=new StaffBUS();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        initComponents();
        addRole(user.getRoleID());
    }
    public boolean checkDataVal(String name,String tel,String address,String username,String password) throws HeadlessException, FileNotFoundException, ClassNotFoundException, IOException, SQLException {
    	if(name.equals("")) {
    		JOptionPane.showMessageDialog(null,"Họ và tên không được để trống");
    		txtTen.requestFocus();
    		return false;
    	}
    	String nameReg = "^[\\p{L} \\.'\\-]+$";
    	if(!name.matches(nameReg)) {
    		JOptionPane.showMessageDialog(null,"Họ tên không hợp lệ");
    		txtTen.requestFocus();
    		return false;
    	}
    	if(tel.equals("")) {
    		JOptionPane.showMessageDialog(null,"Số điện thoại không được để trống");
    		txtSoDienThoai.requestFocus();
    		return false;
    	}
    	if(!staffBUS.checkTel(tel)) {
    		JOptionPane.showMessageDialog(null,"Số điện thoại đã có trong dữ liệu");
    		txtSoDienThoai.requestFocus();
    		return false;
    	}
    	String telReg = "^0[1-9][0-9]{8}$";
    	if(!tel.matches(telReg)) {
    		JOptionPane.showMessageDialog(null,"Số điện thoại không hợp lệ");
    		txtSoDienThoai.requestFocus();
    		return false;
    	}
    	if(address.equals("")) {
    		JOptionPane.showMessageDialog(null,"Địa chỉ không được để trống");
    		txtDiaChi.requestFocus();
    		return false;
    	}
    	if(username.equals("")) {
    		JOptionPane.showMessageDialog(null,"Tên tài khoản không được để trống");
    		txtUsername.requestFocus();
    		return false;
    	}
    	if(password.equals("")) {
    		JOptionPane.showMessageDialog(null,"Mật khẩu không được để trống");
    		txtMatKhau.requestFocus();
    		return false;
    	}
    	String clName=(String) cbChucVu.getSelectedItem();
    	if(clName.trim().toUpperCase().equals("Chức vụ".toUpperCase())) {
    		JOptionPane.showMessageDialog(null,"Vui lòng chọn chức vụ");
    		return false;
    	}
    	return true;
    }
    
    public void addDefaultAD(MyDesign.MyTable tab) throws Exception{
    	tab.setRowCount(0);
        Vector<Account> arr = staffBUS.getAllAD();
        for(int i=0;i<arr.size();i++){
            Account acc=arr.get(i);
            int id=acc.getPersonID();
            String name=acc.getName();
            String tel=acc.getTel();
            String address=acc.getAddress();
            String username=acc.getUsername();
            String role=acc.getRoleID();
            Object row[] = {i+1,id,name,username,role,tel,address};
            tab.addRow(row);
        }
    }
        
        public void addDefaultQL(MyDesign.MyTable tab) throws Exception{
        	tab.setRowCount(0);
            Vector<Account> arr = staffBUS.getAllQL();
            for(int i=0;i<arr.size();i++){
            	 Account acc=arr.get(i);
                 int id=acc.getPersonID();
                 String name=acc.getName();
                 String tel=acc.getTel();
                 String address=acc.getAddress();
                 String username=acc.getUsername();
                 String role=acc.getRoleID();
                 Object row[] = {i+1,id,name,username,role,tel,address};
                 tab.addRow(row);
            }
        }
        
        public void addRole(String role) {
            try {
                model = (DefaultComboBoxModel<String>) cbChucVu.getModel();
                model.removeAllElements();
                model.addElement("Chức vụ");
                if(role.equals("QL")) {
                        model.addAll(staffBUS.getRoleQL());
                }
                if(role.equals("AD")) {
                        model.addAll(staffBUS.getRoleAD());
                }
            } catch (Exception e) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null,e.getMessage());
            }
            cbChucVu.revalidate();
            cbChucVu.repaint();
        }
        
        public void addNewRole() throws Exception {
        	String name="";
        	String nameReg = "^[\\p{L} \\.'\\-]+$";
        	String id="";
        	String idReg = "^[a-zA-Z0-9]{2}$";
        	boolean keyName=false,keyID=false;
        	while(!keyName) {
                    name=JOptionPane.showInputDialog("Nhập tên vai trò mới:");
                    if(name==null) {
                            return;
                    }
                    name=name.trim();
                    if(name.matches(nameReg) && name!="" && roleBUS.addRoleName(name)) {
                            keyName=true;
                            break;
                    }
                    JOptionPane.showMessageDialog(null,"Tên vai trò mới không hợp lệ");
        	}
        	while(!keyID) {
        		id=JOptionPane.showInputDialog("Nhập mã vai trò mới:");
        		if(id==null) {
        			return;
        		}
        		id=id.trim();
        		if(id.matches(idReg) && id!="" && roleBUS.addRoleNameID(id)) {
        			keyID=true;
        			break;
        		}
        		JOptionPane.showMessageDialog(null,"Mã vai trò mới không hợp lệ");
        	}
                role = new Role();
                role.setRoleID(id);
                role.setRoleName(name);
        	JOptionPane.showMessageDialog(null,roleBUS.addBrandNewRole(role));
        }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder_Statistic_Blue1 = new MyDesign.PanelBorder_Statistic_Blue();
        panelBorder_Basic1 = new MyDesign.PanelBorder_Basic();
        jLabel8 = new javax.swing.JLabel();
        txtTen = new MyDesign.MyTextField_Basic();
        jLabel9 = new javax.swing.JLabel();
        txtSoDienThoai = new MyDesign.MyTextField_Basic();
        jLabel10 = new javax.swing.JLabel();
        txtDiaChi = new MyDesign.MyTextField_Basic();
        btnThemNhanVien = new MyDesign.MyButton();
        btnThemNhanVien.setColorOver(new Color(22, 113, 221));
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtUsername = new MyDesign.MyTextField_Basic();
        jLabel12 = new javax.swing.JLabel();
        txtMatKhau = new MyDesign.MyTextField_Basic();
        jLabel13 = new javax.swing.JLabel();
        cbChucVu = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel8.setText("Tên");

        txtTen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel9.setText("Số điện thoại");

        txtSoDienThoai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel10.setText("Địa chỉ");

        txtDiaChi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));

        btnThemNhanVien.setBackground(new java.awt.Color(22, 113, 221));
        btnThemNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        btnThemNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/action-add-white.png"))); // NOI18N
        btnThemNhanVien.setText("Thêm nhân viên");
        btnThemNhanVien.setBorderColor(new java.awt.Color(22, 113, 221));
        btnThemNhanVien.setColor(new java.awt.Color(22, 113, 221));
        btnThemNhanVien.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnThemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanVienActionPerformed(evt);
            }
        });

        jLabel22.setForeground(new java.awt.Color(127, 127, 127));
        jLabel22.setText("Thông tin cá nhân");

        jLabel23.setForeground(new java.awt.Color(127, 127, 127));
        jLabel23.setText("Thông tin tài khoản");

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel11.setText("Username");

        txtUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel12.setText("Mật khẩu");

        txtMatKhau.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel13.setText("Chức vụ");

        cbChucVu.setBackground(new java.awt.Color(246, 250, 255));
        cbChucVu.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cbChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chức vụ" }));
        cbChucVu.setBorder(null);
        cbChucVu.setOpaque(true);
        cbChucVu.setPreferredSize(new java.awt.Dimension(77, 28));

        javax.swing.GroupLayout panelBorder_Basic1Layout = new javax.swing.GroupLayout(panelBorder_Basic1);
        panelBorder_Basic1Layout.setHorizontalGroup(
        	panelBorder_Basic1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        			.addGap(7)
        			.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.TRAILING, false)
        				.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        					.addComponent(jLabel11)
        					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
        				.addComponent(jLabel23)
        				.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        					.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jLabel8)
        						.addComponent(jLabel9)
        						.addComponent(jLabel10))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(txtDiaChi, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(txtSoDienThoai, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(txtTen, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        				.addComponent(jLabel22)
        				.addComponent(btnThemNhanVien, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
        				.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jLabel13)
        						.addComponent(jLabel12))
        					.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
        					.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(cbChucVu, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(txtMatKhau, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder_Basic1Layout.setVerticalGroup(
        	panelBorder_Basic1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        			.addGap(11)
        			.addComponent(jLabel22)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(txtTen, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel8))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(txtSoDienThoai, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel9))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(txtDiaChi, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel10))
        			.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
        			.addComponent(jLabel23)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel11))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(txtMatKhau, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel12))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(cbChucVu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel13))
        			.addGap(18)
        			.addComponent(btnThemNhanVien, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
        			.addGap(19))
        );
        panelBorder_Basic1.setLayout(panelBorder_Basic1Layout);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/staff-white.png"))); // NOI18N
        jLabel4.setText("Thêm nhân viên");

        javax.swing.GroupLayout panelBorder_Statistic_Blue1Layout = new javax.swing.GroupLayout(panelBorder_Statistic_Blue1);
        panelBorder_Statistic_Blue1.setLayout(panelBorder_Statistic_Blue1Layout);
        panelBorder_Statistic_Blue1Layout.setHorizontalGroup(
            panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Blue1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder_Basic1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBorder_Statistic_Blue1Layout.setVerticalGroup(
            panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Blue1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder_Basic1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder_Statistic_Blue1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder_Statistic_Blue1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanVienActionPerformed
        try {
            String name=txtTen.getText().trim();
            String tel=txtSoDienThoai.getText().trim();
            String address=txtDiaChi.getText().trim();
            String username=txtUsername.getText().trim();
            String password=txtMatKhau.getText();
            String role=(String) cbChucVu.getSelectedItem();
        try {
            if(checkDataVal(name,tel,address,username,password)) {
                JOptionPane.showMessageDialog(null,staffBUS.addStaff(new Staff(name,tel,address,personID) ,new Account(username,password,role)));
                addDefaultQL(tab);
                dispose();
            }
        } catch (Exception e1) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(null,e1.getMessage());
        }
        } catch (Exception e1) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(null,e1.getMessage());
        }
    }//GEN-LAST:event_btnThemNhanVienActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private MyDesign.MyButton btnThemNhanVien;
    private javax.swing.JComboBox<String> cbChucVu;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private MyDesign.PanelBorder_Basic panelBorder_Basic1;
    private MyDesign.PanelBorder_Statistic_Blue panelBorder_Statistic_Blue1;
    private MyDesign.MyTextField_Basic txtDiaChi;
    private MyDesign.MyTextField_Basic txtMatKhau;
    private MyDesign.MyTextField_Basic txtSoDienThoai;
    private MyDesign.MyTextField_Basic txtTen;
    private MyDesign.MyTextField_Basic txtUsername;
    // End of variables declaration//GEN-END:variables
}
