

package com.example.dairbordzimbabwe.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.dairbordzimbabwe.R;
import com.example.dairbordzimbabwe.fragments.CustomersFragment;
import com.example.dairbordzimbabwe.fragments.DeliveriesFragment;
import com.example.dairbordzimbabwe.fragments.DispatchesFragment;
import com.example.dairbordzimbabwe.fragments.HomeFragment;
import com.example.dairbordzimbabwe.fragments.ProductsFragment;
import com.example.dairbordzimbabwe.fragments.SuppliersFragment;
import com.example.dairbordzimbabwe.fragments.UsersFragment;
import com.example.dairbordzimbabwe.models.User;
import com.example.dairbordzimbabwe.storage.DairibordSharedPreferences;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer_layout;
    NavigationView navigation_view;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer_layout = findViewById( R.id.drawer_layout );
        navigation_view = findViewById( R.id.navigation_view );
        toolbar = findViewById( R.id.tblToolbar );
        View header_view = navigation_view.getHeaderView(0);

        setSupportActionBar( toolbar );

        navigation_view.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, drawer_layout,
                toolbar, R.string.drawer_open, R.string.drawer_close );

        toggle.syncState();
        navigation_view.setNavigationItemSelectedListener( this );

        TextView tvName = header_view.findViewById(R.id.tvName);

        User user = DairibordSharedPreferences.get_instance( getApplicationContext() ).get_user();

        tvName.setText( String.format( "%s %s", user.getFirst_name(), user.getLast_name() ) );

    }

    @Override
    public void onBackPressed() {

        if ( drawer_layout.isDrawerOpen( GravityCompat.START ) ) {
            drawer_layout.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }

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

    private void load_fragment ( Fragment fragment ) {
        getSupportFragmentManager().beginTransaction()
                .replace( R.id.fragment_container, fragment )
                .addToBackStack( null )
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch ( item.getItemId() ) {

            case R.id.nav_dispatches:

                fragment = new DispatchesFragment();

                break;

            case R.id.nav_products:

                fragment = new ProductsFragment();

                break;

            case R.id.nav_sign_out:

                sign_out();

                break;

            case R.id.nav_home:

                fragment = new HomeFragment();

                break;

            case R.id.nav_customers:

                fragment = new CustomersFragment();

                break;
            case R.id.nav_users:

                fragment = new UsersFragment();

                break;

            case R.id.nav_suppliers:

                fragment = new SuppliersFragment();

                break;

            case R.id.nav_deliveries:

                fragment = new DeliveriesFragment();

                break;

        }

        if ( fragment != null ) {
            load_fragment( fragment );
        }

        drawer_layout.closeDrawer( GravityCompat.START );

        return false;

    }
}