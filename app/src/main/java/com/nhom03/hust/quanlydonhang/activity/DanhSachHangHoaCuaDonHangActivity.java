package com.nhom03.hust.quanlydonhang.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.adapter.HangHoaCuaDonHangAdapter;
import com.nhom03.hust.quanlydonhang.model.HangHoa;
import com.nhom03.hust.quanlydonhang.model.HangHoaCuaDonHang;
import com.nhom03.hust.quanlydonhang.model.KhachHang;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by longs on 06/12/2016.
 */
public class DanhSachHangHoaCuaDonHangActivity extends ActionBarActivity {

    private static final int REQUEST_CODE_DETAIL = 511;
    private static final int REQUEST_CODE_ADD = 512;
    private static final int REQUEST_CODE_EDIT = 513;
    private static final int REQUEST_CODE_DELETE = 514;


    private static final int RESULT_CODE_BACK = 520;
    private static final int RESULT_CODE_ADD = 521;
    private static final int RESULT_CODE_EDIT = 522;
    private static final int RESULT_CODE_DELETE = 523;
    private static final int RESULT_CODE_DONE = 123;

    private static final int RESULT_CODE_CHON_HANG_HOA_CUA_DON_HAN = 600;

    private Intent intent;
    private boolean editable;
    private long idDH;
    private String callingActivity = "";

    private HangHoaCuaDonHangAdapter adapter;
    private ArrayList<HangHoaCuaDonHang> dsHangHoaCuaDonHang;
    private ListView listViewHangHoaDonHang;
    private SearchView searchView;

    private ArrayList<HangHoaCuaDonHang> dsThem;
    private ArrayList<HangHoaCuaDonHang> dsSua;
    private ArrayList<HangHoaCuaDonHang> dsXoa;

    private ArrayList<Integer> vtSua;
    private ArrayList<Integer> vtXoa;

