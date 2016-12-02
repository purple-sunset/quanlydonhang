package com.nhom03.hust.quanlydonhang.rest;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

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

public class AsyncGetAllCategory extends AsyncTask<Void, Void, ArrayList<TheLoai> > {

    @Override
    protected ArrayList<TheLoai>  doInBackground(Void... voids) {
        ArrayList<TheLoai> dsTheLoai = new ArrayList<>();
        try {
            URL url = new URL("http://daotao.misa.com.vn/services/OrderService.svc/rest/GetAllCategory");
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

            return parseJSON(sb.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private ArrayList<TheLoai> parseJSON(String result) {
        ArrayList<TheLoai> dsTheLoai = new ArrayList<>();
        try {
            JSONObject o = new JSONObject(result);
            JSONArray temp = o.getJSONArray("GetAllCategoryResult");
            for (int i = 0; i < temp.length(); i++) {
                JSONObject c = temp.getJSONObject(i);
                TheLoai tl = new TheLoai(c.getInt("CategoryID"), c.getString("CategoryName"), c.getString("Description"));
                DatabaseHelper.getInstance().themTheLoai(tl);
                dsTheLoai.add(tl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsTheLoai;
    }

}
