package GUI;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BUS.RolePermissionBUS;
import BUS.SupplyCardBUS;
import BUS.SupplyCardWithStaffBUS;
import DAO.BookDAO;
import DAO.PublisherDAO;
import DAO.StaffDAO;
import DAO.SupplyCardDAO;
import DAO.SupplyCardDetailDAO;
import DAO.WarehouseDAO;
import DTO.entities.Account;
import DTO.entities.SupplyCard;
import DTO.entities.SupplyCardDetail;
import DTO.entities.SupplyCardWithStaff;
import MyDesign.ScrollBar;
import connection.ConnectDB;
import MyDesign.MyTable;

public class WareHouse_GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private MyDesign.MyButton btnSearch;
	private JLabel lblNewLabel_1;
	private MyTable tbSach;
	private MyTable tbLichSuNhapHang;
	private MyDesign.MyButton btnNhapSach;
	
    private SupplyCardDAO supplyCardDAO;
    private Account user;
    private RolePermissionBUS rolePermissionBUS;
    private SupplyCardWithStaffBUS supplyCardWithStaffBUS;
    private SupplyCardDetailDAO supplyCardDetailDAO;
    private WarehouseDAO warehouseDAO;
    private WareHouseSearch_Dialog whSDialog ;
    private StaffDAO staffDAO ;
    SupplyCardBUS supplybll = new SupplyCardBUS();
    Vector<SupplyCard> list;
    private ConnectDB connectDB;
    // private SupplyCardDAO supplyCardDAO=new SupplyCardDAO(connectDB);
    private WareHouseImport_Dialog whid;
    private WareHouseSearch_Dialog whsdc;
    // private WareHouseScanner_Dialog whscd;
    private SupplyCardWithStaffBUS scws;
    private List<SupplyCardWithStaff> supplyCardWithStaffList;

	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public WareHouse_GUI(Account user) throws SQLException, IOException, ClassNotFoundException {
        this.list = new Vector<>(supplybll.getAllTicket());
        this.user = user;
        this.rolePermissionBUS = new RolePermissionBUS();
        SupplyCardDAO supplyCardDAO = new SupplyCardDAO(connectDB);
        StaffDAO staffDAO = new StaffDAO();
        WarehouseDAO warehouseDAO = new WarehouseDAO(connectDB);
        BookDAO bookDAO = new BookDAO();
        PublisherDAO publisherDAO = new PublisherDAO(connectDB);
        SupplyCardDetailDAO supplyCardDetailDAO = new SupplyCardDetailDAO(connectDB);
        supplyCardWithStaffList = new ArrayList<>();
        List<SupplyCardDetail> supplyCardDetail = new ArrayList<>();
        supplyCardDetail = supplyCardDetailDAO.getAllSupplyCardDetail();
        supplyCardWithStaffList = supplyCardDAO.getAllSupplyCardWithStaff();
        
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Lịch sử nhập hàng");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel.setBounds(41, 25, 172, 36);
		add(lblNewLabel);
		
		JScrollPane spTable = new JScrollPane();
		spTable.setBounds(41, 80, 732, 212);
		add(spTable);
		
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
		
		tbLichSuNhapHang = new MyDesign.MyTable();
		tbLichSuNhapHang.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 phi\u1EBFu nh\u1EADp", "Ng\u00E0y nh\u1EADp", "Nh\u00E0 cung c\u1EA5p", "T\u1ED5ng chi", "Th\u1EE7 kho"
			}){
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
		tbLichSuNhapHang.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		spTable.setViewportView(tbLichSuNhapHang);
		
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
        // Xử lý dữ liệu và hiển thị trong JTable hoặc thành phần giao diện khác
        DefaultTableModel model = (DefaultTableModel) tbLichSuNhapHang.getModel();

        for (SupplyCardWithStaff supplyCardWithStaff : supplyCardWithStaffList) {
        	  int providerID = supplyCardWithStaff.getSupply_Card().getProvider();
        	    
        	    // Thực hiện truy vấn hoặc phương thức để lấy tên của nhà cung cấp từ ID
        	    String providerName = ""; // Khởi tạo biến chứa tên của nhà cung cấp
        	    // Thực hiện truy vấn hoặc phương thức để lấy tên của nhà cung cấp từ ID
				providerName = SupplyCardDAO.getProviderNameByID(providerID);
				String formattedDate = sdf.format(supplyCardWithStaff.getSupply_Card().getSupDate());
				
            // Tạo một mảng các Object chứa thông tin cần hiển thị
            Object[] rowData = {
            	(Object)(model.getRowCount()+1),
            	formattedDate,
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
                        int id = Integer.parseInt(tbLichSuNhapHang.getValueAt(selectedRow, 0).toString());
                        // Gọi phương thức trong DAO để lấy danh sách chi tiết phiếu nhập dựa trên ngày nhập
                        List<SupplyCardDetail> supplyCardDetails = supplyCardDetailDAO.getSupplyCardDetailsById(id);
                        
                        // Xóa các dòng hiện tại trong bảng tbChiTietPhieuNhap
                        DefaultTableModel model2 = (DefaultTableModel) tbSach.getModel();
                        model2.setRowCount(0);

                        // Thêm các chi tiết phiếu nhập mới vào bảng tbChiTietPhieuNhap
                        for (SupplyCardDetail detail : supplyCardDetails) {
                            Object[] rowData = {
                            		(Object)(model2.getRowCount()+1),
                                    detail.getScID(),
                                    detail.getISBN(),
                                    detail.getNum(),
                                    
                            };
                            model2.addRow(rowData);
                        }
                    }
                }
            }
        });
		
		btnSearch = new MyDesign.MyButton();
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setColorOver(new Color(22, 113, 221));
		btnSearch.setColor(new Color(22, 113, 221));
		btnSearch.setBorderColor(new Color(22, 113, 221));
		btnSearch.setBackground(new Color(22, 113, 221));
		btnSearch.setText("Tìm kiếm");
		btnSearch.setIcon(new ImageIcon(WareHouse_GUI.class.getResource("/Images/search-white.png")));
		btnSearch.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnSearch.setBounds(630, 22, 141, 42);
		btnSearch.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	btnSearchActionPerformed(evt);
	            }
	     });
		add(btnSearch);
		
		lblNewLabel_1 = new JLabel("Chi tiết phiếu nhập");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel_1.setBounds(41, 312, 193, 30);
		add(lblNewLabel_1);
		
		JScrollPane spTable2 = new JScrollPane();
		spTable2.setBounds(41, 353, 725, 193);
		add(spTable2);
		
        spTable2.setVerticalScrollBar(new ScrollBar());
        spTable2.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable2.getViewport().setBackground(Color.WHITE);
		
		tbSach = new MyDesign.MyTable();
		tbSach.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT", "M\u00E3 phi\u1EBFu nh\u1EADp", "ISBN", "S\u1ED1 l\u01B0\u1EE3ng s\u00E1ch nh\u1EADp"
			}){
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
		tbSach.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		spTable2.setViewportView(tbSach);
		
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
		
		btnNhapSach = new MyDesign.MyButton();
		btnNhapSach.setColorOver(new Color(22, 113, 221));
		btnNhapSach.setColor(new Color(22, 113, 221));
		btnNhapSach.setBorderColor(new Color(22, 113, 221));
		btnNhapSach.setBackground(new Color(22, 113, 221));
		btnNhapSach.setText("Nhập sách");
		btnNhapSach.setIcon(new ImageIcon(WareHouse_GUI.class.getResource("/Images/warehouse-white.png")));
		btnNhapSach.setForeground(new Color(255, 255, 255));
		btnNhapSach.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnNhapSach.setBounds(625, 562, 141, 42);
        btnNhapSach.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(whid != null && whid.isVisible()) return;
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
              	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
              	            String formatDate = sdf.format(supplyCardWithStaff.getSupply_Card().getSupDate());

                          // Tạo một mảng các Object chứa thông tin cần hiển thị
                          Object[] rowData = {
                          	(Object)(model.getRowCount()+1),
                          	  formatDate,
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
		add(btnNhapSach);
		
        // Setting button state based on permissions
        if (!rolePermissionBUS.hasPerCreate(this.user.getRoleID(), 4))
            btnNhapSach.setEnabled(false);
	}
	
    public void loadSupplyCardList(Vector<SupplyCard> supplyCards) throws ClassNotFoundException, SQLException {
        DefaultTableModel model = (DefaultTableModel) tbLichSuNhapHang.getModel();
        model.setRowCount(0); // Xóa tất cả các hàng hiện có trong bảng

        // Duyệt qua danh sách SupplyCard và thêm từng dòng vào bảng
        for (SupplyCard supplyCard : supplyCards) {
            int id = supplyCard.getId();
            Timestamp supDate = supplyCard.getSupDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formatDate = sdf.format(supDate);
            String providerName = supplyCard.getSuppliername(); // Lấy tên nhà cung cấp trực tiếp từ SupplyCard
            long tongchi = supplyCard.getTongchi();
            String staffname = supplyCard.getStaffname(); // Lấy tên nhân viên trực tiếp từ SupplyCard

            // Tạo một mảng đối tượng để đại diện cho một dòng trong bảng
            Object[] obj = {id, formatDate, providerName, tongchi, staffname};
            // Thêm dòng vào bảng
            model.addRow(obj);
        }
    }
    
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
		    int id = Integer.parseInt(tbLichSuNhapHang.getValueAt(selectedRow, 0).toString());

		    // Gọi phương thức trong DAO để lấy danh sách chi tiết phiếu nhập dựa trên ngày nhập
		    List<SupplyCardDetail> supplyCardDetails = supplyCardDetailDAO.getSupplyCardDetailsById(id);

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
