package com.nhom03.hust.quanlydonhang.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.nhom03.hust.quanlydonhang.R;

/**
 * Created by sakura on 06/12/2016.
 */

public class XemDanhSachHangHoaCuaDonHang extends AppCompatActivity {

    private ListView listHangHoa;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xem_danh_sach_hang_hoa_trong_don_hang);

        listHangHoa = (ListView) findViewById(R.id.list_hang_hoa_cua_don_hang);


    }
}
