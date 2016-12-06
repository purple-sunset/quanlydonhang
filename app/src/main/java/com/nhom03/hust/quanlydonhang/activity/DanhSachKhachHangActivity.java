package com.nhom03.hust.quanlydonhang.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.adapter.KhachHangAdapter;
import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.KhachHang;

import java.util.ArrayList;

/**
 * Created by sakura on 01/12/2016.
 */

public class DanhSachKhachHangActivity extends AppCompatActivity {

    private Intent intent;
    private ArrayList<KhachHang> dsKH;
    private KhachHangAdapter adapter;
    private ListView listViewKH;
    private static final int REQUEST_CODE_DETAIL = 211;
    private static final int REQUEST_CODE_ADD = 212;

    private static final int RESULT_CODE_ADD = 221;
    private static final int RESULT_CODE_EDIT = 222;
    private static final int RESULT_CODE_DELETE = 223;

    private static final int RESULT_CODE_CHON_KHACHHANG = 121;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xem_danh_sach_khach_hang);

        intent = getIntent();
        dsKH = DatabaseHelper.getInstance().layDSKhachHang();
        adapter = new KhachHangAdapter(this, dsKH);
        listViewKH = (ListView) findViewById(R.id.list_khach_hang);
        listViewKH.setAdapter(adapter);
        if (getCallingActivity() == null) {
            listViewKH.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    final KhachHang kh = dsKH.get(i);
                    Intent intent2 = new Intent(DanhSachKhachHangActivity.this, XemChiTietKhachHangActivity.class);
                    intent2.putExtra("KH", kh);
                    intent2.putExtra("Position", i);
                    startActivityForResult(intent2, REQUEST_CODE_DETAIL);


                }
            });
        }
        else {
            listViewKH.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    final KhachHang kh = dsKH.get(i);
                    final Dialog dialog = new Dialog(DanhSachKhachHangActivity.this);
                    dialog.setContentView(R.layout.dialog);
                    TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
                    TextView textView = (TextView) dialog.findViewById(R.id.dialog_text);
                    Button btnOk = (Button) dialog.findViewById(R.id.dialog_ok);
                    Button btnCancel = (Button) dialog.findViewById(R.id.dialog_cancel);
                    btnOk.setText("OK");
                    btnCancel.setText("Cancel");
                    title.setText("Thành công");
                    textView.setText("Quay về trang trước?");
                    btnOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            intent.putExtra("Return_KH", kh);
                            setResult(RESULT_CODE_CHON_KHACHHANG, intent);
                            dialog.dismiss();
                            finish();
                        }
                    });

                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_DETAIL: {
                Log.d("Return From", "ChiTiet");
                switch (resultCode) {
                    case RESULT_CODE_EDIT: {
                        KhachHang kh = (KhachHang) data.getExtras().getSerializable("Return_KH");
                        int i = data.getExtras().getInt("Return_Position");
                        dsKH.remove(i);
                        dsKH.add(0, kh);
                        adapter.notifyDataSetChanged();
                        break;
                    }

                    case RESULT_CODE_DELETE: {
                        int i = data.getExtras().getInt("Return_Position");
                        dsKH.remove(i);
                        adapter.notifyDataSetChanged();
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
                        adapter.notifyDataSetChanged();
                        break;
                    }
                }
                break;
            }


        }

    }

    public void themKhachHang(View view) {
        Intent intent2 = new Intent(this, ThemMoiKhachHangActivity.class);
        startActivityForResult(intent, REQUEST_CODE_ADD);
    }

}
