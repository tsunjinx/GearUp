/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Repository.Global;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author GIGA
 */
public class View_QuanLY extends javax.swing.JFrame {

    /**
     * Creates new form View_QuanLY
     */
    CardLayout cardLayout;

    public View_QuanLY(String IDNV) {
        initComponents();
        this.setLocationRelativeTo(null);
        applyRippleEffectToAllButtons();
        cardLayout = (CardLayout) Cards.getLayout();
        btn_thongKeActionPerformed(null);
    }

    private void applyRippleEffectToAllButtons() {
        replaceButtonsWithRipple(getContentPane());// Start with the content pane
        revalidate();
        repaint();

// Iterate through all components in the content pane
        for (Component component : getContentPane().getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;

                // Replace JButton with RippleButton
                Container parent = button.getParent();
                if (parent != null) {
                    int index = -1;
                    for (int i = 0; i < parent.getComponentCount(); i++) {
                        if (parent.getComponent(i) == button) {
                            index = i;
                            break;
                        }
                    }

                    // Remove the original button and replace it with a RippleButton
                    if (index != -1) {
                        parent.remove(index);
                        RippleButton rippleButton = new RippleButton(button.getText());
                        rippleButton.setBackground(button.getBackground());
                        rippleButton.setForeground(button.getForeground());
                        rippleButton.setFont(button.getFont());
                        parent.add(rippleButton, index);
                    }
                }
            }
        }
        revalidate();
        repaint();
    }

