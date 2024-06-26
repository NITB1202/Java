/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import BUS.RolePermissionBUS;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import BUS.StaffBUS;
import DTO.entities.Account;
import DTO.entities.Reader;
import DTO.entities.Staff;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.GroupLayout;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author QUANG DIEN
 */
public class StaffInfor_Dialog extends javax.swing.JDialog {
	StaffBUS sBLL;
	Account a;
        Staff s;
        private RolePermissionBUS rolePermissionBUS;
        int id;
        MyDesign.MyTable tab;
        String roleID;
        int userID;
    /**
     * Creates new form StaffAdd_Dialog
     */
    public StaffInfor_Dialog(java.awt.Frame parent, boolean modal,int id,MyDesign.MyTable tab,String roleID,int userID) throws IOException {
        super(parent, modal);
        setIconImage(Toolkit.getDefaultToolkit().getImage(StaffInfor_Dialog.class.getResource("/Images/logo.png")));
        setResizable(false);
        setTitle("Chi tiết nhân viên");
        try {
            sBLL =new StaffBUS();
            this.rolePermissionBUS = new RolePermissionBUS();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        this.id = id;
        this.tab = tab;
        this.roleID = roleID;
        this.userID = userID;
        initComponents();
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) cbChucVu.getModel();
        try {
            if(roleID.equals("QL")) {
                    model.addAll(sBLL.getRoleAD());
            }
            if(roleID.equals("AD")) {
                    model.addAll(sBLL.getRoleAll());
        }
        upData(id);
        } catch (Exception e) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
    
    public void addDefault(MyDesign.MyTable tab,String roleID) throws Exception{
    	tab.setRowCount(0);
        if(roleID.equals("AD")) {
        	addDefaultAD(tab);
        }
        if(roleID.equals("QL")) {
        	addDefaultQL(tab);
        }
    }
    
    public void addDefaultAD(MyDesign.MyTable tab) throws Exception{
        Vector<Account> arr=sBLL.getAllAD();
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
        Vector<Account> arr=sBLL.getAllQL();
        for(int i=0;i<arr.size();i++){
            Account acc = arr.get(i);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    public void upData(int id) {
    	try {
            a =sBLL.findbyID(id);
            s = sBLL.findStaffbyID(id);
            txtTen.setText(a.getName());
            txtSoDienThoai.setText(a.getTel());
            txtDiaChi.setText(a.getAddress());
            txtUsername.setText(a.getUsername());
            cbChucVu.setSelectedItem(sBLL.getRole(a.getRoleID()));
            if(rolePermissionBUS.hasPerEdit(a.getRoleID(), 7))
                btnSuaThongTin.setEnabled(true);
            else btnSuaThongTin.setEnabled(false);
            
            if(!rolePermissionBUS.hasPerDelete(a.getRoleID(), 7))
                btnXoaNhanVien.setEnabled(false);
            
            
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
        }
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
    	if(!sBLL.checkTelExcept(tel,a.getPersonID())) {
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
    	String clName=(String) cbChucVu.getSelectedItem();
    	if(clName.trim().toUpperCase().equals("Chức vụ".toUpperCase())) {
    		JOptionPane.showMessageDialog(null,"Vui lòng chọn chức vụ");
    		return false;
    	}
    	return true;
    }
    
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
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtUsername = new MyDesign.MyTextField_Basic();
        jLabel12 = new javax.swing.JLabel();
        txtMatKhau = new MyDesign.MyTextField_Basic();
        jLabel13 = new javax.swing.JLabel();
        cbChucVu = new javax.swing.JComboBox<>();
        btnSuaThongTin = new MyDesign.MyButton();
        btnSuaThongTin.setColorOver(new Color(22, 113, 221));
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

        btnSuaThongTin.setBackground(new java.awt.Color(22, 113, 221));
        btnSuaThongTin.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaThongTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/action-refresh-white.png"))); // NOI18N
        btnSuaThongTin.setText("Sửa thông tin");
        btnSuaThongTin.setBorderColor(new java.awt.Color(22, 113, 221));
        btnSuaThongTin.setColor(new java.awt.Color(22, 113, 221));
        btnSuaThongTin.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnSuaThongTin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaThongTinMouseClicked(evt);
            }
        });
        
        btnXoaNhanVien = new MyDesign.MyButton();
        btnXoaNhanVien.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		xoaNhanVien();
        	}
        });
        
        btnXoaNhanVien.setColorClick(new Color(255, 128, 128));
        btnXoaNhanVien.setColorOver(new Color(255, 204, 204));
        btnXoaNhanVien.setColor(new Color(255, 204, 204));
        btnXoaNhanVien.setBorderColor(new Color(255, 204, 204));
        btnXoaNhanVien.setBackground(new Color(255, 204, 204));
        btnXoaNhanVien.setIcon(new ImageIcon(StaffInfor_Dialog.class.getResource("/Images/action-delete-white.png")));
        btnXoaNhanVien.setForeground(new Color(248, 67, 67));
        btnXoaNhanVien.setText("Xóa nhân viên");
        btnXoaNhanVien.setFont(new Font("SansSerif", Font.BOLD, 14));

        javax.swing.GroupLayout panelBorder_Basic1Layout = new javax.swing.GroupLayout(panelBorder_Basic1);
        panelBorder_Basic1Layout.setHorizontalGroup(
        	panelBorder_Basic1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        			.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        					.addGap(7)
        					.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jLabel23)
        						.addComponent(jLabel22)
        						.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        							.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(jLabel8)
        								.addComponent(jLabel9)
        								.addComponent(jLabel10)
        								.addComponent(jLabel11)
        								.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        									.addPreferredGap(ComponentPlacement.RELATED)
        									.addComponent(jLabel13))
        								.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        									.addPreferredGap(ComponentPlacement.RELATED)
        									.addComponent(jLabel12)))
        							.addGap(27)
        							.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(txtUsername, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
        								.addComponent(txtSoDienThoai, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
        								.addComponent(txtDiaChi, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
        								.addComponent(txtTen, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
        								.addComponent(cbChucVu, 0, 209, Short.MAX_VALUE)
        								.addComponent(txtMatKhau, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)))))
        				.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(btnXoaNhanVien, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(btnSuaThongTin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap())
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
        			.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(cbChucVu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel13))
        			.addGap(18)
        			.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnSuaThongTin, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnXoaNhanVien, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
        			.addGap(19))
        );
        panelBorder_Basic1.setLayout(panelBorder_Basic1Layout);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/staff-white.png"))); // NOI18N
        jLabel4.setText("Thông tin chi tiết");

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

    protected void xoaNhanVien() {
    	try {
	        if (!a.getRoleID().equals(roleID)) {
	        	 int diaRS=JOptionPane.showOptionDialog(null, "Bạn có chắc muốn xóa nhân viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION, 
 	            		JOptionPane.QUESTION_MESSAGE, null,null,null);
	            if (diaRS == JOptionPane.YES_OPTION) {
	                JOptionPane.showMessageDialog(null, sBLL.eraShowStaff(s), "Xóa thành công", JOptionPane.INFORMATION_MESSAGE);
	                addDefault(tab, roleID);
	                dispose();
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "Bạn không có quyền xoá nhân viên này");
	        }
	    } catch (Exception e1) {
	        JOptionPane.showMessageDialog(null, e1.getMessage());
	    }
	}

	private void btnSuaThongTinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaThongTinMouseClicked
        String name=txtTen.getText().trim();
        String tel=txtSoDienThoai.getText().trim();
        String address=txtDiaChi.getText().trim();
        String username=txtUsername.getText().trim();
        String password=txtMatKhau.getText();
        try {
            String role=sBLL.getRoleID((String) cbChucVu.getSelectedItem());
            System.out.println(name+" "+tel+" "+address+" "+username+" "+password+" "+role);
            System.out.println(s.getName()+" "+s.getTel()+" "+s.getAddress()+" "+a.getUsername()+" "+a.getPwd()+" "+a.getRoleID());  ;
            if(!(s.compare(name, tel, address) && a.compare(username, password, role))) {
                    if(!a.getRoleID().equals(roleID) || (a.getRoleID().equals(roleID) && a.getPersonID()==userID)) {
        	            int diaRS=JOptionPane.showOptionDialog(null, "Bạn có chắc muốn sửa nhân viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION, 
        	            		JOptionPane.QUESTION_MESSAGE, null,null,null);
                            if(diaRS==JOptionPane.YES_OPTION){
                                    if(checkDataVal(name,tel,address,username,password)) {
                                            s.setName(name);
                                            s.setTel(tel);
                                            s.setAddress(address);
                                            a.setUsername(username);
                                            a.setPwd(password);
                                            a.setRoleID(role);
                                            JOptionPane.showMessageDialog(null,sBLL.updateOneStaff(s,a));
                                            addDefault(tab,roleID);
                                            dispose();
                                    }
                            }
                    }else {
                            JOptionPane.showMessageDialog(null,"Bạn không có quyền sửa nhân viên này");
                    }
            }else{
                JOptionPane.showMessageDialog(null,"Dữ liệu không thay đổi");
            }
        } catch (Exception e1) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(null,e1.getMessage());
        }
        	 
    }

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
            java.util.logging.Logger.getLogger(StaffInfor_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StaffInfor_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StaffInfor_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StaffInfor_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                StaffInfor_Dialog dialog = new StaffInfor_Dialog(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private MyDesign.MyButton btnSuaThongTin;
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
    private MyDesign.MyButton btnXoaNhanVien;
}