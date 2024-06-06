package GUI;
import MyDesign.ScrollBar;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import BUS.ReaderBUS;
import BUS.RolePermissionBUS;
import DTO.entities.Account;
import DTO.entities.Reader;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Vector;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;

public class Reader1_GUI extends JPanel {

	private static final long serialVersionUID = 1L;

    private ReaderBUS readerBUS;
    private Account user;
    private RolePermissionBUS rolePermissionBUS;
    
	public Reader1_GUI(Account user)throws Exception {
		 initComponents();
	        this.user = user;
	        this.rolePermissionBUS = new RolePermissionBUS();
	        spTable.setVerticalScrollBar(new ScrollBar());
	        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
	        spTable.getViewport().setBackground(Color.WHITE);
	        JPanel p = new JPanel();
	        try {
	            readerBUS = new ReaderBUS();
	            readerBUS.updateFineDate();
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(null, ex.getMessage());
	        }
	        addDefault();
	        p.setBackground(Color.WHITE);
	        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
	        if (rolePermissionBUS.hasPerCreate(user.getRoleID(), 6))
	            btnDocGiaMoi.setEnabled(true);
	        else btnDocGiaMoi.setEnabled(false);

	}
	 public void addDefault() throws Exception {
	        Vector<Reader> arr = readerBUS.getAll();
	        for (int i = 0; i < arr.size(); i++) {
	            Reader acc = arr.get(i);
	            int id = acc.getPersonID();
	            String name = acc.getName();
	            String tel = acc.getTel();
	            String address = acc.getAddress();
	            LocalDate fineDate = acc.getFineDate();
	            Integer isLocked = acc.getStatus();
	            String isL = "Mở";
	            if (isLocked == 1) {
	                isL = "Khoá";
	            }
	            long daysBetween = 0;
	            if (fineDate != null) {
	                LocalDate cuDate = LocalDate.now();
	                daysBetween = ChronoUnit.DAYS.between(cuDate, fineDate);
	            }
	            Object row[] = {i + 1, id, name, tel, address, daysBetween, isL};
	            tbDanhSachDocGia.addRow(row);
	        }
	    }

	    public void findVal(String str) throws Exception {
	        Vector<Reader> arr = readerBUS.allOutSearch(str);
	        if (arr.size() == 0) {
	            JOptionPane.showMessageDialog(null, "Không tìm thấy độc giả theo yêu cầu");
	            return;
	        }
	        tbDanhSachDocGia.setRowCount(0);
	        for (int i = 0; i < arr.size(); i++) {
	            Reader acc = arr.get(i);
	            int id = acc.getPersonID();
	            String name = acc.getName();
	            String tel = acc.getTel();
	            String address = acc.getAddress();
	            LocalDate fineDate = acc.getFineDate();
	            Integer isLocked = acc.getStatus();
	            String isL = "Mở";
	            if (isLocked == 1) {
	                isL = "Khoá";
	            }
	            long daysBetween = 0;
	            if (fineDate != null) {
	                LocalDate cuDate = LocalDate.now();
	                daysBetween = ChronoUnit.DAYS.between(cuDate, fineDate);
	            }
	            Object row[] = {i + 1, id, name, tel, address, daysBetween, isL};
	            tbDanhSachDocGia.addRow(row);
	        }
	    }

