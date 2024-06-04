package GUI;

import java.awt.Color;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import BUS.PermissionBUS;
import BUS.RoleBUS;
import BUS.RolePermissionBUS;
import DTO.entities.Account;
import DTO.entities.Permission;
import DTO.entities.Role;
import DTO.entities.RolePermission;
import MyDesign.ScrollBar;

public class Admin_GUI extends JPanel {

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
    public void styles() {
        // Configuring spTable
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p1 = new JPanel();
        p1.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p1);

        // Configuring spTable1
        spTable1.setVerticalScrollBar(new ScrollBar());
        spTable1.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable1.getViewport().setBackground(Color.WHITE);
        JPanel p2 = new JPanel();
        p2.setBackground(Color.WHITE);
        spTable1.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p2);

        // Checking and setting permissions for editing
        if (rolePermissionBUS.hasPerEdit(this.user.getRoleID(), 9)) {
            btnCapNhat.setEnabled(false);
        } else {
            btnCapNhat.setEnabled(true);
        }

        // Checking and setting permissions for deleting
        if (rolePermissionBUS.hasPerDelete(this.user.getRoleID(), 9)) {
            btnXoaChucVu.setEnabled(false);
        } else {
            btnXoaChucVu.setEnabled(true);
        }
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
        panelBorder1 = new MyDesign.PanelBorder();
        jLabel5 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        tbChucVu = new MyDesign.MyTable();
        btnAddRole = new MyDesign.MyButton();
        panelBorder2 = new MyDesign.PanelBorder();
        jLabel6 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTenChucVu = new MyDesign.MyTextField_Basic();
        jLabel9 = new javax.swing.JLabel();
        txtNguoiTao = new javax.swing.JLabel();
        spTable1 = new javax.swing.JScrollPane();
        tbTinhNang = new MyDesign.MyTable();
        btnXoaChucVu = new MyDesign.MyButton();
        btnCapNhat = new MyDesign.MyButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(127, 127, 127));
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

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(7, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddRole, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddRole, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

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

        btnXoaChucVu.setBackground(new java.awt.Color(255, 241, 241));
        btnXoaChucVu.setForeground(new java.awt.Color(248, 67, 67));
        btnXoaChucVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/action-delete-white.png"))); // NOI18N
        btnXoaChucVu.setText("Xóa chức vụ");
        btnXoaChucVu.setBorderColor(new java.awt.Color(255, 241, 241));
        btnXoaChucVu.setColor(new java.awt.Color(255, 241, 241));
        btnXoaChucVu.setColorOver(new java.awt.Color(255, 241, 241));
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

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel6)
                            .addGroup(panelBorder2Layout.createSequentialGroup()
                                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNguoiTao)
                                    .addComponent(txtTenChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spTable1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                            .addGroup(panelBorder2Layout.createSequentialGroup()
                                .addComponent(btnXoaChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtNguoiTao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spTable1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelBorder_Basic1Layout = new javax.swing.GroupLayout(panelBorder_Basic1);
        panelBorder_Basic1.setLayout(panelBorder_Basic1Layout);
        panelBorder_Basic1Layout.setHorizontalGroup(
            panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBorder_Basic1Layout.setVerticalGroup(
            panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder_Basic1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder_Basic1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
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
            StaffRole_Dialog srd = new StaffRole_Dialog(this.user,new javax.swing.JFrame(), true);
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
        if(evt.getClickCount()==1 || evt.getClickCount()==2){
            int selectedRow = tbChucVu.getSelectedRow();
            if (selectedRow != -1) {
                String role = tbChucVu.getValueAt(selectedRow, 1).toString();
                int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa chức vụ " + role + "?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    try {
                        roleBUS = new RoleBUS();
                        boolean check = roleBUS.deleteRoleByID(role);
                        if(check == true){
                            JOptionPane.showMessageDialog(panelBorder1, "Đã xóa thành công", "Xác nhận xóa", HEIGHT);
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
    private MyDesign.PanelBorder panelBorder1;
    private MyDesign.PanelBorder panelBorder2;
    private MyDesign.PanelBorder_Basic panelBorder_Basic1;
    private javax.swing.JScrollPane spTable;
    private javax.swing.JScrollPane spTable1;
    private MyDesign.MyTable tbChucVu;
    private MyDesign.MyTable tbTinhNang;
    private javax.swing.JLabel txtNguoiTao;
    private MyDesign.MyTextField_Basic txtTenChucVu;
    // End of variables declaration//GEN-END:variables

}
