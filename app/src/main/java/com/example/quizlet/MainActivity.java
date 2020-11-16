package com.example.quizlet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.quizlet.dao.UserDAO;
import com.example.quizlet.database.MyDatabase;
import com.example.quizlet.fragment.AccountFragment;
import com.example.quizlet.fragment.AddCourseFragment;
import com.example.quizlet.fragment.ChatFragment;
import com.example.quizlet.fragment.HomeFragment;
import com.example.quizlet.fragment.SearchFragment;
import com.example.quizlet.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private ActionBar toolbar;
    long idUser;
    ImageView test;
    MyDatabase myDatabase;
    UserDAO userDAO;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = getSupportActionBar();
        setContentView(R.layout.activity_main);
        test = findViewById(R.id.test);
        BottomNavigationView navigationView = findViewById(R.id.naviboton);
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        loadFragment(new HomeFragment());
        Intent intent = getIntent();
//        String urlImage = intent.getStringExtra("urlImage");
        long idUser = Long.parseLong(intent.getStringExtra("idUser"));
        myDatabase = Room.databaseBuilder(MainActivity.this, MyDatabase.class, COMMON.DB_NAME).allowMainThreadQueries().build();
        userDAO = myDatabase.createUserDAO();
        user = userDAO.getUser(idUser);
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        options.inSampleSize = 2;
        Bitmap bitmap = BitmapFactory.decodeByteArray(user.getUriImage(), 0, user.getUriImage().length);

        test.setImageBitmap(bitmap);

//        idUser = Long.parseLong(intent.getStringExtra("idUser"));
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
                    fragment = new AccountFragment(user.getId());
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