package com.nhom03.hust.quanlydonhang.rest;

import android.util.Log;

import com.nhom03.hust.quanlydonhang.MyApplication;
import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.DonHang;
import com.nhom03.hust.quanlydonhang.model.KhachHang;
import com.nhom03.hust.quanlydonhang.view.MainActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sakura on 01/12/2016.
 */

public class APIKhachHang {
    public static final String BASE_URL = "http://daotao.misa.com.vn/services/CustomerService.svc/rest/";
    private static Retrofit retrofit = null;


    public static Retrofit get() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static void layDSKhachHang() {
        APIInterface apiService = APIKhachHang.get().create(APIInterface.class);
        Call<ListKhachHang> call = apiService.getListCustomer();
        call.enqueue(new Callback<ListKhachHang>() {
            @Override
            public void onResponse(Call<ListKhachHang> call, Response<ListKhachHang> response) {
                if(response.isSuccessful()){
                    Log.d("API", "Success");
                    List<KhachHang> result = response.body().getKhachHangList();
                    for(KhachHang kh:result){
                        if((kh.getTenKH() != null) && (kh.getTenKH() != ""))
                            DatabaseHelper.getInstance().themKhachHang(kh);
                    }
                }
                else
                    Log.d("API", "Fail");
            }

            @Override
            public void onFailure(Call<ListKhachHang> call, Throwable t) {
                Log.d("API", "Fail");
            }
        });

    }

    public static void themKhachHang(KhachHang kh) {
        Map<String, Object> map = new HashMap<>();
        map.put("customer", kh);
        APIInterface apiService = APIKhachHang.get().create(APIInterface.class);
        Call<KetQuaThem> call = apiService.addCustomer(map);
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

    public static void suaKhachHang(KhachHang kh) {
        Map<String, Object> map = new HashMap<>();
        map.put("customer", kh);
        APIInterface apiService = APIKhachHang.get().create(APIInterface.class);
        Call<KetQuaSua> call = apiService.editCustomer(map);
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

    public static void xoaKhachHang(KhachHang kh) {
        Map<String, Object> map = new HashMap<>();
        map.put("customerId", kh.getId());
        APIInterface apiService = APIKhachHang.get().create(APIInterface.class);
        Call<KetQuaXoa> call = apiService.deleteCustomer(map);
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
