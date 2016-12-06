package com.nhom03.hust.quanlydonhang.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.adapter.DonHangAdapter;
import com.nhom03.hust.quanlydonhang.adapter.KhachHangAdapter;
import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.DonHang;
import com.nhom03.hust.quanlydonhang.model.KhachHang;

import java.util.ArrayList;

/**
 * Created by longs on 06/12/2016.
 */

public class DanhSachDonHangActivity extends AppCompatActivity {
    private ArrayList<DonHang> dsDH;
    private ListView listViewDH;
    private static final int REQUEST_CODE_DETAIL = 111;
    private static final int REQUEST_CODE_ADD = 112;

    private static final int RESULT_CODE_ADD = 121;
    private static final int RESULT_CODE_EDIT = 122;
    private static final int RESULT_CODE_DELETE = 123;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xem_danh_sach_don_hang);
        dsDH = DatabaseHelper.getInstance().layDSDonHang();
        listViewDH = (ListView) findViewById(R.id.list_don_hang);
        listViewDH.setAdapter(new DonHangAdapter(this, dsDH));
        listViewDH.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DonHang dh = dsDH.get(i);
                Intent intent = new Intent(DanhSachDonHangActivity.this, XemChiTietDonHangActivity.class);
                intent.putExtra("DH", dh);
                intent.putExtra("Position", i);
                startActivityForResult(intent, REQUEST_CODE_DETAIL);
            }
        });
    }
}
