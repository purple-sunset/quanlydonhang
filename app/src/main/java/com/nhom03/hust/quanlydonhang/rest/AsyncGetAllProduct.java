package com.nhom03.hust.quanlydonhang.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.HangHoa;
import com.nhom03.hust.quanlydonhang.model.TheLoai;
import com.nhom03.hust.quanlydonhang.view.MainActivity;

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

public class AsyncGetAllProduct extends AsyncTask<Void, Void, Boolean> {
    @Override
    protected Boolean doInBackground(Void... voids) {
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

            parseJSON(sb.toString());

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

    private void parseJSON(String result) {
        try {
            JSONObject o = new JSONObject(result);
            JSONArray temp = o.getJSONArray("GetAllProductResult");
            for (int i = 0; i < temp.length(); i++) {
                JSONObject c = temp.getJSONObject(i);
                HangHoa hh = new HangHoa(c.getInt("ProductID"), c.getString("ProductName"), c.getBoolean("Discontinued"), c.getLong("UnitPrice"), c.getInt("UnitsInStock"), c.getInt("UnitsOnOrder"), c.getString("QuantityPerUnit"), DatabaseHelper.getInstance().layTheLoai(c.getInt("CategoryID")));
                DatabaseHelper.getInstance().themHangHoa(hh);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(Boolean result){
        Log.d("AsyncGetAllProduct ", " Finished");
        AsyncGetAllCustomer asyncT3 = new AsyncGetAllCustomer();
        asyncT3.execute();
    }

}
