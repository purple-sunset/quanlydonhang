package com.nhom03.hust.quanlydonhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.KhachHang;

import java.util.ArrayList;

/**
 * Created by sakura on 01/12/2016.
 */

public class KhachHangAdapter extends BaseAdapter {

    private ArrayList<KhachHang> listKH;
    private LayoutInflater layoutInflater;
    private Context context;

    public KhachHangAdapter(Context context, ArrayList<KhachHang> listKh){
        this.context = context;
        this.listKH = listKh;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listKH.size();
    }

    @Override
    public Object getItem(int i) {
        return listKH.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
            view = layoutInflater.inflate(R.layout.item_khach_hang, null);

        KhachHang kh = this.listKH.get(i);


        TextView textTenKH = (TextView) view.findViewById(R.id.dskh_ten_kh);
        TextView textDiaChi = (TextView) view.findViewById(R.id.dskh_dia_chi);

        textDiaChi.setText(kh.getDiaChi() +", " + kh.getThanhPho());
        textTenKH.setText(kh.getTenKH());

        return view;
    }

}

