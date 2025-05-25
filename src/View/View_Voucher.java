/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Model.Model_voucher;
import Repository.Responsitory_Voucher;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PonYanki
 */
public class View_Voucher extends javax.swing.JPanel {

    private DefaultTableModel mol = new DefaultTableModel();
    public Responsitory_Voucher repo_voucher;
    private int i = -1;

    /**
     * Creates new form View_Voucher
     */
    public View_Voucher() throws SQLException {
        initComponents();
        repo_voucher = new Responsitory_Voucher();
        repo_voucher.autoUpdate();
        repo_voucher.autoUpdate1();
        filltable_voucher(repo_voucher.getAll_voucher());
    }

    public void filltable_voucher(ArrayList<Model_voucher> list) throws SQLException {
        mol = (DefaultTableModel) tbl_voucher.getModel();
        mol.setRowCount(0);
        for (Model_voucher x : list) {
            mol.addRow(x.toDataRow());

        }
        tbl_voucher.setModel(mol);
    }

    void showDaTa1(int i) {
        String id = tbl_voucher.getValueAt(i, 1).toString();
        String ten = tbl_voucher.getValueAt(i, 2).toString();
        String phanTramGiam = tbl_voucher.getValueAt(i, 3).toString();
        String donHangToiThieu = tbl_voucher.getValueAt(i, 4).toString();
        String ngayBD = tbl_voucher.getValueAt(i, 5).toString();
        String ngayKT = tbl_voucher.getValueAt(i, 6).toString();
        String giaTriGiamToiDa = tbl_voucher.getValueAt(i, 7).toString();
         String trang = tbl_voucher.getValueAt(i, 8).toString();
        
        txt_maVC.setText(id);
        txt_tenVC.setText(ten);
        txt_giamphanTram.setText(phanTramGiam);
        txt_dkVC.setText(donHangToiThieu);
        txt_ngayBatDau.setText(ngayBD);
        txt_ngayKetThuc.setText(ngayKT);
        txt_toida.setText(giaTriGiamToiDa);
        txt_toida.setText(giaTriGiamToiDa);
        
        if(trang.equalsIgnoreCase("Đang Hoạt Động")){
            rdo_hd.setSelected(true);
        } 
        else if (trang.equalsIgnoreCase("Không Hoạt Động")){
             rdo_khd.setSelected(true);
        }
       
    }

    public boolean readForm() throws SQLException {
        // Kiểm tra mã voucher (txt_maVC)
        String maVC = txt_maVC.getText().trim();
        if (maVC.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã voucher!");
            txt_maVC.requestFocus();
            return false;
        }
        for (int i = 0; i < tbl_voucher.getRowCount(); i++) {
            Object existingValue = tbl_voucher.getValueAt(i, 1);

            if (existingValue != null && maVC.equalsIgnoreCase(existingValue.toString())) {
                JOptionPane.showMessageDialog(this, "Mã voucher đã tồn tại!");
                return false;
            }
        }
        if (maVC.length() > 5) {
            JOptionPane.showMessageDialog(this, "Mã voucher không được quá 5 ký tự!");
            txt_maVC.requestFocus();
            return false;
        }

        // Kiểm tra tên voucher (txt_tenVC)
        String tenVC = txt_tenVC.getText().trim();
        if (tenVC.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên voucher!");
            txt_tenVC.requestFocus();
            return false;
        }

        // Kiểm tra điều kiện giảm giá (txt_dkVC): chỉ cho phép số
        String dkVC = txt_dkVC.getText().trim();
        if (!dkVC.matches("\\d+")) { // Kiểm tra chỉ chứa số
            JOptionPane.showMessageDialog(this, "Điều kiện giảm giá chỉ được nhập số!");
            txt_dkVC.requestFocus();
            return false;
        }

        // Kiểm tra phần trăm giảm giá (txt_giamphanTram): chỉ cho phép số
        String giamPhanTram = txt_giamphanTram.getText().trim();
        if (!giamPhanTram.matches("\\d+")) { // Kiểm tra chỉ chứa số
            JOptionPane.showMessageDialog(this, "Giảm phần trăm chỉ được nhập số!");
            txt_giamphanTram.requestFocus();
            return false;
        }

        // Kiểm tra ngày bắt đầu (txt_ngayBatDau): đúng định dạng YYYY/MM/DD
        String ngayBatDau = txt_ngayBatDau.getText().trim();
        if (!ngayBatDau.matches("\\d{4}-\\d{2}-\\d{2}")) { // Kiểm tra định dạng
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải đúng định dạng YYYY/MM/DD!");
            txt_ngayBatDau.requestFocus();
            return false;
        }

        // Kiểm tra ngày kết thúc (txt_ngayKetThuc): đúng định dạng và lớn hơn ngày bắt đầu
        String ngayKetThuc = txt_ngayKetThuc.getText().trim();
        if (!ngayKetThuc.matches("\\d{4}-\\d{2}-\\d{2}")) { // Kiểm tra định dạng
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải đúng định dạng YYYY/MM/DD!");
            txt_ngayKetThuc.requestFocus();
            return false;
        }
        // So sánh ngày bắt đầu và ngày kết thúc
        if (ngayBatDau.compareTo(ngayKetThuc) >= 0) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu!");
            txt_ngayKetThuc.requestFocus();
            return false;
        }

