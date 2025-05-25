/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;
import Model.Model_DeGiay;
import Model.Model_chatLieu;
import Model.Model_kichThuoc;
import Model.Model_loaiDay;
import Model.Model_mauSac;
import DBCon.DbConnection;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author dovan
 */
public class Responsitory_ThuocTinhSanPham {
    public Connection con = null;
    public PreparedStatement pre = null;
    public  Statement stm = null;
    public  ResultSet rs = null;
    public String sql = null;

    public Responsitory_ThuocTinhSanPham() {
        con = DbConnection.getConnection();
    }
    
    public ArrayList<Model_loaiDay> getAll_LoaiDay() throws SQLException{
        sql = "select IDLoaiDay,Ten,TrangThai from LoaiDay";
        ArrayList<Model_loaiDay> lst_LoaiDay = new ArrayList<>();
        con = DBCon.DbConnection.getConnection();
        stm = con.createStatement();
        rs = stm.executeQuery(sql);
        while (rs.next()) {            
            Model_loaiDay ms = new Model_loaiDay(rs.getString(1), rs.getString(2), rs.getInt(3));
            lst_LoaiDay.add(ms);
        }
        return lst_LoaiDay;
    }
    public int add_LoaiDay(Model_loaiDay ld) throws SQLException{
        con = DBCon.DbConnection.getConnection();
        sql = "insert LoaiDay(IDLoaiDay,Ten,NgayTao,NgaySua,TrangThai) values(?,?,GETDATE(),GETDATE(),?)";
        pre = con.prepareStatement(sql);
        pre.setObject(1, ld.getMaLoaiGiay());
        pre.setObject(2, ld.getLoaiGiay());
        pre.setObject(3, ld.getTrangthai());
        return pre.executeUpdate();
    }
    public int update_LoaiDay(Model_loaiDay ld) throws SQLException{
        sql = "update LoaiDay set Ten = ?,NgaySua = GETDATE(),TrangThai = ? where IDLoaiDay = ?";
        con = DBCon.DbConnection.getConnection();
        pre = con.prepareStatement(sql);
        pre.setObject(1, ld.getLoaiGiay());
        pre.setObject(2, ld.getTrangthai());
        pre.setObject(3, ld.getMaLoaiGiay());
        return pre.executeUpdate();
    }
    public ArrayList<Model_mauSac> getAll_mauSac() throws SQLException{
        sql = "select IDMauSac,Ten,TrangThai from MauSac";
        ArrayList<Model_mauSac> lst_MauSac = new ArrayList<>();
        con = DBCon.DbConnection.getConnection();
        stm = con.createStatement();
        rs = stm.executeQuery(sql);
        while (rs.next()) {            
            Model_mauSac ms = new Model_mauSac(rs.getString(1), rs.getString(2), rs.getInt(3));
            lst_MauSac.add(ms);
        }
        return lst_MauSac;
    }
    public int add_MauSac(Model_mauSac ld) throws SQLException{
        con = DBCon.DbConnection.getConnection();
        sql = "insert MauSac(IDMauSac,Ten,NgayTao,NgaySua,TrangThai) values(?,?,GETDATE(),GETDATE(),?)";
        pre = con.prepareStatement(sql);
        pre.setObject(1, ld.getMaMauSac());
        pre.setObject(2, ld.getTenMauSac());
        pre.setObject(3, ld.getTrangThai());
        return pre.executeUpdate();
    }
    public int update_MauSac(Model_mauSac ld) throws SQLException{
        sql = "update MauSac set Ten = ?,NgaySua = GETDATE(),TrangThai = ? where IDMauSac = ?";
        con = DBCon.DbConnection.getConnection();
        pre = con.prepareStatement(sql);
        pre.setObject(1, ld.getTenMauSac());
        pre.setObject(2, ld.getTrangThai());
        pre.setObject(3, ld.getMaMauSac());
        return pre.executeUpdate();
    }
    public ArrayList<Model_kichThuoc> getAll_KichThuoc() throws SQLException{
        sql = "select IDKichThuoc,Ten,TrangThai from KichThuoc";
        ArrayList<Model_kichThuoc> lst_KichThuoc = new ArrayList<>();
        con = DBCon.DbConnection.getConnection();
        stm = con.createStatement();
        rs = stm.executeQuery(sql);
        while (rs.next()) {            
            Model_kichThuoc kt = new Model_kichThuoc(rs.getString(1), rs.getString(2), rs.getInt(3));
            lst_KichThuoc.add(kt);
        }
        return lst_KichThuoc;
    }
    public int add_KichThuoc(Model_kichThuoc ld) throws SQLException{
        con = DBCon.DbConnection.getConnection();
        sql = "insert KichThuoc(IDKichThuoc,Ten,NgayTao,NgaySua,TrangThai) values(?,?,GETDATE(),GETDATE(),?)";
        pre = con.prepareStatement(sql);
        pre.setObject(1, ld.getMaKichThuoc());
        pre.setObject(2, ld.getKichThuoc());
        pre.setObject(3, ld.getTrangThai());
        return pre.executeUpdate();
    }
    public int update_KichThuoc(Model_kichThuoc ld) throws SQLException{
        sql = "update KichThuoc set Ten = ?,NgaySua = GETDATE(),TrangThai = ? where IDKichThuoc = ?";
        con = DBCon.DbConnection.getConnection();
        pre = con.prepareStatement(sql);
        pre.setObject(1, ld.getKichThuoc());
        pre.setObject(2, ld.getTrangThai());
        pre.setObject(3, ld.getMaKichThuoc());
        return pre.executeUpdate();
    }
    public ArrayList<Model_chatLieu> getAll_ChatLieu() throws SQLException{
        sql = "select IDChatLieu,Ten,TrangThai from ChatLieu";
        ArrayList<Model_chatLieu> lst_ChatLieu = new ArrayList<>();
        con = DBCon.DbConnection.getConnection();
        stm = con.createStatement();
        rs = stm.executeQuery(sql);
        while (rs.next()) {            
            Model_chatLieu cl = new Model_chatLieu(rs.getString(1), rs.getString(2), rs.getInt(3));
            lst_ChatLieu.add(cl);
        }
        return lst_ChatLieu;
    }
    public int add_ChatLieu(Model_chatLieu ld) throws SQLException{
        con = DBCon.DbConnection.getConnection();
        sql = "insert ChatLieu(IDChatLieu,Ten,NgayTao,NgaySua,TrangThai) values(?,?,GETDATE(),GETDATE(),?)";
        pre = con.prepareStatement(sql);
        pre.setObject(1, ld.getMaChatLieu());
        pre.setObject(2, ld.getTenChatLieu());
        pre.setObject(3, ld.getTrangThai());
        return pre.executeUpdate();
    }
    public int update_ChatLieu(Model_chatLieu ld) throws SQLException{
        sql = "update ChatLieu set Ten = ?,NgaySua = GETDATE(),TrangThai = ? where IDChatLieu = ?";
        con = DBCon.DbConnection.getConnection();
        pre = con.prepareStatement(sql);
        pre.setObject(1, ld.getTenChatLieu());
        pre.setObject(2, ld.getTrangThai());
        pre.setObject(3, ld.getMaChatLieu());
        return pre.executeUpdate();
    }
    public ArrayList<Model_DeGiay> getAll_DeGiay() throws SQLException{
        sql = "select IDDeGiay,Ten,TrangThai from DeGiay";
        ArrayList<Model_DeGiay> lst_DeGiay = new ArrayList<>();
        con = DBCon.DbConnection.getConnection();
        stm = con.createStatement();
        rs = stm.executeQuery(sql);
        while (rs.next()) {            
            Model_DeGiay DG = new Model_DeGiay(rs.getString(1), rs.getString(2), rs.getInt(3));
            lst_DeGiay.add(DG);
        }
        return lst_DeGiay;
    }
    public int add_DeGiay(Model_DeGiay ld) throws SQLException{
        con = DBCon.DbConnection.getConnection();
        sql = "insert DeGiay(IDDeGiay,Ten,NgayTao,NgaySua,TrangThai) values(?,?,GETDATE(),GETDATE(),?)";
        pre = con.prepareStatement(sql);
        pre.setObject(1, ld.getMaDeGiay());
        pre.setObject(2, ld.getTenDeGiay());
        pre.setObject(3, ld.getTrangThai());
        return pre.executeUpdate();
    }
    public int update_DeGiay(Model_DeGiay ld) throws SQLException{
        sql = "update DeGiay set Ten = ?,NgaySua = GETDATE(),TrangThai = ? where IDDeGiay = ?";
        con = DBCon.DbConnection.getConnection();
        pre = con.prepareStatement(sql);
        pre.setObject(1, ld.getTenDeGiay());
        pre.setObject(2, ld.getTrangThai());
        pre.setObject(3, ld.getMaDeGiay());
        return pre.executeUpdate();
    }
}
