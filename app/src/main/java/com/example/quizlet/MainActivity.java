package com.example.quizlet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.quizlet.fragment.AccountFragment;
import com.example.quizlet.fragment.AddCourseFragment;
import com.example.quizlet.fragment.ChatFragment;
import com.example.quizlet.fragment.HomeFragment;
import com.example.quizlet.fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActionBar toolbar;
    long idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = getSupportActionBar();
        setContentView(R.layout.activity_main);
        BottomNavigationView navigationView = findViewById(R.id.naviboton);
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        loadFragment(new HomeFragment());
        Intent intent = getIntent();
        idUser = Long.parseLong(intent.getStringExtra("idUser"));
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment;
            switch (menuItem.getItemId()) {
                case R.id.navi_home:
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navi_search:
                    fragment = new SearchFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navi_chat:
                    fragment = new AddCourseFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navi_account:
                    fragment = new AccountFragment(idUser);
                    loadFragment(fragment);
                    return true;
            }

            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_home, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}