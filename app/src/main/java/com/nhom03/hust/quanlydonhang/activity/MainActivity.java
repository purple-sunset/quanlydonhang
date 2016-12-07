package com.nhom03.hust.quanlydonhang.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.NguoiDung;
import com.nhom03.hust.quanlydonhang.rest.APIDonHang;
import com.nhom03.hust.quanlydonhang.rest.APIHangHoa;
import com.nhom03.hust.quanlydonhang.rest.APIKhachHang;
import com.nhom03.hust.quanlydonhang.rest.APITheLoai;

public class MainActivity extends AppCompatActivity {

    private NguoiDung nguoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtUserName = (TextView) findViewById(R.id.txt_user);
        Intent dangNhap = getIntent();
        nguoiDung = (NguoiDung) dangNhap.getExtras().getSerializable("NGUOI_DUNG");
        txtUserName.setText(nguoiDung.getTen());
    }

    public void updateDB(View view){
        DatabaseHelper.getInstance().update();

        APIKhachHang.layDSKhachHang();
        APITheLoai.layDSTheLoai();
        APIHangHoa.layDSHangHoa();
        APIDonHang.layDSDonHang();

    }


    public void layDSDonHang(View view){
        Intent intent = new Intent(this, DanhSachDonHangActivity.class);
        startActivity(intent);

    }

    public void layDSKhachHang(View view){
        Intent intent = new Intent(this, DanhSachKhachHangActivity.class);
        startActivity(intent);
    }

    public void layDSHangHoa(View view){
        Intent intent = new Intent(this, DanhSachHangHoaActivity.class);
        startActivity(intent);

    }
}
