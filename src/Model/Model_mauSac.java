/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dovan
 */
public class Model_mauSac {

    private String maMauSac;
    private String tenMauSac;
    private int trangThai;

    public Model_mauSac() {
    }

    public Model_mauSac(String maMauSac, String tenMauSac, int trangThai) {
        this.maMauSac = maMauSac;
        this.tenMauSac = tenMauSac;
        this.trangThai = trangThai;
    }

    public String getMaMauSac() {
        return maMauSac;
    }

    public String getTenMauSac() {
        return tenMauSac;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setMaMauSac(String maMauSac) {
        this.maMauSac = maMauSac;
    }

    public void setTenMauSac(String tenMauSac) {
        this.tenMauSac = tenMauSac;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public Object[] toDataRow() {
        return new Object[]{
            this.getMaMauSac(), this.getTenMauSac(), this.getTrangThai() == 1 ? "Đang Hoạt động" : "Không Hoạt Động"
        };
    }
}
