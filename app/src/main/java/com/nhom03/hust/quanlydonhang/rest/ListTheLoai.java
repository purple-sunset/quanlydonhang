package com.nhom03.hust.quanlydonhang.rest;

import com.google.gson.annotations.SerializedName;
import com.nhom03.hust.quanlydonhang.model.KhachHang;
import com.nhom03.hust.quanlydonhang.model.TheLoai;

import java.util.List;

/**
 * Created by Admin on 02/12/2016.
 */

public class ListTheLoai {
    @SerializedName("GetAllCategoryResult")
    private List<TheLoai> theLoaiList;

    public void setTheLoaiList(List<TheLoai> theLoaiList) {
        this.theLoaiList = theLoaiList;
    }

    public List<TheLoai> getTheLoaiList() {

        return theLoaiList;
    }
}
