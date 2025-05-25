/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dovan
 */
public class Model_voucher {
    private int stt; 
    private String id;
    private String ten ; 
    private int phanTramGiam;
    private int donHangToiThieu;
    private String ngayBD;
    private String ngayKT;
    private int giaTriGiamToiDa;
    private int TrangThai;

    public Model_voucher(int stt, String id, String ten, int phanTramGiam, int donHangToiThieu, String ngayBD, String ngayKT, int giaTriGiamToiDa, int TrangThai) {
        this.stt = stt;
        this.id = id;
        this.ten = ten;
        this.phanTramGiam = phanTramGiam;
        this.donHangToiThieu = donHangToiThieu;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.giaTriGiamToiDa = giaTriGiamToiDa;
        this.TrangThai = TrangThai;
    }

    public Model_voucher(String id, String ten, int phanTramGiam, int donHangToiThieu, String ngayBD, String ngayKT, int giaTriGiamToiDa, int TrangThai) {
        this.id = id;
        this.ten = ten;
        this.phanTramGiam = phanTramGiam;
        this.donHangToiThieu = donHangToiThieu;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.giaTriGiamToiDa = giaTriGiamToiDa;
        this.TrangThai = TrangThai;
    }

    public Model_voucher(String trim, int parseInt, int parseInt0, String trim0, String trim1, int parseInt1, int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(int phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }

    public int getDonHangToiThieu() {
        return donHangToiThieu;
    }

    public void setDonHangToiThieu(int donHangToiThieu) {
        this.donHangToiThieu = donHangToiThieu;
    }

    public String getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(String ngayBD) {
        this.ngayBD = ngayBD;
    }

    public String getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(String ngayKT) {
        this.ngayKT = ngayKT;
    }

    public int getGiaTriGiamToiDa() {
        return giaTriGiamToiDa;
    }

    public void setGiaTriGiamToiDa(int giaTriGiamToiDa) {
        this.giaTriGiamToiDa = giaTriGiamToiDa;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    public Object[] toDataRow(){
        return new Object[]{
            this.getStt(),this.getId(),this.getTen(),this.getPhanTramGiam(),this.getDonHangToiThieu(),this.getNgayBD(),this.getNgayKT(),this.getGiaTriGiamToiDa(),this.getTrangThai()==1? "Đang Hoạt động" : "Không hoạt động"
        };
    }
}
