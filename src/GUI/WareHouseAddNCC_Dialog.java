/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import BUS.PublisherBUS;
import BUS.SupplierBUS;
import BUS.SupplyCardBUS;
import DTO.entities.Account;
import DTO.entities.Supplier;
import DTO.entities.SupplyCard;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author QUANG DIEN
 */
public class WareHouseAddNCC_Dialog extends javax.swing.JDialog {
    static String nameFrame;
    static Account user;
    /**
     * Creates new form WareHouseAddReader_Dialog
     */
    public WareHouseAddNCC_Dialog(java.awt.Frame parent,String nameFrame,Account user, boolean modal) throws SQLException, IOException, ClassNotFoundException {
        super(parent,nameFrame ,modal);
        WareHouseAddNCC_Dialog.nameFrame = nameFrame;        
        WareHouseAddNCC_Dialog.user = user;

        try {
            initComponents();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WareHouseAddNCC_Dialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() throws SQLException, IOException, ClassNotFoundException{

        panelBorder_Statistic_Blue1 = new MyDesign.PanelBorder_Statistic_Blue();
        panelBorder_Basic1 = new MyDesign.PanelBorder_Basic();
        jLabel8 = new javax.swing.JLabel();
        txtNhaCungCap = new MyDesign.MyTextField_Basic();
        btnThemNhaCungCap = new MyDesign.MyButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel8.setText("Nhà cung cấp");

        txtNhaCungCap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));

        btnThemNhaCungCap.setBackground(new java.awt.Color(22, 113, 221));
        btnThemNhaCungCap.setForeground(new java.awt.Color(255, 255, 255));
        btnThemNhaCungCap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/action-add-white.png"))); // NOI18N
        btnThemNhaCungCap.setText("Thêm nhà cung cấp");
        btnThemNhaCungCap.setBorderColor(new java.awt.Color(22, 113, 221));
        btnThemNhaCungCap.setColor(new java.awt.Color(22, 113, 221));
        btnThemNhaCungCap.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        
        btnThemNhaCungCap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtNhaCungCap.getText().equals("")) {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Vui lòng điền đầy đủ thông tin.", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                    	Supplier p = new Supplier();
                        p.setSupplier_name(txtNhaCungCap.getText());
                        if (nameFrame == "more_gui"){
                            More_GUI gui;
                            try {
                                gui = new More_GUI(WareHouseAddNCC_Dialog.user);
                                if(pub.getByNameAuthor(p.getSupplier_name())!=null)
                                {
                                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Tên NCC đã tồn tại!","Thông báo",JOptionPane.WARNING_MESSAGE);
                                }
                                else {
                                    pub.saveInfo(p);
                                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Thêm Thành Công!", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);                                
                                    List<Supplier> publisherList = pub.getAllName();
                                    int stt = 1;
                                    gui.suppliersModel.setRowCount(0);
                                    for(Supplier item : publisherList)
                                    {
                                        if(item.getSupplier_status() == 1)
                                            gui.suppliersModel.addRow(new Object[]{stt++,item.getSupplier_name()});
                                    }
                                    hide();
                                    gui.setVisible(true);
                                    
                                }
                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }catch (ClassNotFoundException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                    }
                        else{
                            WareHouseImport_Dialog whid;
                            try {
                                whid = new WareHouseImport_Dialog(null, rootPaneCheckingEnabled);
                                if(pub.getByNameAuthor(p.getSupplier_name())!=null)
                                {
                                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Tên NCC đã tồn tại!","Thông báo",JOptionPane.WARNING_MESSAGE);
                                    
                                }
                                else {
                                    pub.saveInfo(p);
                                    String value = txtNhaCungCap.getText();
                                    whid.addToComboBox(value);
                                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Thêm Thành Công!", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
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
                    } catch (HeadlessException e1) {
                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Lỗi Thêm NXB.", "ERROR", JOptionPane.ERROR_MESSAGE);
                        System.out.println(e1);
                    }
                }
            }
        });
        
        javax.swing.GroupLayout panelBorder_Basic1Layout = new javax.swing.GroupLayout(panelBorder_Basic1);
        panelBorder_Basic1.setLayout(panelBorder_Basic1Layout);
        panelBorder_Basic1Layout.setHorizontalGroup(
            panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThemNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        panelBorder_Basic1Layout.setVerticalGroup(
            panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Basic1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panelBorder_Basic1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnThemNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/warehouse-white.png"))); // NOI18N
        jLabel4.setText("Nhà cung cấp");

        javax.swing.GroupLayout panelBorder_Statistic_Blue1Layout = new javax.swing.GroupLayout(panelBorder_Statistic_Blue1);
        panelBorder_Statistic_Blue1.setLayout(panelBorder_Statistic_Blue1Layout);
        panelBorder_Statistic_Blue1Layout.setHorizontalGroup(
            panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder_Statistic_Blue1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder_Basic1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBorder_Statistic_Blue1Layout.setVerticalGroup(
            panelBorder_Statistic_Blue1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder_Statistic_Blue1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private MyDesign.MyButton btnThemNhaCungCap;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private MyDesign.PanelBorder_Basic panelBorder_Basic1;
    private MyDesign.PanelBorder_Statistic_Blue panelBorder_Statistic_Blue1;
    MyDesign.MyTextField_Basic txtNhaCungCap;
    private SupplyCardBUS scb = new SupplyCardBUS();
    private SupplierBUS pub = new SupplierBUS();
    // End of variables declaration//GEN-END:variables
}
