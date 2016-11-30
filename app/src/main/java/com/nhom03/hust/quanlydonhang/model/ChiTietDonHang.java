package com.nhom03.hust.quanlydonhang.model;

/**
 * Created by Admin on 30/11/2016.
 */

public class ChiTietDonHang {
    private int id;
    private String ten;
    private int soLuong;
    private long donGia;

    public ChiTietDonHang(int id, String ten, int soLuong, long donGia) {
        this.id = id;
        this.ten = ten;
        this.soLuong = soLuong;
        this.donGia = donGia;
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
}
