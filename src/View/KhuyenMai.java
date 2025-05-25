/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Model.Model_khuyenMai;
import Repository.Responsitory_khuyenMai;
import Model.Model_SPCT;
import Repository.Respository_SPCT;
import java.awt.Component;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PonYanki
 */
public class KhuyenMai extends javax.swing.JPanel {

    private DefaultTableModel mol = new DefaultTableModel();
    public Respository_SPCT repo_spct;
    public Responsitory_khuyenMai repo_khuyenMai;
    private int i = -1;

    /**
     * Creates new form SanPham
     */
    public KhuyenMai() throws SQLException {
        initComponents();
        repo_spct = new Respository_SPCT();
        repo_khuyenMai = new Responsitory_khuyenMai();
        repo_khuyenMai.autoUpdate();
        repo_khuyenMai.autoUpdate1();
        repo_khuyenMai.autoUpdate2();
        filltable_SPCT(repo_spct.GetAll_SPCT());

        filltable_khuyenMai(repo_khuyenMai.getAll_khuyenMai());
        tbl_sanPham.getColumnModel().getColumn(tbl_sanPham.getColumnCount() - 1).setCellEditor(new DefaultCellEditor(new JCheckBox()));
        tbl_sanPham.getColumnModel().getColumn(tbl_sanPham.getColumnCount() - 1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JCheckBox checkBox = new JCheckBox();
                checkBox.setSelected(value != null && (boolean) value); // Kiểm tra giá trị để chọn/tích
                checkBox.setHorizontalAlignment(SwingConstants.CENTER); // Canh giữa
                return checkBox;
            }
        });
        tbl_sanPham.getColumnModel().getColumn(10).setMinWidth(0);
        tbl_sanPham.getColumnModel().getColumn(10).setMaxWidth(0);
        tbl_sanPham.getColumnModel().getColumn(10).setPreferredWidth(0);
    }

    public void updateCheckboxValue(int row, int column, boolean value) {
//        tbl_sanPham.setValueAt(value, row, column); // Cập nhật giá trị trong mô hình bảng 
//        DefaultTableModel model = (DefaultTableModel) tbl_sanPham.getModel();
//        model.fireTableCellUpdated(row, column); // Thông báo cho bảng biết rằng dữ liệu đã thay đổi 
//        tbl_sanPham.setModel(model);
    }

    public String getIDKM(int i) throws SQLException {
        for (Model_SPCT x : repo_spct.GetAll_SPCT()) {
            if (tbl_sanPham.getValueAt(i, 0).equals(x.getIDSPCT())) {
                System.out.println(x.getIDKhuyenMai());
                return x.getIDKhuyenMai();
            }
        }
        return "";
    }

    public void filltable_SPCT(ArrayList<Model_SPCT> spct) throws SQLException {
        mol = (DefaultTableModel) tbl_sanPham.getModel();
        mol.setRowCount(0);
        for (Model_SPCT x : spct) {
            mol.addRow(x.toDataRow());
        }
        tbl_sanPham.setModel(mol);
    }

    public void filltable_khuyenMai(ArrayList<Model_khuyenMai> list) throws SQLException {
        mol = (DefaultTableModel) tbl_km.getModel();
        mol.setRowCount(0);
        for (Model_khuyenMai x : list) {
            mol.addRow(x.toDataRow());

        }
        tbl_km.setModel(mol);
    }

    void showData(int index) {
        String ma = tbl_km.getValueAt(index, 1).toString();
        String ten = tbl_km.getValueAt(index, 2).toString();
        String mucGiam = tbl_km.getValueAt(index, 5).toString();
        String ngay = tbl_km.getValueAt(index, 3).toString();
        String ngay1 = tbl_km.getValueAt(index, 4).toString();
        String trangThai = tbl_km.getValueAt(index, 6).toString();
        txt_ma.setText(ma);
        txt_TenCT.setText(ten);
        txt_MucGia.setText(mucGiam);
        txt_TGBatDau.setText(ngay);
        txt_TGKetThuc.setText(ngay1);

        rdo_HĐ.setSelected(false);
        rdo_NgungHĐ.setSelected(false);

        if (trangThai.equals("Đang Hoạt Động")) {
            rdo_HĐ.setSelected(true);
        } else if (trangThai.equals("Không Hoạt Động")) {
            rdo_NgungHĐ.setSelected(true);
        }

    }

    public boolean readform() throws SQLException {
        for (Model_khuyenMai x : repo_khuyenMai.getAll_khuyenMai()) {
            for (int a = 0; a < tbl_km.getRowCount(); a++) {
                String macu = tbl_km.getValueAt(a, 1).toString();
                if (txt_ma.getText().equals(macu)) {
                    JOptionPane.showMessageDialog(this, "Mã khuyến mãi này đã tồn tại");
                    txt_ma.requestFocus();
                    return false;
                }
            }
            if (txt_ma.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã khuyến mại");
                txt_ma.requestFocus();
                return false;
            } else if (txt_ma.getText().length() != 5) {
                JOptionPane.showMessageDialog(this, "Vui lòng 5 ký tự mã khuyến mại");
                txt_ma.requestFocus();
                return false;
            } else if (txt_TenCT.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khuyến mại");
                txt_TenCT.requestFocus();
                return false;
            } else if (txt_MucGia.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Mức giảm giá");
                txt_MucGia.requestFocus();
                return false;
            } else {
                try {

                    int mucGia = Integer.parseInt(txt_MucGia.getText());
                    if (mucGia < 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số tự nhiên.");
                    txt_MucGia.requestFocus();
                    return false;
                }
            }

            // Kiểm tra định dạng ngày bắt đầu (YY-MM-DD)
            String startDateStr = txt_TGBatDau.getText();
            String regex = "^(\\d{4})-(\\d{2})-(\\d{2})$"; // Sửa regex để nhận năm 4 chữ số
            if (!startDateStr.matches(regex)) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng ngày (YYYY-MM-DD) cho Ngày Bắt Đầu.");
                txt_TGBatDau.requestFocus();
                return false;
            }

            // Kiểm tra định dạng ngày kết thúc (YY-MM-DD)
            String endDateStr = txt_TGKetThuc.getText();
            if (!endDateStr.matches(regex)) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng ngày (YYYY-MM-DD) cho Ngày Kết Thúc.");
                txt_TGKetThuc.requestFocus();
                return false;
            }

            // Chuyển chuỗi ngày thành đối tượng LocalDate
            String[] startParts = startDateStr.split("-");
            int startYear = Integer.parseInt(startParts[0]); // Không cần cộng thêm 2000 nếu năm là 4 chữ số
            int startMonth = Integer.parseInt(startParts[1]);
            int startDay = Integer.parseInt(startParts[2]);
            LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);

            String[] endParts = endDateStr.split("-");
            int endYear = Integer.parseInt(endParts[0]);
            int endMonth = Integer.parseInt(endParts[1]);
            int endDay = Integer.parseInt(endParts[2]);
            LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);

            // Kiểm tra xem ngày kết thúc có lớn hơn ngày bắt đầu không
            if (endDate.isBefore(startDate)) {
                JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu.");
                txt_TGKetThuc.requestFocus();
                return false;
            }

        }
        return true;
    }

    public boolean readform_update() throws SQLException {
        for (Model_khuyenMai x : repo_khuyenMai.getAll_khuyenMai()) {
            if (txt_ma.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã khuyến mại");
                txt_ma.requestFocus();
                return false;
            } else if (txt_ma.getText().length() != 5) {
                JOptionPane.showMessageDialog(this, "Vui lòng 5 ký tự mã khuyến mại");
                txt_ma.requestFocus();
                return false;
            } else if (txt_TenCT.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khuyến mại");
                txt_TenCT.requestFocus();
                return false;
            } else if (txt_MucGia.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Mức giảm giá");
                txt_MucGia.requestFocus();
                return false;
            } else {
                try {

                    int mucGia = Integer.parseInt(txt_MucGia.getText());
                    if (mucGia < 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số tự nhiên.");
                    txt_MucGia.requestFocus();
                    return false;
                }
            }

            // Kiểm tra định dạng ngày bắt đầu (YY-MM-DD)
            String startDateStr = txt_TGBatDau.getText();
            String regex = "^(\\d{4})-(\\d{2})-(\\d{2})$"; // Sửa regex để nhận năm 4 chữ số
            if (!startDateStr.matches(regex)) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng ngày (YYYY-MM-DD) cho Ngày Bắt Đầu.");
                txt_TGBatDau.requestFocus();
                return false;
            }

            // Kiểm tra định dạng ngày kết thúc (YY-MM-DD)
            String endDateStr = txt_TGKetThuc.getText();
            if (!endDateStr.matches(regex)) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng ngày (YYYY-MM-DD) cho Ngày Kết Thúc.");
                txt_TGKetThuc.requestFocus();
                return false;
            }

            // Chuyển chuỗi ngày thành đối tượng LocalDate
            String[] startParts = startDateStr.split("-");
            int startYear = Integer.parseInt(startParts[0]); // Không cần cộng thêm 2000 nếu năm là 4 chữ số
            int startMonth = Integer.parseInt(startParts[1]);
            int startDay = Integer.parseInt(startParts[2]);
            LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);

            String[] endParts = endDateStr.split("-");
            int endYear = Integer.parseInt(endParts[0]);
            int endMonth = Integer.parseInt(endParts[1]);
            int endDay = Integer.parseInt(endParts[2]);
            LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);

            // Kiểm tra xem ngày kết thúc có lớn hơn ngày bắt đầu không
            if (endDate.isBefore(startDate)) {
                JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu.");
                txt_TGKetThuc.requestFocus();
                return false;
            }

        }
        return true;
    }

    public boolean checkTT(int i) {
        if (tbl_km.getValueAt(i, 6).equals("Không Hoạt Động")) {
            JOptionPane.showMessageDialog(this, "Khuyến mãi không hoạt động không thể thêm vào sản phẩm.");
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
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_km = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbo_ApDung = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_sanPham = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_TenCT = new javax.swing.JTextField();
        txt_MucGia = new javax.swing.JTextField();
        txt_TGBatDau = new javax.swing.JTextField();
        txt_TGKetThuc = new javax.swing.JTextField();
        rdo_HĐ = new javax.swing.JRadioButton();
        rdo_NgungHĐ = new javax.swing.JRadioButton();
        btn_Luu = new javax.swing.JButton();
        btn_CapNhat = new javax.swing.JButton();
        btn_Moi = new javax.swing.JButton();
        btn_Moi1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txt_ma = new javax.swing.JTextField();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, java.awt.Color.gray));

        jLabel5.setText("Danh sách khuyến mãi");

        tbl_km.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã KM", "Tên KM", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Giảm Giá", "Trạng Thái"
            }
        ));
        tbl_km.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_kmMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_km);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(40, 40, 40)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, java.awt.Color.gray));

        jLabel2.setText("Danh Sách Sản Phẩm");

        jLabel3.setText("Áp dụng cho");

        cbo_ApDung.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lựa chọn", "Nam", "Nữ" }));
        cbo_ApDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_ApDungActionPerformed(evt);
            }
        });

        tbl_sanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Đơn giá", "Số lượng", "Loại dây", "Chất liệu", "Đế giày", "Kích thước", "Màu sắc", "Trạng Thái", "Giảm giá "
            }
        ));
        tbl_sanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_sanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_sanPham);

        jButton1.setText("Reset bảng Danh sách sản phẩm");
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
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbo_ApDung, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbo_ApDung, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, java.awt.Color.gray));

        jLabel1.setText("Danh Sách Khuyến Mại");

        jLabel7.setText("Tên chương trình");

        jLabel8.setText("Mức Giảm Giá");

        jLabel9.setText("Trạng Thái");

        jLabel10.setText("TG Kết Thúc");

        jLabel11.setText("TG Bắt Đầu");

        buttonGroup1.add(rdo_HĐ);
        rdo_HĐ.setSelected(true);
        rdo_HĐ.setText("Đang Hoạt Động");
        rdo_HĐ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_HĐActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdo_NgungHĐ);
        rdo_NgungHĐ.setText("Ngưng Hoạt Động");

        btn_Luu.setText("Thêm khuyến mại");
        btn_Luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LuuActionPerformed(evt);
            }
        });

        btn_CapNhat.setText("Lưu cập nhật");
        btn_CapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhatActionPerformed(evt);
            }
        });

        btn_Moi.setText("Áp dụng khuyến mại");
        btn_Moi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MoiActionPerformed(evt);
            }
        });

        btn_Moi1.setText("RESET");
        btn_Moi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Moi1ActionPerformed(evt);
            }
        });

        jLabel12.setText("Mã khuyến mại");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_TGKetThuc)
                                    .addComponent(txt_TGBatDau)
                                    .addComponent(txt_MucGia)
                                    .addComponent(txt_TenCT)
                                    .addComponent(txt_ma, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rdo_HĐ)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdo_NgungHĐ))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_Luu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btn_CapNhat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_Moi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_Moi1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel7)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel8)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel11)
                        .addGap(39, 39, 39)
                        .addComponent(jLabel10))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txt_ma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(txt_TenCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_MucGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(txt_TGBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(txt_TGKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_HĐ)
                    .addComponent(rdo_NgungHĐ))
                .addGap(18, 18, 18)
                .addComponent(btn_Luu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_CapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Moi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Moi1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_CapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapNhatActionPerformed
        // TODO add your handling code here:
        String maKhuyenMai = txt_ma.getText().trim();
        String tenKhuyenMai = txt_TenCT.getText().trim();
        String ngayBatDau = txt_TGBatDau.getText().trim();
        String ngayKetThuc = txt_TGKetThuc.getText().trim();
        int giamGia = Integer.parseInt(txt_MucGia.getText().trim());

        // Kiểm tra mức giảm giá, nếu không phải là số tự nhiên, hiển thị lỗi
        // Kiểm tra trạng thái
        int trangThai = rdo_HĐ.isSelected() ? 1 : 0;  // Ví dụ nếu có một RadioButton để chọn trạng thái (1 - Active, 0 - Inactive)

        // Tạo đối tượng Model_khuyenMai
        Model_khuyenMai mk = new Model_khuyenMai(maKhuyenMai, tenKhuyenMai, ngayBatDau, ngayKetThuc, giamGia, trangThai);

        // Kiểm tra tính hợp lệ của form
        try {
            if (readform_update()) {  // Kiểm tra các trường dữ liệu (sử dụng phương thức readform() của bạn)
                // Thêm khuyến mại vào cơ sở dữ liệu
                int result = repo_khuyenMai.update_khuyenMai(mk);
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Sửa khuyến mại thành công.");
                    repo_khuyenMai.autoUpdate();
                    repo_khuyenMai.autoUpdate1();
                    // Cập nhật lại bảng sau khi thêm
                    filltable_khuyenMai(repo_khuyenMai.getAll_khuyenMai());
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa khuyến mại thất bại.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm khuyến mại: " + e.getMessage());
        }
    }//GEN-LAST:event_btn_CapNhatActionPerformed

    private void tbl_sanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_sanPhamMouseClicked

    }//GEN-LAST:event_tbl_sanPhamMouseClicked

    private void btn_Moi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Moi1ActionPerformed
        try {
            // TODO add your handling code here:
            filltable_SPCT(repo_spct.GetAll_SPCT());
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            repo_khuyenMai.autoUpdate();
            repo_khuyenMai.autoUpdate1();
            repo_khuyenMai.autoUpdate2();
            filltable_khuyenMai(repo_khuyenMai.getAll_khuyenMai());
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
        txt_ma.setText("");
        txt_TenCT.setText("");
        txt_TGBatDau.setText("");
        txt_TGKetThuc.setText("");
        txt_MucGia.setText("");
    }//GEN-LAST:event_btn_Moi1ActionPerformed

    private void tbl_kmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_kmMouseClicked
        // TODO add your handling code here:
        i = tbl_km.getSelectedRow();
        this.showData(i);
        for (int j = 0; j < tbl_sanPham.getRowCount(); j++) {
            Object a = "";
            try {
                a = getIDKM(j);
                if (tbl_km.getValueAt(i, 1).equals(a)) {
//                updateCheckboxValue(j, 11, true);
                    tbl_sanPham.setValueAt(true, j, 11);
                } else {
//                updateCheckboxValue(j, 11, false);
                    tbl_sanPham.setValueAt(false, j, 11);
                }
            } catch (SQLException ex) {
                Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tbl_kmMouseClicked

    private void btn_LuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LuuActionPerformed
        // TODO add your handling code here:
        String maKhuyenMai = txt_ma.getText().trim();
        String tenKhuyenMai = txt_TenCT.getText().trim();
        String ngayBatDau = txt_TGBatDau.getText().trim();
        String ngayKetThuc = txt_TGKetThuc.getText().trim();
        int giamGia = Integer.parseInt(txt_MucGia.getText().trim());

        // Kiểm tra mức giảm giá, nếu không phải là số tự nhiên, hiển thị lỗi
        // Kiểm tra trạng thái
        int trangThai = rdo_HĐ.isSelected() ? 1 : 0;  // Ví dụ nếu có một RadioButton để chọn trạng thái (1 - Active, 0 - Inactive)

        // Tạo đối tượng Model_khuyenMai
        Model_khuyenMai mk = new Model_khuyenMai(maKhuyenMai, tenKhuyenMai, ngayBatDau, ngayKetThuc, giamGia, trangThai);

        // Kiểm tra tính hợp lệ của form
        try {
            if (readform()) {  // Kiểm tra các trường dữ liệu (sử dụng phương thức readform() của bạn)
                // Thêm khuyến mại vào cơ sở dữ liệu
                int result = repo_khuyenMai.add_khuyenMai(mk);
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm khuyến mại thành công.");
                    // Cập nhật lại bảng sau khi thêm
                    repo_khuyenMai.autoUpdate();
                    repo_khuyenMai.autoUpdate1();
                    filltable_khuyenMai(repo_khuyenMai.getAll_khuyenMai());
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm khuyến mại thất bại.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm khuyến mại: " + e.getMessage());
        }
    }//GEN-LAST:event_btn_LuuActionPerformed

    private void rdo_HĐActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_HĐActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_HĐActionPerformed

    private void btn_MoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MoiActionPerformed
        int index = tbl_km.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dữ liệu trên bảng");
        } else {
            for (int j = 0; j < tbl_sanPham.getRowCount(); j++) {
                Boolean a = (Boolean) tbl_sanPham.getValueAt(j, 11);
                String b = null;
                if (a != null && a.equals(Boolean.TRUE)) {
                    b = "true";
                } else {
                    b = "false";
                }
                if (b.equals("true")) {
                    if (checkTT(index)) {
                        try {
                            repo_khuyenMai.update((String) tbl_km.getValueAt(index, 1), (String) tbl_sanPham.getValueAt(j, 0));
                        } catch (SQLException ex) {
                            Logger.getLogger(KhuyenMai.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "áp dụng thành công");
        }
    }//GEN-LAST:event_btn_MoiActionPerformed

    private void cbo_ApDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_ApDungActionPerformed
        // TODO add your handling code here:
        String a = (String) cbo_ApDung.getSelectedItem();
        if (a.equals("Nam")) {
            try {
                filltable_SPCT(repo_spct.GetAll_SPCT_Nam());
            } catch (SQLException ex) {
                Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (a.equals("Nữ")) {
            try {
                filltable_SPCT(repo_spct.GetAll_SPCT_Nu());
            } catch (SQLException ex) {
                Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cbo_ApDungActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            filltable_SPCT(repo_spct.GetAll_SPCT());
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_CapNhat;
    private javax.swing.JButton btn_Luu;
    private javax.swing.JButton btn_Moi;
    private javax.swing.JButton btn_Moi1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbo_ApDung;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdo_HĐ;
    private javax.swing.JRadioButton rdo_NgungHĐ;
    private javax.swing.JTable tbl_km;
    private javax.swing.JTable tbl_sanPham;
    private javax.swing.JTextField txt_MucGia;
    private javax.swing.JTextField txt_TGBatDau;
    private javax.swing.JTextField txt_TGKetThuc;
    private javax.swing.JTextField txt_TenCT;
    private javax.swing.JTextField txt_ma;
    // End of variables declaration//GEN-END:variables
}
