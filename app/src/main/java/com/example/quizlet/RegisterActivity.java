package com.example.quizlet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizlet.dao.UserDAO;
import com.example.quizlet.database.MyDatabase;
import com.example.quizlet.model.User;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class RegisterActivity extends AppCompatActivity {
    private TextView textViewAccount;
    private TextView textViewPass;
    private TextView textViewSwichScreenLogin;
    private TextView textViewPassAgain;
    private TextView textEmail;
    private Button buttonRegister;
    private Button buttonGG;
    private Button buttonFB;
    private Button buttonApple;
    private Button buttonChangeAvatar;
    private ImageView imageViewAvatar;
    private Button buttonCamera;
    private Button buttonTakeAPicture;
    private COMMON common;
    MyDatabase myDatabase;
    UserDAO userDAO;
    String uriImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textViewAccount = findViewById(R.id.textViewAccount_register);
        textEmail = findViewById(R.id.etEmail_register);
        textViewPass = findViewById(R.id.etPass_register);
        textViewPassAgain = findViewById(R.id.etPassAgain_register);
        buttonApple = findViewById(R.id.buttonApple_register);
        buttonChangeAvatar = findViewById(R.id.buttonChangeAvatar);
        buttonFB = findViewById(R.id.buttonFb_register);
        buttonGG = findViewById(R.id.buttonGG_register);
        buttonRegister = findViewById(R.id.buttonRegister);
        common = new COMMON();
        imageViewAvatar = findViewById(R.id.imageViewAvatar2);
        textViewSwichScreenLogin = findViewById(R.id.textViewSwichLogin);
        textViewSwichScreenLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        buttonChangeAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(RegisterActivity.this);
                dialog.setContentView(R.layout.dialog_register_changeavatar);
                buttonCamera = dialog.findViewById(R.id.buttonCamera);
                buttonTakeAPicture = dialog.findViewById(R.id.buttonTakeAPicture);
                buttonTakeAPicture.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Chon_Hinh();
                        dialog.cancel();
                    }
                });
                buttonCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Chup_Hinh();
                        dialog.cancel();
                    }
                });

                dialog.show();
                //  Chon_Hinh();

            }
        });
        dangKy();

    }

    public void dangKy() {
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabase = Room.databaseBuilder(RegisterActivity.this, MyDatabase.class, COMMON.DB_NAME).allowMainThreadQueries().build();
                userDAO = myDatabase.createUserDAO();
                if (textViewAccount.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Vui lòng điền tên tài khoản", Toast.LENGTH_SHORT).show();
                } else if (textViewAccount.getText().toString().length() < 6) {
                    Toast.makeText(RegisterActivity.this, "Vui lòng điền tên tài khoản lớn hơn 6 ký tự", Toast.LENGTH_SHORT).show();
                } else if (userDAO.checkAccountExist(textViewAccount.getText().toString()) != null) {
                    Toast.makeText(RegisterActivity.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                } else if (textEmail.getText().toString().equals("") || !common.validateEmail(textEmail.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "Email để trống hoặc không đúng định dạng", Toast.LENGTH_SHORT).show();
                } else if (textViewPass.getText().toString().equals("") || textViewPassAgain.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Vui lòng điền mật khẩu", Toast.LENGTH_SHORT).show();
                } else if (textViewPass.getText().toString().length() < 6) {
                    Toast.makeText(RegisterActivity.this, "Vui lòng điền mật khẩu lớn hơn 6 ký tự", Toast.LENGTH_SHORT).show();
                } else if (!textViewPass.getText().toString().equals(textViewPassAgain.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User(textViewAccount.getText().toString(), textViewPass.getText().toString(), textEmail.getText().toString(), uriImage);
                    try {
                        userDAO.insert(user);
                        Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 321) {
            try {
                Uri imageUri = data.getData();
                uriImage = imageUri + "";
                InputStream is = getContentResolver().openInputStream(imageUri);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                imageViewAvatar.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (requestCode == 123) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageViewAvatar.setImageBitmap(bitmap);
        }
    }

    private void Chon_Hinh() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 321);
    }

    private void Chup_Hinh() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 123);
    }

}