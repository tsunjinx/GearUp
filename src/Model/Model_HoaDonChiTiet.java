/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Repository.Respository_SPCT;
import Repository.Responsitory_ThuocTinhSanPham;
import java.sql.SQLException;

/**
 *
 * @author Hello
 */
public class Model_HoaDonChiTiet {

    public int STT;
    public String IDSPCT;
    public int TheLoai;
    public String IDHD;
    public int SoLuong;
    public int DonGia;
    public int TrangThai;
    public String IDLoaiDay;
    public String IDChatLieu;
    public String IDDeGiay;
    public String IDSize;
    public String IDMauSac;
    Respository_SPCT repo_spct = new Respository_SPCT();
    Responsitory_ThuocTinhSanPham repo_ttsp = new Responsitory_ThuocTinhSanPham();

    public Model_HoaDonChiTiet() {
    }

    public Model_HoaDonChiTiet(int STT, String IDSPCT, int TheLoai, String IDHD, int SoLuong, int DonGia, int TrangThai, String TenLoaiDay, String TenChatLieu, String TenDeGiay, String TenSize, String TenMauSac) {
        this.STT = STT;
        this.IDSPCT = IDSPCT;
        this.TheLoai = TheLoai;
        this.IDHD = IDHD;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.TrangThai = TrangThai;
        this.IDLoaiDay = TenLoaiDay;
        this.IDChatLieu = TenChatLieu;
        this.IDDeGiay = TenDeGiay;
        this.IDSize = TenSize;
        this.IDMauSac = TenMauSac;
    }

    public Model_HoaDonChiTiet(String IDSPCT, int TheLoai, String IDHD, int SoLuong, int DonGia, int TrangThai, String TenLoaiDay, String TenChatLieu, String TenDeGiay, String TenSize, String TenMauSac) {
        this.IDSPCT = IDSPCT;
        this.TheLoai = TheLoai;
        this.IDHD = IDHD;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.TrangThai = TrangThai;
        this.IDLoaiDay = TenLoaiDay;
        this.IDChatLieu = TenChatLieu;
        this.IDDeGiay = TenDeGiay;
        this.IDSize = TenSize;
        this.IDMauSac = TenMauSac;
    }

    public Model_HoaDonChiTiet(String IDSPCT, String IDHD, int SoLuong, int DonGia) {
        this.IDSPCT = IDSPCT;
        this.IDHD = IDHD;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
    }
    

    public int getTheLoai() {
        return TheLoai;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public String getTenLoaiDay() {
        return IDLoaiDay;
    }

    public String getTenChatLieu() {
        return IDChatLieu;
    }

    public String getTenDeGiay() {
        return IDDeGiay;
    }

    public String getTenSize() {
        return IDSize;
    }

    public String getTenMauSac() {
        return IDMauSac;
    }

    public int getSTT() {
        return STT;
    }

    public String getIDSPCT() {
        return IDSPCT;
    }

    public String getIDHD() {
        return IDHD;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public int getDonGia() {
        return DonGia;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public void setIDSPCT(String IDSPCT) {
        this.IDSPCT = IDSPCT;
    }

    public void setIDHD(String IDHD) {
        this.IDHD = IDHD;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public void setDonGia(int DonGia) {
        this.DonGia = DonGia;
    }

    public Object[] ToDataRow() throws SQLException {
        String TenSPCT = null;
        String TenLoaiDay = null;
        String TenChatLieu = null;
        String TenDeGiay = null;
        String TenKichThuoc = null;
        String TenMauSac = null;
        for (Model_SPCT x : repo_spct.GetAll_SPCT()) {
            if (getIDSPCT().equals(x.getIDSPCT())) {
                TenSPCT = x.getTen();
            }
        }
        for (Model_loaiDay x : repo_ttsp.getAll_LoaiDay()) {
            if (getTenLoaiDay().equals(x.getMaLoaiGiay())) {
                TenLoaiDay = x.getLoaiGiay();
            }
        }
        for (Model_chatLieu x : repo_ttsp.getAll_ChatLieu()) {
            if (getTenChatLieu().equals(x.getMaChatLieu())) {
                TenChatLieu = x.getTenChatLieu();
            }
        }
        for (Model_DeGiay x : repo_ttsp.getAll_DeGiay()) {
            if (getTenDeGiay().equals(x.getMaDeGiay())) {
                TenDeGiay = x.getTenDeGiay();
            }
        }
        for (Model_kichThuoc x : repo_ttsp.getAll_KichThuoc()) {
            if (getTenSize().equals(x.getMaKichThuoc())) {
                TenKichThuoc = x.getKichThuoc();
            }
        }
        for (Model_mauSac x : repo_ttsp.getAll_mauSac()) {
            if (getTenMauSac().equals(x.getMaMauSac())) {
                TenMauSac = x.getTenMauSac();
            }
        }
        return new Object[]{getSTT(), getIDSPCT(), TenSPCT, getTheLoai()==1?"Nam":"Nữ",TenLoaiDay,TenChatLieu,TenDeGiay,TenKichThuoc,TenMauSac, getSoLuong(), getDonGia(), getSoLuong() * (getDonGia())};
    }
}
