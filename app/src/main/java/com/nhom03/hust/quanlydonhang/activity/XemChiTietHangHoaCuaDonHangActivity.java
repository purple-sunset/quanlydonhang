package com.nhom03.hust.quanlydonhang.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.HangHoaCuaDonHang;
import com.nhom03.hust.quanlydonhang.rest.APIKhachHang;

/**
 * Created by longs on 06/12/2016.
 */

public class XemChiTietHangHoaCuaDonHangActivity extends AppCompatActivity {

    private static final int RESULT_CODE_BACK = 520;
    private static final int RESULT_CODE_EDIT = 522;
    private static final int RESULT_CODE_DELETE= 523;

    private Intent intent;
    private HangHoaCuaDonHang hangHoaCuaDonHang;
    private boolean editable = false;
    private int position = 0;

    private Button tenHH;
    private EditText soLuong;
    private EditText donGia;
    private EditText thanhTien;

    private Button xoaHH;
    private Button suaHH;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xem_chi_tiet_hang_hoa_cua_don_hang);

        intent = getIntent();
        hangHoaCuaDonHang = (HangHoaCuaDonHang) intent.getExtras().getSerializable("HHDH");
        editable = intent.getExtras().getBoolean("Editable");
        position = intent.getExtras().getInt("Position");

        tenHH = (Button) findViewById(R.id.xcthh_cua_dh_ten);
        tenHH.setText(hangHoaCuaDonHang.getTen());

        donGia = (EditText) findViewById(R.id.xcthh_cua_dh_don_gia);
        donGia.setText(String.valueOf((long) hangHoaCuaDonHang.getDonGia() * 1000));

        soLuong = (EditText) findViewById(R.id.xcthh_cua_dh_so_luong);
        soLuong.setText(String.valueOf(hangHoaCuaDonHang.getSoLuong()));

        thanhTien = (EditText) findViewById(R.id.xcthh_cua_dh_thanh_tien);
        thanhTien.setText(String.valueOf((long) hangHoaCuaDonHang.thanhTien() * 1000));

        suaHH = (Button) findViewById(R.id.xcthh_sua);
        xoaHH = (Button) findViewById(R.id.xcthh_xoa);

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

        if (editable) {
            soLuong.setEnabled(true);
            suaHH.setEnabled(true);
            xoaHH.setEnabled(true);
        }

    }

    @Override
    public void onBackPressed() {

        intent.putExtra("Return", "Back Success");
        setResult(RESULT_CODE_BACK, intent);
        finish();
    }

    public void suaHangHoa(View view) {
        hangHoaCuaDonHang.setSoLuong(Integer.parseInt(soLuong.getText().toString()));
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        TextView textView = (TextView) dialog.findViewById(R.id.dialog_text);
        Button btnOk = (Button) dialog.findViewById(R.id.dialog_ok);
        Button btnCancel = (Button) dialog.findViewById(R.id.dialog_cancel);

        btnOk.setText("OK");
        btnCancel.setText("Cancel");

        title.setText("Sửa hàng hóa");
        textView.setText("Bạn có muốn sửa hàng hóa không?");
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("Return_HHDH", hangHoaCuaDonHang);
                intent.putExtra("Return_Position", position);
                setResult(RESULT_CODE_EDIT, intent);
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

    public void xoaHangHoa(View view) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        TextView textView = (TextView) dialog.findViewById(R.id.dialog_text);
        Button btnOk = (Button) dialog.findViewById(R.id.dialog_ok);
        Button btnCancel = (Button) dialog.findViewById(R.id.dialog_cancel);

        btnOk.setText("Có");
        btnCancel.setText("Không");

        title.setText("Xóa hàng hóa");
        textView.setText("Bạn có muốn xóa hàng hóa không?");
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("Return_HHDH", hangHoaCuaDonHang);
                intent.putExtra("Return_Position", position);
                setResult(RESULT_CODE_DELETE, intent);
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
}

