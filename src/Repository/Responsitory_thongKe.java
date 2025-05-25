/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;
import Model.Model_thongke;
import java.util.ArrayList;

import java.sql.*;
/**
 *
 * @author dovan
 */
public class Responsitory_thongKe {
     
         private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;

    public ArrayList<Model_thongke> getAll(int nam) {
        sql = "select SoThuTu,IDHoaDon,IDKhachHang,NgayMuaHang,TongTien,TrangThai from HoaDon where TrangThai=1 and YEAR(NgayMuaHang) = ? ";
        ArrayList<Model_thongke> list = new ArrayList<>();
        try {
            con = DBCon.DbConnection.getConnection();
            pre = con.prepareStatement(sql);
                            pre.setObject(1, nam);
            res = pre.executeQuery();
            while (res.next()) {
                int STT;
                String idHoDon;
                String idKhachHang;
                String NgayTao;
                int tongTien;
                int trangThai;
                STT = res.getInt(1);
                idHoDon = res.getString(2);
                idKhachHang = res.getString(3);
                NgayTao = res.getString(4);               
                tongTien = res.getInt(5);
                trangThai = res.getInt(6);
                
               Model_thongke tk  = new Model_thongke(STT, idHoDon, idKhachHang, NgayTao, tongTien, trangThai) ;             
                  list.add(tk);        
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
        
           public ArrayList<Model_thongke> getAll_huy(int nam) {
        sql = "select SoThuTu,IDHoaDon,IDKhachHang,NgayMuaHang,TongTien,TrangThai from HoaDon where TrangThai=2 and YEAR(NgayMuaHang) = ? ";
        ArrayList<Model_thongke> list = new ArrayList<>();
        try {
            con = DBCon.DbConnection.getConnection();
            pre = con.prepareStatement(sql);
                            pre.setObject(1, nam);
            res = pre.executeQuery();
            while (res.next()) {
                int STT;
                String idHoDon;
                String idKhachHang;
                String NgayTao;
                int tongTien;
                int trangThai;
                STT = res.getInt(1);
                idHoDon = res.getString(2);
                idKhachHang = res.getString(3);
                NgayTao = res.getString(4);               
                tongTien = res.getInt(5);
                trangThai = res.getInt(6);
                
               Model_thongke tk  = new Model_thongke(STT, idHoDon, idKhachHang, NgayTao, tongTien, trangThai) ;             
                  list.add(tk);        
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
                  public ArrayList<Model_thongke> getAll_homNay() {
        sql = " SELECT SoThuTu, IDHoaDon, IDKhachHang, NgayMuaHang, TongTien, TrangThai\n" +
"FROM HoaDon\n" +
"WHERE \n" +
"  CONVERT(DATE, NgayMuaHang) = CONVERT(DATE, GETDATE());";
        ArrayList<Model_thongke> list = new ArrayList<>();
        try {
            con = DBCon.DbConnection.getConnection();
            pre = con.prepareStatement(sql);
                           
            res = pre.executeQuery();
            while (res.next()) {
                int STT;
                String idHoDon;
                String idKhachHang;
                String NgayTao;
                int tongTien;
                int trangThai;
                STT = res.getInt(1);
                idHoDon = res.getString(2);
                idKhachHang = res.getString(3);
                NgayTao = res.getString(4);               
                tongTien = res.getInt(5);
                trangThai = res.getInt(6);
                
               Model_thongke tk  = new Model_thongke(STT, idHoDon, idKhachHang, NgayTao, tongTien, trangThai) ;             
                  list.add(tk);        
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
                                public ArrayList<Model_thongke> getAll_theoThang(int thang) {
        sql = "SELECT SoThuTu, IDHoaDon, IDKhachHang, NgayMuaHang, TongTien, TrangThai \" +\n" +
"          \"FROM HoaDon WHERE YEAR(NgayMuaHang)= 2024 and MONTH(NgayMuaHang) = ? ";
        ArrayList<Model_thongke> list = new ArrayList<>();
        try {
            con = DBCon.DbConnection.getConnection();
            pre = con.prepareStatement(sql);
                   pre.setObject(1, thang);         
            res = pre.executeQuery();
            while (res.next()) {
                int STT;
                String idHoDon;
                String idKhachHang;
                String NgayTao;
                int tongTien;
                int trangThai;
                STT = res.getInt(1);
                idHoDon = res.getString(2);
                idKhachHang = res.getString(3);
                NgayTao = res.getString(4);               
                tongTien = res.getInt(5);
                trangThai = res.getInt(6);
                
               Model_thongke tk  = new Model_thongke(STT, idHoDon, idKhachHang, NgayTao, tongTien, trangThai) ;             
                  list.add(tk);        
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<Model_thongke> getAll_tim(String startDate, String endDate) {
        String sql = "SELECT SoThuTu, IDHoaDon, IDKhachHang, NgayMuaHang, TongTien, TrangThai " +
                     "FROM HoaDon WHERE NgayMuaHang BETWEEN ? AND ?";
        ArrayList<Model_thongke> list = new ArrayList<>();
        try (Connection con = DBCon.DbConnection.getConnection();
             PreparedStatement pre = con.prepareStatement(sql)) {
            
            // Thiết lập giá trị cho các tham số ngày bắt đầu và ngày kết thúc
            pre.setString(1, startDate);
            pre.setString(2, endDate);

            // Thực thi câu lệnh truy vấn
            ResultSet res = pre.executeQuery();
            
            // Duyệt qua kết quả và thêm vào danh sách
            while (res.next()) {
                int STT = res.getInt(1);
                String idHoDon = res.getString(2);
                String idKhachHang = res.getString(3);
                String NgayTao = res.getString(4);
                int tongTien = res.getInt(5);
                int trangThai = res.getInt(6);

                // Tạo đối tượng Model_thongke và thêm vào danh sách
                Model_thongke tk = new Model_thongke(STT, idHoDon, idKhachHang, NgayTao, tongTien, trangThai);
                list.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    
    
    public ArrayList<Model_thongke> getAll_tatca() {
        sql = "select SoThuTu,IDHoaDon,IDKhachHang,NgayMuaHang,TongTien,TrangThai from HoaDon ";
        ArrayList<Model_thongke> list = new ArrayList<>();
        try {
            con = DBCon.DbConnection.getConnection();
            pre = con.prepareStatement(sql);
                            
            res = pre.executeQuery();
            while (res.next()) {
                int STT;
                String idHoDon;
                String idKhachHang;
                String NgayTao;
                int tongTien;
                int trangThai;
                STT = res.getInt(1);
                idHoDon = res.getString(2);
                idKhachHang = res.getString(3);
                NgayTao = res.getString(4);               
                tongTien = res.getInt(5);
                trangThai = res.getInt(6);
                
               Model_thongke tk  = new Model_thongke(STT, idHoDon, idKhachHang, NgayTao, tongTien, trangThai) ;             
                  list.add(tk);        
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
