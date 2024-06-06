package GUI;

import java.awt.Color;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import BUS.AccountBUS;
import BUS.BookBUS;
import BUS.RolePermissionBUS;
import BUS.StaffBUS;
import DTO.entities.Account;
import DTO.entities.Book1;
import MyDesign.ScrollBar;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import MyDesign.MyButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Book1_GUI extends JPanel {

	private static final long serialVersionUID = 1L;

	private StaffBUS staffBUS;
    private BookBUS bookBUS= new BookBUS();
    int userID;
    String roleID;
    private Account userLogin;
    private RolePermissionBUS rolePermissionBUS;
    ArrayList<Book1> bookList = new ArrayList<>();

    public Book1_GUI(Account userLogin) throws Exception {
        initComponents();
        this.userLogin = userLogin;
        this.userID = userLogin.getPersonID();
        this.roleID = userLogin.getRoleID();
        this.rolePermissionBUS = new RolePermissionBUS();
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        
        
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        upData();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new MyDesign.PanelBorder();
        jLabel5 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        tbSach = new MyDesign.MyTable();
        panelBorder_Basic1 = new MyDesign.PanelBorder_Basic();
        jLabel8 = new javax.swing.JLabel();
        txtTimKiem = new MyDesign.SearchText();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(127, 127, 127));
        jLabel5.setText("Danh sách Sách");

        spTable.setBorder(null);

        tbSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên sách", "Tác giả", "Phiên bản", "Số lương tồn"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSachMouseClicked(evt);
            }
        });
        spTable.setViewportView(tbSach);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder_Basic1Layout = new javax.swing.GroupLayout(panelBorder_Basic1);
        panelBorder_Basic1Layout.setHorizontalGroup(
        	panelBorder_Basic1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(txtTimKiem, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
        			.addGap(18)
        			.addComponent(jLabel8, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        panelBorder_Basic1Layout.setVerticalGroup(
        	panelBorder_Basic1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(txtTimKiem, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addContainerGap())
        );
        panelBorder_Basic1.setLayout(panelBorder_Basic1Layout);
        
        
        MyButton btnThemSach = new MyButton();
        btnThemSach.setIcon(new ImageIcon(Book1_GUI.class.getResource("/Images/action-add-white.png")));
        btnThemSach.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        	    try {
        	        BookImport_Dialog rad = new BookImport_Dialog(new javax.swing.JFrame(), true);
        	        rad.setVisible(true);
        	    } catch (IOException ex) {
        	        // Xử lý ngoại lệ IOException
        	        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra trong quá trình nhập dữ liệu: " + ex.getMessage(), "Lỗi IO", JOptionPane.ERROR_MESSAGE);
        	    } catch (SQLException ex) {
        	        // Xử lý ngoại lệ SQLException
        	        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi truy cập cơ sở dữ liệu: " + ex.getMessage(), "Lỗi Cơ Sở Dữ Liệu", JOptionPane.ERROR_MESSAGE);
        	    } catch (ClassNotFoundException ex) {
        	        // Xử lý ngoại lệ ClassNotFoundException
        	        JOptionPane.showMessageDialog(null, "Không tìm thấy lớp: " + ex.getMessage(), "Lỗi Lớp", JOptionPane.ERROR_MESSAGE);
        	    } catch (Exception ex) {
        	        // Xử lý các ngoại lệ khác
        	        JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        	    }
        	}

        });
        btnThemSach.setToolTipText("");
        btnThemSach.setText("Thêm sách");
        btnThemSach.setForeground(Color.WHITE);
        btnThemSach.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnThemSach.setColorOver(new Color(22, 113, 221));
        btnThemSach.setColorClick(new Color(153, 204, 255));
        btnThemSach.setColor(new Color(22, 113, 221));
        btnThemSach.setBorderColor(new Color(22, 113, 221));
        btnThemSach.setBackground(new Color(22, 113, 221));

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1Layout.setHorizontalGroup(
        	panelBorder1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(panelBorder1Layout.createSequentialGroup()
        			.addContainerGap(589, Short.MAX_VALUE)
        			.addComponent(btnThemSach, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
        			.addGap(27))
        		.addGroup(Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
        			.addGap(20)
        			.addGroup(panelBorder1Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(spTable, GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
        				.addGroup(panelBorder1Layout.createSequentialGroup()
        					.addComponent(jLabel5)
        					.addPreferredGap(ComponentPlacement.RELATED, 371, Short.MAX_VALUE)
        					.addComponent(panelBorder_Basic1, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
        	panelBorder1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(panelBorder1Layout.createSequentialGroup()
        			.addContainerGap(12, Short.MAX_VALUE)
        			.addGroup(panelBorder1Layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(panelBorder1Layout.createSequentialGroup()
        					.addComponent(jLabel5)
        					.addGap(30))
        				.addGroup(panelBorder1Layout.createSequentialGroup()
        					.addComponent(panelBorder_Basic1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)))
        			.addGap(5)
        			.addComponent(spTable, GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnThemSach, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
        			.addGap(19))
        );
        panelBorder1.setLayout(panelBorder1Layout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(panelBorder1, GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE)
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(panelBorder1, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
        );
        this.setLayout(layout);
    }// </editor-fold>//GEN-END:initComponents

    private void tbSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSachMouseClicked
       if (evt.getClickCount() == 2 && rolePermissionBUS.hasPerView(roleID, 5)) {
           int row = tbSach.getSelectedRow();
            if (row >= 0) {
                try{
                    BookInfo1_Dialog bookDia=new BookInfo1_Dialog(new java.awt.Frame(),true,tbSach,bookList.get(row));
                    bookDia.setVisible(true);
                }catch(Exception ex){
                    Logger.getLogger(Book_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }//GEN-LAST:event_tbSachMouseClicked

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        String val=txtTimKiem.getText().trim();
            try {
                bookList=bookBUS.allOutSearch(val);
                if(bookList.size()==0){
                    JOptionPane.showMessageDialog(null,"Không tìm thấy sách");
                }else{
                    tbSach.setRowCount(0);
                    for(int i=0;i<bookList.size();i++){
                    Book1 book=bookList.get(i);
                    //String id=book.getISBN();
                    String name=book.getTenSach();
                    String pulisher=book.getPublisher();
                    String version=book.getVersion();
                    int num=book.getStoreNum();
                    Object row[] = {i+1,name,pulisher,version,num};
                    tbSach.addRow(row);
                    }
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Book_GUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Book_GUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Book_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_txtTimKiemActionPerformed

    public void upData(){
        try {
            bookList = bookBUS.getAllIncludeVersion();
            for(int i=0;i<bookList.size();i++){
            Book1 book=bookList.get(i);
           // String id=book.getISBN();
            String name=book.getTenSach();
            String pulisher=book.getPublisher();
            String version=book.getVersion();
            int num=book.getStoreNum();
            Object row[] = {i+1,name,pulisher,version,num};
            tbSach.addRow(row);
        }
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Book_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private MyDesign.PanelBorder panelBorder1;
    private MyDesign.PanelBorder_Basic panelBorder_Basic1;
    private javax.swing.JScrollPane spTable;
    private MyDesign.MyTable tbSach;
    private MyDesign.SearchText txtTimKiem;
}
