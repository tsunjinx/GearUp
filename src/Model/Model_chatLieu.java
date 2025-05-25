/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dovan
 */
public class Model_chatLieu {

    private String maChatLieu;
    private String tenChatLieu;
    private int trangThai;

    public void setMaChatLieu(String maChatLieu) {
        this.maChatLieu = maChatLieu;
    }

    public void setTenChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaChatLieu() {
        return maChatLieu;
    }

    public String getTenChatLieu() {
        return tenChatLieu;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public Model_chatLieu(String maChatLieu, String tenChatLieu, int trangThai) {
        this.maChatLieu = maChatLieu;
        this.tenChatLieu = tenChatLieu;
        this.trangThai = trangThai;
    }

    public Model_chatLieu() {
    }

    public Object[] toDataRow() {
        return new Object[]{
            this.getMaChatLieu(), this.getTenChatLieu(), this.getTrangThai() == 1 ? "Đang Hoạt động" : "Không Hoạt Động"
        };
    }
}
