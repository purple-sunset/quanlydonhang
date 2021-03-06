package com.nhom03.hust.quanlydonhang.rest;

import android.util.Log;

import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.TheLoai;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 02/12/2016.
 */

public class APITheLoai {
    public static final String BASE_URL = "http://daotao.misa.com.vn/services/OrderService.svc/rest/";
    private static Retrofit retrofit = null;

    public static Retrofit get() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static void layDSTheLoai() {
        APIInterface apiService = APITheLoai.get().create(APIInterface.class);
        Call<ListTheLoai> call = apiService.getListCategory();
        call.enqueue(new Callback<ListTheLoai>() {
            @Override
            public void onResponse(Call<ListTheLoai> call, Response<ListTheLoai> response) {
                if(response.isSuccessful()){
                    Log.d("API", "Success");
                    List<TheLoai> result = response.body().getTheLoaiList();
                    for(TheLoai tl:result){
                        DatabaseHelper.getInstance().themTheLoai(tl);
                    }
                }
            }

            @Override
            public void onFailure(Call<ListTheLoai> call, Throwable t) {
                Log.d("API", "Fail");
            }
        });

    }
}
