package com.nhom03.hust.quanlydonhang.rest;

import android.util.Log;

import com.nhom03.hust.quanlydonhang.MyApplication;
import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.KhachHang;
import com.nhom03.hust.quanlydonhang.view.MainActivity;

import java.util.List;

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
            }

            @Override
            public void onFailure(Call<ListKhachHang> call, Throwable t) {
                Log.d("API", "Fail");
            }
        });

    }

    /*public static void themKhachHang(KhachHang khachHang) {
        KhachHangJson khJ = new KhachHangJson(khachHang);
        APIInterface apiService = APIKhachHang.get().create(APIInterface.class);
        Call<String> call = apiService.addCustomer(MyApplication.getCookie(), khJ);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    Log.d("API", "Success");
                    //DatabaseHelper.getInstance().themKhachHang(kh);
                    Log.d("Return", response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("API", "Fail");
            }
        });
        //return "";
    }*/
}
