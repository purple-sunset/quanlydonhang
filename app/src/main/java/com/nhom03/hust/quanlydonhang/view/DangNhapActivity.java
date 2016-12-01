package com.nhom03.hust.quanlydonhang.view;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.NguoiDung;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;

import java.net.SocketException;

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
        textTen = (EditText) findViewById(R.id.username);
        textMatKhau = (EditText) findViewById(R.id.password);
        btnDangNhap = (Button) findViewById(R.id.login);

    }

    public void logIn(View view){
        String userName = textTen.getText().toString();
        String passWord = textMatKhau.getText().toString();
        boolean result = false;


        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        request.addProperty("username", "demo");
        request.addProperty("password","12345678");
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
                result = true;
            }


        } catch(SocketException ex)
        {
            Log.e("Error : " , "Error on soapPrimitiveData() " + ex.getMessage());
            ex.printStackTrace();
        }
        catch (Exception e) {
            Log.e("Error : " , "Error on soapPrimitiveData() " + e.getMessage());
            e.printStackTrace();
        }

        if(result)
            Log.d("Login", "Success");
        else
            Log.d("Login", "Fail");

    }

}
