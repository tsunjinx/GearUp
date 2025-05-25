/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Model.Model_khachHang;
import Repository.Responsitory_khachHang;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PonYanki
 */
public class khachHang extends javax.swing.JPanel {

    private Responsitory_khachHang repo = new Responsitory_khachHang();
    private DefaultTableModel mol = new DefaultTableModel();
    private int i = -1;

    /**
     * Creates new form SanPham
     */
    public khachHang() {
        initComponents();
        cbo_tinhTrangkhachHang.removeAllItems();
        cbo_tinhTrangkhachHang.addItem("Đang hoạt động");
        cbo_tinhTrangkhachHang.addItem("Không hoạt động");
        fillTable(repo.getAll());
    }

    private void fillTable(ArrayList<Model_khachHang> all) {
        mol = (DefaultTableModel) tbl_danhSachKhachHang.getModel();
        mol.setRowCount(0);
        for (Model_khachHang x : all) {
            mol.addRow(x.toDataRow());
        }
    }

    void showData(int index) {
        String ma = tbl_danhSachKhachHang.getValueAt(index, 0).toString();
        String ten = tbl_danhSachKhachHang.getValueAt(index, 1).toString();
        String sdt = tbl_danhSachKhachHang.getValueAt(index, 2).toString();
        String ngayTao = tbl_danhSachKhachHang.getValueAt(index, 3).toString();
        String ngaySua = tbl_danhSachKhachHang.getValueAt(index, 4).toString();
        String eamil = null;
        if (tbl_danhSachKhachHang.getValueAt(index, 5) == null) {
            eamil = null;
        } else {
            eamil = tbl_danhSachKhachHang.getValueAt(index, 5).toString();
        }
        String gioiTinh = tbl_danhSachKhachHang.getValueAt(index, 6).toString();
        String trangThai = tbl_danhSachKhachHang.getValueAt(index, 7).toString();
        txt_maKhachHang.setText(ma);
        txt_tenKhachHang.setText(ten);
        txt_soDienThoai.setText(sdt);
        txt_email.setText(eamil);
        if (gioiTinh.equals("Nam")) {
            rdo_nam.setSelected(true);
        } else {
            rdo_nu.setSelected(true);
        }
        cbo_tinhTrangkhachHang.setSelectedItem(trangThai);
    }