	    @SuppressWarnings("unchecked")
	    private void initComponents() {
	        panelBorder1 = new MyDesign.PanelBorder();
	        jLabel5 = new javax.swing.JLabel();
	        spTable = new javax.swing.JScrollPane();
	        tbDanhSachDocGia = new MyDesign.MyTable();
	        panelBorder_Basic1 = new MyDesign.PanelBorder_Basic();
	        jLabel8 = new javax.swing.JLabel();
	        txtTimKiem = new MyDesign.SearchText();
	        btnDocGiaMoi = new MyDesign.MyButton();

	        setBackground(new java.awt.Color(255, 255, 255));

	        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 16));
	        jLabel5.setForeground(new java.awt.Color(127, 127, 127));
	        jLabel5.setText("Danh sách độc giả");

	        spTable.setBorder(null);

	        tbDanhSachDocGia.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {},
	            new String [] {
	                "STT", "Mã độc giả", "Tên độc giả", "Số điện thoại", "Địa chỉ", "Số ngày phạt", "Trạng thái"
	            }
	        ) {
	            boolean[] canEdit = new boolean [] {
	                false, false, false, false, false, true, true
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit[columnIndex];
	            }
	        });
	        tbDanhSachDocGia.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	                tbDanhSachDocGiaMouseClicked(evt);
	            }
	        });
	        spTable.setViewportView(tbDanhSachDocGia);
	        if (tbDanhSachDocGia.getColumnModel().getColumnCount() > 0) {
	            tbDanhSachDocGia.getColumnModel().getColumn(0).setMinWidth(40);
	            tbDanhSachDocGia.getColumnModel().getColumn(0).setMaxWidth(50);
	            tbDanhSachDocGia.getColumnModel().getColumn(5).setMinWidth(50);
	            tbDanhSachDocGia.getColumnModel().getColumn(5).setMaxWidth(70);
	            tbDanhSachDocGia.getColumnModel().getColumn(6).setMinWidth(50);
	            tbDanhSachDocGia.getColumnModel().getColumn(6).setMaxWidth(70);
	        }

	        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png")));

	        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                txtTimKiemActionPerformed(evt);
	            }
	        });

	        javax.swing.GroupLayout panelBorder_Basic1Layout = new javax.swing.GroupLayout(panelBorder_Basic1);
	        panelBorder_Basic1.setLayout(panelBorder_Basic1Layout);
	        panelBorder_Basic1Layout.setHorizontalGroup(
	            panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder_Basic1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap())
	        );
	        panelBorder_Basic1Layout.setVerticalGroup(
	            panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
	                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(0, 0, Short.MAX_VALUE))
	                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                .addContainerGap())
	        );

	        btnDocGiaMoi.setBackground(new java.awt.Color(22, 113, 221));
	        btnDocGiaMoi.setForeground(new java.awt.Color(255, 255, 255));
	        btnDocGiaMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/action-add-white.png")));
	        btnDocGiaMoi.setText("Độc giả mới");
	        btnDocGiaMoi.setToolTipText("");
	        btnDocGiaMoi.setBorderColor(new java.awt.Color(22, 113, 221));
	        btnDocGiaMoi.setColor(new java.awt.Color(22, 113, 221));
	        btnDocGiaMoi.setColorClick(new java.awt.Color(153, 204, 255));
	        btnDocGiaMoi.setColorOver(new java.awt.Color(22, 113, 221));
	        btnDocGiaMoi.setFont(new java.awt.Font("SansSerif", 1, 12));
	        btnDocGiaMoi.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                btnDocGiaMoiActionPerformed(evt);
	            }
	        });

	        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
	        panelBorder1.setLayout(panelBorder1Layout);
	        panelBorder1Layout.setHorizontalGroup(
	            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(panelBorder1Layout.createSequentialGroup()
	                .addGap(20, 20, 20)
	                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                        .addGroup(panelBorder1Layout.createSequentialGroup()
	                            .addComponent(jLabel5)
	                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                            .addComponent(panelBorder_Basic1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                        .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addComponent(btnDocGiaMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addContainerGap(20, Short.MAX_VALUE))
	        );
	        panelBorder1Layout.setVerticalGroup(
	            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(panelBorder1Layout.createSequentialGroup()
	                .addContainerGap(14, Short.MAX_VALUE)
	                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addComponent(panelBorder_Basic1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(12, 12, 12)
	                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(btnDocGiaMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(14, 14, 14))
	        );

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	        layout.setHorizontalGroup(
	        	layout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
	        			.addContainerGap()
	        			.addComponent(panelBorder1, GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
	        			.addContainerGap())
	        );
	        layout.setVerticalGroup(
	        	layout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
	        			.addContainerGap()
	        			.addComponent(panelBorder1, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
	        			.addContainerGap())
	        );
	        this.setLayout(layout);
	    }

	    private void tbDanhSachDocGiaMouseClicked(java.awt.event.MouseEvent evt) {
	        if (evt.getClickCount() == 2 && rolePermissionBUS.hasPerView(this.user.getRoleID(), 6)) {
	            int row = tbDanhSachDocGia.getSelectedRow();
	            if (row >= 0) {
	                String cellValue = tbDanhSachDocGia.getValueAt(row, 1).toString();
	                int cellVal = Integer.parseInt(cellValue);
	                try {
	                    ReaderUpdateInfor1_Dialog ruid = new ReaderUpdateInfor1_Dialog(this.user, new javax.swing.JFrame(), true, cellVal, tbDanhSachDocGia);
	                    ruid.setVisible(true);
	                } catch (Exception ex) {
	                    JOptionPane.showMessageDialog(null, ex.getMessage());
	                }
	            }
	        }
	    }

	    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {
	        String text = txtTimKiem.getText().trim();
	        try {
	            findVal(text);
	        } catch (Exception e1) {
	            JOptionPane.showMessageDialog(null, e1.getMessage());
	        }
	    }

	    private void btnDocGiaMoiActionPerformed(java.awt.event.ActionEvent evt) {
	        try {
	            ReaderAdd1_Dialog rad = new ReaderAdd1_Dialog(new javax.swing.JFrame(), true, tbDanhSachDocGia);
	            rad.setVisible(true);
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(null, ex.getMessage());
	        }
	    }

	    // Variables declaration
	    private MyDesign.MyButton btnDocGiaMoi;
	    private javax.swing.JLabel jLabel5;
	    private javax.swing.JLabel jLabel8;
	    private MyDesign.PanelBorder panelBorder1;
	    private MyDesign.PanelBorder_Basic panelBorder_Basic1;
	    private javax.swing.JScrollPane spTable;
	    private MyDesign.MyTable tbDanhSachDocGia;
	    private MyDesign.SearchText txtTimKiem;

}
