package com.nhom03.hust.quanlydonhang.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.DonHang;
import com.nhom03.hust.quanlydonhang.model.HangHoa;
import com.nhom03.hust.quanlydonhang.model.KhachHang;
import com.nhom03.hust.quanlydonhang.model.NguoiDung;
import com.nhom03.hust.quanlydonhang.model.TheLoai;
import com.nhom03.hust.quanlydonhang.rest.AsyncDeleteCustomer;
import com.nhom03.hust.quanlydonhang.rest.AsyncGetAllCategory;
import com.nhom03.hust.quanlydonhang.rest.AsyncGetAllCustomer;
import com.nhom03.hust.quanlydonhang.rest.AsyncGetAllOrder;
import com.nhom03.hust.quanlydonhang.rest.AsyncGetAllOrderDetail;
import com.nhom03.hust.quanlydonhang.rest.AsyncGetAllProduct;
import com.nhom03.hust.quanlydonhang.rest.GetAll;

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
    public static String COOKIE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtUserName = (TextView) findViewById(R.id.txt_user);
        Intent dangNhap = getIntent();
        nguoiDung = (NguoiDung) dangNhap.getExtras().getSerializable("NGUOI_DUNG");
        txtUserName.setText(nguoiDung.getTen());
        COOKIE = nguoiDung.getCookie();



    }

    public void testLogin(View view){
        if(checkLogin()){
            AsyncDeleteCustomer asyncT = new AsyncDeleteCustomer();
            asyncT.execute();
        }

    }

    public boolean checkLogin(){
        final String NAMESPACE = "http://asp.net/ApplicationServices/v200";
        final String METHOD_NAME = "IsLoggedIn";
        final String SOAP_ACTION = "http://asp.net/ApplicationServices/v200/AuthenticationService/IsLoggedIn";
        final String URL = "http://daotao.misa.com.vn/AuthenticationService.svc?wsdl";
        boolean result = false;
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        System.out.println(request);
        List<HeaderProperty> headerIn = new ArrayList<HeaderProperty>();
        HeaderProperty hp = new HeaderProperty("Cookie", nguoiDung.getCookie());
        headerIn.add(hp);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call(SOAP_ACTION, envelope, headerIn);
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
        {
            Log.d("Login", "Success");
        }

        else
            Log.d("Login", "Fail");
        return result;
    }

    public void layDSDonHang(View view){
        try {
            AsyncGetAllCategory asyncT1 = new AsyncGetAllCategory();
            asyncT1.execute();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void layDSKhachHang(View view){
        try {

            AsyncGetAllCustomer asyncT = new AsyncGetAllCustomer();
            asyncT.execute();
            Log.d("Result", "a");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<KhachHang> ds = DatabaseHelper.getInstance().layDSKhachHang();
        Log.d("Khach Hang", ds.get(0).getTenKH());
    }

    public void layDSHangHoa(View view){
        try {
            AsyncGetAllCategory asyncT = new AsyncGetAllCategory();
            asyncT.execute();

            AsyncGetAllProduct asyncT2 = new AsyncGetAllProduct();
            asyncT2.execute();


        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<HangHoa> ds = DatabaseHelper.getInstance().layDSHangHoa();
        Log.d("Hang Hoa", ds.get(0).getTen());
    }
}
