package com.example.dairbordzimbabwe.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairbordzimbabwe.R;
import com.example.dairbordzimbabwe.api.RetrofitClient;
import com.example.dairbordzimbabwe.models.DefaultResponse;
import com.example.dairbordzimbabwe.utils.Validator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuppliersFragment extends Fragment {

    private TextInputLayout address_layout, phone_number_layout, supplier_name_layout;
    private TextInputEditText edtAddress, edtPhoneNumber, edtSupplierName;
    private RecyclerView rcvSuppliers;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_suppliers, container, false );

        address_layout = view.findViewById( R.id.address_layout );
        phone_number_layout = view.findViewById( R.id.phone_number_layout );
        supplier_name_layout = view.findViewById( R.id.supplier_name_layout );

        edtAddress = view.findViewById( R.id.edtAddress );
        edtPhoneNumber = view.findViewById( R.id.edtPhoneNumber );
        edtSupplierName = view.findViewById( R.id.edtSupplierName );

        rcvSuppliers = view.findViewById( R.id.rcvSuppliers );

        view.findViewById( R.id.btnSave ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                add_supplier( view );

            }
        });

        return view;
    }

    private boolean validate_address () {
        String address = edtAddress.getText().toString().trim();

        if ( address.isEmpty() ) {
            address_layout.setError( "Enter the supplier's address." );
            return false;
        } else {
            address_layout.setError( null );
            address_layout.setErrorEnabled( false );
            return true;
        }

    }

    private boolean validate_phone_number () {
        String phone_number = edtPhoneNumber.getText().toString().trim();

        if ( phone_number.isEmpty() ) {
            phone_number_layout.setError( "Enter the supplier's phone number." );
            return false;
        } else if ( !Validator.validate_phone_number( phone_number ) ) {
            phone_number_layout.setError( "Supplier's phone number is not valid." );
            return false;
        } else {
            phone_number_layout.setError( null );
            phone_number_layout.setErrorEnabled( false );
            return true;
        }

    }

    private boolean validate_supplier_name () {
        String supplier_name = edtSupplierName.getText().toString().trim();

        if ( supplier_name.isEmpty() ) {
            supplier_name_layout.setError( "Enter the supplier's name." );
            return false;
        } else {
            supplier_name_layout.setError( null );
            supplier_name_layout.setErrorEnabled( false );
            return true;
        }

    }

    public void add_supplier( View view ) {

        if ( !validate_address() | !validate_phone_number() | !validate_supplier_name() ) {
            return;
        }

        String address = edtAddress.getText().toString().trim();
        String phone_number = edtPhoneNumber.getText().toString().trim();
        String supplier_name = edtSupplierName.getText().toString().trim();

        Call<DefaultResponse> add_supplier_call = RetrofitClient.get_instance().get_api()
                .add_supplier( supplier_name, address, phone_number );

        add_supplier_call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                DefaultResponse default_response = response.body();

                if ( default_response.isSuccess() ) {
                    clear_fields();
                }

                Toast.makeText( getContext(), default_response.getMessage(),
                        Toast.LENGTH_LONG ).show();
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                String error_message = "Could not connect to database: " + t.getMessage();
                Toast.makeText( getContext(), error_message, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void clear_fields() {
        edtAddress.setText( "" );
        edtPhoneNumber.setText( "" );
        edtSupplierName.setText( "" );
    }

}
