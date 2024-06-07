package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import BUS.BookBUS;
import BUS.BorrowCardBUS;
import BUS.DetailBCBUS;
import BUS.ReaderBUS;
import BUS.RolePermissionBUS;
import DTO.entities.Account;
import DTO.entities.Book1;
import DTO.entities.Reader;
import MyDesign.MyButton;
import MyDesign.ScrollBar;

import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import MyDesign.MyTable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import MyDesign.SearchText;

public class Borrow_GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private MyButton btnAdd;
	private MyButton btnSubtract;
	private MyButton btnAddBook;
	private MyButton btnBorrow;
	private MyDesign.MyTable tbSachKhaDung;
	private JScrollPane scrollPane_kd;
	private JTextField textField_num;
	
	private int sl = 0;
	private int max = 0;
	private int tiencoc = 0 ;
	private BookBUS bookBUS;
	private ReaderBUS readerBUS;
	private ArrayList<Book1> bookList;
	private MyTable tbSachDaChon;
	private JLabel bookImage;
	private JLabel info;
	private JComboBox comboBox_reader;
	private JDateChooser dateChooser;
	private JLabel tiencocTxt;
	private SearchText searchTxt;
	private Date expMinDate;
	private Date expMaxDate;
	private Account user;
	private int readerBorrowNum = 0;

	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("serial")
	public Borrow_GUI(Account user) throws ClassNotFoundException, SQLException, IOException {
		this.user = user;
		setBackground(new Color(255, 255, 255));
		
		JLabel lb_book1 = new JLabel("Sách khả dụng");
		lb_book1.setBounds(24, 25, 156, 36);
		lb_book1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel lbl_book2 = new JLabel("Sách đã chọn");
		lbl_book2.setBounds(24, 330, 127, 23);
		lbl_book2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JScrollPane scrollPane_dc = new JScrollPane();
		scrollPane_dc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		scrollPane_dc.setBounds(24, 364, 490, 215);
		
		scrollPane_kd = new JScrollPane();
		scrollPane_kd.setFont(new Font("Segoe UI", Font.BOLD, 13));
		scrollPane_kd.setBorder(null);
		scrollPane_kd.setBackground(new Color(255, 255, 255));
		scrollPane_kd.setBounds(24, 72, 490, 245);
		setLayout(null);
		add(lb_book1);
		add(scrollPane_kd);
		
		tbSachKhaDung = new MyDesign.MyTable();
		tbSachKhaDung.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					chooseBook();
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
		tbSachKhaDung.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT", "ISBN", "T\u00EAn s\u00E1ch", "T\u00E1c gi\u1EA3", "NXB", "C\u00F2n l\u1EA1i"
			})
		{
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

        });
		tbSachKhaDung.getColumnModel().getColumn(0).setPreferredWidth(35);
		tbSachKhaDung.getColumnModel().getColumn(0).setMinWidth(35);
		tbSachKhaDung.getColumnModel().getColumn(1).setPreferredWidth(100);
		tbSachKhaDung.getColumnModel().getColumn(1).setMinWidth(100);
		tbSachKhaDung.getColumnModel().getColumn(2).setPreferredWidth(125);
		tbSachKhaDung.getColumnModel().getColumn(2).setMinWidth(125);
		tbSachKhaDung.getColumnModel().getColumn(3).setPreferredWidth(100);
		tbSachKhaDung.getColumnModel().getColumn(3).setMinWidth(100);
		tbSachKhaDung.getColumnModel().getColumn(4).setPreferredWidth(70);
		tbSachKhaDung.getColumnModel().getColumn(4).setMinWidth(70);
		tbSachKhaDung.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		scrollPane_kd.setViewportView(tbSachKhaDung);
		add(lbl_book2);
		add(scrollPane_dc);
		
		tbSachDaChon = new MyDesign.MyTable();
		tbSachDaChon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					modifyBook();
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		tbSachDaChon.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT", "ISBN", "T\u00EAn s\u00E1ch", "T\u00E1c gi\u1EA3", "NXB", "SL"
			})
			{
	            boolean[] canEdit = new boolean[]{
	                false, false, false, false, false, false
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit[columnIndex];
	            }

	        });
		tbSachDaChon.getColumnModel().getColumn(0).setPreferredWidth(35);
		tbSachDaChon.getColumnModel().getColumn(0).setMinWidth(35);
		tbSachDaChon.getColumnModel().getColumn(1).setPreferredWidth(100);
		tbSachDaChon.getColumnModel().getColumn(1).setMinWidth(100);
		tbSachDaChon.getColumnModel().getColumn(2).setPreferredWidth(125);
		tbSachDaChon.getColumnModel().getColumn(2).setMinWidth(125);
		tbSachDaChon.getColumnModel().getColumn(3).setPreferredWidth(100);
		tbSachDaChon.getColumnModel().getColumn(3).setMinWidth(100);
		tbSachDaChon.getColumnModel().getColumn(4).setPreferredWidth(70);
		tbSachDaChon.getColumnModel().getColumn(4).setMinWidth(70);
		tbSachDaChon.getColumnModel().getColumn(5).setPreferredWidth(55);
		tbSachDaChon.getColumnModel().getColumn(5).setMinWidth(55);
		tbSachDaChon.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		scrollPane_dc.setViewportView(tbSachDaChon);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(566, 340, 224, 257);
		add(panel);
		panel.setLayout(null);
		
		btnAddBook = new MyDesign.MyButton();
		btnAddBook.setColorClick(new Color(166, 202, 255));
		btnAddBook.setBorderColor(new Color(215, 231, 255));
		btnAddBook.setColorOver(new Color(215, 231, 255));
		btnAddBook.setColor(new Color(215, 231, 255));
		btnAddBook.setText("Thêm");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(btnAddBook.getText().equals("Thêm"))
						addBook();
					else
						updateBook();
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
		btnAddBook.setBounds(10, 43, 214, 36);
		panel.add(btnAddBook);
		btnAddBook.setBorder(null);
		btnAddBook.setBackground(new Color(215, 231, 255));
		btnAddBook.setForeground(new Color(22, 113, 221));
		btnAddBook.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnAddBook.setIcon(new ImageIcon(Borrow_GUI.class.getResource("/Images/nav-warehouse.png")));
		btnAddBook.setActionCommand("");
		
		JLabel lbl_reader = new JLabel("Độc giả");
		lbl_reader.setBounds(10, 100, 71, 23);
		panel.add(lbl_reader);
		lbl_reader.setFont(new Font("Segoe UI", Font.BOLD, 14));
		
		comboBox_reader = new JComboBox();
		comboBox_reader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					readerBorrowNum = readerBUS.getNumOfBorrowBook(comboBox_reader.getSelectedItem().toString());
					//Reset value
					DefaultTableModel model = (DefaultTableModel)tbSachDaChon.getModel();
					model.setRowCount(0);
					tiencoc = 0;
					tiencocTxt.setText(String.valueOf(tiencoc));
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		comboBox_reader.setBounds(79, 100, 145, 24);
		panel.add(comboBox_reader);
		comboBox_reader.setBackground(new Color(255, 255, 255));
		comboBox_reader.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		comboBox_reader.setBorder(null);
		
		tiencocTxt = new JLabel("");
		tiencocTxt.setBounds(79, 134, 145, 23);
		panel.add(tiencocTxt);
		tiencocTxt.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tiencocTxt.setBorder(new LineBorder(new Color(128, 128, 128)));
		tiencocTxt.setBackground(new Color(255, 255, 255));
		
		JLabel lbl_reader_1 = new JLabel("Tiền cọc");
		lbl_reader_1.setBounds(10, 134, 71, 23);
		panel.add(lbl_reader_1);
		lbl_reader_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		
		JLabel lbl_reader_1_1 = new JLabel("Ngày trả");
		lbl_reader_1_1.setBounds(10, 168, 59, 23);
		panel.add(lbl_reader_1_1);
		lbl_reader_1_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		
		dateChooser = new JDateChooser();
		dateChooser.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		dateChooser.getCalendarButton().setBackground(new Color(255, 255, 255));
		dateChooser.setBorder(null);
		dateChooser.setBounds(79, 168, 145, 23);
		panel.add(dateChooser);
		dateChooser.setDateFormatString("y-MM-d");
		dateChooser.setBackground(new Color(255, 255, 255));
		
		btnBorrow = new MyDesign.MyButton();
		btnBorrow.setColorOver(new Color(22, 113, 221));
		btnBorrow.setColor(new Color(22, 113, 221));
		btnBorrow.setBorderColor(new Color(22, 113, 221));
		btnBorrow.setText("Cho mượn");
		btnBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					borrowBook();
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBorrow.setBorder(null);
		btnBorrow.setBackground(new Color(22, 113, 221));
		btnBorrow.setForeground(new Color(255, 255, 255));
		btnBorrow.setIcon(new ImageIcon(Borrow_GUI.class.getResource("/Images/borrow-white.png")));
		btnBorrow.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnBorrow.setBounds(10, 203, 214, 43);
		panel.add(btnBorrow);
		
		textField_num = new JTextField();
		textField_num.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				handleInput(e.getKeyChar());
			}
		});
		
		textField_num.setText(String.valueOf(sl));
		textField_num.setFont(new Font("Segoe UI", Font.BOLD, 14));
		textField_num.setBorder(new LineBorder(new Color(128, 128, 128)));
		textField_num.setBounds(87, 0, 49, 20);
		panel.add(textField_num);
		textField_num.setColumns(3);
		
		btnSubtract = new MyDesign.MyButton();
		btnSubtract.setBounds(196, 0, 28, 23);
		panel.add(btnSubtract);
		btnSubtract.setColorClick(new Color(166, 202, 255));
		btnSubtract.setColorOver(new Color(215, 231, 255));
		btnSubtract.setColor(new Color(215, 231, 255));
		btnSubtract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				subtractNum();
			}
		});
		btnSubtract.setIcon(new ImageIcon(Borrow_GUI.class.getResource("/Images/tru.png")));
		btnSubtract.setForeground(new Color(215, 231, 255));
		btnSubtract.setBorder(null);
		btnSubtract.setBackground(new Color(215, 231, 255));
		
		btnAdd = new MyDesign.MyButton();
		btnAdd.setBounds(158, 0, 28, 23);
		panel.add(btnAdd);
		btnAdd.setColorClick(new Color(166, 202, 255));
		btnAdd.setColorOver(new Color(215, 231, 255));
		btnAdd.setColor(new Color(215, 231, 255));
		btnAdd.setBorderColor(new Color(215, 231, 255));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNum();
			}
		});
		btnAdd.setBorder(null);
		btnAdd.setBackground(new Color(215, 231, 255));
		btnAdd.setForeground(new Color(215, 231, 255));
		btnAdd.setIcon(new ImageIcon(Borrow_GUI.class.getResource("/Images/add.png")));
		
		JLabel lbl_sl = new JLabel("Số lượng:");
		lbl_sl.setBounds(10, 0, 71, 23);
		panel.add(lbl_sl);
		lbl_sl.setFont(new Font("Segoe UI", Font.BOLD, 14));
		
		JLabel lb_book1_1 = new JLabel("Thông tin sách");
		lb_book1_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lb_book1_1.setBounds(609, 25, 141, 36);
		add(lb_book1_1);
		
		scrollPane_kd.setVerticalScrollBar(new ScrollBar());
		scrollPane_kd.getVerticalScrollBar().setBackground(Color.WHITE);
		scrollPane_kd.getViewport().setBackground(Color.WHITE);
        
		scrollPane_dc.setVerticalScrollBar(new ScrollBar());
		scrollPane_dc.getVerticalScrollBar().setBackground(Color.WHITE);
		scrollPane_dc.getViewport().setBackground(Color.WHITE);
        
        JPanel panel_search = new JPanel();
        panel_search.setBackground(new Color(255, 255, 255));
        panel_search.setBorder(new LineBorder(new Color(128, 128, 128)));
        panel_search.setBounds(246, 25, 268, 30);
        add(panel_search);
        panel_search.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(Borrow_GUI.class.getResource("/Images/search.png")));
        lblNewLabel.setBounds(238, 3, 24, 25);
        panel_search.add(lblNewLabel);
        
        searchTxt = new MyDesign.SearchText();
        searchTxt.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent e) {
        		searchBook();
        	}
        });
        searchTxt.setHint("Tìm tên sách...");
        searchTxt.setBorder(null);
        searchTxt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        searchTxt.setBounds(10, 5, 218, 20);
        panel_search.add(searchTxt);
        searchTxt.setColumns(10);
        
        JScrollPane scrollPane_info = new JScrollPane();
        scrollPane_info.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_info.getVerticalScrollBar().setBackground(Color.WHITE);
        scrollPane_info.setBounds(566, 72, 224, 245);
        add(scrollPane_info);
        
        JPanel panel_info = new JPanel();
        scrollPane_info.setViewportView(panel_info);
        panel_info.setBorder( BorderFactory.createEmptyBorder(5,5,5,5) );
        panel_info.setBackground(new Color(255, 255, 255));
        panel_info.setLayout(new BoxLayout(panel_info, BoxLayout.Y_AXIS));
        
        bookImage = new JLabel("");
        bookImage.setMinimumSize(new Dimension(200,250));
        bookImage.setPreferredSize(new Dimension(200,250));
        panel_info.add(bookImage);
        
        info = new JLabel("");
        info.setVerticalAlignment(SwingConstants.TOP);
        info.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        info.setMinimumSize(new Dimension(200,250));
        info.setPreferredSize(new Dimension(200,250));
        panel_info.add(info);
        
		scrollPane_info.setVerticalScrollBar(new ScrollBar());
		scrollPane_info.getVerticalScrollBar().setBackground(Color.WHITE);
		
		dateChooser.setBackground(new java.awt.Color(255, 255, 255));
		dateChooser.setForeground(new java.awt.Color(255, 255, 255));
		
        expMinDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(expMinDate);
        cal.add(Calendar.DATE, 1);
        expMinDate = cal.getTime();
        cal.add(Calendar.DATE, 30);
        expMaxDate = new Date();
        expMaxDate = cal.getTime();
        
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setDate(expMinDate);
        dateChooser.setMinSelectableDate(expMinDate);
        dateChooser.setMaxSelectableDate(expMaxDate);
        JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
        editor.setEditable(false);
        
        tiencocTxt.setText(String.valueOf(tiencoc));
        
        bookBUS = new BookBUS();
        readerBUS = new ReaderBUS();;
        bookList = bookBUS.getAll();
        showAvailableBooks();
        loadAllReader();
        readerBorrowNum = readerBUS.getNumOfBorrowBook(comboBox_reader.getSelectedItem().toString());
        
        if(!(new RolePermissionBUS()).hasPerEdit(user.getRoleID(), 3))
        {
            btnBorrow.setEnabled(false);
            btnAddBook.setEnabled(false);
            btnAdd.setEnabled(false);
            btnSubtract.setEnabled(false);
        }
	}

	protected void borrowBook() throws ClassNotFoundException, SQLException, IOException {
		if(tbSachDaChon.getRowCount() < 1)
		{
			JOptionPane.showMessageDialog(null, "Vui lòng chọn sách để tiến hành qui trình mượn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		//Update borrow card info
        Date startDate = new Date();
        String startDateStr = new SimpleDateFormat("yyyy-MM-dd").format(startDate).trim();
        String expReDateStr = new SimpleDateFormat("yyyy-MM-dd").format(dateChooser.getDate()).trim();
        Vector<Reader> readers = new ReaderBUS().getReaderByName(comboBox_reader.getSelectedItem().toString());
        int readerID = readers.get(0).getPersonID(); 
        int idBC = new BorrowCardBUS().addTicket(startDateStr, expReDateStr, tiencoc, readerID, user.getPersonID());
		
		//Update book store number
		DetailBCBUS detailBCBUS = new DetailBCBUS();
		for(int i = 0; i < tbSachDaChon.getRowCount(); i++)
		{
			
			String isbn = tbSachDaChon.getValueAt(i, 1).toString();
			int readerBorrow = Integer.parseInt(tbSachDaChon.getValueAt(i, 5).toString());
			Book1 book = bookBUS.getBookByISBN(isbn);
			int storeNum = book.getStoreNum();
			int borrowNum = book.getBorrowNum();
			int updateStoreNum = storeNum - readerBorrow;
			int updateBorrowNum = borrowNum + readerBorrow;
			detailBCBUS.addDetailBC(idBC, isbn, readerBorrow);
			bookBUS.updateStoreNumBooks(isbn, updateStoreNum, updateBorrowNum);
		}
		JOptionPane.showMessageDialog(null, "Thêm phiếu mượn thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		
		//Reset value;
		DefaultTableModel model = (DefaultTableModel)tbSachDaChon.getModel();
		model.setRowCount(0);
		tiencoc = 0;
		tiencocTxt.setText(String.valueOf(tiencoc));
		comboBox_reader.setSelectedIndex(0);
		dateChooser.setDate(expMinDate);
	}

	protected void searchBook() {
		String searchText = searchTxt.getText().toLowerCase();
		//Clear table
		DefaultTableModel model = (DefaultTableModel) tbSachKhaDung.getModel();
		model.setRowCount(0);
		
		if(!searchText.isBlank())
		{
			for(int i = 0; i < bookList.size(); i++)
			{
				Book1 book = bookList.get(i);
				String bookName = bookList.get(i).getTenSach().toLowerCase();
				if(bookName.contains(searchText))
					tbSachKhaDung.addRow(new Object[] {i+1,book.getISBN(),book.getTenSach(),book.stringAuthor(),book.getPublisher(),book.getStoreNum()} );
			}
		}
		else
			showAvailableBooks();
	}

	protected void modifyBook() throws ClassNotFoundException, SQLException, IOException {
		tbSachKhaDung.clearSelection();
	    int selectBook = tbSachDaChon.getSelectedRow();
	    if (selectBook > -1)
	    {
			btnAddBook.setText("Sửa");
	    	String isbn = tbSachDaChon.getValueAt(selectBook, 1).toString();
	    	loadBookInfo(isbn);
	    	String selectedNum = tbSachDaChon.getValueAt(selectBook, 5).toString();
	    	textField_num.setText(selectedNum);
	    	sl = Integer.parseInt(selectedNum);
	    }
	}

	protected void addBook() throws ClassNotFoundException, SQLException, IOException {
		if(sl == 0)
		{
    		JOptionPane.showMessageDialog(null,"Số lượng sách mượn không được dưới 1.","Lỗi",JOptionPane.ERROR_MESSAGE);
    		return;
		}
		
		if(readerBorrowNum >= 5)
		{
			JOptionPane.showMessageDialog(null, "Người đọc đã mượn quá số sách tối đa.\nVui lòng trả sách để được mượn thêm.","Lỗi",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(readerBorrowNum + sl > 5)
		{
			JOptionPane.showMessageDialog(null, "Số lượng sách mượn vượt quá số sách được mượn tối đa.\nVui lòng trả sách hoặc thay đổi số lượng sách mượn.","Lỗi",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		readerBorrowNum += sl;
		
		int selectedRow = tbSachKhaDung.getSelectedRow();
		String isbn = tbSachKhaDung.getValueAt(selectedRow, 1).toString(); 
		Book1 book = bookBUS.getBookByISBN(isbn);
		
		int rowIndex = checkExist(isbn);
		if(rowIndex != -1)
		{
			String currentNum = tbSachDaChon.getValueAt(rowIndex,5).toString() ;
			int updateNum = Integer.parseInt(currentNum) + sl;
			tbSachDaChon.setValueAt(updateNum, rowIndex, 5);
		}
		else
		{
			int stt = tbSachDaChon.getModel().getRowCount() + 1;
			tbSachDaChon.addRow(new Object[] {stt,isbn,book.getTenSach(),book.stringAuthor(),book.getPublisher(),sl});
		}
		
		//Tien coc bang 1/3 gia tri sach
		tiencoc += Math.ceil(book.getCost()/3)*sl;
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedNumber = decimalFormat.format(tiencoc);
		tiencocTxt.setText(formattedNumber);
		
		//Reset value
		tbSachKhaDung.clearSelection();
		sl = 0;
		max = 0;
		textField_num.setText(String.valueOf(sl));
		bookImage.setIcon(null);
		info.setText(null);
		dateChooser.setDate(expMinDate);
	}
	
	protected void updateBook() throws ClassNotFoundException, SQLException, IOException
	{
		int selectBook = tbSachDaChon.getSelectedRow(); 
		Book1 book = bookBUS.getBookByISBN(tbSachDaChon.getValueAt(selectBook, 1).toString());
		int slBefore = Integer.parseInt(tbSachDaChon.getValueAt(selectBook,5).toString());
		if(readerBorrowNum - slBefore + sl > 5)
		{
			JOptionPane.showMessageDialog(null, "Số lượng sách sau khi sửa vượt quá số lượng sách được mượn tối đa.", "Lỗi",JOptionPane.ERROR_MESSAGE);
			return;
		}
		readerBorrowNum -= slBefore;
		tiencoc -= Math.ceil(book.getCost()/3)*slBefore;
		if(sl == 0)
		{
			DefaultTableModel model = (DefaultTableModel) tbSachDaChon.getModel();
			model.removeRow(selectBook);
			JOptionPane.showMessageDialog(null,"Xóa sách thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			readerBorrowNum += sl;
			tiencoc += Math.ceil(book.getCost()/3)*sl;
			tbSachDaChon.setValueAt(sl, selectBook, 5);
			JOptionPane.showMessageDialog(null,"Sửa số lượng sách thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
		
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedNumber = decimalFormat.format(tiencoc);
		tiencocTxt.setText(formattedNumber);
		
		//Reset value
		tbSachDaChon.clearSelection();
		sl = 0;
		max = 0;
		textField_num.setText(String.valueOf(sl));
		bookImage.setIcon(null);
		info.setText(null);
		btnAddBook.setText("Thêm");
	}
	
	private int checkExist(String isbn)
	{
		for(int i  = 0; i<tbSachDaChon.getRowCount();i++)
			if(tbSachDaChon.getValueAt(i, 1).equals(isbn))
				return i;
		return -1;
	}

	private void loadAllReader() throws ClassNotFoundException, SQLException {
		Vector<Reader> readers = readerBUS.getAllReaderCanBorrow();
	    for (int i=0;i < readers.size();i++)
	    	comboBox_reader.addItem(readers.get(i).getName());
	}

	private void showAvailableBooks() {
		((DefaultTableModel) tbSachKhaDung.getModel()).setRowCount(0);
        if (bookList!=null)
        for (int i = 0; i < bookList.size(); i++) {
            tbSachKhaDung.addRow(new Object[]{i + 1,bookList.get(i).getISBN(), bookList.get(i).getTenSach(), bookList.get(i).stringAuthor(), bookList.get(i).getPublisher(), bookList.get(i).getStoreNum()});
        }
		
	}

	protected void subtractNum() {
		if(sl < 1) return;
		sl--;
		textField_num.setText(String.valueOf(sl));
	}

	protected void addNum() {
		sl++;
    	if(sl>max)
    	{
    		JOptionPane.showMessageDialog(null,"Số lượng mượn không được vượt quá số lượng sách còn lại.","Lỗi",JOptionPane.ERROR_MESSAGE);
    		sl = max;
    	}
		textField_num.setText(String.valueOf(sl));
	}

	protected void handleInput(char c) {
    	String str = textField_num.getText();
    	
        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE)
        {
        	String replaceStr = str.substring(0,str.length()-1);
        	textField_num.setText(replaceStr);
        }
        else
        {
        	if(textField_num.getText().isBlank()) 
        		sl = 0;
        	else
        		sl = Integer.parseInt(str);
        	if(sl>max)
        	{
        		JOptionPane.showMessageDialog(null,"Số lượng mượn không được vượt quá số lượng sách còn lại.","Lỗi",JOptionPane.WARNING_MESSAGE);
        		sl = max;
        	}
        	textField_num.setText(String.valueOf(sl));
        }
	}
	
	private void chooseBook() throws ClassNotFoundException, SQLException, IOException
	{
		tbSachDaChon.clearSelection();
	    int selectBook = tbSachKhaDung.getSelectedRow();
	    if (selectBook > -1)
	    {
			btnAddBook.setText("Thêm");
	    	String isbn = tbSachKhaDung.getValueAt(selectBook, 1).toString();
	    	loadBookInfo(isbn);
		    sl = 0;
			textField_num.setText(String.valueOf(sl));
	    }
	}
	
	private void loadBookInfo(String isbn) throws ClassNotFoundException, SQLException, IOException
	{
		Book1 book = bookBUS.getBookByISBN(isbn);
    	
    	ImageIcon image = new ImageIcon(Borrow_GUI.class.getResource(book.getImg()));
		Image resizedImage = image.getImage();
		resizedImage = resizedImage.getScaledInstance(210, 250, Image.SCALE_SMOOTH);
		image = new ImageIcon(resizedImage);
		
		bookImage.setIcon(image);
	    max = book.getStoreNum();
	    
		String bookName = "<center><b>" + book.getTenSach()+"</b></center>";
		String bookIsbn = "<b>ISBN: </b>"+isbn+"<br>";
		String author = "<b>Tác giả: </b>";
		Vector<String> allAuthor = book.getAuthor();
		for(String str : allAuthor)
			author += (str +"<br>");
		String publisher = "<b>NXB: </b>"+book.getPublisher()+"<br>";
		String edition = "<b>Tái bản: </b>"+book.getVersion();
	
		String bookInfo = "<html>" + bookName + bookIsbn + author + publisher + edition + "</html>";
		
		info.setText(bookInfo);
	}
}
