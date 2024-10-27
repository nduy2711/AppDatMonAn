package com.example.nhom10.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhom10.Model.Product;
import com.example.nhom10.R;

import java.util.List;

public class ProductAdapter  extends BaseAdapter {
    private Context context;
    private List<Product> productList;
    private LayoutInflater inflater;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_gridview, parent, false);
        }

        // Lấy dữ liệu từ danh sách sản phẩm
        Product product = productList.get(position);

        // Liên kết các thành phần trong item_gridview.xml
        ImageView productImage = convertView.findViewById(R.id.item_imageView);
        TextView productName = convertView.findViewById(R.id.item_name);
//        TextView productPrice = convertView.findViewById(R.id.item_price);

        // Gán dữ liệu cho các thành phần
        productImage.setImageResource(product.getImage());
        productName.setText(product.getName());
//        productPrice.setText(String.format("%.2f VND", product.getPrice()));

        return convertView;
    }



}
