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
            view = layoutInflater.inflate(R.layout.item_danh_sach_don_hang, null);

        ChiTietDonHang ctdh = this.listCTDH.get(i);

        TextView textTenKH = (TextView) view.findViewById(R.id.ten_khach_hang);
        TextView textNgayDatHang = (TextView) view.findViewById(R.id.ngay_dat_hang);
        TextView textTongTienHang = (TextView) view.findViewById(R.id.tong_tien_hang);

        textTenKH.setText(dh.getKhachHang().getTenKH());
        textNgayDatHang.setText(dh.getNgayGio().toString());
        textTongTienHang.setText(String.valueOf(dh.getTongTien()*10000));
        return view;
    }
}
