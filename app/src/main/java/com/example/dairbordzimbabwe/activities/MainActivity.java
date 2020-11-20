

package com.example.dairbordzimbabwe.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.dairbordzimbabwe.R;
import com.example.dairbordzimbabwe.storage.DairibordSharedPreferences;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if ( !DairibordSharedPreferences.get_instance( getApplicationContext() ).is_signed_in() ) {
            Intent intent = new Intent( MainActivity.this, Login.class );
            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );

            startActivity( intent );
        }
    }

    private void sign_out() {
        DairibordSharedPreferences.get_instance( getApplicationContext() ).sign_out();

        Intent intent = new Intent( MainActivity.this, Login.class );
        intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );

        startActivity( intent );

    }

}