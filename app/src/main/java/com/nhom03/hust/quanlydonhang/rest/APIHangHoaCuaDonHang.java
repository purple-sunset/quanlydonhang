package com.nhom03.hust.quanlydonhang.rest;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.DonHang;
import com.nhom03.hust.quanlydonhang.model.HangHoaCuaDonHang;
import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;

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

public class APIHangHoaCuaDonHang {

    private static final int RESULT_CODE_ADD_DH = 121;
    private static final int RESULT_CODE_ADD = 421;
    private static final int RESULT_CODE_EDIT = 422;
    private static final int RESULT_CODE_DELETE = 423;

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

    public static void layDSHangHoaCuaDonHang(long id) {
        APIInterface apiService = APIHangHoaCuaDonHang.get().create(APIInterface.class);
        Call<ListHangHoaCuaDonHang> call = apiService.getListDetail(id);
        call.enqueue(new Callback<ListHangHoaCuaDonHang>() {
            @Override
            public void onResponse(Call<ListHangHoaCuaDonHang> call, Response<ListHangHoaCuaDonHang> response) {
                if (response.isSuccessful()) {
                    Log.d("API", "Success");
                    List<HangHoaCuaDonHang> result = response.body().getHangHoaCuaDonHangList();
                    for (HangHoaCuaDonHang hhdh : result) {
                        DatabaseHelper.getInstance().themHangHoaCuaDonHang(hhdh);
                    }
                } else
                    Log.d("API", "Fail");

            }

            @Override
            public void onFailure(Call<ListHangHoaCuaDonHang> call, Throwable t) {
                Log.d("API", "Fail");
            }
        });

    }

    public static void themHangHoaCuaDonHang(final DonHang dh, final HangHoaCuaDonHang hhdh, final Activity activity, final Intent intent) {

        Map<String, Object> map = new HashMap<>();
        map.put("orderDetail", hhdh);
        APIInterface apiService = APIHangHoaCuaDonHang.get().create(APIInterface.class);
        Call<KetQuaThem> call = apiService.addOrderDetail(map);
        call.enqueue(new Callback<KetQuaThem>() {
            @Override
            public void onResponse(Call<KetQuaThem> call, Response<KetQuaThem> response) {
                boolean result = false;
                if (response.isSuccessful()) {
                    Log.d("API", "Success");
                    Log.d("Ket qua", response.body().getMessageJson().getMessage());
                    if (response.body().getMessageJson().getMessage().equals("Success")) {
                        result = true;
                        DatabaseHelper.getInstance().themHangHoaCuaDonHang(hhdh);
                        dh.themHang(hhdh);
                    }
                } else {
                    Log.d("API", "Fail");
                    hienKetQua(RESULT_CODE_ADD, 0, hhdh, result, activity, intent);
                }

            }

            @Override
            public void onFailure(Call<KetQuaThem> call, Throwable t) {
                hienKetQua(RESULT_CODE_ADD, 0, hhdh, false, activity, intent);
                Log.d("API", "Fail");
            }
        });

    }

    public static void themHangHoaCuaDonHang(final DonHang dh, final int sum, final HangHoaCuaDonHang hhdh, final Activity activity, final Intent intent) {

        Map<String, Object> map = new HashMap<>();
        map.put("orderDetail", hhdh);
        APIInterface apiService = APIHangHoaCuaDonHang.get().create(APIInterface.class);
        Call<KetQuaThem> call = apiService.addOrderDetail(map);
        call.enqueue(new Callback<KetQuaThem>() {
            @Override
            public void onResponse(Call<KetQuaThem> call, Response<KetQuaThem> response) {
                boolean result = false;
                if (response.isSuccessful()) {
                    Log.d("API", "Success");
                    Log.d("Ket qua", response.body().getMessageJson().getMessage());
                    if (response.body().getMessageJson().getMessage().equals("Success")) {
                        result = true;
                        DatabaseHelper.getInstance().themHangHoaCuaDonHang(hhdh);
                        dh.themHang(hhdh);
                        if(dh.getDsHang().size() == sum)
                            hienKetQuaThem(RESULT_CODE_ADD_DH, 0, dh, result, activity, intent);
                    }
                } else {
                    Log.d("API", "Fail");
                    hienKetQua(RESULT_CODE_ADD, 0, hhdh, result, activity, intent);
                }

            }

            @Override
            public void onFailure(Call<KetQuaThem> call, Throwable t) {
                hienKetQua(RESULT_CODE_ADD, 0, hhdh, false, activity, intent);
                Log.d("API", "Fail");
            }
        });

    }

