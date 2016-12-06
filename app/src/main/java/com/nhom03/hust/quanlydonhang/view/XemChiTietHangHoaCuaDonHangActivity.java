package com.nhom03.hust.quanlydonhang.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.ChiTietDonHang;
import com.nhom03.hust.quanlydonhang.model.HangHoa;

/**
 * Created by longs on 06/12/2016.
 */

public class XemChiTietHangHoaCuaDonHangActivity extends AppCompatActivity {

    private static final int RESULT_CODE_BACK = 420;

    private Intent intent;
    private ChiTietDonHang chiTietDonHang;

    private Button tenHH;
    private EditText soLuong;
    private EditText DonGia;
    private EditText thanhTien;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xem_chi_tiet_hang_hoa_cua_don_hang);

        intent = getIntent();
        chiTietDonHang = (ChiTietDonHang) intent.getExtras().getSerializable("HH");

        tenHH = (Button) findViewById(R.id.xcthh_cua_dh_ten);
        tenHH.setText(chiTietDonHang.getTen());

        DonGia = (EditText) findViewById(R.id.xcthh_cua_dh_don_gia);
        DonGia.setText(String.valueOf(chiTietDonHang.getDonGia()));

        soLuong = (EditText) findViewById(R.id.xcthh_cua_dh_so_luong);
        soLuong.setText(String.valueOf(chiTietDonHang.getSoLuong()));

        thanhTien = (EditText) findViewById(R.id.xcthh_cua_dh_thanh_tien);
        thanhTien.setText(String.valueOf(chiTietDonHang.thanhTien()));

    }

    @Override
    public void onBackPressed() {

        intent.putExtra("Return", "Back Success");
        setResult(RESULT_CODE_BACK, intent);
        finish();
    }
}

