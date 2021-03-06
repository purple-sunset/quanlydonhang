package com.nhom03.hust.quanlydonhang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Admin on 30/11/2016.
 */

public class HangHoaCuaDonHang implements Serializable {

    @SerializedName("OrderID")
    private long idDonHang;

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

    public HangHoaCuaDonHang(){}

    public HangHoaCuaDonHang(long idDonHang, int idHangHoa, int soLuong, float donGia, float giamGia) {
        this.idDonHang = idDonHang;
        this.idHangHoa = idHangHoa;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.giamGia = giamGia;
    }

    public float thanhTien(){
        float thanhTien = 0;

        thanhTien = this.donGia*this.getSoLuong()*(1- this.getGiamGia());

        return thanhTien;
    }

    public long getIdDonHang() {
        return idDonHang;
    }

    public void setIdDonHang(long idDonHang) {
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
