package com.nhom03.hust.quanlydonhang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.KhachHang;

import java.util.List;

/**
 * Created by sakura on 01/12/2016.
 */

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.KhachHangwHolder> {

    private List<KhachHang> khachHangList;
    private int rowLayout;
    private Context context;

    public KhachHangAdapter(List<KhachHang> khachHangList, int rowLayout, Context context) {
        this.khachHangList = khachHangList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public KhachHangwHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new KhachHangwHolder(view);
    }

    @Override
    public void onBindViewHolder(KhachHangwHolder holder, int position) {
        holder.name_customer.setText(khachHangList.get(position).getTenKH());
        holder.address_customer.setText(khachHangList.get(position).getDiaChi());
    }

    @Override
    public int getItemCount() {
        return khachHangList.size();
    }

    public static class KhachHangwHolder extends RecyclerView.ViewHolder {
        LinearLayout customerLayout;
        TextView name_customer;
        TextView address_customer;


        public KhachHangwHolder(View v) {
            super(v);
            //customerLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            name_customer = (TextView) v.findViewById(R.id.name_customer);
            address_customer = (TextView) v.findViewById(R.id.address_customer);

        }
    }
}
