package com.nhom03.hust.quanlydonhang.rest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.APICallback;
import com.nhom03.hust.quanlydonhang.model.ChiTietDonHang;
import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.HangHoa;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
                else
                    Log.d("API", "Fail");

            }

            @Override
            public void onFailure(Call<ListChiTietDonHang> call, Throwable t) {
                Log.d("API", "Fail");
            }
        });

    }

    public static void themChiTietDonHang(final ChiTietDonHang ct, final Context context, final Intent intent) {

        Map<String, Object> map = new HashMap<>();
        map.put("orderDetail", ct);
        APIInterface apiService = APIChiTietDonHang.get().create(APIInterface.class);
        Call<KetQuaThem> call = apiService.addOrderDetail(map);
        call.enqueue(new Callback<KetQuaThem>() {
            @Override
            public void onResponse(Call<KetQuaThem> call, Response<KetQuaThem> response) {
                if(response.isSuccessful()){
                    boolean result = false;
                    Log.d("API", "Success");
                    Log.d("Ket qua", response.body().getMessageJson().getMessage());
                    if(response.body().getMessageJson().getMessage().equals("Success")) {
                        result = true;
                        DatabaseHelper.getInstance().themChiTietDonHang(ct);
                    }

                    hienKetQua(result, context, intent);



                }
                else
                    Log.d("API", "Fail");
            }

            @Override
            public void onFailure(Call<KetQuaThem> call, Throwable t) {
                hienKetQua(false, context, intent);
                Log.d("API", "Fail");
            }
        });

    }

    public static void suaChiTietDonHang(ChiTietDonHang ct) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderDetail", ct);
        APIInterface apiService = APIChiTietDonHang.get().create(APIInterface.class);
        Call<KetQuaSua> call = apiService.editOrderDetail(map);
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

    public static void xoaChiTietDonHang(ChiTietDonHang ct) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", ct.getIdDonHang());
        map.put("productId", ct.getIdHangHoa());

        APIInterface apiService = APIChiTietDonHang.get().create(APIInterface.class);
        Call<KetQuaXoa> call = apiService.deleteOrderDetail(map);
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

    private static void hienKetQua(boolean result, Context context, Intent intent){

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog);
        TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        TextView textView = (TextView) dialog.findViewById(R.id.dialog_text);
        Button btnOk = (Button) dialog.findViewById(R.id.dialog_ok);
        Button btnCancel = (Button) dialog.findViewById(R.id.dialog_cancel);

        btnOk.setText("OK");
        btnCancel.setText("Cancel");

        if(result) {
            title.setText("Thành công");
            textView.setText("Quay về trang trước?");
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dialog.dismiss();
                }
            });

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        }
        else {
            title.setText("Lỗi");
            textView.setText("Đóng hộp thoại này?");
            btnCancel.setVisibility(View.INVISIBLE);
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        }

        dialog.show();

    }
}
