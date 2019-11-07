package com.uy.uykylelabexerno5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AndroidAdapter extends ArrayAdapter<Android> {
    Context mContext;
    int mResource;

    public AndroidAdapter(@NonNull Context context, int resource, @NonNull List<Android> objects) {
        super(context, resource, objects);

        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int image = getItem(position).getLogo();
        String android = getItem(position).getAndroid();
        String version = getItem(position).getVersion();
        String level = getItem(position).getLevel();
        String date = getItem(position).getDate();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);
        TextView tvAndroid = convertView.findViewById(R.id.tvAndroid);
        TextView tvVersion = convertView.findViewById(R.id.tvVersion);
        TextView tvLevel = convertView.findViewById(R.id.tvLevel);
        TextView tvDate = convertView.findViewById(R.id.tvDate);
        ImageView ivLogo = convertView.findViewById(R.id.logo);
        tvAndroid.setText(android);
        tvVersion.setText(version);
        tvLevel.setText(level);
        tvDate.setText(date);
        ivLogo.setImageResource(image);
        return convertView;
    }
}