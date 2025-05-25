/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


/**
 *
 * @author dovan
 */
public class Model_thongke {
    private int stt ; 
    private String idhoaDon ;
    private String idkhachHang ; 
    private String ngayTao; 
    private int tongTien ; 
    private int trangThai ; 

    public Model_thongke(int stt, String idhoaDon, String idkhachHang, String ngayTao, int tongTien) {
        this.stt = stt;
        this.idhoaDon = idhoaDon;
        this.idkhachHang = idkhachHang;
        this.ngayTao = ngayTao;
        this.tongTien = tongTien;
    }

    public Model_thongke(String idhoaDon, String idkhachHang, String ngayTao, int tongTien) {
        this.idhoaDon = idhoaDon;
        this.idkhachHang = idkhachHang;
        this.ngayTao = ngayTao;
        this.tongTien = tongTien;
    }

    public Model_thongke(String idhoaDon, String idkhachHang, String ngayTao, int tongTien, int trangThai) {
        this.idhoaDon = idhoaDon;
        this.idkhachHang = idkhachHang;
        this.ngayTao = ngayTao;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    public Model_thongke(int stt, String idhoaDon, String idkhachHang, String ngayTao, int tongTien, int trangThai) {
        this.stt = stt;
        this.idhoaDon = idhoaDon;
        this.idkhachHang = idkhachHang;
        this.ngayTao = ngayTao;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getIdhoaDon() {
        return idhoaDon;
    }

    public void setIdhoaDon(String idhoaDon) {
        this.idhoaDon = idhoaDon;
    }

    public String getIdkhachHang() {
        return idkhachHang;
    }

    public void setIdkhachHang(String idkhachHang) {
        this.idkhachHang = idkhachHang;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    public Object[] toDataRow(){
        return new Object[]{
            this.getStt(),this.getIdhoaDon(),this.getIdkhachHang(),this.getNgayTao(),this.getTongTien(),this.getTrangThai()==1? "Đã Thanh Toán" : "Đã hủy"
        };
    }
}


