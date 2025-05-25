/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DBCon.DbConnection;
import Model.Model_voucher;
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
public class Responsitory_Voucher {

    public Connection con;

    public Responsitory_Voucher() {
        con = DbConnection.getConnection();
    }

    public ArrayList<Model_voucher> getAll_voucher() throws SQLException {
        ArrayList<Model_voucher> lst = new ArrayList<>();
        String sql = "select SoThuTu,IDVoucher,TenVoucher,PhanTramGiam,DonHangToiThieu,NgayBatDau,NgayKetThuc,GiaTriGiamToiDa,TrangThai from Voucher";
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            Model_voucher vc = new Model_voucher(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9));
            lst.add(vc);
        }
        return lst;
    }

    public void autoUpdate() throws SQLException {
        String sql = "update Voucher set TrangThai = 0 where NgayKetThuc < GETDATE()";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.executeUpdate();
    }
    
    public void autoUpdate1() throws SQLException {
        String sql = "update Voucher set TrangThai = 1 where NgayKetThuc > GETDATE()";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.executeUpdate();
    }

    public int add_voucher(Model_voucher vc) throws SQLException {
        String sql = "insert Voucher(IDVoucher,TenVoucher,PhanTramGiam,DonHangToiThieu,NgayBatDau,NgayKetThuc,GiaTriGiamToiDa,TrangThai) values (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, vc.getId());
        ps.setString(2, vc.getTen());
        ps.setInt(3, vc.getPhanTramGiam());
        ps.setInt(4, vc.getDonHangToiThieu());

        ps.setString(5, vc.getNgayBD());
        ps.setString(6, vc.getNgayKT());
        ps.setInt(7, vc.getGiaTriGiamToiDa());
        ps.setInt(8, vc.getTrangThai());
        return ps.executeUpdate();
    }

    public int update_voucher(Model_voucher vc) throws SQLException {
        String sql = "UPDATE Voucher SET TenVoucher = ?, PhanTramGiam = ?, DonHangToiThieu = ?, NgayBatDau = ?, NgayKetThuc = ?, GiaTriGiamToiDa = ?, TrangThai = ? WHERE IDVoucher = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, vc.getTen());
        ps.setInt(2, vc.getPhanTramGiam());
        ps.setInt(3, vc.getDonHangToiThieu());
        ps.setString(4, vc.getNgayBD());
        ps.setString(5, vc.getNgayKT());
        ps.setInt(6, vc.getGiaTriGiamToiDa());
        ps.setInt(7, vc.getTrangThai());
        ps.setString(8, vc.getId());
        return ps.executeUpdate();
    }
}
