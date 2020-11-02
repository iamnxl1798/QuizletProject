package com.example.quizlet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.quizlet.model.Question;
import com.example.quizlet.adapter.StudyAdapter;

import java.util.ArrayList;
import java.util.List;

public class StudyActivity extends AppCompatActivity {

    StudyAdapter studyAdapter;
    List<Question> questions;
    private RecyclerView recyView_Study;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        questions = new ArrayList<>();
        questions.add(new Question("aaaaaaaaaaaaaaaaaaaaaaaaqweeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "A", false));
        questions.add(new Question("aaaaaaaaaaaaaaaaaaaaaaaa", "B", false));
        questions.add(new Question("aaaaaaaaaaaaaaaaaaaaaaaa", "C", true));
        questions.add(new Question("aaaaaaaaaaaaaaaaaaaaaaaa", "A", false));
        questions.add(new Question("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "A", true));
        questions.add(new Question("aaaaaaaaaaaaaaaaaaaaaaaa", "A", true));
        questions.add(new Question("aaaaaaaaaaaaaaaaaaaaaaaa", "A", true));
        questions.add(new Question("aaaaaaaaaaaaaaaaaaaaaaaa", "A", false));
        AnhXa();
        studyAdapter = new StudyAdapter(this, questions, new StudyAdapter.OnItemClickListener() {
            @Override
            public void OnClickMore(int position) {
                questions.get(position).setGim(!questions.get(position).isGim());
            }
        });
        recyView_Study.setLayoutManager(new LinearLayoutManager(this));
        recyView_Study.setAdapter(studyAdapter);
    }

    public void AnhXa() {
        recyView_Study = findViewById(R.id.ryce_study);
    }
}