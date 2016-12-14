package com.nhom03.hust.quanlydonhang.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TimePicker;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.DonHang;
import com.nhom03.hust.quanlydonhang.model.HangHoa;
import com.nhom03.hust.quanlydonhang.model.HangHoaCuaDonHang;
import com.nhom03.hust.quanlydonhang.model.KhachHang;
import com.nhom03.hust.quanlydonhang.rest.APIDonHang;
import com.nhom03.hust.quanlydonhang.rest.APIHangHoaCuaDonHang;
import com.nhom03.hust.quanlydonhang.rest.APIKhachHang;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by longs on 06/12/2016.
 */

public class XemChiTietDonHangActivity extends ActionBarActivity {

    private static final int REQUEST_CODE_KHACHHANG = 111;
    private static final int REQUEST_CODE_HANGHOA = 112;

    private static final int RESULT_CODE_BACK = 120;
    private static final int RESULT_CODE_CHON_KHACHHANG = 121;
    private static final int RESULT_CODE_CHON_HANGHOA = 122;
    private static final int RESULT_CODE_DONE = 123;


    private Intent intent;
    private DonHang donHang;
    private int position;
    private boolean editable = false;

    private ArrayList<HangHoaCuaDonHang> dsHH;
    private ArrayList<HangHoaCuaDonHang> dsThem = new ArrayList<>();
    private ArrayList<HangHoaCuaDonHang> dsSua= new ArrayList<>();
    private ArrayList<HangHoaCuaDonHang> dsXoa= new ArrayList<>();
    private ArrayList<Integer> vtSua= new ArrayList<>();
    private ArrayList<Integer> vtXoa= new ArrayList<>();

    private Button tenKH;
    private Button ngayDatHang;
    private EditText diaChiGiaoHang;

