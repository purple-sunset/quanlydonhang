package com.nhom03.hust.quanlydonhang.rest;

import com.google.gson.annotations.SerializedName;
import com.nhom03.hust.quanlydonhang.model.HangHoa;

import java.util.List;

/**
 * Created by Admin on 03/12/2016.
 */
class ListHangHoa {

    @SerializedName("GetAllProductResult")
    private List<HangHoa> hangHoaList;

    public void setHangHoaList(List<HangHoa> hangHoaList) {

        this.hangHoaList = hangHoaList;
    }

    public List<HangHoa> getHangHoaList() {

        return hangHoaList;
    }
}