    private TextView tongTien;
    private MenuItem themHangHoa;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xem_danh_sach_hang_hoa_trong_don_hang);

        intent = getIntent();

        if (intent.getExtras() != null)
            callingActivity = intent.getExtras().getString("Calling_Activity");

        if (intent.getExtras().get("DSHH") == null)
            dsHangHoaCuaDonHang = new ArrayList<>();
        else
            dsHangHoaCuaDonHang = (ArrayList<HangHoaCuaDonHang>) intent.getExtras().get("DSHH");
        editable = intent.getExtras().getBoolean("Editable");
        idDH = intent.getExtras().getLong("IDDH");

        dsThem = new ArrayList<>();
        dsSua = new ArrayList<>();
        dsXoa = new ArrayList<>();
        vtSua = new ArrayList<>();
        vtXoa = new ArrayList<>();

        tongTien = (TextView) findViewById(R.id.dshhdh_tong_tien);
        tinhTien();

        adapter = new HangHoaCuaDonHangAdapter(dsHangHoaCuaDonHang, this);
        listViewHangHoaDonHang = (ListView) findViewById(R.id.list_hang_hoa_cua_don_hang);
        listViewHangHoaDonHang.setAdapter(adapter);

        if (editable) {
            listViewHangHoaDonHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    HangHoaCuaDonHang hangHoaCuaDonHang = dsHangHoaCuaDonHang.get(i);
                    Intent intent2 = new Intent(DanhSachHangHoaCuaDonHangActivity.this, XemChiTietHangHoaCuaDonHangActivity.class);
                    intent2.putExtra("HHDH", hangHoaCuaDonHang);
                    intent2.putExtra("Position", i);
                    intent2.putExtra("Editable", true);
                    startActivityForResult(intent2, REQUEST_CODE_DETAIL);

                }
            });
        }

    }

    private void tinhTien() {
        float f = 0;
        if (dsHangHoaCuaDonHang != null)
            for (HangHoaCuaDonHang hhdh : dsHangHoaCuaDonHang
                    ) {
                f += hhdh.thanhTien();
            }

        tongTien.setText(String.valueOf((long) f * 10000));
    }

    @Override
    public void onBackPressed() {
        if (editable) {

            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog);
            TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
            TextView textView = (TextView) dialog.findViewById(R.id.dialog_text);
            Button btnOk = (Button) dialog.findViewById(R.id.dialog_ok);
            Button btnCancel = (Button) dialog.findViewById(R.id.dialog_cancel);

            btnOk.setText("OK");
            btnCancel.setText("Cancel");

            title.setText("Sửa danh sách hàng");
            textView.setText("Bạn có muốn thực hiện không?");

            switch (callingActivity) {
                case "ThemDonHangActivity": {
                    btnOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            intent.putExtra("DS_Them", dsThem);
                            intent.putExtra("DS_Sua", dsSua);
                            intent.putExtra("VT_Sua", vtSua);
                            intent.putExtra("DS_Xoa", dsXoa);
                            intent.putExtra("VT_Xoa", vtXoa);
                            setResult(RESULT_CODE_DONE, intent);
                            dialog.dismiss();
                            finish();
                        }
                    });
                }

                case "ChiTietDonHangActivity": {
                    btnOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            intent.putExtra("DS_Them", dsThem);
                            intent.putExtra("DS_Sua", dsSua);
                            intent.putExtra("VT_Sua", vtSua);
                            intent.putExtra("DS_Xoa", dsXoa);
                            intent.putExtra("VT_Xoa", vtXoa);
                            setResult(RESULT_CODE_DONE, intent);
                            dialog.dismiss();
                            finish();
                        }
                    });
                }
            }


            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent.putExtra("Return", "Back Success");
                    setResult(RESULT_CODE_BACK, intent);
                    dialog.dismiss();
                    finish();
                }
            });


            dialog.show();


        } else {
            intent.putExtra("Return", "Back Success");
            setResult(RESULT_CODE_BACK, intent);
            finish();
        }

    }

    public void themHangHoa(MenuItem item) {
        Intent intent3 = new Intent(this, ThemMoiHangHoaVaoDonHangActivity.class);
        intent3.putExtra("IDDH", idDH);
        startActivityForResult(intent3, REQUEST_CODE_ADD);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_ADD: {
                switch (resultCode) {
                    case RESULT_CODE_ADD:
                        HangHoaCuaDonHang hhdh = (HangHoaCuaDonHang) data.getExtras().get("HHDH");
                        dsThem.add(hhdh);
                        dsHangHoaCuaDonHang.add(0, hhdh);
                        adapter.notifyDataSetChanged();
                        tinhTien();
                        break;
                }
                break;
            }

            case REQUEST_CODE_DETAIL: {
                switch (resultCode) {
                    case RESULT_CODE_EDIT:
                        HangHoaCuaDonHang hhdh = (HangHoaCuaDonHang) data.getExtras().get("Return_HHDH");
                        int i = data.getExtras().getInt("Return_Position");
                        dsSua.add(hhdh);
                        vtSua.add(i);
                        dsHangHoaCuaDonHang.remove(i);
                        dsHangHoaCuaDonHang.add(0, hhdh);
                        adapter.notifyDataSetChanged();
                        tinhTien();
                        break;

                    case RESULT_CODE_DELETE:
                        HangHoaCuaDonHang hhdh2 = (HangHoaCuaDonHang) data.getExtras().get("Return_HHDH");
                        int i2 = data.getExtras().getInt("Return_Position");
                        dsXoa.add(hhdh2);
                        vtXoa.add(i2);
                        dsHangHoaCuaDonHang.remove(i2);
                        adapter.notifyDataSetChanged();
                        tinhTien();
                        break;
                }
                break;
            }


        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ds_hhdh, menu);
        themHangHoa = menu.findItem(R.id.action_them_hhdh);
        if(editable)
            themHangHoa.setVisible(true);

        searchView = (SearchView) menu.findItem(R.id.action_timkiem_hhdh).getActionView();
        searchView.setFocusable(false);
        searchView.setQueryHint("Tìm kiếm hàng hóa");
        //set OnQueryTextListener cho search view để thực hiện search theo text
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.equals("")){
                    adapter.getFilter().filter("");
                    listViewHangHoaDonHang.clearTextFilter();
                }else {
                    adapter.getFilter().filter(s);
                }
                return true;
            }
        });

        return true;
    }
}
