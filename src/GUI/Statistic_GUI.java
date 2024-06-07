package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import BUS.ReaderBUS;
import BUS.RolePermissionBUS;
import BUS.StatisticsBUS;
import DTO.entities.Account;
import DTO.entities.Reader;
import DTO.entities.StatisticDTO;
import DTO.entities.TopBorrowedBook;
import MyDesign.ScrollBar;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

public class Statistic_GUI extends JPanel {

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
    @SuppressWarnings("serial")
	private void initComponents() {

        pnThanhVienMoi = new MyDesign.PanelBorder();
        jLabel4 = new javax.swing.JLabel();
        lbThanhVienMoi = new javax.swing.JLabel();
        pnThoiGian = new MyDesign.PanelBorder();
        lbThoiGian = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        pnSoLuotMuon = new MyDesign.PanelBorder_Statistic_Blue();
        jLabel6 = new javax.swing.JLabel();
        lbSoLuotMuon = new javax.swing.JLabel();
        cbThoiGian = new javax.swing.JComboBox<>();
        pnKhoangThu = new MyDesign.PanelBorder();
        pnKhoangThu.setBorder(null);
        pnKhoangThu.setBackground(new Color(183, 230, 255));
        jLabel2 = new javax.swing.JLabel();
        lbKhoangThu = new javax.swing.JLabel();
        pnSoSachMat = new MyDesign.PanelBorder_Statistic_Red();
        pnSoSachMat.setBackground(new Color(255, 204, 204));
        jLabel7 = new javax.swing.JLabel();
        lbSoSachMat = new javax.swing.JLabel();
        pnTiLeHoanTra = new MyDesign.PanelBorder();
        jLabel3 = new javax.swing.JLabel();
        lbTiLeHoanTra = new javax.swing.JLabel();
        panelBorder1 = new JPanel();
        panelBorder1.setBackground(new Color(255, 255, 255));
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

        pnThoiGian.setBackground(new Color(192, 192, 192));

        lbThoiGian.setBackground(new java.awt.Color(255, 255, 255));
        lbThoiGian.setFont(new Font("SansSerif", Font.BOLD, 18)); // NOI18N
        lbThoiGian.setForeground(new Color(0, 0, 0));
        lbThoiGian.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbThoiGian.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout pnThoiGianLayout = new javax.swing.GroupLayout(pnThoiGian);
        pnThoiGianLayout.setHorizontalGroup(
        	pnThoiGianLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, pnThoiGianLayout.createSequentialGroup()
        			.addContainerGap(87, Short.MAX_VALUE)
        			.addComponent(lbThoiGian, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        pnThoiGianLayout.setVerticalGroup(
        	pnThoiGianLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(pnThoiGianLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lbThoiGian, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        			.addContainerGap())
        );
        pnThoiGian.setLayout(pnThoiGianLayout);

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

        jLabel5.setFont(new Font("SansSerif", Font.BOLD, 18)); // NOI18N
        jLabel5.setForeground(new Color(0, 0, 0));
        jLabel5.setText("Sách được mượn nhiều nhất");

        spTable.setBorder(null);

        tbStatistic.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"STT", "T\u00EAn s\u00E1ch", "T\u00E1c gi\u1EA3", "Nh\u00E0 xu\u1EA5t b\u1EA3n", "S\u1ED1 l\u01B0\u1EE3ng m\u01B0\u1EE3n"
        	})
        {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

        });
        tbStatistic.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbStatistic.getColumnModel().getColumn(0).setMinWidth(50);
        tbStatistic.getColumnModel().getColumn(0).setMaxWidth(50);
        tbStatistic.getColumnModel().getColumn(1).setPreferredWidth(300);
        tbStatistic.getColumnModel().getColumn(1).setMinWidth(300);
        tbStatistic.getColumnModel().getColumn(1).setMaxWidth(300);
        tbStatistic.getColumnModel().getColumn(2).setPreferredWidth(120);
        tbStatistic.getColumnModel().getColumn(3).setPreferredWidth(110);
        tbStatistic.getColumnModel().getColumn(4).setPreferredWidth(90);
        spTable.setViewportView(tbStatistic);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1Layout.setHorizontalGroup(
        	panelBorder1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(panelBorder1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(panelBorder1Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jLabel5)
        				.addComponent(spTable, GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE))
        			.addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
        	panelBorder1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(panelBorder1Layout.createSequentialGroup()
        			.addComponent(jLabel5)
        			.addGap(16)
        			.addComponent(spTable, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
        			.addContainerGap())
        );
        panelBorder1.setLayout(panelBorder1Layout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(28)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(pnTiLeHoanTra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(pnThanhVienMoi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        				.addComponent(panelBorder1, GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(pnKhoangThu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addGroup(layout.createSequentialGroup()
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(jLabel1)
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addComponent(cbThoiGian, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)))
        					.addPreferredGap(ComponentPlacement.UNRELATED, 47, Short.MAX_VALUE)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(pnThoiGian, 0, 0, Short.MAX_VALUE)
        						.addComponent(pnSoSachMat, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(pnSoLuotMuon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        					.addGap(43)))
        			.addGap(28))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(15)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jLabel1)
        						.addComponent(cbThoiGian, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(8)
        					.addComponent(pnThoiGian, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(pnSoLuotMuon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(pnKhoangThu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(15)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(pnTiLeHoanTra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(pnThanhVienMoi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(pnSoSachMat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addComponent(panelBorder1, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
        			.addGap(10))
        );
        this.setLayout(layout);
    }

    private void cbThoiGianActionPerformed(java.awt.event.ActionEvent evt) {
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
    private JPanel panelBorder1;
    private MyDesign.PanelBorder pnKhoangThu;
    private MyDesign.PanelBorder_Statistic_Blue pnSoLuotMuon;
    private MyDesign.PanelBorder_Statistic_Red pnSoSachMat;
    private MyDesign.PanelBorder pnThanhVienMoi;
    private MyDesign.PanelBorder pnThoiGian;
    private MyDesign.PanelBorder pnTiLeHoanTra;
    private javax.swing.JScrollPane spTable;
    private MyDesign.MyTable tbStatistic;
    // End of variables declaration//GEN-END:variables
}
