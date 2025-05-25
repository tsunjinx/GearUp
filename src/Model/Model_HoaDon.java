/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Repository.Repository_NhanVien;
import Repository.Responsitory_khachHang;

/**
 *
 * @author Hello
 */
public class Model_HoaDon {

    public int STT;
    public String MaHD;
    public String IDNV;
    public String IDKH;
    public String TenKH;
    public String SDT;
    public String MaVoucher;
    public int TongTien;
    public int TrangThai;
    public String NgayTao;
    public String NgaySua;
    Repository_NhanVien repo_nv = new Repository_NhanVien();
    Responsitory_khachHang repo_kh = new Responsitory_khachHang();

    public void setNgayTao(String NgayTao) {
        this.NgayTao = NgayTao;
    }

    public void setNgaySua(String NgaySua) {
        this.NgaySua = NgaySua;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public String getNgaySua() {
        return NgaySua;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getSDT() {
        return SDT;
    }

    public Model_HoaDon() {
    }

    public int getSTT() {
        return STT;
    }

    public String getMaHD() {
        return MaHD;
    }

    public String getIDNV() {
        return IDNV;
    }

    public String getIDKH() {
        return IDKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public String getMaVoucher() {
        return MaVoucher;
    }

    public int getTongTien() {
        return TongTien;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public Repository_NhanVien getRepo_nv() {
        return repo_nv;
    }

    public Responsitory_khachHang getRepo_kh() {
        return repo_kh;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public void setIDNV(String IDNV) {
        this.IDNV = IDNV;
    }

    public void setIDKH(String IDKH) {
        this.IDKH = IDKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public void setMaVoucher(String MaVoucher) {
        this.MaVoucher = MaVoucher;
    }

    public void setTongTien(int TongTien) {
        this.TongTien = TongTien;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public void setRepo_nv(Repository_NhanVien repo_nv) {
        this.repo_nv = repo_nv;
    }

    public void setRepo_kh(Responsitory_khachHang repo_kh) {
        this.repo_kh = repo_kh;
    }

    public Model_HoaDon(int STT, String MaHD, String IDNV, String IDKH, String MaVoucher, int TongTien, int TrangThai, String NgayTao, String NgaySua) {
        this.STT = STT;
        this.MaHD = MaHD;
        this.IDNV = IDNV;
        this.IDKH = IDKH;
        this.MaVoucher = MaVoucher;
        this.TongTien = TongTien;
        this.TrangThai = TrangThai;
        this.NgayTao = NgayTao;
        this.NgaySua = NgaySua;
    }

    public Model_HoaDon(String MaHD, String IDNV, String IDKH, String MaVoucher, int TongTien, int TrangThai) {
        this.MaHD = MaHD;
        this.IDNV = IDNV;
        this.IDKH = IDKH;
        this.MaVoucher = MaVoucher;
        this.TongTien = TongTien;
        this.TrangThai = TrangThai;
    }

    public Object[] ToDataRow() {
        String TenNV = "";
        String TrangThai = "";
        String TenKH = "";
        String SDT = "";
        for (Model_NhanVien x : repo_nv.getAll()) {
            if (getIDNV().equals(x.getIDNhanVien())) {
                TenNV = x.getTen();
            }
        }
        for (Model_khachHang x : repo_kh.getAll()) {
            if (getIDKH().equals(x.getIDKhachHang())) {
                TenKH = x.getTenKH();
                SDT = x.getSDT();
            }
        }
        if (getTrangThai() == 1) {
            TrangThai = "Đã thanh toán";
        } else if (getTrangThai() == 0) {
            TrangThai = "Chưa thanh toán";
        } else {
            TrangThai = "Đã hủy";
        }
        return new Object[]{getSTT(), getMaHD(), TenNV, TenKH, SDT, getMaVoucher(), getTongTien(), TrangThai};
    }

    public Object[] ToDataRow1() {
        String TenNV = "";
        String TrangThai = "";
        String TenKH = "";
        String SDT = "";
        for (Model_NhanVien x : repo_nv.getAll()) {
            if (getIDNV().equals(x.getIDNhanVien())) {
                TenNV = x.getTen();
            }
        }
        for (Model_khachHang x : repo_kh.getAll()) {
            if (getIDKH().equals(x.getIDKhachHang())) {
                TenKH = x.getTenKH();
                SDT = x.getSDT();
            }
        }
        if (getTrangThai() == 1) {
            TrangThai = "Đã thanh toán";
        } else if (getTrangThai() == 0) {
            TrangThai = "Chưa thanh toán";
        } else {
            TrangThai = "Đã hủy";
        }
        return new Object[]{getSTT(), getMaHD(), TenNV, TenKH, getMaVoucher(), getNgayTao(), getNgaySua(), getTongTien(), TrangThai};
    }
}
