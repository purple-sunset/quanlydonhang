package com.nhom03.hust.quanlydonhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import com.nhom03.hust.quanlydonhang.R;
import com.nhom03.hust.quanlydonhang.model.HangHoa;
import com.nhom03.hust.quanlydonhang.model.KhachHang;
import com.nhom03.hust.quanlydonhang.model.TheLoai;

import java.util.ArrayList;

/**
 * Created by Admin on 06/12/2016.
 */

public class TheLoaiAdapter extends BaseExpandableListAdapter implements Filterable {

    private ArrayList<TheLoai> listTL;
    private ArrayList<TheLoai> filteredListTL;
    private HangHoaFilter filter;

    private LayoutInflater layoutInflater;
    private Context context;

    public TheLoaiAdapter(Context context, ArrayList<TheLoai> listTL) {
        this.context = context;
        this.listTL = listTL;
        this.filteredListTL = listTL;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return filteredListTL.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return filteredListTL.get(groupPosition).getDsHangHoa().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return filteredListTL.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return filteredListTL.get(groupPosition).getDsHangHoa().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup viewGroup) {
        final TheLoaiViewHolder holder;
        final TheLoai tl = (TheLoai) getGroup(groupPosition);

        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_the_loai, viewGroup, false);
            holder = new TheLoaiViewHolder();
            holder.tenTheLoai = (TextView) view.findViewById(R.id.dshh_ten_the_loai);
            holder.imgExpansed = (ImageView) view.findViewById(R.id.dshh_expansed);
            view.setTag(holder);
        }
        else {
            holder = (TheLoaiViewHolder) view.getTag();
        }


        if (isExpanded)
            holder.imgExpansed.setImageResource(R.drawable.ic_expand_more_black);
        else
            holder.imgExpansed.setImageResource(R.drawable.ic_expand_less_black);

        holder.tenTheLoai.setText(listTL.get(groupPosition).getTen());

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        final HangHoaViewHolder holder;
        final HangHoa hangHoa = (HangHoa) getChild(groupPosition, childPosition);
        final String[] trangThaiArray = view.getResources().getStringArray(R.array.trang_thai);

        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_hang_hoa, viewGroup, false);
            holder = new HangHoaViewHolder();
            holder.tenHangHoa = (TextView) view.findViewById(R.id.dshh_ten_hang_hoa);
            holder.trangThai = (TextView) view.findViewById(R.id.dshh_trang_thai);
            holder.giaHangHoa = (TextView) view.findViewById(R.id.dshh_gia_hang_hoa);
            view.setTag(holder);
        }
        else {
            holder = (HangHoaViewHolder) view.getTag();
        }

        holder.tenHangHoa.setText(hangHoa.getTen());
        holder.giaHangHoa.setText(String.valueOf((long) hangHoa.getDonGia() * 10000));


        if (hangHoa.isHuyBan())
            holder.trangThai.setText(trangThaiArray[1]);
        else
            holder.trangThai.setText(trangThaiArray[0]);

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class TheLoaiViewHolder {
        TextView tenTheLoai;
        ImageView imgExpansed;
    }


    static class HangHoaViewHolder {
        TextView tenHangHoa;
        TextView trangThai;
        TextView giaHangHoa;
    }


    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new HangHoaFilter();
        }

        return filter;
    }

    private class HangHoaFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                ArrayList<TheLoai> tempList = new ArrayList<TheLoai>();

                // search content in friend list
                for (TheLoai tl : listTL) {
                    ArrayList<HangHoa> tempHH = new ArrayList<>();
                    for (HangHoa hh : tl.getDsHangHoa())
                        if ((hh.getTen() + " " + hh.getChiTiet() + " " + hh.getDonGia()).toLowerCase().contains(constraint.toString().toLowerCase())) {
                            tempHH.add(hh);
                        }
                    if (tempHH.size() > 0) {
                        tl.setDsHangHoa(tempHH);
                        tempList.add(tl);
                    }
                }

                filterResults.count = tempList.size();
                filterResults.values = tempList;
            } else {
                filterResults.count = listTL.size();
                filterResults.values = listTL;
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
            filteredListTL = (ArrayList<TheLoai>) results.values;
            notifyDataSetChanged();
        }
    }
}
