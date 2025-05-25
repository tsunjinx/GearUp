/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;
import Model.Model_thongke;
import Model.Model_HoaDon;
import Model.Model_SanPham;
import Repository.Repository_BanHang;
import Model.Model_HoaDon;
import Model.Model_HoaDonChiTiet;
import Repository.Responsitory_thongKe;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PonYanki
 */
public class ThongKe extends javax.swing.JPanel {
   DefaultTableModel dtm = new DefaultTableModel();
   Responsitory_thongKe repo_tk = new Responsitory_thongKe();
   private int i = -1;
    
   
    /**
     * Creates new form SanPham
     */
    public ThongKe() {
        initComponents();
        txt_thanhcong.setEditable(false);
        txt_nam.setEditable(false);
         txt_huy.setEditable(false);
       txt_doanhSo.setEditable(false);
        cbo_nam.removeAllItems();
        cbo_nam.addItem("2023");
        cbo_nam.addItem("2024");
        cbo_nam.addItem("2025");
        cbo_nam.setSelectedItem("2024");
         txt_nam.setText(cbo_nam.getSelectedItem().toString());     
        fillTable(repo_tk.getAll(Integer.parseInt(cbo_nam.getSelectedItem().toString())));
       fillTable(repo_tk.getAll(2024)); // Doanh thu mặc định cho năm 2024
       cbo_namHuy.removeAllItems();
        cbo_namHuy.addItem("2023");
        cbo_namHuy.addItem("2024");
        cbo_namHuy.addItem("2025");
        cbo_namHuy.setSelectedItem("2024");
       fillTable_huy(repo_tk.getAll_huy(2024));
        fillTable_homNay(repo_tk.getAll_homNay());
        // Gọi updateTotalRevenue để tính tổng doanh thu ngay khi ứng dụng chạy
        updateTotalRevenue(); // Cập nhật tổng doanh thu cho năm 2024

        // Lắng nghe sự kiện chọn năm trong combo box
        cbo_nam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cập nhật năm vào textField
                String selectedYear = (String) cbo_nam.getSelectedItem();
                txt_nam.setText(selectedYear); // Cập nhật nội dung JTextField

                // Lấy năm được chọn và cập nhật bảng
                int selectedYearInt = Integer.parseInt(selectedYear);
                fillTable(repo_tk.getAll(selectedYearInt)); // Cập nhật bảng khi chọn năm

                // Cập nhật doanh thu khi năm thay đổi
                updateTotalRevenue();
            }
        });

        // Khởi tạo DefaultTableModel cho bảng (table phải được khai báo và khởi tạo trước)
        DefaultTableModel model = (DefaultTableModel) tbl_doanhThutheoNam.getModel();
        
        // Lắng nghe sự thay đổi trong bảng và tính tổng doanh thu
        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                // Tính tổng doanh thu sau khi bảng thay đổi
                updateTotalRevenue();
            }
        });
        
        
        
        
        
        
         cbo_namHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cập nhật năm vào textField
                String selectedYear1 = (String) cbo_namHuy.getSelectedItem();
                txt_nam.setText(selectedYear1); // Cập nhật nội dung JTextField

                // Lấy năm được chọn và cập nhật bảng
                int selectedYearInt1 = Integer.parseInt(selectedYear1);
                fillTable_huy(repo_tk.getAll_huy(selectedYearInt1)); // Cập nhật bảng khi chọn năm

                // Cập nhật doanh thu khi năm thay đổi
                updateTotalRevenue();
            }
        });

        // Khởi tạo DefaultTableModel cho bảng (table phải được khai báo và khởi tạo trước)
        DefaultTableModel model1 = (DefaultTableModel) tbl_banghuy1.getModel();
        
        // Lắng nghe sự thay đổi trong bảng và tính tổng doanh thu
        model1.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                // Tính tổng doanh thu sau khi bảng thay đổi
                updateTotalRevenue();
            }
        });
        
        // Khởi tạo DefaultTableModel cho bảng (table phải được khai báo và khởi tạo trước)
        DefaultTableModel model_homNay = (DefaultTableModel) tbl_homnay.getModel();
        
        // Lắng nghe sự thay đổi trong bảng và tính tổng doanh thu
        model_homNay.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                // Tính tổng doanh thu sau khi bảng thay đổi
                updateTotalRevenu_homNay();
            }
        });
        
        DefaultTableModel model_thang = (DefaultTableModel) tbl_thang.getModel();
        
        // Lắng nghe sự thay đổi trong bảng và tính tổng doanh thu
        model_thang.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                // Tính tổng doanh thu sau khi bảng thay đổi
                updateTotalRevenu_thang();
            }
        });
        
        
        tbl_doanhThutheoNam.getColumnModel().getColumn(0).setMinWidth(0);
        tbl_doanhThutheoNam.getColumnModel().getColumn(0).setMaxWidth(0);
        tbl_doanhThutheoNam.getColumnModel().getColumn(0).setPreferredWidth(0);
        
        
        
        tbl_doanhThutheoNam.getColumnModel().getColumn(5).setMinWidth(0);
        tbl_doanhThutheoNam.getColumnModel().getColumn(5).setMaxWidth(0);
        tbl_doanhThutheoNam.getColumnModel().getColumn(5).setPreferredWidth(0);
        
        
         tbl_banghuy1.getColumnModel().getColumn(0).setMinWidth(0);
        tbl_banghuy1.getColumnModel().getColumn(0).setMaxWidth(0);
        tbl_banghuy1.getColumnModel().getColumn(0).setPreferredWidth(0);
        
        
        
        tbl_banghuy1.getColumnModel().getColumn(5).setMinWidth(0);
        tbl_banghuy1.getColumnModel().getColumn(5).setMaxWidth(0);
        tbl_banghuy1.getColumnModel().getColumn(5).setPreferredWidth(0);
        
        
        
        tbl_homnay.getColumnModel().getColumn(0).setMinWidth(0);
        tbl_homnay.getColumnModel().getColumn(0).setMaxWidth(0);
        tbl_homnay.getColumnModel().getColumn(0).setPreferredWidth(0);
        
         tbl_thang.getColumnModel().getColumn(0).setMinWidth(0);
        tbl_thang.getColumnModel().getColumn(0).setMaxWidth(0);
        tbl_thang.getColumnModel().getColumn(0).setPreferredWidth(0);
        txt_thanhCongHomNay.setEditable(false);
        txt_huyHomNay.setEditable(false);
        txt_doanhThuHomNay.setEditable(false);
        txt_doanhThuHomNay.setEditable(false);
        
        txt_thang.setEditable(false);
        txt_thang1.setEditable(false);
        txt_thang2.setEditable(false);
        txt_thang3.setEditable(false);
        
        updateTotalRevenu_homNay();
         LocalDate today = LocalDate.now();
        // Định dạng ngày (yyyy-MM-dd)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // Gán giá trị cho JTextField
        txt_homNay.setText(today.format(formatter));
        
        cbo_nam.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Lấy giá trị được chọn từ cbo1
        String selectedValue = (String) cbo_nam.getSelectedItem();
        
        // Đặt giá trị tương ứng cho cbo2
        cbo_namHuy.setSelectedItem(selectedValue);
    }
});
                cbo_namHuy.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Lấy giá trị được chọn từ cbo1
        String selectedValue = (String) cbo_namHuy.getSelectedItem();
        
        // Đặt giá trị tương ứng cho cbo2
        cbo_nam.setSelectedItem(selectedValue);
    }
});
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        // Chuyển sang tab "Doanh thu theo năm"
        tbl_donHuy.setSelectedIndex(0); // Giả sử tab "Doanh thu theo năm" ở vị trí index 0
    }
});
           jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        // Chuyển sang tab "Doanh thu theo năm"
        tbl_donHuy.setSelectedIndex(2); // Giả sử tab "Doanh thu theo năm" ở vị trí index 0
    }
});    
                      jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        // Chuyển sang tab "Doanh thu theo năm"
        tbl_donHuy.setSelectedIndex(3); // Giả sử tab "Doanh thu theo năm" ở vị trí index 0
    }
});   
                                            jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        // Chuyển sang tab "Doanh thu theo năm"
        tbl_donHuy.setSelectedIndex(4); // Giả sử tab "Doanh thu theo năm" ở vị trí index 0
    }
});  
                
                cbo_thang.removeAllItems();
                cbo_thang.addItem("1");
                cbo_thang.addItem("2");
                cbo_thang.addItem("3");
                cbo_thang.addItem("4");
                cbo_thang.addItem("5");
                cbo_thang.addItem("6");
                cbo_thang.addItem("7");
                cbo_thang.addItem("8");
                cbo_thang.addItem("9");
                cbo_thang.addItem("10");
                cbo_thang.addItem("11");
                cbo_thang.addItem("12");
                fillTable_thang(repo_tk.getAll_theoThang(Integer.parseInt(cbo_thang.getSelectedItem().toString())));
                setMonthToCurrent();
                cbo_thang.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Lấy giá trị được chọn từ cbo_thang
        String selectedMonth = cbo_thang.getSelectedItem().toString();

        // Cập nhật giá trị cho txt_thang
        txt_thang.setText(selectedMonth);
    }
});
                txt_thang.addKeyListener(new KeyAdapter() {
    @Override
    public void keyReleased(KeyEvent e) {
        try {
            // Lấy giá trị từ txt_thang
            int month = Integer.parseInt(txt_thang.getText());

            // Kiểm tra giá trị có hợp lệ (1-12) không
            if (month >= 1 && month <= 12) {
                cbo_thang.setSelectedItem(String.valueOf(month));
            }
        } catch (NumberFormatException ex) {
            // Nếu không phải số hợp lệ, bỏ qua
        }
    }
});
           setInitialMonth();     
           
           
           cbo_thang.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Lấy tháng đã chọn từ cbo_thang
        int selectedMonth = Integer.parseInt(cbo_thang.getSelectedItem().toString());

        // Gọi phương thức getAll_theoThang để lấy dữ liệu theo tháng
        ArrayList<Model_thongke> list = repo_tk.getAll_theoThang(selectedMonth);

        // Điền dữ liệu vào bảng tbl_thang
        fillTable_thang(list);

        // Cập nhật tổng doanh thu và các thống kê khác
        updateTotalRevenu_thang();
    }
});
       
       int currentMonth = LocalDate.now().getMonthValue(); // Tháng hiện tại (1 - 12)

        // Thiết lập giá trị của cbo_thang là tháng hiện tại
        cbo_thang.setSelectedItem(currentMonth);

        // Cập nhật bảng với dữ liệu của tháng hiện tại
        updateTableForCurrentMonth(currentMonth);
       
           
           
           
           
           fillTable_tim(repo_tk.getAll_tatca());
           
           
           
    }
    
        public void updateTableForCurrentMonth(int month) {
        // Lấy dữ liệu hóa đơn cho tháng đã chọn
        ArrayList<Model_thongke> list = repo_tk.getAll_theoThang(month);

        // Điền dữ liệu vào bảng tbl_thang
        fillTable_thang(list);

        // Cập nhật tổng doanh thu và các thông tin khác
        updateTotalRevenu_thang();
    }
    
      private void setInitialMonth() {
        LocalDate today = LocalDate.now(); // Lấy ngày hiện tại
        int currentMonth = today.getMonthValue(); // Lấy tháng hiện tại (1-12)

        cbo_thang.setSelectedItem(String.valueOf(currentMonth)); // Đặt giá trị cho ComboBox
        txt_thang.setText(String.valueOf(currentMonth)); // Đồng bộ txt_thang với cbo_thang
    }
     private void setMonthToCurrent() {
        LocalDate today = LocalDate.now();
        int currentMonth = today.getMonthValue();
        txt_thang.setText(String.valueOf(currentMonth)); // Đặt giá trị vào txt_thang
    }
    
    // Phương thức tính tổng doanh thu
    private void updateTotalRevenue() {
        int tongTien = 0;
        int columnIndex = 4; // Cột "Số Tiền" có index là 2

        // Lặp qua tất cả các dòng trong bảng và cộng tổng số tiền
        for (int i = 0; i < tbl_doanhThutheoNam.getRowCount(); i++) {
            Object value = tbl_doanhThutheoNam.getValueAt(i, columnIndex); // Lấy giá trị từ cột "Số Tiền"
            if (value != null && value instanceof Number) {
                tongTien += ((Number) value).doubleValue();
            }
        }

        // Cập nhật tổng vào JTextField txt_doanhSo
        txt_doanhSo.setText(" " + tongTien+" VNĐ");
        
        int TongHuy = 0;
        for (int i = 0; i < tbl_banghuy1.getRowCount(); i++) {
            TongHuy++;
        }
        txt_huy.setText(" " + TongHuy);
        
        
        int Tongthanhtoan = 0;
        for (int i = 0; i < tbl_doanhThutheoNam.getRowCount(); i++) {
            Tongthanhtoan++;
        }
        txt_thanhcong.setText(" " + Tongthanhtoan);
    
        
        
    }
    
    private void updateTotalRevenu_homNay() {
        int tongTien = 0;
        int columnIndex = 4; // Cột "Số Tiền" có index là 2

        // Lặp qua tất cả các dòng trong bảng và cộng tổng số tiền
        for (int i = 0; i < tbl_homnay.getRowCount(); i++) {
            Object value = tbl_homnay.getValueAt(i, columnIndex); // Lấy giá trị từ cột "Số Tiền"
            if (value != null && value instanceof Number) {
                tongTien += ((Number) value).doubleValue();
            }
        }

        // Cập nhật tổng vào JTextField txt_doanhSo
        txt_doanhThuHomNay.setText(" " + tongTien+" VNĐ");
        int col = 5 ;
        int TongHuy = 0;
        for (int i = 0; i < tbl_homnay.getRowCount(); i++) {
            if(tbl_homnay.getValueAt(i, col).toString().equalsIgnoreCase("Đã hủy")){
                TongHuy++;
            }
            
        }
        txt_huyHomNay.setText(" " + TongHuy);
        
        
        int Tongthanhtoan = 0;
        for (int i = 0; i < tbl_homnay.getRowCount(); i++) {
            if(tbl_homnay.getValueAt(i, col).toString().equalsIgnoreCase("Đã Thanh Toán")){
                Tongthanhtoan++;
            }
        }
        txt_thanhCongHomNay.setText(" " + Tongthanhtoan);

        
    }
    
    private void updateTotalRevenu_thang() {
        int tongTien = 0;
        int columnIndex = 4; // Cột "Số Tiền" có index là 2

        // Lặp qua tất cả các dòng trong bảng và cộng tổng số tiền
        for (int i = 0; i < tbl_thang.getRowCount(); i++) {
            Object value = tbl_thang.getValueAt(i, columnIndex); // Lấy giá trị từ cột "Số Tiền"
            if (value != null && value instanceof Number) {
                tongTien += ((Number) value).doubleValue();
            }
        }

        // Cập nhật tổng vào JTextField txt_doanhSo
        txt_thang3.setText(" " + tongTien+" VNĐ");
        int col = 5 ;
        int TongHuy = 0;
        for (int i = 0; i < tbl_thang.getRowCount(); i++) {
            if(tbl_thang.getValueAt(i, col).toString().equalsIgnoreCase("Đã hủy")){
                TongHuy++;
            }
            
        }
        txt_thang2.setText(" " + TongHuy);
        
        
        int Tongthanhtoan = 0;
        for (int i = 0; i < tbl_thang.getRowCount(); i++) {
            if(tbl_thang.getValueAt(i, col).toString().equalsIgnoreCase("Đã Thanh Toán")){
                Tongthanhtoan++;
            }
        }
        txt_thang1.setText(" " + Tongthanhtoan);

        
    }
      
        
        public void fillTable_homNay(ArrayList<Model_thongke> list) {
        dtm = (DefaultTableModel) tbl_homnay.getModel();
        dtm.setRowCount(0);
        for (Model_thongke x : list) {
            dtm.addRow(x.toDataRow());
        }
        tbl_homnay.setModel(dtm);
    }

    public void fillTable_tim(ArrayList<Model_thongke> list) {
    DefaultTableModel dtm = (DefaultTableModel) tbl_tim.getModel();
    dtm.setRowCount(0);  // Xóa các hàng cũ trong bảng
    for (Model_thongke x : list) {
        dtm.addRow(x.toDataRow());  // Thêm từng dòng dữ liệu vào bảng
    }
    tbl_tim.setModel(dtm);
}
    public void fillTable(ArrayList<Model_thongke> list) {
        dtm = (DefaultTableModel) tbl_doanhThutheoNam.getModel();
        dtm.setRowCount(0);
        for (Model_thongke x : list) {
            dtm.addRow(x.toDataRow());
        }
        tbl_doanhThutheoNam.setModel(dtm);
    }
    
    public void fillTable_huy(ArrayList<Model_thongke> list) {
        dtm = (DefaultTableModel) tbl_banghuy1.getModel();
        dtm.setRowCount(0);
        for (Model_thongke x : list) {
            dtm.addRow(x.toDataRow());
        }
        tbl_banghuy1.setModel(dtm);
    }
        public void fillTable_thang(ArrayList<Model_thongke> list) {
        dtm = (DefaultTableModel) tbl_thang.getModel();
        dtm.setRowCount(0);
        for (Model_thongke x : list) {
            dtm.addRow(x.toDataRow());
        }
        tbl_thang.setModel(dtm);
    }
    public boolean readForm() {
    String startDateStr = txt_startDate.getText(); // Lấy ngày bắt đầu từ TextField
    String endDateStr = txt_endDate.getText(); // Lấy ngày kết thúc từ TextField
    
    // Định dạng ngày chuẩn (yyyy-MM-dd)
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    try {
        // Kiểm tra định dạng ngày bắt đầu và kết thúc
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);

        // Kiểm tra xem ngày bắt đầu có bé hơn ngày kết thúc không
        if (startDate.isAfter(endDate)) {
            JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải nhỏ hơn ngày kết thúc.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Nếu tất cả đúng, trả về true
        return true;
    } catch (Exception e) {
        // Nếu định dạng ngày sai, thông báo lỗi
        JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng ngày (YYYY-MM-DD).", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return false;
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lable_doanhThu = new javax.swing.JLabel();
        txt_doanhSo = new javax.swing.JTextField();
        txt_nam = new javax.swing.JTextField();
        lable_doanhThu1 = new javax.swing.JLabel();
        txt_huy = new javax.swing.JTextField();
        txt_thanhcong = new javax.swing.JTextField();
        lable_doanhThu2 = new javax.swing.JLabel();
        tbl_donHuy = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cbo_nam = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_doanhThutheoNam = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_banghuy1 = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbo_namHuy = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_homnay = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_thang = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        cbo_thang = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_tim = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txt_homNay = new javax.swing.JTextField();
        lable_doanhThu9 = new javax.swing.JLabel();
        txt_thanhCongHomNay = new javax.swing.JTextField();
        lable_doanhThu10 = new javax.swing.JLabel();
        txt_huyHomNay = new javax.swing.JTextField();
        lable_doanhThu11 = new javax.swing.JLabel();
        txt_doanhThuHomNay = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txt_startDate = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_endDate = new javax.swing.JTextField();
        btn_baoCao = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        lable_doanhThu14 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lable_doanhThu12 = new javax.swing.JLabel();
        lable_doanhThu13 = new javax.swing.JLabel();
        txt_thang = new javax.swing.JTextField();
        txt_thang1 = new javax.swing.JTextField();
        txt_thang2 = new javax.swing.JTextField();
        txt_thang3 = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Doanh thu năm ");

        lable_doanhThu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lable_doanhThu.setForeground(new java.awt.Color(255, 255, 255));
        lable_doanhThu.setText("Doanh thu:");

        txt_doanhSo.setBackground(new java.awt.Color(51, 204, 255));
        txt_doanhSo.setForeground(new java.awt.Color(255, 255, 255));
        txt_doanhSo.setBorder(null);

        txt_nam.setBackground(new java.awt.Color(51, 204, 255));
        txt_nam.setForeground(new java.awt.Color(255, 255, 255));
        txt_nam.setBorder(null);
        txt_nam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namActionPerformed(evt);
            }
        });

        lable_doanhThu1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lable_doanhThu1.setForeground(new java.awt.Color(255, 255, 255));
        lable_doanhThu1.setText("Đơn hủy");

        txt_huy.setBackground(new java.awt.Color(51, 204, 255));
        txt_huy.setForeground(new java.awt.Color(255, 255, 255));
        txt_huy.setBorder(null);

        txt_thanhcong.setBackground(new java.awt.Color(51, 204, 255));
        txt_thanhcong.setForeground(new java.awt.Color(255, 255, 255));
        txt_thanhcong.setBorder(null);

        lable_doanhThu2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lable_doanhThu2.setForeground(new java.awt.Color(255, 255, 255));
        lable_doanhThu2.setText("Đơn hàng thành công");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nam, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lable_doanhThu2)
                                .addGap(4, 4, 4)
                                .addComponent(txt_thanhcong, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lable_doanhThu1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lable_doanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_doanhSo, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(txt_huy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nam, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lable_doanhThu2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_thanhcong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lable_doanhThu1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_huy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lable_doanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_doanhSo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Năm");

        cbo_nam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tbl_doanhThutheoNam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã hóa đơn", "Mã khách hàng", "Ngày Mua Hàng", "Tổng Tiền", "Trạng Thái"
            }
        ));
        jScrollPane1.setViewportView(tbl_doanhThutheoNam);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Chi tiết doanh thu");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cbo_nam, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(303, 303, 303)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11)
                    .addComponent(cbo_nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tbl_donHuy.addTab("Doanh thu theo năm", jPanel2);

        tbl_banghuy1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Mã Khách hàng", "Ngày Mua Hàng", "Tổng tiền", "Trạng thái"
            }
        ));
        jScrollPane2.setViewportView(tbl_banghuy1);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("Thông tin chi tiết sản phẩm");

        jLabel2.setText("Theo năm");

        cbo_namHuy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1068, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cbo_namHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(171, 171, 171)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(121, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbo_namHuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(29, 29, 29)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tbl_donHuy.addTab("Đơn hủy theo năm", jPanel3);

        tbl_homnay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Mã Khách hàng", "Ngày Mua Hàng", "Tổng tiền", "Trạng thái"
            }
        ));
        jScrollPane3.setViewportView(tbl_homnay);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Thông kê doanh số hôm nay");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(453, 453, 453)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(131, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tbl_donHuy.addTab(" Thống kê ngày hôm nay", jPanel7);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("Thông kê doanh số tháng");

        tbl_thang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Mã Khách hàng", "Ngày Mua Hàng", "Tổng tiền", "Trạng thái"
            }
        ));
        jScrollPane4.setViewportView(tbl_thang);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setText("Tháng ");

        cbo_thang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(cbo_thang, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(303, 303, 303)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10)
                    .addComponent(cbo_thang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 27, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbl_donHuy.addTab("Thống kê theo tháng", jPanel8);

        tbl_tim.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã hóa đơn", "Mã Khách hàng", "Ngày Mua hàng", "Thành tiền", "Trạnh thái"
            }
        ));
        jScrollPane5.setViewportView(tbl_tim);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 38, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tbl_donHuy.addTab("Tìm kiếm", jPanel4);

        jPanel9.setBackground(new java.awt.Color(255, 0, 0));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Doanh theo ngày");

        txt_homNay.setBackground(new java.awt.Color(255, 0, 0));
        txt_homNay.setForeground(new java.awt.Color(255, 255, 255));
        txt_homNay.setBorder(null);

        lable_doanhThu9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lable_doanhThu9.setForeground(new java.awt.Color(255, 255, 255));
        lable_doanhThu9.setText("Đơn hàng thành công");

        txt_thanhCongHomNay.setBackground(new java.awt.Color(255, 0, 0));
        txt_thanhCongHomNay.setForeground(new java.awt.Color(255, 255, 255));
        txt_thanhCongHomNay.setBorder(null);

        lable_doanhThu10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lable_doanhThu10.setForeground(new java.awt.Color(255, 255, 255));
        lable_doanhThu10.setText("Đơn hủy");

        txt_huyHomNay.setBackground(new java.awt.Color(255, 0, 0));
        txt_huyHomNay.setForeground(new java.awt.Color(255, 255, 255));
        txt_huyHomNay.setBorder(null);

        lable_doanhThu11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lable_doanhThu11.setForeground(new java.awt.Color(255, 255, 255));
        lable_doanhThu11.setText("Doanh thu:");

        txt_doanhThuHomNay.setBackground(new java.awt.Color(255, 0, 0));
        txt_doanhThuHomNay.setForeground(new java.awt.Color(255, 255, 255));
        txt_doanhThuHomNay.setBorder(null);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addComponent(txt_homNay, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(lable_doanhThu9)
                        .addGap(4, 4, 4)
                        .addComponent(txt_thanhCongHomNay, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lable_doanhThu10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lable_doanhThu11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txt_huyHomNay, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_doanhThuHomNay, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_homNay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lable_doanhThu9, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_thanhCongHomNay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lable_doanhThu10, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_huyHomNay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lable_doanhThu11, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_doanhThuHomNay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        jPanel10.setBackground(new java.awt.Color(204, 204, 204));

        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Tìm kiếm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setText("Bắt đầu từ ngày");

        jLabel6.setText("Đến ngày");

        txt_endDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_endDateActionPerformed(evt);
            }
        });

        btn_baoCao.setText("Báo cáo ");
        btn_baoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_baoCaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btn_baoCao)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_endDate, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                .addComponent(txt_startDate, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_startDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_endDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_baoCao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        jPanel6.setBackground(new java.awt.Color(255, 204, 204));

        lable_doanhThu14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lable_doanhThu14.setForeground(new java.awt.Color(255, 255, 255));
        lable_doanhThu14.setText("Doanh thu:");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Doanh theo tháng");

        lable_doanhThu12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lable_doanhThu12.setForeground(new java.awt.Color(255, 255, 255));
        lable_doanhThu12.setText("Đơn hàng thành công");

        lable_doanhThu13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lable_doanhThu13.setForeground(new java.awt.Color(255, 255, 255));
        lable_doanhThu13.setText("Đơn hủy");

        txt_thang.setBackground(new java.awt.Color(255, 204, 204));
        txt_thang.setBorder(null);

        txt_thang1.setEditable(false);
        txt_thang1.setBackground(new java.awt.Color(255, 204, 204));
        txt_thang1.setBorder(null);

        txt_thang2.setBackground(new java.awt.Color(255, 204, 204));
        txt_thang2.setBorder(null);

        txt_thang3.setBackground(new java.awt.Color(255, 204, 204));
        txt_thang3.setBorder(null);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lable_doanhThu13)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_thang, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(lable_doanhThu12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_thang1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(txt_thang2)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lable_doanhThu14, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_thang3, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_thang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lable_doanhThu12, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_thang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lable_doanhThu13, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_thang2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lable_doanhThu14, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_thang3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(tbl_donHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbl_donHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_namActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txt_namActionPerformed

    private void txt_endDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_endDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_endDateActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        fillTable_homNay(repo_tk.getAll_homNay());
        fillTable_thang(repo_tk.getAll_theoThang(Integer.parseInt(cbo_thang.getSelectedItem().toString())));
        fillTable_huy(repo_tk.getAll_huy(Integer.parseInt(cbo_namHuy.getSelectedItem().toString())));
        fillTable(repo_tk.getAll(Integer.parseInt(cbo_nam.getSelectedItem().toString())));
        fillTable_tim(repo_tk.getAll_tatca());
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
  
     if(readForm()){
         String start = txt_startDate.getText();
         String end = txt_endDate.getText();
         if(repo_tk.getAll_tim(start, end).isEmpty()){
             JOptionPane.showMessageDialog(this, "không tìm thấy");
     }
         else {
             fillTable_tim(repo_tk.getAll_tim(start, end));
         }
     }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_baoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_baoCaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_baoCaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_baoCao;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbo_nam;
    private javax.swing.JComboBox<String> cbo_namHuy;
    private javax.swing.JComboBox<String> cbo_thang;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lable_doanhThu;
    private javax.swing.JLabel lable_doanhThu1;
    private javax.swing.JLabel lable_doanhThu10;
    private javax.swing.JLabel lable_doanhThu11;
    private javax.swing.JLabel lable_doanhThu12;
    private javax.swing.JLabel lable_doanhThu13;
    private javax.swing.JLabel lable_doanhThu14;
    private javax.swing.JLabel lable_doanhThu2;
    private javax.swing.JLabel lable_doanhThu9;
    private javax.swing.JTable tbl_banghuy1;
    private javax.swing.JTable tbl_doanhThutheoNam;
    private javax.swing.JTabbedPane tbl_donHuy;
    private javax.swing.JTable tbl_homnay;
    private javax.swing.JTable tbl_thang;
    private javax.swing.JTable tbl_tim;
    private javax.swing.JTextField txt_doanhSo;
    private javax.swing.JTextField txt_doanhThuHomNay;
    private javax.swing.JTextField txt_endDate;
    private javax.swing.JTextField txt_homNay;
    private javax.swing.JTextField txt_huy;
    private javax.swing.JTextField txt_huyHomNay;
    private javax.swing.JTextField txt_nam;
    private javax.swing.JTextField txt_startDate;
    private javax.swing.JTextField txt_thang;
    private javax.swing.JTextField txt_thang1;
    private javax.swing.JTextField txt_thang2;
    private javax.swing.JTextField txt_thang3;
    private javax.swing.JTextField txt_thanhCongHomNay;
    private javax.swing.JTextField txt_thanhcong;
    // End of variables declaration//GEN-END:variables
}
