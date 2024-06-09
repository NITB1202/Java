/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.PermissionBUS;
import BUS.RoleBUS;
import BUS.RolePermissionBUS;
import DTO.entities.Account;
import DTO.entities.Permission;
import DTO.entities.Role;
import DTO.entities.RolePermission;
import MyDesign.ScrollBar;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 *
 * @author QUANG DIEN
 */
public class Admin_GUI extends javax.swing.JPanel {
    private Role role;
    private Account user;
    private RoleBUS roleBUS;
    private Permission perrmission;
    private PermissionBUS permissionBUS;
    private RolePermission rolePermission;
    private RolePermissionBUS rolePermissionBUS;
    private ArrayList<Role> listRole;
    
    
    private ArrayList<Permission> listPermisson;    
    private ArrayList<RolePermission> listRolePer;

    private DefaultTableModel rolesModel;    
    private DefaultTableModel permissionsModel;
    private String roleIDSelected = null;

    /**
     * Creates new form Admin_GUI
     */
    public Admin_GUI(Account user) throws ClassNotFoundException, SQLException, IOException, NoSuchAlgorithmException {
        this.user = user;
        this.permissionBUS = new PermissionBUS();
        this.rolePermissionBUS = new RolePermissionBUS();
        initComponents();
        styles();
        initTableRoles();
    }
    public void styles(){
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        
        spTable1.setVerticalScrollBar(new ScrollBar());
        spTable1.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable1.getViewport().setBackground(Color.WHITE);
        
//        if(rolePermissionBUS.hasPerEdit(this.user.getRoleID(), 9)){
//            btnCapNhat.setEnabled(false);
//        }
//        else btnCapNhat.setEnabled(true);
//        if(rolePermissionBUS.hasPerDelete(this.user.getRoleID(), 9)){
//            btnXoaChucVu.setEnabled(false);
//        }
//        else btnXoaChucVu.setEnabled(true);
        
    }
    public void initTableRoles() throws ClassNotFoundException, SQLException, IOException, NoSuchAlgorithmException{
        listRole = null;
        RoleBUS roleBUS = new RoleBUS();
        listRole = roleBUS.getList();
        rolesModel = (DefaultTableModel) tbChucVu.getModel();
        rolesModel.setRowCount(0);
        int stt = 1;
        String roleName;
        for (Role role : listRole){
            if(role.getIsDeleted() == 1){
                roleName = role.getRoleName();
                rolesModel.addRow(new Object[]{stt++,roleName});
            }
        }
        if (!listRole.isEmpty()) {
            Role firstRole = listRole.get(0);
            String roleID = firstRole.getRoleID();
            initTablePermission(roleID); 
        }
    }
    public void initTablePermission(String roleID) throws ClassNotFoundException, SQLException, IOException, NoSuchAlgorithmException{
        RolePermissionBUS rolePer = new RolePermissionBUS();
        listPermisson = permissionBUS.getList();
        permissionsModel = (DefaultTableModel) tbTinhNang.getModel();
        ArrayList<Integer> listPer = new ArrayList<>();
        listRolePer = rolePer.canAccessForm(roleID);
        for (Role role : listRole){
            if(role.getRoleID().equals(roleID)) 
                txtTenChucVu.setText(role.getRoleName());
        }
        txtTenChucVu.setEnabled(false);
        permissionsModel.setRowCount(0);
        
        if(roleID.equals("AD")){
            btnCapNhat.setVisible(false);
            btnXoaChucVu.setVisible(false);
        }
        else{
            btnCapNhat.setVisible(true);
            btnXoaChucVu.setVisible(true);
        }
        int stt = 1;
        int permissionID;
        String permissionName;
        for (Permission permission : listPermisson){
            permissionID = permission.getPermissionID();
            permissionName = permission.getPermissionName();
            if(permissionName.equals("Quản Lý Kho"))
                permissionName = "Quản Lý Phiếu Nhập";
            if(permissionID == 10 ){
                continue;
            }
            listPer  = rolePermissionBUS.hasPermission(permissionID,listRolePer);
            boolean A = (listPer.get(0) == 1);            
            boolean C = (listPer.get(1) == 1);
            boolean R = (listPer.get(2) == 1);
            boolean U = (listPer.get(3) == 1);
            boolean D = (listPer.get(4) == 1);
            permissionsModel.addRow(new Object[]{stt++,permissionName, A,C,R,U,D});
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

        panelBorder_Basic1 = new MyDesign.PanelBorder_Basic();
        panelBorder_Basic1.setBounds(10, 11, 682, 611);
        panelBorder1 = new JPanel();
        panelBorder1.setBounds(10, 11, 224, 600);
        panelBorder1.setBackground(new Color(255, 255, 255));
        jLabel5 = new javax.swing.JLabel();
        jLabel5.setBounds(54, 11, 128, 21);
        spTable = new javax.swing.JScrollPane();
        spTable.setBounds(10, 43, 204, 480);
        tbChucVu = new MyDesign.MyTable();
        btnAddRole = new MyDesign.MyButton();
        btnAddRole.setBounds(10, 540, 204, 40);
        btnAddRole.setColorOver(new Color(22, 113, 221));
        panelBorder2 = new JPanel();
        panelBorder2.setBounds(240, 11, 432, 600);
        panelBorder2.setBackground(new Color(255, 255, 255));
        jLabel6 = new javax.swing.JLabel();
        jLabel6.setBounds(14, 11, 124, 21);
        jLabel22 = new javax.swing.JLabel();
        jLabel22.setBounds(14, 40, 110, 15);
        jLabel22.setFont(new Font("SansSerif", Font.PLAIN, 12));
        jLabel8 = new javax.swing.JLabel();
        jLabel8.setBounds(14, 66, 87, 19);
        txtTenChucVu = new MyDesign.MyTextField_Basic();
        txtTenChucVu.setBounds(119, 62, 161, 29);
        txtTenChucVu.setForeground(new Color(0, 0, 0));
        txtTenChucVu.setFont(new Font("SansSerif", Font.BOLD, 13));
        jLabel9 = new javax.swing.JLabel();
        jLabel9.setBounds(14, 97, 69, 19);
        txtNguoiTao = new javax.swing.JLabel();
        txtNguoiTao.setBounds(119, 97, 81, 19);
        spTable1 = new javax.swing.JScrollPane();
        spTable1.setBounds(10, 127, 412, 399);
        tbTinhNang = new MyDesign.MyTable();
        btnXoaChucVu = new MyDesign.MyButton();
        btnXoaChucVu.setBounds(14, 537, 186, 40);
        btnXoaChucVu.setColorClick(new Color(255, 128, 128));
        btnCapNhat = new MyDesign.MyButton();
        btnCapNhat.setColorOver(new Color(22, 113, 221));
        btnCapNhat.setBounds(252, 537, 170, 40);

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new Font("SansSerif", Font.BOLD, 16)); // NOI18N
        jLabel5.setForeground(new Color(0, 0, 0));
        jLabel5.setText("Tất cả chức vụ");

        spTable.setBorder(null);

        tbChucVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Chức vụ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbChucVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbChucVuMouseClicked(evt);
            }
        });
        spTable.setViewportView(tbChucVu);
        if (tbChucVu.getColumnModel().getColumnCount() > 0) {
            tbChucVu.getColumnModel().getColumn(0).setMinWidth(50);
            tbChucVu.getColumnModel().getColumn(0).setMaxWidth(70);
        }

        btnAddRole.setBackground(new java.awt.Color(22, 113, 221));
        btnAddRole.setForeground(new java.awt.Color(255, 255, 255));
        btnAddRole.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/action-refresh-white.png"))); // NOI18N
        btnAddRole.setText("Thêm chức vụ");
        btnAddRole.setBorderColor(new java.awt.Color(22, 113, 221));
        btnAddRole.setColor(new java.awt.Color(22, 113, 221));
        btnAddRole.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnAddRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRoleActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Quyền tài khoản");

        jLabel22.setForeground(new java.awt.Color(127, 127, 127));
        jLabel22.setText("Thông tin chức vụ");

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel8.setText("Tên chức vụ");

        txtTenChucVu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));
        txtTenChucVu.setText("Thủ kho");

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel9.setText("Người tạo");

        txtNguoiTao.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtNguoiTao.setText("Quản trị viên");

        spTable1.setBorder(null);

        tbTinhNang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tính năng", "Truy cập", "Tạo", "Xem", "Sửa", "Xóa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable1.setViewportView(tbTinhNang);
        if (tbTinhNang.getColumnModel().getColumnCount() > 0) {
            tbTinhNang.getColumnModel().getColumn(0).setMinWidth(40);
            tbTinhNang.getColumnModel().getColumn(0).setMaxWidth(50);
            tbTinhNang.getColumnModel().getColumn(2).setMinWidth(60);
            tbTinhNang.getColumnModel().getColumn(2).setMaxWidth(60);
            tbTinhNang.getColumnModel().getColumn(3).setMinWidth(40);
            tbTinhNang.getColumnModel().getColumn(3).setMaxWidth(50);
            tbTinhNang.getColumnModel().getColumn(4).setMinWidth(40);
            tbTinhNang.getColumnModel().getColumn(4).setMaxWidth(50);
            tbTinhNang.getColumnModel().getColumn(5).setMinWidth(40);
            tbTinhNang.getColumnModel().getColumn(5).setMaxWidth(50);
            tbTinhNang.getColumnModel().getColumn(6).setMinWidth(40);
            tbTinhNang.getColumnModel().getColumn(6).setMaxWidth(50);
        }

        btnXoaChucVu.setBackground(new Color(255, 204, 204));
        btnXoaChucVu.setForeground(new java.awt.Color(248, 67, 67));
        btnXoaChucVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/action-delete-white.png"))); // NOI18N
        btnXoaChucVu.setText("Xóa chức vụ");
        btnXoaChucVu.setBorderColor(new Color(255, 204, 204));
        btnXoaChucVu.setColor(new Color(255, 204, 204));
        btnXoaChucVu.setColorOver(new Color(255, 204, 204));
        btnXoaChucVu.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnXoaChucVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaChucVuMouseClicked(evt);
            }
        });

        btnCapNhat.setBackground(new java.awt.Color(22, 113, 221));
        btnCapNhat.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/action-refresh-white.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.setBorderColor(new java.awt.Color(22, 113, 221));
        btnCapNhat.setColor(new java.awt.Color(22, 113, 221));
        btnCapNhat.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnCapNhat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCapNhatMouseClicked(evt);
            }
        });
        setLayout(null);
        panelBorder2.setLayout(null);
        panelBorder2.add(jLabel22);
        panelBorder2.add(jLabel6);
        panelBorder2.add(jLabel8);
        panelBorder2.add(jLabel9);
        panelBorder2.add(txtNguoiTao);
        panelBorder2.add(txtTenChucVu);
        panelBorder2.add(spTable1);
        panelBorder2.add(btnXoaChucVu);
        panelBorder2.add(btnCapNhat);
        panelBorder_Basic1.setLayout(null);
        panelBorder_Basic1.add(panelBorder1);
        panelBorder1.setLayout(null);
        panelBorder1.add(spTable);
        panelBorder1.add(jLabel5);
        panelBorder1.add(btnAddRole);
        panelBorder_Basic1.add(panelBorder2);
        add(panelBorder_Basic1);
    }// </editor-fold>//GEN-END:initComponents

    private void tbChucVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbChucVuMouseClicked
        int selectedRow = tbChucVu.getSelectedRow();
        System.out.println("Role selected: "+ selectedRow);
        if (selectedRow != -1) {
            System.out.println("Check");
            String value = (String) tbChucVu.getValueAt(selectedRow, 1); // Replace 0 with the desired column index
            for (Role role : listRole){
                if(role.getRoleName().equals(value)){
                    value = role.getRoleID();
                    break;
                }
            }
            try {
                initTablePermission(value);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Admin_GUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Admin_GUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Admin_GUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Admin_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tbChucVuMouseClicked

    private void btnAddRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRoleActionPerformed
        try {
            StaffRole_Dialog srd = new StaffRole_Dialog(this.user,new javax.swing.JFrame(), true, this);
            srd.setVisible(true);
            srd.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                try {
                    initTableRoles();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Admin_GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Admin_GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Admin_GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(Admin_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                }
            });
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Admin_GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Admin_GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Admin_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddRoleActionPerformed

    private void btnXoaChucVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaChucVuMouseClicked
    	int selectedRow = tbChucVu.getSelectedRow();
        if (selectedRow != -1) {
            String role = tbChucVu.getValueAt(selectedRow, 1).toString();
            int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa chức vụ " + role + "?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                try {
                    roleBUS = new RoleBUS();
                    boolean check = roleBUS.deleteRoleByID(role);
                    if(check == true){
                        JOptionPane.showMessageDialog(null, "Đã xóa thành công", "Xác nhận xóa", JOptionPane.INFORMATION_MESSAGE);
                        DefaultTableModel model = (DefaultTableModel) tbChucVu.getModel();
                        model.setRowCount(0);
                        initTableRoles();
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(More_GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(More_GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Admin_GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(Admin_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnXoaChucVuMouseClicked

    private void btnCapNhatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCapNhatMouseClicked
        int rowCount = tbTinhNang.getRowCount();
        int columnCount = tbTinhNang.getColumnCount()-1;
        List<List<Object>> dataList = new ArrayList<>();
        for (int row = 0; row < rowCount; row++) {
            System.out.println(row);
                List<Object> rowData = new ArrayList<>();
                for (int column = 2; column <= columnCount; column++) {
                    Object cellValue = tbTinhNang.getValueAt(row, column);
                    rowData.add(cellValue);
                }
                dataList.add(rowData);
                System.out.println(rowData);
        }
        try {
            rolePermissionBUS.updateRolePermissions(dataList, txtTenChucVu.getText());
            DefaultTableModel model = (DefaultTableModel) tbChucVu.getModel();
            model.setRowCount(0);
            initTableRoles();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Admin_GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Admin_GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Admin_GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Admin_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCapNhatMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private MyDesign.MyButton btnAddRole;
    private MyDesign.MyButton btnCapNhat;
    private MyDesign.MyButton btnXoaChucVu;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private JPanel panelBorder1;
    private JPanel panelBorder2;
    private MyDesign.PanelBorder_Basic panelBorder_Basic1;
    private javax.swing.JScrollPane spTable;
    private javax.swing.JScrollPane spTable1;
    private MyDesign.MyTable tbChucVu;
    private MyDesign.MyTable tbTinhNang;
    private javax.swing.JLabel txtNguoiTao;
    private MyDesign.MyTextField_Basic txtTenChucVu;
    // End of variables declaration//GEN-END:variables
}
