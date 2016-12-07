package com.nhom03.hust.quanlydonhang.model;

import java.io.Serializable;

/**
 * Created by Admin on 30/11/2016.
 */

public class NguoiDung implements Serializable {
    private String ten;
    private String matKhau;

    public NguoiDung(){}

    public NguoiDung(String ten, String matKhau) {
        this.ten = ten;
        this.matKhau = matKhau;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
