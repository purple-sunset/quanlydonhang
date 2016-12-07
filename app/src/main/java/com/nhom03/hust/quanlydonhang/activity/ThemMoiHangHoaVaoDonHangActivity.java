package com.nhom03.hust.quanlydonhang.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.HangHoa;
import com.nhom03.hust.quanlydonhang.model.HangHoaCuaDonHang;

/**
 * Created by Admin on 07/12/2016.
 */

public class ThemMoiHangHoaVaoDonHangActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_HANGHOA = 611;

    private static final int RESULT_CODE_BACK = 520;
    private static final int RESULT_CODE_ADD = 521;
    private static final int RESULT_CODE_HANGHOA = 522;;

    private Intent intent;
    private HangHoa hh;
    private long idDH;

    private Button tenHang;
    private EditText soLuong;
    private EditText donGia;
    private EditText thanhTien;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them_moi_hang_hoa_vao_don_hang);

        intent = getIntent();
        hh = new HangHoa();
        idDH = intent.getExtras().getLong("IDDH");
        tenHang = (Button) findViewById(R.id.tmhh_th);
        soLuong = (EditText) findViewById(R.id.tmhh_sl);
        donGia = (EditText) findViewById(R.id.tmhh_dg);
        thanhTien = (EditText) findViewById(R.id.tmhh_tt);

        soLuong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    int sl = Integer.parseInt(soLuong.getText().toString());
                    long dg = Long.parseLong(donGia.getText().toString());
                    thanhTien.setText(String.valueOf(sl*dg));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    thanhTien.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onBackPressed() {

        intent.putExtra("Return", "Back Success");
        setResult(RESULT_CODE_BACK, intent);
        finish();


    }

    public void chonHangHoa(View view) {
        Intent intent1 = new Intent(this, DanhSachHangHoaActivity.class);
        intent1.putExtra("Calling_Activity", "DonHangActivity");
        startActivityForResult(intent1, REQUEST_CODE_HANGHOA);
    }

    public void themHangHoaVaoDonHang(View view) {
        final HangHoaCuaDonHang hhdh = new HangHoaCuaDonHang();
        hhdh.setIdDonHang(idDH);
        hhdh.setTen(hh.getTen());
        hhdh.setIdHangHoa(hh.getId());
        hhdh.setDonGia(Float.parseFloat(donGia.getText().toString()) / 100000);
        hhdh.setSoLuong(Integer.parseInt(soLuong.getText().toString()));

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        TextView textView = (TextView) dialog.findViewById(R.id.dialog_text);
        Button btnOk = (Button) dialog.findViewById(R.id.dialog_ok);
        Button btnCancel = (Button) dialog.findViewById(R.id.dialog_cancel);

        btnOk.setText("OK");
        btnCancel.setText("Cancel");

        title.setText("Thêm hàng hóa");
        textView.setText("Bạn có muốn thêm hàng hóa không?");
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("HHDH", hhdh);
                setResult(RESULT_CODE_ADD, intent);
                finish();
                dialog.dismiss();
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        hh = (HangHoa) data.getExtras().get("HH");
        tenHang.setText(hh.getTen());
        donGia.setText(String.valueOf((long) hh.getDonGia()*10000));
    }
}
