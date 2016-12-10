package com.nhom03.hust.quanlydonhang.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.NguoiDung;
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

public class DangNhapActivity extends AppCompatActivity {

    private static final String NAMESPACE = "http://asp.net/ApplicationServices/v200";
    private static final String METHOD_NAME = "Login";
    private static final String SOAP_ACTION = "http://asp.net/ApplicationServices/v200/AuthenticationService/Login";
    private static final String URL = "http://daotao.misa.com.vn/AuthenticationService.svc?wsdl";

    private EditText textTen;
    private EditText textMatKhau;
    private Button btnDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_nhap);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //Tạo các thành phần
        textTen = (EditText) findViewById(R.id.ten_nguoi_dung);
        textMatKhau = (EditText) findViewById(R.id.mat_khau);
        btnDangNhap = (Button) findViewById(R.id.login);

    }

    public void logIn(View view){
        String userName = textTen.getText().toString();
        String passWord = textMatKhau.getText().toString();
        boolean result = false;
        NguoiDung nd = new NguoiDung(userName, passWord);

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        request.addProperty("username", nd.getTen());
        request.addProperty("password", nd.getMatKhau());
        request.addProperty("customCredential"," ");
        request.addProperty("isPersistent","true");

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        System.out.println(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

            Log.i("myApp", response.toString());
            System.out.println("response " + response);

            if(response.toString().equalsIgnoreCase("true"))
            {
                Log.d("Login", "Success");
                result = true;
                /*APIKhachHang.layDSKhachHang();
                APITheLoai.layDSTheLoai();
                APIHangHoa.layDSHangHoa();
                APIDonHang.layDSDonHang();*/
                Intent dangNhap = new Intent(this, MainActivity.class);
                dangNhap.putExtra("NGUOI_DUNG", nd);
                startActivity(dangNhap);
                finish();
            }
            else {
                Log.d("Login", "Fail");
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog);
                TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
                TextView textView = (TextView) dialog.findViewById(R.id.dialog_text);
                Button btnOk = (Button) dialog.findViewById(R.id.dialog_ok);
                Button btnCancel = (Button) dialog.findViewById(R.id.dialog_cancel);
                btnCancel.setVisibility(View.INVISIBLE);
                btnOk.setText("OK");

                title.setText("Lỗi");
                textView.setText("Không thể đăng nhập!");
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }



        } catch(Exception ex)
        {
            Log.e("Error : " , "Error on soapPrimitiveData() " + ex.getMessage());
            ex.printStackTrace();
            Log.d("Login", "Fail");
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog);
            TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
            TextView textView = (TextView) dialog.findViewById(R.id.dialog_text);
            Button btnOk = (Button) dialog.findViewById(R.id.dialog_ok);
            Button btnCancel = (Button) dialog.findViewById(R.id.dialog_cancel);
            btnCancel.setVisibility(View.INVISIBLE);
            btnOk.setText("OK");

            title.setText("Lỗi");
            textView.setText("Không có kết nối Internet!");
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        }


    }

}
