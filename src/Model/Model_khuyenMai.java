/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dovan
 */
public class Model_khuyenMai {

    public static int getRowCount() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void addRow(Object[] object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private int stt_khuyenMai ;
    private String maKhuyenMai ; 
    private String tenKhuyenMai;
    private String ngayBatDau ; 
    private String ngayKetThuc ; 
    private int giamGia; 
    private int trangThai ; 

    public Model_khuyenMai(String maKhuyenMai, String tenKhuyenMai, String ngayBatDau, String ngayKetThuc, int giamGia, int trangThai) {
        this.maKhuyenMai = maKhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.giamGia = giamGia;
        this.trangThai = trangThai;
    }

    public Model_khuyenMai(int stt_khuyenMai, String maKhuyenMai, String tenKhuyenMai, String ngayBatDau, String ngayKetThuc, int giamGia, int trangThai) {
        this.stt_khuyenMai = stt_khuyenMai;
        this.maKhuyenMai = maKhuyenMai;
        this.tenKhuyenMai = tenKhuyenMai;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.giamGia = giamGia;
        this.trangThai = trangThai;
    }

    public Model_khuyenMai(int stt_khuyenMai, String maKhuyenMai, String ngayBatDau, String ngayKetThuc, int giamGia, int trangThai) {
        this.stt_khuyenMai = stt_khuyenMai;
        this.maKhuyenMai = maKhuyenMai;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.giamGia = giamGia;
        this.trangThai = trangThai;
    }
    
    public int getStt_khuyenMai() {
        return stt_khuyenMai;
    }

    public void setStt_khuyenMai(int stt_khuyenMai) {
        this.stt_khuyenMai = stt_khuyenMai;
    }

    public String getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(String maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(int giamGia) {
        this.giamGia = giamGia;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    public Object[] toDataRow(){
        return new Object[]{
            this.getStt_khuyenMai(),this.getMaKhuyenMai(),this.getTenKhuyenMai(),this.getNgayBatDau(),this.getNgayKetThuc(),this.getGiamGia(),this.getTrangThai()==1? "Đang Hoạt động" : "Không Hoạt Động"
        };
    }
}
