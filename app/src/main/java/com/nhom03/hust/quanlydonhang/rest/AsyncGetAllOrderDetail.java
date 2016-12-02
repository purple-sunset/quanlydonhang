package com.nhom03.hust.quanlydonhang.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.nhom03.hust.quanlydonhang.model.ChiTietDonHang;
import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.HangHoa;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Admin on 02/12/2016.
 */

public class AsyncGetAllOrderDetail extends AsyncTask<Integer, Void, Boolean> {
    @Override
    protected Boolean doInBackground(Integer... integers) {
        try {
            URL url = new URL("http://daotao.misa.com.vn/Services/OrderService.svc/rest/GetAllOrderDetail?orderId=" + integers[0]);
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

            parseJSON(integers[0], sb.toString());

            int code = httpURLConnection.getResponseCode();
            if(code == HttpURLConnection.HTTP_OK)
                return true;
            else
                return false;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void parseJSON(int id, String result) {
        try {
            JSONObject o = new JSONObject(result);
            JSONArray temp = o.getJSONArray("GetAllOrderDetailResult");
            for (int i = 0; i < temp.length(); i++) {
                JSONObject c = temp.getJSONObject(i);
                //HangHoa hh = DatabaseHelper.getInstance().layHangHoa(c.getInt("ProductID"));
                ChiTietDonHang ct = new ChiTietDonHang(c.getInt("OrderID"), " ", c.getInt("Quantity"), c.getLong("UnitPrice"), Float.parseFloat(c.getString("Discount")));
                DatabaseHelper.getInstance().themChiTietDonHang(id, ct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onPostExecute(Boolean result){
        Log.d("AsyncGetAllDetail ", " Finished");
    }
}
