package com.noveo.internship.androidFriends.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.noveo.internship.androidFriends.R;

public class FriendsAndMeetingAdapter extends BaseAdapter {
    private String[] strings;
    private Context context;

    private static class ViewHolder {
        private TextView title;
        private TextView subtitle;
        private ImageView icon;
    }

    public FriendsAndMeetingAdapter(Context context, String[] strings) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.friends_item_layout, parent, false);

            final ViewHolder holder = new ViewHolder();

            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.subtitle = (TextView) convertView.findViewById(R.id.subtitle);
            holder.icon =  (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
        }

        final String item = getItem(position);
        final ViewHolder holder = (ViewHolder) convertView.getTag();

        holder.title.setText(item);
        holder.subtitle.setText("walk");

        return convertView;
    }
}