package com.example.quizlet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private TextView textViewEmail;
    private TextView textViewPass;
    private TextView textViewForgotPass;
    private TextView textViewNewRegister;
    private CheckBox checkBoxRemeberPass;
    private Button buttonLogin;
    private Button buttonGG;
    private Button buttonFB;
    private Button buttonApple;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textViewEmail = findViewById(R.id.etEmail_register);
        textViewPass = findViewById(R.id.etPassword_login);
        textViewForgotPass = findViewById(R.id.etForGotPass);
        textViewNewRegister = findViewById(R.id.textViewSwichRegister);
        checkBoxRemeberPass = findViewById(R.id.checkBoxRemeberPass);
        buttonApple = findViewById(R.id.buttonApple_register);
        buttonFB = findViewById(R.id.buttonFB_login);
        buttonGG = findViewById(R.id.buttonGG_register);
        buttonLogin = findViewById(R.id.buttonLogin);

        loadAccount();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("taikhoan", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                if (checkBoxRemeberPass.isChecked()) {
                    edit.putString("email", textViewEmail.getText().toString());
                    edit.putString("pass", textViewPass.getText().toString());
                    edit.putBoolean("checked", true);
                } else {
                    edit.putString("email", "");
                    edit.putString("pass", "");
                    edit.putBoolean("checked", false);
                }
                edit.commit();
            }
        });
        textViewNewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RegisterActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAccount();
    }

    private void loadAccount() {
        SharedPreferences sharedPreferences = getSharedPreferences("taikhoan", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");
        String pass = sharedPreferences.getString("pass", "");
        Boolean check = sharedPreferences.getBoolean("checked", false);
        textViewEmail.setText(email);
        textViewPass.setText(pass);
        checkBoxRemeberPass.setChecked(check);
    }
}