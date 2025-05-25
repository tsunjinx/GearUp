/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dovan
 */
public class Model_SanPham {
    private int STT;
    private String IDSanPham;
    private String tenSanPham;
    private String NgayTao ; 
    private String ngaySua ; 
    private int trangThai ; 

    public Model_SanPham(int STT, String IDSanPham, String tenSanPham, String NgayTao, String ngaySua, int trangThai) {
        this.STT = STT;
        this.IDSanPham = IDSanPham;
        this.tenSanPham = tenSanPham;
        this.NgayTao = NgayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

    public Model_SanPham(String IDSanPham, String tenSanPham, int trangThai) {
        this.IDSanPham = IDSanPham;
        this.tenSanPham = tenSanPham;
        this.trangThai = trangThai;
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public String getIDSanPham() {
        return IDSanPham;
    }

    public void setIDSanPham(String IDSanPham) {
        this.IDSanPham = IDSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String NgayTao) {
        this.NgayTao = NgayTao;
    }

    public String getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(String ngaySua) {
        this.ngaySua = ngaySua;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    public Object[] toDataRow(){
        return new Object[]{
            this.getSTT(),this.getIDSanPham(),this.getTenSanPham(),this.getNgayTao(),this.getNgaySua(),this.getTrangThai()==1? "Đang Hoạt động" : "Không Hoạt Động"
        };
    }
}
