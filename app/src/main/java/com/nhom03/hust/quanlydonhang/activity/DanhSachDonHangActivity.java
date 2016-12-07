package com.nhom03.hust.quanlydonhang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.adapter.DonHangAdapter;
import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.DonHang;
import com.nhom03.hust.quanlydonhang.model.HangHoaCuaDonHang;
import com.nhom03.hust.quanlydonhang.model.KhachHang;

import java.util.ArrayList;

/**
 * Created by longs on 06/12/2016.
 */

public class DanhSachDonHangActivity extends AppCompatActivity {
    private ArrayList<DonHang> dsDH;
    private DonHangAdapter adapter;
    private ListView listViewDH;
    private static final int REQUEST_CODE_DETAIL = 011;
    private static final int REQUEST_CODE_ADD = 012;

    private static final int RESULT_CODE_ADD = 121;
    private static final int RESULT_CODE_EDIT = 122;
    private static final int RESULT_CODE_DELETE = 123;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xem_danh_sach_don_hang);
        dsDH = DatabaseHelper.getInstance().layDSDonHang();
        listViewDH = (ListView) findViewById (R.id.list_don_hang);
        adapter = new DonHangAdapter(this, dsDH);
        listViewDH.setAdapter(adapter);
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

    public void themDonHang(View view){
        Intent intent2 = new Intent(this, ThemMoiDonHangActivity.class);
        startActivityForResult(intent2, REQUEST_CODE_ADD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_ADD: {
                Log.d("Return From", "Don Hang");
                switch (resultCode) {
                    case RESULT_CODE_ADD: {
                        DonHang dh = (DonHang) data.getExtras().get("Return_DH");
                        dsDH.add(0, DatabaseHelper.getInstance().layDonHang(dh.getId()));
                        adapter.notifyDataSetChanged();
                        break;
                    }

                }
                break;
            }

            case REQUEST_CODE_DETAIL: {
                Log.d("Return From", "Khach Hang");
                switch (resultCode) {
                    case RESULT_CODE_EDIT: {
                        DonHang dh = (DonHang) data.getExtras().get("Return_DH");
                        int i = data.getExtras().getInt("Return_Position");
                        dsDH.remove(i);
                        dsDH.add(0, DatabaseHelper.getInstance().layDonHang(dh.getId()));
                        adapter.notifyDataSetChanged();
                        break;
                    }

                    case RESULT_CODE_DELETE: {
                        int i = data.getExtras().getInt("Return_Position");
                        dsDH.remove(i);
                        adapter.notifyDataSetChanged();
                        break;
                    }

                }
                break;
            }

        }

    }


}
