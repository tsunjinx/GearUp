/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dovan
 */
public class Model_loaiDay {

    private String maLoaiGiay;
    private String loaiGiay;
    private int trangthai;

    public Model_loaiDay() {
    }

    public Model_loaiDay(String maLoaiGiay, String loaiGiay, int trangthai) {
        this.maLoaiGiay = maLoaiGiay;
        this.loaiGiay = loaiGiay;
        this.trangthai = trangthai;
    }

    public String getMaLoaiGiay() {
        return maLoaiGiay;
    }

    public String getLoaiGiay() {
        return loaiGiay;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setMaLoaiGiay(String maLoaiGiay) {
        this.maLoaiGiay = maLoaiGiay;
    }

    public void setLoaiGiay(String loaiGiay) {
        this.loaiGiay = loaiGiay;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public Object[] toDataRow() {
        return new Object[]{
            this.getMaLoaiGiay(), this.getLoaiGiay(), this.getTrangthai() == 1 ? "Đang Hoạt động" : "Không Hoạt Động"
        };
    }
}
