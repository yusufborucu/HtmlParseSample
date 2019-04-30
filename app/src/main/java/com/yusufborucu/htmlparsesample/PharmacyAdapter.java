package com.yusufborucu.htmlparsesample;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PharmacyAdapter extends BaseAdapter {

    private Context context;
    private List<PharmacyModel> rowItems;

    public PharmacyAdapter(Context context, List<PharmacyModel> items) {
        this.context = context;
        this.rowItems = items;
    }

    private class ViewHolder {
        TextView txtName;
        TextView txtAddress;
        TextView txtPhone;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_pharmacy, null);
            holder = new ViewHolder();
            holder.txtName = convertView.findViewById(R.id.tvPharmacyName);
            holder.txtAddress = convertView.findViewById(R.id.tvPharmacyAddress);
            holder.txtPhone = convertView.findViewById(R.id.tvPharmacyPhone);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final PharmacyModel rowItem = (PharmacyModel) getItem(position);

        holder.txtName.setText(rowItem.getName());
        holder.txtAddress.setText(rowItem.getAddress());
        holder.txtPhone.setText(rowItem.getPhone());

        return convertView;
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }
}
