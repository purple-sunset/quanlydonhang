package com.nhom03.hust.quanlydonhang.rest;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.adapter.KhachHangAdapter;
import com.nhom03.hust.quanlydonhang.model.KhachHang;
import com.nhom03.hust.quanlydonhang.model.ListKhachHang;
import com.nhom03.hust.quanlydonhang.model.TheLoai;

import java.util.ArrayList;
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

    public static Retrofit getListTheLoai() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ArrayList<TheLoai> layDSTheLoai() {
        final ArrayList<TheLoai> dsKhachHang = new ArrayList<TheLoai>();
        ApiInterface apiService = APITheLoai.getListTheLoai().create(ApiInterface.class);
        Call<ListTheLoai> call = apiService.getListCategory();
        call.enqueue(new Callback<ListTheLoai>() {
            @Override
            public void onResponse(Call<ListTheLoai> call, Response<ListTheLoai> response) {
                int statusCode = response.code();
                for (TheLoai tl: response.body().getTheLoaiList()
                     ) {
                    dsKhachHang.add(tl);
                }
            }

            @Override
            public void onFailure(Call<ListTheLoai> call, Throwable t) {

            }
        });
        return dsKhachHang;
    }
}
