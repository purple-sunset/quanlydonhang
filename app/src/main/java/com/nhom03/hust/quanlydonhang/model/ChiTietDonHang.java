package com.nhom03.hust.quanlydonhang.model;

/**
 * Created by Admin on 30/11/2016.
 */

public class ChiTietDonHang {
    private int id;
    private String ten;
    private int soLuong;
    private long donGia;
    private float giamGia;

    public ChiTietDonHang(){}

    public ChiTietDonHang(int id, String ten, int soLuong, long donGia, float giamGia) {
        this.id = id;
        this.ten = ten;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.giamGia = giamGia;
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public long getDonGia() {
        return donGia;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }

    public float getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(float giamGia) {
        this.giamGia = giamGia;
    }
}
