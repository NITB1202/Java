/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import MyDesign.ScrollBar;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import BUS.BorrowCardBUS;
import DTO.entities.BorrowCard;
import DTO.entities.Book;
import DTO.entities.Book1;
import DTO.entities.DetailBC;
import java.util.Date;

/**
 *
 * @author QUANG DIEN
 */
public class ReaderHistoryDone_Dialog extends javax.swing.JDialog {
    BorrowCardBUS sbc;
    Vector<BorrowCard> a;
    /**
     * Creates new form ReaderHistory_Dialog
     */
    public ReaderHistoryDone_Dialog(java.awt.Frame parent, boolean modal,int id) {
        super(parent, modal);
        initComponents(id);
        try {
            sbc=new BorrowCardBUS();
            getData(id);
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
        }
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        
        sp.setVerticalScrollBar(new ScrollBar());
        sp.getVerticalScrollBar().setBackground(Color.WHITE);
        sp.getViewport().setBackground(Color.WHITE);
        p.setBackground(Color.WHITE);
        sp.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        clickFirstRow();
        rbTatCa.setSelected(true);
    }
    
    public void clickFirstRow() {
    	tbPhieuMuon.changeSelection(0, 0, false, false);
        int firstRow = 0;
        int firstColumn = 0;
        int clickCount = 1; // Number of clicks (1 for single-click)
        int modifiers = 0; // No additional modifiers
        if(a.size()>0) {
	        MouseEvent mouseEvent = new MouseEvent(
	        		tbPhieuMuon, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(),
	                modifiers, firstColumn, firstRow, clickCount, false);
	
	        for (MouseListener listener : tbPhieuMuon.getMouseListeners()) {
	            listener.mouseClicked(mouseEvent);
	        }	
        }else {
        	panelBorder5.setBackground(new java.awt.Color(242, 255, 244));
        	viewport.setBackground(new java.awt.Color(242, 255, 244));
        	myTable1.setRowCount(0);
        	lbMaPM.setText("");
        	lbTrangThai.setText("");
        	lbTrangThai.setIcon(null);
        }
    }

    public void getData(int id) throws Exception {
    	tbPhieuMuon.setRowCount(0);
    	a=sbc.getAll(id);
    	for(int i=0;i<a.size();i++) {
    		BorrowCard acc=a.get(i);
    		Date startDate =acc.getStartDate();
    		int num=acc.getNum();
    		String staffName=acc.getStaffname();
    		Object row[] = {i+1,startDate,num,staffName};
    		tbPhieuMuon.addRow(row);
    	}
    }
    
    public void getDataReturn(int id) throws Exception {
    	tbPhieuMuon.setRowCount(0);
    	a=sbc.getReturn(id);
    	for(int i=0;i<a.size();i++) {
    		BorrowCard acc=a.get(i);
    		Date startDate =acc.getStartDate();
    		int num=acc.getNum();
    		String staffName=acc.getStaffname();
    		Object row[] = {i+1,startDate,num,staffName};
    		tbPhieuMuon.addRow(row);
    	}
    }
    
    public void getDataNoReturn(int id) throws Exception {
    	tbPhieuMuon.setRowCount(0);
    	a=sbc.getNoReturn(id);
    	for(int i=0;i<a.size();i++) {
    		BorrowCard acc=a.get(i);
    		Date startDate =acc.getStartDate();
    		int num=acc.getNum();
    		String staffName=acc.getStaffname();
    		Object row[] = {i+1,startDate,num,staffName};
    		tbPhieuMuon.addRow(row);
    	}
    }

