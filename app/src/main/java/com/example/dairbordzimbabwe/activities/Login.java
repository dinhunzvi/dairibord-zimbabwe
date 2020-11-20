
package com.example.dairbordzimbabwe.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.dairbordzimbabwe.R;
import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {

    private TextInputEditText edtUsername, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtPassword = findViewById( R.id.edtPassword );
        edtUsername = findViewById( R.id.edtUsername );

    }
}