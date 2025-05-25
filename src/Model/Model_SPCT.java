package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import Repository.Responsitory_khuyenMai;
import java.sql.SQLException;

public class Model_SPCT {
     Responsitory_khuyenMai repo_km;
    public String IDSPCT;
    public String Ten;
    public int TheLoai;
    public int DonGia;
    public String IDKhuyenMai;
    public int SoLuong;
    public int TrangThai;
    public String TenLoaiDay;
    public String TenChatLieu;
    public String TenDeGiay;
    public String TenSize;
    public String TenMauSac;

    public Model_SPCT() {
        repo_km = new Responsitory_khuyenMai();
    }

    public Model_SPCT(String IDSPCT, String Ten, int TheLoai, int DonGia, int SoLuong, String TenLoaiDay, String TenChatLieu, String TenSize, String TenMauSac) {
        this.IDSPCT = IDSPCT;
        this.Ten = Ten;
        this.TheLoai = TheLoai;
        this.DonGia = DonGia;
        this.SoLuong = SoLuong;
        this.TenLoaiDay = TenLoaiDay;
        this.TenChatLieu = TenChatLieu;
        this.TenSize = TenSize;
        this.TenMauSac = TenMauSac;
    }

    public String getIDKhuyenMai() {
        return IDKhuyenMai;
    }

    public void setIDKhuyenMai(String IDKhuyenMai) {
        this.IDKhuyenMai = IDKhuyenMai;
    }

    public Model_SPCT(String IDSPCT, String Ten, int TheLoai, int DonGia, String IDKhuyenMai, int SoLuong, int TrangThai, String TenLoaiDay, String TenChatLieu, String TenDeGiay, String TenSize, String TenMauSac) {
        this.IDSPCT = IDSPCT;
        this.Ten = Ten;
        this.TheLoai = TheLoai;
        this.DonGia = DonGia;
        this.IDKhuyenMai = IDKhuyenMai;
        this.SoLuong = SoLuong;
        this.TrangThai = TrangThai;
        this.TenLoaiDay = TenLoaiDay;
        this.TenChatLieu = TenChatLieu;
        this.TenDeGiay = TenDeGiay;
        this.TenSize = TenSize;
        this.TenMauSac = TenMauSac;
    }

    public Model_SPCT(String IDSPCT, String Ten, int TheLoai, int DonGia, int SoLuong, int TrangThai, String TenLoaiDay, String TenChatLieu, String TenDeGiay, String TenSize, String TenMauSac) {
        this.IDSPCT = IDSPCT;
        this.Ten = Ten;
        this.TheLoai = TheLoai;
        this.DonGia = DonGia;
        this.SoLuong = SoLuong;
        this.TenLoaiDay = TenLoaiDay;
        this.TenMauSac = TenMauSac;
        this.TenSize = TenSize;
        this.TenChatLieu = TenChatLieu;
        this.TenDeGiay = TenDeGiay;
        this.TrangThai = TrangThai;
    }

    public String getIDSPCT() {
        return IDSPCT;
    }

    public String getTen() {
        return Ten;
    }

    public int getTheLoai() {
        return TheLoai;
    }

    public int getDonGia() {
        return DonGia;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public String getTenLoaiDay() {
        return TenLoaiDay;
    }

    public String getTenChatLieu() {
        return TenChatLieu;
    }

    public String getTenDeGiay() {
        return TenDeGiay;
    }

    public String getTenSize() {
        return TenSize;
    }

    public String getTenMauSac() {
        return TenMauSac;
    }

    public void setIDSPCT(String IDSPCT) {
        this.IDSPCT = IDSPCT;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public void setTheLoai(int TheLoai) {
        this.TheLoai = TheLoai;
    }

    public void setDonGia(int DonGia) {
        this.DonGia = DonGia;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public void setTenLoaiDay(String TenLoaiDay) {
        this.TenLoaiDay = TenLoaiDay;
    }

    public void setTenChatLieu(String TenChatLieu) {
        this.TenChatLieu = TenChatLieu;
    }

    public void setTenDeGiay(String TenDeGiay) {
        this.TenDeGiay = TenDeGiay;
    }

    public void setTenSize(String TenSize) {
        this.TenSize = TenSize;
    }

    public void setTenMauSac(String TenMauSac) {
        this.TenMauSac = TenMauSac;
    }

    public Object[] toDataRow() {
        return new Object[]{
            getIDSPCT(), getTen(), getTheLoai() == 1 ? "Nam" : "Nữ", getDonGia(), getSoLuong(), getTenLoaiDay(), getTenChatLieu(), getTenDeGiay(), getTenSize(), getTenMauSac(), getTrangThai() == 1 ? "Hoạt động" : "Không hoạt động"
        };
    }
    public Object[] toDataRow1() throws SQLException {
        int a = 0;
        for (Model_khuyenMai x : repo_km.getAll_khuyenMai()) {
            if(getIDKhuyenMai().equals(x.getMaKhuyenMai())){
                a = x.getGiamGia();
            }
        }
        return new Object[]{
            getIDSPCT(), getTen(), getTheLoai() == 1 ? "Nam" : "Nữ", getDonGia(),getDonGia()*a/100, getSoLuong(), getTenLoaiDay(), getTenChatLieu(), getTenDeGiay(), getTenSize(), getTenMauSac(), getTrangThai() == 1 ? "Hoạt động" : "Không hoạt động"
        };
    }
}
