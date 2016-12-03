package com.nhom03.hust.quanlydonhang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Admin on 30/11/2016.
 */

public class DonHang {

    @SerializedName("OrderID")
    private int id;

    @SerializedName("EmployeeID")
    private int idNhanVien = 1;

    @SerializedName("ISOOrderDate")
    private Date ngayGio;

    @SerializedName("Freight")
    private float cuocPhi;

    @SerializedName("ShipName")
    private String tenChuyenHang;

    @SerializedName("CustomerID")
    private String idKhachHang;

    @SerializedName("ShipAddress")
    private String diaChiGiaoHang;

    @SerializedName("ShipCity")
    private String thanhPhoGiaoHang;

    @SerializedName("ShipPostalCode")
    private String mbcGiaoHang;

    @SerializedName("ShipRegion")
    private String vungGiaoHang;

    @SerializedName("ShipCountry")
    private String quocGiaGiaoHang;

    @SerializedName("ShipVia")
    private int shipVia = 1;

    @Expose
    private KhachHang khachHang;

    @Expose
    private ArrayList<ChiTietDonHang> dsHang;

    public DonHang() {

    }

    public DonHang(int id, int idNhanVien, Date ngayGio, float cuocPhi, String tenChuyenHang,
                   String idKhachHang, String diaChiGiaoHang, String thanhPhoGiaoHang, String mbcGiaoHang, String vungGiaoHang, String quocGiaGiaoHang, int shipVia) {
        this.id = id;
        this.idNhanVien = idNhanVien;
        this.ngayGio = ngayGio;
        this.cuocPhi = cuocPhi;
        this.tenChuyenHang = tenChuyenHang;
        this.idKhachHang = idKhachHang;
        this.diaChiGiaoHang = diaChiGiaoHang;
        this.thanhPhoGiaoHang = thanhPhoGiaoHang;
        this.mbcGiaoHang = mbcGiaoHang;
        this.vungGiaoHang = vungGiaoHang;
        this.quocGiaGiaoHang = quocGiaGiaoHang;
        this.shipVia = shipVia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public Date getNgayGio() {
        return ngayGio;
    }

    public void setNgayGio(Date ngayGio) {
        this.ngayGio = ngayGio;
    }

    public float getCuocPhi() {
        return cuocPhi;
    }

    public void setCuocPhi(float cuocPhi) {
        this.cuocPhi = cuocPhi;
    }

    public String getTenChuyenHang() {
        return tenChuyenHang;
    }

    public void setTenChuyenHang(String tenChuyenHang) {
        this.tenChuyenHang = tenChuyenHang;
    }

    public String getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(String idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getDiaChiGiaoHang() {
        return diaChiGiaoHang;
    }

    public void setDiaChiGiaoHang(String diaChiGiaoHang) {
        this.diaChiGiaoHang = diaChiGiaoHang;
    }

    public String getThanhPhoGiaoHang() {
        return thanhPhoGiaoHang;
    }

    public void setThanhPhoGiaoHang(String thanhPhoGiaoHang) {
        this.thanhPhoGiaoHang = thanhPhoGiaoHang;
    }

    public String getMbcGiaoHang() {
        return mbcGiaoHang;
    }

    public void setMbcGiaoHang(String mbcGiaoHang) {
        this.mbcGiaoHang = mbcGiaoHang;
    }

    public String getVungGiaoHang() {
        return vungGiaoHang;
    }

    public void setVungGiaoHang(String vungGiaoHang) {
        this.vungGiaoHang = vungGiaoHang;
    }

    public String getQuocGiaGiaoHang() {
        return quocGiaGiaoHang;
    }

    public void setQuocGiaGiaoHang(String quocGiaGiaoHang) {
        this.quocGiaGiaoHang = quocGiaGiaoHang;
    }

    public int getShipVia() {
        return shipVia;
    }

    public void setShipVia(int shipVia) {
        this.shipVia = shipVia;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
        this.idKhachHang = khachHang.getId();
    }

    public ArrayList<ChiTietDonHang> getDsHang() {
        return dsHang;
    }

    public void setDsHang(ArrayList<ChiTietDonHang> dsHang) {
        this.dsHang = dsHang;
    }
}
