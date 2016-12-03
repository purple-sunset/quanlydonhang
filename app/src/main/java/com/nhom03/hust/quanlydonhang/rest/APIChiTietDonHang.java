package com.nhom03.hust.quanlydonhang.rest;

import android.util.Log;

import com.nhom03.hust.quanlydonhang.model.ChiTietDonHang;
import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.HangHoa;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 03/12/2016.
 */

public class APIChiTietDonHang {

    public static final String BASE_URL = "http://daotao.misa.com.vn/Services/OrderService.svc/rest/";
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

    public static void layDSChiTietDonHang(int id) {
        APIInterface apiService = APIChiTietDonHang.get().create(APIInterface.class);
        Call<ListChiTietDonHang> call = apiService.getListDetail(id);
        call.enqueue(new Callback<ListChiTietDonHang>() {
            @Override
            public void onResponse(Call<ListChiTietDonHang> call, Response<ListChiTietDonHang> response) {
                if(response.isSuccessful()){
                    Log.d("API", "Success");
                    List<ChiTietDonHang> result = response.body().getChiTietDonHangList();
                    for(ChiTietDonHang ct:result){
                        DatabaseHelper.getInstance().themChiTietDonHang(ct);
                    }
                }

            }

            @Override
            public void onFailure(Call<ListChiTietDonHang> call, Throwable t) {
                Log.d("API", "Fail");
            }
        });

    }
}
