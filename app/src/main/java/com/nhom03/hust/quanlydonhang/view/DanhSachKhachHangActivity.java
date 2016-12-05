package com.nhom03.hust.quanlydonhang.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.adapter.KhachHangAdapter;
import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.KhachHang;

import java.util.ArrayList;

/**
 * Created by sakura on 01/12/2016.
 */

public class DanhSachKhachHangActivity extends AppCompatActivity {

    private ArrayList<KhachHang> dsKH;
    private ListView listViewKH;
    private static final int REQUEST_CODE_DETAIL = 311;
    private static final int REQUEST_CODE_ADD = 312;

    private static final int RESULT_CODE_ADD = 321;
    private static final int RESULT_CODE_EDIT = 322;
    private static final int RESULT_CODE_DELETE = 323;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xem_danh_sach_khach_hang);
        dsKH = DatabaseHelper.getInstance().layDSKhachHang();
        listViewKH = (ListView) findViewById(R.id.list_khach_hang);
        listViewKH.setAdapter(new KhachHangAdapter(this, dsKH));
        listViewKH.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                KhachHang kh = dsKH.get(i);
                Intent intent = new Intent(DanhSachKhachHangActivity.this, XemChiTietKhachHangActivity.class);
                intent.putExtra("KH", kh);
                intent.putExtra("Position", i);
                startActivityForResult(intent, REQUEST_CODE_DETAIL);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE_DETAIL: {
                Log.d("Return From", "ChiTiet");
                switch (resultCode) {
                    case RESULT_CODE_EDIT: {
                        KhachHang kh = (KhachHang) data.getExtras().getSerializable("Return_KH");
                        int i = data.getExtras().getInt("Return_Position");
                        dsKH.remove(i);
                        dsKH.add(0, kh);
                        ((KhachHangAdapter) listViewKH.getAdapter()).notifyDataSetChanged();
                        break;
                    }

                    case RESULT_CODE_DELETE: {
                        int i = data.getExtras().getInt("Return_Position");
                        dsKH.remove(i);
                        ((KhachHangAdapter) listViewKH.getAdapter()).notifyDataSetChanged();
                        break;
                    }

                }
                break;
            }

            case REQUEST_CODE_ADD: {
                Log.d("Return From", "Them");
                switch (resultCode) {
                    case RESULT_CODE_ADD: {
                        KhachHang kh = (KhachHang) data.getExtras().getSerializable("Return_KH");
                        dsKH.add(0, kh);
                        ((KhachHangAdapter) listViewKH.getAdapter()).notifyDataSetChanged();
                        break;
                    }
                }
                break;
            }


        }

    }

    public void themKhachHang(View view) {
        Intent intent = new Intent(this, ThemMoiKhachHangActivity.class);
        startActivityForResult(intent, REQUEST_CODE_ADD);
    }

}
