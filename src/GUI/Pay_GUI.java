package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BUS.PayBUS;
import BUS.ReaderBUS;
import BUS.RolePermissionBUS;
import DTO.entities.Account;
import DTO.entities.Book1;
import DTO.entities.BorrowCard;
import DTO.entities.DetailBC;
import DTO.entities.Reader;
import MyDesign.ScrollBar;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pay_GUI extends JPanel {

	private static final long serialVersionUID = 1L;

	private PayBUS payBUS;
	private Vector<BorrowCard> BCList;
	private BorrowCard selectBC;
	private Vector<DetailBC> bookList;
	private PayReport_Dialog payReportDialog;
	private Date today;
	private Account user;
	private RolePermissionBUS rolePermissionBUS;
	private int slRowIndex = -1;
	private DetailBC selectBook;
	private int tiencoc;
	private int tienphat;
	private ReaderBUS readerBUS;
	
	    public Pay_GUI(Account user) throws Exception 
	    {
	    	
	        initComponents();
	        this.user = user;
	        this.rolePermissionBUS = new RolePermissionBUS();
	        payBUS = new PayBUS();
	        readerBUS = new ReaderBUS();
	        
	        spTable.setVerticalScrollBar(new ScrollBar());
	        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
	        spTable.getViewport().setBackground(Color.WHITE);
	        
	        spSach.setVerticalScrollBar(new ScrollBar());
	        spSach.getVerticalScrollBar().setBackground(Color.WHITE);
	        spSach.getViewport().setBackground(Color.WHITE);

	        spTicketDetail.setVerticalScrollBar(new ScrollBar());
	        spTicketDetail.getVerticalScrollBar().setBackground(Color.WHITE);
	        spTicketDetail.getViewport().setBackground(Color.WHITE);

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
            tbSach.getColumnModel().getColumn(0).setHeaderRenderer(headerRenderer);
	        	        
	        setLayout(null);
	        panelChiTietPM.setLayout(null);
	        panelChiTietPM.add(jLabel7);
	        panelChiTietPM.add(jLabel4);
	        panelChiTietPM.add(jLabel6);
	        panelChiTietPM.add(txtDocGia);
	        panelChiTietPM.add(txtNgayMuon);
	        panelChiTietPM.add(txtNgayHenTra);
	        panelChiTietPM.add(txtTienCoc);
	        panelChiTietPM.add(jLabel24);
	        panelChiTietPM.add(lbLine);
	        panelChiTietPM.add(spSach);
	        panelChiTietPM.add(jLabel3);
	        panelChiTietPM.add(jLabel2);
	        panelChiTietPM.add(lbMaPhieuMuon);
	        panelChiTietPM.add(jLabel1);
	        
	        JPanel panel = new JPanel();
	        panel.setBorder(new LineBorder(new Color(128, 128, 128)));
	        panel.setBackground(new Color(255, 255, 255));
	        panel.setBounds(129, 476, 90, 23);
	        panelChiTietPM.add(panel);
	        panel.setLayout(null);
	        
	        txtTienPhat = new JLabel("0");
	        txtTienPhat.setForeground(new Color(255, 0, 0));
	        txtTienPhat.setFont(new Font("Segoe UI", Font.BOLD, 13));
	        txtTienPhat.setBounds(5, 5, 80, 14);
	        panel.add(txtTienPhat);
	        
	        add(panelMain);
	        
	        panelMain.setLayout(null);
	        panelMain.add(panelPhieuMuon);
	        panelPhieuMuon.setLayout(null);
	        panelPhieuMuon.add(spTable);
	        panelPhieuMuon.add(jLabel5);
	        panelPhieuMuon.add(panel_search);
	        panelMain.add(spTicketDetail);
	        
	        btnNhanSach = new MyDesign.MyButton();
	        btnNhanSach.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		try {
						nhanSach();
					} catch (ClassNotFoundException | SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        	}
	        });
	        btnNhanSach.setColorOver(new Color(22, 113, 255));
	        btnNhanSach.setColor(new Color(22, 113, 255));
	        btnNhanSach.setBorderColor(new Color(22, 113, 255));
	        btnNhanSach.setBackground(new Color(22, 113, 221));
	        btnNhanSach.setText("Nhận sách");
	        btnNhanSach.setForeground(new Color(255, 255, 255));
	        btnNhanSach.setIcon(new ImageIcon(Pay_GUI.class.getResource("/Images/warehouse-white.png")));
	        btnNhanSach.setFont(new Font("Segoe UI", Font.BOLD, 18));
	        btnNhanSach.setBounds(545, 532, 215, 50);
	        panelMain.add(btnNhanSach);
	        
	        if(!rolePermissionBUS.hasPerEdit(this.user.getRoleID(), 2)){
	            btnNhanSach.setEnabled(false);       
	        }
	        
	        today = new Date();
	        String formatDate = new SimpleDateFormat("yyyy-MM-dd").format(today);
	        
	        txtNgayNhan = new JLabel("New label");
	        txtNgayNhan.setBounds(92, 449, 105, 19);
	        panelChiTietPM.add(txtNgayNhan);
	        txtNgayNhan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	        txtNgayNhan.setText(formatDate);
	        
	        JLabel lblNewLabel = new JLabel("Tiền phạt trễ hẹn:");
	        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
	        lblNewLabel.setBounds(10, 478, 113, 20);
	        panelChiTietPM.add(lblNewLabel);
	        showBC();
	    }
	     
	    protected void nhanSach() throws ClassNotFoundException, SQLException, IOException 
	    {
	    	int selectRow = tbPhieuMuon.getSelectedRow();
	    	
	    	if(selectRow < 0 || lbMaPhieuMuon.getText().isBlank())
	    	{
	    		JOptionPane.showMessageDialog(null,"Vui lòng chọn 1 phiếu mượn để tiến hành nhận sách." , "Lỗi", JOptionPane.ERROR_MESSAGE);
	    		return;
	    	}
	    	
	    	Date ngayHenTra = selectBC.getExpReDate();
	        java.sql.Date realReDate = new java.sql.Date(today.getTime());
	        
	    	if(ngayHenTra.before(today))
	    		JOptionPane.showMessageDialog(null,"Trả sách trễ hẹn. Tiền cọc sẽ bị khấu trừ.","Thông báo",JOptionPane.WARNING_MESSAGE);
	    	
	        if(tiencoc >= tienphat)
	        {
	        	tiencoc -= tienphat;
	        	tienphat = 0;
	        	printTienCoc();
	        	txtTienPhat.setText("0");
	        	JOptionPane.showMessageDialog(null, "Nhận sách thành công!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
	        }
	        else
	        {
	        	tienphat -= tiencoc;
	        	tiencoc = 0;
	        	txtTienCoc.setText("0");
	        	printTienPhat();
	        	
	        	int tienPhatCu = readerBUS.getPenalty(selectBC.getReadername());
	        	if(tienPhatCu>0)
	        	{
	        		Reader reader = readerBUS.getReaderByName(selectBC.getReadername()).get(0);
	    	    	int readerID = reader.getPersonID();
	        		payBUS.updatePenalty(readerID, tienPhatCu + tienphat);
	        		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        		String banDateStr = reader.getFineDate().format(formatter);
	        		JOptionPane.showMessageDialog(null, "Tiền trễ hẹn đã được thêm vào tiền phạt.\nVui lòng thanh toán tiền phạt trước ngày "+banDateStr+" để mở khóa thẻ.");
	        	}
	        	else
	        		khoaTaiKhoan(tienphat);
	        }
	        
	    	payBUS.updateDeposit(selectBC.getID(),tiencoc);
    		payBUS.RecoverBook(selectBC);
    		payBUS.setRealReDate(selectBC.getID(),realReDate);
    		
    		//Clear info
    		tbPhieuMuon.clearSelection();
    		DefaultTableModel modelPM =(DefaultTableModel)tbPhieuMuon.getModel();
    		modelPM.removeRow(selectRow);
    		lbMaPhieuMuon.setText("");
    		txtDocGia.setText("");
    		txtNgayMuon.setText("");
    		txtNgayHenTra.setText("");
    		txtTienCoc.setText("");
    		DefaultTableModel modelSach =(DefaultTableModel)tbSach.getModel();
    		modelSach.setRowCount(0);
    		tienphat = 0;
    		txtTienPhat.setText("0");
		}
	    
	    private void printTienCoc()
	    {
	        DecimalFormat decimalFormat = new DecimalFormat("#,###");
	        String formattedNumber = decimalFormat.format(tiencoc);
	        txtTienCoc.setText(formattedNumber);
	    }
	    
	    private void printTienPhat() 
	    {
	        DecimalFormat decimalFormat = new DecimalFormat("#,###");
	        String formattedNumber = decimalFormat.format(tienphat);
	        txtTienPhat.setText(formattedNumber);
	    }

		private void showBC() throws Exception
	    {
	    	BCList = payBUS.getAll();   
            for (BorrowCard bc : BCList) 
            {
                Object[] row = {bc.getID(), bc.getReadername(), bc.getStartDate(), bc.getExpReDate(), bc.getStaffname()};
                tbPhieuMuon.addRow(row); 
            }
	    }
	    
	    public void showBooks() throws ClassNotFoundException, SQLException, IOException, Exception
	    {
	    	
	        String bcID ="#PM"+selectBC.getID();
	        lbMaPhieuMuon.setText(bcID);
	        txtDocGia.setText(selectBC.getReadername());
	        txtNgayMuon.setText(selectBC.getStartDate().toString());
	        txtNgayHenTra.setText(selectBC.getExpReDate().toString());
	        tiencoc = (int)selectBC.getdeposit();
	        printTienCoc();
	        bookList = new Vector<DetailBC>(selectBC.getListBook());
	        
	        DefaultTableModel model = (DefaultTableModel) tbSach.getModel();
	        model.setRowCount(0);
	        
            DefaultTableCellRenderer customRenderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value,
                                                               boolean isSelected, boolean hasFocus, int row, int column) {
                    Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    if (row%4 == 3)
                    {
                        comp.setBackground(new Color(255,204,204));
                        comp.setForeground(Color.red); 
                        comp.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    }
                    else {
                        comp.setBackground(Color.WHITE);
                        comp.setForeground(Color.BLACK);
                    }
                    return comp;
                }
            };
	        for(int i = 0; i< bookList.size();i++)
	        {
	            Object[] book={"Tên sách: "+bookList.get(i).getBookname()};
	            model.addRow(book);
	            Object[] author={"Tác giả: "+bookList.get(i).getAuthorname().get(0)};
	            model.addRow(author);
	            Object[] num={"Số lượng: "+bookList.get(i).getNum()};
	            model.addRow(num);
	            Object[] lost={"                Báo mất sách"};
	            model.addRow(lost);
	        }
	        
	        tbSach.getColumnModel().getColumn(0).setCellRenderer(customRenderer);       
	    }
	    
	    private void initComponents() {

	        panelMain = new javax.swing.JPanel();
	        panelMain.setBounds(0, 0, 829, 628);
	        panelPhieuMuon = new JPanel();
	        panelPhieuMuon.setBounds(10, 11, 498, 606);
	        panelPhieuMuon.setBackground(new Color(255, 255, 255));
	        jLabel5 = new javax.swing.JLabel();
	        jLabel5.setBounds(10, 41, 109, 24);
	        spTable = new javax.swing.JScrollPane();
	        spTable.setBounds(10, 86, 478, 486);
	        tbPhieuMuon = new MyDesign.MyTable();
	        panel_search = new JPanel();
	        panel_search.setBounds(241, 30, 247, 35);
	        panel_search.setBorder(new LineBorder(new Color(128, 128, 128)));
	        panel_search.setBackground(new Color(255, 255, 255));
	        image = new javax.swing.JLabel();
	        image.setBounds(217, 5, 24, 24);
	        searchTxt = new MyDesign.SearchText();
	        searchTxt.setHint("Tìm tên độc giả...");
	        searchTxt.setBounds(6, 5, 200, 26);
	        searchTxt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
	        spTicketDetail = new javax.swing.JScrollPane();
	        spTicketDetail.setBounds(535, 11, 240, 510);
	        panelChiTietPM = new JPanel();
	        panelChiTietPM.setBackground(new Color(255, 255, 255));
	        lbMaPhieuMuon = new javax.swing.JLabel();
	        lbMaPhieuMuon.setBounds(0, 25, 235, 36);
	        jLabel3 = new javax.swing.JLabel();
	        jLabel3.setBounds(10, 83, 54, 18);
	        jLabel2 = new javax.swing.JLabel();
	        jLabel2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
	        jLabel2.setBounds(74, 62, 98, 16);
	        jLabel1 = new javax.swing.JLabel();
	        jLabel1.setBounds(0, 11, 235, 14);
	        txtDocGia = new javax.swing.JLabel();
	        txtDocGia.setFont(new Font("Segoe UI", Font.PLAIN, 13));
	        txtDocGia.setBounds(67, 83, 178, 18);
	        jLabel4 = new javax.swing.JLabel();
	        jLabel4.setBounds(10, 106, 86, 18);
	        jLabel6 = new javax.swing.JLabel();
	        jLabel6.setBounds(10, 130, 90, 18);
	        jLabel7 = new javax.swing.JLabel();
	        jLabel7.setBounds(10, 152, 56, 18);
	        txtNgayMuon = new javax.swing.JLabel();
	        txtNgayMuon.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	        txtNgayMuon.setBounds(92, 106, 105, 18);
	        txtNgayHenTra = new javax.swing.JLabel();
	        txtNgayHenTra.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	        txtNgayHenTra.setBounds(100, 130, 120, 18);
	        txtTienCoc = new javax.swing.JLabel();
	        txtTienCoc.setBounds(70, 152, 102, 18);
	        lbLine = new javax.swing.JLabel();
	        lbLine.setBounds(10, 183, 209, 6);
	        jLabel24 = new javax.swing.JLabel();
	        jLabel24.setBounds(10, 452, 86, 16);
	        spSach = new javax.swing.JScrollPane();
	        spSach.setBounds(10, 200, 210, 241);
	        tbSach = new javax.swing.JTable();
	        tbSach.setFont(new Font("Segoe UI", Font.PLAIN, 12));

	        setBackground(new java.awt.Color(255, 255, 255));

	        panelMain.setBackground(new java.awt.Color(255, 255, 255));

	        jLabel5.setFont(new Font("SansSerif", Font.BOLD, 18)); // NOI18N
	        jLabel5.setForeground(new Color(0, 0, 0));
	        jLabel5.setText("Phiếu mượn");

	        spTable.setBorder(null);

	        tbPhieuMuon.setModel(new DefaultTableModel(
	        	new Object[][] {
	        	},
	        	new String[] {
	        		"ID", "\u0110\u1ED9c gi\u1EA3", "Ng\u00E0y m\u01B0\u1EE3n", "Ng\u00E0y h\u1EB9n tr\u1EA3", "Th\u1EE7 th\u01B0"
	        	}){
	            boolean[] canEdit = new boolean[]{
		                false, false, false, false, false, false
		            };

		            public boolean isCellEditable(int rowIndex, int columnIndex) {
		                return canEdit[columnIndex];
		            }

		        });
	        tbPhieuMuon.getColumnModel().getColumn(0).setPreferredWidth(35);
	        tbPhieuMuon.getColumnModel().getColumn(0).setMinWidth(35);
	        tbPhieuMuon.getColumnModel().getColumn(1).setPreferredWidth(150);
	        tbPhieuMuon.getColumnModel().getColumn(1).setMinWidth(150);
	        tbPhieuMuon.getColumnModel().getColumn(2).setPreferredWidth(100);
	        tbPhieuMuon.getColumnModel().getColumn(2).setMinWidth(100);
	        tbPhieuMuon.getColumnModel().getColumn(3).setPreferredWidth(100);
	        tbPhieuMuon.getColumnModel().getColumn(3).setMinWidth(100);
	        tbPhieuMuon.getColumnModel().getColumn(4).setPreferredWidth(100);
	        tbPhieuMuon.getColumnModel().getColumn(4).setMinWidth(100);
	        tbPhieuMuon.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	                try {
						selectPhieuMuon();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        });
	        spTable.setViewportView(tbPhieuMuon);

	        image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N

	        searchTxt.addKeyListener(new java.awt.event.KeyAdapter() {
	            public void keyReleased(java.awt.event.KeyEvent evt) {
	                try {
						searchPhieuMuon();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        });
	        panel_search.setLayout(null);
	        panel_search.add(searchTxt);
	        panel_search.add(image);

	        spTicketDetail.setBackground(new java.awt.Color(246, 250, 255));
	        spTicketDetail.setBorder(null);

	        panelChiTietPM.setPreferredSize(new Dimension(217, 300));

	        lbMaPhieuMuon.setFont(new Font("Segoe UI", Font.BOLD, 24)); // NOI18N
	        lbMaPhieuMuon.setForeground(new java.awt.Color(22, 113, 221));
	        lbMaPhieuMuon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

	        jLabel3.setFont(new Font("Segoe UI", Font.BOLD, 13)); // NOI18N
	        jLabel3.setText("Độc giả:");

	        jLabel2.setForeground(new java.awt.Color(127, 127, 127));
	        jLabel2.setText("Thông tin cơ bản");

	        jLabel1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12)); // NOI18N
	        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
	        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        jLabel1.setText("MÃ PHIẾU MƯỢN");

	        jLabel4.setFont(new Font("Segoe UI", Font.BOLD, 13)); // NOI18N
	        jLabel4.setText("Ngày mượn:");

	        jLabel6.setFont(new Font("Segoe UI", Font.BOLD, 13)); // NOI18N
	        jLabel6.setText("Ngày hẹn trả:");

	        jLabel7.setFont(new Font("Segoe UI", Font.BOLD, 13)); // NOI18N
	        jLabel7.setText("Tiền cọc:");

	        txtTienCoc.setFont(new Font("SansSerif", Font.PLAIN, 13)); // NOI18N

	        lbLine.setForeground(new java.awt.Color(204, 204, 204));
	        lbLine.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)));

	        jLabel24.setFont(new Font("Segoe UI", Font.BOLD, 13)); // NOI18N
	        jLabel24.setText("Ngày Nhận:");

	        tbSach.setModel(new DefaultTableModel(
	        	new Object[][] {
	        	},
	        	new String[] {
	        		"C\u00E1c s\u00E1ch \u0111\u00E3 m\u01B0\u1EE3n:"
	        	}){
	            boolean[] canEdit = new boolean[]{
		                false, false, false, false, false, false
		            };

		            public boolean isCellEditable(int rowIndex, int columnIndex) {
		                return canEdit[columnIndex];
		            }

		        });
	        tbSach.getColumnModel().getColumn(0).setPreferredWidth(210);
	        tbSach.getColumnModel().getColumn(0).setMinWidth(210);
	        tbSach.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	                baoMatSach();
	            }
	        });
	        spSach.setViewportView(tbSach);

	        spTicketDetail.setViewportView(panelChiTietPM);
	    }

	    private void searchPhieuMuon() throws Exception 
	    {    
			String searchText = searchTxt.getText().toLowerCase();
			//Clear table
			DefaultTableModel model = (DefaultTableModel) tbPhieuMuon.getModel();
			model.setRowCount(0);
			
			if(!searchText.isBlank())
			{
				for(int i = 0; i < BCList.size(); i++)
				{
					BorrowCard bc = BCList.get(i);
					String readerName = bc.getReadername().toLowerCase();
					if(readerName.contains(searchText))
						tbPhieuMuon.addRow(new Object[] {bc.getID(),bc.getReadername(),bc.getStartDate(),bc.getExpReDate(),bc.getStaffname()} );
				}
			}
			else
				showBC();
	    }

	    private void selectPhieuMuon() throws ClassNotFoundException, SQLException, IOException, Exception 
	    {
            int selectIndex = tbPhieuMuon.getSelectedRow();
            int selectedId = Integer.parseInt(tbPhieuMuon.getValueAt(selectIndex, 0).toString());
            for (BorrowCard card : BCList) 
            {
                if (card.getID() == selectedId) 
                {
                    selectBC = card;
                    break;
                }
            }
            showBooks();
            tinhTienPhat();
	    }

	    private void tinhTienPhat() throws ClassNotFoundException, SQLException, IOException 
	    {
	    	tienphat = 0;
			int phiTre = 1000;
			Date ngayHenTra = selectBC.getExpReDate();
			
			if(today.after(ngayHenTra) && !today.equals(ngayHenTra))
			{
		        long khoangCach = today.getTime() - ngayHenTra.getTime();
		        long soNgayTre = TimeUnit.DAYS.convert(khoangCach, TimeUnit.MILLISECONDS);
				tienphat += (int) (phiTre*soNgayTre); 
			}
			
			printTienPhat();
		}

		private void baoMatSach()
	    {
	    	int selectedRow = tbSach.getSelectedRow();
	    	if(selectedRow%4 == 3)
	    	{
	    		String selectBookName = tbSach.getValueAt(selectedRow-3, 0).toString();
	    		slRowIndex = selectedRow-1;
	    		selectBookName = selectBookName.replace("Tên sách: ", "");
	    		for(DetailBC book : bookList)
	    		{	
	    			if(book.getBookname().equals(selectBookName))
	    			{
	    				selectBook = book;
	    				if(payReportDialog == null || !payReportDialog.isShowing())
	    				{
		    	    		payReportDialog = new PayReport_Dialog(selectBC,book,this);
		                    payReportDialog.setVisible(true);
		                    break;
	    				}
	    			}
	    		}
	    	}
	    }
	    
	    public void updateTBSach(int slMat)
	    {
	    	int newNum = selectBook.getNum() - slMat;
	    	tbSach.setValueAt("Số lượng: " + newNum, slRowIndex, 0);
	    	
	    	slRowIndex = -1;
	    	selectBook = null;
	    }
	    
	    public void updateTienCoc(int tienMatSach) throws ClassNotFoundException, SQLException, IOException
	    {
	    	tiencoc -= tienMatSach;
	    	
	    	
	    	if(tiencoc < 0)
	    	{
	    		khoaTaiKhoan(Math.abs(tiencoc));
	    		tiencoc = 0;
	    	}
	    	printTienCoc();
	    	payBUS.updateDeposit(selectBC.getID(),tiencoc);
	    }
	    
	    public void khoaTaiKhoan(int tienPhat) throws ClassNotFoundException, SQLException, IOException
	    {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(today);
	        cal.add(Calendar.DATE, 7);
	        java.sql.Date banDate = new java.sql.Date((cal.getTime()).getTime());
	        String banDateStr = new SimpleDateFormat("dd/MM/yyyy").format(banDate);
	        JOptionPane.showMessageDialog(null, "Thẻ bị khóa. Vui lòng thanh toán tiền phạt trước ngày "+banDateStr+" để mở khóa thẻ.", "Thông báo",JOptionPane.INFORMATION_MESSAGE);
	    	int readerID = readerBUS.getReaderByName(selectBC.getReadername()).get(0).getPersonID();
	        payBUS.updatePenalty(readerID, tienPhat);
	        payBUS.banAcc(selectBC.getID(), banDate);
	    }
	    
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel2;
	    private javax.swing.JLabel jLabel24;
	    private javax.swing.JLabel jLabel3;
	    private javax.swing.JLabel jLabel4;
	    private javax.swing.JLabel jLabel5;
	    private javax.swing.JLabel jLabel6;
	    private javax.swing.JLabel jLabel7;
	    private javax.swing.JLabel image;
	    private javax.swing.JPanel panelMain;
	    private javax.swing.JLabel lbLine;
	    private javax.swing.JLabel lbMaPhieuMuon;
	    private JPanel panelPhieuMuon;
	    private JPanel panelChiTietPM;
	    private JPanel panel_search;
	    private javax.swing.JScrollPane spTable;
	    private javax.swing.JScrollPane spSach;
	    private javax.swing.JScrollPane spTicketDetail;
	    private javax.swing.JTable tbSach;
	    private MyDesign.MyTable tbPhieuMuon;
	    private javax.swing.JLabel txtDocGia;
	    private javax.swing.JLabel txtNgayMuon;
	    private MyDesign.SearchText searchTxt;
	    private javax.swing.JLabel txtNgayHenTra;
	    private javax.swing.JLabel txtTienCoc;
	    private MyDesign.MyButton btnNhanSach;
	    private JLabel txtNgayNhan;
	    private JLabel txtTienPhat;
}
