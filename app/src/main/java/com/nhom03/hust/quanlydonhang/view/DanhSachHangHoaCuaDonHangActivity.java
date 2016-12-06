package com.nhom03.hust.quanlydonhang.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.adapter.TheLoaiAdapter;
import com.nhom03.hust.quanlydonhang.model.ChiTietDonHang;
import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.HangHoa;

import java.util.ArrayList;

/**
 * Created by longs on 06/12/2016.
 */

public class DanhSachHangHoaCuaDonHangActivity extends AppCompatActivity {

    private Intent intent;
    private ArrayList<ChiTietDonHang> dsChiTietDonHang;


    /*protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xem_danh_sach_hang_hoa);
        intent=getIntent();
        dsChiTietDonHang=intent.getExtras().get("dsChiTietDonHang");
        dsChiTietDonHang = DatabaseHelper.getInstance().layDSTheLoai();
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
    }*/
}
