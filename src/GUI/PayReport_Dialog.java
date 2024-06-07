package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import BUS.PayBUS;
import DTO.entities.BorrowCard;
import DTO.entities.DetailBC;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Color;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class PayReport_Dialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DetailBC selectBook;
	private BorrowCard bc;
	private float bookCost = 0;
	private Pay_GUI parent;
	private int tienCanDenBu;
	
    public PayReport_Dialog(BorrowCard bc, DetailBC book, Pay_GUI payUI) {
    	setFont(new Font("Segoe UI", Font.PLAIN, 13));
    	setTitle("Báo mất sách");
    	setIconImage(Toolkit.getDefaultToolkit().getImage(PayReport_Dialog.class.getResource("/Images/logo.png")));
    	this.bc = bc;
    	parent = payUI;
        selectBook = book;
        
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int x = ((int)screenSize.getWidth()- 385)/2;
		int y = ((int)screenSize.getHeight() - 324)/2;
		
		setLocation(x,y);
        
        initComponents();
        
        ((DefaultEditor) snSoLuongMat.getEditor()).getTextField().setEditable(false);
        
        txtTenSach.setText(selectBook.getBookname());
        
        Vector<String> s = selectBook.getAuthorname();
        String authorString = null;
        for(String i: s){
            authorString = String.join(", ", i);
        }
        txtTacGia.setText(authorString);
        
        bookCost = selectBook.getBookCost();
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedNumber = decimalFormat.format(bookCost);
        txtGiaCuon.setText(String.valueOf(formattedNumber));
        
        pack();
    }
    private void initComponents() {

        panelBorder_Statistic_Blue1 = new MyDesign.PanelBorder_Statistic_Blue();
        panelBorder_Basic2 = new MyDesign.PanelBorder_Basic();
        jLabel13 = new javax.swing.JLabel();
        txtTenSach = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtTacGia = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtGiaCuon = new MyDesign.MyTextField_Basic();
        txtGiaCuon.setCaretColor(new Color(128, 128, 128));
        txtGiaCuon.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbLine = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel4.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnXacNhan = new MyDesign.MyButton();
        btnXacNhan.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		thongBaoMatSach();
        	}
        });
        btnXacNhan.setColorOver(new Color(22, 113, 221));
        
        snSoLuongMat = new javax.swing.JSpinner();
        snSoLuongMat.setBorder(new LineBorder(new Color(128, 128, 128)));
        snSoLuongMat.setBackground(new Color(255, 255, 255));
        snSoLuongMat.setForeground(new Color(0, 0, 0));
        snSoLuongMat.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel13.setFont(new Font("Segoe UI", Font.BOLD, 14)); // NOI18N
        jLabel13.setText("Tên sách:");

        txtTenSach.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtTenSach.setText("Một chiều cô đơn");

        jLabel14.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jLabel14.setText("Tác giả:");

        txtTacGia.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtTacGia.setText("Lâm Chấn Huy");

        jLabel15.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jLabel15.setText("Số lượng mất:");

        jLabel16.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jLabel16.setText("Giá/cuốn:");

        txtGiaCuon.setBorder(new LineBorder(new Color(128, 128, 128)));
        txtGiaCuon.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtGiaCuon.setText("20.000");
        txtGiaCuon.setEnabled(false);

        lbLine.setForeground(new java.awt.Color(204, 204, 204));
        lbLine.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)));

        jLabel4.setForeground(new java.awt.Color(127, 127, 127));
        jLabel4.setText("Đền bù");

        btnXacNhan.setBackground(new java.awt.Color(22, 113, 221));
        btnXacNhan.setForeground(new java.awt.Color(255, 255, 255));
        btnXacNhan.setText("Xác nhận");
        btnXacNhan.setBorderColor(new java.awt.Color(22, 113, 221));
        btnXacNhan.setColor(new java.awt.Color(22, 113, 221));
        btnXacNhan.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnXacNhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                thongBaoMatSach();
            }
        });

        snSoLuongMat.setModel(new javax.swing.SpinnerNumberModel());
        snSoLuongMat.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                thayDoiSL();
            }
        });
        
        txtDenBu = new JLabel("0");
        txtDenBu.setForeground(new Color(255, 0, 0));
        txtDenBu.setFont(new Font("Segoe UI", Font.BOLD, 16));

        javax.swing.GroupLayout panelBorder_Basic2Layout = new javax.swing.GroupLayout(panelBorder_Basic2);
        panelBorder_Basic2Layout.setHorizontalGroup(
        	panelBorder_Basic2Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(panelBorder_Basic2Layout.createSequentialGroup()
        			.addGap(35)
        			.addGroup(panelBorder_Basic2Layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(lbLine, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
        				.addGroup(panelBorder_Basic2Layout.createSequentialGroup()
        					.addGroup(panelBorder_Basic2Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jLabel14)
        						.addComponent(jLabel13)
        						.addComponent(jLabel15)
        						.addComponent(jLabel16))
        					.addGap(24)
        					.addGroup(panelBorder_Basic2Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(txtGiaCuon, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
        						.addGroup(panelBorder_Basic2Layout.createParallelGroup(Alignment.LEADING, false)
        							.addComponent(txtTacGia, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
        							.addComponent(txtTenSach)
        							.addComponent(snSoLuongMat, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))))
        				.addGroup(panelBorder_Basic2Layout.createSequentialGroup()
        					.addGroup(panelBorder_Basic2Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jLabel4)
        						.addComponent(txtDenBu, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
        					.addGap(108)
        					.addComponent(btnXacNhan, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
        			.addGap(35))
        );
        panelBorder_Basic2Layout.setVerticalGroup(
        	panelBorder_Basic2Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(panelBorder_Basic2Layout.createSequentialGroup()
        			.addGap(25)
        			.addGroup(panelBorder_Basic2Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel13)
        				.addComponent(txtTenSach))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(panelBorder_Basic2Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel14)
        				.addComponent(txtTacGia))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(panelBorder_Basic2Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel15)
        				.addComponent(snSoLuongMat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(panelBorder_Basic2Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel16)
        				.addComponent(txtGiaCuon, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addComponent(lbLine, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(panelBorder_Basic2Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(panelBorder_Basic2Layout.createSequentialGroup()
        					.addComponent(jLabel4)
        					.addGap(2)
        					.addComponent(txtDenBu))
        				.addComponent(btnXacNhan, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
        			.addContainerGap())
        );
        panelBorder_Basic2.setLayout(panelBorder_Basic2Layout);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/warehouse-white.png"))); // NOI18N
        jLabel3.setText("Báo mất");

        javax.swing.GroupLayout panelBorder_Statistic_Blue1Layout = new javax.swing.GroupLayout(panelBorder_Statistic_Blue1);
        panelBorder_Statistic_Blue1.setLayout(panelBorder_Statistic_Blue1Layout);
        panelBorder_Statistic_Blue1Layout.setHorizontalGroup(
            panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Blue1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder_Basic2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBorder_Statistic_Blue1Layout.setVerticalGroup(
            panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Blue1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder_Basic2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder_Statistic_Blue1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder_Statistic_Blue1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    protected void thongBaoMatSach() 
    {
		int slMat = (int)snSoLuongMat.getValue();
		if(slMat < 1)
		{
			JOptionPane.showMessageDialog(null, "Số lượng sách mất phải lớn hơn 1", "Lỗi",JOptionPane.ERROR_MESSAGE);
			return;
		}
		else
		{
            try {
				PayBUS payBUS = new PayBUS();
				payBUS.BooksLost(bc, slMat, selectBook.getISBN());
				parent.updateTBSach(slMat);
				parent.updateTienCoc(tienCanDenBu);
				JOptionPane.showMessageDialog(null, "Báo mất sách thành công!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
				dispose();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

    private void thayDoiSL()
    {
        int soLuong = (int) snSoLuongMat.getValue();
        if(soLuong < 0)
        {
            snSoLuongMat.setValue(0);
            return;
        }
        
        if (soLuong > selectBook.getNum()) 
        {
	        snSoLuongMat.setValue(selectBook.getNum());
	        soLuong = selectBook.getNum();
        }

        if (selectBook!= null) 
        {
        	tienCanDenBu =soLuong * (int)bookCost;
        	DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String formattedNumber = decimalFormat.format(tienCanDenBu);
            txtDenBu.setText(formattedNumber);
        }   
     
    }

    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lbLine;
    private MyDesign.MyButton btnXacNhan;
    private MyDesign.PanelBorder_Basic panelBorder_Basic2;
    private MyDesign.PanelBorder_Statistic_Blue panelBorder_Statistic_Blue1;
    private javax.swing.JSpinner snSoLuongMat;
    private MyDesign.MyTextField_Basic txtGiaCuon;
    private javax.swing.JLabel txtTacGia;
    private javax.swing.JLabel txtTenSach;
    private JLabel txtDenBu;
}