    private Model_khachHang readForm_KhacHang() {

        String IDKhachHang;
        String tenKH;
        String SDT;
        String email;
        int gioiTinh;
        int trangThai;
        IDKhachHang = txt_maKhachHang.getText().trim();
        tenKH = txt_tenKhachHang.getText().trim();
        SDT = txt_soDienThoai.getText().trim();
        email = txt_email.getText().trim();

        if (rdo_nam.isSelected()) {
            gioiTinh = 1;
        } else {
            gioiTinh = 0;
        }
        if (cbo_tinhTrangkhachHang.getSelectedItem().equals("Đang hoạt động")) {
            trangThai = 1;
        } else {
            trangThai = 0;
        }
        if (tenKH.isEmpty() || tenKH.equals(" ")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khách hàng");
            return null;
        } else if (IDKhachHang.length() != 5) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã đúng định dạng(5 kí tự).");
            return null;
        } else if (SDT.isEmpty() || SDT.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Số Điện Thoại");
            return null;
        } else if (SDT.length() > 14) {
            JOptionPane.showMessageDialog(this, "Số Điện Thoại Tối đa 14 ký tự");
            return null;
        } else if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email rỗng");
            return null;
        }
        if (IDKhachHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Mã khách hàng");
            return null;
        } else if ((Integer.parseInt(SDT)) < 0) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được âm");
            return null;
        } else if (!(SDT.startsWith("0"))) {
            JOptionPane.showMessageDialog(this, "Số điện thoại bắt đầu bằng số 0");
            return null;
        } else if (!(email.contains("@email.com") || email.contains("@gmail.com"))) {
            JOptionPane.showMessageDialog(this, "Không đúng cấu trúc email");
            return null;
        }

        for (int i = 0; i < tbl_danhSachKhachHang.getRowCount(); i++) {
            Object existingValue = tbl_danhSachKhachHang.getValueAt(i, 0);
            if (existingValue != null && IDKhachHang.equalsIgnoreCase(existingValue.toString())) {
                JOptionPane.showMessageDialog(this, "Mã khach hang đã tồn tại!");
                return null;
            }
        }

        return new Model_khachHang(IDKhachHang, tenKH, SDT, email, gioiTinh, trangThai);
    }

    private Model_khachHang readForm_KhacHang1() {
        String maCu = tbl_danhSachKhachHang.getValueAt(i, 0).toString();
        String IDKhachHang;
        String tenKH;
        String SDT;
        String email;
        int gioiTinh;
        int trangThai;
        IDKhachHang = txt_maKhachHang.getText().trim();
        tenKH = txt_tenKhachHang.getText().trim();
        SDT = txt_soDienThoai.getText().trim();
        email = txt_email.getText().trim();

        if (rdo_nam.isSelected()) {
            gioiTinh = 1;
        } else {
            gioiTinh = 0;
        }
        if (cbo_tinhTrangkhachHang.getSelectedItem().equals("Đang hoạt động")) {
            trangThai = 1;
        } else {
            trangThai = 0;
        }

        if (tenKH.isEmpty() || tenKH.equals(" ")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khách hàng");
            return null;
        } else if (IDKhachHang.length() != 5) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã đúng định dạng(5 kí tự).");
            return null;
        } else if (!(maCu.equalsIgnoreCase(IDKhachHang))) {
            JOptionPane.showMessageDialog(this, "Vui lòng không sửa Mã Khách Hàng");
            return null;
        } else if (SDT.isEmpty() || SDT.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Số Điện Thoại");
            return null;
        } else if (SDT.length() > 14) {
            JOptionPane.showMessageDialog(this, "Số Điện Thoại Tối đa 14 ký tự");
            return null;
        } else if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email rỗng");
            return null;
        } else if (!(email.contains("@email.com") || email.contains("@gmail.com"))) {
            JOptionPane.showMessageDialog(this, "Không đúng cấu trúc email");
            return null;
        }

        if (IDKhachHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Mã khách hàng");
            return null;
        } else if ((Integer.parseInt(SDT)) < 0) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được âm");
            return null;
        } else if (!(SDT.startsWith("0"))) {
            JOptionPane.showMessageDialog(this, "Số điện thoại bắt đầu bằng số 0");
            return null;
        }
        return new Model_khachHang(IDKhachHang, tenKH, SDT, email, gioiTinh, trangThai);
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
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        btn_tim2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txt_maKhachHang2022 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_tenKhachHang = new javax.swing.JTextField();
        txt_soDienThoai = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        rdo_nam = new javax.swing.JRadioButton();
        rdo_nu = new javax.swing.JRadioButton();
        btn_them1 = new javax.swing.JButton();
        btn_sua1 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        cbo_tinhTrangkhachHang = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        txt_maKhachHang = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_danhSachKhachHang = new javax.swing.JTable();
        btnSearchDHD2 = new javax.swing.JButton();
        btnSearchKHD2 = new javax.swing.JButton();

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jPanel5CaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });

        jLabel10.setText("Tìm kiếm");

        btn_tim2.setText("Tìm");
        btn_tim2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tim2ActionPerformed(evt);
            }
        });

        jLabel11.setText("Tên khách hàng");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_maKhachHang2022)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_tim2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_maKhachHang2022, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(btn_tim2))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setText("Thông tin khách hàng:");

        jLabel13.setText("Tên khách hàng");

        jLabel14.setText("Số điện thoại:");

        jLabel15.setText("Email:");

        jLabel16.setText("Giới tính:");

        buttonGroup1.add(rdo_nam);
        rdo_nam.setSelected(true);
        rdo_nam.setText("Nam");

        buttonGroup1.add(rdo_nu);
        rdo_nu.setText("Nữ");

        btn_them1.setText("Thêm");
        btn_them1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_them1ActionPerformed(evt);
            }
        });

        btn_sua1.setText("Sửa");
        btn_sua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sua1ActionPerformed(evt);
            }
        });

        jLabel17.setText("Trạng thái");

        cbo_tinhTrangkhachHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_tinhTrangkhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_tinhTrangkhachHangActionPerformed(evt);
            }
        });

        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txt_maKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_maKhachHangActionPerformed(evt);
            }
        });

        jLabel18.setText("Mã Khách hàng");

        jButton2.setText("Tình theo mã");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txt_soDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_maKhachHang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tenKhachHang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel15))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(rdo_nam)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdo_nu))
                                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbo_tinhTrangkhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_sua1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_them1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addGap(22, 22, 22))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addGap(28, 28, 28)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txt_soDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_maKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18)
                                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15)
                                    .addComponent(btn_them1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(rdo_nam)
                                    .addComponent(rdo_nu)
                                    .addComponent(btn_sua1)
                                    .addComponent(txt_tenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(cbo_tinhTrangkhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1))))
                        .addContainerGap(13, Short.MAX_VALUE))))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel19.setText("Danh sách khách hàng");

        tbl_danhSachKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Ngày Tạo", "Ngày Sửa", "Email", "Giới tính", "Trạng thái"
            }
        ));
        tbl_danhSachKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_danhSachKhachHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_danhSachKhachHang);

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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(0, 925, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSearchDHD2)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearchKHD2)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearchDHD2)
                    .addComponent(btnSearchKHD2))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tim2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tim2ActionPerformed
        // TODO add your handling code here:
        String tem = txt_maKhachHang2022.getText().trim();
        if (repo.timTen_KhachHang(tem).isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tồn tại");
        } else {
            JOptionPane.showMessageDialog(this, "Đã tìm thấy");
            this.fillTable(repo.timTen_KhachHang(tem));
        }
    }//GEN-LAST:event_btn_tim2ActionPerformed

    private void btn_them1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_them1ActionPerformed
        // TODO add your handling code here:
        if (this.readForm_KhacHang() != null) {
            int them = JOptionPane.showConfirmDialog(this, "Ban Muon them khong");
            if (them == 0) {
                repo.add_khachHang(this.readForm_KhacHang());
                this.fillTable(repo.getAll());
                JOptionPane.showMessageDialog(this, "Them Thanh Cong");

            }
        } else {
            JOptionPane.showMessageDialog(this, "Them Khong Thanh Cong");
        }
    }//GEN-LAST:event_btn_them1ActionPerformed

    private void btn_sua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sua1ActionPerformed
        // TODO add your handling code here:
        if (this.readForm_KhacHang1() != null) {
            int sua = JOptionPane.showConfirmDialog(this, "Ban Muon sua khong");
            if (sua == 0) {
                repo.update_khachHang(String.valueOf(tbl_danhSachKhachHang.getValueAt(i, 0).toString()), this.readForm_KhacHang1());
                this.fillTable(repo.getAll());
                JOptionPane.showMessageDialog(this, "Sua Thanh Cong");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Sua Khong Thanh Cong");

        }
    }//GEN-LAST:event_btn_sua1ActionPerformed

    private void cbo_tinhTrangkhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_tinhTrangkhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_tinhTrangkhachHangActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        txt_maKhachHang2022.setText("");
        txt_tenKhachHang.setText("");
        txt_soDienThoai.setText("");
        txt_email.setText("");
        fillTable(repo.getAll());
        txt_maKhachHang.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_maKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_maKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_maKhachHangActionPerformed

    private void tbl_danhSachKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_danhSachKhachHangMouseClicked
        // TODO add your handling code here:
        i = tbl_danhSachKhachHang.getSelectedRow();
        this.showData(i);
    }//GEN-LAST:event_tbl_danhSachKhachHangMouseClicked

    private void btnSearchDHD2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchDHD2ActionPerformed
        // TODO add your handling code here:
        fillTable(repo.getAll_tt1());

    }//GEN-LAST:event_btnSearchDHD2ActionPerformed

    private void btnSearchKHD2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchKHD2ActionPerformed
        // TODO add your handling code here
        fillTable(repo.getAll_tt0());

    }//GEN-LAST:event_btnSearchKHD2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String ma= JOptionPane.showInputDialog("Nhập mã khách hàng: ");
        if(repo.getAll_timMa(ma).isEmpty()){
            JOptionPane.showMessageDialog(this, "Mã Khách hàng không tồn tại ");
        }
        else {
            fillTable(repo.getAll_timMa(ma));
            JOptionPane.showMessageDialog(this, "Mã Khách hàng đã tìm thấy ");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jPanel5CaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jPanel5CaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel5CaretPositionChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearchDHD2;
    private javax.swing.JButton btnSearchKHD2;
    private javax.swing.JButton btn_sua1;
    private javax.swing.JButton btn_them1;
    private javax.swing.JButton btn_tim2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbo_tinhTrangkhachHang;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdo_nam;
    private javax.swing.JRadioButton rdo_nu;
    private javax.swing.JTable tbl_danhSachKhachHang;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_maKhachHang;
    private javax.swing.JTextField txt_maKhachHang2022;
    private javax.swing.JTextField txt_soDienThoai;
    private javax.swing.JTextField txt_tenKhachHang;
    // End of variables declaration//GEN-END:variables
}
