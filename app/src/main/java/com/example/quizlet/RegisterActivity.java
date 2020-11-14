package com.example.quizlet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 321) {
            try {
                Uri imageUri = data.getData();
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
    private void Chup_Hinh(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,123);
    }

}