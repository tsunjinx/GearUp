/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.Model_SanPham;
import Model.Model_khachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import DBCon.DbConnection;

/**
 *
 * @author dovan
 */
public class Responsitory_khachHang {

    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;

    public Responsitory_khachHang() {
        con = DbConnection.getConnection();
    }

    public ArrayList<Model_khachHang> getAll() {
        sql = "select IDKhachHang,Ten,SoDienThoai,NGayTao,NgaySua,Email,GioiTinh,TrangThai from KhachHang";
        ArrayList<Model_khachHang> list = new ArrayList<>();
        try {
            con = DBCon.DbConnection.getConnection();
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()) {
                String IDKhachHang;
                String tenKH;
                String SDT;
                String ngayTao;
                String ngaySua;
                String email;
                int gioiTinh;
                int trangThai;
                IDKhachHang = res.getString(1);
                tenKH = res.getString(2);
                SDT = res.getString(3);
                ngayTao = res.getString(4);
                ngaySua = res.getString(5);
                email = res.getString(6);
                gioiTinh = res.getInt(7);
                trangThai = res.getInt(8);
                Model_khachHang kh = new Model_khachHang(IDKhachHang, tenKH, SDT, ngayTao, ngaySua, email, gioiTinh, trangThai);
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int add_khachHang(Model_khachHang ml) {
        sql = "insert into KhachHang(IDKhachHang,Ten,SoDienThoai,NgayTao,NgaySua,Email,GioiTinh,TrangThai) values (?,?,?,GETDATE(),GETDATE(),?,?,?) ";
        try {
            con = DBCon.DbConnection.getConnection();
            pre = con.prepareStatement(sql);
            pre.setObject(1, ml.getIDKhachHang());
            pre.setObject(2, ml.getTenKH());
            pre.setObject(3, ml.getSDT());
            pre.setObject(4, ml.getEmail());
            pre.setObject(5, ml.getGioiTinh());
            pre.setObject(6, ml.getTrangThai());
            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int update_khachHang(String IDKhachHang1, Model_khachHang ml) {
        sql = "update  KhachHang set IDKhachHang=?,Ten=?,SoDienThoai=?,NgaySua= GETDATE(),Email=?,GioiTinh=?,TrangThai=? where IDKhachHang=? ";
        try {
            con = DBCon.DbConnection.getConnection();
            pre = con.prepareStatement(sql);
            pre.setObject(1, ml.getIDKhachHang());
            pre.setObject(2, ml.getTenKH());
            pre.setObject(3, ml.getSDT());
            pre.setObject(4, ml.getEmail());
            pre.setObject(5, ml.getGioiTinh());
            pre.setObject(6, ml.getTrangThai());
            pre.setObject(7, IDKhachHang1);
            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String TimTen_TheoSDT(String sdt) throws SQLException{
        sql = "select Ten from KhachHang where SoDienThoai = ?";
        con = DbConnection.getConnection();
        pre = con.prepareStatement(sql);
        pre.setObject(1, sdt);
        res = pre.executeQuery();
        String i = null;
        while (res.next()) {            
            i = res.getString(1);
        }
        return i;
    }
    
    public ArrayList<Model_khachHang> timTen_KhachHang(String tenKH1) {
        // lấy toàn bộ dữ liệu từ datavbase ra list
        sql = "select IDKhachHang,Ten,SoDienThoai,NGayTao,NgaySua,Email,GioiTinh,TrangThai from KhachHang where Ten like ? ";
        ArrayList<Model_khachHang> list = new ArrayList();
        try {// kết nối data lấy dữ liệu ok
            con = DBCon.DbConnection.getConnection();
            pre = con.prepareStatement(sql);// chuẩn bị thực hiện lệnh
            pre.setObject(1, "%" + tenKH1 + "%");
            res = pre.executeQuery();// kết quả truy vấn
            // đọc dl từ rs ra list
            while (res.next()) {
                String IDKhachHang;
                String tenKH;
                String SDT;
                String ngayTao;
                String ngaySua;
                String email;
                int gioiTinh;
                int trangThai;
                IDKhachHang = res.getString(1);
                tenKH = res.getString(2);
                SDT = res.getString(3);
                ngayTao = res.getString(4);
                ngaySua = res.getString(5);
                email = res.getString(6);
                gioiTinh = res.getInt(7);
                trangThai = res.getInt(8);
                Model_khachHang kh = new Model_khachHang(IDKhachHang, tenKH, SDT, ngayTao, ngaySua, email, gioiTinh, trangThai);
                list.add(kh);
            }// đóng while sau khi đọc hết dl
            return list;
        } catch (Exception e) {// không kết nối được
            e.printStackTrace();// thông báo lỗi
            return null;
        }
    }
    
    
     public ArrayList<Model_khachHang> getAll_tt1() {
        sql = "select IDKhachHang,Ten,SoDienThoai,NGayTao,NgaySua,Email,GioiTinh,TrangThai from KhachHang where TrangThai = 1 ";
        ArrayList<Model_khachHang> list = new ArrayList<>();
        try {
            con = DBCon.DbConnection.getConnection();
            pre = con.prepareStatement(sql);
           
            res = pre.executeQuery();
            while (res.next()) {
                String IDKhachHang;
                String tenKH;
                String SDT;
                String ngayTao;
                String ngaySua;
                String email;
                int gioiTinh;
                int trangThai;
                IDKhachHang = res.getString(1);
                tenKH = res.getString(2);
                SDT = res.getString(3);
                ngayTao = res.getString(4);
                ngaySua = res.getString(5);
                email = res.getString(6);
                gioiTinh = res.getInt(7);
                trangThai = res.getInt(8);
                Model_khachHang kh = new Model_khachHang(IDKhachHang, tenKH, SDT, ngayTao, ngaySua, email, gioiTinh, trangThai);
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
      public ArrayList<Model_khachHang> getAll_tt0() {
        sql = "select IDKhachHang,Ten,SoDienThoai,NGayTao,NgaySua,Email,GioiTinh,TrangThai from KhachHang where TrangThai = 0 ";
        ArrayList<Model_khachHang> list = new ArrayList<>();
        try {
            con = DBCon.DbConnection.getConnection();
            pre = con.prepareStatement(sql);
           
            res = pre.executeQuery();
            while (res.next()) {
                String IDKhachHang;
                String tenKH;
                String SDT;
                String ngayTao;
                String ngaySua;
                String email;
                int gioiTinh;
                int trangThai;
                IDKhachHang = res.getString(1);
                tenKH = res.getString(2);
                SDT = res.getString(3);
                ngayTao = res.getString(4);
                ngaySua = res.getString(5);
                email = res.getString(6);
                gioiTinh = res.getInt(7);
                trangThai = res.getInt(8);
                Model_khachHang kh = new Model_khachHang(IDKhachHang, tenKH, SDT, ngayTao, ngaySua, email, gioiTinh, trangThai);
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
        public ArrayList<Model_khachHang> getAll_timMa(String maKH) {
        sql = "select IDKhachHang,Ten,SoDienThoai,NGayTao,NgaySua,Email,GioiTinh,TrangThai from KhachHang where IDKhachHang=? ";
        ArrayList<Model_khachHang> list = new ArrayList<>();
        try {
            con = DBCon.DbConnection.getConnection();
            pre = con.prepareStatement(sql);
           pre.setObject(1, maKH);
            res = pre.executeQuery();
            while (res.next()) {
                String IDKhachHang;
                String tenKH;
                String SDT;
                String ngayTao;
                String ngaySua;
                String email;
                int gioiTinh;
                int trangThai;
                IDKhachHang = res.getString(1);
                tenKH = res.getString(2);
                SDT = res.getString(3);
                ngayTao = res.getString(4);
                ngaySua = res.getString(5);
                email = res.getString(6);
                gioiTinh = res.getInt(7);
                trangThai = res.getInt(8);
                Model_khachHang kh = new Model_khachHang(IDKhachHang, tenKH, SDT, ngayTao, ngaySua, email, gioiTinh, trangThai);
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