    private Button xoaDonhang;
    private Button suaDonHang;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xem_thong_tin_don_hang);

        intent = getIntent();
        donHang = (DonHang) intent.getExtras().getSerializable("DH");
        position = intent.getExtras().getInt("Position");
        dsHH = donHang.getDsHang();

        tenKH = (Button) findViewById(R.id.xttdh_ten_khach_hang);
        tenKH.setText(donHang.getKhachHang().getTenKH());

        ngayDatHang = (Button) findViewById(R.id.xttdh_ngay_dat_hang);
        ngayDatHang.setText(donHang.getNgayGio().toLocaleString());

        diaChiGiaoHang = (EditText) findViewById(R.id.xttdh_dia_chi_giao_hang);
        diaChiGiaoHang.setText(diaChi());

        xoaDonhang = (Button) findViewById(R.id.xttdh_BT_xoa_don_hang);
        suaDonHang = (Button) findViewById(R.id.BT_sua_don_hang);

    }

    private String diaChi() {
        if (donHang.getDiaChiGiaoHang().equals("")) {
            return donHang.getKhachHang().getDiaChi();
        } else
            return donHang.getDiaChiGiaoHang();
    }

    @Override
    public void onBackPressed() {

        intent.putExtra("Return", "Back Success");
        setResult(RESULT_CODE_BACK, intent);
        finish();
    }


    public void suaTTKhachHang(View view) {
        Intent intent2 = new Intent(this, DanhSachKhachHangActivity.class);
        intent2.putExtra("Calling_Activity", "DonHangActivity");
        intent2.putExtra("KH", donHang.getKhachHang());
        startActivityForResult(intent2, REQUEST_CODE_KHACHHANG);
    }

    public void suaNgayGio(View view) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.date_time_picker);

        final TabHost tabs = (TabHost) dialog.findViewById(R.id.tabhost);

        tabs.setup();

        TabHost.TabSpec dateTab = tabs.newTabSpec("Ngày");
        dateTab.setContent(R.id.date_tab);
        dateTab.setIndicator("date");

        TabHost.TabSpec timeTab = tabs.newTabSpec("Giờ");
        timeTab.setContent(R.id.time_tab);
        timeTab.setIndicator("time");

        tabs.addTab(dateTab);
        tabs.addTab(timeTab);

        final DatePicker datePicker = (DatePicker) dialog.findViewById(R.id.date_picker);
        final TimePicker timePicker = (TimePicker) dialog.findViewById(R.id.time_picker);
        Button btnOK = (Button) dialog.findViewById(R.id.date_time_set);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = new GregorianCalendar(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getCurrentHour(), timePicker.getCurrentMinute());
                Date d = c.getTime();
                donHang.setNgayGio(d);
                ngayDatHang.setText(donHang.getNgayGio().toLocaleString());
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void xemTTHangHoa(View view) {
        Intent intent3 = new Intent(this, DanhSachHangHoaCuaDonHangActivity.class);
        intent3.putExtra("Calling_Activity", "ChiTietDonHangActivity");
        intent3.putExtra("DSHH", dsHH);
        intent3.putExtra("IDDH", donHang.getId());
        intent3.putExtra("Editable", editable);
        startActivityForResult(intent3, REQUEST_CODE_HANGHOA);
    }


    public void suaTTDonHang(View view) {
        if (suaDonHang.getText().equals("Lưu")) {
            if (dsXoa != null)
                for (HangHoaCuaDonHang hhdh : dsXoa
                        ) {
                    APIHangHoaCuaDonHang.xoaHangHoaCuaDonHang(donHang, 0, hhdh, this, intent);
                }

            if (dsSua != null)
                for (HangHoaCuaDonHang hhdh : dsSua
                        ) {
                    APIHangHoaCuaDonHang.suaHangHoaCuaDonHang(donHang, 0, hhdh, this, intent);
                }

            if (dsThem != null)
                for (HangHoaCuaDonHang hhdh : dsThem
                        ) {
                    APIHangHoaCuaDonHang.themHangHoaCuaDonHang(donHang, hhdh, this, intent);
                }


            ngayDatHang.setEnabled(true);
            diaChiGiaoHang.setEnabled(true);

            donHang.setDiaChiGiaoHang(diaChiGiaoHang.getText().toString());
            donHang.setThanhPhoGiaoHang(donHang.getKhachHang().getThanhPho());
            donHang.setVungGiaoHang(donHang.getKhachHang().getVung());
            donHang.setQuocGiaGiaoHang(donHang.getKhachHang().getQuocGia());
            donHang.setMbcGiaoHang(donHang.getKhachHang().getMaBuuChinh());
            APIDonHang.suaDonHang(position, donHang, this, intent);
        } else if (suaDonHang.getText().equals("Sửa")) {
            editable = true;
            tenKH.setEnabled(true);
            ngayDatHang.setEnabled(true);
            diaChiGiaoHang.setEnabled(true);
            suaDonHang.setText("Lưu");
            xoaDonhang.setVisibility(View.INVISIBLE);
        }


    }

    public void xoaDonHang(View view) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        TextView textView = (TextView) dialog.findViewById(R.id.dialog_text);
        Button btnOk = (Button) dialog.findViewById(R.id.dialog_ok);
        Button btnCancel = (Button) dialog.findViewById(R.id.dialog_cancel);

        btnOk.setText("Có");
        btnCancel.setText("Không");

        title.setText("Xóa đơn hàng");
        textView.setText("Bạn có muốn xóa đơn hàng không?");
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (donHang.getDsHang().size() > 0)
                    APIHangHoaCuaDonHang.xoaDonHang(donHang, position, XemChiTietDonHangActivity.this, intent);
                else
                    APIDonHang.xoaDonHang(position, donHang, XemChiTietDonHangActivity.this, intent);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_KHACHHANG: {
                Log.d("Return From", "Khach Hang");
                switch (resultCode) {
                    case RESULT_CODE_CHON_KHACHHANG: {
                        KhachHang kh = (KhachHang) data.getExtras().getSerializable("Return_KH");
                        donHang.setKhachHang(kh);
                        tenKH.setText(donHang.getKhachHang().getTenKH());
                        break;
                    }

                }
                break;
            }

            case REQUEST_CODE_HANGHOA: {
                switch (resultCode) {
                    case RESULT_CODE_DONE: {
                        if(data.getExtras().get("DS_Them") != null) {
                            dsThem.addAll((ArrayList<HangHoaCuaDonHang>)data.getExtras().get("DS_Them"));
                            if (dsThem.size() > 0)
                                for (HangHoaCuaDonHang hhdh : dsThem
                                        ) {
                                    dsHH.add(0, hhdh);
                                }
                        }

                        if(data.getExtras().get("DS_Sua") != null){
                            dsSua.addAll((ArrayList<HangHoaCuaDonHang>) data.getExtras().get("DS_Sua"));
                            vtSua.addAll((ArrayList<Integer>) data.getExtras().get("VT_Sua"));
                            if (dsSua.size() > 0)
                                for (int i : vtXoa
                                        ) {
                                    dsHH.remove(i);
                                    dsHH.add(i, dsSua.get(i));
                                }

                    }

                        if(data.getExtras().get("DS_Xoa") != null) {
                            dsXoa.addAll((ArrayList<HangHoaCuaDonHang>) data.getExtras().get("DS_Xoa"));
                            vtXoa.addAll((ArrayList<Integer>) data.getExtras().get("VT_Xoa"));
                            if (dsXoa.size() > 0)
                                for (int i : vtXoa
                                        ) {
                                    dsHH.remove(i);
                                }
                        }

                    }
                }
            }


        }

    }

}
