package com.nhom03.hust.quanlydonhang.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.nhom03.hust.quanlydonhang.model.ChiTietDonHang;
import com.nhom03.hust.quanlydonhang.model.DatabaseHelper;
import com.nhom03.hust.quanlydonhang.model.DonHang;
import com.nhom03.hust.quanlydonhang.model.HangHoa;
import com.nhom03.hust.quanlydonhang.model.KhachHang;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Admin on 02/12/2016.
 */

public class AsyncGetAllOrder extends AsyncTask<Void, Void, ArrayList<DonHang>> {
    @Override
    protected ArrayList<DonHang> doInBackground(Void... voids) {
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


            int code = httpURLConnection.getResponseCode();

            return parseJSON(sb.toString());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<DonHang> parseJSON(String result) {
        ArrayList<DonHang> dsDonHang = new ArrayList<DonHang>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a", Locale.US);
        try {
            JSONObject o = new JSONObject(result);
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
                DatabaseHelper.getInstance().themDonHang(dh);
                dsDonHang.add(dh);

            }
            return dsDonHang;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    protected void onPostExecute(ArrayList<DonHang> result){
        Log.d("AsyncGetAllOrder ", " Finished");
        for (DonHang dh:result
             ) {
            AsyncGetAllOrderDetail asyncT = new AsyncGetAllOrderDetail();
            asyncT.execute(dh.getId());
        }
    }
}
