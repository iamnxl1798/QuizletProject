package com.example.quizlet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = getSupportActionBar();
        setContentView(R.layout.activity_main);
        BottomNavigationView navigationView = findViewById(R.id.naviboton);
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment;
            switch (menuItem.getItemId()) {
                case R.id.navi_home:
                    toolbar.setTitle("HOME");
                    return true;
                case R.id.navi_search:
                    toolbar.setTitle("SEARCH");
                    return true;
                case R.id.navi_chat:
                    toolbar.setTitle("CHAT");
                    return true;
            }
            return false;
        }
    };
}