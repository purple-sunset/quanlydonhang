package com.nhom03.hust.quanlydonhang.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.DonHang;
import com.nhom03.hust.quanlydonhang.model.KhachHang;
import com.nhom03.hust.quanlydonhang.rest.APIDonHang;
import com.nhom03.hust.quanlydonhang.rest.APIKhachHang;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by longs on 06/12/2016.
 */

public class XemChiTietDonHangActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_KHACHHANG = 111;

    private static final int RESULT_CODE_BACK = 120;
    private static final int RESULT_CODE_CHON_KHACHHANG = 121;


    private Intent intent;
    private DonHang donHang;
    private int position;


    private EditText tenKH;
    private EditText ngayDatHang;
    private EditText diaChiGiaoHang;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xem_thong_tin_don_hang);

        intent = getIntent();
        donHang = (DonHang) intent.getExtras().getSerializable("DH");
        position = intent.getExtras().getInt("Position");

        tenKH = (EditText) findViewById(R.id.xttdh_ten_khach_hang);
        tenKH.setText(donHang.getKhachHang().getTenKH());

        ngayDatHang = (EditText) findViewById(R.id.xttdh_ngay_dat_hang);
        ngayDatHang.setText(donHang.getNgayGio().toString());

        diaChiGiaoHang = (EditText) findViewById(R.id.xttdh_dia_chi_giao_hang);
        diaChiGiaoHang.setText(donHang.getDiaChiGiaoHang());
    }

    @Override
    public void onBackPressed() {

        intent.putExtra("Return", "Back Success");
        setResult(RESULT_CODE_BACK, intent);
        finish();
    }
    public void xemHangHoa(View view){
        Intent intent = new Intent();
    }

    public void suaTTKhachHang(View view){
        Intent intent2 = new Intent(this, DanhSachKhachHangActivity.class);
        intent2.putExtra("KH", donHang.getKhachHang());
        startActivityForResult(intent2, REQUEST_CODE_KHACHHANG);
    }


    public void suaTTDonHang(View view) {
        ngayDatHang.setEnabled(true);
        diaChiGiaoHang.setEnabled(true);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a", Locale.US);

        donHang.setDiaChiGiaoHang(diaChiGiaoHang.getText().toString());
        try {
            Date date = df.parse(ngayDatHang.getText().toString());
            donHang.setNgayGio(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    /*public void xoaDonHang(View view) {
        APIDonHang.xoaDonHang(DonHang);
    }*/

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE_KHACHHANG: {
                Log.d("Return From", "Khach Hang");
                switch (resultCode) {
                    case RESULT_CODE_CHON_KHACHHANG: {
                        KhachHang kh = (KhachHang) data.getExtras().getSerializable("Return_KH");
                        donHang.setKhachHang(kh);
                        tenKH.setText(donHang.getKhachHang().getTenKH());
                        break;
                    }

                }
                break;
            }



        }

    }

}
