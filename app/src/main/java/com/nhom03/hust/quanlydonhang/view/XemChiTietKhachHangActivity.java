package com.nhom03.hust.quanlydonhang.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.adapter.KhachHangAdapter;
import com.nhom03.hust.quanlydonhang.model.KhachHang;
import com.nhom03.hust.quanlydonhang.model.ListKhachHang;
import com.nhom03.hust.quanlydonhang.model.NguoiDung;
import com.nhom03.hust.quanlydonhang.rest.ApiInterface;
import com.nhom03.hust.quanlydonhang.rest.ApiKhachHang;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sakura on 01/12/2016.
 */

public class XemChiTietKhachHangActivity extends AppCompatActivity {

    private NguoiDung nguoiDung;

    private KhachHang khachHang;

    private EditText diaChi;
    private EditText thanhPho;
    private EditText tenLienLac;
    private EditText thongTinLienLac;
    private EditText quocGia;
    private EditText iDKhachHang;
    private EditText fax;
    private EditText soDienThoai;
    private EditText maBuuChinh;
    private EditText khuVuc;

    private Button suaKhachHang;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xem_chi_tiet_khach_hang);

        connectToVIew();

        suaKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                suaKhachHang();
            }
        });
    }

    private void connectToVIew() {
        diaChi = (EditText) findViewById(R.id.ET_dia_chi);
        thanhPho = (EditText) findViewById(R.id.ET_thanh_pho);
        tenLienLac = (EditText) findViewById(R.id.ET_ten_lien_lac);
        thongTinLienLac = (EditText) findViewById(R.id.ET_thong_tin_lien_lac);
        quocGia = (EditText) findViewById(R.id.ET_quoc_gia);
        iDKhachHang = (EditText) findViewById(R.id.ET_id_khach_hang);
        fax = (EditText) findViewById(R.id.ET_fax);
        soDienThoai = (EditText) findViewById(R.id.ET_sdt);
        maBuuChinh = (EditText) findViewById(R.id.ET_ma_buu_chinh);
        khuVuc = (EditText) findViewById(R.id.ET_khu_vuc);

        suaKhachHang = (Button) findViewById(R.id.btn_sua_khach_hang);

    }

    private void suaKhachHang() {
        KhachHang khachHang1 = getKhachHang();
        ApiInterface apiService = ApiKhachHang.getListKhachHang().create(ApiInterface.class);

        Call<String> call = apiService.suaKhacHang(nguoiDung.getCookie(),khachHang1);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String respone = response.body().toString();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private KhachHang getKhachHang() {
        khachHang.setDiaChi(diaChi.getText().toString());
        khachHang.setThanhPho(thanhPho.getText().toString());
        khachHang.setTenKH(tenLienLac.getText().toString());
        khachHang.setTieuDe(thongTinLienLac.getText().toString());
        khachHang.setQuocGia(quocGia.getText().toString());
        khachHang.setId(iDKhachHang.getText().toString());
        khachHang.setFax(fax.getText().toString());
        khachHang.setSdt(soDienThoai.getText().toString());
        khachHang.setMaBuuChinh(maBuuChinh.getText().toString());
        khachHang.setTenKH(khuVuc.getText().toString());
        return khachHang;
    }
}
