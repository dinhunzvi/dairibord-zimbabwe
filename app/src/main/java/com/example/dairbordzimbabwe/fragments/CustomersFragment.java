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

public class CustomersFragment extends Fragment {

    private RecyclerView rcvCustomer;
    private TextInputEditText edtAddress, edtContactPerson, edtCustomerName, edtPhoneNumber;
    private TextInputLayout customer_name_layout, contact_person_layout, address_layout,
            phone_number_layout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customers, container, false );

        rcvCustomer = view.findViewById( R.id.rcvCustomers );

        edtAddress = view.findViewById( R.id.edtAddress );
        edtContactPerson = view.findViewById( R.id.edtContactPerson );
        edtCustomerName = view.findViewById( R.id.edtCustomerName );
        edtPhoneNumber = view.findViewById( R.id.edtPhoneNumber );

        address_layout = view.findViewById( R.id.address_layout );
        contact_person_layout = view.findViewById( R.id.contact_person_layout );
        customer_name_layout = view.findViewById( R.id.customer_name_layout );
        phone_number_layout = view.findViewById( R.id.phone_number_layout );

        view.findViewById( R.id.btnSave ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                add_customer( view );

            }
        });

        return view;

    }

    private boolean validate_address () {
        String address = edtAddress.getText().toString().trim();

        if ( address.isEmpty() ) {
            address_layout.setError( "Enter the customer's address." );
            return false;
        } else {
            address_layout.setError( null );
            address_layout.setErrorEnabled( false );
            return true;
        }

    }

    private boolean validate_customer_name () {
        String customer_name = edtCustomerName.getText().toString().trim();

        if ( customer_name.isEmpty() ) {
            customer_name_layout.setError( "Enter the customer's name." );
            return false;
        } else {
            customer_name_layout.setError( null );
            customer_name_layout.setErrorEnabled( false );
            return true;
        }

    }

    private boolean validate_contact_person () {
        String contact_person = edtContactPerson.getText().toString().trim();

        if ( contact_person.isEmpty() ) {
            contact_person_layout.setError( "Enter the customer's contact person." );
            return false;
        } else {
            contact_person_layout.setError( null );
            contact_person_layout.setErrorEnabled( false );
            return true;
        }

    }

    private boolean validate_phone_number () {
        String phone_number = edtPhoneNumber.getText().toString().trim();

        if ( phone_number.isEmpty() ) {
            phone_number_layout.setError( "Enter the customer's phone number." );
            return false;
        } else if ( !Validator.validate_phone_number( phone_number ) ) {
            phone_number_layout.setError( "Customer's phone number is not valid." );
            return false;
        } else {
            phone_number_layout.setError( null );
            phone_number_layout.setErrorEnabled( false );
            return true;
        }

    }

    public void add_customer ( View view ) {

        if ( !validate_address() | !validate_contact_person() | !validate_customer_name() |
                !validate_phone_number() ) {
            return;
        }

        String address = edtAddress.getText().toString().trim();
        String contact_person = edtContactPerson.getText().toString().trim();
        String customer_name = edtCustomerName.getText().toString().trim();
        String phone_number = edtPhoneNumber.getText().toString().trim();

        Call<DefaultResponse> add_customer_call = RetrofitClient.get_instance().get_api()
                .add_customer(customer_name, address, contact_person, phone_number );

        add_customer_call.enqueue(new Callback<DefaultResponse>() {
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
                Toast.makeText( getContext(), error_message, Toast.LENGTH_LONG ).show();
            }
        });

    }

    private void clear_fields() {
        edtAddress.setText( "" );
        edtContactPerson.setText( "" );
        edtCustomerName.setText( "" );
        edtPhoneNumber.setText( "" );
    }

}
