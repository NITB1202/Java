package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BUS.PermissionBUS;
import BUS.RoleBUS;
import BUS.RolePermissionBUS;
import BUS.StaffBUS;
import DTO.entities.Account;
import DTO.entities.Permission;
import DTO.entities.Role;
import MyDesign.ScrollBar;
import java.awt.Toolkit;

public class StaffRole_Dialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel permissionsModel;
    private ArrayList<Permission> listPermisson;
    private ArrayList<Role> listRole;
    private RolePermissionBUS rolePermissionBUS;
    private PermissionBUS pbus;
    private RoleBUS roleBUS;
    private StaffBUS staffBUS;
    private Account user;
    private Role newRole;
    javax.swing.JComboBox<String> cbChucVu;
    /**
     * Creates new form StaffRole_Dialog
     * @wbp.parser.constructor
     */
    public StaffRole_Dialog(Account user,javax.swing.JComboBox<String> cbChucVu,java.awt.Frame parent, boolean modal) throws ClassNotFoundException, SQLException, IOException {
        super(parent, modal);
        setTitle("Chức vụ");
        setIconImage(Toolkit.getDefaultToolkit().getImage(StaffRole_Dialog.class.getResource("/Images/logo.png")));
        initComponents();
        this.user = user;
        this.rolePermissionBUS = new RolePermissionBUS();
        this.pbus = new PermissionBUS();
        this.roleBUS = new RoleBUS();
        this.staffBUS=new StaffBUS();
        this.cbChucVu=cbChucVu;
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable1.setVerticalScrollBar(new ScrollBar());
        spTable1.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable1.getViewport().setBackground(Color.WHITE);
        p.setBackground(Color.WHITE);
        spTable1.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        initTable();
    }
    
    public StaffRole_Dialog(Account user,java.awt.Frame parent, boolean modal) throws ClassNotFoundException, SQLException, IOException {
        super(parent, modal);
        initComponents();
        this.user = user;
        this.rolePermissionBUS = new RolePermissionBUS();
        this.pbus = new PermissionBUS();
        this.roleBUS = new RoleBUS();
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable1.setVerticalScrollBar(new ScrollBar());
        spTable1.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable1.getViewport().setBackground(Color.WHITE);
        p.setBackground(Color.WHITE);
        spTable1.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        initTable();
    }
    
    public void addRole(String role) {
            try {
                DefaultComboBoxModel model = (DefaultComboBoxModel<String>) cbChucVu.getModel();
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

    public void initTable(){
        permissionsModel = (DefaultTableModel) tbTinhNang.getModel();
        permissionsModel.setRowCount(0);
        int stt = 1;
        int permissionID;
        String permissionName;
        listPermisson = pbus.getList();
        for (Permission permission : listPermisson){
            permissionName = permission.getPermissionName();
            permissionsModel.addRow(new Object[]{stt++,permissionName, false,false,false,false,false});
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

        panelBorder_Statistic_Blue1 = new MyDesign.PanelBorder_Statistic_Blue();
        panelBorder_Basic1 = new MyDesign.PanelBorder_Basic();
        panelBorder2 = new MyDesign.PanelBorder();
        jLabel6 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTenChucVu = new MyDesign.MyTextField_Basic();
        jLabel9 = new javax.swing.JLabel();
        txtNguoiTao = new javax.swing.JLabel();
        spTable1 = new javax.swing.JScrollPane();
        tbTinhNang = new MyDesign.MyTable();
        btnThemMoi = new MyDesign.MyButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Quyền tài khoản");

        jLabel22.setForeground(new java.awt.Color(127, 127, 127));
        jLabel22.setText("Thông tin chức vụ");

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel8.setText("Tên chức vụ");

        txtTenChucVu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));

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
            tbTinhNang.getColumnModel().getColumn(2).setMinWidth(70);
            tbTinhNang.getColumnModel().getColumn(2).setMaxWidth(70);
            tbTinhNang.getColumnModel().getColumn(3).setMinWidth(70);
            tbTinhNang.getColumnModel().getColumn(3).setMaxWidth(70);
            tbTinhNang.getColumnModel().getColumn(4).setMinWidth(70);
            tbTinhNang.getColumnModel().getColumn(4).setMaxWidth(70);
            tbTinhNang.getColumnModel().getColumn(5).setMinWidth(70);
            tbTinhNang.getColumnModel().getColumn(5).setMaxWidth(70);
            tbTinhNang.getColumnModel().getColumn(6).setMinWidth(70);
            tbTinhNang.getColumnModel().getColumn(6).setMaxWidth(70);
        }

        btnThemMoi.setBackground(new java.awt.Color(22, 113, 221));
        btnThemMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnThemMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/action-add-white.png"))); // NOI18N
        btnThemMoi.setText("Thêm mới");
        btnThemMoi.setBorderColor(new java.awt.Color(22, 113, 221));
        btnThemMoi.setColor(new java.awt.Color(22, 113, 221));
        btnThemMoi.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnThemMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemMoiMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTable1, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnThemMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
                    .addContainerGap()))
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
                .addComponent(spTable1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
            .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                    .addContainerGap(486, Short.MAX_VALUE)
                    .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout panelBorder_Basic1Layout = new javax.swing.GroupLayout(panelBorder_Basic1);
        panelBorder_Basic1.setLayout(panelBorder_Basic1Layout);
        panelBorder_Basic1Layout.setHorizontalGroup(
            panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBorder_Basic1Layout.setVerticalGroup(
            panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/staff-white.png"))); // NOI18N
        jLabel4.setText("Chức vụ");

        javax.swing.GroupLayout panelBorder_Statistic_Blue1Layout = new javax.swing.GroupLayout(panelBorder_Statistic_Blue1);
        panelBorder_Statistic_Blue1.setLayout(panelBorder_Statistic_Blue1Layout);
        panelBorder_Statistic_Blue1Layout.setHorizontalGroup(
            panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Blue1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder_Basic1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelBorder_Statistic_Blue1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelBorder_Statistic_Blue1Layout.setVerticalGroup(
            panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Blue1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(panelBorder_Basic1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelBorder_Statistic_Blue1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(552, Short.MAX_VALUE)))
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

    private void btnThemMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMoiMouseClicked
        try {
            String newRoleName = txtTenChucVu.getText();
            if(newRoleName.isEmpty()){
                JOptionPane.showMessageDialog(this,"Bạn cần nhập tên Role cần thêm mới", "Thông báo lỗi", JOptionPane.INFORMATION_MESSAGE);
            }else{
                int rowCount = tbTinhNang.getRowCount();
                int[] columnIndices = {2, 3, 4, 5, 6}; // Chỉ mục của các cột cần lấy giá trị
                List<List<Object>> dataList = new ArrayList<>();
                for (int row = 0; row < rowCount; row++) {
                    List<Object> rowData = new ArrayList<>();
                    for (int columnIndex : columnIndices) {
                        Object cellValue = tbTinhNang.getValueAt(row, columnIndex);
                        rowData.add(cellValue);
                    }
                    dataList.add(rowData);

                }
                roleBUS = new RoleBUS();
                String ID  = Role.generateID();
                listRole = roleBUS.getList();
                // Kiểm tra xem ID có trùng lặp không
                boolean isDuplicateID = false;
                boolean isDuplicateName = false;
                for (Role role : listRole) {
                    if (role.getRoleID().equals(ID)) {
                        isDuplicateID = true;
                        break;
                    }
                    if(role.getRoleName().equals(newRoleName)){
                        isDuplicateName = true;
                        break;
                    }
                }
                // Nếu ID trùng lặp, tiếp tục tạo mới ID
                while (isDuplicateID) {
                    ID = Role.generateID();
                    isDuplicateID = false;
                    for (Role role : listRole) {
                        if (role.getRoleID().equals(ID)) {
                            isDuplicateID = true;
                            break;
                        }
                    }
                }
                if(isDuplicateName){
                    JOptionPane.showMessageDialog(this,"Tên chức vụ trùng lặp", "Thông báo lỗi", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    newRole = new Role(ID, newRoleName,1);
                    roleBUS.addBrandNewRole(newRole);
                    rolePermissionBUS.savePermissions(dataList, ID);
                    this.setVisible(false);
                    if(cbChucVu!=null){
                        addRole(user.getRoleID());
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StaffRole_Dialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StaffRole_Dialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StaffRole_Dialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(StaffRole_Dialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnThemMoiMouseClicked

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private MyDesign.MyButton btnThemMoi;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private MyDesign.PanelBorder panelBorder2;
    private MyDesign.PanelBorder_Basic panelBorder_Basic1;
    private MyDesign.PanelBorder_Statistic_Blue panelBorder_Statistic_Blue1;
    private javax.swing.JScrollPane spTable1;
    private MyDesign.MyTable tbTinhNang;
    private javax.swing.JLabel txtNguoiTao;
    private MyDesign.MyTextField_Basic txtTenChucVu;
    // End of variables declaration//GEN-END:variables
}