// Recursive method to apply ripple effect to all buttons in a container
    private void replaceButtonsWithRipple(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;

                // Create a RippleButton to replace the JButton
                RippleButton rippleButton = new RippleButton(button.getText());
                rippleButton.setBackground(button.getBackground());
                rippleButton.setForeground(button.getForeground());
                rippleButton.setFont(button.getFont());
                rippleButton.setIcon(button.getIcon());
                rippleButton.setFocusPainted(button.isFocusPainted());

                // Copy ActionListeners from the original button
                for (ActionListener listener : button.getActionListeners()) {
                    rippleButton.addActionListener(listener);
                }

                // Replace the JButton in its parent container
                Container parent = button.getParent();
                if (parent != null) {
                    LayoutManager layout = parent.getLayout();

                    if (layout instanceof javax.swing.GroupLayout) {
                        // Use GroupLayout.replace() directly without removing the button
                        javax.swing.GroupLayout groupLayout = (javax.swing.GroupLayout) layout;
                        groupLayout.replace(button, rippleButton);
                    } else {
                        // Remove and replace the button for other layouts
                        parent.remove(button);
                        if (layout instanceof BorderLayout) {
                            BorderLayout borderLayout = (BorderLayout) layout;
                            Object constraints = borderLayout.getLayoutComponent(button);
                            parent.add(rippleButton, constraints);
                        } else if (layout instanceof FlowLayout || layout instanceof GridLayout) {
                            parent.add(rippleButton); // Add without constraints
                        } else if (layout instanceof GridBagLayout) {
                            System.err.println("Warning: Cannot preserve GridBagLayout constraints.");
                            parent.add(rippleButton); // Add without preserving constraints
                        } else {
                            parent.add(rippleButton); // Default handling
                        }
                    }
                }
            } else if (component instanceof JSplitPane) {
                // Special handling for JSplitPane
                JSplitPane splitPane = (JSplitPane) component;

                // Process the left and right components separately
                Component leftComponent = splitPane.getLeftComponent();
                if (leftComponent instanceof Container) {
                    replaceButtonsWithRipple((Container) leftComponent);
                }

                Component rightComponent = splitPane.getRightComponent();
                if (rightComponent instanceof Container) {
                    replaceButtonsWithRipple((Container) rightComponent);
                }
            } else if (component instanceof Container) {
                // Recursively handle nested containers
                replaceButtonsWithRipple((Container) component);
            }
        }
        container.revalidate(); // Refresh the container's layout
        container.repaint();
    }

    // Custom JButton class with Ripple Effect
    static class RippleButton extends JButton {

        private Point clickPoint;
        private int rippleRadius = 0;
        private Timer rippleTimer;

        public RippleButton(String text) {
            super(text);

            // Add mouse listener for ripple effect
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    clickPoint = e.getPoint();
                    rippleRadius = 0;

                    // Start ripple animation
                    if (rippleTimer != null && rippleTimer.isRunning()) {
                        rippleTimer.stop();
                    }
                    rippleTimer = new Timer(15, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            rippleRadius += 10;
                            if (rippleRadius > getWidth() * 2) {
                                rippleTimer.stop();
                            }
                            repaint();
                        }
                    });
                    rippleTimer.start();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (clickPoint != null) {
                Graphics2D g2d = (Graphics2D) g.create();

                // Set rendering hints for smooth animation
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(255, 255, 255, 80)); // Ripple color with transparency

                // Draw ripple
                g2d.fill(new Ellipse2D.Double(
                        clickPoint.x - rippleRadius / 2.0,
                        clickPoint.y - rippleRadius / 2.0,
                        rippleRadius,
                        rippleRadius
                ));

                g2d.dispose();
            }
        }
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
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        btn_sanPham = new javax.swing.JButton();
        btn_nhanVien = new javax.swing.JButton();
        btn_banHang = new javax.swing.JButton();
        btn_khachHang = new javax.swing.JButton();
        btn_hoaDon = new javax.swing.JButton();
        btn_khuyenMai = new javax.swing.JButton();
        btn_dangXuat = new javax.swing.JButton();
        btn_thongKe = new javax.swing.JButton();
        btn_voucher = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Cards = new javax.swing.JPanel();
        View_DoiMatKhau = new View.DoiMatKhau();
        View_ThongKe = new View.ThongKe();
        try {
            View_banHang = new View.BanHang();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            View_SanPham = new View.SanPham();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        View_NhanVien = new View.NhanVien();
        try {
            View_HoaDon = new View.HoaDon();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            khuyenMai1 = new View.KhuyenMai();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        try {
            View_Voucher = new View.View_Voucher();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        View_KhachHang = new View.khachHang();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(980, 1280));

        jSplitPane1.setMaximumSize(new java.awt.Dimension(980, 1280));
        jSplitPane1.setMinimumSize(new java.awt.Dimension(980, 1280));

        jPanel1.setBackground(new java.awt.Color(250, 249, 246));

        btn_sanPham.setBackground(new java.awt.Color(99, 198, 108));
        btn_sanPham.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        btn_sanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/products_1312307.png"))); // NOI18N
        btn_sanPham.setText("Sản Phẩm");
        btn_sanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sanPhamActionPerformed(evt);
            }
        });

        btn_nhanVien.setBackground(new java.awt.Color(99, 198, 108));
        btn_nhanVien.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        btn_nhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/team_924520.png"))); // NOI18N
        btn_nhanVien.setText("Nhân Viên");
        btn_nhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nhanVienActionPerformed(evt);
            }
        });

        btn_banHang.setBackground(new java.awt.Color(99, 198, 108));
        btn_banHang.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        btn_banHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/store-manager_18303087.png"))); // NOI18N
        btn_banHang.setText("Bán Hàng");
        btn_banHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_banHangActionPerformed(evt);
            }
        });

        btn_khachHang.setBackground(new java.awt.Color(99, 198, 108));
        btn_khachHang.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        btn_khachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/target_1605350.png"))); // NOI18N
        btn_khachHang.setText("Khách Hàng");
        btn_khachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_khachHangActionPerformed(evt);
            }
        });

        btn_hoaDon.setBackground(new java.awt.Color(99, 198, 108));
        btn_hoaDon.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        btn_hoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/receipt_2522929.png"))); // NOI18N
        btn_hoaDon.setText("Hóa Đơn");
        btn_hoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hoaDonActionPerformed(evt);
            }
        });

        btn_khuyenMai.setBackground(new java.awt.Color(99, 198, 108));
        btn_khuyenMai.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        btn_khuyenMai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/online-shop_567699.png"))); // NOI18N
        btn_khuyenMai.setText("Khuyến Mãi");
        btn_khuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_khuyenMaiActionPerformed(evt);
            }
        });

        btn_dangXuat.setBackground(new java.awt.Color(99, 198, 108));
        btn_dangXuat.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        btn_dangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit_3094700.png"))); // NOI18N
        btn_dangXuat.setText("Đăng Xuất");
        btn_dangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dangXuatActionPerformed(evt);
            }
        });

        btn_thongKe.setBackground(new java.awt.Color(99, 198, 108));
        btn_thongKe.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        btn_thongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrows_5080304.png"))); // NOI18N
        btn_thongKe.setText("Thống Kê");
        btn_thongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thongKeActionPerformed(evt);
            }
        });

        btn_voucher.setBackground(new java.awt.Color(99, 198, 108));
        btn_voucher.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        btn_voucher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/voucher_3837136.png"))); // NOI18N
        btn_voucher.setText("Voucher");
        btn_voucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_voucherActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo_to.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_voucher, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_khuyenMai, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_hoaDon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_khachHang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_banHang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_nhanVien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_thongKe, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_sanPham, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_dangXuat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101)
                .addComponent(btn_thongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_sanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_nhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_banHang, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_khachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_hoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_khuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_voucher, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_dangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(575, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(jPanel1);

        Cards.setLayout(new java.awt.CardLayout());
        Cards.add(View_DoiMatKhau, "View_DoiMatKhau");

        View_ThongKe.setMinimumSize(new java.awt.Dimension(1412, 832));
        Cards.add(View_ThongKe, "View_ThongKe");
        Cards.add(View_banHang, "View_banHang");
        Cards.add(View_SanPham, "View_SanPham");
        Cards.add(View_NhanVien, "View_NhanVien");
        Cards.add(View_HoaDon, "View_HoaDon");
        Cards.add(khuyenMai1, "View_KhuyenMai");

        View_Voucher.setToolTipText("");
        Cards.add(View_Voucher, "View_Voucher");
        Cards.add(View_KhachHang, "View_KhachHang");

        jSplitPane1.setRightComponent(Cards);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1799, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_sanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sanPhamActionPerformed
        // TODO add your handling code here:
        if (Global.employeeRole.equals(true)) {
            cardLayout.show(Cards, "View_SanPham");
        } else {
            JOptionPane.showMessageDialog(this, "Nhân viên không có quyền hạn xem mục này!!");
        }
    }//GEN-LAST:event_btn_sanPhamActionPerformed

    private void btn_nhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nhanVienActionPerformed
        // TODO add your handling code here:
        if (Global.employeeRole.equals(true)) {
            cardLayout.show(Cards, "View_NhanVien");
        } else {
            JOptionPane.showMessageDialog(this, "Nhân viên không có quyền hạn xem mục này!!");
        }
    }//GEN-LAST:event_btn_nhanVienActionPerformed

    private void btn_banHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_banHangActionPerformed
        // TODO add your handling code here:
        cardLayout.show(Cards, "View_banHang");
    }//GEN-LAST:event_btn_banHangActionPerformed

    private void btn_khachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_khachHangActionPerformed
        // TODO add your handling code here:
        cardLayout.show(Cards, "View_KhachHang");
    }//GEN-LAST:event_btn_khachHangActionPerformed

    private void btn_hoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hoaDonActionPerformed
        // TODO add your handling code here:
        cardLayout.show(Cards, "View_HoaDon");
    }//GEN-LAST:event_btn_hoaDonActionPerformed

    private void btn_khuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_khuyenMaiActionPerformed
        // TODO add your handling code here:
        if (Global.employeeRole.equals(true)) {
            cardLayout.show(Cards, "View_KhuyenMai");
        } else {
            JOptionPane.showMessageDialog(this, "Nhân viên không có quyền hạn xem mục này!!");
        }
    }//GEN-LAST:event_btn_khuyenMaiActionPerformed

    private void btn_dangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dangXuatActionPerformed
        // TODO add your handling code here:
        int i = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất không?");
        if (i == 0) {
            dispose();
        }
    }//GEN-LAST:event_btn_dangXuatActionPerformed

    private void btn_thongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thongKeActionPerformed
        // TODO add your handling code here:
        cardLayout.show(Cards, "View_ThongKe");
    }//GEN-LAST:event_btn_thongKeActionPerformed

    private void btn_voucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_voucherActionPerformed
        // TODO add your handling code here:
        if (Global.employeeRole.equals(true)) {
            cardLayout.show(Cards, "View_Voucher");
        } else {
            JOptionPane.showMessageDialog(this, "Nhân viên không có quyền hạn xem mục này!!");
        }
    }//GEN-LAST:event_btn_voucherActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Cards;
    private View.DoiMatKhau View_DoiMatKhau;
    private View.HoaDon View_HoaDon;
    private View.khachHang View_KhachHang;
    private View.NhanVien View_NhanVien;
    private View.SanPham View_SanPham;
    private View.ThongKe View_ThongKe;
    private View.View_Voucher View_Voucher;
    private View.BanHang View_banHang;
    private javax.swing.JButton btn_banHang;
    private javax.swing.JButton btn_dangXuat;
    private javax.swing.JButton btn_hoaDon;
    private javax.swing.JButton btn_khachHang;
    private javax.swing.JButton btn_khuyenMai;
    private javax.swing.JButton btn_nhanVien;
    private javax.swing.JButton btn_sanPham;
    private javax.swing.JButton btn_thongKe;
    private javax.swing.JButton btn_voucher;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSplitPane jSplitPane1;
    private View.KhuyenMai khuyenMai1;
    // End of variables declaration//GEN-END:variables
}
