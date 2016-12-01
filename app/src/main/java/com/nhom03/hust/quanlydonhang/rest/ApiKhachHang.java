package com.nhom03.hust.quanlydonhang.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sakura on 01/12/2016.
 */

public class ApiKhachHang {
    public static final String BASE_URL = "http://daotao.misa.com.vn/services/CustomerService.svc/rest/";
    private static Retrofit retrofit = null;


    public static Retrofit getListKhachHang() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
