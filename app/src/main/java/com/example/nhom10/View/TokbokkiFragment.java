package com.example.nhom10.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nhom10.Control.ProductHandler;
import com.example.nhom10.Model.Category;
import com.example.nhom10.Model.Product;
import com.example.nhom10.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TokbokkiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TokbokkiFragment extends Fragment {

    private GridView gridView;
    private ListView listView;
    private ProductAdapter productAdapter;
    private OrderAdapter orderAdapter;
    private ProductHandler productHandler;
    private TextView totalPriceTextView;
    private Button confirmButton;

    private ArrayList<Product> selectedProducts = new ArrayList<>();
    private int totalPrice = 0;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TokbokkiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TokbokkiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TokbokkiFragment newInstance(String param1, String param2) {
        TokbokkiFragment fragment = new TokbokkiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tokbokki, container, false);
        gridView = view.findViewById(R.id.tokbokki_gridview);
        listView = view.findViewById(R.id.item_listView);
        totalPriceTextView = view.findViewById(R.id.total_price);
        confirmButton = view.findViewById(R.id.confirm_button);

        // Initialize ProductHandler
        productHandler = new ProductHandler(getContext());

        // Get the products for category 1 (Thịt nướng)
        ArrayList<Product> meatProducts = productHandler.getProductsByCategory(2);

        // Set up the adapter for GridView
        productAdapter = new ProductAdapter(getContext(), meatProducts);
        gridView.setAdapter(productAdapter);

        // Set up the custom adapter for ListView
        orderAdapter = new OrderAdapter(getContext(), selectedProducts);
        listView.setAdapter(orderAdapter);

        // GridView item click listener
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product selectedProduct = meatProducts.get(i);
                addToOrder(selectedProduct);
            }
        });
        return view;
    }

    private void addToOrder(Product product) {
        // Add the selected product to the order list
        selectedProducts.add(product);

        // Update total price
        totalPrice += product.getPrice();

        // Refresh the order ListView and total price
        orderAdapter.notifyDataSetChanged();
        totalPriceTextView.setText("Tổng cộng: " + totalPrice + " VND");
    }

}