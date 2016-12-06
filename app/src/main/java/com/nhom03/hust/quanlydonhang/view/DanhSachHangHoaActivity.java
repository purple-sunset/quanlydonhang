package com.nhom03.hust.quanlydonhang.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.adapter.KhachHangAdapter;
import com.nhom03.hust.quanlydonhang.adapter.TheLoaiAdapter;
import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.HangHoa;
import com.nhom03.hust.quanlydonhang.model.KhachHang;
import com.nhom03.hust.quanlydonhang.model.TheLoai;

import java.util.ArrayList;

/**
 * Created by Admin on 06/12/2016.
 */

public class DanhSachHangHoaActivity extends AppCompatActivity {

    private ArrayList<TheLoai> dsTL;
    private TheLoaiAdapter adapter;
    private ExpandableListView listViewTL;

    private static final int REQUEST_CODE_DETAIL = 311;
    private static final int REQUEST_CODE_ADD = 312;
    private static final int RESULT_CODE_ADD = 321;
    private static final int RESULT_CODE_EDIT = 322;
    private static final int RESULT_CODE_DELETE = 323;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xem_danh_sach_hang_hoa);
        dsTL = DatabaseHelper.getInstance().layDSTheLoai();
        adapter = new TheLoaiAdapter(this, dsTL);
        listViewTL = (ExpandableListView) findViewById(R.id.list_hang_hoa);
        listViewTL.setAdapter(adapter);

        listViewTL.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                HangHoa hh = dsTL.get(groupPosition).getDsHangHoa().get(childPosition);
                Intent intent = new Intent(DanhSachHangHoaActivity.this, XemChiTietHangHoaActivity.class);
                intent.putExtra("HH", hh);
                startActivityForResult(intent, REQUEST_CODE_DETAIL);
                return false;
            }
        });

        listViewTL.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                return false;
            }
        });

        listViewTL.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });

        listViewTL.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
