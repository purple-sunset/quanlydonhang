package com.nhom03.hust.quanlydonhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
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

public class TheLoaiAdapter extends BaseExpandableListAdapter {

    private ArrayList<TheLoai> listTL;
    private LayoutInflater layoutInflater;
    private Context context;

    public TheLoaiAdapter(Context context, ArrayList<TheLoai> listTL) {
        this.context = context;
        this.listTL = listTL;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return listTL.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listTL.get(groupPosition).getDsHangHoa().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listTL.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listTL.get(groupPosition).getDsHangHoa().get(childPosition);
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
    public View getGroupView(int groupPosition, boolean b, View view, ViewGroup viewGroup) {
        if(view == null)
            view = layoutInflater.inflate(R.layout.item_the_loai, viewGroup, false);
        TextView tenTheLoai = (TextView) view.findViewById(R.id.dshh_ten_the_loai);
        tenTheLoai.setText(listTL.get(groupPosition).getTen());

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        if (view == null)
            view = layoutInflater.inflate(R.layout.item_hang_hoa, viewGroup, false);

        HangHoa hangHoa = listTL.get(groupPosition).getDsHangHoa().get(childPosition);

        TextView tenHangHoa = (TextView) view.findViewById(R.id.dshh_ten_hang_hoa);
        TextView giaHangHoa = (TextView) view.findViewById(R.id.dshh_gia_hang_hoa);

        tenHangHoa.setText(hangHoa.getTen());
        giaHangHoa.setText(String.valueOf(hangHoa.getDonGia()) + " đ");

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
