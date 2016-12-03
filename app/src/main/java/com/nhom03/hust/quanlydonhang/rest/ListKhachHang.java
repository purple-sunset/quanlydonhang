package com.nhom03.hust.quanlydonhang.rest;

import com.google.gson.annotations.SerializedName;
import com.nhom03.hust.quanlydonhang.model.KhachHang;

import java.util.List;

/**
 * Created by sakura on 01/12/2016.
 */

class ListKhachHang {

    @SerializedName("GetAllCustomerResult")
    private List<KhachHang> khachHangList;

    public void setKhachHangList(List<KhachHang> khachHangList) {
        this.khachHangList = khachHangList;
    }

    public List<KhachHang> getKhachHangList() {

        return khachHangList;
    }
}

