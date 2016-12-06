package com.nhom03.hust.quanlydonhang.rest;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nhom03.hust.quanlydonhang.MyApplication;
import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.DonHang;
import com.nhom03.hust.quanlydonhang.model.KhachHang;
import com.nhom03.hust.quanlydonhang.view.MainActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sakura on 01/12/2016.
 */

public class APIKhachHang {
    private static final int RESULT_CODE_ADD = 221;
    private static final int RESULT_CODE_EDIT = 222;
    private static final int RESULT_CODE_DELETE = 223;

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
                else
                    Log.d("API", "Fail");
            }

            @Override
            public void onFailure(Call<ListKhachHang> call, Throwable t) {
                Log.d("API", "Fail");
            }
        });

    }

    public static void themKhachHang(final KhachHang kh, final Activity activity, final Intent intent) {
        Map<String, Object> map = new HashMap<>();
        map.put("customer", kh);
        APIInterface apiService = APIKhachHang.get().create(APIInterface.class);
        Call<KetQuaThem> call = apiService.addCustomer(map);
        call.enqueue(new Callback<KetQuaThem>() {
            @Override
            public void onResponse(Call<KetQuaThem> call, Response<KetQuaThem> response) {
                boolean result = false;
                if(response.isSuccessful()){
                    Log.d("API", "Success");
                    Log.d("Ket qua", response.body().getMessageJson().getMessage());
                    if(response.body().getMessageJson().getMessage().equals("Success")) {
                        result = true;
                        DatabaseHelper.getInstance().themKhachHang(kh);
                    }
                }
                else
                    Log.d("API", "Fail");

                hienKetQua(RESULT_CODE_ADD, 0, kh, result, activity, intent);

            }

            @Override
            public void onFailure(Call<KetQuaThem> call, Throwable t) {
                Log.d("API", "Fail");
                hienKetQua(RESULT_CODE_ADD, 0, kh, false, activity, intent);
            }
        });

    }

    public static void suaKhachHang(final int position, final KhachHang kh, final Activity activity, final Intent intent) {
        Map<String, Object> map = new HashMap<>();
        map.put("customer", kh);
        APIInterface apiService = APIKhachHang.get().create(APIInterface.class);
        Call<KetQuaSua> call = apiService.editCustomer(map);
        call.enqueue(new Callback<KetQuaSua>() {
            @Override
            public void onResponse(Call<KetQuaSua> call, Response<KetQuaSua> response) {
                boolean result = false;
                if(response.isSuccessful()){
                    Log.d("API", "Success");
                    Log.d("Ket qua", response.body().getMessageJson().getMessage());
                    if(response.body().getMessageJson().getMessage().equals("Success")) {
                        result = true;
                        DatabaseHelper.getInstance().suaKhachHang(kh);
                    }
                }
                else
                    Log.d("API", "Fail");

                hienKetQua(RESULT_CODE_EDIT, position, kh, result, activity, intent);
            }

            @Override
            public void onFailure(Call<KetQuaSua> call, Throwable t) {
                Log.d("API", "Fail");
                hienKetQua(RESULT_CODE_EDIT, position, kh, false, activity, intent);
            }
        });

    }

    public static void xoaKhachHang(final int position, final KhachHang kh, final Activity activity, final Intent intent) {
        Map<String, Object> map = new HashMap<>();
        map.put("customerId", kh.getId());
        APIInterface apiService = APIKhachHang.get().create(APIInterface.class);
        Call<KetQuaXoa> call = apiService.deleteCustomer(map);
        call.enqueue(new Callback<KetQuaXoa>() {
            @Override
            public void onResponse(Call<KetQuaXoa> call, Response<KetQuaXoa> response) {
                boolean result = false;
                if(response.isSuccessful()){
                    Log.d("API", "Success");
                    Log.d("Ket qua", response.body().getMessageJson().getMessage());
                    if(response.body().getMessageJson().getMessage().equals("Success")) {
                        result = true;
                        DatabaseHelper.getInstance().xoaKhachHang(kh);
                    }
                }
                else
                    Log.d("API", "Fail");

                hienKetQua(RESULT_CODE_DELETE, position, kh, result, activity, intent);

            }

            @Override
            public void onFailure(Call<KetQuaXoa> call, Throwable t) {
                Log.d("API", "Fail");
                hienKetQua(RESULT_CODE_DELETE, position, kh, false, activity, intent);
            }
        });
    }

    private static void hienKetQua(final int resultCode, final int position, final KhachHang kh, boolean result, final Activity activity, final Intent intent){

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
                    intent.putExtra("Return_KH", kh);
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
