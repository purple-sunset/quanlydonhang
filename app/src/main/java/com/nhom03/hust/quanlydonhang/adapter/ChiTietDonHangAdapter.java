package com.nhom03.hust.quanlydonhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.ChiTietDonHang;
import com.nhom03.hust.quanlydonhang.model.DonHang;

import java.util.ArrayList;

/**
 * Created by longs on 06/12/2016.
 */

public class ChiTietDonHangAdapter extends BaseAdapter {

    private ArrayList<ChiTietDonHang> listCTDH;
    private LayoutInflater layoutInflater;
    private Context context;

    @Override
    public int getCount() {
        return listCTDH.size();
    }

    @Override
    public Object getItem(int i) {
        return listCTDH.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
            view = layoutInflater.inflate(R.layout.item_hang_hoa_cua_don_hang, null);

        ChiTietDonHang ctdh = this.listCTDH.get(i);

        TextView textTenHH = (TextView) view.findViewById(R.id.item_hhcdh_ten_hang_hoa);
        TextView textDonGia = (TextView) view.findViewById(R.id.item_hhcdh_don_gia);
        TextView textSoLuong = (TextView) view.findViewById(R.id.item_hhcdh_so_luong);
        TextView textThanhTien = (TextView) view.findViewById(R.id.item_hhcdh_thanh_tien);

        textTenHH.setText(ctdh.getTen());
        textDonGia.setText(String.valueOf(ctdh.getDonGia()));
        textSoLuong.setText(String.valueOf(ctdh.getSoLuong()));
        textThanhTien.setText(String.valueOf(ctdh.thanhTien()));

        return view;
    }
}