        // Kiểm tra giá giảm tối đa (txt_toida): chỉ cho phép số
        String giaToiDa = txt_toida.getText().trim();
        if (!giaToiDa.matches("\\d+")) { // Kiểm tra chỉ chứa số
            JOptionPane.showMessageDialog(this, "Giá giảm tối đa chỉ được nhập số!");
            txt_toida.requestFocus();
            return false;
        }

        // Nếu tất cả kiểm tra đều hợp lệ
        return true;
    }
    private String originalMaVC;

    public boolean readForm_update() throws SQLException {
        // Kiểm tra mã voucher (txt_maVC)
        String maVC = txt_maVC.getText().trim();
        if (maVC.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã voucher!");
            txt_maVC.requestFocus();
            return false;
        }
        if (originalMaVC != null && !originalMaVC.equalsIgnoreCase(maVC)) {
            JOptionPane.showMessageDialog(this, "Mã voucher không được phép sửa!");
            txt_maVC.setText(originalMaVC); // Khôi phục mã gốc
            txt_maVC.requestFocus();
            return false;
        }
        if (maVC.length() > 5) {
            JOptionPane.showMessageDialog(this, "Mã voucher không được quá 5 ký tự!");
            txt_maVC.requestFocus();
            return false;
        }
        String tenVC = txt_tenVC.getText().trim();
        if (tenVC.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên voucher!");
            txt_tenVC.requestFocus();
            return false;
        }
        String dkVC = txt_dkVC.getText().trim();
        if (!dkVC.matches("\\d+")) { // Kiểm tra chỉ chứa số
            JOptionPane.showMessageDialog(this, "Điều kiện giảm giá chỉ được nhập số!");
            txt_dkVC.requestFocus();
            return false;
        }
        String giamPhanTram = txt_giamphanTram.getText().trim();
        if (!giamPhanTram.matches("\\d+")) { // Kiểm tra chỉ chứa số
            JOptionPane.showMessageDialog(this, "Giảm phần trăm chỉ được nhập số!");
            txt_giamphanTram.requestFocus();
            return false;
        }
        String ngayBatDau = txt_ngayBatDau.getText().trim();
        if (!ngayBatDau.matches("\\d{4}-\\d{2}-\\d{2}")) { // Kiểm tra định dạng
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải đúng định dạng YYYY/MM/DD!");
            txt_ngayBatDau.requestFocus();
            return false;
        }
        String ngayKetThuc = txt_ngayKetThuc.getText().trim();
        if (!ngayKetThuc.matches("\\d{4}-\\d{2}-\\d{2}")) { // Kiểm tra định dạng
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải đúng định dạng YYYY/MM/DD!");
            txt_ngayKetThuc.requestFocus();
            return false;
        }
        if (ngayBatDau.compareTo(ngayKetThuc) >= 0) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu!");
            txt_ngayKetThuc.requestFocus();
            return false;
        }
        String giaToiDa = txt_toida.getText().trim();
        if (!giaToiDa.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Giá giảm tối đa chỉ được nhập số!");
            txt_toida.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_maVC = new javax.swing.JTextField();
        txt_tenVC = new javax.swing.JTextField();
        txt_dkVC = new javax.swing.JTextField();
        txt_giamphanTram = new javax.swing.JTextField();
        txt_ngayBatDau = new javax.swing.JTextField();
        txt_ngayKetThuc = new javax.swing.JTextField();
        txt_toida = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        rdo_hd = new javax.swing.JRadioButton();
        rdo_khd = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_voucher = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Voucher"));

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Sửa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Reset");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("Mã voucher");

        jLabel2.setText("Tên voucher");

        jLabel3.setText("Đơn hàng tối thiểu");

        jLabel4.setText("GIảm (%)");

        jLabel5.setText("Ngày bắt đầu");

        jLabel6.setText("Ngày kết thúc");

        txt_ngayBatDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ngayBatDauActionPerformed(evt);
            }
        });

        txt_ngayKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ngayKetThucActionPerformed(evt);
            }
        });

        jLabel7.setText("Số tiền giảm tối đa");

        jLabel8.setText("Trạng thái");

        buttonGroup1.add(rdo_hd);
        rdo_hd.setSelected(true);
        rdo_hd.setText("Đang hoạt động");

        buttonGroup1.add(rdo_khd);
        rdo_khd.setText("Không hoạt động");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_maVC, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(txt_tenVC, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_giamphanTram, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_dkVC, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txt_ngayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_toida, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_ngayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdo_hd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdo_khd)))))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txt_ngayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)
                        .addComponent(txt_maVC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_tenVC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txt_ngayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_dkVC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jButton3)
                    .addComponent(txt_toida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_giamphanTram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(rdo_hd)
                    .addComponent(rdo_khd))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách Voucher"));

        tbl_voucher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã voucher", "Tên voucher", "Phần trăm giảm", "ĐK giảm", "Ngày bắt đầu", "Ngày kết thúc", "Số tiền giảm tối đa", "Trạng Thái"
            }
        ));
        tbl_voucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_voucherMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_voucher);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_voucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_voucherMouseClicked
        i = tbl_voucher.getSelectedRow();
        this.showDaTa1(i);
    }//GEN-LAST:event_tbl_voucherMouseClicked

    private void txt_ngayKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ngayKetThucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ngayKetThucActionPerformed

    private void txt_ngayBatDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ngayBatDauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ngayBatDauActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (readForm()) {
                Model_voucher vc = new Model_voucher(
                        txt_maVC.getText().trim(),
                        txt_tenVC.getText().trim(),
                        Integer.parseInt(txt_giamphanTram.getText().trim()),
                        Integer.parseInt(txt_dkVC.getText().trim()),
                        txt_ngayBatDau.getText().trim(),
                        txt_ngayKetThuc.getText().trim(),
                        Integer.parseInt(txt_toida.getText().trim()),
                        rdo_khd.isSelected() ? 1 : 0);
                try {
                    int result = repo_voucher.add_voucher(vc);
                    if (result > 0) {
                        JOptionPane.showMessageDialog(this, "Voucher đã được thêm thành công");
                        repo_voucher.autoUpdate();
                        repo_voucher.autoUpdate1();
                        filltable_voucher(repo_voucher.getAll_voucher());
                    } else {
                        JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi thêm voucher");
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(View_Voucher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            if (readForm_update()) {
                Model_voucher vc = new Model_voucher(
                        txt_maVC.getText().trim(),
                        txt_tenVC.getText().trim(),
                        Integer.parseInt(txt_giamphanTram.getText().trim()),
                        Integer.parseInt(txt_dkVC.getText().trim()),
                        txt_ngayBatDau.getText().trim(),
                        txt_ngayKetThuc.getText().trim(),
                        Integer.parseInt(txt_toida.getText().trim()),
                        rdo_khd.isSelected() ? 1 : 0);
                try {
                    int result = repo_voucher.update_voucher(vc);
                    if (result > 0) {
                        JOptionPane.showMessageDialog(this, "Sửa thành công");
                        repo_voucher.autoUpdate();
                        repo_voucher.autoUpdate1();
                        filltable_voucher(repo_voucher.getAll_voucher());
                    } else {
                        JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi sửa voucher ");
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(View_Voucher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        txt_dkVC.setText("");
        txt_giamphanTram.setText("");
        txt_maVC.setText("");
        txt_ngayBatDau.setText("");
        txt_ngayKetThuc.setText("");
        txt_tenVC.setText("");
        txt_toida.setText("");
        rdo_khd.setSelected(true);
        try {
            filltable_voucher(repo_voucher.getAll_voucher());
        } catch (SQLException ex) {
            Logger.getLogger(View_Voucher.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            repo_voucher.autoUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(View_Voucher.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            repo_voucher.autoUpdate1();
        } catch (SQLException ex) {
            Logger.getLogger(View_Voucher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdo_hd;
    private javax.swing.JRadioButton rdo_khd;
    private javax.swing.JTable tbl_voucher;
    private javax.swing.JTextField txt_dkVC;
    private javax.swing.JTextField txt_giamphanTram;
    private javax.swing.JTextField txt_maVC;
    private javax.swing.JTextField txt_ngayBatDau;
    private javax.swing.JTextField txt_ngayKetThuc;
    private javax.swing.JTextField txt_tenVC;
    private javax.swing.JTextField txt_toida;
    // End of variables declaration//GEN-END:variables
}
