package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import MyDesign.PanelBorder_Statistic_Blue;

import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import BUS.AuthorBUS;
import BUS.BookBUS;
import BUS.CategoryBUS;
import BUS.PublisherBUS;
import DTO.entities.Author;
import DTO.entities.Book1;
import DTO.entities.Category;
import DTO.entities.Publisher;

import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddBook_Dialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField nameTxt;
	private JTextField editionTxt;
	private JTextField isbnTxt;
	
	List<Author> authors;
	List<Category> categories;
	List<Publisher> publishers;
	List<Book1> books;
	
	String imagePath = "";
	private JComboBox cbNXB;
	private JComboBox cbCategory;
	private JComboBox cbAuthor;
	private JLabel imageView;
	private JTextField costTxt;
	private Book_GUI parent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		try {
////			AddBook_Dialog dialog = new AddBook_Dialog(new Book_GUI());
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	/**
	 * Create the dialog.
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public AddBook_Dialog(Book_GUI parent) throws SQLException, IOException, ClassNotFoundException {
		this.parent = parent;
		books = (new BookBUS()).getAll();
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddBook_Dialog.class.getResource("/Images/logo.png")));
		setTitle("Thêm sách");
		setBounds(100, 100, 411, 652);
		getContentPane().setLayout(null);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int x = ((int)screenSize.getWidth()- this.getWidth())/2;
		int y = ((int)screenSize.getHeight() - this.getHeight())/2;
		
		setLocation(x,y);
		
		MyDesign.MyButton btnNewButton = new MyDesign.MyButton();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addBook();
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		costTxt = new JTextField();
		costTxt.setText("0");
		costTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				handleInput(e.getKeyChar());
			}
		});
		costTxt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		costTxt.setColumns(10);
		costTxt.setBorder(new LineBorder(new Color(128, 128, 128)));
		costTxt.setBounds(132, 505, 232, 29);
		getContentPane().add(costTxt);
		
		JLabel lblNewLabel2 = new JLabel("Giá");
		lblNewLabel2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel2.setBounds(25, 513, 72, 20);
		getContentPane().add(lblNewLabel2);
		btnNewButton.setColorOver(new Color(22, 113, 221));
		btnNewButton.setColor(new Color(22, 113, 221));
		btnNewButton.setBorderColor(new Color(22, 113, 221));
		btnNewButton.setBackground(new Color(22, 113, 221));
		btnNewButton.setText("Thêm");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setIcon(new ImageIcon(AddBook_Dialog.class.getResource("/Images/action-add-white.png")));
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnNewButton.setBounds(132, 550, 138, 40);
		getContentPane().add(btnNewButton);
		
		cbNXB = new JComboBox();
		cbNXB.setBounds(132, 465, 232, 29);
		getContentPane().add(cbNXB);
		
		JLabel lblNewLabel_3 = new JLabel("Nhà xuất bản");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_3.setBounds(25, 474, 89, 20);
		getContentPane().add(lblNewLabel_3);
		
		cbCategory = new JComboBox();
		cbCategory.setBounds(132, 425, 232, 29);
		getContentPane().add(cbCategory);
		
		JLabel lblNewLabel4 = new JLabel("Thể loại");
		lblNewLabel4.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel4.setBounds(25, 431, 89, 20);
		getContentPane().add(lblNewLabel4);
		
		cbAuthor = new JComboBox();
		cbAuthor.setBounds(132, 383, 232, 29);
		getContentPane().add(cbAuthor);
		
		JLabel lblNewLabel5 = new JLabel("Tác giả");
		lblNewLabel5.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel5.setBounds(25, 387, 67, 20);
		getContentPane().add(lblNewLabel5);
		
		isbnTxt = new JTextField();
		isbnTxt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		isbnTxt.setColumns(10);
		isbnTxt.setBorder(new LineBorder(new Color(128, 128, 128)));
		isbnTxt.setBounds(132, 342, 232, 29);
		getContentPane().add(isbnTxt);
		
		JLabel lblNewLabel7 = new JLabel("ISBN");
		lblNewLabel7.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel7.setBounds(25, 351, 67, 20);
		getContentPane().add(lblNewLabel7);
		
		editionTxt = new JTextField();
		editionTxt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		editionTxt.setColumns(10);
		editionTxt.setBorder(new LineBorder(new Color(128, 128, 128)));
		editionTxt.setBounds(132, 302, 232, 29);
		getContentPane().add(editionTxt);
		
		JLabel lblNewLabel_1 = new JLabel("Phiên bản");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1.setBounds(25, 310, 67, 20);
		getContentPane().add(lblNewLabel_1);
		
		nameTxt = new JTextField();
		nameTxt.setBorder(new LineBorder(new Color(128, 128, 128)));
		nameTxt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		nameTxt.setBounds(132, 262, 232, 29);
		getContentPane().add(nameTxt);
		nameTxt.setColumns(10);
		
		JLabel bookNameTxt = new JLabel("Tên sách");
		bookNameTxt.setFont(new Font("Segoe UI", Font.BOLD, 14));
		bookNameTxt.setBounds(25, 267, 67, 29);
		getContentPane().add(bookNameTxt);
		
		imageView = new JLabel("New label");
		imageView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chooseImage();
			}
		});
		
		imageView.setIcon(new ImageIcon(AddBook_Dialog.class.getResource("/Images/AddImage.png")));
		imageView.setBounds(145, 71, 118, 180);
		getContentPane().add(imageView);
		
		MyDesign.PanelBorder_Basic panel_1 = new MyDesign.PanelBorder_Basic();
		panel_1.setBounds(10, 63, 375, 539);
		getContentPane().add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Thêm sách");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setIcon(new ImageIcon(AddBook_Dialog.class.getResource("/Images/warehouse-white.png")));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel.setBounds(0, 10, 395, 50);
		getContentPane().add(lblNewLabel);
		
		PanelBorder_Statistic_Blue panel = new PanelBorder_Statistic_Blue();
		panel.setBounds(0, 0, 395, 617);
		getContentPane().add(panel);
		
		JLabel lblNewLabel6 = new JLabel("ISBN");
		lblNewLabel6.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel6.setBounds(25, 382, 67, 20);
		getContentPane().add(lblNewLabel6);
		
		getAllAuthor();
		getAllCategory();
		getAllPublisher();
	}
	
    protected void addBook() throws ClassNotFoundException, SQLException, IOException {
		
    	String name = nameTxt.getText();
    	String edition = editionTxt.getText();
    	String isbn = isbnTxt.getText();
    	String author = cbAuthor.getSelectedItem().toString();
    	String category = cbCategory.getSelectedItem().toString();
    	String publisher = cbNXB.getSelectedItem().toString();
    	int cost = Integer.parseInt(costTxt.getText());
    	
    	if(name.isBlank() || edition.isBlank() || isbn.isBlank())
    	{
    		JOptionPane.showMessageDialog(null,"Vui lòng điền đầy đủ thông tin.","Lỗi",JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	
    	if(imagePath.isBlank())
    	{
    		JOptionPane.showMessageDialog(null,"Chưa chọn ảnh.","Lỗi",JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	
    	if(cost == 0)
    	{
    		JOptionPane.showMessageDialog(null,"Giá phải lớn hơn 0.","Lỗi",JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	
    	String isbnCheck = isbn.replace("-", "");
    	
//    	if(!isValidISBN13(isbnCheck))
//    	{
//    		JOptionPane.showMessageDialog(null,"Chuỗi ISBN không hợp lệ.","Lỗi",JOptionPane.ERROR_MESSAGE);
//    		return;
//    	}
    	
    	for(Book1 book : books)
    		if(book.getTenSach().equals(name) && book.getVersion().equals(edition))
    		{
        		JOptionPane.showMessageDialog(null,"Sách đã tồn tại.","Lỗi",JOptionPane.ERROR_MESSAGE);
        		return;
    		}
    	
    	int authorID = getAuthorID(author);
    	int publisherID = getPublisherID(publisher);
    	int categoryID = getCategoryID(category);
    	
    	(new BookBUS()).insertBook(name, isbn, edition, publisherID, cost, imagePath, authorID, categoryID);
    	parent.loadAllBooks();
    	JOptionPane.showMessageDialog(null,"Thêm sách thành công!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
    	
    	dispose();
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

        ImageIcon image = new ImageIcon(AddBook_Dialog.class.getResource(imagePath));
		Image resizedImage = image.getImage();
		resizedImage = resizedImage.getScaledInstance(imageView.getWidth(), imageView.getHeight(), Image.SCALE_SMOOTH);
		image = new ImageIcon(resizedImage);
		imageView.setIcon(image);
	}
    
    public  boolean isValidISBN13(String isbn) {
        if (isbn == null || isbn.length() != 13) {
            return false;
        }

        try {
            int sum = 0;
            for (int i = 0; i < 12; i++) {
                int digit = Integer.parseInt(isbn.substring(i, i + 1));
                sum += (i % 2 == 0) ? digit : digit * 3;
            }

            int checksum = 10 - (sum % 10);
            if (checksum == 10) {
                checksum = 0;
            }

            return checksum == Integer.parseInt(isbn.substring(12));
        } catch (NumberFormatException nfe) {
            return false;
      }
    }
    
    protected void handleInput(char c) {
    	String str = costTxt.getText();
    	
        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE)
        {
        	String replaceStr = str.substring(0,str.length()-1);
        	costTxt.setText(replaceStr);
        }
        else
        {
        	if(costTxt.getText().isBlank()) 
        		costTxt.setText("0");
        	else
        		costTxt.setText(String.valueOf(Integer.parseInt(str)));
        }
	}
}
