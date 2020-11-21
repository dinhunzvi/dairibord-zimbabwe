package com.example.dairbordzimbabwe.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.dairbordzimbabwe.R;
import com.example.dairbordzimbabwe.api.RetrofitClient;
import com.example.dairbordzimbabwe.models.LoginResponse;
import com.example.dairbordzimbabwe.storage.DairibordSharedPreferences;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private TextInputEditText edtUsername, edtPassword;
    private TextInputLayout username_layout, password_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtPassword = findViewById( R.id.edtPassword );
        edtUsername = findViewById( R.id.edtUsername );

        username_layout = findViewById( R.id.username_layout );
        password_layout =findViewById( R.id.password_layout );

        findViewById( R.id.btnSignIn ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sign_in( view );

            }
        });

    }

    private boolean validate_username() {
        String username = edtUsername.getText().toString().trim();

        if ( username.isEmpty() ) {
            username_layout.setError( "Enter your username." );
            return false;
        } else {
            username_layout.setError( null );
            username_layout.setErrorEnabled( false );
            return true;
        }
    }

    private boolean validate_password() {
        String password = edtPassword.getText().toString().trim();

        if ( password.isEmpty() ) {
            password_layout.setError( "Enter your password." );
            return false;
        } else {
            password_layout.setError( null );
            password_layout.setErrorEnabled( false );
            return true;
        }
    }

    private void sign_in( View view ) {

        if ( !validate_password() | !validate_username() ) {
            return;
        }

        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        Call<LoginResponse> login_call = RetrofitClient.get_instance().get_api()
                .sign_in( username, password );
        login_call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse login_response = response.body();

                Toast.makeText(Login.this, login_response.getMessage(),
                        Toast.LENGTH_LONG ).show();

                if ( login_response.isSuccess() ) {
                    DairibordSharedPreferences.get_instance( getApplicationContext() )
                            .save_user( login_response.getUser() );
                    Intent intent = new Intent( Login.this, MainActivity.class );
                    intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.
                            FLAG_ACTIVITY_NEW_TASK );

                    startActivity( intent );
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                String error_message = "Could not connect to database server: " + t.getMessage();
                Toast.makeText(Login.this, error_message, Toast.LENGTH_LONG ).show();
            }
        });
    }
}