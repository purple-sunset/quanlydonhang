package com.nhom03.hust.quanlydonhang.rest;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.DonHang;
import com.nhom03.hust.quanlydonhang.model.HangHoaCuaDonHang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 03/12/2016.
 */

public class APIDonHang {

    private static final int RESULT_CODE_ADD = 121;
    private static final int RESULT_CODE_EDIT = 122;
    private static final int RESULT_CODE_DELETE = 123;

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
                        APIHangHoaCuaDonHang.layDSHangHoaCuaDonHang(dh.getId());
                    }
                }
                else
                    Log.d("API", "Fail");
            }

            @Override
            public void onFailure(Call<ListDonHang> call, Throwable t) {
                Log.d("API", "Fail");
            }
        });

    }

    public static void themDonHang(final DonHang dh, final Activity activity, final Intent intent) {
        Map<String, Object> map = new HashMap<>();
        map.put("order", dh);
        APIInterface apiService = APIDonHang.get().create(APIInterface.class);
        Call<KetQuaThem> call = apiService.addOrder(map);
        call.enqueue(new Callback<KetQuaThem>() {
            @Override
            public void onResponse(Call<KetQuaThem> call, Response<KetQuaThem> response) {
                boolean result = false;
                if(response.isSuccessful()){
                    Log.d("API", "Success");
                    Log.d("Ket qua", response.body().getMessageJson().getMessage());
                    if(response.body().getMessageJson().getMessage().equals("Success")) {
                        result = true;
                        DatabaseHelper.getInstance().themDonHang(dh);
                    }
                }
                else
                    Log.d("API", "Fail");

                hienKetQua(RESULT_CODE_ADD, 0, dh, result, activity, intent);
            }

            @Override
            public void onFailure(Call<KetQuaThem> call, Throwable t) {
                Log.d("API", "Fail");
                hienKetQua(RESULT_CODE_ADD, 0, dh, false, activity, intent);
            }
        });

    }

    public static void themDonHang(final DonHang dh, final ArrayList<HangHoaCuaDonHang> dsHang, final Activity activity, final Intent intent) {
        Map<String, Object> map = new HashMap<>();
        DatabaseHelper.getInstance().themDonHangNoID(dh);

        map.put("order", dh);
        APIInterface apiService = APIDonHang.get().create(APIInterface.class);
        Call<KetQuaThem> call = apiService.addOrder(map);
        call.enqueue(new Callback<KetQuaThem>() {
            @Override
            public void onResponse(Call<KetQuaThem> call, Response<KetQuaThem> response) {
                boolean result = false;
                if(response.isSuccessful()){
                    Log.d("API", "Success");
                    Log.d("Ket qua", response.body().getMessageJson().getMessage());
                    if(response.body().getMessageJson().getMessage().equals("Success")) {
                        result = true;

                        for (HangHoaCuaDonHang hhdh: dsHang
                             ) {
                            hhdh.setIdDonHang(dh.getId());
                            APIHangHoaCuaDonHang.themHangHoaCuaDonHang(dh, hhdh, activity, intent);
                        }
                    }
                }
                else
                    Log.d("API", "Fail");

                hienKetQua(RESULT_CODE_ADD, 0, dh, result, activity, intent);
            }

            @Override
            public void onFailure(Call<KetQuaThem> call, Throwable t) {
                Log.d("API", "Fail");
                hienKetQua(RESULT_CODE_ADD, 0, dh, false, activity, intent);
            }
        });

    }

    public static void suaDonHang(final int position, final DonHang dh, final Activity activity, final Intent intent) {
        Map<String, Object> map = new HashMap<>();
        map.put("order", dh);
        APIInterface apiService = APIDonHang.get().create(APIInterface.class);
        Call<KetQuaSua> call = apiService.editOrder(map);
        call.enqueue(new Callback<KetQuaSua>() {
            @Override
            public void onResponse(Call<KetQuaSua> call, Response<KetQuaSua> response) {
                boolean result = false;
                if(response.isSuccessful()){
                    Log.d("API", "Success");
                    Log.d("Ket qua", response.body().getMessageJson().getMessage());
                    if(response.body().getMessageJson().getMessage().equals("Success")) {
                        result = true;
                        DatabaseHelper.getInstance().suaDonHang(dh);
                    }
                }
                else
                    Log.d("API", "Fail");

                hienKetQua(RESULT_CODE_EDIT, position, dh, result, activity, intent);
            }

            @Override
            public void onFailure(Call<KetQuaSua> call, Throwable t) {
                Log.d("API", "Fail");
                hienKetQua(RESULT_CODE_EDIT, position, dh, false, activity, intent);
            }
        });

    }

    public static void xoaDonHang(final int position, final DonHang dh, final Activity activity, final Intent intent) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", dh.getId());
        APIInterface apiService = APIDonHang.get().create(APIInterface.class);
        Call<KetQuaXoa> call = apiService.deleteOrder(map);
        call.enqueue(new Callback<KetQuaXoa>() {
            @Override
            public void onResponse(Call<KetQuaXoa> call, Response<KetQuaXoa> response) {
                boolean result = false;
                if(response.isSuccessful()){
                    Log.d("API", "Success");
                    Log.d("Ket qua", response.body().getMessageJson().getMessage());
                    if(response.body().getMessageJson().getMessage().equals("Success")) {
                        result = true;
                        DatabaseHelper.getInstance().xoaDonHang(dh);
                    }
                }
                else
                    Log.d("API", "Fail");

                hienKetQua(RESULT_CODE_DELETE, position, dh, result, activity, intent);

            }

            @Override
            public void onFailure(Call<KetQuaXoa> call, Throwable t) {
                Log.d("API", "Fail");
                hienKetQua(RESULT_CODE_DELETE, position, dh, false, activity, intent);
            }
        });
    }

    private static void hienKetQua(final int resultCode, final int position, final DonHang dh, boolean result, final Activity activity, final Intent intent){

        final Dialog dialog = new Dialog(activity);
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
                    intent.putExtra("Return_DH", dh);
                    intent.putExtra("Return_Position", position);
                    activity.setResult(resultCode, intent);
                    dialog.dismiss();
                    activity.finish();
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
