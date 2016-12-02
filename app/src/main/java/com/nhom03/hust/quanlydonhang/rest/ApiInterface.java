package com.nhom03.hust.quanlydonhang.rest;

import com.nhom03.hust.quanlydonhang.model.KhachHang;
import com.nhom03.hust.quanlydonhang.model.ListKhachHang;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by sakura on 01/12/2016.
 */

public interface ApiInterface {
    //danh dacc khach hang
    @GET("GetAllCustomer")
    Call<ListKhachHang> getListCustomer();

    //them khach hang
    @POST("AddCustomer")
    Call<String> suaKhacHang(@Header("Cookie") String cookie, @Query("customer") KhachHang khachHang);


}
