package com.nhom03.hust.quanlydonhang.model;

/**
 * Created by Admin on 30/11/2016.
 */

public class HangHoa {
    private int id;
    private String ten;
    private boolean huyBan;
    private long donGia;
    private int slTon;
    private int slDat;
    private String chiTiet;

    public HangHoa(int id, String ten, boolean huyBan, long donGia,
                   int slTon, int slDat, String chiTiet) {
        this.id = id;
        this.ten = ten;
        this.huyBan = huyBan;
        this.donGia = donGia;
        this.slTon = slTon;
        this.slDat = slDat;
        this.chiTiet = chiTiet;
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

    public boolean isHuyBan() {
        return huyBan;
    }

    public void setHuyBan(boolean huyBan) {
        this.huyBan = huyBan;
    }

    public long getDonGia() {
        return donGia;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }

    public int getSlTon() {
        return slTon;
    }

    public void setSlTon(int slTon) {
        this.slTon = slTon;
    }

    public int getSlDat() {
        return slDat;
    }

    public void setSlDat(int slDat) {
        this.slDat = slDat;
    }

    public String getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(String chiTiet) {
        this.chiTiet = chiTiet;
    }
}

