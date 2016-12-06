package com.nhom03.hust.quanlydonhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.HangHoa;

import java.util.List;

/**
 * Created by sakura on 06/12/2016.
 */

public class HangHoaCuaDonHangAdapter extends BaseAdapter {
    private List<HangHoa> hangHoaList;
    private LayoutInflater layoutInflater;
    private Context context;

    public HangHoaCuaDonHangAdapter(Context context, List<HangHoa> hangHoaList) {
        this.context = context;
        this.hangHoaList = hangHoaList;
        this.layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return hangHoaList.size();
    }

    @Override
    public Object getItem(int i) {
        return hangHoaList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_hang_hoa_cua_don_hang, null);
        }
        HangHoa hangHoa = hangHoaList.get(i);
        TextView tenHangHoa = (TextView)view.findViewById(R.id.item_hhcdh_ten_hang_hoa);
        TextView dongGia = (TextView) view.findViewById(R.id.item_hhcdh_don_gia);
        TextView soLuong = (TextView) view.findViewById(R.id.item_hhcdh_so_luong);
        TextView thanhTien = (TextView) view.findViewById(R.id.item_hhcdh_thanh_tien);
        return view;
    }
}
