package com.example.dairbordzimbabwe.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairbordzimbabwe.R;
import com.example.dairbordzimbabwe.adapters.ProductsAdapter;
import com.example.dairbordzimbabwe.api.RetrofitClient;
import com.example.dairbordzimbabwe.models.DefaultResponse;
import com.example.dairbordzimbabwe.models.Product;
import com.example.dairbordzimbabwe.models.ProductList;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsFragment extends Fragment {

    private RecyclerView rcvProducts;
    private TextInputEditText edtMilkRequired, edtProductName, edtStockQuantity;
    private TextInputLayout milk_required_layout, product_name_layout, stock_quantity_layout;
    private List<Product> m_products_list;
    private ProductsAdapter products_adapter;

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
        rcvProducts.setLayoutManager( new LinearLayoutManager( getContext() ) );

        view.findViewById( R.id.btnSave ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                add_product( view );

            }
        });

        return view;
    }

    private void get_products() {
        Call<ProductList> get_products_call = RetrofitClient.get_instance().get_api().
                get_products();

        get_products_call.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {

                m_products_list = response.body().getProducts();

                products_adapter = new ProductsAdapter( m_products_list, getContext() );

                rcvProducts.setAdapter( products_adapter );

                products_adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                String error_message = "Could not connect to database: " + t.getMessage();
                Toast.makeText( getContext(), error_message, Toast.LENGTH_LONG ).show();
            }
        });
    }

    private boolean validate_product_name () {
        String product_name = edtProductName.getText().toString().trim();

        if ( product_name.isEmpty() ) {
            product_name_layout.setError( "Enter the product's name." );
            return false;
        } else {
            product_name_layout.setError( null );
            product_name_layout.setErrorEnabled( false );
            return true;
        }
    }

    private boolean validate_stock_quantity() {
        if ( edtStockQuantity.getText().toString().trim().isEmpty() ) {
            stock_quantity_layout.setError( "Enter the product's quantity in stock." );
            return false;
        } else {
            if ( Integer.parseInt( edtStockQuantity.getText().toString().trim() ) >= 0 ) {
                stock_quantity_layout.setError( null );
                stock_quantity_layout.setErrorEnabled( false );
                return true;
            } else {
                stock_quantity_layout.setError( "The product's quantity in stock must not be less than 0" );
                return false;
            }
        }
    }

    private boolean validate_milk_required() {
        if ( edtMilkRequired.getText().toString().trim().isEmpty() ) {
            milk_required_layout.setError( "Enter number of litres of milk required to make one unit of product." );
            return false;
        } else {
            if ( Double.parseDouble( edtMilkRequired.getText().toString().trim() ) > 0 ) {
                milk_required_layout.setError( null );
                milk_required_layout.setErrorEnabled( false );
                return true;
            } else {
                milk_required_layout.setError( "Number of liters required to make one unit must be greater than 0." );
                return false;
            }
        }

    }

    public void add_product ( View view ) {
        if ( !validate_product_name() | !validate_stock_quantity() | !validate_milk_required() ) {
            return;
        }

        int quantity_stock = Integer.parseInt( edtStockQuantity.getText().toString().trim() );
        String product_name = edtProductName.getText().toString().trim();
        double milk_required = Double.parseDouble( edtMilkRequired.getText().toString().trim() );

        Call<DefaultResponse> app_product_call = RetrofitClient.get_instance().get_api()
                .add_product( product_name, quantity_stock, milk_required );

        app_product_call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                DefaultResponse default_response = response.body();

                Toast.makeText( getContext(), default_response.getMessage(),
                        Toast.LENGTH_LONG ).show();

                if ( default_response.isSuccess() ) {
                    clear_fields();
                    get_products();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                String error_message = "Could not connect to database: " + t.getMessage();
                Toast.makeText( getContext(), error_message, Toast.LENGTH_LONG ).show();
            }

        });

    }

    private void clear_fields () {
        edtMilkRequired.setText( "" );
        edtProductName.setText( "" );
        edtStockQuantity.setText( "" );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        get_products();;

    }
}
