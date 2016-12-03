package com.nhom03.hust.quanlydonhang.rest;

import com.google.gson.annotations.SerializedName;
import com.nhom03.hust.quanlydonhang.model.DonHang;

import java.util.List;

/**
 * Created by Admin on 03/12/2016.
 */

public class ListDonHang {

    @SerializedName("GetAllOrderResult")
    private List<DonHang> donHangList;

    public List<DonHang> getDonHangList() {
        return donHangList;
    }

    public void setDonHangList(List<DonHang> donHangList) {
        this.donHangList = donHangList;
    }
}
