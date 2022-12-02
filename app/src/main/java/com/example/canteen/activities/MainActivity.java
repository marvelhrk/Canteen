package com.example.canteen.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.canteen.R;
import com.example.canteen.utils.LocalStorage;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 900;
    Timer timer;
    ImageView image;
    LocalStorage localStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        timer = new Timer();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(() -> {
           localStorage = new LocalStorage(getApplicationContext());
        if (localStorage.isUserLoggedIn()) {
            startActivity(new Intent(getApplicationContext(), MainScreen.class));
            finish();
        }
        else{
            Intent homeintent = new Intent(MainActivity.this, Welcome.class);
            startActivity(homeintent);
            finish();
        }
        },SPLASH_TIME_OUT);
        image = findViewById(R.id.imageView);
    }
    @Override
    protected void onStop() {
        timer.cancel();
        super.onStop();
    }

    @Override
    protected void onPause() {
        timer.cancel();
        super.onPause();
    }

}