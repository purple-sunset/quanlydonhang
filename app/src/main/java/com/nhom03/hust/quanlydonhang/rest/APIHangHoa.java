package com.nhom03.hust.quanlydonhang.rest;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.HangHoa;
import com.nhom03.hust.quanlydonhang.model.KhachHang;

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

    private static final int RESULT_CODE_ADD = 321;
    private static final int RESULT_CODE_EDIT = 322;
    private static final int RESULT_CODE_DELETE = 323;

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

    private static void hienKetQua(final int resultCode, final int position, final HangHoa hh, boolean result, final Activity activity, final Intent intent){

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
                    intent.putExtra("Return_HH", hh);
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
