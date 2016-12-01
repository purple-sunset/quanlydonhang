package com.nhom03.hust.quanlydonhang.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nhom03.hust.quanlydonhang.R;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class DangNhapActivity extends AppCompatActivity {

    private static final String WSDL_TARGET_NAMESPACE = "http://daotao.misa.com.vn/AuthenticationService.svc?wsdl";
    private static final String SOAPLOGINMETHOD = "Login";
    private static final String SOAP_ADDRESS = "http://daotao.misa.com.vn/authenticationservice.svc";
    private static final String SOAPLOGINACTION = "Login";

    private EditText textTen;
    private EditText textMatKhau;
    private Button btnDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_nhap);

        //Tạo các thành phần
        textTen = (EditText) findViewById(R.id.username);
        textMatKhau = (EditText) findViewById(R.id.password);
        btnDangNhap = (Button) findViewById(R.id.login);

    }

    public void logIn(View view){

        String userName = textTen.getText().toString();
        String passWord = textMatKhau.getText().toString();
        int i = loginCheck(userName, passWord);
        if(i>0)
            Log.d("Login", "Login success");
    }

    public static int loginCheck(String userName, String passWord){
        int id= 0;

        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,SOAPLOGINMETHOD);

        request.addProperty("username",userName);
        request.addProperty("password",passWord);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);


        try{
            httpTransport.call(SOAPLOGINACTION, envelope);
            SoapObject response=(SoapObject) envelope.bodyIn;
            Log.d("parameter", response.getProperty(0).toString());
            id=Integer.parseInt(response.getProperty(0).toString());
        }
        catch (Exception exception){

            Log.d("Login Service Exception",exception.toString());
            exception.printStackTrace();
        }


        return id;
    }
}
