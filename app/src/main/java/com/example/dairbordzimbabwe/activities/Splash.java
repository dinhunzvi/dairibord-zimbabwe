package com.example.dairbordzimbabwe.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dairbordzimbabwe.R;

public class Splash extends AppCompatActivity {

    private TextView tvTitle;
    private ImageView imgLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tvTitle = findViewById( R.id.tvTitle );
        imgLogo = findViewById( R.id.imgLogo );
    }
}