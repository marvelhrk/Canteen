package com.example.canteen.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.canteen.CartActivity;
import com.example.canteen.R;
import com.example.canteen.fragments.home;
import com.example.canteen.fragments.mycart;
import com.example.canteen.fragments.orders;
import com.example.canteen.fragments.profile;
import com.example.canteen.fragments.search;
import com.example.canteen.models.Order;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainScreen extends BaseActivity {
    BottomNavigationView bottomNavigationView;
    home Home = new home();
    search Search = new search();
    mycart Mycart = new mycart();
    orders Orders = new orders();
    profile Profile = new profile();

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmnet_layout,Home).commit();

        bottomNavigationView = findViewById(R.id.bottomnavbar);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.homenav:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmnet_layout,Home).commit();
                        return true;

                    case R.id.searchnav:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmnet_layout,Search).commit();
                        return true;
                    case R.id.mycart:
                        Intent Z = new Intent(getApplicationContext(), CartActivity.class);
                        startActivity(Z);
                        return true;
                    case R.id.orders:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmnet_layout,Orders).commit();
                        return true;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmnet_layout,Profile).commit();
                        return true;
                }
                return false;
            }
        });
    }
}