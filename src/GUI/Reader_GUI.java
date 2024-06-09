/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import MyDesign.ScrollBar;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import BUS.ReaderBUS;
import BUS.RolePermissionBUS;
import DTO.entities.Account;
import DTO.entities.BorrowCard;
import DTO.entities.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import MyDesign.MyButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Reader_GUI extends JPanel {
    ReaderBUS readerBUS;
    Account user;
    private RolePermissionBUS rolePermissionBUS ;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private Vector<Reader> readerList;
    /**
     * Creates new form Reader_GUI
     */
    public Reader_GUI(Account user) throws Exception {
        initComponents();
        this.user = user;
        this.rolePermissionBUS = new RolePermissionBUS();
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        try{
            readerBUS=new ReaderBUS();
            readerBUS.updateFineDate();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        addDefault();
        setLayout(null);
        panel.setLayout(null);
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        panel.add(spTable);
        panel.add(jLabel5);
        panel.add(panelBorder_Basic1);
        panelBorder_Basic1.setLayout(null);
        panelBorder_Basic1.add(txtTimKiem);
        panelBorder_Basic1.add(jLabel8);
        panel.add(btnDocGiaMoi);
        add(panel);
        
        btnThanhToan = new MyButton();
        btnThanhToan.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					thanhToan();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        btnThanhToan.setIcon(new ImageIcon(Reader_GUI.class.getResource("/Images/borrow-white.png")));
        btnThanhToan.setToolTipText("");
        btnThanhToan.setText("Thanh toán tiền phạt");
        btnThanhToan.setForeground(Color.WHITE);
        btnThanhToan.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnThanhToan.setColorOver(new Color(133, 232, 115));
        btnThanhToan.setColorClick(new Color(64, 217, 36));
        btnThanhToan.setColor(new Color(133, 232, 115));
        btnThanhToan.setBorderColor(new Color(133, 232, 115));
        btnThanhToan.setBackground(new Color(133, 232, 115));
        btnThanhToan.setBounds(374, 525, 212, 45);
        panel.add(btnThanhToan);
        if(rolePermissionBUS.hasPerCreate(user.getRoleID(), 6))
            btnDocGiaMoi.setEnabled(true);
        else btnDocGiaMoi.setEnabled(false);
    }
    protected void thanhToan() throws NumberFormatException, Exception {
		int selectedRow = tbDanhSachDocGia.getSelectedRow();
		if(selectedRow < 0)
		{
			JOptionPane.showMessageDialog(null, "Vui lòng chọn độc giả để thanh toán tiền phạt", "Lỗi",JOptionPane.ERROR_MESSAGE);
			return;
		}
		else
		{
			Reader reader = readerBUS.findbyID(Integer.parseInt(tbDanhSachDocGia.getValueAt(selectedRow, 1).toString()));
			
			if(reader.getPenalty() == 0)
			{
				JOptionPane.showMessageDialog(null, "Độc giả này không có tiền phạt cần thanh toán", "Lỗi",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			LocalDate homnay = LocalDate.now();
			if(reader.getFineDate().isBefore(homnay))
			{
				JOptionPane.showMessageDialog(null, "Đã quá hạn thanh toán. Tài khoản độc giả này sẽ bị khóa vĩnh viễn.", "Thông báo",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			readerBUS.payPenalty(reader.getPersonID());
			JOptionPane.showMessageDialog(null, "Thanh toán thành công. Đã mở khóa tài khoản.", "Thông báo",JOptionPane.INFORMATION_MESSAGE);
			addDefault();
		}
		
	}
    
    
	public void addDefault() throws Exception{
        readerList = readerBUS.getAll();
        for(int i=0;i<readerList.size();i++){
            Reader acc = readerList.get(i);
            int id=acc.getPersonID();
            String name=acc.getName();
            String tel=acc.getTel();
            String address=acc.getAddress();
            LocalDate fineDate=acc.getFineDate();
            String formatDate = "Không có";
            if(fineDate != null)
            		formatDate = fineDate.format(formatter);      
            Object row[] = {i+1,id,name,tel,address,formatDate};
            tbDanhSachDocGia.addRow(row);
        }
    }
    
    public void search() throws Exception
    {
		String searchText = txtTimKiem.getText().toLowerCase();
		//Clear table
		DefaultTableModel model = (DefaultTableModel) tbDanhSachDocGia.getModel();
		model.setRowCount(0);
		
		if(!searchText.isBlank())
		{
			for(int i = 0; i < readerList.size(); i++)
			{
				Reader reader = readerList.get(i);
				String readerName = reader.getName().toLowerCase();
				if(readerName.contains(searchText))
				{
					int id=reader.getPersonID();
		            String name=reader.getName();
		            String tel=reader.getTel();
		            String address=reader.getAddress();
		            LocalDate fineDate=reader.getFineDate();
		            String formatDate = "Không có";
		            if(fineDate != null)
		            		formatDate = fineDate.format(formatter);
		            Object row[] = {i+1,id,name,tel,address,formatDate};
		            tbDanhSachDocGia.addRow(row);
				}
			}
		}
		else
			addDefault();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new JPanel();
        panel.setBounds(10, 11, 827, 606);
        panel.setBackground(new Color(255, 255, 255));
        jLabel5 = new javax.swing.JLabel();
        jLabel5.setBounds(20, 33, 206, 26);
        spTable = new javax.swing.JScrollPane();
        spTable.setBounds(20, 83, 738, 426);
        tbDanhSachDocGia = new MyDesign.MyTable();
        panelBorder_Basic1 = new JPanel();
        panelBorder_Basic1.setBorder(new LineBorder(new Color(128, 128, 128)));
        panelBorder_Basic1.setBackground(new Color(255, 255, 255));
        panelBorder_Basic1.setBounds(483, 23, 275, 36);
        jLabel8 = new javax.swing.JLabel();
        jLabel8.setBounds(245, 3, 24, 28);
        txtTimKiem = new MyDesign.SearchText();
        txtTimKiem.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent e) {
        		try {
					search();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        txtTimKiem.setHint("Tìm tên độc giả...");
        txtTimKiem.setBounds(10, 5, 234, 28);
        txtTimKiem.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnDocGiaMoi = new MyDesign.MyButton();
        btnDocGiaMoi.setBounds(612, 525, 145, 45);

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new Font("SansSerif", Font.BOLD, 20)); // NOI18N
        jLabel5.setForeground(new Color(0, 0, 0));
        jLabel5.setText("Danh sách độc giả");

        spTable.setBorder(null);

        tbDanhSachDocGia.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"STT", "M\u00E3 \u0111\u1ED9c gi\u1EA3", "T\u00EAn \u0111\u1ED9c gi\u1EA3", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "\u0110\u1ECBa ch\u1EC9", "Hạn nộp phạt"
        	}        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
        tbDanhSachDocGia.getColumnModel().getColumn(0).setPreferredWidth(30);
        tbDanhSachDocGia.getColumnModel().getColumn(0).setMinWidth(30);
        tbDanhSachDocGia.getColumnModel().getColumn(1).setPreferredWidth(62);
        tbDanhSachDocGia.getColumnModel().getColumn(1).setMinWidth(62);
        tbDanhSachDocGia.getColumnModel().getColumn(2).setPreferredWidth(90);
        tbDanhSachDocGia.getColumnModel().getColumn(2).setMinWidth(90);
        tbDanhSachDocGia.getColumnModel().getColumn(3).setMinWidth(75);
        tbDanhSachDocGia.getColumnModel().getColumn(4).setPreferredWidth(190);
        tbDanhSachDocGia.getColumnModel().getColumn(4).setMinWidth(190);
        tbDanhSachDocGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDanhSachDocGiaMouseClicked(evt);
            }
        });
        spTable.setViewportView(tbDanhSachDocGia);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N

        btnDocGiaMoi.setBackground(new java.awt.Color(22, 113, 221));
        btnDocGiaMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnDocGiaMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/action-add-white.png"))); // NOI18N
        btnDocGiaMoi.setText("Độc giả mới");
        btnDocGiaMoi.setToolTipText("");
        btnDocGiaMoi.setBorderColor(new java.awt.Color(22, 113, 221));
        btnDocGiaMoi.setColor(new java.awt.Color(22, 113, 221));
        btnDocGiaMoi.setColorClick(new java.awt.Color(153, 204, 255));
        btnDocGiaMoi.setColorOver(new java.awt.Color(22, 113, 221));
        btnDocGiaMoi.setFont(new Font("Segoe UI", Font.BOLD, 15)); // NOI18N
        btnDocGiaMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocGiaMoiActionPerformed(evt);
            }
        });
    }// </editor-fold>//GEN-END:initComponents

    private void tbDanhSachDocGiaMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2 && rolePermissionBUS.hasPerView(this.user.getRoleID(), 6)) {
            int row = tbDanhSachDocGia.getSelectedRow();
            if (row >= 0) {
                String cellValue = tbDanhSachDocGia.getValueAt(row, 1).toString();
                int cellVal=Integer.parseInt(cellValue);
                try {
                        ReaderUpdateInfor_Dialog ruid=new ReaderUpdateInfor_Dialog(this.user, new javax.swing.JFrame(), true,cellVal,tbDanhSachDocGia);
                        ruid.setVisible(true);
                }catch(Exception ex) {
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        }
    }


    private void btnDocGiaMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDocGiaMoiActionPerformed
        try {
            ReaderAdd_Dialog rad=new ReaderAdd_Dialog(new javax.swing.JFrame(), true,tbDanhSachDocGia);
            rad.setVisible(true);
        }catch(Exception ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }//GEN-LAST:event_btnDocGiaMoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private MyDesign.MyButton btnDocGiaMoi;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private JPanel panel;
    private JPanel panelBorder_Basic1;
    private javax.swing.JScrollPane spTable;
    private MyDesign.MyTable tbDanhSachDocGia;
    private MyDesign.SearchText txtTimKiem;
    private MyButton btnThanhToan;
}
