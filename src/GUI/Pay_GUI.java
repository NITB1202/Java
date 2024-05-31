/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.BookBUS;
import BUS.PayBUS;
import BUS.RolePermissionBUS;
import DTO.entities.Account;
import DTO.entities.Book1;
import DTO.entities.BorrowCard;
import DTO.entities.DetailBC;

import MyDesign.ScrollBar;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.record.PageBreakRecord;

/**
 *
 * @author QUANG DIEN
 */
public class Pay_GUI extends javax.swing.JPanel {
    private PayBUS pbus;
    DefaultTableModel tableModel;
    private BorrowCard bc;
    Vector<BorrowCard> list;
    public static BorrowCard bc__static;     
    public static DetailBC dt__static; 
    private int n;
    private Account user;
    private RolePermissionBUS rolePermissionBUS;
    /**
     * Creates new form Pay_GUI
     */
    public Pay_GUI(Account user) throws ClassNotFoundException, ClassNotFoundException, SQLException, IOException {
        initComponents();
        this.user = user;
        this.rolePermissionBUS = new RolePermissionBUS();
        try {
            pbus = new PayBUS();
            showAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        spTable1.setVerticalScrollBar(new ScrollBar());
        spTable1.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable1.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        spTicketDetail.setVerticalScrollBar(new ScrollBar());
        spTicketDetail.getVerticalScrollBar().setBackground(Color.WHITE);
        spTicketDetail.getViewport().setBackground(Color.WHITE);
        p.setBackground(Color.WHITE);
        spTicketDetail.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        spTable1.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        Calendar minDate = Calendar.getInstance();
        dtgNgayNhan.setMaxSelectableDate(minDate.getTime());
        if(rolePermissionBUS.hasPerEdit(this.user.getRoleID(), 2)){
            btnChoMuon.setEnabled(true);       
        }
        else btnChoMuon.setEnabled(false);
    }
    
    private void showAll(){
        tableModel = (DefaultTableModel)tbSachKhaDung.getModel();
        showBC();
        
    }
     
    private void showBC(){
        try {
            Vector<BorrowCard> list_bc = pbus.getAll();
            
            tableModel.setRowCount(0);
            for (BorrowCard bc : list_bc) {
                Object[] row = {bc.getID(), bc.getReadername(), bc.getStartDate(), bc.getExpReDate(), bc.getStaffname()};
                tableModel.addRow(row); 
            }
            
        } catch (Exception e) {
            e.printStackTrace();
    }}
    
    public void showBooks(int i) throws ClassNotFoundException, SQLException, IOException, Exception{
        list = pbus.getAll();
        String idborrow="#PM"+String.valueOf(tableModel.getValueAt(i,0));
        lbMaPhieuMuon.setText(idborrow);
        txtDocGia.setText(tableModel.getValueAt(i, 1).toString());
        txtNgayMuon.setText(tableModel.getValueAt(i, 2).toString());
        txtThuKho.setText(tableModel.getValueAt(i, 4).toString());
        txtTienCoc.setText(String.valueOf(list.get(i).getdeposit())+"đ");
        Vector<DetailBC> listBook = new Vector<DetailBC>(list.get(i).getListBook());
        DefaultTableModel Bookmodel = (DefaultTableModel) tbSach.getModel();
        Bookmodel.setRowCount(0);
        for(DetailBC obj:listBook){
            Object[] lost={"Sách                         Mất sách"};
            Bookmodel.addRow(lost);
            Object[] book={"Tên sách:        "+obj.getBookname()};
            Bookmodel.addRow(book);
            Object[] author={"Tác giả:           "+obj.getAuthorname()};
            Bookmodel.addRow(author);
            Object[] num={"Số lượng:        "+obj.getNum()};
            Bookmodel.addRow(num);
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

        jPanel1 = new javax.swing.JPanel();
        panelBorder1 = new MyDesign.PanelBorder();
        jLabel5 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        tbSachKhaDung = new MyDesign.MyTable();
        panelBorder_Basic1 = new MyDesign.PanelBorder_Basic();
        jLabel8 = new javax.swing.JLabel();
        txtSearch = new MyDesign.SearchText();
        spTicketDetail = new javax.swing.JScrollPane();
        panelBorder2 = new MyDesign.PanelBorder();
        lbMaPhieuMuon = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtDocGia = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNgayMuon = new javax.swing.JLabel();
        txtThuKho = new javax.swing.JLabel();
        txtTienCoc = new javax.swing.JLabel();
        lbLine = new javax.swing.JLabel();
        dtgNgayNhan = new com.toedter.calendar.JDateChooser();
        jLabel24 = new javax.swing.JLabel();
        spTable1 = new javax.swing.JScrollPane();
        tbSach = new javax.swing.JTable();
        btnChoMuon = new MyDesign.MyButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(127, 127, 127));
        jLabel5.setText("Phiếu mượn");

        spTable.setBorder(null);

        tbSachKhaDung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Độc giả", "Ngày mượn", "Ngày trả", "Thủ thư"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSachKhaDung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSachKhaDungMouseClicked(evt);
            }
        });
        spTable.setViewportView(tbSachKhaDung);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panelBorder_Basic1Layout = new javax.swing.GroupLayout(panelBorder_Basic1);
        panelBorder_Basic1.setLayout(panelBorder_Basic1Layout);
        panelBorder_Basic1Layout.setHorizontalGroup(
            panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder_Basic1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelBorder_Basic1Layout.setVerticalGroup(
            panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder_Basic1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelBorder_Basic1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(panelBorder_Basic1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addContainerGap())
        );

        spTicketDetail.setBackground(new java.awt.Color(246, 250, 255));
        spTicketDetail.setBorder(null);

        panelBorder2.setPreferredSize(new java.awt.Dimension(217, 327));

        lbMaPhieuMuon.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lbMaPhieuMuon.setForeground(new java.awt.Color(22, 113, 221));
        lbMaPhieuMuon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel3.setText("Độc giả");

        jLabel2.setForeground(new java.awt.Color(127, 127, 127));
        jLabel2.setText("Thông tin cơ bản");

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MÃ PHIẾU MƯỢN");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel4.setText("Ngày mượn");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel6.setText("Thủ Thư");

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel7.setText("Tiền cọc");

        txtTienCoc.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N

        lbLine.setForeground(new java.awt.Color(204, 204, 204));
        lbLine.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)));

        dtgNgayNhan.setToolTipText("");
        dtgNgayNhan.setDateFormatString("yyyy-MM-dd");
        dtgNgayNhan.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtgNgayNhanPropertyChange(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setText("Ngày Nhận:");

        tbSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                ""
            }
        ));
        tbSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSachMouseClicked(evt);
            }
        });
        spTable1.setViewportView(tbSach);

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbLine, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addComponent(jLabel6)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtgNgayNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(26, 26, 26)
                        .addComponent(txtTienCoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbMaPhieuMuon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelBorder2Layout.createSequentialGroup()
                            .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4))
                            .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelBorder2Layout.createSequentialGroup()
                                    .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelBorder2Layout.createSequentialGroup()
                                            .addGap(8, 8, 8)
                                            .addComponent(txtDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelBorder2Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtNgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(20, 20, 20))
                                .addGroup(panelBorder2Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(txtThuKho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addComponent(spTable1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(lbMaPhieuMuon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDocGia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNgayMuon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtThuKho))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTienCoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbLine, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable1, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dtgNgayNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        spTicketDetail.setViewportView(panelBorder2);

        btnChoMuon.setBackground(new java.awt.Color(22, 113, 221));
        btnChoMuon.setForeground(new java.awt.Color(255, 255, 255));
        btnChoMuon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/warehouse-white.png"))); // NOI18N
        btnChoMuon.setText("Nhận sách");
        btnChoMuon.setBorderColor(new java.awt.Color(22, 113, 221));
        btnChoMuon.setColor(new java.awt.Color(22, 113, 221));
        btnChoMuon.setColorOver(new java.awt.Color(22, 113, 221));
        btnChoMuon.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnChoMuon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChoMuonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(spTicketDetail)
                        .addGap(15, 15, 15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnChoMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(spTicketDetail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnChoMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnChoMuonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChoMuonMouseClicked
        // TODO add your handling code here:
        try {
            list = pbus.getAll();
            int n= tbSachKhaDung.getSelectedRow();
            if(n != -1){
                int selectedId = (int) tbSachKhaDung.getValueAt(n, 0);

                for (BorrowCard card : list) {
                    if (card.getID() == selectedId) {
                        bc = card;
                        break;
                    }
                }
            
                if(dtgNgayNhan.getDate()!=null){
                    java.util.Date getDate = dtgNgayNhan.getDate();
                    java.sql.Date readDate = new java.sql.Date(getDate.getTime());
                    //Thêm 7 ngày
                    LocalDate localDate = readDate.toLocalDate().plusDays(7);
                java.sql.Date banAccDate = java.sql.Date.valueOf(localDate);

                long songayMuon = pbus.CacluteDate(bc.getStartDate(), bc.getExpReDate());
                long ngayTrathucte = pbus.CacluteDate(bc.getRealReDate(),bc.getExpReDate());
                if(ngayTrathucte < 0){
                    JOptionPane.showMessageDialog(null, "Trả không đúng hạn! Thẻ sẽ bị khóa đến ngày: "+banAccDate);
                    pbus.banAcc(bc.getID(), banAccDate);
                    JOptionPane.showMessageDialog(null, "Nhận thành công!");
                    pbus.RecoverBook(bc);
                }
                else if(ngayTrathucte >=0 && ngayTrathucte < songayMuon){
                    pbus.RecoverBook(bc);
                    JOptionPane.showMessageDialog(null, "Nhận thành công!");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Ngày nhận sai thực tế!");
                }
                showBC();     
            }else{
                JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày nhận! ");
            }
            }else{
                JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu mượn! ");             
            } 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnChoMuonMouseClicked

    private void dtgNgayNhanPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtgNgayNhanPropertyChange
        try {

            // TODO add your handling code here:
            if ("date".equals(evt.getPropertyName())) {
                java.util.Date utilDate = dtgNgayNhan.getDate();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                pbus = new PayBUS();
                pbus.getRealDate(bc__static.getID(), sqlDate);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Pay_GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Pay_GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Pay_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_dtgNgayNhanPropertyChange

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        try {
            String keyword = txtSearch.getText().toLowerCase();
            if (!keyword.isEmpty()) {
                Vector<BorrowCard> kq = pbus.getAll();
                tableModel.setRowCount(0);
                if (kq.isEmpty()) {
                } else {
                    for (BorrowCard bc : kq) {
                        String maPM = String.valueOf(bc.getID());
                        String rName = bc.getReadername().toLowerCase();
                        String sName = bc.getStaffname().toLowerCase();
                        String sDate = bc.getStartDate().toString();
                        String eDate = bc.getExpReDate().toString();

                        if(maPM.contains(keyword)||rName.contains(keyword)||sName.contains(keyword)||sDate.contains(keyword)||eDate.contains(keyword)){
                            Object[] row = { bc.getID(), bc.getReadername(), bc.getStartDate(), bc.getExpReDate(), bc.getStaffname() };
                            tableModel.addRow(row);
                        }
                    }
                }
            } else {
                showBC();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void tbSachKhaDungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSachKhaDungMouseClicked

        // TODO add your handling code here:
        Vector<BorrowCard> list;
        try {
            list = pbus.getAll();
            n = tbSachKhaDung.getSelectedRow();
            int selectedId = (int) tbSachKhaDung.getValueAt(n, 0);
            bc = new BorrowCard();
            for (BorrowCard card : list) {
                if (card.getID() == selectedId) {
                    bc = card;
                    break;
                }
            }
            bc__static = bc;

            showBooks(n);
                       
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_tbSachKhaDungMouseClicked

    private void tbSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSachMouseClicked
        try {
            // TODO add your handling code here:
            int soluong = 0;
            list = pbus.getAll();
            Vector<DetailBC> listBook = new Vector<DetailBC>(list.get(n).getListBook());
            
            int selectedRow = tbSach.getSelectedRow();
            if (selectedRow >= 0) {
                String cellValue = tbSach.getValueAt(selectedRow, 0).toString();
                if (cellValue.contains("Mất sách") && rolePermissionBUS.hasPerEdit(this.user.getRoleID(), 2)) {
                    String bookName = tbSach.getValueAt(selectedRow + 1, 0).toString().replace("Tên sách:        ", "").trim();
                    for (DetailBC detailBC : listBook) {
                        if(detailBC.getBookname().equals(bookName)){
                            dt__static = detailBC;
                            soluong = detailBC.getNum();
                        }
                    }
                    if (soluong > 0) {
                        JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);

                        PayReport_Dialog payReportDialog = new PayReport_Dialog(parent, true);
                        payReportDialog.setVisible(true);
                        payReportDialog.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                try {
                                    showBooks(n);
                                } catch (SQLException ex) {
                                    Logger.getLogger(Pay_GUI.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IOException ex) {
                                    Logger.getLogger(Pay_GUI.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (Exception ex) {
                                    Logger.getLogger(Pay_GUI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                    } else{
                        JOptionPane.showMessageDialog(null, "Không còn sách để mất!");
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Pay_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbSachMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private MyDesign.MyButton btnChoMuon;
    private com.toedter.calendar.JDateChooser dtgNgayNhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbLine;
    private javax.swing.JLabel lbMaPhieuMuon;
    private MyDesign.PanelBorder panelBorder1;
    private MyDesign.PanelBorder panelBorder2;
    private MyDesign.PanelBorder_Basic panelBorder_Basic1;
    private javax.swing.JScrollPane spTable;
    private javax.swing.JScrollPane spTable1;
    private javax.swing.JScrollPane spTicketDetail;
    private javax.swing.JTable tbSach;
    private MyDesign.MyTable tbSachKhaDung;
    private javax.swing.JLabel txtDocGia;
    private javax.swing.JLabel txtNgayMuon;
    private MyDesign.SearchText txtSearch;
    private javax.swing.JLabel txtThuKho;
    private javax.swing.JLabel txtTienCoc;
    // End of variables declaration//GEN-END:variables
}
