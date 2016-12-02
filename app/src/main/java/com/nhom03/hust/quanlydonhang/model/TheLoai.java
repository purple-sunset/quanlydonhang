package com.nhom03.hust.quanlydonhang.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 01/12/2016.
 */

public class TheLoai {

    private int id;

    private String ten;

    private String moTa;

    public TheLoai(){}

    public TheLoai(int id, String ten, String moTa) {
        this.id = id;
        this.ten = ten;
        this.moTa = moTa;
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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
