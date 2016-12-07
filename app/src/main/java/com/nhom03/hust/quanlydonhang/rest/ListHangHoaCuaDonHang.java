package com.nhom03.hust.quanlydonhang.rest;

import com.google.gson.annotations.SerializedName;
import com.nhom03.hust.quanlydonhang.model.HangHoaCuaDonHang;

import java.util.List;

/**
 * Created by Admin on 03/12/2016.
 */
public class ListHangHoaCuaDonHang {

    @SerializedName("GetAllOrderDetailResult")
    List<HangHoaCuaDonHang> hangHoaCuaDonHangList;

    public List<HangHoaCuaDonHang> getHangHoaCuaDonHangList() {
        return hangHoaCuaDonHangList;
    }

    public void setHangHoaCuaDonHangList(List<HangHoaCuaDonHang> hangHoaCuaDonHangList) {
        this.hangHoaCuaDonHangList = hangHoaCuaDonHangList;
    }
}
