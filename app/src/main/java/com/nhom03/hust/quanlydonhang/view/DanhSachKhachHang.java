package com.nhom03.hust.quanlydonhang.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.adapter.KhachHangAdapter;
import com.nhom03.hust.quanlydonhang.model.KhachHang;
import com.nhom03.hust.quanlydonhang.model.ListKhachHang;
import com.nhom03.hust.quanlydonhang.rest.ApiInterface;
import com.nhom03.hust.quanlydonhang.rest.ApiKhachHang;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sakura on 01/12/2016.
 */

public class DanhSachKhachHang extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xem_danh_sach_khach_hang);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.view_list_customer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService = ApiKhachHang.getListKhachHang().create(ApiInterface.class);

        Call<ListKhachHang> call = apiService.getListCustomer();
        call.enqueue(new Callback<ListKhachHang>() {
            @Override
            public void onResponse(Call<ListKhachHang> call, Response<ListKhachHang> response) {
                int statusCode = response.code();
                List<KhachHang> khachHangList= response.body().getKhachHangList();
                recyclerView.setAdapter(new KhachHangAdapter(khachHangList, R.layout.xem_khach_hang_list_row, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<ListKhachHang> call, Throwable t) {

            }
        });
//
    }

}
