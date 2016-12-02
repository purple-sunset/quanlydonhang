package com.nhom03.hust.quanlydonhang.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Admin on 30/11/2016.
 */

public class DonHang {
    private int id;
    private Date ngayGio;
    private long cuocPhi;
    private String tenChuyenHang;
    private KhachHang khachHang;
    private ArrayList<ChiTietDonHang> dsHang;

    public DonHang() {

    }

    public DonHang(int id, Date ngayGio, long cuocPhi, String tenChuyenHang, KhachHang khachHang, ArrayList<ChiTietDonHang> dsHang) {
        this.id = id;
        this.ngayGio = ngayGio;
        this.cuocPhi = cuocPhi;
        this.tenChuyenHang = tenChuyenHang;
        this.khachHang = khachHang;
        this.dsHang = dsHang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getNgayGio() {
        return ngayGio;
    }

    public void setNgayGio(Date ngayGio) {
        this.ngayGio = ngayGio;
    }

    public long getCuocPhi() {
        return cuocPhi;
    }

    public void setCuocPhi(long cuocPhi) {
        this.cuocPhi = cuocPhi;
    }

    public String getTenChuyenHang() {
        return tenChuyenHang;
    }

    public void setTenChuyenHang(String tenChuyenHang) {
        this.tenChuyenHang = tenChuyenHang;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public ArrayList<ChiTietDonHang> getDsHang() {
        return dsHang;
    }

    public void setDsHang(ArrayList<ChiTietDonHang> dsHang) {
        this.dsHang = dsHang;
    }
}
