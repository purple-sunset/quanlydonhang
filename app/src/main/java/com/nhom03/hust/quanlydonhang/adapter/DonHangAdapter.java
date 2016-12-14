package com.nhom03.hust.quanlydonhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.DonHang;
import com.nhom03.hust.quanlydonhang.model.KhachHang;

import java.util.ArrayList;

/**
 * Created by longs on 06/12/2016.
 */

public class DonHangAdapter extends BaseAdapter implements Filterable{
    private ArrayList<DonHang> listDH;
    private ArrayList<DonHang> filteredListDH;
    private DonHangFilter filter;
    private LayoutInflater layoutInflater;
    private Context context;

    public DonHangAdapter(Context context, ArrayList<DonHang> listDH){
        this.context = context;
        this.listDH = listDH;
        this.filteredListDH = listDH;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return filteredListDH.size();
    }

    @Override
    public Object getItem(int i) {
        return filteredListDH.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        final DonHang dh = (DonHang) getItem(i);

        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_don_hang, null);
            holder = new ViewHolder();
            holder.textTenKH = (TextView) view.findViewById(R.id.ten_khach_hang);
            holder.textNgayDatHang = (TextView) view.findViewById(R.id.ngay_dat_hang);
            holder.textTongTienHang = (TextView) view.findViewById(R.id.tong_tien_hang);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        holder.textTenKH.setText(dh.getKhachHang().getTenKH());
        holder.textNgayDatHang.setText(dh.getNgayGio().toLocaleString());
        holder.textTongTienHang.setText(String.valueOf((long) dh.getTongTien()*10000));
        return view;
    }

    static class ViewHolder {
        TextView textTenKH;
        TextView textNgayDatHang;
        TextView textTongTienHang;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    private class DonHangFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                ArrayList<DonHang> tempList = new ArrayList<DonHang>();

                // search content in friend list
                for (DonHang dh : listDH) {
                    if ((dh.getKhachHang().getTenKH() + " " + dh.getDiaChiGiaoHang() + " " + dh.getTongTien()).toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(dh);
                    }
                }

                filterResults.count = tempList.size();
                filterResults.values = tempList;
            } else {
                filterResults.count = listDH.size();
                filterResults.values = listDH;
            }

            return filterResults;
        }

        /**
         * Notify about filtered list to ui
         *
         * @param constraint text
         * @param results    filtered result
         */
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredListDH = (ArrayList<DonHang>) results.values;
            notifyDataSetChanged();
        }
    }
}
