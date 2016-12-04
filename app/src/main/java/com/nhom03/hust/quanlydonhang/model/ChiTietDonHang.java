package com.nhom03.hust.quanlydonhang.model;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nhom03.hust.quanlydonhang.rest.APIChiTietDonHang;

/**
 * Created by Admin on 30/11/2016.
 */

public class ChiTietDonHang {

    @SerializedName("OrderID")
    private int idDonHang;

    @SerializedName("ProductID")
    private int idHangHoa;

    @Expose
    private String ten;

    @SerializedName("Quantity")
    private int soLuong;

    @SerializedName("UnitPrice")
    private float donGia;

    @SerializedName("Discount")
    private float giamGia;

    public ChiTietDonHang(){}

    public ChiTietDonHang(int idDonHang, int idHangHoa, int soLuong, float donGia, float giamGia) {
        this.idDonHang = idDonHang;
        this.idHangHoa = idHangHoa;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.giamGia = giamGia;
    }

    public int getIdDonHang() {
        return idDonHang;
    }

    public void setIdDonHang(int idDonHang) {
        this.idDonHang = idDonHang;
    }

    public int getIdHangHoa() {
        return idHangHoa;
    }

    public void setIdHangHoa(int idHangHoa) {
        this.idHangHoa = idHangHoa;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public float getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(float giamGia) {
        this.giamGia = giamGia;
    }

}
