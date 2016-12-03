package com.nhom03.hust.quanlydonhang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 30/11/2016.
 */

public class HangHoa {

    @SerializedName("ProductID")
    private int id;

    @SerializedName("ProductName")
    private String ten;

    @SerializedName("Discontinued")
    private boolean huyBan;

    @SerializedName("ReorderLevel")
    private int reOrderLevel = 0;

    @SerializedName("QuantityPerUnit")
    private String chiTiet;

    @SerializedName("UnitPrice")
    private float donGia;

    @SerializedName("UnitsInStock")
    private int slTon;

    @SerializedName("UnitsOnOrder")
    private int slDat;

    @SerializedName("SupplierID")
    private int supplierId;

    @SerializedName("CategoryID")
    private int idTheLoai;

    @Expose
    private TheLoai theLoai;

    public HangHoa(){}

    public HangHoa(int id, String ten, boolean huyBan, int reOrderLevel, String chiTiet,
                   float donGia, int slTon, int slDat, int supplierId, int idTheLoai) {
        this.id = id;
        this.ten = ten;
        this.huyBan = huyBan;
        this.reOrderLevel = reOrderLevel;
        this.chiTiet = chiTiet;
        this.donGia = donGia;
        this.slTon = slTon;
        this.slDat = slDat;
        this.supplierId = supplierId;
        this.idTheLoai = idTheLoai;
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

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
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

    public TheLoai getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(TheLoai theLoai) {
        this.theLoai = theLoai;
        //this.idTheLoai = theLoai.getId();
    }

    public int getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(int idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

    public int getReOrderLevel() {
        return reOrderLevel;
    }

    public void setReOrderLevel(int reOrderLevel) {
        this.reOrderLevel = reOrderLevel;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
}