    public static void suaHangHoaCuaDonHang(final DonHang dh, final int position, final HangHoaCuaDonHang hhdh, final Activity activity, final Intent intent) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderDetail", hhdh);
        APIInterface apiService = APIHangHoaCuaDonHang.get().create(APIInterface.class);
        Call<KetQuaSua> call = apiService.editOrderDetail(map);
        call.enqueue(new Callback<KetQuaSua>() {
            @Override
            public void onResponse(Call<KetQuaSua> call, Response<KetQuaSua> response) {
                boolean result = false;
                if (response.isSuccessful()) {
                    Log.d("API", "Success");
                    Log.d("Ket qua", response.body().getMessageJson().getMessage());
                    if (response.body().getMessageJson().getMessage().equals("Success")) {
                        result = true;
                        DatabaseHelper.getInstance().suaHangHoaCuaDonHang(hhdh);
                        dh.suaHang(position, hhdh);
                    }
                } else {
                    Log.d("API", "Fail");
                    hienKetQua(RESULT_CODE_EDIT, position, hhdh, result, activity, intent);
                }

            }

            @Override
            public void onFailure(Call<KetQuaSua> call, Throwable t) {
                Log.d("API", "Fail");
                hienKetQua(RESULT_CODE_EDIT, position, hhdh, false, activity, intent);
            }
        });

    }

    public static void xoaHangHoaCuaDonHang(final DonHang dh, final int position, final HangHoaCuaDonHang hhdh, final Activity activity, final Intent intent) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", hhdh.getIdDonHang());
        map.put("productId", hhdh.getIdHangHoa());

        APIInterface apiService = APIHangHoaCuaDonHang.get().create(APIInterface.class);
        Call<KetQuaXoa> call = apiService.deleteOrderDetail(map);
        call.enqueue(new Callback<KetQuaXoa>() {
            @Override
            public void onResponse(Call<KetQuaXoa> call, Response<KetQuaXoa> response) {
                boolean result = false;
                if (response.isSuccessful()) {
                    Log.d("API", "Success");
                    Log.d("Ket qua", response.body().getMessageJson().getMessage());
                    if (response.body().getMessageJson().getMessage().equals("Success")) {
                        result = true;
                        DatabaseHelper.getInstance().xoaHangHoaCuaDonHang(hhdh);
                        dh.xoaHang(position);
                    }
                } else {
                    Log.d("API", "Fail");
                    hienKetQua(RESULT_CODE_DELETE, position, hhdh, result, activity, intent);
                }


            }

            @Override
            public void onFailure(Call<KetQuaXoa> call, Throwable t) {
                Log.d("API", "Fail");
                hienKetQua(RESULT_CODE_DELETE, position, hhdh, false, activity, intent);
            }
        });
    }

    public static void xoaDonHang(final DonHang dh, final int position, final Activity activity, final Intent intent) {
        if (dh.getDsHang().size() > 0) {
            Map<String, Object> map = new HashMap<>();
            final HangHoaCuaDonHang hhdh = dh.getDsHang().get(0);
            map.put("orderId", hhdh.getIdDonHang());
            map.put("productId", hhdh.getIdHangHoa());

            APIInterface apiService = APIHangHoaCuaDonHang.get().create(APIInterface.class);
            Call<KetQuaXoa> call = apiService.deleteOrderDetail(map);
            call.enqueue(new Callback<KetQuaXoa>() {
                @Override
                public void onResponse(Call<KetQuaXoa> call, Response<KetQuaXoa> response) {
                    boolean result = false;
                    if (response.isSuccessful()) {
                        Log.d("API", "Success");
                        Log.d("Ket qua", response.body().getMessageJson().getMessage());
                        if (response.body().getMessageJson().getMessage().equals("Success")) {
                            result = true;
                            DatabaseHelper.getInstance().xoaHangHoaCuaDonHang(hhdh);
                            dh.xoaHang(0);

                            if (dh.getDsHang().size() == 0)
                                APIDonHang.xoaDonHang(position, dh, activity, intent);
                            else
                                xoaDonHang(dh, position, activity, intent);
                        }
                    } else {
                        Log.d("API", "Fail");
                        hienKetQua(RESULT_CODE_DELETE, 0, hhdh, result, activity, intent);
                    }


                }

                @Override
                public void onFailure(Call<KetQuaXoa> call, Throwable t) {
                    Log.d("API", "Fail");
                    hienKetQua(RESULT_CODE_DELETE, 0, hhdh, false, activity, intent);
                }
            });
        }

    }

    private static void hienKetQua(final int resultCode, final int position, final HangHoaCuaDonHang hhdh, boolean result, final Activity activity, final Intent intent) {

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog);
        TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        TextView textView = (TextView) dialog.findViewById(R.id.dialog_text);
        Button btnOk = (Button) dialog.findViewById(R.id.dialog_ok);
        Button btnCancel = (Button) dialog.findViewById(R.id.dialog_cancel);

        btnOk.setText("OK");
        btnCancel.setText("Cancel");

        if (result) {
            title.setText("Thành công");
            textView.setText("Quay về trang trước?");
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent.putExtra("Return_HHCDH", hhdh);
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
        } else {
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

    private static void hienKetQuaThem(final int resultCode, final int position, final DonHang dh, boolean result, final Activity activity, final Intent intent){

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
