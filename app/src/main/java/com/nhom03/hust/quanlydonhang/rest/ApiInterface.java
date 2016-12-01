package com.nhom03.hust.quanlydonhang.rest;

import com.nhom03.hust.quanlydonhang.model.ListKhachHang;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sakura on 01/12/2016.
 */

public interface ApiInterface {
    //danh dacc khach hang
    @GET("GetAllCustomer")
    Call<ListKhachHang> getTopRatedMovies();


}
