package com.example.quizlet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizlet.dao.AnswerDAO;
import com.example.quizlet.dao.ImportQuestionDAO;
import com.example.quizlet.dao.JoinedCouesesDAO;
import com.example.quizlet.dao.QuesstionDAO;
import com.example.quizlet.database.MyDatabase;
import com.example.quizlet.model.Answers;
import com.example.quizlet.model.ImportantQuestions;
import com.example.quizlet.model.Item;
import com.example.quizlet.model.JoinedCourses;
import com.example.quizlet.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TheGhiNhoActivity extends AppCompatActivity {

    List<Question> questions;
    LinearLayout linearLayout;
    TextView textView, sttghinho;
    private float x1, y1, x2, y2;
    private float radio = 1;
    ProgressBar progressBar;
    MyDatabase myDatabase;
    private QuesstionDAO quesstionDAO;
    AnswerDAO answerDAO;
    ImageView back_home;
    Button bat_dau;
    Switch aSwitch;
    EditText et_nhap_sl;
    JoinedCouesesDAO joinedCouesesDAO;
    ImportQuestionDAO importQuestionDAO;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_ghi_nho);
        AnhXa();
        myDatabase = Room.databaseBuilder(TheGhiNhoActivity.this, MyDatabase.class, COMMON.DB_NAME).allowMainThreadQueries().build();
        quesstionDAO = myDatabase.createQuesstionDAO();
        joinedCouesesDAO = myDatabase.createJoinCourseDAO();
        importQuestionDAO = myDatabase.createImportQuestionDAO();
        answerDAO = myDatabase.createAnswerDAO();
        Intent intent = this.getIntent();
        final long courseId = Long.parseLong(intent.getStringExtra("QuestionListExtra"));
        questions = quesstionDAO.getAllQuesstionByCourseId(courseId);
        final List<Item> items = new ArrayList<>();
        final List<Item> items1 = new ArrayList<>();

        for (int i = 0; i < questions.size(); i++) {
            List<Answers> answers = answerDAO.getAnswerByQuestion(questions.get(i).getId());

            items.add(new Item(questions.get(i), (ArrayList<Answers>) answers));
            items1.add(new Item(questions.get(i), (ArrayList<Answers>) answers));

        }

        back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        et_nhap_sl.setText("" + items1.size());

        Toast.makeText(TheGhiNhoActivity.this, "test", Toast.LENGTH_SHORT).show();
        final int[] count = {0};
        final boolean[] check = {false};
        bat_dau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count[0] = 0;
                int soLuong = Integer.parseInt(et_nhap_sl.getText().toString());
                boolean check = false;

                if (aSwitch.isChecked()) {
                    JoinedCourses joinedCourss = joinedCouesesDAO.getAllJoinedCoursesByUserCourse(1, courseId);
//                    List<Question> questions = new ArrayList<>();
                    questions = importQuestionDAO.getQuestionSao(joinedCourss.getId());

                    for (int i = items.size() - 1; i >= 0; i--) {
                        items.remove(i);

                    }

                    for (int i = items1.size() - 1; i >= 0; i--) {
                        items1.remove(i);

                    }
                    for (int i = 0; i < questions.size(); i++) {
                        List<Answers> answers = answerDAO.getAnswerByQuestion(questions.get(i).getId());
                        items.add(new Item(questions.get(i), (ArrayList<Answers>) answers));
                        items1.add(new Item(questions.get(i), (ArrayList<Answers>) answers));
                    }
                } else {
                    questions = quesstionDAO.getAllQuesstionByCourseId(courseId);

                    for (int i = items.size() - 1; i >= 0; i--) {
                        items.remove(i);
                    }

                    for (int i = items1.size() - 1; i >= 0; i--) {
                        items1.remove(i);

                    }

                    for (int i = 0; i < questions.size(); i++) {
                        List<Answers> answers = answerDAO.getAnswerByQuestion(questions.get(i).getId());

                        items.add(new Item(questions.get(i), (ArrayList<Answers>) answers));
                        items1.add(new Item(questions.get(i), (ArrayList<Answers>) answers));

                    }
                }

                if (soLuong > items.size()) {
                    Toast.makeText(TheGhiNhoActivity.this, "Nhập số quá lơn", Toast.LENGTH_LONG).show();
                } else if (soLuong <= 0) {
                    Toast.makeText(TheGhiNhoActivity.this, "Nhập số quá nhỏ", Toast.LENGTH_LONG).show();
                } else if (soLuong == 1) {
                    for (int i = items1.size() - 1; i >= 0; i--) {
                        items1.remove(i);
                    }
                    Item item3 = items.get(new Random().nextInt(items.size()));
                    items1.add(item3);

                } else {
                    for (int i = items1.size() - 1; i >= 0; i--) {
                        items1.remove(i);
                    }
                    int counttt = 1;

                    while (counttt < soLuong) {
                        boolean checkTrung = false;
                        Item item3 = items.get(new Random().nextInt(items.size()));
                        if (items1.isEmpty()) items1.add(item3);
                        else {
                            for (int j = 0; j < items1.size(); j++) {
                                if (items1.get(j).equals(item3)) {
                                    checkTrung = true;
                                }
                            }
                            if (!checkTrung) {
                                items1.add(item3);
                                counttt++;
                            }
                        }

                    }
                }
                final List<Answers> answers = items1.get(count[0]).getDefinition();
                String quesstionName = items1.get(count[0]).getTerm().getQuestionName() + "\n";
                String s = "";
                String anwserTrue = "";
                int a = 97;
                for (int i = 0; i < answers.size(); i++) {
                    char b = (char) a;
                    if (answers.get(i).isTrue()) {
                        anwserTrue += b;
                    }
                    String c = b + " : " + answers.get(i).getAnswer();
                    a++;
                    s += c + "\n";
                }

                quesstionName += "\n" + s;
                textView.setText(quesstionName);
                progressBar.setMin(0);
                progressBar.setMax(items1.size() - 1);

                sttghinho.setText("1/" + (items1.size()));

            }
        });
        final List<Answers> answers = items1.get(count[0]).getDefinition();
        String quesstionName = items1.get(count[0]).getTerm().getQuestionName() + "\n";
        String s = "";
        String anwserTrue = "";
        int a = 97;
        for (int i = 0; i < answers.size(); i++) {
            char b = (char) a;
            if (answers.get(i).isTrue()) {
                anwserTrue += b;
            }
            String c = b + " : " + answers.get(i).getAnswer();
            a++;
            s += c + "\n";
        }

        quesstionName += "\n" + s;
        textView.setText(quesstionName);
        progressBar.setMin(0);
        progressBar.setMax(items1.size() - 1);

        sttghinho.setText("1/" + (items1.size()));

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
                            if (count[0] > items1.size() - 1) count[0] = items1.size() - 1;

                            String quesstionName = items1.get(count[0]).getTerm().getQuestionName() + "\n";
                            final List<Answers> answers = items1.get(count[0]).getDefinition();

                            String s = "";
                            String anwserTrue = "";
                            int a = 97;
                            for (int i = 0; i < answers.size(); i++) {

                                char b = (char) a;

                                if (answers.get(i).isTrue()) {
                                    anwserTrue += b;
                                }
                                String c = b + " : " + answers.get(i).getAnswer();
                                a++;
                                s += c + "\n";
                            }
                            quesstionName += "\n" + s;
                            textView.setText(quesstionName);
                            progressBar.setProgress(count[0]);
                            sttghinho.setText((count[0] + 1) + "/" + (items1.size()));

                            ObjectAnimator animator1 = ObjectAnimator.ofFloat(linearLayout, "translationX", 300, 0);
                            animator1.setDuration(500);  // set thời gian để chạy từ 0f - 1f
                            animator1.setRepeatMode(ValueAnimator.REVERSE); // alpha chạy ngược lại từ 1f - 0f
                            animator1.start();
                            ObjectAnimator animator = ObjectAnimator.ofFloat(linearLayout, "alpha", 1f, 0f);
                            animator.setDuration(500);  // set thời gian để chạy từ 0f - 1f
                            animator.start();
                            ObjectAnimator animator2 = ObjectAnimator.ofFloat(linearLayout, "alpha", 0f, 1f);
                            animator2.setDuration(500);  // alpha chạy ngược lại từ 1f - 0f
                            animator2.start();
                        }
                    } else if (x2 - x1 > 0) {
                        if (count[0] > 0) {
                            count[0]--;
                            if (count[0] < 0) count[0] = 0;

                            String quesstionName = items1.get(count[0]).getTerm().getQuestionName() + "\n";
                            final List<Answers> answers = items1.get(count[0]).getDefinition();

                            String s = "";
                            String anwserTrue = "";
                            int a = 97;
                            for (int i = 0; i < answers.size(); i++) {

                                char b = (char) a;

                                if (answers.get(i).isTrue()) {
                                    anwserTrue += b;
                                }
                                String c = b + " : " + answers.get(i).getAnswer();
                                a++;
                                s += c + "\n";
                            }
                            sttghinho.setText((count[0] + 1) + "/" + (items1.size()));

                            quesstionName += "\n" + s;
                            textView.setText(quesstionName);
                            progressBar.setProgress(count[0]);
                            ObjectAnimator animator1 = ObjectAnimator.ofFloat(linearLayout, "translationX", -300, 0);
                            animator1.setDuration(500);  // set thời gian để chạy từ 0f    - 1f
                            animator1.setRepeatMode(ValueAnimator.REVERSE); // alpha chạy ngược lại từ 1f - 0f
                            animator1.start();
                            ObjectAnimator animator = ObjectAnimator.ofFloat(linearLayout, "alpha", 1f, 0f);
                            animator.setDuration(500);  // set thời gian để chạy từ 0f - 1f
                            animator.start();
                            ObjectAnimator animator2 = ObjectAnimator.ofFloat(linearLayout, "alpha", 0f, 1f);
                            animator2.setDuration(500);  // alpha chạy ngược lại từ 1f - 0f
                            animator2.start();
                        }
                    } else {
                        String quesstionName = items1.get(count[0]).getTerm().getQuestionName() + "\n";
                        final List<Answers> answers = items1.get(count[0]).getDefinition();
                        String s = "";
                        String anwserTrue = "";
                        int a = 97;
                        for (int i = 0; i < answers.size(); i++) {

                            char b = (char) a;

                            if (answers.get(i).isTrue()) {
                                anwserTrue += b;
                            }
                            String c = b + " : " + answers.get(i).getAnswer();
                            a++;
                            s += c + "\n";
                        }
                        quesstionName += "\n" + s;
                        ObjectAnimator animator = ObjectAnimator.ofFloat(linearLayout, "alpha", 1f, 0f);
                        animator.setDuration(500);  // set thời gian để chạy từ 0f - 1f
                        animator.start();
                        if (!check[0]) {
                            textView.setText(anwserTrue);
                        } else {
                            textView.setText(quesstionName);
                        }
                        ObjectAnimator animator1 = ObjectAnimator.ofFloat(linearLayout, "alpha", 0f, 1f);
                        animator1.setDuration(500);  // alpha chạy ngược lại từ 1f - 0f
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
        sttghinho = findViewById(R.id.sttghinho);
        back_home = findViewById(R.id.back_home);
        bat_dau = findViewById(R.id.bat_dau);
        aSwitch = findViewById(R.id.switch_gansao);
        et_nhap_sl = findViewById(R.id.et_nhap_sl);
    }
}