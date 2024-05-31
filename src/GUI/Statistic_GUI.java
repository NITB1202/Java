/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.ReaderBUS;
import BUS.RolePermissionBUS;
import BUS.StatisticsBUS;
import DTO.entities.*;

import MyDesign.ScrollBar;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author QUANG DIEN
 */
public class Statistic_GUI extends javax.swing.JPanel {
    private StatisticsBUS sBus;
    private int yearSTT;
    private int quarterSTT;
    private int monthSTT;
    private Account user;
    private RolePermissionBUS rolePermissionBUS;
    DefaultTableModel tableModel;
    private  DecimalFormat decimalFormat = new DecimalFormat("#,###");
    /**
     * Creates new form statistic_GUI
     */
    public Statistic_GUI(Account user) throws ClassNotFoundException, SQLException, IOException {
        initComponents();
        // add row table   
        this.user = user;
        this.rolePermissionBUS = new RolePermissionBUS();
        try {
            sBus = new StatisticsBUS();
            ShowAll();
       } catch (Exception e) {
          e.printStackTrace();
        }
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        if(rolePermissionBUS.hasPerEdit(this.user.getRoleID(), 1)){
            cbThoiGian.setEnabled(true);       
        }
        else cbThoiGian.setEnabled(false);
    }
    
    private void ShowAll(){
       try {
            StatisticDTO stt = new StatisticDTO();
            tableModel = (DefaultTableModel)tbStatistic.getModel();
            ShowTopBook();
            ReaderBUS rBUS = new ReaderBUS();
            Vector<Reader> r = rBUS.getAll();
            //Lấy tất cả thành viên
            lbThanhVienMoi.setText(String.valueOf(r.size()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void ShowTopBook(){
        try {
            Vector<TopBorrowedBook> top = sBus.TopSachMuon();
            tableModel.setRowCount(0);
            int n = 1;
            for (TopBorrowedBook b : top) {
                Object[] r = {n++, b.getNameBook(), b.getBookAuthor(), b.getNXB(), b.getSoLuotMuon()};
                tableModel.addRow(r);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    //tạo menu con
    private void showSelection(JPopupMenu popupMenu) {
        Point location = cbThoiGian.getLocationOnScreen();
        popupMenu.show(cbThoiGian, 0, cbThoiGian.getHeight());
    }
    
    //Tạo menu con cho Selection
    private JPopupMenu creatSelection(int start, int end, String prefix, ActionListener listener) {
            JPopupMenu popupMenu = new JPopupMenu();
            for (int i = start; i <= end; i++) {
                JMenuItem menuItem = new JMenuItem(prefix + i);
                menuItem.setFont(new Font("Arial", Font.PLAIN, 16));
                menuItem.addActionListener(listener);
                popupMenu.add(menuItem);
        }
        return popupMenu;
    }

    //Khi chọn jMenuItem
    private void statisticSelection(ActionEvent event){
        try {
            JMenuItem source = (JMenuItem) (event.getSource());
            String selectedItem = source.getText();
            lbThoiGian.setText(selectedItem);
            
            if (selectedItem.startsWith("Tháng")) {
                monthSTT = Integer.parseInt(selectedItem.substring(6));
                showSelection(creatSelection(2023, 2027, "Năm ", this::yearSelection));
            } 
            else if (selectedItem.startsWith("Quý")) {
                quarterSTT = Integer.parseInt(selectedItem.substring(4));
                showSelection(creatSelection(2023, 2027, "Năm ", this::yearSelection));
            } else if(selectedItem.startsWith("Năm")){
                //Gọi hàm thống kê theo năm
                int year = Integer.parseInt(selectedItem.substring(4));
                if(monthSTT==0&&quarterSTT==0){statisticToYear(year);}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Chọn năm cho JMenuItem
    private void yearSelection(ActionEvent event) {
        lbThoiGian.setText(lbThoiGian.getText() + " - " + ((JMenuItem) event.getSource()).getText());
        JMenuItem source = (JMenuItem) (event.getSource());
        String selectedItem = source.getText();

        if (selectedItem.startsWith("Năm")) {
            String yearString = selectedItem.substring( 4);
            yearSTT = Integer.parseInt(yearString);
        }
        
        //gọi hàm thống kê theo tháng
        if(monthSTT != 0){statisticToMonth(monthSTT, yearSTT);}
        monthSTT=0;
        //gọi hàm thống kê theo quý
        if(quarterSTT != 0){statisticToQuarter(quarterSTT,yearSTT);}
        quarterSTT=0;
    }
    
    //Thống kê theo tháng
    private void statisticToMonth(int monthSelection, int yearSelection) {
        try {
            sBus = new StatisticsBUS();
            Vector<StatisticDTO> datas = sBus.getAll();
            double soTienThu = 0;
            int soLuotMuon = 0;
            int soLuotTra = 0;
            float phanTramTra = 100;
            int soSachMat = 0;

            for (StatisticDTO dt : datas) {
                int thang = dt.getThang();
                int nam = dt.getNam();

                if (thang == monthSelection && nam == yearSelection) {
                    soTienThu += dt.getTienThu();
                    soLuotMuon += dt.getTongPhieuMuon();
                    soLuotTra += dt.getTraDungHan(); // Đổi thành phương thức lấy số lượt trả đúng hạn
                    soSachMat += dt.getSoSachMat();
                }
            }

            if (soLuotMuon != 0) {
                phanTramTra = (soLuotTra * 100) / soLuotMuon;
            }

            lbKhoangThu.setText(decimalFormat.format (soTienThu) + "đ");
            lbSoLuotMuon.setText(String.valueOf(soLuotMuon));
            lbTiLeHoanTra.setText(String.valueOf(phanTramTra) + "%");
            lbSoSachMat.setText(String.valueOf(soSachMat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Thống kê theo quý
    private void statisticToQuarter(int quarterSelection, int yearSelection) {
        try {
            sBus = new StatisticsBUS();
            Vector<StatisticDTO> datas = sBus.getAll();
            double soTienThu = 0;
            int soLuotMuon = 0;
            int soLuotTra = 0;
            float phanTramTra = 100;
            int soSachMat = 0;

            for (StatisticDTO dt : datas) {
                int thang = dt.getThang();
                int nam = dt.getNam();

                // Tính toán quý dựa trên tháng (1-3: Q1, 4-6: Q2, 7-9: Q3, 10-12: Q4)
                int quarter = (thang - 1) / 3 + 1;

                if (quarter == quarterSelection && nam == yearSelection) {
                    soTienThu += dt.getTienThu();
                    soLuotMuon += dt.getTongPhieuMuon();
                    soLuotTra += dt.getTraDungHan();
                    soSachMat += dt.getSoSachMat();
                }
            }

            if (soLuotMuon != 0) {
                phanTramTra = (soLuotTra * 100) / soLuotMuon;
            }

            lbKhoangThu.setText(decimalFormat.format (soTienThu) + "đ");;
            lbSoLuotMuon.setText(String.valueOf(soLuotMuon));
            lbTiLeHoanTra.setText(String.valueOf(phanTramTra) + "%");
            lbSoSachMat.setText(String.valueOf(soSachMat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Thống kê theo năm
    private void statisticToYear(int yearSelection) throws ClassNotFoundException {
        try {
            sBus = new StatisticsBUS();
            Vector<StatisticDTO> datas = sBus.getAll();
            double soTienThu = 0;
            int soLuotMuon = 0;
            int soLuotTra = 0;
            float phanTramTra = 100;
            int soSachMat = 0;

            for (StatisticDTO dt : datas) {
                int nam = dt.getNam();

                // Kiểm tra năm
                if (nam == yearSelection) {
                    soTienThu += dt.getTienThu();
                    soLuotMuon += dt.getTongPhieuMuon();
                    soLuotTra += dt.getTraDungHan();
                    soSachMat += dt.getSoSachMat();
                }
            }

            if (soLuotMuon != 0) {
                phanTramTra = (soLuotTra * 100) / soLuotMuon;
            }

            lbKhoangThu.setText(decimalFormat.format (soTienThu) + "đ");
            lbSoLuotMuon.setText(String.valueOf(soLuotMuon));
            lbTiLeHoanTra.setText(String.valueOf(phanTramTra) + "%");
            lbSoSachMat.setText(String.valueOf(soSachMat));
        } catch (Exception e) {
            e.printStackTrace();
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

        pnThanhVienMoi = new MyDesign.PanelBorder();
        jLabel4 = new javax.swing.JLabel();
        lbThanhVienMoi = new javax.swing.JLabel();
        pnThoiGian = new javax.swing.JPanel();
        lbThoiGian = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        pnSoLuotMuon = new MyDesign.PanelBorder_Statistic_Blue();
        jLabel6 = new javax.swing.JLabel();
        lbSoLuotMuon = new javax.swing.JLabel();
        cbThoiGian = new javax.swing.JComboBox<>();
        pnKhoangThu = new MyDesign.PanelBorder();
        jLabel2 = new javax.swing.JLabel();
        lbKhoangThu = new javax.swing.JLabel();
        pnSoSachMat = new MyDesign.PanelBorder_Statistic_Red();
        jLabel7 = new javax.swing.JLabel();
        lbSoSachMat = new javax.swing.JLabel();
        pnTiLeHoanTra = new MyDesign.PanelBorder();
        jLabel3 = new javax.swing.JLabel();
        lbTiLeHoanTra = new javax.swing.JLabel();
        panelBorder1 = new MyDesign.PanelBorder();
        jLabel5 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        tbStatistic = new MyDesign.MyTable();

        setBackground(new java.awt.Color(255, 255, 255));

        pnThanhVienMoi.setPreferredSize(new java.awt.Dimension(217, 92));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Thành viên đang hoạt động");

        lbThanhVienMoi.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        lbThanhVienMoi.setForeground(new java.awt.Color(22, 113, 221));

        javax.swing.GroupLayout pnThanhVienMoiLayout = new javax.swing.GroupLayout(pnThanhVienMoi);
        pnThanhVienMoi.setLayout(pnThanhVienMoiLayout);
        pnThanhVienMoiLayout.setHorizontalGroup(
            pnThanhVienMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThanhVienMoiLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel4)
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnThanhVienMoiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbThanhVienMoi)
                .addContainerGap())
        );
        pnThanhVienMoiLayout.setVerticalGroup(
            pnThanhVienMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThanhVienMoiLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel4)
                .addGap(0, 0, 0)
                .addComponent(lbThanhVienMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnThoiGian.setBackground(new java.awt.Color(220, 220, 220));

        lbThoiGian.setBackground(new java.awt.Color(255, 255, 255));
        lbThoiGian.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbThoiGian.setForeground(new java.awt.Color(0, 204, 0));
        lbThoiGian.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbThoiGian.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout pnThoiGianLayout = new javax.swing.GroupLayout(pnThoiGian);
        pnThoiGian.setLayout(pnThoiGianLayout);
        pnThoiGianLayout.setHorizontalGroup(
            pnThoiGianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThoiGianLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbThoiGian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnThoiGianLayout.setVerticalGroup(
            pnThoiGianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThoiGianLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbThoiGian, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel1.setText("Thống kê theo");

        pnSoLuotMuon.setPreferredSize(new java.awt.Dimension(218, 92));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Số lượt mượn");

        lbSoLuotMuon.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        lbSoLuotMuon.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnSoLuotMuonLayout = new javax.swing.GroupLayout(pnSoLuotMuon);
        pnSoLuotMuon.setLayout(pnSoLuotMuonLayout);
        pnSoLuotMuonLayout.setHorizontalGroup(
            pnSoLuotMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSoLuotMuonLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addComponent(lbSoLuotMuon)
                .addContainerGap())
        );
        pnSoLuotMuonLayout.setVerticalGroup(
            pnSoLuotMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSoLuotMuonLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnSoLuotMuonLayout.createSequentialGroup()
                .addContainerGap(87, Short.MAX_VALUE)
                .addComponent(lbSoLuotMuon)
                .addGap(5, 5, 5))
        );

        cbThoiGian.setBackground(new java.awt.Color(246, 250, 255));
        cbThoiGian.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cbThoiGian.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tháng", "Quý", "Năm" }));
        cbThoiGian.setBorder(null);
        cbThoiGian.setPreferredSize(new java.awt.Dimension(77, 28));
        cbThoiGian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbThoiGianActionPerformed(evt);
            }
        });

        pnKhoangThu.setPreferredSize(new java.awt.Dimension(445, 92));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Khoảng thu");

        lbKhoangThu.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        lbKhoangThu.setForeground(new java.awt.Color(22, 113, 221));

        javax.swing.GroupLayout pnKhoangThuLayout = new javax.swing.GroupLayout(pnKhoangThu);
        pnKhoangThu.setLayout(pnKhoangThuLayout);
        pnKhoangThuLayout.setHorizontalGroup(
            pnKhoangThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKhoangThuLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 345, Short.MAX_VALUE)
                .addComponent(lbKhoangThu)
                .addGap(14, 14, 14))
        );
        pnKhoangThuLayout.setVerticalGroup(
            pnKhoangThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKhoangThuLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnKhoangThuLayout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addComponent(lbKhoangThu)
                .addContainerGap())
        );

        pnSoSachMat.setPreferredSize(new java.awt.Dimension(218, 92));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(248, 67, 67));
        jLabel7.setText("Số sách mất, hỏng");

        lbSoSachMat.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        lbSoSachMat.setForeground(new java.awt.Color(248, 67, 67));

        javax.swing.GroupLayout pnSoSachMatLayout = new javax.swing.GroupLayout(pnSoSachMat);
        pnSoSachMat.setLayout(pnSoSachMatLayout);
        pnSoSachMatLayout.setHorizontalGroup(
            pnSoSachMatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSoSachMatLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(lbSoSachMat)
                .addContainerGap())
        );
        pnSoSachMatLayout.setVerticalGroup(
            pnSoSachMatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSoSachMatLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnSoSachMatLayout.createSequentialGroup()
                .addContainerGap(87, Short.MAX_VALUE)
                .addComponent(lbSoSachMat)
                .addGap(5, 5, 5))
        );

        pnTiLeHoanTra.setPreferredSize(new java.awt.Dimension(217, 92));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Tỉ lệ hoàn trả");

        lbTiLeHoanTra.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        lbTiLeHoanTra.setForeground(new java.awt.Color(22, 113, 221));

        javax.swing.GroupLayout pnTiLeHoanTraLayout = new javax.swing.GroupLayout(pnTiLeHoanTra);
        pnTiLeHoanTra.setLayout(pnTiLeHoanTraLayout);
        pnTiLeHoanTraLayout.setHorizontalGroup(
            pnTiLeHoanTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTiLeHoanTraLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTiLeHoanTraLayout.createSequentialGroup()
                .addContainerGap(211, Short.MAX_VALUE)
                .addComponent(lbTiLeHoanTra)
                .addContainerGap())
        );
        pnTiLeHoanTraLayout.setVerticalGroup(
            pnTiLeHoanTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTiLeHoanTraLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel3)
                .addGap(0, 0, 0)
                .addComponent(lbTiLeHoanTra, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(127, 127, 127));
        jLabel5.setText("Sách được mượn nhiều nhất");

        spTable.setBorder(null);

        tbStatistic.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên sách", "Tác giả", "Nhà xuất bản", "Số lượt mượn"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(tbStatistic);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(spTable))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnTiLeHoanTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnThanhVienMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnSoSachMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnKhoangThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnSoLuotMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnThoiGian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(cbThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnKhoangThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnSoLuotMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnTiLeHoanTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnThanhVienMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnSoSachMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbThoiGianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbThoiGianActionPerformed
        // TODO add your handling code here:
        String selectedOption = (String) cbThoiGian.getSelectedItem();

        switch (selectedOption) {
            case "Tháng":
            showSelection(creatSelection(1, 12, "Tháng ", this::statisticSelection));
            break;
            case "Quý":
            showSelection(creatSelection(1, 4, "Quý ", this::statisticSelection));
            break;
            case "Năm":
            showSelection(creatSelection(2023, 2027, "Năm ", this::statisticSelection));
            break;
            default:
            break;
        }
    }//GEN-LAST:event_cbThoiGianActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbThoiGian;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lbKhoangThu;
    private javax.swing.JLabel lbSoLuotMuon;
    private javax.swing.JLabel lbSoSachMat;
    private javax.swing.JLabel lbThanhVienMoi;
    private javax.swing.JLabel lbThoiGian;
    private javax.swing.JLabel lbTiLeHoanTra;
    private MyDesign.PanelBorder panelBorder1;
    private MyDesign.PanelBorder pnKhoangThu;
    private MyDesign.PanelBorder_Statistic_Blue pnSoLuotMuon;
    private MyDesign.PanelBorder_Statistic_Red pnSoSachMat;
    private MyDesign.PanelBorder pnThanhVienMoi;
    private javax.swing.JPanel pnThoiGian;
    private MyDesign.PanelBorder pnTiLeHoanTra;
    private javax.swing.JScrollPane spTable;
    private MyDesign.MyTable tbStatistic;
    // End of variables declaration//GEN-END:variables
}
