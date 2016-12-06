package com.nhom03.hust.quanlydonhang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.HangHoa;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    private ListView list;
    private Button btn;
    private ArrayAdapter<String> adapter;
    private ArrayList<HangHoa> dsHang;
    private ArrayList<String> ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //
        btn = (Button) findViewById(R.id.btnTest);
        list = (ListView) findViewById(R.id.listTest);

        //

       // HangHoa hh1 = new HangHoa(1, "abc", false, 50000, 50, 2, "chi tiet");
        //HangHoa hh2 = new HangHoa(2, "xyz", true, 10000, 20, 3, "chi");
        //db.themHangHoa(hh1);
       // db.themHangHoa(hh2);

        dsHang = DatabaseHelper.getInstance().layDSHangHoa();
        ds = new ArrayList<String>();
        for (HangHoa hang:dsHang
             ) {
            ds.add(hang.getTen());
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ds);

        list.setAdapter(adapter);
    }

    public void onClick(View view){
        DatabaseHelper.getInstance().xoaHangHoa(dsHang.get(0));
        dsHang = DatabaseHelper.getInstance().layDSHangHoa();
        ds = new ArrayList<String>();
        for (HangHoa hang:dsHang
                ) {
            ds.add(hang.getTen());
        }
        adapter.notifyDataSetChanged();
        list.invalidate();
    }

}
