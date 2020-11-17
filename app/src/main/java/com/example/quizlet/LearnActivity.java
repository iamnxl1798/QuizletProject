package com.example.quizlet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizlet.model.Answers;
import com.example.quizlet.adapter.AnswerAdapter;
import com.example.quizlet.adapter.LearnAdapter;
import com.example.quizlet.dao.TestDAO;
import com.example.quizlet.database.MyDatabase;
import com.example.quizlet.model.AnswersOfQuestion;
import com.example.quizlet.model.Question;

import java.util.ArrayList;
import java.util.List;

public class LearnActivity extends AppCompatActivity {
    private MyDatabase myDatabase;
    private RecyclerView recyclerView;
    private List<Question> getQuestions;
    private LearnAdapter adapter;
    private TestDAO testDAO;
    private Button btnSubmit;
    private TextView textView15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, COMMON.DB_NAME).allowMainThreadQueries().build();
        testDAO = myDatabase.createTestDAO();
        getQuestions = testDAO.getQuestion();

        recyclerView = findViewById(R.id.testItemRcl);
        adapter = new LearnAdapter(getQuestions, getApplicationContext());
        recyclerView.setAdapter(adapter);
        btnSubmit = findViewById(R.id.btnSubmit);
        textView15 = findViewById(R.id.textView15);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<AnswersOfQuestion> getAnswersOfQuestions = AnswerAdapter.answersOfQuestions;
                ArrayList<Answers> getAnswers = (ArrayList<Answers>) myDatabase.createAnswerDAO().getAnswersList();
                double pointsOfAllQuestions = 0;
                double mark1111 = 0;
                double mark2222 = 0;
                int k = 0;

                for (Question q : getQuestions) {
                    double numberOfAnswerIsTrue = 0;
                    double numberOfAnswerIsTrueIsChose = 0;
                    double numberOfAnswerIsChose = 0;
                    ArrayList<Answers> getA1 = new ArrayList<>();

                    for (int i = 0; i < getAnswers.size(); i++) {
                        if (q.getId() == getAnswers.get(i).getQuestionId()) {
                            getA1.add(getAnswers.get(i));
                        }
                    }

                    for (Answers a : getA1) {
                        if (a.isTrue()) {
                            numberOfAnswerIsTrue++;

                            //get numberOfAnswerIsTrueIsChose
                            for (int j = 0; j < getAnswersOfQuestions.size(); j++) {
                                if (a.getQuestionId() == getAnswersOfQuestions.get(j).getQuestionId()
                                        && a.getId() == getAnswersOfQuestions.get(j).getAnswerId()) {
                                    numberOfAnswerIsTrueIsChose++;
                                }
                            }

                            //get numberOfAnswerIsChose
                            ArrayList<AnswersOfQuestion> answersOfQuestions = new ArrayList<>();
                            for (int i = 0; i < getAnswersOfQuestions.size(); i++) {
                                if (a.getQuestionId() == getAnswersOfQuestions.get(i).getQuestionId()) {
                                    answersOfQuestions.add(getAnswersOfQuestions.get(i));
                                }
                            }
                            numberOfAnswerIsChose = answersOfQuestions.size();
                        }
                    }
                    if (k == 0) {
                        mark1111 = numberOfAnswerIsTrue;
                        mark2222 = numberOfAnswerIsTrueIsChose;
                        k = 1;
                    }
                    if (numberOfAnswerIsTrue >= numberOfAnswerIsChose) {
                        pointsOfAllQuestions += (numberOfAnswerIsTrueIsChose / numberOfAnswerIsTrue);
                    }
                }
                //Toast.makeText(TestActivity.this, "numberOfAnswerIsTrue = " + mark1111, Toast.LENGTH_SHORT).show();
                //Toast.makeText(TestActivity.this, "numberOfAnswerIsTrueIsChose = " + mark2222, Toast.LENGTH_SHORT).show();
                double pointAll = pointsOfAllQuestions / getQuestions.size() * 10;
                //Toast.makeText(v.getContext(), "pointAll = " + pointAll, Toast.LENGTH_SHORT).show();
                int totalQuestion = getQuestions.size();

                AnswerAdapter.answersOfQuestions.removeAll(getAnswersOfQuestions);

                final Dialog dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.popup_result);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                TextView tvBtnSaveChange = dialog.getWindow().findViewById(R.id.tvBtnSaveChange);
                TextView textView14 = dialog.getWindow().findViewById(R.id.textView15);
                textView14.setText("You choose correct :"+pointsOfAllQuestions+"/"+getQuestions.size()+"\n \n My Point is : \n"+pointAll);
                tvBtnSaveChange.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(dialog.getContext(), TestActivity.class);
                        startActivity(intent);
                    }
                });


            }
        });
    }
}