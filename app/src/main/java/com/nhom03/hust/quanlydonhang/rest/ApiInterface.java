package com.nhom03.hust.quanlydonhang.rest;

import com.nhom03.hust.quanlydonhang.model.KhachHang;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by sakura on 01/12/2016.
 */

public interface APIInterface {
    //danh dacc khach hang
    @GET("GetAllCustomer")
    Call<ListKhachHang> getListCustomer();

    @GET("GetAllCategory")
    Call<ListTheLoai> getListCategory();

    @GET("GetAllProduct")
    Call<ListHangHoa> getListProduct();

    @GET("GetAllOrder")
    Call<ListDonHang> getListOrder();

    @GET("GetAllOrderDetail")
    Call<ListChiTietDonHang> getListDetail(@Query("orderId") int id);


    //them khach hang
    @POST("AddCustomer")
    Call<String> suaKhacHang(@Header("Cookie") String cookie, @Query("customer") KhachHang khachHang);

}

