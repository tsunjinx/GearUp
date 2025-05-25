/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.Model_SanPham;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author dovan
 */
public class Responsitory_SanPham {

    private Connection con = null;
    private PreparedStatement pre = null;
    private ResultSet res = null;
    private String sql = null;

    public ArrayList<Model_SanPham> getAll() {
        sql = "select SoThuTu , IDSanPham, TenSanPham , NgayTao , NgaySua , TrangThai from SanPham";
        ArrayList<Model_SanPham> list = new ArrayList<>();
        try {
            con = DBCon.DbConnection.getConnection();
            pre = con.prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()) {
                int STT;
                String IDSanPham;
                String tenSanPham;
                String NgayTao;
                String ngaySua;
                int trangThai;
                STT = res.getInt(1);
                IDSanPham = res.getString(2);
                tenSanPham = res.getString(3);
                NgayTao = res.getString(4);
                ngaySua = res.getString(5);
                trangThai = res.getInt(6);
                Model_SanPham sp = new Model_SanPham(STT, IDSanPham, tenSanPham, NgayTao, ngaySua, trangThai);
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int add_SanPham(Model_SanPham ml) {
        sql = "insert into SanPham(IDSanPham, TenSanPham , NgayTao , NgaySua , TrangThai) values (?,?,GETDATE(),GETDATE(),?) ";
        try {
            con = DBCon.DbConnection.getConnection();
            pre = con.prepareStatement(sql);
            pre.setObject(1, ml.getIDSanPham());
            pre.setObject(2, ml.getTenSanPham());
            pre.setObject(3, ml.getTrangThai());
            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int update_SanPham(String ID, Model_SanPham ml) {
        sql = "update  SanPham set IDSanPham=?, TenSanPham=? , NgaySua = GETDATE() , TrangThai=? where IDSanPham=? ";
        try {
            con = DBCon.DbConnection.getConnection();
            pre = con.prepareStatement(sql);
            pre.setObject(1, ml.getIDSanPham());
            pre.setObject(2, ml.getTenSanPham());
            pre.setObject(3, ml.getTrangThai());
            pre.setObject(4, ID);
            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int delete_SanPham(String ma) {
        sql = "delete from SanPham where IDSanPham like ? ";
        try {
            con = DBCon.DbConnection.getConnection();
            pre = con.prepareStatement(sql);
            pre.setObject(1, ma);

            return pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
     public ArrayList<Model_SanPham> getAll_loc(Model_SanPham ml) {
        sql = "select SoThuTu , IDSanPham, TenSanPham , NgayTao , NgaySua , TrangThai from SanPham where TrangThai =?";
        ArrayList<Model_SanPham> list = new ArrayList<>();
        try {
            con = DBCon.DbConnection.getConnection();
            pre = con.prepareStatement(sql);
            pre.setObject(1,1 );
            res = pre.executeQuery();
            while (res.next()) {
                int STT;
                String IDSanPham;
                String tenSanPham;
                String NgayTao;
                String ngaySua;
                int trangThai;
                STT = res.getInt(1);
                IDSanPham = res.getString(2);
                tenSanPham = res.getString(3);
                NgayTao = res.getString(4);
                ngaySua = res.getString(5);
                trangThai = res.getInt(6);
                Model_SanPham sp = new Model_SanPham(STT, IDSanPham, tenSanPham, NgayTao, ngaySua, trangThai);
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
      public ArrayList<Model_SanPham> getAll_loc2(Model_SanPham ml) {
        sql = "select SoThuTu , IDSanPham, TenSanPham , NgayTao , NgaySua , TrangThai from SanPham where TrangThai =?";
        ArrayList<Model_SanPham> list = new ArrayList<>();
        try {
            con = DBCon.DbConnection.getConnection();
            pre = con.prepareStatement(sql);
            pre.setObject(1,0 );
            res = pre.executeQuery();
            while (res.next()) {
                int STT;
                String IDSanPham;
                String tenSanPham;
                String NgayTao;
                String ngaySua;
                int trangThai;
                STT = res.getInt(1);
                IDSanPham = res.getString(2);
                tenSanPham = res.getString(3);
                NgayTao = res.getString(4);
                ngaySua = res.getString(5);
                trangThai = res.getInt(6);
                Model_SanPham sp = new Model_SanPham(STT, IDSanPham, tenSanPham, NgayTao, ngaySua, trangThai);
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
      public ArrayList<Model_SanPham> timTen(String ma1){
        // lấy toàn bộ dữ liệu từ datavbase ra list
        sql="select SoThuTu , IDSanPham, TenSanPham , NgayTao , NgaySua , TrangThai from SanPham where IDSanPham like ?";
        ArrayList<Model_SanPham> list_SP= new ArrayList();
        try {// kết nối data lấy dữ liệu ok
            con = DBCon.DbConnection.getConnection();
            pre=con.prepareStatement(sql);// chuẩn bị thực hiện lệnh
            pre.setObject(1, "%"+ma1+"%");
            res=pre.executeQuery();// kết quả truy vấn
            // đọc dl từ rs ra list
            while(res.next()){
                int STT;
                String IDSanPham;
                String tenSanPham;
                String NgayTao;
                String ngaySua;
                int trangThai;
                STT = res.getInt(1);
                IDSanPham = res.getString(2);
                tenSanPham = res.getString(3);
                NgayTao = res.getString(4);
                ngaySua = res.getString(5);
                trangThai = res.getInt(6);
                Model_SanPham sp = new Model_SanPham(STT, IDSanPham, tenSanPham, NgayTao, ngaySua, trangThai);
                list_SP.add(sp);
            }// đóng while sau khi đọc hết dl
            return list_SP;
        } catch (Exception e) {// không kết nối được
            e.printStackTrace();// thông báo lỗi
            return null;
        }
    }
}
