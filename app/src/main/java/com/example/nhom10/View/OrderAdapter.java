package com.example.nhom10.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nhom10.Model.Product;
import com.example.nhom10.R;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<Product> {
    private Context context;
    private List<Product> selectedProducts;

    public OrderAdapter(Context context, List<Product> selectedProducts) {
        super(context, 0, selectedProducts);
        this.context = context;
        this.selectedProducts = selectedProducts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Product product = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_list_item, parent, false);
        }

        // Lookup views for data population
        TextView productName = convertView.findViewById(R.id.product_name);
        TextView productQuantity = convertView.findViewById(R.id.product_quantity);

        // Populate the data into the views
        productName.setText(product.getName());
        productQuantity.setText("x1"); // Set quantity as needed, e.g., from a list if you have varying quantities

        // Return the completed view to render on screen
        return convertView;
    }
}
