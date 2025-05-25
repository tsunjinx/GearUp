/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author PonYanki
 */
public class Model_NhanVien {

    private int soThuTu;
    private String IDNhanVien;
    private String Ten;
    private int Tuoi;
    private boolean gioiTinh;
    private String diaChi;
    private boolean vaiTro;
    private String tenDangNhap;
    private String matKhau;
    private String ngayTao;
    private String ngaySua;
    private boolean trangThai;

    public Model_NhanVien(int soThuTu, String IDNhanVien, String Ten, int Tuoi, boolean gioiTinh, String diaChi, boolean vaiTro, String tenDangNhap, String matKhau, String ngayTao, String ngaySua, boolean trangThai) {
        this.soThuTu = soThuTu;
        this.IDNhanVien = IDNhanVien;
        this.Ten = Ten;
        this.Tuoi = Tuoi;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.vaiTro = vaiTro;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

    public Model_NhanVien(String IDNhanVien, String Ten, int Tuoi, boolean gioiTinh, String diaChi, boolean vaiTro, String tenDangNhap, String matKhau, String ngayTao, String ngaySua, boolean trangThai) {
        this.IDNhanVien = IDNhanVien;
        this.Ten = Ten;
        this.Tuoi = Tuoi;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.vaiTro = vaiTro;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

    public int getSoThuTu() {
        return soThuTu;
    }

    public String getIDNhanVien() {
        return IDNhanVien;
    }

    public String getTen() {
        return Ten;
    }

    public int getTuoi() {
        return Tuoi;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public boolean isVaiTro() {
        return vaiTro;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public String getNgaySua() {
        return ngaySua;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setSoThuTu(int soThuTu) {
        this.soThuTu = soThuTu;
    }

    public void setIDNhanVien(String IDNhanVien) {
        this.IDNhanVien = IDNhanVien;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public void setTuoi(int Tuoi) {
        this.Tuoi = Tuoi;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public void setNgaySua(String ngaySua) {
        this.ngaySua = ngaySua;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    
    

    public Model_NhanVien() {
    }

    
    public Object[] toDataRow(){
     return new Object[]{
         this.getSoThuTu(),
         this.getIDNhanVien(),
         this.getTen(),
         this.getTuoi(),
         this.isGioiTinh()?"Nam":"Nữ",
         this.getDiaChi(),
         this.isVaiTro()?"Quản Lý":"Nhân Viên",
         this.getTenDangNhap(),
         this.getMatKhau(),
         this.getNgaySua(),
         this.getNgaySua(),
         this.isTrangThai()?"Đang Hoạt Động":"Không Hoạt Động"
     };
    }
}
