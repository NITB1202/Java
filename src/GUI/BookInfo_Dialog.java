/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import BUS.AuthorBUS;
import BUS.BookBUS;
import BUS.CategoryBUS;
import BUS.PublisherBUS;
import BUS.RolePermissionBUS;
import DTO.entities.Author;
import DTO.entities.Book1;
import DTO.entities.Category;
import DTO.entities.Publisher;

import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;

public class BookInfo_Dialog extends javax.swing.JDialog {

        MyDesign.MyTable tab;
        Book1 book;
        CategoryBUS cateBUS;
        BookBUS bookBUS;
    	List<Author> authors;
    	List<Category> categories;
    	List<Publisher> publishers;
    	String imagePath;

    /**
     * Creates new form StaffAdd_Dialog
     */
    public BookInfo_Dialog(java.awt.Frame parent, boolean modal,MyDesign.MyTable tab,Book1 book) throws IOException, SQLException, ClassNotFoundException {
        super(parent, modal);
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage(BookInfo_Dialog.class.getResource("/Images/logo.png")));
        setTitle("Chi tiết sách");
        this.book=book;
        this.tab=tab;
        initComponents();
        cateBUS=new CategoryBUS();
        bookBUS=new BookBUS();
        loadData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbImageBook1 = new javax.swing.JLabel();
        panelBorder_Statistic_Blue1 = new MyDesign.PanelBorder_Statistic_Blue();
        panelBorder_Basic1 = new MyDesign.PanelBorder_Basic();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtVersion = new MyDesign.MyTextField_Basic();
        txtVersion.setFont(new Font("SansSerif", Font.PLAIN, 13));
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtQuantity = new MyDesign.MyTextField_Basic();
        txtQuantity.setFont(new Font("SansSerif", Font.PLAIN, 13));
        jLabel12 = new javax.swing.JLabel();
        cbAuthor = new javax.swing.JComboBox<>();
        cbAuthor.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtISBN = new MyDesign.MyTextField_Basic();
        txtISBN.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtBookName = new MyDesign.MyTextField_Basic();
        txtBookName.setFont(new Font("SansSerif", Font.PLAIN, 13));
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtDangMuon = new MyDesign.MyTextField_Basic();
        txtDangMuon.setFont(new Font("SansSerif", Font.PLAIN, 13));
        jLabel15 = new javax.swing.JLabel();
        cbCategory = new javax.swing.JComboBox<>();
        cbCategory.setFont(new Font("SansSerif", Font.PLAIN, 13));
        jLabel4 = new javax.swing.JLabel();

        lbImageBook1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ExampleBook.png"))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel8.setText("Tên sách");

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel9.setText("Phiên bản");

        txtVersion.setBorder(new LineBorder(new Color(128, 128, 128)));

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel10.setText("Tác giả");

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel11.setText("Số lượng");

        txtQuantity.setBorder(new LineBorder(new Color(128, 128, 128)));

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel12.setText("Đang mượn");

        txtISBN.setBorder(new LineBorder(new Color(128, 128, 128)));

        txtBookName.setBorder(new LineBorder(new Color(128, 128, 128)));

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel13.setText("ISBN");

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel14.setText("Nhà xuất bản");

        txtDangMuon.setBorder(new LineBorder(new Color(128, 128, 128)));

        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel15.setText("Thể loại");
        
