/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dovan
 */
public class Model_khachHang {
    private int soThuTu;
    private String IDKhachHang;
    private String tenKH;
    private String SDT;
    private String ngayTao;
    private String ngaySua;
    private String email;
    private int gioiTinh;
    private int trangThai;

    public Model_khachHang(String IDKhachHang, String tenKH, String SDT, String email, int gioiTinh, int trangThai) {
        this.IDKhachHang = IDKhachHang;
        this.tenKH = tenKH;
        this.SDT = SDT;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangThai;
    }

    public Model_khachHang(String IDKhachHang, String tenKH, String SDT) {
        this.IDKhachHang = IDKhachHang;
        this.tenKH = tenKH;
        this.SDT = SDT;
    }

    public Model_khachHang(int soThuTu, String IDKhachHang, String tenKH, String SDT, String ngayTao, String ngaySua, String email, int gioiTinh, int trangThai) {
        this.soThuTu = soThuTu;
        this.IDKhachHang = IDKhachHang;
        this.tenKH = tenKH;
        this.SDT = SDT;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangThai;
    }

    public Model_khachHang(String tenKH, String SDT, String email, int gioiTinh, int trangThai) {
        this.tenKH = tenKH;
        this.SDT = SDT;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangThai;
    }

    public Model_khachHang(String IDKhachHang, String tenKH, String SDT, String ngayTao, String ngaySua, String email, int gioiTinh, int trangThai) {
        this.IDKhachHang = IDKhachHang;
        this.tenKH = tenKH;
        this.SDT = SDT;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangThai;
    }

    public int getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(int soThuTu) {
        this.soThuTu = soThuTu;
    }

    public String getIDKhachHang() {
        return IDKhachHang;
    }

    public void setIDKhachHang(String IDKhachHang) {
        this.IDKhachHang = IDKhachHang;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(String ngaySua) {
        this.ngaySua = ngaySua;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    

        
    public Object[] toDataRow(){
        return new Object[]{
            this.getIDKhachHang(),this.getTenKH(),this.getSDT(),this.getNgayTao(),this.getNgaySua(),this.getEmail(),this.getGioiTinh()==1?"Nam":"Nữ",this.getTrangThai()==1?"Đang hoạt động":"Không hoạt động"
        };
    }
    
}
