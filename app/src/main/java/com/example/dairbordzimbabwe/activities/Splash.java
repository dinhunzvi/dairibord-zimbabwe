package com.example.dairbordzimbabwe.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dairbordzimbabwe.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN );

        TextView tvTitle = findViewById(R.id.tvTitle);
        ImageView imgLogo = findViewById(R.id.imgLogo);

        Animation bottom = AnimationUtils.loadAnimation(this, R.anim.bottom);
        Animation top = AnimationUtils.loadAnimation(this, R.anim.top);

        tvTitle.setAnimation(bottom);
        imgLogo.setAnimation(top);

        int SPLASH_SCREEN = 5000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent( Splash.this, MainActivity.class );
                startActivity( intent );
                finish();
            }
        }, SPLASH_SCREEN);
    }
}