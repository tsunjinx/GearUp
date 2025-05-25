/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;
import DBCon.DbConnection;
/**
 *
 * @author PonYanki
 */
import Model.Model_NhanVien;
import java.sql.*;
import java.util.ArrayList;

public class Repository_NhanVien {
    private Connection con = null;
    private PreparedStatement pr = null;
    private ResultSet rs = null;
    private String sql = null;

    public Repository_NhanVien() {
        con = DbConnection.getConnection();
    }
    
    public ArrayList<Model.Model_NhanVien> getAll() {
        sql = "SELECT SoThuTu,IDNhanVien,Ten,Tuoi,GioiTinh,DiaChi,VaiTro,TenDangNhap,MatKhau,NgayTao,NgaySua,TrangThai FROM NhanVien";
        ArrayList<Model.Model_NhanVien> list = new ArrayList<>();
        try {
            con = DBCon.DbConnection.getConnection();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            while (rs.next()) {
                int soThuTu;
                String IDNhanVien;
                String Ten;
                int Tuoi;
                boolean gioiTinh;
                String diaChi;
                boolean vaiTro;
                String tenDangNhap;
                String matKhau;
                String ngayTao;
                String ngaySua;
                boolean trangThai;
                soThuTu = rs.getInt(1);
                IDNhanVien = rs.getString(2);
                Ten = rs.getString(3);
                Tuoi = rs.getInt(4);
                gioiTinh = rs.getBoolean(5);
                diaChi = rs.getString(6);
                vaiTro = rs.getBoolean(7);
                tenDangNhap = rs.getString(8);
                matKhau = rs.getString(9);
                ngayTao = rs.getString(10);
                ngaySua = rs.getString(11);
                trangThai = rs.getBoolean(12);
                Model_NhanVien nv = new Model_NhanVien(soThuTu, IDNhanVien, Ten, Tuoi, gioiTinh, diaChi, vaiTro, tenDangNhap, matKhau, ngayTao, ngaySua, trangThai);
                list.add(nv);

            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public int updateNV(Model_NhanVien nv, String manv_sua) {
        sql = "UPDATE NhanVien \n"
                + "SET \n"
                + "	Ten = ?,\n"
                + "	Tuoi = ?,\n"
                + "	GioiTinh = ?,\n"
                + "	DiaChi = ?,\n"
                + "	TenDangNhap = ?,\n"
                + "	MatKhau = ?,\n"
                + "	VaiTro = ?,\n"
                + "	TrangThai = ?\n"
                + "WHERE IDNhanVien = ?";
        try {
            con = DBCon.DbConnection.getConnection();
            pr = con.prepareStatement(sql);
            pr.setObject(1, nv.getTen());
            pr.setObject(2, nv.getTuoi());
            pr.setInt(3, nv.isGioiTinh()?1:0);
            pr.setObject(4, nv.getDiaChi());
            pr.setObject(5, nv.getTenDangNhap());
            pr.setObject(6, nv.getMatKhau());
            pr.setInt(7, nv.isVaiTro()?1:0);
            pr.setObject(8, nv.isTrangThai());
            pr.setObject(9, manv_sua);
            return pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ArrayList<Model_NhanVien> timkiem(String manv_cantim) {
        sql = "SELECT SoThuTu,IDNhanVien,Ten,Tuoi,GioiTinh,DiaChi,VaiTro,TenDangNhap,MatKhau,NgayTao,NgaySua,TrangThai FROM NhanVien\n"
                + "Where ten like ?";
        ArrayList<Model_NhanVien> list_nv = new ArrayList<>();
        try {
            con = DBCon.DbConnection.getConnection();
            pr = con.prepareStatement(sql);
            pr.setObject(1, "%" + manv_cantim + "%");
            rs = pr.executeQuery();
            while (rs.next()) {
                int soThuTu;
                String IDNhanVien;
                String Ten;
                int Tuoi;
                boolean gioiTinh;
                String diaChi;
                boolean vaiTro;
                String tenDangNhap;
                String matKhau;
                String ngayTao;
                String ngaySua;
                boolean trangThai;
                soThuTu = rs.getInt(1);
                IDNhanVien = rs.getString(2);
                Ten = rs.getString(3);
                Tuoi = rs.getInt(4);
                gioiTinh = rs.getBoolean(5);
                diaChi = rs.getString(6);
                vaiTro = rs.getBoolean(7);
                tenDangNhap = rs.getString(8);
                matKhau = rs.getString(9);
                ngayTao = rs.getString(10);
                ngaySua = rs.getString(11);
                trangThai = rs.getBoolean(12);
                Model_NhanVien nv = new Model_NhanVien(soThuTu, IDNhanVien, Ten, Tuoi, gioiTinh, diaChi, vaiTro, tenDangNhap, matKhau, ngayTao, ngaySua, trangThai);
                list_nv.add(nv);
            }
            return list_nv;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int addNhanVien(Model_NhanVien nv) {
        //ml là đối tượng mới nhập từ form
        sql = "INSERT INTO NhanVien(IDNhanVien,Ten,Tuoi,GioiTinh,DiaChi,VaiTro,TenDangNhap,MatKhau,NgayTao,NgaySua,TrangThai) \n"
                + "VALUES(?,?,?,?,?,?,?,?,GETDATE(),GETDATE(),?)";
        try {// thêm được
            con = DBCon.DbConnection.getConnection();
            pr = con.prepareStatement(sql);
            // nếu trong sql có ? thì phải setObject
            pr.setObject(1, nv.getIDNhanVien());
            // số 1: thay cho ? đầu tiên
            pr.setObject(2, nv.getTen());
            pr.setObject(3, nv.getTuoi());
            pr.setObject(4, nv.isGioiTinh());
            pr.setObject(5, nv.getDiaChi());
            pr.setObject(6, nv.isVaiTro());
            pr.setObject(7, nv.getTenDangNhap());
            pr.setObject(8, nv.getMatKhau());
            pr.setObject(9, nv.isTrangThai());
            return pr.executeUpdate();
// thêm/sửa/xóa dùng executeUpdate()
        } catch (Exception e) {//không thêm được
            e.printStackTrace();
            return 0;
        }
    }

}
