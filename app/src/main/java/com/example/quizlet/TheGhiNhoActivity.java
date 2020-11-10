package com.example.quizlet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizlet.model.Question;

import java.util.ArrayList;
import java.util.List;

public class TheGhiNhoActivity extends AppCompatActivity {

    List<Question> questions;
    LinearLayout linearLayout;
    TextView textView;
    private float x1, y1, x2, y2;
    private float radio = 1;
    ProgressBar progressBar;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_ghi_nho);
        AnhXa();

        questions = new ArrayList<>();

        questions.add(new Question("aaaaaaaaaaaaaaaaaaaaaaaaqweeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        questions.add(new Question("aaaaaaaaaaaaaaaaaaaaaaaaqweeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        questions.add(new Question("aaaaaaaaaaaaaaaaaaaaaaaaqweeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        questions.add(new Question("aaaaaaaaaaaaaaaaaaaaaaaaqweeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        questions.add(new Question("aaaaaaaaaaaaaaaaaaaaaaaaqweeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        questions.add(new Question("aaaaaaaaaaaaaaaaaaaaaaaaqweeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));


        Toast.makeText(TheGhiNhoActivity.this, "test", Toast.LENGTH_SHORT).show();
        final int[] count = {0};
        final boolean[] check = {false};
        textView.setText(questions.get(count[0]).getQuestionName() + "");
        progressBar.setMin(0);
        progressBar.setMax(questions.size() - 1);
        progressBar.setProgress(2);

        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == event.ACTION_DOWN) {
                    x1 = event.getX();
                    y1 = event.getY();
                }
                if (event.getAction() == event.ACTION_UP) {
                    x2 = event.getX();
                    y2 = event.getY();
                    if (x2 - x1 < 0) {
                        if (count[0] <= questions.size() - 1) {
                            count[0]++;
                            textView.setText(questions.get(count[0]).getQuestionName() + "");
                            progressBar.setProgress(count[0]);
                            ObjectAnimator animator1 = ObjectAnimator.ofFloat(linearLayout, "translationX", 300, 0);
                            animator1.setDuration(1000);  // set thời gian để chạy từ 0f - 1f
                            animator1.setRepeatMode(ValueAnimator.REVERSE); // alpha chạy ngược lại từ 1f - 0f
                            animator1.start();
                            ObjectAnimator animator = ObjectAnimator.ofFloat(linearLayout, "alpha", 1f, 0f);
                            animator.setDuration(1000);  // set thời gian để chạy từ 0f - 1f
                            animator.start();
                            ObjectAnimator animator2 = ObjectAnimator.ofFloat(linearLayout, "alpha", 0f, 1f);
                            animator2.setDuration(1000);  // alpha chạy ngược lại từ 1f - 0f
                            animator2.start();
                        }
                    } else if (x2 - x1 > 0) {
                        if (count[0] > 0) {
                            count[0]--;
                            textView.setText(questions.get(count[0]).getQuestionName() + "");
                            progressBar.setProgress(count[0]);
                            ObjectAnimator animator1 = ObjectAnimator.ofFloat(linearLayout, "translationX", -300, 0);
                            animator1.setDuration(1000);  // set thời gian để chạy từ 0f    - 1f
                            animator1.setRepeatMode(ValueAnimator.REVERSE); // alpha chạy ngược lại từ 1f - 0f
                            animator1.start();
                            ObjectAnimator animator = ObjectAnimator.ofFloat(linearLayout, "alpha", 1f, 0f);
                            animator.setDuration(1000);  // set thời gian để chạy từ 0f - 1f
                            animator.start();
                            ObjectAnimator animator2 = ObjectAnimator.ofFloat(linearLayout, "alpha", 0f, 1f);
                            animator2.setDuration(1000);  // alpha chạy ngược lại từ 1f - 0f
                            animator2.start();
                        }
                    } else {
                        ObjectAnimator animator = ObjectAnimator.ofFloat(linearLayout, "alpha", 1f, 0f);
                        animator.setDuration(1000);  // set thời gian để chạy từ 0f - 1f
                        animator.start();
                        if (!check[0]) {
//                            textView.setText(questions.get(count[0]).getAnswer() + "");
                        } else {
                            textView.setText(questions.get(count[0]).getQuestionName() + "");

                        }

                        ObjectAnimator animator1 = ObjectAnimator.ofFloat(linearLayout, "alpha", 0f, 1f);
                        animator1.setDuration(1000);  // alpha chạy ngược lại từ 1f - 0f
                        animator1.start();
                        check[0] = !check[0];
                    }
                }
                return true;
            }
        });
    }

    public void AnhXa() {
        linearLayout = findViewById(R.id.theghinhohihi);
        textView = findViewById(R.id.hienthiganthe);
        progressBar = findViewById(R.id.progressBar);
    }
}