package com.example.quizlet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizlet.dao.UserDAO;
import com.example.quizlet.database.MyDatabase;
import com.example.quizlet.model.User;

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
    MyDatabase myDatabase;
    UserDAO userDAO;

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

                myDatabase = Room.databaseBuilder(LoginActivity.this, MyDatabase.class, COMMON.DB_NAME).allowMainThreadQueries().build();
                userDAO = myDatabase.createUserDAO();

                if (textViewEmail.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Vui lòng điền tài khoản", Toast.LENGTH_SHORT).show();
                } else if (textViewPass.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Vui lòng điền mật khẩu", Toast.LENGTH_SHORT).show();
                }
                else{
                    User user = userDAO.checkAccountUser(textViewEmail.getText().toString(), textViewPass.getText().toString());
                    if (user != null) {
                        edit.remove("userID");
                        edit.putLong("userID",user.getId());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("idUser", user.getId()+"");
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu sai", Toast.LENGTH_SHORT).show();
                    }
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