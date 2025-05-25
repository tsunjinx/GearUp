package View;

import Model.Model_DeGiay;
import Model.Model_SPCT;
import Model.Model_SanPham;
import Model.Model_chatLieu;
import Model.Model_kichThuoc;
import Model.Model_loaiDay;
import Model.Model_mauSac;
import Repository.Responsitory_SanPham;
import Repository.Responsitory_ThuocTinhSanPham;
import Repository.Respository_SPCT;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SanPham extends javax.swing.JPanel {

    public Respository_SPCT repo_spct;
    public Responsitory_ThuocTinhSanPham repo_tt;
    public Responsitory_SanPham repo;
    public DefaultTableModel mol = new DefaultTableModel();

    /**
     * Creates new form SanPham
     */
    public SanPham() throws SQLException {
        initComponents();
        txt_tongSP.setEditable(false);
        repo = new Responsitory_SanPham();
        repo_tt = new Responsitory_ThuocTinhSanPham();
        repo_spct = new Respository_SPCT();
        fillTable(repo.getAll());
        model();
        fillTable_tt(repo_tt.getAll_LoaiDay(), repo_tt.getAll_mauSac(), repo_tt.getAll_KichThuoc(), repo_tt.getAll_ChatLieu(), repo_tt.getAll_DeGiay());
        filltable_SPCT(repo_spct.GetAll_SPCT());
        int dem = tbl_thongTinSanPham.getRowCount();
        txt_tongSP.setText(String.valueOf(dem));
    }

    public void fillTable(ArrayList<Model_SanPham> list) {
        mol = (DefaultTableModel) tbl_bang.getModel();
        mol.setRowCount(0);
        for (Model_SanPham x : list) {
            mol.addRow(x.toDataRow());
        }
        tbl_bang.setModel(mol);
    }

    public void showData(int index) {
        String ma;
        String ten;
        int trangthai;
        ma = tbl_bang.getValueAt(index, 1).toString();
        ten = tbl_bang.getValueAt(index, 2).toString();
        trangthai = Integer.parseInt(tbl_bang.getValueAt(index, 5).toString());
        txt_maSP.setText(ma);
        txt_tenSP.setText(ten);
        if (trangthai == 1) {
            rdo_hd.isSelected();
        } else {
            rdo_khd.isSelected();
        }
    }

    private Model_SanPham readForm1() {
        String id;
        String ten;
        int tinhtrang;
        id = txt_maSP.getText();
        ten = txt_tenSP.getText();
        if (rdo_hd.isSelected()) {
            tinhtrang = 1;
        } else {
            tinhtrang = 0;
        }
        if (ten.isEmpty() || ten.equals(" ")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên");
            return null;
        } else if (id.length() != 5) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã đúng định dạng(5 kí tự).");
            return null;
        }
        if (id.isEmpty() || id.equals(" ")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Mã sản phẩm");
            return null;
        }
        for (int i = 0; i < tbl_bang.getRowCount(); i++) {
            Object existingValue = tbl_bang.getValueAt(i, 1);

            if (existingValue != null && id.equalsIgnoreCase(existingValue.toString())) {
                JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại!");
                return null;
            }
        }
        return new Model_SanPham(id, ten, tinhtrang);
    }

    private Model_SanPham readForm11() {
        String id;
        String ten;
        int tinhtrang;
        id = txt_maSP.getText();
        ten = txt_tenSP.getText();
        if (rdo_hd.isSelected()) {
            tinhtrang = 1;
        } else {
            tinhtrang = 0;
        }
        if (ten.isEmpty() || ten.equals(" ")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên");
            return null;
        }
        if (id.isEmpty() || id.equals(" ")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Mã sản phẩm");
            return null;
        }
        return new Model_SanPham(id, ten, tinhtrang);
    }

    public void model() throws SQLException {
        txt_tenSanPham.removeAllItems();
        cbo_chatLieu.removeAllItems();
        cbo_loaiDay.removeAllItems();
        cbo_mauSac.removeAllItems();
        cbo_kichThuoc.removeAllItems();
        cbo_deGiay.removeAllItems();
        for (Model_SanPham x : repo.getAll()) {
            if (x.getTrangThai() == 1) {
                txt_tenSanPham.addItem(x.getTenSanPham());
            }
        }
        for (Model_loaiDay x : repo_tt.getAll_LoaiDay()) {
            if (x.getTrangthai() == 1) {
                cbo_loaiDay.addItem(x.getLoaiGiay());
            }
        }
        for (Model_mauSac x : repo_tt.getAll_mauSac()) {
            if (x.getTrangThai() == 1) {
                cbo_mauSac.addItem(x.getTenMauSac());
            }
        }
        for (Model_kichThuoc x : repo_tt.getAll_KichThuoc()) {
            if (x.getTrangThai() == 1) {
                cbo_kichThuoc.addItem(x.getKichThuoc());
            }
        }
        for (Model_chatLieu x : repo_tt.getAll_ChatLieu()) {
            if (x.getTrangThai() == 1) {
                cbo_chatLieu.addItem(x.getTenChatLieu());
            }
        }
        for (Model_DeGiay x : repo_tt.getAll_DeGiay()) {
            if (x.getTrangThai() == 1) {
                cbo_deGiay.addItem(x.getTenDeGiay());
            }
        }
    }

    private Model_SanPham readForm() {
        String id;
        String ten;
        int tinhtrang;
        id = txt_maSP.getText();
        ten = txt_tenSP.getText();
        if (rdo_hd.isSelected()) {
            tinhtrang = 1;
        } else {
            tinhtrang = 0;
        }
        return new Model_SanPham(id, ten, tinhtrang);
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        rdo_hd = new javax.swing.JRadioButton();
        rdo_khd = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_bang = new javax.swing.JTable();
        txt_maSP = new javax.swing.JTextField();
        txt_tenSP = new javax.swing.JTextField();
        txt_tim = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnSearchDHD2 = new javax.swing.JButton();
        btnSearchKHD2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_maSanPham = new javax.swing.JTextField();
        txt_donGia = new javax.swing.JTextField();
        txt_soLuong = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbo_mauSac = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbo_kichThuoc = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cbo_chatLieu = new javax.swing.JComboBox<>();
        cbo_loaiSanPham = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        cbo_deGiay = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        cbo_loaiDay = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        cbo_trangThaiSanPhamCHiTiet = new javax.swing.JComboBox<>();
        txt_tenSanPham = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_thongTinSanPham = new javax.swing.JTable();
        btn_themThongTinSanPham = new javax.swing.JButton();
        btn_suaThongTinSanPham = new javax.swing.JButton();
        btn_resetThongTinSanPham = new javax.swing.JButton();
        btnSearchKHD = new javax.swing.JButton();
        btnSearchDHD = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        txt_tongSP = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_tenThuocTinh = new javax.swing.JTextField();
        rdo_loaiDay = new javax.swing.JRadioButton();
        rdo_mauSac = new javax.swing.JRadioButton();
        rdo_kichThuoc = new javax.swing.JRadioButton();
        rdo_chatLieu = new javax.swing.JRadioButton();
        rdo_deGiay = new javax.swing.JRadioButton();
        btn_themThuocTinhSanPham = new javax.swing.JButton();
        btn_suaThuocTinhSanPham = new javax.swing.JButton();
        btn_resetThuocTinhSanPham = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        cbo_trangThai = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        txtMaTT = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_thuocTinhSanPham = new javax.swing.JTable();
        btnSearchDHD1 = new javax.swing.JButton();
        btnSearchKHD1 = new javax.swing.JButton();

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Mã sản phẩm");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Tên Sản phẩm");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Trạng Thái");

        buttonGroup1.add(rdo_hd);
        rdo_hd.setText("Đang hoạt động");

        buttonGroup1.add(rdo_khd);
        rdo_khd.setText("Không hoạt động");

        tbl_bang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Ngày tạo", "Ngày sửa", "Tình trạng"
            }
        ));
        tbl_bang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_bangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_bang);

        txt_tim.setText("Nhập mã sản phẩm");
        txt_tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timActionPerformed(evt);
            }
        });

        jButton4.setText("Tìm Kiếm");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_maSP)
                            .addComponent(txt_tenSP, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rdo_khd)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdo_hd)
                                .addGap(6, 6, 6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_tim, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jLabel20)
                            .addComponent(rdo_hd)
                            .addComponent(txt_maSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(txt_tenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(rdo_khd))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_tim, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        btnSearchDHD2.setText("Đang hoạt động");
        btnSearchDHD2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchDHD2ActionPerformed(evt);
            }
        });

        btnSearchKHD2.setText("Không hoạt động");
        btnSearchKHD2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchKHD2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(322, 322, 322)
                        .addComponent(btnSearchDHD2)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearchKHD2))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSearchDHD2)
                        .addComponent(btnSearchKHD2)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản Phẩm", jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Quản lý sản phẩm");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel1.setText("Mã sản phẩm");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel3.setText("Tên sản phẩm");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel4.setText("Loại sản phẩm");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel5.setText("Đơn giá");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel6.setText("Số lượng");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel7.setText("Màu sắc");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel8.setText("Kích thước");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel9.setText("Chất liệu");

        cbo_loaiSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel14.setText("Đế Giày");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel15.setText("Loại dây");

        jLabel17.setText("Trạng Thái");

        cbo_trangThaiSanPhamCHiTiet.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt động", "Không hoạt động" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(txt_soLuong))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(txt_donGia))
                    .addComponent(jLabel2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_maSanPham, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbo_loaiSanPham, 0, 341, Short.MAX_VALUE)
                            .addComponent(txt_tenSanPham, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(71, 71, 71)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbo_mauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbo_kichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbo_chatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbo_loaiDay, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbo_deGiay, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbo_trangThaiSanPhamCHiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(162, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(cbo_loaiDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbo_mauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cbo_kichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cbo_chatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(cbo_deGiay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(cbo_trangThaiSanPhamCHiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_maSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_tenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbo_loaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_donGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Thông tin sản phẩm");

        tbl_thongTinSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Đơn giá", "Số lượng", "Loại dây", "Chất liệu", "Đế giày", "Kích thước", "Màu sắc", "Trạng Thái"
            }
        ));
        tbl_thongTinSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_thongTinSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_thongTinSanPham);

        btn_themThongTinSanPham.setText("Thêm");
        btn_themThongTinSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themThongTinSanPhamActionPerformed(evt);
            }
        });

        btn_suaThongTinSanPham.setText("Sửa");
        btn_suaThongTinSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaThongTinSanPhamActionPerformed(evt);
            }
        });

        btn_resetThongTinSanPham.setText("Reset");
        btn_resetThongTinSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetThongTinSanPhamActionPerformed(evt);
            }
        });

        btnSearchKHD.setText("Không hoạt động");
        btnSearchKHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchKHDActionPerformed(evt);
            }
        });

        btnSearchDHD.setText("Đang hoạt động");
        btnSearchDHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchDHDActionPerformed(evt);
            }
        });

        jLabel22.setText("Tổng sản phẩm ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btn_themThongTinSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btn_suaThongTinSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_resetThongTinSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 433, Short.MAX_VALUE)
                        .addComponent(btnSearchDHD)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearchKHD)
                        .addGap(38, 38, 38))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1078, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_tongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(125, 125, 125)))))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel22)
                    .addComponent(txt_tongSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearchKHD)
                    .addComponent(btnSearchDHD)
                    .addComponent(btn_resetThongTinSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_suaThongTinSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_themThongTinSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(136, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thông tin chi tiết", jPanel1);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Thuộc tính sản phẩm");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel12.setText("Tên thuộc tính");

        buttonGroup1.add(rdo_loaiDay);
        rdo_loaiDay.setSelected(true);
        rdo_loaiDay.setText("Loại dây");
        rdo_loaiDay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdo_loaiDayMouseClicked(evt);
            }
        });

        buttonGroup1.add(rdo_mauSac);
        rdo_mauSac.setText("Màu sắc");
        rdo_mauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdo_mauSacMouseClicked(evt);
            }
        });

        buttonGroup1.add(rdo_kichThuoc);
        rdo_kichThuoc.setText("Kích thước");
        rdo_kichThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdo_kichThuocMouseClicked(evt);
            }
        });

        buttonGroup1.add(rdo_chatLieu);
        rdo_chatLieu.setText("Chất liệu");
        rdo_chatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdo_chatLieuMouseClicked(evt);
            }
        });

        buttonGroup1.add(rdo_deGiay);
        rdo_deGiay.setText("Đế giày");
        rdo_deGiay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdo_deGiayMouseClicked(evt);
            }
        });

        btn_themThuocTinhSanPham.setText("Thêm");
        btn_themThuocTinhSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themThuocTinhSanPhamActionPerformed(evt);
            }
        });

        btn_suaThuocTinhSanPham.setText("Sửa");
        btn_suaThuocTinhSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaThuocTinhSanPhamActionPerformed(evt);
            }
        });

        btn_resetThuocTinhSanPham.setText("Reset");
        btn_resetThuocTinhSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetThuocTinhSanPhamActionPerformed(evt);
            }
        });

        jLabel16.setText("Trạng Thái");

        cbo_trangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt động", "Không hoạt động" }));

        jLabel21.setText("Mã thuộc tính");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(btn_themThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(btn_suaThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(btn_resetThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(23, 23, 23)
                                .addComponent(txtMaTT)
                                .addGap(51, 51, 51)))
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbo_trangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(rdo_loaiDay)
                                .addGap(50, 50, 50)
                                .addComponent(rdo_mauSac)
                                .addGap(51, 51, 51)
                                .addComponent(rdo_kichThuoc)
                                .addGap(39, 39, 39)
                                .addComponent(rdo_chatLieu)
                                .addGap(34, 34, 34)
                                .addComponent(rdo_deGiay))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_tenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdo_loaiDay)
                        .addComponent(rdo_mauSac)
                        .addComponent(rdo_kichThuoc)
                        .addComponent(rdo_chatLieu)
                        .addComponent(rdo_deGiay))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(txtMaTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_suaThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_themThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_resetThuocTinhSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(cbo_trangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Quản lý thuộc tính");

        tbl_thuocTinhSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã thuộc tính", "Tên Thuộc Tính", "Trạng Thái"
            }
        ));
        tbl_thuocTinhSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_thuocTinhSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_thuocTinhSanPham);

        btnSearchDHD1.setText("Đang hoạt động");
        btnSearchDHD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchDHD1ActionPerformed(evt);
            }
        });

        btnSearchKHD1.setText("Không hoạt động");
        btnSearchKHD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchKHD1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addContainerGap(942, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1049, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(btnSearchDHD1)
                                .addGap(18, 18, 18)
                                .addComponent(btnSearchKHD1)
                                .addGap(245, 245, 245))))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearchDHD1)
                    .addComponent(btnSearchKHD1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(142, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thuộc tính sản phẩm", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        txt_maSP.setText("");
        txt_tenSP.setText("");
        rdo_hd.setSelected(true);
        fillTable(repo.getAll());
        txt_tim.setText("Nhập mã sản phẩm");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (this.readForm11() != null) {
            int sua = JOptionPane.showConfirmDialog(this, "Ban Muon sua khong");
            if (sua == 0) {
                repo.update_SanPham(String.valueOf(tbl_bang.getValueAt(tbl_bang.getSelectedRow(), 1).toString()), this.readForm());
                this.fillTable(repo.getAll());
                JOptionPane.showMessageDialog(this, "Sua Thanh Cong");
            }
            try {
                model();
            } catch (SQLException ex) {
                Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Sua Khong Thanh Cong");

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (this.readForm1() != null) {
            int them = JOptionPane.showConfirmDialog(this, "Ban Muon them khong");
            if (them == 0) {
                repo.add_SanPham(this.readForm1());
                this.fillTable(repo.getAll());
                JOptionPane.showMessageDialog(this, "Them Thanh Cong");
                try {
                    model();
                } catch (SQLException ex) {
                    Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Them Khong Thanh Cong");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbl_bangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bangMouseClicked
        // TODO add your handling code here:
        String tinhtrang = "Đang Hoạt động";
        int row = tbl_bang.getSelectedRow();
        if (row != -1) {
            txt_maSP.setText(tbl_bang.getValueAt(row, 1).toString());
            txt_tenSP.setText(tbl_bang.getValueAt(row, 2).toString());
            if (tinhtrang.equals(tbl_bang.getValueAt(row, 5).toString())) {
                rdo_hd.setSelected(true);
            } else {
                rdo_khd.setSelected(true);
            }
        }
    }//GEN-LAST:event_tbl_bangMouseClicked

    private void btn_themThuocTinhSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themThuocTinhSanPhamActionPerformed
        if (readformTT()) {
            if (readformTT1()) {
                int index = cbo_trangThai.getSelectedIndex() == 0 ? 1 : 0;
                if (index == -1) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn trạng thái.");
                }
                int them = JOptionPane.showConfirmDialog(this, "Ban muon them khong?");
                if (them == 0) {
                    if (rdo_loaiDay.isSelected()) {
                        Model_loaiDay ld = new Model_loaiDay(txtMaTT.getText(), txt_tenThuocTinh.getText(), index);
                        try {
                            int i = repo_tt.add_LoaiDay(ld);
                            mol = (DefaultTableModel) tbl_thuocTinhSanPham.getModel();
                            mol.setRowCount(0);
                            ArrayList<Model_loaiDay> b;
                            try {
                                b = repo_tt.getAll_LoaiDay();
                                for (Model_loaiDay x : b) {
                                    mol.addRow(x.toDataRow());
                                }
                                tbl_thuocTinhSanPham.setModel(mol);
                            } catch (SQLException ex) {
                                Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (i == 1) {
                                JOptionPane.showMessageDialog(this, "Them thanh cong");
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (rdo_mauSac.isSelected()) {
                        Model_mauSac ld = new Model_mauSac(txtMaTT.getText(), txt_tenThuocTinh.getText(), index);
                        try {
                            int i = repo_tt.add_MauSac(ld);
                            mol = (DefaultTableModel) tbl_thuocTinhSanPham.getModel();
                            mol.setRowCount(0);
                            ArrayList<Model_mauSac> a;
                            try {
                                a = repo_tt.getAll_mauSac();
                                for (Model_mauSac x : a) {
                                    mol.addRow(x.toDataRow());
                                }
                                tbl_thuocTinhSanPham.setModel(mol);
                            } catch (SQLException ex) {
                                Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (i == 1) {
                                JOptionPane.showMessageDialog(this, "Them Thanh Cong");
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (rdo_kichThuoc.isSelected()) {
                        Model_kichThuoc ld = new Model_kichThuoc(txtMaTT.getText(), txt_tenThuocTinh.getText(), index);
                        try {
                            int i = repo_tt.add_KichThuoc(ld);
                            mol = (DefaultTableModel) tbl_thuocTinhSanPham.getModel();
                            mol.setRowCount(0);
                            ArrayList<Model_kichThuoc> c;
                            try {
                                c = repo_tt.getAll_KichThuoc();
                                for (Model_kichThuoc x : c) {
                                    mol.addRow(x.toDataRow());
                                }
                                tbl_thuocTinhSanPham.setModel(mol);
                            } catch (SQLException ex) {
                                Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (i == 1) {
                                JOptionPane.showMessageDialog(this, "Them Thanh Cong");
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (rdo_chatLieu.isSelected()) {
                        Model_chatLieu ld = new Model_chatLieu(txtMaTT.getText(), txt_tenThuocTinh.getText(), index);
                        try {
                            int i = repo_tt.add_ChatLieu(ld);
                            mol = (DefaultTableModel) tbl_thuocTinhSanPham.getModel();
                            mol.setRowCount(0);
                            ArrayList<Model_chatLieu> d;
                            try {
                                d = repo_tt.getAll_ChatLieu();
                                for (Model_chatLieu x : d) {
                                    mol.addRow(x.toDataRow());
                                }
                                tbl_thuocTinhSanPham.setModel(mol);
                            } catch (SQLException ex) {
                                Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (i == 1) {
                                JOptionPane.showMessageDialog(this, "Them Thanh Cong");
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (rdo_deGiay.isSelected()) {
                        Model_DeGiay ld = new Model_DeGiay(txtMaTT.getText(), txt_tenThuocTinh.getText(), index);
                        try {
                            int i = repo_tt.add_DeGiay(ld);
                            mol = (DefaultTableModel) tbl_thuocTinhSanPham.getModel();
                            mol.setRowCount(0);
                            ArrayList<Model_DeGiay> e;
                            try {
                                e = repo_tt.getAll_DeGiay();
                                for (Model_DeGiay x : e) {
                                    mol.addRow(x.toDataRow());
                                }
                                tbl_thuocTinhSanPham.setModel(mol);
                            } catch (SQLException ex) {
                                Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (i == 1) {
                                JOptionPane.showMessageDialog(this, "Them Thanh Cong");
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                try {
                    model();
                } catch (SQLException ex) {
                    Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btn_themThuocTinhSanPhamActionPerformed

    private void btn_suaThuocTinhSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaThuocTinhSanPhamActionPerformed
        if (readformTT()) {
            int index = cbo_trangThai.getSelectedIndex() == 0 ? 1 : 0;
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn trạng thái.");
            }
            int them = JOptionPane.showConfirmDialog(this, "Ban muon sua khong?");
            if (them == 0) {
                if (rdo_loaiDay.isSelected()) {
                    Model_loaiDay ld = new Model_loaiDay((String) tbl_thuocTinhSanPham.getValueAt(tbl_thuocTinhSanPham.getSelectedRow(), 0), txt_tenThuocTinh.getText(), index);
                    try {
                        int i = repo_tt.update_LoaiDay(ld);
                        mol = (DefaultTableModel) tbl_thuocTinhSanPham.getModel();
                        mol.setRowCount(0);
                        ArrayList<Model_loaiDay> b;
                        try {
                            b = repo_tt.getAll_LoaiDay();
                            for (Model_loaiDay x : b) {
                                mol.addRow(x.toDataRow());
                            }
                            tbl_thuocTinhSanPham.setModel(mol);
                        } catch (SQLException ex) {
                            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (i == 1) {
                            JOptionPane.showMessageDialog(this, "Sua thanh cong");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (rdo_mauSac.isSelected()) {
                    Model_mauSac ld = new Model_mauSac((String) tbl_thuocTinhSanPham.getValueAt(tbl_thuocTinhSanPham.getSelectedRow(), 0), txt_tenThuocTinh.getText(), index);
                    try {
                        int i = repo_tt.update_MauSac(ld);
                        mol = (DefaultTableModel) tbl_thuocTinhSanPham.getModel();
                        mol.setRowCount(0);
                        ArrayList<Model_mauSac> a;
                        try {
                            a = repo_tt.getAll_mauSac();
                            for (Model_mauSac x : a) {
                                mol.addRow(x.toDataRow());
                            }
                            tbl_thuocTinhSanPham.setModel(mol);
                        } catch (SQLException ex) {
                            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (i == 1) {
                            JOptionPane.showMessageDialog(this, "Sua thanh cong");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (rdo_kichThuoc.isSelected()) {
                    Model_kichThuoc ld = new Model_kichThuoc((String) tbl_thuocTinhSanPham.getValueAt(tbl_thuocTinhSanPham.getSelectedRow(), 0), txt_tenThuocTinh.getText(), index);
                    try {
                        int i = repo_tt.update_KichThuoc(ld);
                        mol = (DefaultTableModel) tbl_thuocTinhSanPham.getModel();
                        mol.setRowCount(0);
                        ArrayList<Model_kichThuoc> c;
                        try {
                            c = repo_tt.getAll_KichThuoc();
                            for (Model_kichThuoc x : c) {
                                mol.addRow(x.toDataRow());
                            }
                            tbl_thuocTinhSanPham.setModel(mol);
                        } catch (SQLException ex) {
                            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (i == 1) {
                            JOptionPane.showMessageDialog(this, "Sua thanh cong");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (rdo_chatLieu.isSelected()) {
                    Model_chatLieu ld = new Model_chatLieu((String) tbl_thuocTinhSanPham.getValueAt(tbl_thuocTinhSanPham.getSelectedRow(), 0), txt_tenThuocTinh.getText(), index);
                    try {
                        int i = repo_tt.update_ChatLieu(ld);
                        mol = (DefaultTableModel) tbl_thuocTinhSanPham.getModel();
                        mol.setRowCount(0);
                        ArrayList<Model_chatLieu> d;
                        try {
                            d = repo_tt.getAll_ChatLieu();
                            for (Model_chatLieu x : d) {
                                mol.addRow(x.toDataRow());
                            }
                            tbl_thuocTinhSanPham.setModel(mol);
                        } catch (SQLException ex) {
                            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (i == 1) {
                            JOptionPane.showMessageDialog(this, "Sua thanh cong");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (rdo_deGiay.isSelected()) {
                    Model_DeGiay ld = new Model_DeGiay((String) tbl_thuocTinhSanPham.getValueAt(tbl_thuocTinhSanPham.getSelectedRow(), 0), txt_tenThuocTinh.getText(), index);
                    try {
                        int i = repo_tt.update_DeGiay(ld);
                        mol = (DefaultTableModel) tbl_thuocTinhSanPham.getModel();
                        mol.setRowCount(0);
                        ArrayList<Model_DeGiay> e;
                        try {
                            e = repo_tt.getAll_DeGiay();
                            for (Model_DeGiay x : e) {
                                mol.addRow(x.toDataRow());
                            }
                            tbl_thuocTinhSanPham.setModel(mol);
                        } catch (SQLException ex) {
                            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (i == 1) {
                            JOptionPane.showMessageDialog(this, "Sua thanh cong");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            try {
                model();
            } catch (SQLException ex) {
                Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_suaThuocTinhSanPhamActionPerformed

    private void btn_resetThuocTinhSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetThuocTinhSanPhamActionPerformed
        txtMaTT.setText("");
        txt_tenThuocTinh.setText("");
        rdo_loaiDay.setSelected(true);
        cbo_trangThai.setSelectedIndex(0);
        try {
            fillTable_tt(repo_tt.getAll_LoaiDay(), repo_tt.getAll_mauSac(), repo_tt.getAll_KichThuoc(), repo_tt.getAll_ChatLieu(), repo_tt.getAll_DeGiay());
        } catch (SQLException ex) {
            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_resetThuocTinhSanPhamActionPerformed

    private void tbl_thuocTinhSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_thuocTinhSanPhamMouseClicked
        int index = tbl_thuocTinhSanPham.getSelectedRow();
        txtMaTT.setText((String) tbl_thuocTinhSanPham.getValueAt(index, 0));
        txt_tenThuocTinh.setText((String) tbl_thuocTinhSanPham.getValueAt(index, 1));
        if (tbl_thuocTinhSanPham.getValueAt(index, 2).equals("Đang Hoạt động")) {
            cbo_trangThai.setSelectedIndex(0);
        } else {
            cbo_trangThai.setSelectedIndex(1);
        }
        ArrayList<Model_loaiDay> ld;
        try {
            ld = repo_tt.getAll_LoaiDay();
            for (int i = 0; i < ld.size(); i++) {
                if (ld.get(i).getMaLoaiGiay().equals(tbl_thuocTinhSanPham.getValueAt(index, 0))) {
                    rdo_loaiDay.setSelected(true);
                    break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Model_mauSac> ms;
        try {
            ms = repo_tt.getAll_mauSac();
            for (int i = 0; i < ms.size(); i++) {
                if (ms.get(i).getMaMauSac().equals(tbl_thuocTinhSanPham.getValueAt(index, 0))) {
                    rdo_mauSac.setSelected(true);
                    break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Model_kichThuoc> kt;
        try {
            kt = repo_tt.getAll_KichThuoc();
            for (int i = 0; i < kt.size(); i++) {
                if (kt.get(i).getMaKichThuoc().equals(tbl_thuocTinhSanPham.getValueAt(index, 0))) {
                    rdo_kichThuoc.setSelected(true);
                    break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Model_chatLieu> cl;
        try {
            cl = repo_tt.getAll_ChatLieu();
            for (int i = 0; i < cl.size(); i++) {
                if (cl.get(i).getMaChatLieu().equals(tbl_thuocTinhSanPham.getValueAt(index, 0))) {
                    rdo_chatLieu.setSelected(true);
                    break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Model_DeGiay> dg;
        try {
            dg = repo_tt.getAll_DeGiay();
            for (int i = 0; i < dg.size(); i++) {
                if (dg.get(i).getMaDeGiay().equals(tbl_thuocTinhSanPham.getValueAt(index, 0))) {
                    rdo_deGiay.setSelected(true);
                    break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbl_thuocTinhSanPhamMouseClicked

    private void rdo_loaiDayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdo_loaiDayMouseClicked
        mol = (DefaultTableModel) tbl_thuocTinhSanPham.getModel();
        mol.setRowCount(0);
        ArrayList<Model_loaiDay> ld;
        try {
            ld = repo_tt.getAll_LoaiDay();
            for (Model_loaiDay x : ld) {
                mol.addRow(x.toDataRow());
            }
            tbl_thuocTinhSanPham.setModel(mol);
        } catch (SQLException ex) {
            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rdo_loaiDayMouseClicked

    private void rdo_mauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdo_mauSacMouseClicked
        mol = (DefaultTableModel) tbl_thuocTinhSanPham.getModel();
        mol.setRowCount(0);
        ArrayList<Model_mauSac> ld;
        try {
            ld = repo_tt.getAll_mauSac();
            for (Model_mauSac x : ld) {
                mol.addRow(x.toDataRow());
            }
            tbl_thuocTinhSanPham.setModel(mol);
        } catch (SQLException ex) {
            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rdo_mauSacMouseClicked

    private void rdo_kichThuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdo_kichThuocMouseClicked
        mol = (DefaultTableModel) tbl_thuocTinhSanPham.getModel();
        mol.setRowCount(0);
        ArrayList<Model_kichThuoc> ld;
        try {
            ld = repo_tt.getAll_KichThuoc();
            for (Model_kichThuoc x : ld) {
                mol.addRow(x.toDataRow());
            }
            tbl_thuocTinhSanPham.setModel(mol);
        } catch (SQLException ex) {
            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rdo_kichThuocMouseClicked

    private void rdo_chatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdo_chatLieuMouseClicked
        mol = (DefaultTableModel) tbl_thuocTinhSanPham.getModel();
        mol.setRowCount(0);
        ArrayList<Model_chatLieu> ld;
        try {
            ld = repo_tt.getAll_ChatLieu();
            for (Model_chatLieu x : ld) {
                mol.addRow(x.toDataRow());
            }
            tbl_thuocTinhSanPham.setModel(mol);
        } catch (SQLException ex) {
            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rdo_chatLieuMouseClicked

    private void rdo_deGiayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdo_deGiayMouseClicked
        mol = (DefaultTableModel) tbl_thuocTinhSanPham.getModel();
        mol.setRowCount(0);
        ArrayList<Model_DeGiay> ld;
        try {
            ld = repo_tt.getAll_DeGiay();
            for (Model_DeGiay x : ld) {
                mol.addRow(x.toDataRow());
            }
            tbl_thuocTinhSanPham.setModel(mol);
        } catch (SQLException ex) {
            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rdo_deGiayMouseClicked

    private void tbl_thongTinSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_thongTinSanPhamMouseClicked
        int index = tbl_thongTinSanPham.getSelectedRow();
        txt_maSanPham.setText((String) tbl_thongTinSanPham.getValueAt(index, 0));
        txt_tenSanPham.setSelectedItem((String) tbl_thongTinSanPham.getValueAt(index, 1));
        cbo_loaiSanPham.setSelectedItem(tbl_thongTinSanPham.getValueAt(index, 2));
        txt_donGia.setText(Integer.toString((int) tbl_thongTinSanPham.getValueAt(index, 3)));
        txt_soLuong.setText(Integer.toString((int) tbl_thongTinSanPham.getValueAt(index, 4)));
        cbo_loaiDay.setSelectedItem(tbl_thongTinSanPham.getValueAt(index, 5));
        cbo_mauSac.setSelectedItem(tbl_thongTinSanPham.getValueAt(index, 9));
        cbo_kichThuoc.setSelectedItem(tbl_thongTinSanPham.getValueAt(index, 8));
        cbo_chatLieu.setSelectedItem(tbl_thongTinSanPham.getValueAt(index, 6));
        cbo_deGiay.setSelectedItem(tbl_thongTinSanPham.getValueAt(index, 7));
        cbo_trangThaiSanPhamCHiTiet.setSelectedItem(tbl_thongTinSanPham.getValueAt(index, 10));
    }//GEN-LAST:event_tbl_thongTinSanPhamMouseClicked

    private void btn_themThongTinSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themThongTinSanPhamActionPerformed
        if (readform()) {
            if (readform1()) {
                int them = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn không?");
                if (them == 0) {
                    try {
                        int i = repo_spct.add_SPCT(txt_maSanPham.getText(), checkTT(cbo_kichThuoc.getSelectedItem()), checkTT(cbo_mauSac.getSelectedItem()), checkSP(txt_tenSanPham.getSelectedItem()), checkTT(cbo_deGiay.getSelectedItem()), checkTT(cbo_loaiDay.getSelectedItem()), checkTT(cbo_chatLieu.getSelectedItem()), Integer.parseInt(txt_soLuong.getText()), cbo_loaiSanPham.getSelectedItem().equals("Nam") ? 1 : 0, Integer.parseInt(txt_donGia.getText()), cbo_trangThaiSanPhamCHiTiet.getSelectedItem().equals("Hoạt động") ? 1 : 0);
                        if (i == 1) {
                            JOptionPane.showMessageDialog(this, "Thêm thành công.");
                        } else {
                            JOptionPane.showMessageDialog(this, "Thêm thất bại.");
                        }
                        filltable_SPCT(repo_spct.GetAll_SPCT());
                    } catch (SQLException ex) {
                        Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_btn_themThongTinSanPhamActionPerformed

    private void btn_suaThongTinSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaThongTinSanPhamActionPerformed
        if (readform()) {
            int them = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn không?");
            if (them == 0) {
                try {
                    int i = repo_spct.update_SPCT(txt_maSanPham.getText(), checkTT(cbo_kichThuoc.getSelectedItem()), checkTT(cbo_mauSac.getSelectedItem()), checkSP(txt_tenSanPham.getSelectedItem()), checkTT(cbo_deGiay.getSelectedItem()), checkTT(cbo_loaiDay.getSelectedItem()), checkTT(cbo_chatLieu.getSelectedItem()), Integer.parseInt(txt_soLuong.getText()), cbo_loaiSanPham.getSelectedItem().equals("Nam") ? 1 : 0, Integer.parseInt(txt_donGia.getText()), cbo_trangThaiSanPhamCHiTiet.getSelectedItem().equals("Hoạt động") ? 1 : 0);
                    if (i == 1) {
                        JOptionPane.showMessageDialog(this, "Sửa thành công.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Sửa thất bại.");
                    }
                    filltable_SPCT(repo_spct.GetAll_SPCT());
                } catch (SQLException ex) {
                    Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btn_suaThongTinSanPhamActionPerformed

    private void btn_resetThongTinSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetThongTinSanPhamActionPerformed
        txt_maSanPham.setText("");
        txt_tenSanPham.setSelectedIndex(0);
        cbo_loaiSanPham.setSelectedIndex(0);
        txt_donGia.setText("");
        txt_soLuong.setText("");
        cbo_loaiDay.setSelectedIndex(0);
        cbo_deGiay.setSelectedIndex(0);
        cbo_kichThuoc.setSelectedIndex(0);
        cbo_chatLieu.setSelectedIndex(0);
        cbo_mauSac.setSelectedIndex(0);
        cbo_trangThai.setSelectedIndex(0);
        try {
            filltable_SPCT(repo_spct.GetAll_SPCT());
        } catch (SQLException ex) {
            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        int dem = tbl_thongTinSanPham.getRowCount();
        txt_tongSP.setText(String.valueOf(dem));
    }//GEN-LAST:event_btn_resetThongTinSanPhamActionPerformed

    private void btnSearchDHD2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchDHD2ActionPerformed
        // TODO add your handling code here:
        fillTable(repo.getAll_loc(this.readForm()));
    }//GEN-LAST:event_btnSearchDHD2ActionPerformed

    private void btnSearchKHD2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchKHD2ActionPerformed
        // TODO add your handling code here:
        fillTable(repo.getAll_loc2(this.readForm()));
    }//GEN-LAST:event_btnSearchKHD2ActionPerformed

    private void btnSearchKHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchKHDActionPerformed
        mol = (DefaultTableModel) tbl_thongTinSanPham.getModel();
        mol.setRowCount(0);
        try {
            for (Model_SPCT x : repo_spct.GetAll_SPCT()) {
                if (x.getTrangThai()==0) {
                    mol.addRow(x.toDataRow());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbl_thongTinSanPham.setModel(mol);
    }//GEN-LAST:event_btnSearchKHDActionPerformed

    private void btnSearchDHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchDHDActionPerformed
        mol = (DefaultTableModel) tbl_thongTinSanPham.getModel();
        mol.setRowCount(0);
        try {
            for (Model_SPCT x : repo_spct.GetAll_SPCT()) {
                if (x.getTrangThai()==1) {
                    mol.addRow(x.toDataRow());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbl_thongTinSanPham.setModel(mol);
    }//GEN-LAST:event_btnSearchDHDActionPerformed

    private void btnSearchKHD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchKHD1ActionPerformed
        mol = (DefaultTableModel) tbl_thuocTinhSanPham.getModel();
        mol.setRowCount(0);
        try {
            for (Model_loaiDay x : repo_tt.getAll_LoaiDay()) {
                if (x.getTrangthai()==0) {
                    mol.addRow(x.toDataRow());
                }
            }
            for (Model_mauSac x : repo_tt.getAll_mauSac()) {
                if (x.getTrangThai()==0) {
                    mol.addRow(x.toDataRow());
                }
            }
            for (Model_kichThuoc x : repo_tt.getAll_KichThuoc()) {
                if (x.getTrangThai()==0) {
                    mol.addRow(x.toDataRow());
                }
            }
            for (Model_chatLieu x : repo_tt.getAll_ChatLieu()) {
                if (x.getTrangThai()==0) {
                    mol.addRow(x.toDataRow());
                }
            }
            for (Model_DeGiay x : repo_tt.getAll_DeGiay()) {
                if (x.getTrangThai()==0) {
                    mol.addRow(x.toDataRow());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbl_thuocTinhSanPham.setModel(mol);
    }//GEN-LAST:event_btnSearchKHD1ActionPerformed

    private void btnSearchDHD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchDHD1ActionPerformed
        mol = (DefaultTableModel) tbl_thuocTinhSanPham.getModel();
        mol.setRowCount(0);
        try {
            for (Model_loaiDay x : repo_tt.getAll_LoaiDay()) {
                if (x.getTrangthai()==1) {
                    mol.addRow(x.toDataRow());
                }
            }
            for (Model_mauSac x : repo_tt.getAll_mauSac()) {
                if (x.getTrangThai()==1) {
                    mol.addRow(x.toDataRow());
                }
            }
            for (Model_kichThuoc x : repo_tt.getAll_KichThuoc()) {
                if (x.getTrangThai()==1) {
                    mol.addRow(x.toDataRow());
                }
            }
            for (Model_chatLieu x : repo_tt.getAll_ChatLieu()) {
                if (x.getTrangThai()==1) {
                    mol.addRow(x.toDataRow());
                }
            }
            for (Model_DeGiay x : repo_tt.getAll_DeGiay()) {
                if (x.getTrangThai()==1) {
                    mol.addRow(x.toDataRow());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbl_thuocTinhSanPham.setModel(mol);
    }//GEN-LAST:event_btnSearchDHD1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
           String ma = txt_tim.getText().trim();
        if(repo.timTen(ma).isEmpty()){
            JOptionPane.showMessageDialog(this, "Không tồn tại");
        }
        else {
            JOptionPane.showMessageDialog(this, "Đã tìm thấy");
            this.fillTable(repo.timTen(ma));
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txt_timActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timActionPerformed
        // TODO add your handling code here:
        txt_tim.setText("");
    }//GEN-LAST:event_txt_timActionPerformed

    public boolean readform() {
        if (txt_maSanPham.getText().equals(" ") || txt_maSanPham.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sản phẩm chi tiết.");
            txt_maSanPham.requestFocus();
            return false;
        } else if (txt_maSanPham.getText().length() != 5) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã đúng định dạng(5 kí tự).");
            txt_maSanPham.requestFocus();
            return false;
        } else if ( txt_donGia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đơn giá.");
            txt_donGia.requestFocus();
            return false;
        } else if ((Integer.parseInt(txt_donGia.getText())) <= 0  ) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đơn giá >0");
            txt_donGia.requestFocus();
            return false;
        } else if ( txt_soLuong.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng.");
            txt_soLuong.requestFocus();
            return false;
        } else if ((Integer.parseInt(txt_soLuong.getText())) <= 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng >0");
            txt_soLuong.requestFocus();
            return false;
        }
        return true;
    }

    public boolean readform1() {
        for (int i = 0; i < tbl_thongTinSanPham.getRowCount(); i++) {
            if (txt_maSanPham.getText().equals(tbl_thongTinSanPham.getValueAt(i, 0))) {
                JOptionPane.showMessageDialog(this, "Mã sản phẩm chi tiết đã tồn tại.");
                txt_maSanPham.requestFocus();
                return false;
            }
        }
        return true;
    }

    public String checkTT(Object Ten) {
        for (int i = 0; i < tbl_thuocTinhSanPham.getRowCount(); i++) {
            if (tbl_thuocTinhSanPham.getValueAt(i, 1).equals(Ten)) {
                return (String) tbl_thuocTinhSanPham.getValueAt(i, 0);
            }
        }
        return null;
    }

    public String checkSP(Object Ten) {
        for (int i = 0; i < tbl_bang.getRowCount(); i++) {
            if (tbl_bang.getValueAt(i, 2).equals(Ten)) {
                return (String) tbl_bang.getValueAt(i, 1);
            }
        }
        return null;
    }

    private void fillTable_tt(ArrayList<Model_loaiDay> ld, ArrayList<Model_mauSac> ms, ArrayList<Model_kichThuoc> kt, ArrayList<Model_chatLieu> cl, ArrayList<Model_DeGiay> dg) {
        mol = (DefaultTableModel) tbl_thuocTinhSanPham.getModel();
        mol.setRowCount(0);
        for (Model_loaiDay x : ld) {
            mol.addRow(x.toDataRow());
        }
        for (Model_mauSac x : ms) {
            mol.addRow(x.toDataRow());
        }
        for (Model_kichThuoc x : kt) {
            mol.addRow(x.toDataRow());
        }
        for (Model_chatLieu x : cl) {
            mol.addRow(x.toDataRow());
        }
        for (Model_DeGiay x : dg) {
            mol.addRow(x.toDataRow());
        }
        tbl_thuocTinhSanPham.setModel(mol);
    }

    public boolean readformTT() {
        if (txtMaTT.getText().equals(" ") || txtMaTT.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng không để trống mã.");
            txtMaTT.requestFocus();
            return false;
        } else if (txtMaTT.getText().length() != 5) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã đúng định dạng(5 kí tự).");
            txtMaTT.requestFocus();
            return false;
        } else if (txt_tenThuocTinh.getText().equals(" ") || txt_tenThuocTinh.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng không để trống tên thuộc tính.");
            txt_tenThuocTinh.requestFocus();
            return false;
        }
        return true;
    }

    public boolean readformTT1() {
        for (int i = 0; i < tbl_thuocTinhSanPham.getRowCount(); i++) {
            if (txtMaTT.getText().equals(tbl_thuocTinhSanPham.getValueAt(i, 0))) {
                JOptionPane.showMessageDialog(this, "Mã đã tồn tại");
                txtMaTT.requestFocus();
                return false;
            }
        }
        return true;
    }

    public String checkTT_SP(Object Ten) {
        for (int i = 0; i < tbl_bang.getRowCount(); i++) {
            if (tbl_bang.getValueAt(i, 2).equals(Ten)) {
                return (String) tbl_bang.getValueAt(i, 5);
            }
        }
        return null;
    }

    public void filltable_SPCT(ArrayList<Model_SPCT> spct) throws SQLException {
        mol = (DefaultTableModel) tbl_thongTinSanPham.getModel();
        mol.setRowCount(0);
        for (Model_SPCT x : spct) {
            if (checkTT_SP(x.getTen()).equals("Đang Hoạt động")) {
                mol.addRow(x.toDataRow());
            }
        }
        tbl_thongTinSanPham.setModel(mol);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearchDHD;
    private javax.swing.JButton btnSearchDHD1;
    private javax.swing.JButton btnSearchDHD2;
    private javax.swing.JButton btnSearchKHD;
    private javax.swing.JButton btnSearchKHD1;
    private javax.swing.JButton btnSearchKHD2;
    private javax.swing.JButton btn_resetThongTinSanPham;
    private javax.swing.JButton btn_resetThuocTinhSanPham;
    private javax.swing.JButton btn_suaThongTinSanPham;
    private javax.swing.JButton btn_suaThuocTinhSanPham;
    private javax.swing.JButton btn_themThongTinSanPham;
    private javax.swing.JButton btn_themThuocTinhSanPham;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cbo_chatLieu;
    private javax.swing.JComboBox<String> cbo_deGiay;
    private javax.swing.JComboBox<String> cbo_kichThuoc;
    private javax.swing.JComboBox<String> cbo_loaiDay;
    private javax.swing.JComboBox<String> cbo_loaiSanPham;
    private javax.swing.JComboBox<String> cbo_mauSac;
    private javax.swing.JComboBox<String> cbo_trangThai;
    private javax.swing.JComboBox<String> cbo_trangThaiSanPhamCHiTiet;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdo_chatLieu;
    private javax.swing.JRadioButton rdo_deGiay;
    private javax.swing.JRadioButton rdo_hd;
    private javax.swing.JRadioButton rdo_khd;
    private javax.swing.JRadioButton rdo_kichThuoc;
    private javax.swing.JRadioButton rdo_loaiDay;
    private javax.swing.JRadioButton rdo_mauSac;
    private javax.swing.JTable tbl_bang;
    private javax.swing.JTable tbl_thongTinSanPham;
    private javax.swing.JTable tbl_thuocTinhSanPham;
    private javax.swing.JTextField txtMaTT;
    private javax.swing.JTextField txt_donGia;
    private javax.swing.JTextField txt_maSP;
    private javax.swing.JTextField txt_maSanPham;
    private javax.swing.JTextField txt_soLuong;
    private javax.swing.JTextField txt_tenSP;
    private javax.swing.JComboBox<String> txt_tenSanPham;
    private javax.swing.JTextField txt_tenThuocTinh;
    private javax.swing.JTextField txt_tim;
    private javax.swing.JTextField txt_tongSP;
    // End of variables declaration//GEN-END:variables
}
