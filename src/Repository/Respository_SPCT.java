package Repository;

import Model.Model_SPCT;
import Model.Model_SanPham;
import java.sql.*;
import java.util.ArrayList;

public class Respository_SPCT {

    public Connection con = null;
    public PreparedStatement ps = null ; 
    public Respository_SPCT() {
        con = DBCon.DbConnection.getConnection();
    }

    public ArrayList<Model.Model_SPCT> GetAll_SPCT() throws SQLException {
        ArrayList<Model.Model_SPCT> lst = new ArrayList();
        String sql = "select SanPhamChiTiet.IDSanPhamChiTiet , SanPham.TenSanPham ,TheLoai, SanPhamChiTiet.DonGia , SanPhamChiTiet.SoLuong , SanPhamChiTiet.TrangThai ,LoaiDay.ten, ChatLieu.Ten , DeGiay.Ten , KichThuoc.ten , MauSac.ten , SanPhamChiTiet.IDKhuyenMai from SanPhamChiTiet "
                + "              join SanPham on SanPham.IDSanPham = SanPhamChiTiet.IDSanPham\n"
                + "              join LoaiDay on LoaiDay.IDLoaiDay = SanPhamChiTiet.IDLoaiDay\n"
                + "              join ChatLieu on ChatLieu.IDChatLieu = SanPhamChiTiet.IDChatLieu\n"
                + "              join DeGiay on DeGiay.IDDeGiay = SanPhamChiTiet.IDDeGiay\n"
                + "              join KichThuoc on KichThuoc.IDKichThuoc = SanPhamChiTiet.IDKichThuoc\n"
                + "              join MauSac on MauSac.IDMauSac = SanPhamChiTiet.IDMauSac";
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            Model_SPCT spct = new Model_SPCT();
            spct.setIDSPCT(rs.getString(1));
            spct.setTen(rs.getString(2));
            spct.setTheLoai(rs.getInt(3));
            spct.setDonGia(rs.getInt(4));
            spct.setSoLuong(rs.getInt(5));
            spct.setTrangThai(rs.getInt(6));
            spct.setTenLoaiDay(rs.getString(7));
            spct.setTenChatLieu(rs.getString(8));
            spct.setTenDeGiay(rs.getString(9));
            spct.setTenSize(rs.getString(10));
            spct.setTenMauSac(rs.getString(11));
            spct.setIDKhuyenMai(rs.getString(12));
            lst.add(spct);
        }
        return lst;
    }
    
    
    public ArrayList<Model.Model_SPCT> GetAll_SPCT_Nam() throws SQLException {
        ArrayList<Model.Model_SPCT> lst = new ArrayList();
        String sql = "select SanPhamChiTiet.IDSanPhamChiTiet , SanPham.TenSanPham ,TheLoai, SanPhamChiTiet.DonGia , SanPhamChiTiet.SoLuong , SanPhamChiTiet.TrangThai ,LoaiDay.ten, ChatLieu.Ten , DeGiay.Ten , KichThuoc.ten , MauSac.ten , SanPhamChiTiet.IDKhuyenMai from SanPhamChiTiet "
                + "              join SanPham on SanPham.IDSanPham = SanPhamChiTiet.IDSanPham\n"
                + "              join LoaiDay on LoaiDay.IDLoaiDay = SanPhamChiTiet.IDLoaiDay\n"
                + "              join ChatLieu on ChatLieu.IDChatLieu = SanPhamChiTiet.IDChatLieu\n"
                + "              join DeGiay on DeGiay.IDDeGiay = SanPhamChiTiet.IDDeGiay\n"
                + "              join KichThuoc on KichThuoc.IDKichThuoc = SanPhamChiTiet.IDKichThuoc\n"
                + "              join MauSac on MauSac.IDMauSac = SanPhamChiTiet.IDMauSac where TheLoai =1";
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            Model_SPCT spct = new Model_SPCT();
            spct.setIDSPCT(rs.getString(1));
            spct.setTen(rs.getString(2));
            spct.setTheLoai(rs.getInt(3));
            spct.setDonGia(rs.getInt(4));
            spct.setSoLuong(rs.getInt(5));
            spct.setTrangThai(rs.getInt(6));
            spct.setTenLoaiDay(rs.getString(7));
            spct.setTenChatLieu(rs.getString(8));
            spct.setTenDeGiay(rs.getString(9));
            spct.setTenSize(rs.getString(10));
            spct.setTenMauSac(rs.getString(11));
            spct.setIDKhuyenMai(rs.getString(12));
            lst.add(spct);
        }
        return lst;
    }
    
    public ArrayList<Model.Model_SPCT> GetAll_SPCT_Nu() throws SQLException {
        ArrayList<Model.Model_SPCT> lst = new ArrayList();
        String sql = "select SanPhamChiTiet.IDSanPhamChiTiet , SanPham.TenSanPham ,TheLoai, SanPhamChiTiet.DonGia , SanPhamChiTiet.SoLuong , SanPhamChiTiet.TrangThai ,LoaiDay.ten, ChatLieu.Ten , DeGiay.Ten , KichThuoc.ten , MauSac.ten , SanPhamChiTiet.IDKhuyenMai from SanPhamChiTiet "
                + "              join SanPham on SanPham.IDSanPham = SanPhamChiTiet.IDSanPham\n"
                + "              join LoaiDay on LoaiDay.IDLoaiDay = SanPhamChiTiet.IDLoaiDay\n"
                + "              join ChatLieu on ChatLieu.IDChatLieu = SanPhamChiTiet.IDChatLieu\n"
                + "              join DeGiay on DeGiay.IDDeGiay = SanPhamChiTiet.IDDeGiay\n"
                + "              join KichThuoc on KichThuoc.IDKichThuoc = SanPhamChiTiet.IDKichThuoc\n"
                + "              join MauSac on MauSac.IDMauSac = SanPhamChiTiet.IDMauSac where TheLoai =0";
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            Model_SPCT spct = new Model_SPCT();
            spct.setIDSPCT(rs.getString(1));
            spct.setTen(rs.getString(2));
            spct.setTheLoai(rs.getInt(3));
            spct.setDonGia(rs.getInt(4));
            spct.setSoLuong(rs.getInt(5));
            spct.setTrangThai(rs.getInt(6));
            spct.setTenLoaiDay(rs.getString(7));
            spct.setTenChatLieu(rs.getString(8));
            spct.setTenDeGiay(rs.getString(9));
            spct.setTenSize(rs.getString(10));
            spct.setTenMauSac(rs.getString(11));
            spct.setIDKhuyenMai(rs.getString(12));
            lst.add(spct);
        }
        return lst;
    }
    
    
    
    
    
    

    public int add_SPCT(String IDSPCT, String ID_KT, String ID_MS, String ID_SP, String ID_DG, String ID_LD, String ID_CL, int SL, int TL, int DG, int TT) throws SQLException {
        String sql = "insert SanPhamChiTiet(IDSanPhamChiTiet,IDKichThuoc,IDMauSac,IDSanPham,IDDeGiay,IDLoaiDay,IDChatLieu,SoLuong,TheLoai,DonGia,NgayTao,NgaySua,TrangThai,IDKhuyenMai) values(?,?,?,?,?,?,?,?,?,?,GETDATE(),GETDATE(),?,'KM001')";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, IDSPCT);
        ps.setString(2, ID_KT);
        ps.setString(3, ID_MS);
        ps.setString(4, ID_SP);
        ps.setString(5, ID_DG);
        ps.setString(6, ID_LD);
        ps.setString(7, ID_CL);
        ps.setInt(8, SL);
        ps.setInt(9, TL);
        ps.setInt(10, DG);
        ps.setInt(11, TT);
        return ps.executeUpdate();
    }

    public int update_SPCT(String IDSPCT, String ID_KT, String ID_MS, String ID_SP, String ID_DG, String ID_LD, String ID_CL, int SL, int TL, int DG, int TT) throws SQLException {
        String sql = "update SanPhamChiTiet set IDKichThuoc=?,IDMauSac=?,IDSanPham=?,IDDeGiay=?,IDLoaiDay=?,IDChatLieu=?,SoLuong=?,TheLoai=?,DonGia=?,NgaySua= GETDATE(),TrangThai=? where IDSanPhamChiTiet=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, ID_KT);
        ps.setString(2, ID_MS);
        ps.setString(3, ID_SP);
        ps.setString(4, ID_DG);
        ps.setString(5, ID_LD);
        ps.setString(6, ID_CL);
        ps.setInt(7, SL);
        ps.setInt(8, TL);
        ps.setInt(9, DG);
        ps.setInt(10, TT);
        ps.setString(11, IDSPCT);
        return ps.executeUpdate();
    }
    public ArrayList<Model.Model_SPCT> GetAll_SPCT_tim(String ma13) throws SQLException {
        ArrayList<Model.Model_SPCT> lst = new ArrayList();
        String sql = """
                     select SanPhamChiTiet.IDSanPhamChiTiet , SanPham.TenSanPham ,TheLoai, SanPhamChiTiet.DonGia , SanPhamChiTiet.SoLuong , SanPhamChiTiet.TrangThai ,LoaiDay.ten, ChatLieu.Ten , DeGiay.Ten , KichThuoc.ten , MauSac.ten from SanPhamChiTiet               join SanPham on SanPham.IDSanPham = SanPhamChiTiet.IDSanPham
                                   join LoaiDay on LoaiDay.IDLoaiDay = SanPhamChiTiet.IDLoaiDay
                                   join ChatLieu on ChatLieu.IDChatLieu = SanPhamChiTiet.IDChatLieu
                                   join DeGiay on DeGiay.IDDeGiay = SanPhamChiTiet.IDDeGiay
                                   join KichThuoc on KichThuoc.IDKichThuoc = SanPhamChiTiet.IDKichThuoc
                                   join MauSac on MauSac.IDMauSac = SanPhamChiTiet.IDMauSac
                                   where SanPhamChiTiet.IDSanPhamChiTiet like ?""";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1 , ma13);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Model_SPCT spct = new Model_SPCT();
            spct.setIDSPCT(rs.getString(1));
            spct.setTen(rs.getString(2));
            spct.setTheLoai(rs.getInt(3));
            spct.setDonGia(rs.getInt(4));
            spct.setSoLuong(rs.getInt(5));
            spct.setTrangThai(rs.getInt(6));
            spct.setTenLoaiDay(rs.getString(7));
            spct.setTenChatLieu(rs.getString(8));
            spct.setTenDeGiay(rs.getString(9));
            spct.setTenSize(rs.getString(10));
            spct.setTenMauSac(rs.getString(11));
            lst.add(spct);
        }
        return lst;
    }
    public int update_SoLuong_SPCT(int SoLuong,int TrangThai, String MaSPCT) throws SQLException {
        String sql = "update SanPhamChiTiet set SoLuong=?,TrangThai=? where IDSanPhamChiTiet=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, SoLuong);
        ps.setInt(2, TrangThai);
        ps.setString(3, MaSPCT);
        return ps.executeUpdate();
    }
}
