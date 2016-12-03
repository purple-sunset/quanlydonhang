package com.nhom03.hust.quanlydonhang.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 30/11/2016.
 */

public class KhachHang {

    @SerializedName("CustomerID")
    private String id;

    @SerializedName("ContactName")
    private String tenKH;

    @SerializedName("CompanyName")
    private String tenCT;

    @SerializedName("ContactTitle")
    private String tieuDe;

    @SerializedName("Address")
    private String diaChi;

    @SerializedName("City")
    private String thanhPho;

    @SerializedName("Region")
    private String vung;

    @SerializedName("Country")
    private String quocGia;

    @SerializedName("Phone")
    private String sdt;

    @SerializedName("Fax")
    private String fax;

    @SerializedName("PostalCode")
    private String maBuuChinh;

    public KhachHang(){}

    public KhachHang(String id, String tenKH, String tenCT, String tieuDe, String diaChi, String thanhPho,
                     String vung, String quocGia, String sdt, String fax, String maBuuChinh) {
        this.id = id;
        this.tenKH = tenKH;
        this.tenCT = tenCT;
        this.tieuDe = tieuDe;
        this.diaChi = diaChi;
        this.thanhPho = thanhPho;
        this.vung = vung;
        this.quocGia = quocGia;
        this.sdt = sdt;
        this.fax = fax;
        this.maBuuChinh = maBuuChinh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getTenCT() {
        return tenCT;
    }

    public void setTenCT(String tenCT) {
        this.tenCT = tenCT;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getThanhPho() {
        return thanhPho;
    }

    public void setThanhPho(String thanhPho) {
        this.thanhPho = thanhPho;
    }

    public String getVung() {
        return vung;
    }

    public void setVung(String vung) {
        this.vung = vung;
    }

    public String getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(String quocGia) {
        this.quocGia = quocGia;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMaBuuChinh() {
        return maBuuChinh;
    }

    public void setMaBuuChinh(String maBuuChinh) {
        this.maBuuChinh = maBuuChinh;
    }
}
