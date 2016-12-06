package com.nhom03.hust.quanlydonhang.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    private static final int RESULT_CODE_BACK = 120;

    private Intent intent;
    private DonHang DonHang;
    private int position;


    private EditText tenKH;
    private EditText ngayDatHang;
    private EditText diaChiGiaoHang;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xem_thong_tin_don_hang);

        intent = getIntent();
        DonHang = (DonHang) intent.getExtras().getSerializable("H");
        position = intent.getExtras().getInt("Position");

        tenKH = (EditText) findViewById(R.id.xttdh_ten_khach_hang);
        tenKH.setText(DonHang.getKhachHang().getTenKH());

        ngayDatHang = (EditText) findViewById(R.id.xttdh_ngay_dat_hang);
        ngayDatHang.setText(DonHang.getNgayGio().toString());

        diaChiGiaoHang = (EditText) findViewById(R.id.xttdh_dia_chi_giao_hang);
        diaChiGiaoHang.setText(DonHang.getDiaChiGiaoHang());
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

    }


    public void suaTTDonHang(View view) {
        ngayDatHang.setEnabled(true);
        diaChiGiaoHang.setEnabled(true);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a", Locale.US);

        DonHang.setDiaChiGiaoHang(diaChiGiaoHang.getText().toString());
        try {
            Date date = df.parse(ngayDatHang.getText().toString());
            DonHang.setNgayGio(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void xoaDonHang(View view) {
        APIDonHang.xoaDonHang(DonHang);
    }

}
