package com.nhom03.hust.quanlydonhang.rest;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nhom03.hust.quanlydonhang.model.ChiTietDonHang;
import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.DonHang;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 03/12/2016.
 */

public class APIDonHang {

    public static final String BASE_URL = "http://daotao.misa.com.vn/Services/OrderService.svc/rest/";
    private static Retrofit retrofit = null;

    private static Gson gson = new GsonBuilder()
            .setDateFormat("MM/dd/yyyy hh:mm:ss a")
            .create();

    public static Retrofit get() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    public static void layDSDonHang() {
        APIInterface apiService = APIDonHang.get().create(APIInterface.class);
        Call<ListDonHang> call = apiService.getListOrder();
        call.enqueue(new Callback<ListDonHang>() {
            @Override
            public void onResponse(Call<ListDonHang> call, Response<ListDonHang> response) {
                if(response.isSuccessful()){
                    Log.d("API", "Success");
                    List<DonHang> result = response.body().getDonHangList();
                    for(DonHang dh:result){
                        if ((dh.getIdKhachHang() == null) || (dh.getIdKhachHang() == ""))
                            dh.setIdKhachHang("0x222");
                        DatabaseHelper.getInstance().themDonHang(dh);
                        APIChiTietDonHang.layDSChiTietDonHang(dh.getId());
                    }
                }
                else
                    Log.d("API", "Fail");
            }

            @Override
            public void onFailure(Call<ListDonHang> call, Throwable t) {
                Log.d("API", "Fail");
            }
        });

    }

    public static void themDonHang(DonHang dh) {
        Map<String, Object> map = new HashMap<>();
        map.put("order", dh);
        APIInterface apiService = APIDonHang.get().create(APIInterface.class);
        Call<KetQuaThem> call = apiService.addOrder(map);
        call.enqueue(new Callback<KetQuaThem>() {
            @Override
            public void onResponse(Call<KetQuaThem> call, Response<KetQuaThem> response) {
                if(response.isSuccessful()){
                    Log.d("API", "Success");
                    Log.d("Ket qua", response.body().getMessageJson().getMessage());
                }
                else
                    Log.d("API", "Fail");
            }

            @Override
            public void onFailure(Call<KetQuaThem> call, Throwable t) {
                Log.d("API", "Fail");
            }
        });

    }

    public static void suaDonHang(DonHang dh) {
        Map<String, Object> map = new HashMap<>();
        map.put("order", dh);
        APIInterface apiService = APIDonHang.get().create(APIInterface.class);
        Call<KetQuaSua> call = apiService.editOrder(map);
        call.enqueue(new Callback<KetQuaSua>() {
            @Override
            public void onResponse(Call<KetQuaSua> call, Response<KetQuaSua> response) {
                if(response.isSuccessful()){
                    Log.d("API", "Success");
                    Log.d("Ket qua", response.body().getMessageJson().getMessage());
                }
                else
                    Log.d("API", "Fail");
            }

            @Override
            public void onFailure(Call<KetQuaSua> call, Throwable t) {
                Log.d("API", "Fail");
            }
        });

    }

    public static void xoaDonHang(DonHang dh) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", dh.getId());
        APIInterface apiService = APIDonHang.get().create(APIInterface.class);
        Call<KetQuaXoa> call = apiService.deleteOrder(map);
        call.enqueue(new Callback<KetQuaXoa>() {
            @Override
            public void onResponse(Call<KetQuaXoa> call, Response<KetQuaXoa> response) {
                if(response.isSuccessful()){
                    Log.d("API", "Success");
                    Log.d("Ket qua", response.body().getMessageJson().getMessage());
                }
                else
                    Log.d("API", "Fail");

            }

            @Override
            public void onFailure(Call<KetQuaXoa> call, Throwable t) {
                Log.d("API", "Fail");
            }
        });
    }
}
