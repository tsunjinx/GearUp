/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DBCon.DbConnection;
import Model.Model_HoaDon;
import Model.Model_khuyenMai;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author dovan
 */
public class Responsitory_khuyenMai {

    public Connection con;

    public Responsitory_khuyenMai() {
        con = DbConnection.getConnection();
    }

    public void autoUpdate() throws SQLException {
        String sql = "update KhuyenMai set TrangThai = 0 where NgayKetThuc < GETDATE()";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.executeUpdate();
    }

    public void autoUpdate1() throws SQLException {
        String sql = "update KhuyenMai set TrangThai = 1 where NgayKetThuc > GETDATE()";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.executeUpdate();
    }

    public void autoUpdate2() throws SQLException {
        String sql = """
                     update SanPhamChiTiet
                     set IDKhuyenMai = 'KM001' 
                     from SanPhamChiTiet spct join KhuyenMai km on spct.IDKhuyenMai=km.IDKhuyenMai
                     where km.TrangThai = 0 """;
        PreparedStatement ps = con.prepareStatement(sql);
        ps.executeUpdate();
    }

    public ArrayList<Model_khuyenMai> getAll_khuyenMai() throws SQLException {
        ArrayList<Model_khuyenMai> lst = new ArrayList<>();
        String sql = "select SoThuTu,IDKhuyenMai,TenKhuyenMai,NgayBatDau,NgayKetThuc,PhanTramGiam,TrangThai from KhuyenMai";
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            Model_khuyenMai km = new Model_khuyenMai(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7));
            lst.add(km);
        }
        return lst;
    }

    public int add_khuyenMai(Model_khuyenMai mk) throws SQLException {
        String sql = "insert KhuyenMai(IDKhuyenMai,TenKhuyenMai,NgayBatDau,NgayKetThuc,PhanTramGiam,TrangThai) values (?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, mk.getMaKhuyenMai());
        ps.setString(2, mk.getTenKhuyenMai());
        ps.setString(3, mk.getNgayBatDau());
        ps.setString(4, mk.getNgayKetThuc());
        ps.setInt(5, mk.getGiamGia());
        ps.setInt(6, mk.getTrangThai());
        return ps.executeUpdate();
    }

    public int update_khuyenMai(Model_khuyenMai mk) throws SQLException {
        String sql = "UPDATE KhuyenMai SET TenKhuyenMai = ?, NgayBatDau = ?, NgayKetThuc = ?, PhanTramGiam = ?, TrangThai = ? WHERE IDKhuyenMai = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, mk.getTenKhuyenMai()); // Tên khuyến mãi
        ps.setString(2, mk.getNgayBatDau());  // Ngày bắt đầu
        ps.setString(3, mk.getNgayKetThuc()); // Ngày kết thúc
        ps.setInt(4, mk.getGiamGia());        // Phần trăm giảm giá
        ps.setInt(5, mk.getTrangThai());      // Trạng thái
        ps.setString(6, mk.getMaKhuyenMai()); // ID khuyến mãi (khóa chính)
        return ps.executeUpdate();
    }

    public int update(String IDKM, String IDSPCT) throws SQLException {
        String sql = "update SanPhamChiTiet set IDKhuyenMai = ? where IDSanPhamChiTiet = ?";
        PreparedStatement ps = con.prepareCall(sql);
        ps.setString(1, IDKM);
        ps.setString(2, IDSPCT);
        return ps.executeUpdate();
    }
}
