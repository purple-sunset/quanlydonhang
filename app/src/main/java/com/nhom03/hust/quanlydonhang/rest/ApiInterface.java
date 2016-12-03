package com.nhom03.hust.quanlydonhang.rest;

import com.nhom03.hust.quanlydonhang.model.ChiTietDonHang;
import com.nhom03.hust.quanlydonhang.model.KhachHang;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
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
    Call<String> addCustomer(@Body JSONObject object);

    @POST("AddOrderDetail")
    Call<KetQuaThem> addOrderDetail(@Body Map<String, Object> body);

    @POST("EditOrderDetail")
    Call<KetQuaSua> editOrderDetail(@Body Map<String, Object> body);

    @POST("DeleteOrderDetail")
    Call<KetQuaXoa> deleteOrderDetail(@Body Map<String, Object> body);

}

