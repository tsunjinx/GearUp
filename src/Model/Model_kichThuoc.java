/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dovan
 */
public class Model_kichThuoc {

    private String maKichThuoc;
    private String kichThuoc;
    private int trangThai;

    public Model_kichThuoc() {
    }

    public Model_kichThuoc(String maKichThuoc, String kichThuoc, int trangThai) {
        this.maKichThuoc = maKichThuoc;
        this.kichThuoc = kichThuoc;
        this.trangThai = trangThai;
    }

    public String getMaKichThuoc() {
        return maKichThuoc;
    }

    public String getKichThuoc() {
        return kichThuoc;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setMaKichThuoc(String maKichThuoc) {
        this.maKichThuoc = maKichThuoc;
    }

    public void setKichThuoc(String kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public Object[] toDataRow() {
        return new Object[]{
            this.getMaKichThuoc(), this.getKichThuoc(), this.getTrangThai() == 1 ? "Đang Hoạt động" : "Không Hoạt Động"
        };
    }
}
