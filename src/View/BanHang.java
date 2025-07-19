/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Model.Model_HoaDon;
import Model.Model_HoaDonChiTiet;
import Model.Model_SPCT;
import Model.Model_khachHang;
import Repository.Global;
import Repository.Repository_BanHang;
import Repository.Respository_SPCT;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import Repository.Responsitory_khachHang;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author PonYanki
 */
public class BanHang extends javax.swing.JPanel {

    DefaultTableModel mol = new DefaultTableModel();
    Respository_SPCT repo_spct;
    Repository_BanHang repo_bh;
    Responsitory_khachHang repo_kh;
    int index1 = -1;
    int index2;
    int index3;

    /**
     * Creates new form SanPham
     */
    public BanHang() throws SQLException {
        initComponents();
        repo_spct = new Respository_SPCT();
        repo_bh = new Repository_BanHang();
        repo_kh = new Responsitory_khachHang();
        filltable_SPCT(repo_spct.GetAll_SPCT());
        filltable_HD(repo_bh.getAll_HD());
        reloadTienKhachDua();
        reloadSDT();
        reloadTienKhachCanTra();
    }

    public void reloadTienKhachDua() {
        txt_tienKhachDua.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateLabel();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateLabel();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateLabel();
            }

            private void updateLabel() {
                if (check2()) {
                    String i = String.valueOf((Integer.parseInt(txt_tienKhachDua.getText()) - Integer.parseInt(lblKhachCanTra.getText())));
                    lblTienThua.setText(i);
                }
            }
        });
    }

    public void reloadTienKhachCanTra() {
        // Simplified - no voucher logic needed
        if (tbl_danhSachHoaDon.getRowCount() == 0) {
            lblTongTien.setText("0");
            lblKhachCanTra.setText("0");
        } else {
            try {
                TongTien();
            } catch (SQLException ex) {
                Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void reloadSDT() {
        txt_soDienThoai.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    updateLabel();
                } catch (SQLException ex) {
                    Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    updateLabel();
                } catch (SQLException ex) {
                    Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    updateLabel();
                } catch (SQLException ex) {
                    Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void updateLabel() throws SQLException {
                String i = repo_kh.TimTen_TheoSDT(txt_soDienThoai.getText());
                txt_tenKhachHang.setText(i);
            }
        });
    }

    public boolean check2() {
        if (txt_tienKhachDua.getText().length() > 9) {
            JOptionPane.showMessageDialog(lblTongTien, "Vui lòng nhập giá tiền dưới 10 số.");
            txt_tienKhachDua.requestFocus();
            return false;
        } else {
            try {
                int i = Integer.parseInt(txt_tienKhachDua.getText());
                return true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(lblTongTien, "Vui lòng nhập tiền khách đưa là số.");
                txt_tienKhachDua.requestFocus();
                return false;
            }
        }
    }

    public void filltable_SPCT(ArrayList<Model_SPCT> spct) throws SQLException {
        mol = (DefaultTableModel) tbl_spct.getModel();
        mol.setRowCount(0);
        for (Model_SPCT x : spct) {
            if (x.getTrangThai() == 1) {
                mol.addRow(x.toDataRow1());
            }
        }
        tbl_spct.setModel(mol);
    }

    public void filltable_HD(ArrayList<Model_HoaDon> hd) {
        mol = (DefaultTableModel) tbl_danhSachHoaDon.getModel();
        mol.setRowCount(0);
        for (Model_HoaDon x : hd) {
            if (x.getTrangThai() == 0) {
                mol.addRow(x.ToDataRow());
            }
        }
        tbl_danhSachHoaDon.setModel(mol);
    }

    public String MaKH() {
        ArrayList<Model_khachHang> lst = repo_kh.getAll();
        int i = lst.size() - 1;
        String a = lst.get(i).getIDKhachHang();
        String b = a.substring(2);
        int c = Integer.parseInt(b);
        int d = c + 1;
        String e = null;
        if (c < 100) {
            e = "KH" + "0" + String.valueOf(d);
        } else {
            e = "KH" + String.valueOf(d);
        }
        return e;
    }

    public String MaHD() throws SQLException {
        ArrayList<Model_HoaDon> lst = repo_bh.getAll_HD();
        int i = lst.size() - 1;
        String a = lst.get(i).getMaHD();
        String b = a.substring(2);
        int c = Integer.parseInt(b);
        int d = c + 1;
        String e = null;
        if (c < 100) {
            e = "HD" + "0" + String.valueOf(d);
        } else {
            e = "HD" + String.valueOf(d);
        }
        return e;
    }

    public String GetMaKH() {
        ArrayList<Model_khachHang> lst = new ArrayList<>();
        lst = repo_kh.getAll();
        int i = lst.size() - 1;
        String a = lst.get(i).getIDKhachHang();
        return a;
    }

    public void filltable_HDCT(ArrayList<Model_HoaDonChiTiet> hdct) throws SQLException {
        mol = (DefaultTableModel) tbl_gioHang.getModel();
        mol.setRowCount(0);
        for (Model_HoaDonChiTiet x : hdct) {
            mol.addRow(x.ToDataRow());
        }
        tbl_gioHang.setModel(mol);
    }

    void showData(int index1) {
        String ten = tbl_danhSachHoaDon.getValueAt(index1, 3).toString();
        JOptionPane.showMessageDialog(this, "Bạn vừa chọn hóa đơn có mã là: " + tbl_danhSachHoaDon.getValueAt(index1, 1));
        txt_tenKhachHang.setText(ten);
        txt_soDienThoai.setText((String) tbl_danhSachHoaDon.getValueAt(index1, 4));
    }

    public void TongTien() throws SQLException {
        int Tong = 0;
        if (check(index1)) {
            for (Model_HoaDonChiTiet x : repo_bh.GetAllHoaDonChiTiet((String) tbl_danhSachHoaDon.getValueAt(index1, 1))) {
                Tong = Tong + (x.getSoLuong() * x.getDonGia());
            }
            lblTongTien.setText(String.valueOf(Tong));
            lblKhachCanTra.setText(String.valueOf(Tong)); // No voucher discount, so total = amount to pay
        }
    }

    public boolean readform() throws SQLException {
        if (txt_tenKhachHang.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khách hàng.");
            txt_tenKhachHang.requestFocus();
            return false;
        } else if (txt_tenKhachHang.getText().trim().length() > 50) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khách hàng đúng định dạng(50 ký tự).");
            txt_tenKhachHang.requestFocus();
            return false;
        } else if (txt_soDienThoai.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại khách hàng.");
            txt_soDienThoai.requestFocus();
            return false;
        }
          else if (!txt_soDienThoai.getText().trim().matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại chỉ được chứa số tự nhiên.");
            txt_soDienThoai.requestFocus();
            return false;
        } else if (txt_soDienThoai.getText().trim().length() > 14) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khách hàng đúng định dạng(10->14 ký tự).");
            txt_soDienThoai.requestFocus();
            return false;
        }
        return true;
    }

    public int check_HDCT() throws SQLException {
        for (Model_HoaDonChiTiet x : repo_bh.GetAllHoaDonChiTiet((String) tbl_danhSachHoaDon.getValueAt(index1, 1))) {
            if (tbl_spct.getValueAt(index3, 0).equals(x.getIDSPCT())) {
                return x.getSTT();
            }
        }
        return 0;
    }

    public boolean readform1() {
        if (txt_tienKhachDua.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số tiền khách đưa.");
            txt_tienKhachDua.requestFocus();
            return false;
        }
        return true;
    }

    public void TraDuLieu(ArrayList<Model_HoaDonChiTiet> hdct) throws SQLException {
        for (Model_HoaDonChiTiet x : hdct) {
            for (Model_SPCT y : repo_spct.GetAll_SPCT_tim(x.getIDSPCT())) {
                int SoLuong = x.getSoLuong() + y.getSoLuong();
                repo_spct.update_SoLuong_SPCT(SoLuong, 1, x.getIDSPCT());
            }
        }
    }

    public boolean check(int i) {
        if (i == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dự liệu trên bảng.");
            return false;
        }
        return true;
    }

    public boolean checkNewKH() {
        for (Model_khachHang x : repo_kh.getAll()) {
            if (txt_soDienThoai.getText().equals(x.getSDT())) {
                return false;
            }
        }
        return true;
    }

    public String checkKH() {
        for (Model_khachHang x : repo_kh.getAll()) {
            if (txt_soDienThoai.getText().equals(x.getSDT())) {
                return x.getIDKhachHang();
            }
        }
        return null;
    }

    public void set() {
        int rowCount = tbl_danhSachHoaDon.getRowCount();
        if (rowCount > 0) {
            tbl_danhSachHoaDon.setRowSelectionInterval(rowCount - 1, rowCount - 1);
            tbl_danhSachHoaDon.scrollRectToVisible(tbl_danhSachHoaDon.getCellRect(rowCount - 1, 0, true));
        }
    }

    public boolean checkGH() {
        if (tbl_gioHang.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Giỏ hàng của bạn đang trống");
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_danhSachHoaDon = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_tenKhachHang = new javax.swing.JTextField();
        txt_soDienThoai = new javax.swing.JTextField();
        btn_taoHoaDon = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        txt_tienKhachDua = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lblKhachCanTra = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btn_thanhToan = new javax.swing.JButton();
        btn_huyHoaDon = new javax.swing.JButton();
        lblTienThua = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_gioHang = new javax.swing.JTable();
        btn_xoaSanPhamGioHang = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_spct = new javax.swing.JTable();

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        tbl_danhSachHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hóa đơn", "Tên nhân viên", "Tên khách hàng", "SDT", "Mã voucher", "Tổng tiền", "Trạng thái"
            }
        ));
        tbl_danhSachHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_danhSachHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_danhSachHoaDon);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tạo Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        jLabel4.setText("Tên khách hàng:");

        jLabel5.setText("Số điện thoại:");

        btn_taoHoaDon.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btn_taoHoaDon.setText("Tạo Hóa Đơn");
        btn_taoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_taoHoaDonActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setText("Tiền Thừa:");

        jLabel11.setText("Tiền Khách Đưa:");

        jLabel7.setText("Khách cần trả:");

        jLabel6.setText("Tổng tiền hàng:");

        lblTongTien.setText("0");

        lblKhachCanTra.setText("0");

        jSeparator2.setBackground(new java.awt.Color(255, 255, 255));

        btn_thanhToan.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        btn_thanhToan.setText("Thanh toán");
        btn_thanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thanhToanActionPerformed(evt);
            }
        });

        btn_huyHoaDon.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btn_huyHoaDon.setText("Hủy");
        btn_huyHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_huyHoaDonActionPerformed(evt);
            }
        });

        lblTienThua.setText("0");

        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_thanhToan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btn_taoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_huyHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblKhachCanTra))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblTongTien)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblTienThua)
                                    .addComponent(txt_tienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_soDienThoai)
                                    .addComponent(txt_tenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_tenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_soDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTongTien)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblKhachCanTra, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_tienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lblTienThua))
                .addGap(48, 48, 48)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_thanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_taoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_huyHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hóa Đơn", jPanel4);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        tbl_gioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên Sản Phẩm", "Giới tính", "Loại dây", "Chất liệu", "Đế giày", "Kích thước", "Màu sắc", "Số Lượng", "Đơn Giá ", "Thành Tiền"
            }
        ));
        jScrollPane2.setViewportView(tbl_gioHang);

        btn_xoaSanPhamGioHang.setText("Xóa");
        btn_xoaSanPhamGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaSanPhamGioHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btn_xoaSanPhamGioHang)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_xoaSanPhamGioHang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        tbl_spct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Giới tính", "Đơn giá", "Giảm giá", "Số lượng", "Loại dây", "Chất liệu", "Đế giày", "Kích thước", "Màu sắc"
            }
        ));
        tbl_spct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_spctMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_spct);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_xoaSanPhamGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaSanPhamGioHangActionPerformed
        index2 = tbl_gioHang.getSelectedRow();
        if (check(index2)) {
            int i = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không?");
            if (i == 0) {
                try {
                    TraDuLieu(repo_bh.GetAllHoaDonChiTiet_tim((String) tbl_danhSachHoaDon.getValueAt(index1, 1), (String) tbl_gioHang.getValueAt(index2, 1)));
                    repo_bh.deleteHoaDonChiTiet((int) tbl_gioHang.getValueAt(index2, 0));
                    filltable_SPCT(repo_spct.GetAll_SPCT());
                    filltable_HDCT(repo_bh.GetAllHoaDonChiTiet((String) tbl_danhSachHoaDon.getValueAt(index1, 1)));
                    TongTien();
                    if (!checkGH()) {
                        lblTongTien.setText("0");
                        lblKhachCanTra.setText("0");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }//GEN-LAST:event_btn_xoaSanPhamGioHangActionPerformed

    private void tbl_spctMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_spctMouseClicked
        index3 = tbl_spct.getSelectedRow();
        if (check(index3)) {
            if (check(index1)) {
                int TrangThai = 1;
                int i = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm sản phẩm: " + tbl_spct.getValueAt(index3, 1) + " không?");
                if (i == 0) {
                    int SoLuong = Integer.parseInt(JOptionPane.showInputDialog("Vui lòng nhập số lượng: "));
                    if (SoLuong <= 0) {
                        JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng >0.");
                    } else if (SoLuong > ((int) tbl_spct.getValueAt(index3, 5))) {
                        JOptionPane.showMessageDialog(this, "Số lượng hàng tồn không đủ.");
                    } else {
                        if ((int) tbl_spct.getValueAt(index3, 5) - SoLuong == 0) {
                            TrangThai = 0;
                        }
                        try {
                            if (check_HDCT() == 0) {
                                try {
                                    repo_spct.update_SoLuong_SPCT((int) tbl_spct.getValueAt(index3, 5) - SoLuong, TrangThai, (String) tbl_spct.getValueAt(index3, 0));
                                } catch (SQLException ex) {
                                    Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                Model_HoaDonChiTiet hdct = new Model_HoaDonChiTiet((String) tbl_spct.getValueAt(index3, 0), (String) tbl_danhSachHoaDon.getValueAt(index1, 1), SoLuong, (int) ((int) tbl_spct.getValueAt(index3, 3) - (int) tbl_spct.getValueAt(index3, 4)));
                                try {
                                    repo_bh.addHoaDonChiTiet(hdct);
                                    TongTien();
                                    filltable_HDCT(repo_bh.GetAllHoaDonChiTiet((String) tbl_danhSachHoaDon.getValueAt(index1, 1)));
                                    filltable_SPCT(repo_spct.GetAll_SPCT());
                                } catch (SQLException ex) {
                                    Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                try {
                                    repo_spct.update_SoLuong_SPCT((int) tbl_spct.getValueAt(index3, 5) - SoLuong, TrangThai, (String) tbl_spct.getValueAt(index3, 0));
                                } catch (SQLException ex) {
                                    Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                try {
                                    repo_bh.updateHoaDonChiTiet(SoLuong, check_HDCT());
                                    TongTien();
                                    filltable_HDCT(repo_bh.GetAllHoaDonChiTiet((String) tbl_danhSachHoaDon.getValueAt(index1, 1)));
                                    filltable_SPCT(repo_spct.GetAll_SPCT());
                                } catch (SQLException ex) {
                                    Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_tbl_spctMouseClicked

    private void btn_thanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thanhToanActionPerformed
        if (check(index1) && readform1() && checkGH()) {
            if (Integer.parseInt(lblTienThua.getText()) >= 0) {
                int i = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thanh toán không?");
                if (i == 0) {
                    try {
                        repo_bh.updateHD("", 1, Integer.parseInt(lblKhachCanTra.getText()), (String) tbl_danhSachHoaDon.getValueAt(index1, 1));
                        JOptionPane.showMessageDialog(this, "Thanh toán hóa đơn thành công.");
                        filltable_HD(repo_bh.getAll_HD());
                        mol = (DefaultTableModel) tbl_gioHang.getModel();
                        mol.setRowCount(0);
                        tbl_gioHang.setModel(mol);
                    } catch (SQLException ex) {
                        Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Số tiền khách trả không đủ.");
            }
        }

    }//GEN-LAST:event_btn_thanhToanActionPerformed

    private void btn_huyHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huyHoaDonActionPerformed
        if (check(index1)) {
            int i = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hủy không?");
            if (i == 0) {
                try {
                    repo_bh.updateHD("", 2, Integer.parseInt(lblKhachCanTra.getText()), (String) tbl_danhSachHoaDon.getValueAt(index1, 1));
                    TraDuLieu(repo_bh.GetAllHoaDonChiTiet((String) tbl_danhSachHoaDon.getValueAt(index1, 1)));
                    JOptionPane.showMessageDialog(this, "Hủy hóa đơn thành công.");
                    filltable_HD(repo_bh.getAll_HD());
                    filltable_SPCT(repo_spct.GetAll_SPCT());
                    mol = (DefaultTableModel) tbl_gioHang.getModel();
                    mol.setRowCount(0);
                    tbl_gioHang.setModel(mol);
                } catch (SQLException ex) {
                    Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btn_huyHoaDonActionPerformed

    private void tbl_danhSachHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_danhSachHoaDonMouseClicked
        index1 = tbl_danhSachHoaDon.getSelectedRow();
        if (check(index1)) {
            try {
                filltable_HDCT(repo_bh.GetAllHoaDonChiTiet((String) tbl_danhSachHoaDon.getValueAt(index1, 1)));
                TongTien();
            } catch (SQLException ex) {
                Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.showData(index1);
        }
    }//GEN-LAST:event_tbl_danhSachHoaDonMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        txt_soDienThoai.setText("");
        txt_tenKhachHang.setText("");
        lblTongTien.setText("0");
        lblKhachCanTra.setText("0");
        lblTienThua.setText("0");
        txt_tienKhachDua.setText("");
        try {
            filltable_SPCT(repo_spct.GetAll_SPCT());
        } catch (SQLException ex) {
            Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            filltable_HD(repo_bh.getAll_HD());
        } catch (SQLException ex) {
            Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        mol = (DefaultTableModel) tbl_gioHang.getModel();
        mol.setRowCount(0);
        tbl_gioHang.setModel(mol);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_taoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_taoHoaDonActionPerformed
        try {
            if (readform()) {
                if (checkNewKH()) {
                    lblTongTien.setText("0");
                    lblKhachCanTra.setText("0");
                    Model_khachHang ml = new Model_khachHang(MaKH(), txt_tenKhachHang.getText(), txt_soDienThoai.getText());
                    repo_kh.add_khachHang(ml);
                    Model_HoaDon hd = new Model_HoaDon(MaHD(), Global.employeeId, GetMaKH(), "", Integer.parseInt(lblTongTien.getText()), 0);
                    try {
                        mol = (DefaultTableModel) tbl_gioHang.getModel();
                        mol.setRowCount(0);
                        repo_bh.addHD(hd);
                        filltable_HD(repo_bh.getAll_HD());
                        set();
                    } catch (SQLException ex) {
                        Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    lblTongTien.setText("0");
                    lblKhachCanTra.setText("0");
                    try {
                        Model_HoaDon hd = new Model_HoaDon(MaHD(), Global.employeeId, checkKH(), "", Integer.parseInt(lblTongTien.getText()), 0);
                        mol = (DefaultTableModel) tbl_gioHang.getModel();
                        mol.setRowCount(0);
                        tbl_gioHang.setModel(mol);
                        repo_bh.addHD(hd);
                        filltable_HD(repo_bh.getAll_HD());
                        set();
                    } catch (SQLException ex) {
                        Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_taoHoaDonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_huyHoaDon;
    private javax.swing.JButton btn_taoHoaDon;
    private javax.swing.JButton btn_thanhToan;
    private javax.swing.JButton btn_xoaSanPhamGioHang;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblKhachCanTra;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JTable tbl_danhSachHoaDon;
    private javax.swing.JTable tbl_gioHang;
    private javax.swing.JTable tbl_spct;
    private javax.swing.JTextField txt_soDienThoai;
    private javax.swing.JTextField txt_tenKhachHang;
    private javax.swing.JTextField txt_tienKhachDua;
    // End of variables declaration//GEN-END:variables
}
