package com.nhom03.hust.quanlydonhang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Admin on 01/12/2016.
 */

public class TheLoai {

    @SerializedName("CategoryID")
    private int id;

    @SerializedName("CategoryName")
    private String ten;

    @SerializedName("Description")
    private String moTa;

    @Expose
    private ArrayList<HangHoa> dsHangHoa;

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

    public ArrayList<HangHoa> getDsHangHoa() {
        return dsHangHoa;
    }

    public void setDsHangHoa(ArrayList<HangHoa> dsHangHoa) {
        this.dsHangHoa = dsHangHoa;
    }
}
