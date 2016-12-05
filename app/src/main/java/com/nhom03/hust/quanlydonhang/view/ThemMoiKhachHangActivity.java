package com.nhom03.hust.quanlydonhang.view;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.KhachHang;
import com.nhom03.hust.quanlydonhang.rest.APIKhachHang;

import java.util.Random;

public class ThemMoiKhachHangActivity extends AppCompatActivity {

    private static final int RESULT_CODE_BACK = 320;

    private Intent intent;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them_moi_khach_hang);

        intent = getIntent();

        tenKH = (EditText) findViewById(R.id.tmkh_ten_kh);
        tenCT = (EditText) findViewById(R.id.tmkh_ten_ct);
        tieuDe = (EditText) findViewById(R.id.tmkh_tieu_de);
        diaChi = (EditText) findViewById(R.id.tmkh_dia_chi);
        thanhPho = (EditText) findViewById(R.id.tmkh_thanh_pho);
        vung = (EditText) findViewById(R.id.tmkh_vung);
        quocGia = (EditText) findViewById(R.id.tmkh_quoc_gia);
        sdt = (EditText) findViewById(R.id.tmkh_sdt);
        fax = (EditText) findViewById(R.id.tmkh_fax);
        maBuuChinh = (EditText) findViewById(R.id.tmkh_ma_buu_chinh);

    }

    @Override
    public void onBackPressed() {

        intent.putExtra("Return", "Back Success");
        setResult(RESULT_CODE_BACK, intent);
        finish();
    }

    public void themKhachHang(View view) {

        Log.d("ID", taoID());
        KhachHang khachHang = new KhachHang();
        khachHang.setId(taoID());
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

        APIKhachHang.themKhachHang(khachHang, this, intent);
    }



    private String taoID(){

        String AlphaNumeric = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder();
        String s1 = tenKH.getText().toString().replace(" ", "");
        String s2 = tenCT.getText().toString().replace(" ", "");
        if(s1.length() > 1)
            sb.append(s1, 0, 2);
        else if(s1.length() > 0)
            sb.append(s1, 0, 1);

        if(s2.length() > 0)
            sb.append(s2, 0, 1);

        while ( sb.length() < 5 )
            sb.append( AlphaNumeric.charAt( rnd.nextInt(AlphaNumeric.length()) ) );

        return sb.toString();
    }

}
