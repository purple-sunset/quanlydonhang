package com.nhom03.hust.quanlydonhang.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.adapter.TheLoaiAdapter;
import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.HangHoa;
import com.nhom03.hust.quanlydonhang.model.KhachHang;
import com.nhom03.hust.quanlydonhang.model.TheLoai;

import java.util.ArrayList;

/**
 * Created by Admin on 06/12/2016.
 */

public class DanhSachHangHoaActivity extends ActionBarActivity {

    private Intent intent;
    private String callingActivity = "";

    private ArrayList<TheLoai> dsTL;
    private TheLoaiAdapter adapter;
    private ExpandableListView listViewTL;
    private SearchView searchView;

    private static final int REQUEST_CODE_DETAIL = 311;
    private static final int REQUEST_CODE_ADD = 312;
    private static final int RESULT_CODE_ADD = 321;
    private static final int RESULT_CODE_EDIT = 322;
    private static final int RESULT_CODE_DELETE = 323;
    private static final int RESULT_CODE_HANGHOA = 522;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xem_danh_sach_hang_hoa);

        intent = getIntent();
        if(intent.getExtras() != null)
            callingActivity = intent.getExtras().getString("Calling_Activity");

        dsTL = DatabaseHelper.getInstance().layDSTheLoai();
        adapter = new TheLoaiAdapter(this, dsTL);
        listViewTL = (ExpandableListView) findViewById(R.id.list_hang_hoa);
        listViewTL.setAdapter(adapter);

        switch (callingActivity) {
            case "DonHangActivity": {
                listViewTL.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                        final HangHoa hh = dsTL.get(groupPosition).getDsHangHoa().get(childPosition);
                        final Dialog dialog = new Dialog(DanhSachHangHoaActivity.this);
                        dialog.setContentView(R.layout.dialog);
                        TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
                        TextView textView = (TextView) dialog.findViewById(R.id.dialog_text);
                        Button btnOk = (Button) dialog.findViewById(R.id.dialog_ok);
                        Button btnCancel = (Button) dialog.findViewById(R.id.dialog_cancel);

                        btnOk.setText("OK");
                        btnCancel.setText("Cancel");

                        if(!hh.isHuyBan()) {
                            title.setText("Thành công");
                            textView.setText("Quay về trang trước?");
                            btnOk.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    intent.putExtra("HH", hh);
                                    setResult(RESULT_CODE_HANGHOA, intent);
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
                        }
                        else {
                            title.setText("Lỗi");
                            textView.setText("Đóng hộp thoại này?");
                            btnCancel.setVisibility(View.INVISIBLE);
                            btnOk.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
                                }
                            });
                        }

                        dialog.show();
                        return false;
                    }
                });
                break;
            }

            default: {
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
            }
        }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ds_hanghoa, menu);


        searchView = (SearchView) menu.findItem(R.id.action_timkiem_hanghoa).getActionView();
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
                    listViewTL.clearTextFilter();
                }else {
                    adapter.getFilter().filter(s);
                }
                return true;
            }
        });

        return true;
    }
}
