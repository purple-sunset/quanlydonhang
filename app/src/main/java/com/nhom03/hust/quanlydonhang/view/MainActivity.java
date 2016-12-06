package com.nhom03.hust.quanlydonhang.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DialogTitle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.ChiTietDonHang;
import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.KhachHang;
import com.nhom03.hust.quanlydonhang.model.NguoiDung;
import com.nhom03.hust.quanlydonhang.rest.APIChiTietDonHang;
import com.nhom03.hust.quanlydonhang.rest.APIDonHang;
import com.nhom03.hust.quanlydonhang.rest.APIHangHoa;
import com.nhom03.hust.quanlydonhang.rest.APIKhachHang;
import com.nhom03.hust.quanlydonhang.rest.APITheLoai;

import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NguoiDung nguoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtUserName = (TextView) findViewById(R.id.txt_user);
        /*Intent dangNhap = getIntent();
        nguoiDung = (NguoiDung) dangNhap.getExtras().getSerializable("NGUOI_DUNG");
        txtUserName.setText(nguoiDung.getTen());*/
    }

    public void updateDB(View view){
        DatabaseHelper.remove();

        APIKhachHang.layDSKhachHang();
        APITheLoai.layDSTheLoai();
        APIHangHoa.layDSHangHoa();
        APIDonHang.layDSDonHang();

    }


    public void layDSDonHang(View view){
        Intent intent = new Intent(this, DanhSachDonHangActivity.class);
        startActivity(intent);

    }

    public void layDSKhachHang(View view){
        Intent intent = new Intent(this, DanhSachKhachHangActivity.class);
        startActivity(intent);
        //finish();
    }

    public void layDSHangHoa(View view){
        Intent intent = new Intent(this, DanhSachHangHoaActivity.class);
        startActivity(intent);

    }
}
