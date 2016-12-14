package com.nhom03.hust.quanlydonhang.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.NguoiDung;
import com.nhom03.hust.quanlydonhang.rest.APIDonHang;
import com.nhom03.hust.quanlydonhang.rest.APIHangHoa;
import com.nhom03.hust.quanlydonhang.rest.APIKhachHang;
import com.nhom03.hust.quanlydonhang.rest.APITheLoai;

public class MainActivity extends ActionBarActivity {

    private static NguoiDung nguoiDung = new NguoiDung();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtUserName = (TextView) findViewById(R.id.txt_user);
        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            nguoiDung.setTen(((NguoiDung) intent.getExtras().getSerializable("NGUOI_DUNG")).getTen());
            nguoiDung.setMatKhau(((NguoiDung) intent.getExtras().getSerializable("NGUOI_DUNG")).getMatKhau());
        }
        txtUserName.setText(nguoiDung.getTen());
    }

    public void updateDB(MenuItem item){
        DatabaseHelper.getInstance().update();

        APIKhachHang.layDSKhachHang();
        APITheLoai.layDSTheLoai();
        APIHangHoa.layDSHangHoa();
        APIDonHang.layDSDonHang();

    }


    public void layDSDonHang(View view){
        Intent intent2 = new Intent(this, DanhSachDonHangActivity.class);
        startActivity(intent2);

    }

    public void layDSKhachHang(View view){
        Intent intent3 = new Intent(this, DanhSachKhachHangActivity.class);
        startActivity(intent3);
    }

    public void layDSHangHoa(View view){
        Intent intent4 = new Intent(this, DanhSachHangHoaActivity.class);
        startActivity(intent4);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
