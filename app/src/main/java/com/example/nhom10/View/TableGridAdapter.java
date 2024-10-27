package com.example.nhom10.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.nhom10.R;

public class TableGridAdapter extends BaseAdapter {
    private Context context;
    private int[] images;

    public TableGridAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int i) {
        return images[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.table_gridview_item, null);
        ImageView imageView = (ImageView)view.findViewById(R.id.table_imageView);
        imageView.setImageResource(images[i]);

        return imageView;
    }
}
