package com.nhom03.hust.quanlydonhang.rest;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.TheLoai;
import com.nhom03.hust.quanlydonhang.view.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Admin on 02/12/2016.
 */

public class AsyncGetAllCategory extends AsyncTask<Void, Void, Boolean > {

    @Override
    protected Boolean doInBackground(Void... voids) {
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

            parseJSON(sb.toString());

            if(code == HttpURLConnection.HTTP_OK)
                return true;
            else
                return false;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    private void parseJSON(String result) {
        try {
            JSONObject o = new JSONObject(result);
            JSONArray temp = o.getJSONArray("GetAllCategoryResult");
            for (int i = 0; i < temp.length(); i++) {
                JSONObject c = temp.getJSONObject(i);
                TheLoai tl = new TheLoai(c.getInt("CategoryID"), c.getString("CategoryName"), c.getString("Description"));
                DatabaseHelper.getInstance().themTheLoai(tl);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onPostExecute(Boolean result){
        Log.d("AsyncGetAllCategory ", " Finished");
        AsyncGetAllProduct asyncT2 = new AsyncGetAllProduct();
        asyncT2.execute();
    }

}