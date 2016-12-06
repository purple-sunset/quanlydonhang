package com.nhom03.hust.quanlydonhang.view;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.adapter.ChiTietDonHangAdapter;
import com.nhom03.hust.quanlydonhang.adapter.KhachHangAdapter;
import com.nhom03.hust.quanlydonhang.adapter.TheLoaiAdapter;
import com.nhom03.hust.quanlydonhang.model.ChiTietDonHang;
import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.HangHoa;
import com.nhom03.hust.quanlydonhang.model.KhachHang;
import java.util.ArrayList;
/**
 * Created by longs on 06/12/2016.
 */
public class DanhSachHangHoaCuaDonHangActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_DETAIL = 500;
    private static final int RESULT_CODE_CHON_HANG_HOA_CUA_DON_HAN = 600;

    private ChiTietDonHangAdapter adapter;
    private ArrayList<ChiTietDonHang> dsChiTietDonHang;
    private ListView listViewChiTietDonHang;
    private Intent intent;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xem_danh_sach_hang_hoa);
        intent=getIntent();
        dsChiTietDonHang= (ArrayList<ChiTietDonHang>) intent.getExtras().get("DsCTHH");
        adapter = new ChiTietDonHangAdapter(dsChiTietDonHang, this);
        listViewChiTietDonHang = (ExpandableListView) findViewById(R.id.list_hang_hoa);
        listViewChiTietDonHang.setAdapter(adapter);
        listViewChiTietDonHang.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChiTietDonHang chiTietDonHang = dsChiTietDonHang.get(i);
                if(intent != null) {
                    Intent intent2 = new Intent(DanhSachHangHoaCuaDonHangActivity.this, XemChiTietHangHoaCuaDonHangActivity.class);
                    intent2.putExtra("HH", chiTietDonHang );
                    intent2.putExtra("Position", i);
                    startActivityForResult(intent2, REQUEST_CODE_DETAIL);
                }
                else {
                    intent.putExtra("Return_KH", chiTietDonHang);
                    //setResult(RESULT_CODE_CHON_HANG_HOA_CUA_DON_HANG, intent);
                    finish();
                }
            }
        });
    }
}
