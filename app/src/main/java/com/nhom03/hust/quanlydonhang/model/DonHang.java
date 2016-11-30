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
    private String diaChi;
    private KhachHang khachHang;
    private ArrayList<ChiTietHoaDon> dsHang;

    public DonHang(int id, Date ngayGio, long cuocPhi, String diaChi, KhachHang khachHang, ArrayList<ChiTietHoaDon> dsHang) {
        this.id = id;
        this.ngayGio = ngayGio;
        this.cuocPhi = cuocPhi;
        this.diaChi = diaChi;
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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public ArrayList<ChiTietHoaDon> getDsHang() {
        return dsHang;
    }

    public void setDsHang(ArrayList<ChiTietHoaDon> dsHang) {
        this.dsHang = dsHang;
    }
}
