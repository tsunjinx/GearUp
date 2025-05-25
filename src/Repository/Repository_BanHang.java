/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.sql.*;
import DBCon.DbConnection;
import Model.Model_HoaDon;
import Model.Model_HoaDonChiTiet;
import java.util.ArrayList;

/**
 *
 * @author Hello
 */
public class Repository_BanHang {

    public Connection con;

    public Repository_BanHang() {
        con = DbConnection.getConnection();
    }

    public ArrayList<Model_HoaDon> getAll_HD() throws SQLException {
        ArrayList<Model_HoaDon> lst = new ArrayList<>();
        String sql = "select SoThuTu,IDHoaDon,IDNhanVien,IDKhachHang,IDVoucher,TongTien,TrangThai,NgayTao,NgaySua from HoaDon";
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            Model_HoaDon hd = new Model_HoaDon(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7),rs.getString(8),rs.getString(9));
            lst.add(hd);
        }
        return lst;
    }

    public int addHD(Model_HoaDon hd) throws SQLException {
        String sql = "insert HoaDon(IDHoaDon,IDNhanVien,IDKhachHang,IDVoucher,NgayMuaHang,TongTien,NgayTao,NgaySua,TrangThai) values(?,?,?,?,GETDATE(),?,GETDATE(),GETDATE(),?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, hd.getMaHD());
        ps.setString(2, hd.getIDNV());
        ps.setString(3, hd.getIDKH());
        ps.setString(4, hd.getMaVoucher());
        ps.setInt(5, hd.getTongTien());
        ps.setInt(6, hd.getTrangThai());
        return ps.executeUpdate();
    }

    public int updateHD(String IDVoucher, int TrangThai,int TongTien, String MaHD) throws SQLException {
        String sql = "update HoaDon set IDVoucher = ?, TrangThai = ?,TongTien = ? where IDHoaDon = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, IDVoucher);
        ps.setInt(2, TrangThai);
        ps.setInt(3, TongTien);
        ps.setString(4, MaHD);
        return ps.executeUpdate();
    }

    public ArrayList<Model_HoaDonChiTiet> GetAllHoaDonChiTiet(String MaHD) throws SQLException {
        ArrayList<Model_HoaDonChiTiet> lst = new ArrayList<>();
        String sql = """
                     select hdct.SoThuTu,hdct.IDSanPhamChiTiet,spct.TheLoai,IDHoaDon,hdct.SoLuong,hdct.DonGia,hdct.TrangThai,spct.IDLoaiDay,spct.IDChatLieu,spct.IDDeGiay,spct.IDKichThuoc,spct.IDMauSac
                     from HoaDonChiTiet hdct left join SanPhamChiTiet spct on hdct.IDSanPhamChiTiet = spct.IDSanPhamChiTiet
                     where IDHoaDon like ?""";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, MaHD);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Model_HoaDonChiTiet hdct = new Model_HoaDonChiTiet(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));
            lst.add(hdct);
        }
        return lst;
    }

    public int addHoaDonChiTiet(Model_HoaDonChiTiet hdct) throws SQLException {
        String sql = "insert HoaDonChiTiet(IDSanPhamChiTiet,IDHoaDon,SoLuong,DonGia,NgayTao) values(?,?,?,?,GETDATE())";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, hdct.getIDSPCT());
        ps.setString(2, hdct.getIDHD());
        ps.setInt(3, hdct.getSoLuong());
        ps.setInt(4, hdct.getDonGia());
        return ps.executeUpdate();
    }

    public int deleteHoaDonChiTiet(int STT) throws SQLException {
        String sql = "Delete HoaDonChiTiet where SoThuTu like ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, STT);
        return ps.executeUpdate();
    }
    
    public int updateHoaDonChiTiet(int SoLuong, int STT) throws SQLException{
        String sql = "update HoaDonChiTiet set SoLuong = SoLuong + ? where SoThuTu = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, SoLuong);
        ps.setInt(2, STT);
        return ps.executeUpdate();
    }

    public ArrayList<Model_HoaDonChiTiet> GetAllHoaDonChiTiet_tim(String MaHD, String IDSPCT) throws SQLException {
        ArrayList<Model_HoaDonChiTiet> lst = new ArrayList<>();
        String sql = """
                    select hdct.SoThuTu,hdct.IDSanPhamChiTiet,spct.TheLoai,IDHoaDon,hdct.SoLuong,hdct.DonGia,hdct.TrangThai,spct.IDLoaiDay,spct.IDChatLieu,spct.IDDeGiay,spct.IDKichThuoc,spct.IDMauSac
                    from HoaDonChiTiet hdct left join SanPhamChiTiet spct on hdct.IDSanPhamChiTiet = spct.IDSanPhamChiTiet
                    where IDHoaDon like ? AND hdct.IDSanPhamChiTiet like ?""";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, MaHD);
        ps.setString(2, IDSPCT);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Model_HoaDonChiTiet hdct = new Model_HoaDonChiTiet(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));
            lst.add(hdct);
        }
        return lst;
    }
    
}
