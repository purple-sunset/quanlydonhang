package com.nhom03.hust.quanlydonhang.rest;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nhom03.hust.quanlydonhang.model.ChiTietDonHang;
import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.DonHang;

import java.util.List;

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
            }

            @Override
            public void onFailure(Call<ListDonHang> call, Throwable t) {
                Log.d("API", "Fail");
            }
        });

    }
}