    public void addValTab(int idx) {
    	myTable1.setRowCount(0);
            Vector<DetailBC> tmp=a.get(idx).getListBook();
    	for(int i=0;i<tmp.size();i++) {
    		Object row1[] = {"Sách "+(i+1)," "};
    		Object row2[] = {"Tên sách",tmp.get(i).getBookname()};
    		Object row3[] = {"Tác giả",tmp.get(i).getAuthorname().get(0)};
    		Object row4[][]=new Object[tmp.get(i).getAuthorname().size()][2];
    		for(int j=1;j<tmp.get(i).getAuthorname().size();j++) {
    			row4[j][0]="";
    			row4[j][1]=tmp.get(i).getAuthorname().get(j);
    		}
    		Object row5[] = {"Số lượng",tmp.get(i).getNum()};
    		Object row6[] = {"Báo mất",tmp.get(i).getLost()};
    		myTable1.addRow(row1);
    		myTable1.addRow(row2);
    		myTable1.addRow(row3);
    		for(int j=1;j<tmp.get(i).getAuthorname().size();j++) {
    			myTable1.addRow(row4[j]);
    		}
    		myTable1.addRow(row5);
    		myTable1.addRow(row6);
    	}
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents(int userID) {

        btnGroup = new javax.swing.ButtonGroup();
        panelBorder_Statistic_Blue1 = new MyDesign.PanelBorder_Statistic_Blue();
        panelBorder_Basic1 = new MyDesign.PanelBorder_Basic();
        panelBorder1 = new MyDesign.PanelBorder();
        jLabel5 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        tbPhieuMuon = new MyDesign.MyTable();
        panelBorder_Basic2 = new MyDesign.PanelBorder_Basic();
        rbTatCa = new javax.swing.JRadioButton();
        rbDaTra = new javax.swing.JRadioButton();
        rbChuaTra = new javax.swing.JRadioButton();
        panelBorder5 = new MyDesign.PanelBorder();
        lbMaPM = new javax.swing.JLabel();
        lbTrangThai = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        sp = new javax.swing.JScrollPane();
        myTable1 = new MyDesign.MyTable();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(127, 127, 127));
        jLabel5.setText("Phiếu mượn");

        spTable.setBorder(null);

        tbPhieuMuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Ngày mượn", "Số quyển", "Nhân viên"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(tbPhieuMuon);
        tbPhieuMuon.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int row = tbPhieuMuon.getSelectedRow();
                    if (row >= 0) {
                    	BorrowCard tmp =a.get(row);
                    	lbMaPM.setText("#"+tmp.getID());
                        addValTab(row);
                        if(tmp.getRealReDate()==null) {
                        	lbTrangThai.setText("Chưa trả");
                            lbTrangThai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/RedNode.png")));
                        	lbTrangThai.setForeground(new java.awt.Color(234, 38, 44));
                        	lbMaPM.setForeground(new java.awt.Color(234, 38, 44));
                        	panelBorder5.setBackground(new java.awt.Color(255, 241, 241));
                            viewport.setBackground(new java.awt.Color(255, 241, 241));
                            for (int i = 0; i < myTable1.getColumnCount(); i++) {
                            	myTable1.getColumnModel().getColumn(i).setCellRenderer(cellRed);
                            }
                        }else {
                        	lbTrangThai.setText("Đã trả");
                        	lbTrangThai.setForeground(new java.awt.Color(18, 210, 49));
                            lbTrangThai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Node.png")));
                        	lbMaPM.setForeground(new java.awt.Color(18, 210, 49));
                        	panelBorder5.setBackground(new java.awt.Color(242, 255, 244));
                        	viewport.setBackground(new java.awt.Color(242, 255, 244));
                        	for (int i = 0; i < myTable1.getColumnCount(); i++) {
                            	myTable1.getColumnModel().getColumn(i).setCellRenderer(cellGreen);
                            }
                        }
                    }
                }
            }
        });


        btnGroup.add(rbTatCa);
        rbTatCa.setText("Tất cả");
        rbTatCa.setBackground(Color.WHITE);
        rbTatCa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
					getData(userID);
					clickFirstRow();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
            }
        });
        
        btnGroup.add(rbDaTra);
        rbDaTra.setText("Đã trả");
        rbDaTra.setBackground(Color.WHITE);
        rbDaTra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
					getDataReturn(userID);
					clickFirstRow();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
            }
        });

        btnGroup.add(rbChuaTra);
        rbChuaTra.setText("Chưa trả");
        rbChuaTra.setBackground(Color.WHITE);
        rbChuaTra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
					getDataNoReturn(userID);
					clickFirstRow();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
            }
        });


        javax.swing.GroupLayout panelBorder_Basic2Layout = new javax.swing.GroupLayout(panelBorder_Basic2);
        panelBorder_Basic2.setLayout(panelBorder_Basic2Layout);
        panelBorder_Basic2Layout.setHorizontalGroup(
            panelBorder_Basic2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder_Basic2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbTatCa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbDaTra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbChuaTra)
                .addContainerGap())
        );
        panelBorder_Basic2Layout.setVerticalGroup(
            panelBorder_Basic2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder_Basic2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBorder_Basic2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbDaTra)
                    .addComponent(rbChuaTra)
                    .addComponent(rbTatCa))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelBorder_Basic2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel5))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelBorder_Basic2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelBorder5.setBackground(new java.awt.Color(242, 255, 244));
        panelBorder5.setOpaque(true);
        panelBorder5.setPreferredSize(new java.awt.Dimension(217, 327));

        lbMaPM.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lbMaPM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbTrangThai.setForeground(new java.awt.Color(18, 210, 49));
        lbTrangThai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel33.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(127, 127, 127));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("MÃ PHIẾU MƯỢN");

        sp.setBorder(null);
        sp.setColumnHeaderView(null);
        sp.setColumnHeaderView(null);

        cellRed = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                component.setBackground(new java.awt.Color(255, 241, 241));
                myTable1.setGridColor(new java.awt.Color(255, 241, 241));
                if (column == 0) {
                    Font boldFont = new Font(component.getFont().getName(), Font.BOLD, component.getFont().getSize());
                    component.setFont(boldFont);
                }
                return component;
            }
        };
        
        cellGreen = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                component.setBackground(new java.awt.Color(242, 255, 244));
                myTable1.setGridColor(new java.awt.Color(242, 255, 244));
                if (column == 0) {
                    Font boldFont = new Font(component.getFont().getName(), Font.BOLD, component.getFont().getSize());
                    component.setFont(boldFont);
                }
                return component;
            }
        };

        myTable1.setBackground(new java.awt.Color(242, 255, 244));
        myTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JTableHeader tableHeader = myTable1.getTableHeader();
        tableHeader.setPreferredSize(new java.awt.Dimension(0, 0));
        sp.setViewportView(myTable1);
        viewport = sp.getViewport();

        javax.swing.GroupLayout panelBorder5Layout = new javax.swing.GroupLayout(panelBorder5);
        panelBorder5.setLayout(panelBorder5Layout);
        panelBorder5Layout.setHorizontalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbMaPM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBorder5Layout.setVerticalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33)
                .addGap(0, 0, 0)
                .addComponent(lbMaPM)
                .addGap(0, 0, 0)
                .addComponent(lbTrangThai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelBorder_Basic1Layout = new javax.swing.GroupLayout(panelBorder_Basic1);
        panelBorder_Basic1.setLayout(panelBorder_Basic1Layout);
        panelBorder_Basic1Layout.setHorizontalGroup(
            panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder5, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBorder_Basic1Layout.setVerticalGroup(
            panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder_Basic1Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder5, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/info-history-white.png"))); // NOI18N
        jLabel3.setText("Lịch sử mượn sách");

        javax.swing.GroupLayout panelBorder_Statistic_Blue1Layout = new javax.swing.GroupLayout(panelBorder_Statistic_Blue1);
        panelBorder_Statistic_Blue1.setLayout(panelBorder_Statistic_Blue1Layout);
        panelBorder_Statistic_Blue1Layout.setHorizontalGroup(
            panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Blue1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder_Basic1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBorder_Statistic_Blue1Layout.setVerticalGroup(
            panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder_Statistic_Blue1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder_Basic1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReaderHistoryDone_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReaderHistoryDone_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReaderHistoryDone_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReaderHistoryDone_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        // java.awt.EventQueue.invokeLater(new Runnable() {
        //     public void run() {
        //         ReaderHistoryDone_Dialog dialog = new ReaderHistoryDone_Dialog(new javax.swing.JFrame(), true);
        //         dialog.addWindowListener(new java.awt.event.WindowAdapter() {
        //             @Override
        //             public void windowClosing(java.awt.event.WindowEvent e) {
        //                 System.exit(0);
        //             }
        //         });
        //         dialog.setVisible(true);
        //     }
        // });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroup;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lbMaPM;
    private javax.swing.JLabel lbTrangThai;
    private MyDesign.MyTable myTable1;
    private MyDesign.PanelBorder panelBorder1;
    private MyDesign.PanelBorder panelBorder5;
    private MyDesign.PanelBorder_Basic panelBorder_Basic1;
    private MyDesign.PanelBorder_Basic panelBorder_Basic2;
    private MyDesign.PanelBorder_Statistic_Blue panelBorder_Statistic_Blue1;
    private javax.swing.JRadioButton rbChuaTra;
    private javax.swing.JRadioButton rbDaTra;
    private javax.swing.JRadioButton rbTatCa;
    private javax.swing.JScrollPane sp;
    private javax.swing.JScrollPane spTable;
    private MyDesign.MyTable tbPhieuMuon;
    private JViewport viewport;
    private DefaultTableCellRenderer cellRed;
    private DefaultTableCellRenderer cellGreen;
    // End of variables declaration//GEN-END:variables
}
