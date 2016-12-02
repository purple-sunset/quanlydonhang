package com.nhom03.hust.quanlydonhang.rest;

import com.nhom03.hust.quanlydonhang.model.ChiTietDonHang;
import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.DonHang;
import com.nhom03.hust.quanlydonhang.model.HangHoa;
import com.nhom03.hust.quanlydonhang.model.KhachHang;
import com.nhom03.hust.quanlydonhang.model.TheLoai;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Admin on 03/12/2016.
 */

public class GetAll implements Runnable {
    @Override
    public void run() {
        GetAllCategory();
        GetAllProduct();
        GetAllCustomer();
        GetAllOrder();
    }

    protected void GetAllCategory(){
        try {
            URL url = new URL("http://daotao.misa.com.vn/services/OrderService.svc/rest/GetAllCategory");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            int code = httpURLConnection.getResponseCode();
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    httpURLConnection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            JSONObject o = new JSONObject(sb.toString());
            JSONArray temp = o.getJSONArray("GetAllCategoryResult");
            for (int i = 0; i < temp.length(); i++) {
                JSONObject c = temp.getJSONObject(i);
                TheLoai tl = new TheLoai(c.getInt("CategoryID"), c.getString("CategoryName"), c.getString("Description"));
                DatabaseHelper.getInstance().themTheLoai(tl);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    protected void GetAllProduct(){
        try {
            URL url = new URL("http://daotao.misa.com.vn/Services/OrderService.svc/rest/GetAllProduct");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    httpURLConnection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            JSONObject o = new JSONObject(sb.toString());
            JSONArray temp = o.getJSONArray("GetAllProductResult");
            for (int i = 0; i < temp.length(); i++) {
                JSONObject c = temp.getJSONObject(i);
                HangHoa hh = new HangHoa(c.getInt("ProductID"), c.getString("ProductName"), c.getBoolean("Discontinued"), c.getLong("UnitPrice"), c.getInt("UnitsInStock"), c.getInt("UnitsOnOrder"), c.getString("QuantityPerUnit"), DatabaseHelper.getInstance().layTheLoai(c.getInt("CategoryID")));
                DatabaseHelper.getInstance().themHangHoa(hh);

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void GetAllCustomer(){
        try {
            URL url = new URL("http://daotao.misa.com.vn/services/CustomerService.svc/rest/GetAllCustomer");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    httpURLConnection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }


            JSONObject o = new JSONObject(sb.toString());
            JSONArray temp = o.getJSONArray("GetAllCustomerResult");
            for (int i = 0; i < temp.length(); i++) {
                JSONObject c = temp.getJSONObject(i);
                KhachHang kh = new KhachHang(c.getString("CustomerID"), c.getString("ContactName")
                        , c.getString("CompanyName"), c.getString("ContactTitle"), c.getString("Address")
                        , c.getString("City"), c.getString("Region"), c.getString("Country"), c.getString("Phone")
                        , c.getString("Fax"), c.getString("PostalCode"));
                DatabaseHelper.getInstance().themKhachHang(kh);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void GetAllOrder(){
        try {
            URL url = new URL("http://daotao.misa.com.vn/services/OrderService.svc/rest/GetAllOrder");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    httpURLConnection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a", Locale.US);
            JSONObject o = new JSONObject(sb.toString());
            JSONArray temp = o.getJSONArray("GetAllOrderResult");
            for (int i = 0; i < temp.length(); i++) {
                JSONObject c = temp.getJSONObject(i);

                Date ngayGio = dateFormat.parse(c.getString("ISOOrderDate"));

                Long l = 0L;
                if (c.getString("Freight") != "null")
                    l = c.getLong("Freight");

                String idKhach = "RICAR";
                if (c.getString("CustomerID") != "null")
                    idKhach = c.getString("CustomerID");

                DonHang dh = new DonHang(c.getInt("OrderID"), ngayGio, l, c.getString("ShipName"), DatabaseHelper.getInstance().layKhachHang(idKhach), null);
                GetAllOrderDetail(dh.getId());
                DatabaseHelper.getInstance().themDonHang(dh);


            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void GetAllOrderDetail(int id){
        try {
            URL url = new URL("http://daotao.misa.com.vn/Services/OrderService.svc/rest/GetAllOrderDetail?orderId=" + id);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    httpURLConnection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }


            JSONObject o = new JSONObject(sb.toString());
            JSONArray temp = o.getJSONArray("GetAllOrderDetailResult");
            for (int i = 0; i < temp.length(); i++) {
                JSONObject c = temp.getJSONObject(i);
                //HangHoa hh = DatabaseHelper.getInstance().layHangHoa(c.getInt("ProductID"));
                ChiTietDonHang ct = new ChiTietDonHang(c.getInt("OrderID"), " ", c.getInt("Quantity"), c.getLong("UnitPrice"), Float.parseFloat(c.getString("Discount")));
                DatabaseHelper.getInstance().themChiTietDonHang(id, ct);

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