        lbImageBook = new JLabel("");
        lbImageBook.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		chooseImage();
        	}
        });
        
        cbNXB = new JComboBox<String>();
        cbNXB.setFont(new Font("SansSerif", Font.PLAIN, 13));
        
        btnCapNhat = new MyDesign.MyButton();
        btnCapNhat.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					capNhatSach();
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        btnCapNhat.setColorOver(new Color(22, 113, 221));
        btnCapNhat.setColor(new Color(22, 113, 221));
        btnCapNhat.setBorderColor(new Color(22, 113, 221));
        btnCapNhat.setBackground(new Color(22, 113, 221));
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.setForeground(new Color(255, 255, 255));
        btnCapNhat.setIcon(new ImageIcon(BookInfo_Dialog.class.getResource("/Images/action-refresh-white.png")));
        btnCapNhat.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnXoaNhanVien = new MyDesign.MyButton();
        btnXoaNhanVien.setColorClick(new Color(255, 128, 128));
        
                btnXoaNhanVien.setBackground(new Color(255, 204, 204));
                btnXoaNhanVien.setForeground(new java.awt.Color(248, 67, 67));
                btnXoaNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/action-delete-white.png"))); // NOI18N
                btnXoaNhanVien.setText("Xóa sách");
                btnXoaNhanVien.setBorderColor(new Color(255, 204, 204));
                btnXoaNhanVien.setColor(new Color(255, 204, 204));
                btnXoaNhanVien.setColorOver(new Color(255, 204, 204));
                btnXoaNhanVien.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
                btnXoaNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        btnXoaNhanVienMouseClicked(evt);
                    }
                });
        
        lblNewLabel = new JLabel("Giá");
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        txtCost = new JTextField();
        txtCost.setBorder(new LineBorder(new Color(128, 128, 128)));
        txtCost.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtCost.setColumns(10);

        javax.swing.GroupLayout panelBorder_Basic1Layout = new javax.swing.GroupLayout(panelBorder_Basic1);
        panelBorder_Basic1Layout.setHorizontalGroup(
        	panelBorder_Basic1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        					.addComponent(jLabel13, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
        					.addGap(25)
        					.addComponent(txtISBN, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
        				.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        					.addComponent(jLabel15, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
        					.addGap(25)
        					.addComponent(cbCategory, 0, 230, Short.MAX_VALUE))
        				.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        					.addComponent(jLabel12, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
        					.addGap(28)
        					.addComponent(txtDangMuon, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
        				.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        					.addComponent(jLabel8, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
        					.addGap(27)
        					.addComponent(txtBookName, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
        				.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        					.addComponent(jLabel9, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
        					.addGap(27)
        					.addComponent(txtVersion, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
        				.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        					.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jLabel10, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
        						.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        							.addGap(1)
        							.addComponent(jLabel11, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
        						.addComponent(jLabel14))
        					.addGap(25)
        					.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(txtCost, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        						.addComponent(cbNXB, 0, 230, Short.MAX_VALUE)
        						.addComponent(txtQuantity, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        						.addComponent(cbAuthor, 0, 230, Short.MAX_VALUE))))
        			.addContainerGap())
        		.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        			.addGap(24)
        			.addComponent(btnCapNhat, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
        			.addComponent(btnXoaNhanVien, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(24))
        		.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        			.addGap(108)
        			.addComponent(lbImageBook, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(99, Short.MAX_VALUE))
        		.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblNewLabel)
        			.addContainerGap(335, Short.MAX_VALUE))
        );
        panelBorder_Basic1Layout.setVerticalGroup(
        	panelBorder_Basic1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lbImageBook, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
        			.addGap(18)
        			.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel8)
        				.addComponent(txtBookName, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel9)
        				.addComponent(txtVersion, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        					.addComponent(lblNewLabel)
        					.addGap(24))
        				.addGroup(panelBorder_Basic1Layout.createSequentialGroup()
        					.addComponent(txtCost, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)))
        			.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(cbAuthor, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel10))
        			.addGap(12)
        			.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel15)
        				.addComponent(cbCategory, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel13)
        				.addComponent(txtISBN, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel14)
        				.addComponent(cbNXB, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel11)
        				.addComponent(txtQuantity, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel12)
        				.addComponent(txtDangMuon, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(panelBorder_Basic1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnCapNhat, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnXoaNhanVien, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
        			.addGap(16))
        );
        panelBorder_Basic1.setLayout(panelBorder_Basic1Layout);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new ImageIcon(BookInfo_Dialog.class.getResource("/Images/warehouse-white.png"))); // NOI18N
        jLabel4.setText("Thông tin sách");

        javax.swing.GroupLayout panelBorder_Statistic_Blue1Layout = new javax.swing.GroupLayout(panelBorder_Statistic_Blue1);
        panelBorder_Statistic_Blue1Layout.setHorizontalGroup(
        	panelBorder_Statistic_Blue1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, panelBorder_Statistic_Blue1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(panelBorder_Statistic_Blue1Layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(panelBorder_Basic1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jLabel4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE))
        			.addContainerGap())
        );
        panelBorder_Statistic_Blue1Layout.setVerticalGroup(
        	panelBorder_Statistic_Blue1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(panelBorder_Statistic_Blue1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(panelBorder_Basic1, GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
        			.addContainerGap())
        );
        panelBorder_Statistic_Blue1.setLayout(panelBorder_Statistic_Blue1Layout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(panelBorder_Statistic_Blue1, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(panelBorder_Statistic_Blue1, GroupLayout.PREFERRED_SIZE, 733, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    protected void chooseImage() 
    {
        // Tạo JFileChooser để chọn ảnh
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));

        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String srcFolder = "src/Images";
            String binFolder = "bin/Images"; // Or "target/classes/Images" for Maven projects

            File destSrcFolder = new File(srcFolder);
            File destBinFolder = new File(binFolder);

            if (!destSrcFolder.exists()) {
                System.out.println("Khong tim thay thu muc anh.");
                return;
            }
            // Kiểm tra sự tồn tại của ảnh trong thư mục src/Images và bin/Images
            File destSrcFile = new File(destSrcFolder, selectedFile.getName());
            File destBinFile = new File(destBinFolder, selectedFile.getName());
            try {
                if (!destSrcFile.exists()) {
                    // Sao chép ảnh vào thư mục src/Images
                    Files.copy(selectedFile.toPath(), destSrcFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Image copied to: " + destSrcFile.getPath());
                } else {
                    System.out.println("Image already exists in: " + destSrcFile.getPath());
                }

                if (destBinFolder.exists() && !destBinFile.exists()) {
                    // Sao chép ảnh vào thư mục bin/Images
                    Files.copy(selectedFile.toPath(), destBinFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Image copied to: " + destBinFile.getPath());
                } else {
                    System.out.println("Image already exists in: " + destBinFile.getPath());
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            // Lưu đường dẫn của ảnh dưới dạng resource
            imagePath = "/Images/" + selectedFile.getName();
            System.out.println("Classpath resource path: " + imagePath);
        }
        
    	ImageIcon image = new ImageIcon(BookInfo_Dialog.class.getResource(imagePath));
		Image resizedImage = image.getImage();
		resizedImage = resizedImage.getScaledInstance(lbImageBook.getWidth(), lbImageBook.getHeight(), Image.SCALE_SMOOTH);
		image = new ImageIcon(resizedImage);
		lbImageBook.setIcon(image);
        
	}
    
    public static boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }

	protected void capNhatSach() throws ClassNotFoundException, SQLException, IOException {
		
		String bookName = txtBookName.getText();
		String version = txtVersion.getText();
		String author = cbAuthor.getSelectedItem().toString();
		String category = cbCategory.getSelectedItem().toString();
		String isbn = txtISBN.getText();
		String publisher = cbNXB.getSelectedItem().toString();
		
		if(bookName.isBlank() || version.isBlank() || isbn.isBlank())
		{
			JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin.","Lỗi",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(!isNumeric(txtCost.getText()))
		{
			JOptionPane.showMessageDialog(null, "Giá phải là số.","Lỗi",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int cost = Integer.parseInt(txtCost.getText());
		
		if(cost == 0)
		{
			JOptionPane.showMessageDialog(null, "Giá phải lớn hơn 0.","Lỗi",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int authorID = getAuthorID(author);
		int publisherID = getPublisherID(publisher);
		int categoryID = getCategoryID(category);
		
		if(authorID == -1 || publisherID == -1 || categoryID == -1)
		{
			System.out.println("Khong tim thay id");
			return;
		}
		bookBUS.updateBook(book.getTenSach(), bookName, book.getISBN(), isbn, version, imagePath, publisherID, authorID, categoryID,cost);
		JOptionPane.showMessageDialog(null, "Cập nhật thành công!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
		setUPTable();
		//Update book Info in UI
		book.setAuthor(author);
		book.setISBN(isbn);
		book.setPublisher(publisher);
		book.setTenSach(bookName);
		book.setVersion(version);
		book.setImg(imagePath);
		book.setCost(cost);
	}

	private void btnXoaNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaNhanVienMouseClicked
        try {
            int diaRS=JOptionPane.showOptionDialog(null, "Bạn có chắc xoá cuốn sách này?", "Xác nhận", JOptionPane.YES_NO_OPTION, 
            		JOptionPane.QUESTION_MESSAGE, null,null,null);
            if(diaRS==JOptionPane.YES_OPTION){
                if(book.getBorrowNum()==0){
                    JOptionPane.showMessageDialog(null,bookBUS.delBook(book.getISBN()),"Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    setUPTable();
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null,"Sách vẫn đang được mượn. Không thể xoá sách!");
                }
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null,e1.getMessage());
        }
    }
    
    public void setUPTable(){
        try {
            tab.setRowCount(0);
            ArrayList<Book1> bookList = bookBUS.getAllIncludeVersion();
            for(int i=0;i<bookList.size();i++){
            Book1 book=bookList.get(i);
            String id=book.getISBN();
            String name=book.getTenSach();
            String author=book.stringAuthor();
            String version=book.getVersion();
            int num=book.getStoreNum();
            Object row[] = {i+1,id,name,author,version,num};
            tab.addRow(row);
        }
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Book_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadData() throws SQLException, IOException
    {
    	imagePath = book.getImg();
    	txtCost.setText(String.valueOf((int)book.getCost()));
        txtBookName.setText(book.getTenSach());
        txtVersion.setText(book.getVersion());
        cbAuthor.setModel(new DefaultComboBoxModel(book.getAuthor()));
        Vector<String> cateList=cateBUS.getCategoryByISBN(book.getISBN());
        cbCategory.setModel(new DefaultComboBoxModel(cateList));
        cbNXB.setModel(new DefaultComboBoxModel(new String[] {book.getPublisher()}));
        txtISBN.setText(book.getISBN());
        txtQuantity.setText(String.valueOf(book.getStoreNum()));
        txtDangMuon.setText(String.valueOf(book.getBorrowNum()));
    	ImageIcon image = new ImageIcon(Borrow_GUI.class.getResource(book.getImg()));
		Image resizedImage = image.getImage();
		resizedImage = resizedImage.getScaledInstance(lbImageBook.getWidth(), lbImageBook.getHeight(), Image.SCALE_SMOOTH);
		image = new ImageIcon(resizedImage);
		lbImageBook.setIcon(image);
		getAllAuthor();
		getAllCategory();
		getAllPublisher();
    }
    
    public void getAllAuthor() throws SQLException, IOException
    {
    	authors = (new AuthorBUS()).getAllName();
    	DefaultComboBoxModel model = (DefaultComboBoxModel)cbAuthor.getModel();
    	for(int i = 0; i < authors.size();i++)
    		if(!authors.get(i).equals(cbAuthor.getItemAt(0)))
    			model.addElement(authors.get(i).getName());
    }
    
    public void getAllCategory() throws SQLException, IOException
    {
    	categories = (new CategoryBUS()).getAll();
    	DefaultComboBoxModel model = (DefaultComboBoxModel)cbCategory.getModel();
    	for(int i = 0; i < categories.size();i++)
    		if(!categories.get(i).equals(cbCategory.getItemAt(0)))
    			model.addElement(categories.get(i).getName());
    }
    public void getAllPublisher() throws SQLException, IOException
    {
    	publishers = (new PublisherBUS()).getAllName();
    	DefaultComboBoxModel model = (DefaultComboBoxModel)cbNXB.getModel();
    	for(int i = 0; i < publishers.size();i++)
    		if(!publishers.get(i).equals(cbNXB.getItemAt(0)))
    			model.addElement(publishers.get(i).getName());
    }
    
    public int getPublisherID(String name)
    {
    	for(Publisher p : publishers)
    		if(p.getName().equals(cbNXB.getSelectedItem().toString()))
    			return p.getId();
    	return -1;
    }
    
    public int getAuthorID(String name)
    {
    	for(Author a: authors)
    		if(a.getName().equals(cbAuthor.getSelectedItem().toString()))
    			return a.getId();
    	return -1;
    }
    
    public int getCategoryID(String name)
    {
    	for(Category c: categories)
    		if(c.getName().equals(cbCategory.getSelectedItem().toString()))
    			return c.getId();
    	return -1;
    }
    
    private MyDesign.MyButton btnXoaNhanVien;
    private javax.swing.JComboBox<String> cbCategory;
    private javax.swing.JComboBox<String> cbAuthor;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lbImageBook1;
    private MyDesign.PanelBorder_Basic panelBorder_Basic1;
    private MyDesign.PanelBorder_Statistic_Blue panelBorder_Statistic_Blue1;
    private MyDesign.MyTextField_Basic txtBookName;
    private MyDesign.MyTextField_Basic txtDangMuon;
    private MyDesign.MyTextField_Basic txtISBN;
    private MyDesign.MyTextField_Basic txtQuantity;
    private MyDesign.MyTextField_Basic txtVersion;
    private JLabel lbImageBook;
    private MyDesign.MyButton btnCapNhat;
    private JComboBox<String> cbNXB;
    private JLabel lblNewLabel;
    private JTextField txtCost;
}