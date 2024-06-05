package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import BUS.ReaderBUS;
import DTO.entities.Reader;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class ReaderAdd1_Dialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	 ReaderBUS readerBUS;    
	 MyDesign.MyTable tab;
	 
	  public ReaderAdd1_Dialog(JFrame parent, boolean modal, MyDesign.MyTable tab) throws ClassNotFoundException, SQLException {
	        super(parent, modal);
	        readerBUS = new ReaderBUS();
	        this.tab = tab;
	        initComponents();
	    }
	  
	  public void addDefault(MyDesign.MyTable tab) throws Exception {
	        tab.setRowCount(0);
	        Vector<Reader> arr = readerBUS.getAll();
	        for (int i = 0; i < arr.size(); i++) {
	            Reader acc = arr.get(i);
	            int id = acc.getPersonID();
	            String name = acc.getName();
	            String tel = acc.getTel();
	            String address = acc.getAddress();
	            LocalDate fineDate = acc.getFineDate();
	            Integer isLocked = acc.getStatus();
	            String isL = "Mở";
	            if (isLocked == 1) {
	                isL = "Khoá";
	            }
	            long daysBetween = 0; 
	            if (fineDate != null) {
	                LocalDate cuDate = LocalDate.now();
	                daysBetween = ChronoUnit.DAYS.between(cuDate, fineDate);
	            }        
	            Object row[] = {i + 1, id, name, tel, address, daysBetween, isL};
	            tab.addRow(row);
	        }
	    }

	    public boolean checkDataVal(String name, String tel, String address) throws HeadlessException, FileNotFoundException, ClassNotFoundException, IOException, SQLException {
	        if (name.equals("")) {
	            JOptionPane.showMessageDialog(null, "Họ và tên không được để trống");
	            txtTen.requestFocus();
	            return false;
	        }
	        String nameReg = "^[\\p{L} \\.'\\-]+$";
	        if (!name.matches(nameReg)) {
	            JOptionPane.showMessageDialog(null, "Họ tên không hợp lệ");
	            txtTen.requestFocus();
	            return false;
	        }
	        if (tel.equals("")) {
	            JOptionPane.showMessageDialog(null, "Số điện thoại không được để trống");
	            txtSoDienThoai.requestFocus();
	            return false;
	        }
	        if (!readerBUS.checkTel(tel)) {
	            JOptionPane.showMessageDialog(null, "Số điện thoại đã có trong dữ liệu");
	            txtSoDienThoai.requestFocus();
	            return false;
	        }
	        String telReg = "^0[1-9][0-9]{8}$";
	        if (!tel.matches(telReg)) {
	            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ");
	            txtSoDienThoai.requestFocus();
	            return false;
	        }
	        if (address.equals("")) {
	            JOptionPane.showMessageDialog(null, "Địa chỉ không được để trống");
	            txtDiaChi.requestFocus();
	            return false;
	        }
	        return true;
	    }

	    private void btnThemDocGiaActionPerformed(java.awt.event.ActionEvent evt) {
	        String name = txtTen.getText().trim();
	        String tel = txtSoDienThoai.getText().trim();
	        String address = txtDiaChi.getText().trim();
	        try {
	            if (checkDataVal(name, tel, address)) {
	                JOptionPane.showMessageDialog(null, readerBUS.addReader(new Reader(name, tel, address)));
	                addDefault(tab);
	                dispose();
	            }
	        } catch (Exception e1) {
	            JOptionPane.showMessageDialog(null, e1.getMessage());
	        }
	    }

	    private void initComponents() {
	        panelBorder_Statistic_Blue1 = new MyDesign.PanelBorder_Statistic_Blue();
	        panelBorder_Basic1 = new MyDesign.PanelBorder_Basic();
	        jLabel8 = new javax.swing.JLabel();
	        txtTen = new MyDesign.MyTextField_Basic();
	        jLabel9 = new javax.swing.JLabel();
	        txtSoDienThoai = new MyDesign.MyTextField_Basic();
	        jLabel10 = new javax.swing.JLabel();
	        txtDiaChi = new MyDesign.MyTextField_Basic();
	        btnThemDocGia = new MyDesign.MyButton();
	        jLabel3 = new javax.swing.JLabel();

	        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

	        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
	        jLabel8.setText("Tên");

	        txtTen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));

	        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
	        jLabel9.setText("Số điện thoại");

	        txtSoDienThoai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));

	        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
	        jLabel10.setText("Địa chỉ");

	        txtDiaChi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));

	        btnThemDocGia.setBackground(new java.awt.Color(22, 113, 221));
	        btnThemDocGia.setForeground(new java.awt.Color(255, 255, 255));
	        btnThemDocGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/action-add-white.png"))); // NOI18N
	        btnThemDocGia.setText("Thêm độc giả");
	        btnThemDocGia.setBorderColor(new java.awt.Color(22, 113, 221));
	        btnThemDocGia.setColor(new java.awt.Color(22, 113, 221));
	        btnThemDocGia.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
	        btnThemDocGia.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                btnThemDocGiaActionPerformed(evt);
	            }
	        });

	        javax.swing.GroupLayout panelBorder_Basic1Layout = new javax.swing.GroupLayout(panelBorder_Basic1);
	        panelBorder_Basic1.setLayout(panelBorder_Basic1Layout);
	        panelBorder_Basic1Layout.setHorizontalGroup(
	            panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder_Basic1Layout.createSequentialGroup()
	                .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
	                        .addContainerGap(25, Short.MAX_VALUE)
	                        .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel8)
	                            .addComponent(jLabel9)
	                            .addComponent(jLabel10))
	                        .addGap(45, 45, 45)
	                        .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                            .addComponent(txtSoDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                            .addComponent(txtTen, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
	                            .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
	                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder_Basic1Layout.createSequentialGroup()
	                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addComponent(btnThemDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addGap(28, 28, 28))
	        );
	        panelBorder_Basic1Layout.setVerticalGroup(
	            panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
	                .addGap(17, 17, 17)
	                .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel8))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel9))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel10))
	                .addGap(18, 18, 18)
	                .addComponent(btnThemDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(18, Short.MAX_VALUE))
	        );

	        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
	        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
	        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/nav-reader.png"))); // NOI18N
	        jLabel3.setText("Thêm độc giả");

	        javax.swing.GroupLayout panelBorder_Statistic_Blue1Layout = new javax.swing.GroupLayout(panelBorder_Statistic_Blue1);
	        panelBorder_Statistic_Blue1.setLayout(panelBorder_Statistic_Blue1Layout);
	        panelBorder_Statistic_Blue1Layout.setHorizontalGroup(
	            panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(panelBorder_Statistic_Blue1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(panelBorder_Basic1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                .addContainerGap())
	        );
	        panelBorder_Statistic_Blue1Layout.setVerticalGroup(
	            panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(panelBorder_Statistic_Blue1Layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(panelBorder_Basic1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
	        setLocationRelativeTo(null);
	    }

	    
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReaderAdd_Dialog dialog = new ReaderAdd_Dialog(new javax.swing.JFrame(), true, new MyDesign.MyTable());
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
	}

	/**
	 * Create the dialog.
	 */
	public ReaderAdd1_Dialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	 private MyDesign.MyButton btnThemDocGia;
	    private javax.swing.JLabel jLabel10;
	    private javax.swing.JLabel jLabel3;
	    private javax.swing.JLabel jLabel8;
	    private javax.swing.JLabel jLabel9;
	    private MyDesign.PanelBorder_Basic panelBorder_Basic1;
	    private MyDesign.PanelBorder_Statistic_Blue panelBorder_Statistic_Blue1;
	    private MyDesign.MyTextField_Basic txtDiaChi;
	    private MyDesign.MyTextField_Basic txtSoDienThoai;
	    private MyDesign.MyTextField_Basic txtTen;
}
