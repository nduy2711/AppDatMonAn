package com.example.nhom10.View;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.nhom10.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Add_dish_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Add_dish_Fragment extends DialogFragment {

    private EditText dishNameEditText, dishPriceEditText;
    private Button addDishButton;
    private ImageView closeButton;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Add_dish_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment add_dish_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Add_dish_Fragment newInstance(String param1, String param2) {
        Add_dish_Fragment fragment = new Add_dish_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null) {
            // Thiết lập chiều rộng của dialog là match_parent để chiếm toàn bộ màn hình
            getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
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
        View view = inflater.inflate(R.layout.fragment_add_dish_, container, false);

        dishNameEditText = view.findViewById(R.id.dish_name);
        dishPriceEditText = view.findViewById(R.id.dish_price);
        addDishButton = view.findViewById(R.id.add_dish_button);
        closeButton = view.findViewById(R.id.close_button);

        // Close button click listener to dismiss the fragment
        closeButton.setOnClickListener(v -> dismiss());

        // Add dish button logic to insert into the database
        addDishButton.setOnClickListener(v -> {
            String dishName = dishNameEditText.getText().toString().trim();
            String dishPrice = dishPriceEditText.getText().toString().trim();

            if (!dishName.isEmpty() && !dishPrice.isEmpty()) {
                // Perform database insertion logic here
                // Example: Insert product to the ProductHandler or Database

                dismiss();  // Close the dialog after adding
            }
        });

        return view;
    }
}