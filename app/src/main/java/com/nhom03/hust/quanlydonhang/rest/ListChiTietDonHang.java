package com.nhom03.hust.quanlydonhang.rest;

import android.app.Notification;

import com.google.gson.annotations.SerializedName;
import com.nhom03.hust.quanlydonhang.model.ChiTietDonHang;

import java.util.List;

/**
 * Created by Admin on 03/12/2016.
 */
public class ListChiTietDonHang {

    @SerializedName("GetAllOrderDetailResult")
    List<ChiTietDonHang> chiTietDonHangList;

    public List<ChiTietDonHang> getChiTietDonHangList() {
        return chiTietDonHangList;
    }

    public void setChiTietDonHangList(List<ChiTietDonHang> chiTietDonHangList) {
        this.chiTietDonHangList = chiTietDonHangList;
    }
}
