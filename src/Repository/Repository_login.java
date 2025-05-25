/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

/**
 *
 * @author PonYanki
 */
import java.sql.*;
public class Repository_login {
    public boolean dangNhap(String taiKhoan, String matKhau){
    String sql = "SELECT TenDangNhap, MatKhau from NhanVien WHERE TenDangNhap = ? AND MatKhau = ?";
     try {
            Connection con = DBCon.DbConnection.getConnection();
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setObject(1, taiKhoan);
            pr.setObject(2, matKhau);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                return true;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
        }
    }

