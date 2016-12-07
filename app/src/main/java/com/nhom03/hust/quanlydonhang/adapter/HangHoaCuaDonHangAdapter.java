package com.nhom03.hust.quanlydonhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.HangHoaCuaDonHang;

import java.util.ArrayList;

/**
 * Created by longs on 06/12/2016.
 */

public class HangHoaCuaDonHangAdapter extends BaseAdapter {

    private ArrayList<HangHoaCuaDonHang> listHHDH;
    private LayoutInflater layoutInflater;
    private Context context;

    public HangHoaCuaDonHangAdapter(ArrayList<HangHoaCuaDonHang> listHHDH, Context context) {
        this.listHHDH = listHHDH;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listHHDH.size();
    }

    @Override
    public Object getItem(int i) {
        return listHHDH.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
            view = layoutInflater.inflate(R.layout.item_hang_hoa_cua_don_hang, null);

        HangHoaCuaDonHang hhdh = this.listHHDH.get(i);

        TextView textTenHH = (TextView) view.findViewById(R.id.item_hhcdh_ten_hang_hoa);
        TextView textDonGia = (TextView) view.findViewById(R.id.item_hhcdh_don_gia);
        TextView textSoLuong = (TextView) view.findViewById(R.id.item_hhcdh_so_luong);
        TextView textThanhTien = (TextView) view.findViewById(R.id.item_hhcdh_thanh_tien);

        textTenHH.setText(hhdh.getTen());
        textDonGia.setText(String.valueOf((long) hhdh.getDonGia()*10000));
        textSoLuong.setText(String.valueOf(hhdh.getSoLuong()));
        textThanhTien.setText(String.valueOf((long) hhdh.thanhTien()*10000));

        return view;
    }
}
