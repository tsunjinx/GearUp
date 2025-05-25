/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author PonYanki
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Model_Login {
    private Integer idNhanVien;
    private String taiKhoan;
    private String matKhau;
    private String ten;
    private boolean trangThai;
    
}
