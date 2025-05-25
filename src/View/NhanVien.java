/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Model.Model_NhanVien;
/**
 *
 * @author PonYanki
 */
import Model.Model_NhanVien;
import Repository.Repository_NhanVien;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class NhanVien extends javax.swing.JPanel {

    /**
     * Creates new form SanPham
     */
    private Repository.Repository_NhanVien repo = new Repository_NhanVien();
    private DefaultTableModel mol = new DefaultTableModel();
    private int i = -1;

    public NhanVien() {
        initComponents();
        this.fillTable(repo.getAll());
    }

    void fillTable(ArrayList<Model.Model_NhanVien> list) {
        mol = (DefaultTableModel) tbl_bangNhanVien.getModel();
        mol.setRowCount(0);
        for (Model_NhanVien x : list) {
            mol.addRow(x.toDataRow());
        }
    }

    void showData(int i) {
        String maNhanVien;
        String ten;
        String tuoi;
        String gioiTinh;
        String diaChi;
        String tenDangNhap;
        String matKhau;
        String vaiTro;
        String trangThai;
        // Retrieving values from table based on index `i`
        maNhanVien = tbl_bangNhanVien.getValueAt(i, 1).toString();
        ten = tbl_bangNhanVien.getValueAt(i, 2).toString();
        tuoi = tbl_bangNhanVien.getValueAt(i, 3).toString();
        gioiTinh = tbl_bangNhanVien.getValueAt(i, 4).toString();  // Corrected extra `)`
        diaChi = tbl_bangNhanVien.getValueAt(i, 5).toString();
        tenDangNhap = tbl_bangNhanVien.getValueAt(i, 7).toString();
        matKhau = tbl_bangNhanVien.getValueAt(i, 8).toString();
        vaiTro = tbl_bangNhanVien.getValueAt(i, 6).toString();
        trangThai = tbl_bangNhanVien.getValueAt(i, 11).toString();
        // Setting retrieved values to appropriate text fields
        txt_maNhanVien.setText(maNhanVien);
        txt_tenNhanVien.setText(ten);
        txt_tuoiNhanVien.setText(tuoi);
        txt_diaChi.setText(diaChi);
        txt_taiKhoan.setText(tenDangNhap);
        txt_matKhau.setText(matKhau);
 
        // Setting the gender radio button
        if (vaiTro.equalsIgnoreCase("Quản Lý")) {
            rdo_QuanLy.setSelected(true);
        } else {
            rdo_NhanVien.setSelected(true);
        }
        if (gioiTinh.equalsIgnoreCase("Nam")) {
            rdo_nam.setSelected(true);
        } else {
            rdo_nu.setSelected(true);
        }

        // Setting the status radio button
        if (trangThai.equalsIgnoreCase("Đang Hoạt Động")) {
            rdo_DangHoatDong.setSelected(true);
        } else {
            rdo_khongHoatDong.setSelected(true);
        }
    }

    public Model_NhanVien readFrom() {
        if (txt_maNhanVien.getText().equals(" ")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên.");
            txt_maNhanVien.requestFocus();
            return null;
        } else if (txt_maNhanVien.getText().length() != 5) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên đúng định dạng(5 kí tự)");
            txt_maNhanVien.requestFocus();
            return null;
        } else if (txt_tenNhanVien.getText().equals(" ")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên nhân viên.");
            txt_tenNhanVien.requestFocus();
            return null;
        } else if (txt_tuoiNhanVien.getText().equals(" ")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tuổi nhân viên.");
            txt_tuoiNhanVien.requestFocus();
            return null;
        } else if (Integer.parseInt(txt_tuoiNhanVien.getText()) < 18) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tuổi nhân viên hợp lệ(tuổi>18).");
            txt_tuoiNhanVien.requestFocus();
            return null;
        } else if (txt_diaChi.getText().equals(" ")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ nhân viên.");
            txt_diaChi.requestFocus();
            return null;
        } else if (txt_taiKhoan.getText().equals(" ")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tài khoản nhân viên.");
            txt_taiKhoan.requestFocus();
            return null;
        } else if (txt_matKhau.getText().equals(" ")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu nhân viên.");
            txt_matKhau.requestFocus();
            return null;
        }
        Model_NhanVien nv = new Model_NhanVien(txt_maNhanVien.getText(), txt_tenNhanVien.getText(), Integer.parseInt(txt_tuoiNhanVien.getText()), rdo_nam.isSelected() ? true : false, txt_diaChi.getText(), rdo_NhanVien.isSelected() ? false : true, txt_taiKhoan.getText(), txt_matKhau.getText(), TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY, rdo_DangHoatDong.isSelected() ? true : false);
        return nv;
    }

    public boolean readform() {
        for (int j = 0; j < tbl_bangNhanVien.getRowCount(); j++) {
            if (txt_maNhanVien.getText().equals(tbl_bangNhanVien.getValueAt(j, 1))) {
                JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại.");
                txt_maNhanVien.requestFocus();
                return false;
            }
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
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_bangNhanVien = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txt_maNhanVien = new javax.swing.JTextField();
        btn_timKiem = new javax.swing.JButton();
        txt_tenNhanVien = new javax.swing.JTextField();
        txt_tuoiNhanVien = new javax.swing.JTextField();
        txt_diaChi = new javax.swing.JTextField();
        btn_themNhanVien = new javax.swing.JButton();
        btn_suaThongTin = new javax.swing.JButton();
        rdo_nam = new javax.swing.JRadioButton();
        rdo_nu = new javax.swing.JRadioButton();
        rdo_DangHoatDong = new javax.swing.JRadioButton();
        rdo_khongHoatDong = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        rdo_NhanVien = new javax.swing.JRadioButton();
        rdo_QuanLy = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        txt_taiKhoan = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_matKhau = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Danh Sách nhân viên");

        tbl_bangNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "IDNhanVien", "Tên", "Tuổi", "Giới Tính", "Địa Chỉ", "Vai Trò", "Tên Đăng Nhập", "Mật Khẩu", "Ngày Tạo", "Ngày Sửa", "Trạng Thái"
            }
        ));
        tbl_bangNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_bangNhanVienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_bangNhanVien);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 875, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setText("Tên:");

        jLabel3.setText("Tuổi:");

        jLabel4.setText("Giới tính:");

        jLabel5.setText("Địa chỉ:");

        jLabel6.setText("Trạng thái:");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin nhân viên"));

        jLabel7.setText("Mã Nhân viên:");

        btn_timKiem.setText("Tìm kiếm");
        btn_timKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_maNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btn_timKiem)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_maNhanVien)
                    .addComponent(btn_timKiem))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        txt_tenNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tenNhanVienActionPerformed(evt);
            }
        });

        txt_diaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_diaChiActionPerformed(evt);
            }
        });

        btn_themNhanVien.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btn_themNhanVien.setText("Thêm");
        btn_themNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themNhanVienActionPerformed(evt);
            }
        });

        btn_suaThongTin.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btn_suaThongTin.setText("Sửa");
        btn_suaThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaThongTinActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdo_nam);
        rdo_nam.setText("Nam");

        buttonGroup1.add(rdo_nu);
        rdo_nu.setSelected(true);
        rdo_nu.setText("Nữ");

        buttonGroup2.add(rdo_DangHoatDong);
        rdo_DangHoatDong.setSelected(true);
        rdo_DangHoatDong.setText("Đang Hoạt Động");

        buttonGroup2.add(rdo_khongHoatDong);
        rdo_khongHoatDong.setText("Không Hoạt Động");
        rdo_khongHoatDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_khongHoatDongActionPerformed(evt);
            }
        });

        jLabel9.setText("Vai Trò:");

        buttonGroup3.add(rdo_NhanVien);
        rdo_NhanVien.setSelected(true);
        rdo_NhanVien.setText("Nhân Viên");

        buttonGroup3.add(rdo_QuanLy);
        rdo_QuanLy.setText("Quản Lý");

        jLabel10.setText("Tên Đăng Nhập :");

        txt_taiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_taiKhoanActionPerformed(evt);
            }
        });

        jLabel11.setText("Mật Khẩu :");

        txt_matKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_matKhauActionPerformed(evt);
            }
        });

        jButton1.setText("RESET");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                                .addComponent(txt_tuoiNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(25, 25, 25)
                                .addComponent(rdo_nam)
                                .addGap(18, 18, 18)
                                .addComponent(rdo_nu))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdo_DangHoatDong)
                                    .addComponent(rdo_NhanVien))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdo_khongHoatDong)
                                    .addComponent(rdo_QuanLy)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(72, 72, 72)
                                .addComponent(txt_tenNhanVien))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(55, 55, 55)
                                .addComponent(txt_diaChi))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_matKhau)
                                    .addComponent(txt_taiKhoan))))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_themNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_suaThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btn_themNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_suaThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_tenNhanVien))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_tuoiNhanVien))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(rdo_nam)
                            .addComponent(rdo_nu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_diaChi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txt_taiKhoan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txt_matKhau))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(rdo_NhanVien)
                            .addComponent(rdo_QuanLy))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(rdo_DangHoatDong)
                            .addComponent(rdo_khongHoatDong))))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_suaThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaThongTinActionPerformed
        // TODO add your handling code here:
        i = tbl_bangNhanVien.getSelectedRow();
        if (i < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng cần sửa");
        } else {
            if (this.readFrom() != null) {
                int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn chọn không!");
                if (chon == 0) {
                    String maNV = (tbl_bangNhanVien.getValueAt(i, 1).toString());
                    int a = repo.updateNV(this.readFrom(), maNV);
                    if (a == 1) {
                        JOptionPane.showMessageDialog(this, "sửa thanh cong");
                    }
                    this.fillTable(repo.getAll());
                }
            }
        }
    }//GEN-LAST:event_btn_suaThongTinActionPerformed

    private void txt_diaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_diaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_diaChiActionPerformed

    private void txt_tenNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tenNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tenNhanVienActionPerformed

    private void rdo_khongHoatDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_khongHoatDongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_khongHoatDongActionPerformed

    private void tbl_bangNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bangNhanVienMouseClicked
        // TODO add your handling code here:
        i = tbl_bangNhanVien.getSelectedRow();
        this.showData(i);
    }//GEN-LAST:event_tbl_bangNhanVienMouseClicked

    private void btn_timKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timKiemActionPerformed
        // TODO add your handling code here:
        String ten = txt_tenNhanVien.getText();
        if (repo.timkiem(ten).isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu");
        } else {
            JOptionPane.showMessageDialog(this, "Đã tìm thấy dữ liệu");
            this.fillTable(repo.timkiem(ten));
        }

    }//GEN-LAST:event_btn_timKiemActionPerformed

    private void txt_taiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_taiKhoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_taiKhoanActionPerformed

    private void txt_matKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_matKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_matKhauActionPerformed

    private void btn_themNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themNhanVienActionPerformed
        // TODO add your handling code here:
        if (this.readFrom() != null) {// có đối mới từ form để thêm
            if(readform()){
                int chon = JOptionPane.showConfirmDialog(this, "bạn có muốn thêm k?");
                if (chon == 0) {// có thêm
                    repo.addNhanVien(this.readFrom());// dl them vào data
                    JOptionPane.showMessageDialog(this, "thêm thanh công");
                    // them vào bảng
                    this.fillTable(repo.getAll());
                } else {// không thêm
                    JOptionPane.showMessageDialog(this, "ban k thêm dl");
                }
            }
            
        }
    }//GEN-LAST:event_btn_themNhanVienActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        txt_maNhanVien.setText("");
        txt_tenNhanVien.setText("");
        txt_tuoiNhanVien.setText("");
        txt_taiKhoan.setText("");
        txt_matKhau.setText("");
        txt_diaChi.setText("");
          this.fillTable(repo.getAll());
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_suaThongTin;
    private javax.swing.JButton btn_themNhanVien;
    private javax.swing.JButton btn_timKiem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdo_DangHoatDong;
    private javax.swing.JRadioButton rdo_NhanVien;
    private javax.swing.JRadioButton rdo_QuanLy;
    private javax.swing.JRadioButton rdo_khongHoatDong;
    private javax.swing.JRadioButton rdo_nam;
    private javax.swing.JRadioButton rdo_nu;
    private javax.swing.JTable tbl_bangNhanVien;
    private javax.swing.JTextField txt_diaChi;
    private javax.swing.JTextField txt_maNhanVien;
    private javax.swing.JTextField txt_matKhau;
    private javax.swing.JTextField txt_taiKhoan;
    private javax.swing.JTextField txt_tenNhanVien;
    private javax.swing.JTextField txt_tuoiNhanVien;
    // End of variables declaration//GEN-END:variables
}
