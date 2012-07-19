package com.noveo.internship.androidFriends.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.noveo.internship.androidFriends.R;

public class FlagsSettingAdapter extends BaseAdapter {
    private String[] strings;
    private Context context;

    private static class ViewHolder {
        private TextView title;
        private CheckBox checkBox;
    }

    public FlagsSettingAdapter(Context context, String[] strings) {
        this.strings = strings;
        this.context = context;
    }

    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public String getItem(int position) {
        return strings[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.flag_item_layout, parent, false);

            final ViewHolder holder = new ViewHolder();

            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.checkBox = (CheckBox)convertView.findViewById(R.id.checkbox);
            convertView.setTag(holder);
        }

        final String item = getItem(position);
        final ViewHolder holder = (ViewHolder) convertView.getTag();

        holder.title.setText(item);

        return convertView;
    }
}