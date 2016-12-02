package com.nhom03.hust.quanlydonhang.rest;

import com.nhom03.hust.quanlydonhang.model.ListKhachHang;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by sakura on 01/12/2016.
 */

public interface ApiInterface {
    //danh dacc khach hang
    @GET("GetAllCustomer")
    Call<ListKhachHang> getListCustomer();

    @GET("GetAllCategory")
    Call<ArrayList<TheLoai>> getListCategory();

    //them khach hang
    @POST("AddCustomer")
    Call<String> suaKhacHang(@Header("Cookie") String cookie, @Query("customer") KhachHang khachHang);
}
