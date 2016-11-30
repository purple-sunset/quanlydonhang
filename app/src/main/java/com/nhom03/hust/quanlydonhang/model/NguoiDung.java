package com.nhom03.hust.quanlydonhang.model;

/**
 * Created by Admin on 30/11/2016.
 */

public class NguoiDung {
    private int id;
    private String ten;
    private String matKhau;

    public NguoiDung(int id, String ten, String matKhau) {
        this.id = id;
        this.ten = ten;
        this.matKhau = matKhau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
