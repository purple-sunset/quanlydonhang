package com.nhom03.hust.quanlydonhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.KhachHang;

import java.util.ArrayList;

/**
 * Created by sakura on 01/12/2016.
 */

public class KhachHangAdapter extends BaseAdapter implements Filterable {

    private ArrayList<KhachHang> listKH;
    private ArrayList<KhachHang> filteredListKH;
    private KhachHangFilter filter;
    private LayoutInflater layoutInflater;
    private Context context;

    public KhachHangAdapter(Context context, ArrayList<KhachHang> listKh) {
        this.context = context;
        this.listKH = listKh;
        this.filteredListKH = listKh;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return filteredListKH.size();
    }

    @Override
    public Object getItem(int i) {
        return filteredListKH.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final ViewHolder holder;
        final KhachHang kh = (KhachHang) getItem(i);

        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_khach_hang, null);
            holder = new ViewHolder();
            holder.textTenKH = (TextView) view.findViewById(R.id.dskh_ten_kh);
            holder.textDiaChi = (TextView) view.findViewById(R.id.dskh_dia_chi);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.textDiaChi.setText(kh.getDiaChi() + ", " + kh.getThanhPho());
        holder.textTenKH.setText(kh.getTenKH());
        return view;
    }

    static class ViewHolder {
        TextView textTenKH;
        TextView textDiaChi;
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new KhachHangFilter();
        }

        return filter;
    }

    private class KhachHangFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                ArrayList<KhachHang> tempList = new ArrayList<KhachHang>();

                // search content in friend list
                for (KhachHang kh : listKH) {
                    if ((kh.getTenKH() + "" + kh.getTenCT() + " " + kh.getTieuDe() + "  " + kh.getDiaChi() + " " + kh.getThanhPho()
                            + " " + kh.getVung() + " " + kh.getQuocGia() + " " + kh.getSdt()).toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(kh);
                    }
                }

                filterResults.count = tempList.size();
                filterResults.values = tempList;
            } else {
                filterResults.count = listKH.size();
                filterResults.values = listKH;
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
            filteredListKH = (ArrayList<KhachHang>) results.values;
            notifyDataSetChanged();
        }
    }

}