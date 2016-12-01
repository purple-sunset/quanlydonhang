package com.nhom03.hust.quanlydonhang.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sakura on 01/12/2016.
 */

public class ListKhachHang {
    @SerializedName("GetAllCustomerResult")
    private List<KhachHang> khachHangList;

    public void setKhachHangList(List<KhachHang> khachHangList) {
        this.khachHangList = khachHangList;
    }

    public List<KhachHang> getKhachHangList() {

        return khachHangList;
    }
}
