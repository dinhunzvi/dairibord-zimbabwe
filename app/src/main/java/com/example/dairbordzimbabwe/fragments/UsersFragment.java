package com.example.dairbordzimbabwe.fragments;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dairbordzimbabwe.R;
import com.example.dairbordzimbabwe.api.RetrofitClient;
import com.example.dairbordzimbabwe.models.DefaultResponse;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersFragment extends Fragment {

    private TextInputLayout first_name_layout, last_name_layout, username_layout, email_layout;
    private TextInputEditText edtFirstName, edtLastName, edtUsername, edtEmail;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false );

        email_layout = view.findViewById( R.id.email_layout );
        first_name_layout = view.findViewById( R.id.first_name_layout );
        last_name_layout = view.findViewById( R.id.last_name_layout );
        username_layout = view.findViewById( R.id.username_layout );

        edtEmail = view.findViewById( R.id.edtEmail );
        edtFirstName = view.findViewById( R.id.edtFirstName );
        edtLastName = view.findViewById( R.id.edtLastName );
        edtUsername = view.findViewById( R.id.edtUsername );

        view.findViewById( R.id.btnSave ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                save_user( view );

            }
        });

        return view;
    }

    private boolean validate_first_name () {
        String first_name = edtFirstName.getText().toString().trim();

        if ( first_name.isEmpty() ) {
            first_name_layout.setError( "Enter the user's first name." );
            return false;
        } else {
            first_name_layout.setError( null );
            first_name_layout.setErrorEnabled( false );
            return true;
        }
    }

    private boolean validate_email () {
        String email = edtEmail.getText().toString().trim();

        if ( email.isEmpty() ) {
            email_layout.setError( "Enter the user's email address." );
            return false;
        } else if ( !Patterns.EMAIL_ADDRESS.matcher( email ).matches() ) {
            email_layout.setError( "User's email address is not valid." );
            return false;
        }
        else {
            email_layout.setError( null );
            email_layout.setErrorEnabled( false );
            return true;
        }
    }

    private boolean validate_last_name () {
        String last_name = edtLastName.getText().toString().trim();

        if ( last_name.isEmpty() ) {
            last_name_layout.setError( "Enter the user's last name." );
            return false;
        } else {
            last_name_layout.setError( null );
            last_name_layout.setErrorEnabled( false );
            return true;
        }
    }

    private boolean validate_username () {
        String username = edtUsername.getText().toString().trim();

        if ( username.isEmpty() ) {
            username_layout.setError( "Enter the user's username." );
            return false;
        } else {
            username_layout.setError( null );
            username_layout.setErrorEnabled( false );
            return true;
        }
    }

    public void save_user( View viw ) {
        if ( !validate_first_name() | !validate_email() | !validate_last_name() |
                !validate_username() ) {
            return;
        }

        String username = edtUsername.getText().toString().trim();
        String last_name = edtLastName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String first_name = edtFirstName.getText().toString().trim();

        Call<DefaultResponse> save_user_call = RetrofitClient.get_instance().get_api()
                .add_user( email, username, first_name, last_name );

        save_user_call.enqueue(new Callback<DefaultResponse>() {
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
                String error_message = "Could not connect to database. " + t.getMessage();
                Toast.makeText( getContext(), error_message, Toast.LENGTH_LONG ).show();
            }
        });

    }

    private void clear_fields () {
        edtEmail.setText( "" );
        edtFirstName.setText( "" );
        edtLastName.setText( "" );
        edtUsername.setText( "" );
    }
}
