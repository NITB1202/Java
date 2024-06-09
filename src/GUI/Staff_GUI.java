package GUI;

import java.awt.Color;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import BUS.RoleBUS;
import BUS.RolePermissionBUS;
import BUS.StaffBUS;
import DTO.entities.Account;
import DTO.entities.Role;
import DTO.entities.Staff;
import MyDesign.ScrollBar;
import javax.swing.JList;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Staff_GUI extends JPanel {

	private static final long serialVersionUID = 1L;

	private StaffBUS staffBUS;
    int userID;
    String roleID;
    private Account userLogin;
    private RolePermissionBUS rolePermissionBUS;
    private Vector<Account> staffList;
    /**
     * Creates new form Staff_GUI
     */

    public Staff_GUI(Account userLogin) throws Exception {
        initComponents();
        this.userLogin = userLogin;
        this.userID = userLogin.getPersonID();
        this.roleID = userLogin.getRoleID();
        this.rolePermissionBUS = new RolePermissionBUS();
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        try{
            staffBUS =new StaffBUS();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        if(this.roleID.equals("AD")) {
            addDefaultAD();
        }
        if(this.roleID.equals("QL")) {
                addDefaultQL();
        }
        setLayout(null);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        panelBorder1.setLayout(null);
        panelBorder1.add(jLabel5);
        panelBorder1.add(CbFilter);
        panelBorder1.add(panelBorder_Basic1);
        panelBorder_Basic1.setLayout(null);
        panelBorder_Basic1.add(txtTimKiem);
        panelBorder_Basic1.add(jLabel8);
        panelBorder1.add(spTable);
        panelBorder1.add(btnNhanVienMoi);
        add(panelBorder1);
        SelectedRole = "Tất cả";
        loadRole(userLogin.getRoleID());
        if(rolePermissionBUS.hasPerCreate(roleID, 8))
            btnNhanVienMoi.setEnabled(true);
        else  btnNhanVienMoi.setEnabled(false);
        
    }
    
    public void addDefaultAD() throws Exception{
        staffList = staffBUS.getAllAD();
        System.out.println(staffList);
        for(int i=0;i<staffList.size();i++){
            Account acc = staffList.get(i);
            int id=acc.getPersonID();
            String name=acc.getName();
            String tel=acc.getTel();
            String address=acc.getAddress();
            String username=acc.getUsername();
            String role=acc.getRoleID();
            Object row[] = {i+1,id,name,username,role,tel,address};
            tbDanhSachNhanVien.addRow(row);
        }
    }
    
    public void addDefaultQL() throws Exception{
    	staffList = staffBUS.getAllQL();
        System.out.println(staffList);
        for(int i=0;i<staffList.size();i++){
            Account acc = staffList.get(i);
            int id=acc.getPersonID();
            String name=acc.getName();
            String tel=acc.getTel();
            String address=acc.getAddress();
            String username=acc.getUsername();
            String role=acc.getRoleID();
            Object row[] = {i+1,id,name,username,role,tel,address};
            tbDanhSachNhanVien.addRow(row);}
    }
    
    public void findVal() throws Exception 
    {
    	if(this.roleID.equals("AD")) {
            staffList = staffBUS.getAllAD();
        }
        if(this.roleID.equals("QL")) {
        	staffList = staffBUS.getAllQL();
        }
    	
		String searchText = txtTimKiem.getText().toLowerCase();
		//Clear table
		DefaultTableModel model = (DefaultTableModel) tbDanhSachNhanVien.getModel();
		model.setRowCount(0);
		
		
		if(!searchText.isBlank())
		{
			for(int i = 0; i < staffList.size(); i++)
			{
				Account acc = staffList.get(i);
				String name = acc.getName().toLowerCase();
                String role = acc.getRoleID();
                if(name.equals("mị"))
                	System.out.println(role);
				
				if(name.contains(searchText) && acc.getRoleID().equals(role))
				{
		            int id=acc.getPersonID();
		            String tel=acc.getTel();
		            String address=acc.getAddress();
		            String username=acc.getUsername();
		            Object row[] = {i+1,id,acc.getName(),username,role,tel,address};
		            tbDanhSachNhanVien.addRow(row);
				}
			}
		}
		else
			filterStaffByRole(roleID, SelectedRole);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new JPanel();
        panelBorder1.setBackground(new Color(255, 255, 255));
        panelBorder1.setBounds(10, 11, 839, 608);
        jLabel5 = new javax.swing.JLabel();
        jLabel5.setBounds(30, 46, 225, 21);
        spTable = new javax.swing.JScrollPane();
        spTable.setBounds(30, 92, 743, 411);
        tbDanhSachNhanVien = new MyDesign.MyTable();
        panelBorder_Basic1 = new JPanel();
        panelBorder_Basic1.setBorder(new LineBorder(new Color(128, 128, 128)));
        panelBorder_Basic1.setBackground(new Color(255, 255, 255));
        panelBorder_Basic1.setBounds(533, 32, 240, 35);
        jLabel8 = new javax.swing.JLabel();
        jLabel8.setBounds(206, 5, 24, 24);
        txtTimKiem = new MyDesign.SearchText();
        txtTimKiem.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent e) {
        		try {
					findVal();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        txtTimKiem.setHint("Tìm tên nhân viên...");
        txtTimKiem.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtTimKiem.setBounds(10, 5, 192, 24);
        btnNhanVienMoi = new MyDesign.MyButton();
        btnNhanVienMoi.setBounds(600, 524, 173, 45);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new Font("Segoe UI", Font.BOLD, 20)); // NOI18N
        jLabel5.setForeground(new Color(0, 0, 0));
        jLabel5.setText("Danh sách nhân viên");

        spTable.setBorder(null);

        tbDanhSachNhanVien.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"STT", "M\u00E3 nh\u00E2n vi\u00EAn", "T\u00EAn nh\u00E2n vi\u00EAn", "Username", "Ch\u1EE9c v\u1EE5", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "\u0110\u1ECBa ch\u1EC9"
        	}) {
                boolean[] canEdit = new boolean [] {
                        false, false, false, false, false, false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
        tbDanhSachNhanVien.getColumnModel().getColumn(0).setPreferredWidth(30);
        tbDanhSachNhanVien.getColumnModel().getColumn(0).setMinWidth(30);
        tbDanhSachNhanVien.getColumnModel().getColumn(1).setMinWidth(75);
        tbDanhSachNhanVien.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbDanhSachNhanVien.getColumnModel().getColumn(2).setMinWidth(100);
        tbDanhSachNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDanhSachNhanVienMouseClicked(evt);
            }
        });
        spTable.setViewportView(tbDanhSachNhanVien);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        CbFilter = new JComboBox<String>();
        CbFilter.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        CbFilter.setBounds(395, 32, 93, 35);
        ComboBoxEditor editor = (ComboBoxEditor)CbFilter.getEditor();
        JTextField txt = (JTextField)editor.getEditorComponent();
        txt.setBackground(Color.WHITE);
        CbFilter.setModel(new DefaultComboBoxModel<>(new String[] {"Tất cả"}));
        CbFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	SelectedRole = (String) CbFilter.getSelectedItem();
                	if (SelectedRole == null) {
                		SelectedRole = "Tất cả";
                	}
                    filterStaffByRole(roleID, SelectedRole);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        btnNhanVienMoi.setBackground(new java.awt.Color(22, 113, 221));
        btnNhanVienMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnNhanVienMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/action-add-white.png"))); // NOI18N
        btnNhanVienMoi.setText("Nhân viên mới");
        btnNhanVienMoi.setToolTipText("");
        btnNhanVienMoi.setBorderColor(new java.awt.Color(22, 113, 221));
        btnNhanVienMoi.setColor(new java.awt.Color(22, 113, 221));
        btnNhanVienMoi.setColorClick(new java.awt.Color(153, 204, 255));
        btnNhanVienMoi.setColorOver(new java.awt.Color(22, 113, 221));
        btnNhanVienMoi.setFont(new Font("Segoe UI", Font.BOLD, 15)); // NOI18N
        btnNhanVienMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienMoiActionPerformed(evt);
            }
        });
    }// </editor-fold>//GEN-END:initComponents

    private void filterStaffByRole(String roleID, String selectedRole) {
        try {
            Vector<Account> arr = staffBUS.getAllQL();
            tbDanhSachNhanVien.setRowCount(0);

            for (int i = 0; i < arr.size(); i++) {
                Account acc = arr.get(i);
                int id = acc.getPersonID();
                String name = acc.getName();
                String tel = acc.getTel();
                String address = acc.getAddress();
                String username = acc.getUsername();
                String role = acc.getRoleID();

                if (selectedRole.equals(role) || selectedRole.equals("Tất cả")) {
                    Object row[] = {i + 1, id, name, username, role, tel, address};
                    tbDanhSachNhanVien.addRow(row);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

	private void loadRole(String roleID) {
	        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) CbFilter.getModel();
	        try {
                model = (DefaultComboBoxModel<String>) CbFilter.getModel();
                model.removeAllElements();
                model.addElement("Tất cả");
            	model.addAll(staffBUS.getRoleAD());
            } catch (Exception e) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null,e.getMessage());
            }
            CbFilter.revalidate();
            CbFilter.repaint();
	}

	private void tbDanhSachNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDanhSachNhanVienMouseClicked
       if (evt.getClickCount() == 2 && rolePermissionBUS.hasPerView(roleID, 7)) {
            int row = tbDanhSachNhanVien.getSelectedRow();
            if (row >= 0) {
                String cellValue = tbDanhSachNhanVien.getValueAt(row, 1).toString();
                int cellVal=Integer.parseInt(cellValue);
                try {
                        StaffInfor_Dialog ruid=new StaffInfor_Dialog(new javax.swing.JFrame(), true,cellVal,tbDanhSachNhanVien,roleID,userID);
                        ruid.setVisible(true);
                }catch(Exception ex) {
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        }
    }

    private void btnNhanVienMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienMoiActionPerformed
        try {
            StaffAdd_Dialog sad=new StaffAdd_Dialog(this.userLogin ,new javax.swing.JFrame(), true,tbDanhSachNhanVien);
            sad.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Staff_GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Staff_GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Staff_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnNhanVienMoiActionPerformed

    	// Variables declaration - do not modify//GEN-BEGIN:variables
    private MyDesign.MyButton btnNhanVienMoi;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private JPanel panelBorder1;
    private JPanel panelBorder_Basic1;
    private javax.swing.JScrollPane spTable;
    private MyDesign.MyTable tbDanhSachNhanVien;
    private MyDesign.SearchText txtTimKiem;
    private JComboBox<String> CbFilter;
    private String SelectedRole;
}