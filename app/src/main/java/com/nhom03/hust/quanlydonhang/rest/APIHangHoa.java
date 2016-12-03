package com.nhom03.hust.quanlydonhang.rest;

import android.util.Log;

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

public class APIHangHoa {

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

    public static void layDSHangHoa() {
        APIInterface apiService = APIHangHoa.get().create(APIInterface.class);
        Call<ListHangHoa> call = apiService.getListProduct();
        call.enqueue(new Callback<ListHangHoa>() {
            @Override
            public void onResponse(Call<ListHangHoa> call, Response<ListHangHoa> response) {
                if(response.isSuccessful()){
                    Log.d("API", "Success");
                    List<HangHoa> result = response.body().getHangHoaList();
                    for(HangHoa hh:result){
                        DatabaseHelper.getInstance().themHangHoa(hh);
                    }
                }

            }

            @Override
            public void onFailure(Call<ListHangHoa> call, Throwable t) {
                Log.d("API", "Fail");
            }
        });

    }
}
