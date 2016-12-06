package com.nhom03.hust.quanlydonhang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.HangHoa;

/**
 * Created by Admin on 06/12/2016.
 */

public class XemChiTietHangHoaActivity extends AppCompatActivity {
    private static final int RESULT_CODE_BACK = 320;

    private Intent intent;
    private HangHoa hangHoa;

    private EditText tenHH;
    private Spinner trangThai;
    private EditText uuTien;
    private EditText chiTiet;
    private EditText donGia;
    private EditText slTon;
    private EditText slDat;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xem_chi_tiet_hang_hoa);

        intent = getIntent();
        hangHoa = (HangHoa) intent.getExtras().getSerializable("HH");

        tenHH = (EditText) findViewById(R.id.cthh_ten_hh);
        tenHH.setText(hangHoa.getTen());

        trangThai = (Spinner) findViewById(R.id.cthh_trang_thai);
        trangThai.setEnabled(false);
        trangThai.setClickable(false);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.trang_thai, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.dropdown_item);
        trangThai.setAdapter(adapter);

        if(hangHoa.isHuyBan())
            trangThai.setSelection(1);
        else
            trangThai.setSelection(0);

        uuTien = (EditText) findViewById(R.id.cthh_uu_tien);
        uuTien.setText(String.valueOf(hangHoa.getReOrderLevel()));

        chiTiet = (EditText) findViewById(R.id.cthh_chi_tiet);
        chiTiet.setText(hangHoa.getChiTiet());

        donGia = (EditText) findViewById(R.id.cthh_don_gia);
        donGia.setText(String.valueOf((long) hangHoa.getDonGia() * 10000));

        slTon = (EditText) findViewById(R.id.cthh_slt);
        slTon.setText(String.valueOf(hangHoa.getSlTon()));

        slDat = (EditText) findViewById(R.id.cthh_sld);
        slDat.setText(String.valueOf(hangHoa.getSlDat()));

    }

    @Override
    public void onBackPressed() {

        intent.putExtra("Return", "Back Success");
        setResult(RESULT_CODE_BACK, intent);
        finish();
    }
}


