package com.deliverykreani;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.core.content.ContextCompat;

import com.deliverykreani.activity.media.MediaActivity;
import com.deliverykreani.fragment.ProfileFragment;
import com.deliverykreani.fragment.TaskFragment;
import com.deliverykreani.fragment.HomeFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.deliverykreani.service.LocationUpdateService;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    Fragment currentFragment = null;
    FragmentTransaction ft;
    private long onRecentBackPressedTime;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_dashboard:
                    currentFragment = new TaskFragment();
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_layout, currentFragment);
                    ft.commit();
                    return true;

                case R.id.navigation_notifications:
                    currentFragment = new ProfileFragment();
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_layout, currentFragment);
                    ft.commit();
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentFragment = new TaskFragment();
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout, currentFragment);
        ft.commit();
        startLocationService();
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void startLocationService() {
        Intent intent = new Intent(this, LocationUpdateService.class);
        ContextCompat.startForegroundService(this, intent);
    }

    @Override
    public void onBackPressed() {

        if (System.currentTimeMillis() - onRecentBackPressedTime > 2000) {
            onRecentBackPressedTime = System.currentTimeMillis();
            Toast.makeText(this, "Please press BACK again to exit", Toast.LENGTH_SHORT).show();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {

    }
}
