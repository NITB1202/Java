/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.BorrowCardBUS;
import BUS.RolePermissionBUS;
import BUS.SupplyCardBUS;
import MyDesign.ScrollBar;
import connection.ConnectDB;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import BUS.SupplyCardWithStaffBUS;
import BUS.SupplyCardBUS;
import DAO.BookDAO;
import DAO.BorrowCardDAO;
import DAO.PublisherDAO;
import DAO.StaffDAO;
import DAO.SupplyCardDAO;
import DAO.SupplyCardDetailDAO;
import DAO.WarehouseDAO;
import DTO.entities.Account;
import DTO.entities.BorrowCard;
import DTO.entities.Staff;
import DTO.entities.SupplyCard;
import DTO.entities.SupplyCardDetail;
import DTO.entities.SupplyCardWithStaff;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;


/**
 *
 * @author QUANG DIEN
 */
public class WareHouse_GUI extends javax.swing.JPanel {
    private SupplyCardDAO supplyCardDAO;
    private Account user;
    private RolePermissionBUS rolePermissionBUS;
    private SupplyCardWithStaffBUS supplyCardWithStaffBUS;
    private SupplyCardDetailDAO supplyCardDetailDAO;
    private WarehouseDAO warehouseDAO;
    private WareHouseSearch_Dialog whSDialog ;
    private StaffDAO staffDAO ;
    SupplyCardBUS supplybll= new SupplyCardBUS();
    Vector<SupplyCard> list;

    
	/**
     * Creates new form WareHouse_GUI
     */
    public WareHouse_GUI(Account user) throws SQLException, IOException, ClassNotFoundException {
        initComponents();
        this.list = new Vector<>(supplybll.getAllTicket());
        this.user = user;
        this.rolePermissionBUS = new RolePermissionBUS();

        // Setting up spTable
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p1 = new JPanel();
        p1.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p1);
        spTable.setViewportView(tbLichSuNhapHang);

