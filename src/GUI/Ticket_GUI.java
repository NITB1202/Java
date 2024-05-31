/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import MyDesign.ScrollBar;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
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
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        loadTicketList(list);
        spTicketDetail2.setVerticalScrollBar(new ScrollBar());
        spTicketDetail2.getVerticalScrollBar().setBackground(Color.WHITE);
        spTicketDetail2.getViewport().setBackground(Color.WHITE);
        p.setBackground(Color.WHITE);
        spTicketDetail2.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        jScrollPane2.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
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
        String idborrow="#LB"+String.valueOf(model.getValueAt(i,0));
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
                    Object[] lost={"Sách "+dem+"                                         Còn sách"};
                Bookmodel.addRow(lost);
            }else{
                Object[] lost={"Sách "+dem+"                                             Mất sách"};
                Bookmodel.addRow(lost);
            }
            Object[] book={"Tên sách:        "+obj.getBookname()};
            Bookmodel.addRow(book);
            Object[] author={"Tác giả:           "+obj.getAuthorname()};
            Bookmodel.addRow(author);
            Object[] num={"Số lượng:        "+obj.getNum()};
            Bookmodel.addRow(num);
            dem++;
        }
//        DefaultComboBoxModel BookName = new DefaultComboBoxModel();
//        BookName.addElement("Chọn sách");
//        for(DetailBC obj : listBook){
//            BookName.addElement(obj.getBookname());
//        }
//        lbBaoMat2.setText("");
//        txtDocGia5.setText("");
//        txtTacGia2.setText("");
//        txtSoLuong2.setText("");
//        jComboBox1.setModel(BookName);
//        jComboBox1.addActionListener(new ActionListener(){
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                for(DetailBC obj : listBook){
//                    if(jComboBox1.getSelectedItem().equals(obj.getBookname())){
//                        if(obj.getLost()==0){
//                            lbBaoMat2.setText("Còn sách");
//                        }else{
//                            lbBaoMat2.setText("Mất sách");
//                        }
//                        txtDocGia5.setText(obj.getBookname());
//                        txtTacGia2.setText(obj.getAuthorname());
//                        txtSoLuong2.setText(String.valueOf(obj.getNum()));
//                    }
//                }
//            }
//        });
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
        panelBorder1 = new MyDesign.PanelBorder();
        jLabel5 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        tbPhieuMuon = new MyDesign.MyTable();
        myButton1 = new MyDesign.MyButton();
        spTicketDetail2 = new javax.swing.JScrollPane();
        panelBorder4 = new MyDesign.PanelBorder();
        lbMaPM = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
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
        panelBorder5 = new MyDesign.PanelBorder();
        cbDaTra = new javax.swing.JCheckBox();
        cbChuaTra = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(127, 127, 127));
        jLabel5.setText("Phiếu mượn");

        spTable.setBorder(null);

        tbPhieuMuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Độc giả", "Ngày mượn", "Thủ kho", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                .addContainerGap())
        );

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

        jLabel23.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
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

        javax.swing.GroupLayout panelBorder4Layout = new javax.swing.GroupLayout(panelBorder4);
        panelBorder4.setLayout(panelBorder4Layout);
        panelBorder4Layout.setHorizontalGroup(
            panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbLine2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelBorder4Layout.createSequentialGroup()
                        .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26))
                        .addGap(39, 39, 39)
                        .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtThuKho2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTienCoc2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder4Layout.createSequentialGroup()
                        .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel24)
                            .addComponent(jLabel30))
                        .addGap(18, 18, 18)
                        .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayMuon2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDocGia4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtHanTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder4Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(223, 223, 223)
                        .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTacGia2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelBorder4Layout.createSequentialGroup()
                                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSoLuong2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDocGia5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(6, 6, 6))))
                    .addGroup(panelBorder4Layout.createSequentialGroup()
                        .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelBorder4Layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(jLabel22))
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(lbMaPM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelBorder4Layout.setVerticalGroup(
            panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addGap(0, 0, 0)
                .addComponent(lbMaPM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtDocGia4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtNgayMuon2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtHanTra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtThuKho2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtTienCoc2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbLine2)
                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder4Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(txtDocGia5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTacGia2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoLuong2))
                    .addGroup(panelBorder4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        spTicketDetail2.setViewportView(panelBorder4);

        cbDaTra.setBackground(new java.awt.Color(246, 250, 255));
        cbDaTra.setForeground(new java.awt.Color(127, 127, 127));
        cbDaTra.setText("Đã trả");
        cbDaTra.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbDaTraItemStateChanged(evt);
            }
        });

        cbChuaTra.setBackground(new java.awt.Color(246, 250, 255));
        cbChuaTra.setForeground(new java.awt.Color(127, 127, 127));
        cbChuaTra.setText("Chưa trả");
        cbChuaTra.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbChuaTraItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout panelBorder5Layout = new javax.swing.GroupLayout(panelBorder5);
        panelBorder5.setLayout(panelBorder5Layout);
        panelBorder5Layout.setHorizontalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbDaTra)
                .addGap(18, 18, 18)
                .addComponent(cbChuaTra)
                .addGap(1, 1, 1))
        );
        panelBorder5Layout.setVerticalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbDaTra, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbChuaTra, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTicketDetail2, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                    .addComponent(panelBorder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(panelBorder5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spTicketDetail2, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
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
    private MyDesign.PanelBorder panelBorder1;
    private MyDesign.PanelBorder panelBorder4;
    private MyDesign.PanelBorder panelBorder5;
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
    // End of variables declaration//GEN-END:variables
}
