package com.example.dairbordzimbabwe.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairbordzimbabwe.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ProductsFragment extends Fragment {

    private RecyclerView rcvProducts;
    private TextInputEditText edtMilkRequired, edtProductName, edtStockQuantity;
    private TextInputLayout milk_required_layout, product_name_layout, stock_quantity_layout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false );

        edtMilkRequired = view.findViewById( R.id.edtMilkRequired );
        edtProductName = view.findViewById( R.id.edtProductName );
        edtStockQuantity = view.findViewById( R.id.edtStockQuantity );

        milk_required_layout = view.findViewById( R.id.milk_required_layout );
        product_name_layout = view.findViewById( R.id.product_name_layout );
        stock_quantity_layout = view.findViewById( R.id.stock_quantity_layout );

        rcvProducts = view.findViewById( R.id.rcvProducts );

        return view;
    }

    private void get_products() {

    }
}
