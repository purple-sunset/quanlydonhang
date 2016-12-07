package com.nhom03.hust.quanlydonhang.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TimePicker;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.DonHang;
import com.nhom03.hust.quanlydonhang.model.HangHoaCuaDonHang;
import com.nhom03.hust.quanlydonhang.model.KhachHang;
import com.nhom03.hust.quanlydonhang.rest.APIDonHang;
import com.nhom03.hust.quanlydonhang.rest.APIHangHoaCuaDonHang;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Admin on 07/12/2016.
 */

public class ThemMoiDonHangActivity extends AppCompatActivity {


    private static final int REQUEST_CODE_KHACHHANG = 111;
    private static final int REQUEST_CODE_HANGHOA = 112;
    private static final int REQUEST_CODE_ADD = 012;

    private static final int RESULT_CODE_BACK = 120;
    private static final int RESULT_CODE_CHON_KHACHHANG = 121;
    private static final int RESULT_CODE_CHON_HANGHOA = 122;
    private static final int RESULT_CODE_DONE = 123;


    private Intent intent;
    private DonHang donHang;
    private int position;
    private boolean editable = false;

    private ArrayList<HangHoaCuaDonHang> dsHH= new ArrayList<>();
    private ArrayList<HangHoaCuaDonHang> dsThem= new ArrayList<>();
    private ArrayList<HangHoaCuaDonHang> dsSua= new ArrayList<>();
    private ArrayList<HangHoaCuaDonHang> dsXoa= new ArrayList<>();
    private ArrayList<Integer> vtSua= new ArrayList<>();
    private ArrayList<Integer> vtXoa= new ArrayList<>();

    private Button tenKH;
    private Button ngayDatHang;
    private EditText diaChiGiaoHang;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them_moi_don_hang);

        intent = getIntent();
        donHang = new DonHang();

        tenKH = (Button) findViewById(R.id.tdh_ten_khach_hang);


        ngayDatHang = (Button) findViewById(R.id.tdh_ngay_dat_hang);


        diaChiGiaoHang = (EditText) findViewById(R.id.tdh_dia_chi);


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

    public void suaTTKhachHang2(View view) {
        Intent intent2 = new Intent(this, DanhSachKhachHangActivity.class);
        intent2.putExtra("Calling_Activity", "DonHangActivity");
        intent2.putExtra("KH", donHang.getKhachHang());
        startActivityForResult(intent2, REQUEST_CODE_KHACHHANG);
    }

    public void suaNgayGio2(View view) {
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

    public void themHangHoa2(View view) {
        Intent intent3 = new Intent(this, DanhSachHangHoaCuaDonHangActivity.class);
        intent3.putExtra("Calling_Activity", "ThemDonHangActivity");
        intent3.putExtra("DSHH", donHang.getDsHang());
        intent3.putExtra("IDDH", donHang.getId());
        intent3.putExtra("Editable", true);
        startActivityForResult(intent3, REQUEST_CODE_HANGHOA);
    }

    public void themDonHang2(View view) {
        donHang.setDsHang(dsHH);
        donHang.setDiaChiGiaoHang(diaChiGiaoHang.getText().toString());
        donHang.setThanhPhoGiaoHang(donHang.getKhachHang().getThanhPho());
        donHang.setVungGiaoHang(donHang.getKhachHang().getVung());
        donHang.setQuocGiaGiaoHang(donHang.getKhachHang().getQuocGia());
        donHang.setMbcGiaoHang(donHang.getKhachHang().getMaBuuChinh());
        APIDonHang.themDonHang(donHang, dsHH, this, intent);
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
                        if (data.getExtras().get("DS_Them") != null) {
                            dsThem.addAll((ArrayList<HangHoaCuaDonHang>) data.getExtras().get("DS_Them"));
                            if (dsThem.size() > 0)
                                for (HangHoaCuaDonHang hhdh : dsThem
                                        ) {
                                    dsHH.add(0, hhdh);
                                }
                        }

                        if (data.getExtras().get("DS_Sua") != null) {
                            dsSua.addAll((ArrayList<HangHoaCuaDonHang>) data.getExtras().get("DS_Sua"));
                            vtSua.addAll((ArrayList<Integer>) data.getExtras().get("VT_Sua"));
                            if (dsSua.size() > 0)
                                for (int i : vtXoa
                                        ) {
                                    dsHH.remove(i);
                                    dsHH.add(i, dsSua.get(i));
                                }

                        }

                        if (data.getExtras().get("DS_Xoa") != null) {
                            dsXoa.addAll((ArrayList<HangHoaCuaDonHang>) data.getExtras().get("DS_Xoa"));
                            vtXoa.addAll((ArrayList<Integer>) data.getExtras().get("VT_Xoa"));
                            if (dsXoa.size() > 0)
                                for (int i : vtXoa
                                        ) {
                                    dsHH.remove(i);
                                }
                        }
                        break;
                    }
                }
                break;
            }


        }

    }
}
