/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import MyDesign.ScrollBar;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import DTO.entities.BorrowCard;
import DTO.entities.DetailBC;
import BUS.BorrowCardBUS;
import BUS.DetailBCBUS;
import DTO.entities.Account;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
/**
 *
 * @author QUANG DIEN
 */
public class Ticket_GUI extends javax.swing.JPanel {
    Account user;
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    BorrowCardBUS ticketbll= new BorrowCardBUS();
    DetailBCBUS detailbcbll=new DetailBCBUS();
    DefaultTableModel model;
    Vector<BorrowCard> list;
    String modstatus;
    int ID;
    String Reader;
    Date startDate;
    Date realreDate;
    String Staff;
    /**
     * Creates new form Ticket_GUI
     */
    public Ticket_GUI(Account user) throws ClassNotFoundException, SQLException, IOException {
        this.list = new Vector<>(ticketbll.getAllTicket());
        this.user = user;
        initComponents();
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        jScrollPane2.setVerticalScrollBar(new ScrollBar());
        jScrollPane2.getVerticalScrollBar().setBackground(Color.WHITE);
        jScrollPane2.getViewport().setBackground(Color.WHITE);

        loadTicketList(list);
        spTicketDetail2.setVerticalScrollBar(new ScrollBar());
        setLayout(null);
        add(panelBorder1);
        panelBorder1.setLayout(null);
        panelBorder1.add(jLabel5);
        panelBorder1.add(myButton1);
        panelBorder1.add(spTable);
        add(spTicketDetail2);
        add(panelBorder5);
        spTicketDetail2.getVerticalScrollBar().setBackground(Color.WHITE);
        spTicketDetail2.getViewport().setBackground(Color.WHITE);
        
        DefaultTableCellRenderer customRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row%4 == 0)
                    comp.setFont(new Font("Segoe UI", Font.BOLD, 13));
                return comp;
            }
        };
        tbBook.getColumnModel().getColumn(0).setCellRenderer(customRenderer); 
    }
    
    
    public MyDesign.MyTable gettbPhieuMuon(){
        return tbPhieuMuon;
    }
    public JCheckBox getcbChuaTra(){
        return cbChuaTra;
    }
    public JCheckBox getcbDaTra(){
        return cbDaTra;
    }
    
    //-----TẠO BẢNG-----
    public void loadTicketList(Vector<BorrowCard> l) throws ClassNotFoundException, SQLException{
        model = (DefaultTableModel) tbPhieuMuon.getModel();
        System.out.print("loadTicketList");
        model.setRowCount(0);
        for(BorrowCard obj : l){
            ID=obj.getID();
            Reader=obj.getReadername();
            startDate=obj.getStartDate();
            realreDate=obj.getRealReDate();
            Staff=obj.getStaffname();
            if(realreDate!=null){
                modstatus="Đã trả";
            }else{
                modstatus="Chưa trả";
            }
            Object[] row={ID, Reader, startDate, Staff, modstatus};
            model.addRow(row);
        }
    }
    
    //-----LỌC CHƯA TRẢ-----
    public void setChuatra(Vector<BorrowCard> l){
        model = (DefaultTableModel) tbPhieuMuon.getModel();
        model.setRowCount(0);
        for(BorrowCard obj : l){
            ID=obj.getID();
            Reader=obj.getReadername();
            startDate=obj.getStartDate();
            realreDate=obj.getRealReDate();
            Staff=obj.getStaffname();
            if(realreDate==null){
                modstatus="Chưa trả";
                Object[] row={ID, Reader, startDate, Staff, modstatus};
                model.addRow(row);
            }
        }
    }
    
    //-----LỌC ĐÃ TRẢ-----
    public void setDatra(Vector<BorrowCard> l){
        model = (DefaultTableModel) tbPhieuMuon.getModel();
        model.setRowCount(0);
        for(BorrowCard obj : l){
            ID=obj.getID();
            Reader=obj.getReadername();
            startDate=obj.getStartDate();
            realreDate=obj.getRealReDate();
            Staff=obj.getStaffname();
            if(realreDate!=null){
                modstatus="Đã trả";
                Object[] row={ID, Reader, startDate, Staff, modstatus};
                model.addRow(row);
            }
        }
    }
    
    //-----HIỆN THÔNG TIN LÊN BẢNG-----
    public void showinfo(int i) throws ClassNotFoundException, SQLException, IOException{
        String idborrow="#PM"+String.valueOf(model.getValueAt(i,0));
        lbMaPM.setText(idborrow);
        txtDocGia4.setText(model.getValueAt(i, 1).toString());
        txtNgayMuon2.setText(model.getValueAt(i, 2).toString());
        txtHanTra.setText(String.valueOf(list.get(i).getExpReDate().toString()));
        txtThuKho2.setText(model.getValueAt(i, 3).toString());
        txtTienCoc2.setText(formatter.format(list.get(i).getdeposit())+"đ");
        Vector<DetailBC> listBook=new Vector<DetailBC>(detailbcbll.getinfo((int) model.getValueAt(i,0)));
        DefaultTableModel Bookmodel = (DefaultTableModel) tbBook.getModel();
        Bookmodel.setRowCount(0);
        int dem=1;
        for(DetailBC obj:listBook){
            if(obj.getLost()==0){
                    Object[] lost={"Sách "+dem+"          Còn sách"};
                Bookmodel.addRow(lost);
            }else{
                Object[] lost={"Sách "+dem+"          Mất sách"};
                Bookmodel.addRow(lost);
            }
            Object[] book={"Tên sách:        "+obj.getBookname()};
            Bookmodel.addRow(book);
            Object[] author={"Tác giả:           "+obj.getAuthorname().get(0)};
            Bookmodel.addRow(author);
            Object[] num={"Số lượng:        "+obj.getNum()};
            Bookmodel.addRow(num);
            dem++;
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

        btnGroup = new javax.swing.ButtonGroup();
        panelBorder1 = new JPanel();
        panelBorder1.setBackground(new Color(255, 255, 255));
        panelBorder1.setBounds(0, 0, 484, 608);
        jLabel5 = new javax.swing.JLabel();
        jLabel5.setBounds(10, 30, 140, 26);
        spTable = new javax.swing.JScrollPane();
        spTable.setBounds(10, 80, 464, 504);
        tbPhieuMuon = new MyDesign.MyTable();
        myButton1 = new MyDesign.MyButton();
        myButton1.setFont(new Font("Segoe UI", Font.BOLD, 15));
        myButton1.setBackground(new Color(22, 113, 221));
        myButton1.setColorOver(new Color(22, 113, 221));
        myButton1.setBounds(344, 20, 130, 45);
        spTicketDetail2 = new javax.swing.JScrollPane();
        spTicketDetail2.setBounds(500, 64, 278, 519);
        panelBorder4 = new JPanel();
        panelBorder4.setBorder(new LineBorder(new Color(128, 128, 128)));
        panelBorder4.setBackground(new Color(255, 255, 255));
        lbMaPM = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel22.setFont(new Font("SansSerif", Font.PLAIN, 11));
        jLabel23 = new javax.swing.JLabel();
        txtDocGia4 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtNgayMuon2 = new javax.swing.JLabel();
        txtThuKho2 = new javax.swing.JLabel();
        txtTienCoc2 = new javax.swing.JLabel();
        lbLine2 = new javax.swing.JLabel();
        txtDocGia5 = new javax.swing.JLabel();
        txtTacGia2 = new javax.swing.JLabel();
        txtSoLuong2 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtHanTra = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbBook = new MyDesign.MyTable();
        panelBorder5 = new JPanel();
        panelBorder5.setForeground(new Color(128, 128, 128));
        panelBorder5.setBorder(new LineBorder(new Color(128, 128, 128)));
        panelBorder5.setBackground(new Color(215, 231, 255));
        panelBorder5.setBounds(500, 20, 278, 48);
        cbDaTra = new javax.swing.JCheckBox();
        cbDaTra.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cbChuaTra = new javax.swing.JCheckBox();
        cbChuaTra.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new Font("SansSerif", Font.BOLD, 20)); // NOI18N
        jLabel5.setForeground(new Color(0, 0, 0));
        jLabel5.setText("Phiếu mượn");

        spTable.setBorder(null);

        tbPhieuMuon.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"ID", "\u0110\u1ED9c gi\u1EA3", "Ng\u00E0y m\u01B0\u1EE3n", "Th\u1EE7 thư", "Tr\u1EA1ng th\u00E1i"
        	}){
                boolean[] canEdit = new boolean [] {
                        false, false, false, false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
        tbPhieuMuon.getColumnModel().getColumn(0).setPreferredWidth(35);
        tbPhieuMuon.getColumnModel().getColumn(0).setMinWidth(35);
        tbPhieuMuon.getColumnModel().getColumn(1).setPreferredWidth(125);
        tbPhieuMuon.getColumnModel().getColumn(1).setMinWidth(125);
        tbPhieuMuon.getColumnModel().getColumn(2).setPreferredWidth(80);
        tbPhieuMuon.getColumnModel().getColumn(2).setMinWidth(80);
        tbPhieuMuon.getColumnModel().getColumn(3).setPreferredWidth(125);
        tbPhieuMuon.getColumnModel().getColumn(3).setMinWidth(125);
        tbPhieuMuon.getColumnModel().getColumn(4).setMinWidth(75);
        tbPhieuMuon.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbPhieuMuon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPhieuMuonMouseClicked(evt);
            }
        });
        tbPhieuMuon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbPhieuMuonKeyReleased(evt);
            }
        });
        spTable.setViewportView(tbPhieuMuon);

        myButton1.setForeground(new java.awt.Color(255, 255, 255));
        myButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search-white.png"))); // NOI18N
        myButton1.setText("Tìm kiếm");
        myButton1.setBorderColor(new java.awt.Color(22, 113, 221));
        myButton1.setBorderPainted(false);
        myButton1.setColor(new java.awt.Color(22, 113, 221));
        myButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton1ActionPerformed(evt);
            }
        });

        spTicketDetail2.setBackground(new java.awt.Color(246, 250, 255));
        spTicketDetail2.setBorder(null);
        spTicketDetail2.setOpaque(false);

        panelBorder4.setPreferredSize(new java.awt.Dimension(217, 327));

        lbMaPM.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lbMaPM.setForeground(new java.awt.Color(22, 113, 221));
        lbMaPM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMaPM.setText(" ");

        jLabel21.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel21.setText("Độc giả");

        jLabel22.setForeground(new java.awt.Color(127, 127, 127));
        jLabel22.setText("Thông tin cơ bản");

        jLabel23.setFont(new Font("SansSerif", Font.PLAIN, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(127, 127, 127));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("MÃ PHIẾU MƯỢN");
        jLabel23.setRequestFocusEnabled(false);

        txtDocGia4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel24.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel24.setText("Ngày mượn");

        jLabel25.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel25.setText("Thủ kho");

        jLabel26.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel26.setText("Tiền cọc");

        txtNgayMuon2.setToolTipText("");
        txtNgayMuon2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtThuKho2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtTienCoc2.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        txtTienCoc2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtTienCoc2.setToolTipText("");
        txtTienCoc2.setFocusable(false);
        txtTienCoc2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        lbLine2.setForeground(new java.awt.Color(204, 204, 204));
        lbLine2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)));

        jLabel30.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        jLabel30.setText("Hạn trả");

        txtHanTra.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jScrollPane2.setBackground(new java.awt.Color(246, 250, 255));

        tbBook.setBackground(new java.awt.Color(246, 250, 255));
        tbBook.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Thông tin sách mượn"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbBook.setColumnSelectionAllowed(true);
        tbBook.setGridColor(new java.awt.Color(255, 255, 255));
        tbBook.setSelectionBackground(new java.awt.Color(246, 250, 255));
        tbBook.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tbBook);
        tbBook.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                comp.setFont(new Font("Segoe UI", Font.BOLD, 13));
                setHorizontalAlignment(SwingConstants.CENTER);
                comp.setBackground(new Color(22, 113, 255));
                comp.setForeground(Color.WHITE);
                return comp;
            }
        };
        tbBook.getColumnModel().getColumn(0).setHeaderRenderer(headerRenderer);

        javax.swing.GroupLayout panelBorder4Layout = new javax.swing.GroupLayout(panelBorder4);
        panelBorder4Layout.setHorizontalGroup(
        	panelBorder4Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(panelBorder4Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(panelBorder4Layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(panelBorder4Layout.createSequentialGroup()
        					.addGroup(panelBorder4Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jLabel25)
        						.addComponent(jLabel26))
        					.addGap(39)
        					.addGroup(panelBorder4Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(txtThuKho2, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
        						.addComponent(txtTienCoc2, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)))
        				.addGroup(panelBorder4Layout.createSequentialGroup()
        					.addGroup(panelBorder4Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jLabel21)
        						.addComponent(jLabel24)
        						.addComponent(jLabel30))
        					.addGap(18)
        					.addGroup(panelBorder4Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(txtNgayMuon2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        						.addComponent(txtDocGia4, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        						.addComponent(txtHanTra, GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        						.addComponent(jLabel22)))
        				.addGroup(panelBorder4Layout.createSequentialGroup()
        					.addGroup(panelBorder4Layout.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(lbMaPM, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(jLabel23, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE))
        					.addGap(0, 337, Short.MAX_VALUE))
        				.addGroup(panelBorder4Layout.createSequentialGroup()
        					.addGroup(panelBorder4Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(lbLine2, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
        						.addGroup(panelBorder4Layout.createSequentialGroup()
        							.addGap(10)
        							.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)))
        					.addGap(205)
        					.addGroup(panelBorder4Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(txtTacGia2, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
        						.addGroup(panelBorder4Layout.createSequentialGroup()
        							.addGroup(panelBorder4Layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(txtSoLuong2, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
        								.addComponent(txtDocGia5, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
        							.addGap(6)))))
        			.addContainerGap())
        );
        panelBorder4Layout.setVerticalGroup(
        	panelBorder4Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(panelBorder4Layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jLabel23)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(lbMaPM)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jLabel22)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(panelBorder4Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel21)
        				.addComponent(txtDocGia4))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(panelBorder4Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel24)
        				.addComponent(txtNgayMuon2))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(panelBorder4Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel30)
        				.addComponent(txtHanTra))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(panelBorder4Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel25)
        				.addComponent(txtThuKho2))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(panelBorder4Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel26)
        				.addComponent(txtTienCoc2))
        			.addGap(7)
        			.addGroup(panelBorder4Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(panelBorder4Layout.createSequentialGroup()
        					.addGap(40)
        					.addComponent(txtDocGia5)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(txtTacGia2)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(txtSoLuong2))
        				.addGroup(panelBorder4Layout.createSequentialGroup()
        					.addGap(4)
        					.addComponent(lbLine2, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(21, Short.MAX_VALUE))
        );
        panelBorder4.setLayout(panelBorder4Layout);

        spTicketDetail2.setViewportView(panelBorder4);

        cbDaTra.setBackground(new Color(215, 231, 255));
        cbDaTra.setForeground(new Color(0, 0, 0));
        cbDaTra.setText("Đã trả");
        cbDaTra.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbDaTraItemStateChanged(evt);
            }
        });

        cbChuaTra.setBackground(new Color(215, 231, 255));
        cbChuaTra.setForeground(new Color(0, 0, 0));
        cbChuaTra.setText("Chưa trả");
        cbChuaTra.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbChuaTraItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout panelBorder5Layout = new javax.swing.GroupLayout(panelBorder5);
        panelBorder5Layout.setHorizontalGroup(
        	panelBorder5Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(panelBorder5Layout.createSequentialGroup()
        			.addGap(126)
        			.addComponent(cbDaTra)
        			.addGap(18)
        			.addComponent(cbChuaTra)
        			.addContainerGap(13, Short.MAX_VALUE))
        );
        panelBorder5Layout.setVerticalGroup(
        	panelBorder5Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(panelBorder5Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(panelBorder5Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(cbDaTra, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        				.addComponent(cbChuaTra, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(9, Short.MAX_VALUE))
        );
        panelBorder5.setLayout(panelBorder5Layout);
    }// </editor-fold>//GEN-END:initComponents

    private void tbPhieuMuonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPhieuMuonMouseClicked
        //-----BẮT SỰ KIỆN KHI CLICK CHUỘT VÀO BẢNG-----
        
        tbPhieuMuon.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                int i=tbPhieuMuon.getSelectedRow();
                if(i>=0){
                    try {
                        showinfo(i);
                    } catch (ClassNotFoundException ex) {
                        System.out.println(ex);
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Ticket_GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }//GEN-LAST:event_tbPhieuMuonMouseClicked

    private void cbDaTraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbDaTraItemStateChanged
        // TODO add your handling code here:
        //-----KIỂM TRA ĐÃ TRẢ-----
        if(evt.getStateChange()==1){
            if(getcbChuaTra().isSelected()){
                try {
                loadTicketList(list);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Ticket_GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Ticket_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                setDatra(list);
            }
        }else{
            if(getcbChuaTra().isSelected()){
                setChuatra(list);
            }else{
                try {
                loadTicketList(list);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Ticket_GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Ticket_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_cbDaTraItemStateChanged

    private void cbChuaTraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbChuaTraItemStateChanged
        // TODO add your handling code here:
        //-----KIỂM TRA CHƯA TRẢ-----
        if(evt.getStateChange()==1){
            if(getcbDaTra().isSelected()){
                try {
                loadTicketList(list);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Ticket_GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Ticket_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                setChuatra(list);
            }
        }else{
            if(getcbDaTra().isSelected()){
                setDatra(list);
            }else{
                try {
                loadTicketList(list);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Ticket_GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Ticket_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_cbChuaTraItemStateChanged

    private void myButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton1ActionPerformed
        // TODO add your handling code here:
        //-----BẮT SỰ KIỆN KHI NHẤN NÚT TRONG DIALOG-----
        
        TicketSearch_Dialog dialog = new TicketSearch_Dialog(new javax.swing.JFrame(), true);
        dialog.getbtnThemNhaCungCap().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dialog.getReadername().matches(".*\\d.*") || dialog.getStaffname().matches(".*\\d.*")){
                    JOptionPane.showMessageDialog(new javax.swing.JFrame(), "Tên không có chữ số ! Vui lòng nhập lại !", "Cảnh báo", JOptionPane.ERROR_MESSAGE);
                    dialog.setVisible(true);
                }else{
                    String condition="";
                    try {
                        if(dialog.getDate()!=null){
                            condition=condition+"startDate LIKE '%" + dialog.getDate() + "%'";
                        }

                        if(!dialog.getReadername().isEmpty()){
                            if(condition.isEmpty()){
                                condition=condition+"Reader.name LIKE N'%" + dialog.getReadername() + "%'";
                            }else{
                                condition=condition+" AND Reader.name LIKE N'%" + dialog.getReadername() + "%'";
                            }
                        }

                        if(!dialog.getStaffname().isEmpty()){
                            if(condition.isEmpty()){
                                condition=condition+"Staff.name LIKE N'%" + dialog.getStaffname() + "%'";
                            }else{
                                condition=condition+" AND Staff.name LIKE N'%" + dialog.getStaffname() + "%'";
                            }
                        }
                        if(condition.isEmpty()){
                            list=new Vector<BorrowCard>(ticketbll.getAllTicket());
                            loadTicketList(list);
                        }else{
                            list=new Vector<BorrowCard>(ticketbll.getByCondition(condition));
                            loadTicketList(list);
                        }

                        //-----KIỂM TRA NÚT ĐÃ TRẢ VÀ CHƯA TRẢ-----
                        if(getcbChuaTra().isSelected()){
                            setChuatra(list);
                        }
                        if(getcbDaTra().isSelected()){
                            setDatra(list);
                        }
                        dialog.setVisible(false);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(TicketSearch_Dialog.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(TicketSearch_Dialog.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Ticket_GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        dialog.setVisible(true);
    }//GEN-LAST:event_myButton1ActionPerformed

    private void tbPhieuMuonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPhieuMuonKeyReleased
        // TODO add your handling code here:
        //-----BẮT SỰ KIỆN KHI NHẤN NÚT LÊN XUỐNG BẰNG PHÍM TRONG BẢN-----
        
        int i=tbPhieuMuon.getSelectedRow();
        if(i>=0){
            try {
                showinfo(i);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Ticket_GUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Ticket_GUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Ticket_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tbPhieuMuonKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroup;
    private javax.swing.JCheckBox cbChuaTra;
    private javax.swing.JCheckBox cbDaTra;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbLine2;
    private javax.swing.JLabel lbMaPM;
    private MyDesign.MyButton myButton1;
    private JPanel panelBorder1;
    private JPanel panelBorder4;
    private JPanel panelBorder5;
    private javax.swing.JScrollPane spTable;
    private javax.swing.JScrollPane spTicketDetail2;
    private MyDesign.MyTable tbBook;
    private MyDesign.MyTable tbPhieuMuon;
    private javax.swing.JLabel txtDocGia4;
    private javax.swing.JLabel txtDocGia5;
    private javax.swing.JLabel txtHanTra;
    private javax.swing.JLabel txtNgayMuon2;
    private javax.swing.JLabel txtSoLuong2;
    private javax.swing.JLabel txtTacGia2;
    private javax.swing.JLabel txtThuKho2;
    private javax.swing.JLabel txtTienCoc2;
}
