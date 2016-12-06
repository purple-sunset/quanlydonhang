package com.nhom03.hust.quanlydonhang.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.KhachHang;
import com.nhom03.hust.quanlydonhang.model.NguoiDung;
import com.nhom03.hust.quanlydonhang.rest.APIInterface;
import com.nhom03.hust.quanlydonhang.rest.APIKhachHang;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sakura on 01/12/2016.
 */

public class XemChiTietKhachHangActivity extends AppCompatActivity {

    private static final int RESULT_CODE_BACK = 220;

    private Intent intent;
    private KhachHang khachHang;
    private int position;

    private EditText tenKH;
    private EditText tenCT;
    private EditText tieuDe;
    private EditText diaChi;
    private EditText thanhPho;
    private EditText vung;
    private EditText quocGia;
    private EditText sdt;
    private EditText fax;
    private EditText maBuuChinh;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xem_chi_tiet_khach_hang);

        intent = getIntent();
        khachHang = (KhachHang) intent.getExtras().getSerializable("KH");
        position = intent.getExtras().getInt("Position");

        tenKH = (EditText) findViewById(R.id.ctkh_ten_kh);
        tenKH.setText(khachHang.getTenKH());

        tenCT = (EditText) findViewById(R.id.ctkh_ten_ct);
        tenCT.setText(khachHang.getTenCT());

        tieuDe = (EditText) findViewById(R.id.ctkh_tieu_de);
        tieuDe.setText(khachHang.getTieuDe());

        diaChi = (EditText) findViewById(R.id.ctkh_dia_chi);
        diaChi.setText(khachHang.getDiaChi());

        thanhPho = (EditText) findViewById(R.id.ctkh_thanh_pho);
        thanhPho.setText(khachHang.getThanhPho());

        vung = (EditText) findViewById(R.id.ctkh_vung);
        vung.setText(khachHang.getVung());

        quocGia = (EditText) findViewById(R.id.ctkh_quoc_gia);
        quocGia.setText(khachHang.getQuocGia());

        sdt = (EditText) findViewById(R.id.ctkh_sdt);
        sdt.setText(khachHang.getSdt());

        fax = (EditText) findViewById(R.id.ctkh_fax);
        fax.setText(khachHang.getFax());

        maBuuChinh = (EditText) findViewById(R.id.ctkh_ma_buu_chinh);
        maBuuChinh.setText(khachHang.getMaBuuChinh());

    }

    @Override
    public void onBackPressed() {

        intent.putExtra("Return", "Back Success");
        setResult(RESULT_CODE_BACK, intent);
        finish();
    }

    public void suaTTKhachHang(View view) {
        khachHang.setTenKH(tenKH.getText().toString());
        khachHang.setTenCT(tenCT.getText().toString());
        khachHang.setTieuDe(tieuDe.getText().toString());
        khachHang.setDiaChi(diaChi.getText().toString());
        khachHang.setThanhPho(thanhPho.getText().toString());
        khachHang.setVung(vung.getText().toString());
        khachHang.setQuocGia(quocGia.getText().toString());
        khachHang.setSdt(sdt.getText().toString());
        khachHang.setFax(fax.getText().toString());
        khachHang.setMaBuuChinh(maBuuChinh.getText().toString());

        APIKhachHang.suaKhachHang(position, khachHang, this, intent);
    }

    public void xoaKhachHang(View view) {
        APIKhachHang.xoaKhachHang(position, khachHang, this, intent);
    }
}