        // Setting up spTable2
        spTable2.setVerticalScrollBar(new ScrollBar());
        spTable2.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable2.getViewport().setBackground(Color.WHITE);
        JPanel p2 = new JPanel();
        p2.setBackground(Color.WHITE);
        spTable2.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p2);
        spTable2.setViewportView(tbSach);
    }

    public WareHouse_GUI() throws SQLException, IOException, ClassNotFoundException {
        initComponents();

        // Setting up spTable
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p1 = new JPanel();
        p1.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p1);
        spTable.setViewportView(tbLichSuNhapHang);

        // Setting up spTable2
        spTable2.setVerticalScrollBar(new ScrollBar());
        spTable2.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable2.getViewport().setBackground(Color.WHITE);
        JPanel p2 = new JPanel();
        p2.setBackground(Color.WHITE);
        spTable2.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p2);
        spTable2.setViewportView(tbSach);

        // Setting button state based on permissions
        if (rolePermissionBUS.hasPerCreate(this.user.getRoleID(), 4)) {
            btnNhapSach.setEnabled(true);
        } else {
            btnNhapSach.setEnabled(false);
        }
    }
    
    public void loadSupplyCardList(Vector<SupplyCard> supplyCards) throws ClassNotFoundException, SQLException {
        DefaultTableModel model = (DefaultTableModel) tbLichSuNhapHang.getModel();
        model.setRowCount(0); // Xóa tất cả các hàng hiện có trong bảng

        // Duyệt qua danh sách SupplyCard và thêm từng dòng vào bảng
        for (SupplyCard supplyCard : supplyCards) {
            int id = supplyCard.getId();
            Timestamp supDate = supplyCard.getSupDate();
            String providerName = supplyCard.getSuppliername(); // Lấy tên nhà cung cấp trực tiếp từ SupplyCard
            long tongchi = supplyCard.getTongchi();
            String staffname = supplyCard.getStaffname(); // Lấy tên nhân viên trực tiếp từ SupplyCard

            // Tạo một mảng đối tượng để đại diện cho một dòng trong bảng
            Object[] obj = {id, supDate, providerName, tongchi, staffname};
            // Thêm dòng vào bảng
            model.addRow(obj);
        }
    }

  


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     * @throws IOException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() throws SQLException, IOException, ClassNotFoundException {

        panelBorder1 = new MyDesign.PanelBorder();
        jLabel5 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        tbLichSuNhapHang = new MyDesign.MyTable();
        tbSach = new MyDesign.MyTable();
        
        SupplyCardDAO supplyCardDAO = new SupplyCardDAO(connectDB);
        StaffDAO staffDAO = new StaffDAO();
        
        WarehouseDAO warehouseDAO = new WarehouseDAO(connectDB);
        BookDAO bookDAO = new BookDAO();
        PublisherDAO publisherDAO = new PublisherDAO(connectDB);
        
        SupplyCardDetailDAO supplyCardDetailDAO = new SupplyCardDetailDAO(connectDB);
       
        List<SupplyCardWithStaff> supplyCardWithStaffList = new ArrayList<>();
        List<SupplyCardDetail> supplyCardDetail = new ArrayList<>();
        supplyCardDetail = supplyCardDetailDAO.getAllSupplyCardDetail();
        supplyCardWithStaffList = supplyCardDAO.getAllSupplyCardWithStaff();
        jLabel7 = new javax.swing.JLabel();
        spTable2 = new javax.swing.JScrollPane();
        panelBorder_Basic1 = new MyDesign.PanelBorder_Basic();
        jLabel8 = new javax.swing.JLabel();
        btnNhapSach = new MyDesign.MyButton();
        btnSearch = new MyDesign.MyButton();


        
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(127, 127, 127));
        jLabel5.setText("Lịch sử nhập hàng");

        spTable.setBorder(null);

        tbLichSuNhapHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Ngày nhập", "Nhà cung cấp", "Tổng chi", "Thủ kho"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        // Xử lý dữ liệu và hiển thị trong JTable hoặc thành phần giao diện khác
        DefaultTableModel model = (DefaultTableModel) tbLichSuNhapHang.getModel();

        for (SupplyCardWithStaff supplyCardWithStaff : supplyCardWithStaffList) {
        	  int providerID = supplyCardWithStaff.getSupply_Card().getProvider();
        	    
        	    // Thực hiện truy vấn hoặc phương thức để lấy tên của nhà cung cấp từ ID
        	    String providerName = ""; // Khởi tạo biến chứa tên của nhà cung cấp
        	    // Thực hiện truy vấn hoặc phương thức để lấy tên của nhà cung cấp từ ID
				providerName = SupplyCardDAO.getProviderNameByID(providerID);

            // Tạo một mảng các Object chứa thông tin cần hiển thị
            Object[] rowData = {
            	(Object)(model.getRowCount()+1),
                supplyCardWithStaff.getSupply_Card().getSupDate(),
                providerName, 
                supplyCardWithStaff.getSupply_Card().getTongchi(),
                supplyCardWithStaff.getStaff().getName()
            };
            model.addRow(rowData);
        }
        
        tbLichSuNhapHang.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e)
        	{
                if (e.getClickCount() == 1 || e.getClickCount() == 2) {
                    DefaultTableModel model = (DefaultTableModel) tbLichSuNhapHang.getModel();
                    int selectedRow = tbLichSuNhapHang.getSelectedRow();
                    if (selectedRow >= 0) {
                        Object ngayNhap = tbLichSuNhapHang.getValueAt(selectedRow, 1);

                        // Chuyển đổi ngày nhập từ Object sang định dạng phù hợp để truy vấn cơ sở dữ liệu
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String ngayNhapString = dateFormat.format(ngayNhap); // Định dạng ngày nhập

                        // Gọi phương thức trong DAO để lấy danh sách chi tiết phiếu nhập dựa trên ngày nhập
                        List<SupplyCardDetail> supplyCardDetails = supplyCardDetailDAO.getSupplyCardDetailsByDate(ngayNhapString);

                        // Xóa các dòng hiện tại trong bảng tbChiTietPhieuNhap
                        DefaultTableModel model2 = (DefaultTableModel) tbSach.getModel();
                        model2.setRowCount(0);

                        // Thêm các chi tiết phiếu nhập mới vào bảng tbChiTietPhieuNhap
                        for (SupplyCardDetail detail : supplyCardDetails) {
                            Object[] rowData = {
                            		(Object)(model2.getRowCount()+1),
                                    detail.getScID(),
                                    detail.getISBN(),
                                    detail.getNum()
                            };
                            model2.addRow(rowData);
                        }
                    }
                }
            }
        });
        
        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(127, 127, 127));
        jLabel7.setText("Chi tiết phiếu nhập");

        spTable2.setBorder(null);

        tbSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
            		"STT", "Mã phiếu nhập", "Mã ISBN", "Số lượng nhập sách"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    
        DefaultTableModel model2 = (DefaultTableModel) tbSach.getModel();
        model2.setRowCount(0);

        // Thêm các chi tiết phiếu nhập mới vào bảng tbChiTietPhieuNhap
        for (SupplyCardDetail detail : supplyCardDetail) {
            Object[] rowData = {
            		(Object)(model2.getRowCount()+1),
                    detail.getScID(),
                    detail.getISBN(),
                    detail.getNum()
            };
            model2.addRow(rowData);
        }
        
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        
        
        
        javax.swing.GroupLayout panelBorder_Basic1Layout = new javax.swing.GroupLayout(panelBorder_Basic1);
        panelBorder_Basic1Layout.setHorizontalGroup(
        	panelBorder_Basic1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        			.addContainerGap(166, Short.MAX_VALUE)
        			.addComponent(jLabel8, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        panelBorder_Basic1Layout.setVerticalGroup(
        	panelBorder_Basic1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jLabel8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addContainerGap())
        );
        panelBorder_Basic1.setLayout(panelBorder_Basic1Layout);
        panelBorder_Basic1.setVisible(false);
        btnNhapSach.setBackground(new java.awt.Color(22, 113, 221));
        btnNhapSach.setForeground(new java.awt.Color(255, 255, 255));
        btnNhapSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/warehouse-white.png"))); // NOI18N
        btnNhapSach.setText("Nhập sách");
        btnNhapSach.setToolTipText("");
        btnNhapSach.setBorderColor(new java.awt.Color(22, 113, 221));
        btnNhapSach.setColor(new java.awt.Color(22, 113, 221));
        btnNhapSach.setColorClick(new java.awt.Color(153, 204, 255));
        btnNhapSach.setColorOver(new java.awt.Color(22, 113, 221));
        btnNhapSach.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        
        
        btnNhapSach.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				WareHouseImport_Dialog whid;
                try {
                    whid = new WareHouseImport_Dialog(null, getFocusTraversalKeysEnabled());
                    whid.setVisible(true);
                    if (whid != null && !whid.isVisible()) {
    			        // Dialog đã đóng, thực hiện các thao tác tiếp theo
                    	DefaultTableModel model = (DefaultTableModel) tbLichSuNhapHang.getModel();
                    	List<SupplyCardWithStaff> supplyCardWithStaffList = new ArrayList<>();
                    	supplyCardWithStaffList = supplyCardDAO.getAllSupplyCardWithStaff();
                    	model.setRowCount(0);
                    	for (SupplyCardWithStaff supplyCardWithStaff : supplyCardWithStaffList) {
                      	  int providerID = supplyCardWithStaff.getSupply_Card().getProvider();
                      	    
                      	    // Thực hiện truy vấn hoặc phương thức để lấy tên của nhà cung cấp từ ID
                      	    String providerName = ""; // Khởi tạo biến chứa tên của nhà cung cấp
                      	    // Thực hiện truy vấn hoặc phương thức để lấy tên của nhà cung cấp từ ID
              				providerName = SupplyCardDAO.getProviderNameByID(providerID);

                          // Tạo một mảng các Object chứa thông tin cần hiển thị
                          Object[] rowData = {
                          	(Object)(model.getRowCount()+1),
                              supplyCardWithStaff.getSupply_Card().getSupDate(),
                              providerName, 
                              supplyCardWithStaff.getSupply_Card().getTongchi(),
                              supplyCardWithStaff.getStaff().getName()
                          };
                          model.addRow(rowData);
                      }
                    	
                    	DefaultTableModel model2 = (DefaultTableModel) tbSach.getModel();
                    	List<SupplyCardDetail> supplyCardDetail = new ArrayList<>();
                    	supplyCardDetail = supplyCardDetailDAO.getAllSupplyCardDetail();
                        model2.setRowCount(0);
                    	for (SupplyCardDetail i : supplyCardDetail) {
                            // Tạo một mảng các Object chứa thông tin cần hiển thị
                            Object[] rowData = {
                            	(Object)(model2.getRowCount()+1),
                            	i.getScID(),
                                i.getISBN(),
                                i.getNum()
                            };
                            model2.addRow(rowData);
                        }
    			    }
                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
				
			}
		});
        
        
        
        btnSearch.setBackground(new Color(0, 255, 255));
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.setToolTipText("");
        btnSearch.setBorderColor(new java.awt.Color(22, 113, 221));
        btnSearch.setColor(new java.awt.Color(22, 113, 221));
        btnSearch.setColorClick(new java.awt.Color(153, 204, 255));
        btnSearch.setColorOver(new java.awt.Color(22, 113, 221));
        btnSearch.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnSearchActionPerformed(evt);
            }
        });

       


        
        GroupLayout panelBorder1Layout = new GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelBorder1Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(panelBorder1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(btnNhapSach, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelBorder1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelBorder_Basic1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addComponent(spTable, GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
                            .addComponent(spTable2)))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelBorder1Layout.createSequentialGroup()
                    .addContainerGap(17, Short.MAX_VALUE)
                    .addGroup(panelBorder1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, GroupLayout.Alignment.TRAILING)
                        .addComponent(panelBorder_Basic1, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(12, 12, 12)
                    .addComponent(spTable, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel7)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(spTable2, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnNhapSach, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20))
        );


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
    public static boolean isAlpha(String str) {
        return str.matches("[a-zA-Z]+");
    }
    public static boolean containsStringIgnoreCase(String text, String searchString) {
        // Chuyển đổi cả hai chuỗi thành chữ thường trước khi so sánh
        return text.toUpperCase().contains(searchString.toUpperCase());
    }
    public static boolean containsString(String text, String searchString) {
        return text.contains(searchString);
    }
   
    
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {
        WareHouseSearch_Dialog dialog = new WareHouseSearch_Dialog(new javax.swing.JFrame(), true);
            
        dialog.getbtnTimKiem().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String condition = ""; // Chuỗi điều kiện tìm kiếm

                // Xây dựng điều kiện tìm kiếm từ các giá trị trong hộp thoại
                if (dialog.getDate() != null) {
                    condition = "supDate LIKE '%" + dialog.getDate() + "%'";
                }

                if (!dialog.getSupplierName().isEmpty()) {
                    if (!condition.isEmpty()) {
                        condition += " AND ";
                    }
                    condition += "Supplier.name LIKE N'%" + dialog.getSupplierName() + "%'";
                }

                if (!dialog.getWarehouseStaff().isEmpty()) {
                    if (!condition.isEmpty()) {
                        condition += " AND ";
                    }
                    condition += "Staff.name LIKE N'%" + dialog.getWarehouseStaff() + "%'";
                }

                if (supplybll != null) {
                    try {
                        if (condition.isEmpty()) {
                            supplybll.getAllTicket();
                        } else {
                            list = new Vector<SupplyCard>(supplybll.getByCondition(condition));
                            loadSupplyCardList(list);
                        }
                        dialog.setVisible(false);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(new javax.swing.JFrame(), "Không thể tải dữ liệu !", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        dialog.setVisible(true);
    }



    
    public void loadAll() {
        DefaultTableModel model = (DefaultTableModel) tbLichSuNhapHang.getModel();
		int selectedRow = tbLichSuNhapHang.getSelectedRow();
		if (selectedRow >= 0) {
		    Object ngayNhap = tbLichSuNhapHang.getValueAt(selectedRow, 1);

		    // Chuyển đổi ngày nhập từ Object sang định dạng phù hợp để truy vấn cơ sở dữ liệu
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    String ngayNhapString = dateFormat.format(ngayNhap); // Định dạng ngày nhập

		    // Gọi phương thức trong DAO để lấy danh sách chi tiết phiếu nhập dựa trên ngày nhập
		    List<SupplyCardDetail> supplyCardDetails = supplyCardDetailDAO.getSupplyCardDetailsByDate(ngayNhapString);

		    // Xóa các dòng hiện tại trong bảng tbChiTietPhieuNhap
		    DefaultTableModel model2 = (DefaultTableModel) tbSach.getModel();
		    model2.setRowCount(0);

		    // Thêm các chi tiết phiếu nhập mới vào bảng tbChiTietPhieuNhap
		    for (SupplyCardDetail detail : supplyCardDetails) {
		        Object[] rowData = {
		        		(Object)(model2.getRowCount()+1),
		                detail.getScID(),
		                detail.getISBN(),
		                detail.getNum()
		        };
		        model2.addRow(rowData);
		    }
		}
    }
    
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private MyDesign.MyButton btnNhapSach;
    private MyDesign.MyButton btnSearch;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private MyDesign.PanelBorder panelBorder1;
    private MyDesign.PanelBorder_Basic panelBorder_Basic1;
    private javax.swing.JScrollPane spTable;
    private javax.swing.JScrollPane spTable2;
    public MyDesign.MyTable tbSach;
    public MyDesign.MyTable tbLichSuNhapHang;
    private ConnectDB connectDB;
    // private SupplyCardDAO supplyCardDAO=new SupplyCardDAO(connectDB);
    private WareHouseImport_Dialog whid;
    private WareHouseSearch_Dialog whsdc;
    // private WareHouseScanner_Dialog whscd;
    private SupplyCardWithStaffBUS scws;
    /**
     * @wbp.nonvisual location=547,49
     */
}