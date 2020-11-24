package com.example.dairbordzimbabwe.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairbordzimbabwe.R;
import com.example.dairbordzimbabwe.api.RetrofitClient;
import com.example.dairbordzimbabwe.models.DefaultResponse;
import com.example.dairbordzimbabwe.models.Supplier;
import com.example.dairbordzimbabwe.models.SupplierList;
import com.example.dairbordzimbabwe.storage.DairibordSharedPreferences;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveriesFragment extends Fragment {

    private RecyclerView rcvDeliveries;
    private Spinner spnSupplier;
    private TextInputEditText edtQuantity;
    private TextInputLayout quantity_layout;
    private List<Supplier> m_suppliers_list;
    private int supplier_id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deliveries, container, false );

        rcvDeliveries = view.findViewById( R.id.rcvDeliveries );

        spnSupplier = view.findViewById( R.id.spnSupplier );

        edtQuantity = view.findViewById( R.id.edtQuantity );

        quantity_layout = view.findViewById( R.id.quantity_layout );

        m_suppliers_list = new ArrayList<Supplier>();

        spnSupplier.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                supplier_id = m_suppliers_list.get( i ).getSupplier_id();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        view.findViewById( R.id.btnSave ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                add_delivery( view );

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        get_suppliers();

    }


    public void get_suppliers () {
        Call<SupplierList> get_suppliers_call = RetrofitClient.get_instance().get_api()
                .get_suppliers();

        get_suppliers_call.enqueue(new Callback<SupplierList>() {
            @Override
            public void onResponse(Call<SupplierList> call, Response<SupplierList> response) {

                m_suppliers_list = response.body().getSuppliers();

                ArrayList<String> suppliers;

                suppliers = new ArrayList<String>();

                for(int i = 0; i < m_suppliers_list.size(); i++ ) {
                    suppliers.add( m_suppliers_list.get( i ).getSupplier_name() );
                }

                spnSupplier.setAdapter( new ArrayAdapter<String>( getContext(),
                        R.layout.support_simple_spinner_dropdown_item, suppliers ) );

            }

            @Override
            public void onFailure(Call<SupplierList> call, Throwable t) {
                String error_message = "Could not connect to database: " + t.getMessage();
                Toast.makeText( getContext(), error_message, Toast.LENGTH_LONG ).show();
            }
        });

    }

    private boolean validate_quantity() {
        if ( edtQuantity.getText().toString().trim().isEmpty() ) {
            quantity_layout.setError( "Enter the quantity delivered by the supplier." );
            return false;
        } else {
            if ( Integer.parseInt( edtQuantity.getText().toString().trim() ) > 0 ) {
                quantity_layout.setError( null );
                quantity_layout.setErrorEnabled( false );
                return true;
            } else {
                quantity_layout.setError( "Quantity delivered must be greater than 0." );
                return false;
            }
        }

    }

    public void add_delivery( View view ) {
        if ( !validate_quantity() ) {
            return;
        }

        int quantity = Integer.parseInt( edtQuantity.getText().toString().trim() );

        int user = DairibordSharedPreferences.get_instance( getContext() ).get_user().getUser_id();

        Call<DefaultResponse> add_delivery_call = RetrofitClient.get_instance().get_api()
                .add_delivery( supplier_id, user, quantity );

        add_delivery_call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                DefaultResponse default_response = response.body();

                Toast.makeText( getContext(), default_response.getMessage(),
                        Toast.LENGTH_LONG ).show();

                if ( default_response.isSuccess() ) {
                    edtQuantity.setText( "" );

                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                String error_message = "Could not connect to database: " + t.getMessage();
                Toast.makeText( getContext(), error_message, Toast.LENGTH_LONG ).show();
            }
        });
    }

}
