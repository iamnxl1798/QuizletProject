package com.example.quizlet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.quizlet.model.Question;
import com.example.quizlet.adapter.StudyAdapter;

import java.util.ArrayList;
import java.util.List;

public class StudyActivity extends AppCompatActivity {

    StudyAdapter studyAdapter;
    List<Question> questions;
    private RecyclerView recyView_Study;
    ImageView back;
    LinearLayout checkAll, checkSao,theghinho;
    View view1, view2;

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
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        view1.setBackgroundColor(Color.BLUE);
        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view1.setBackgroundColor(Color.BLUE);
                view2.setBackgroundColor(Color.WHITE);

            }
        });

        checkSao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view2.setBackgroundColor(Color.BLUE);
                view1.setBackgroundColor(Color.WHITE);

            }
        });

        theghinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudyActivity.this, TheGhiNhoActivity.class);
                startActivity(intent);
            }
        });
    }

    public void AnhXa() {
        recyView_Study = findViewById(R.id.ryce_study);
        back = findViewById(R.id.back_home);
        checkAll = findViewById(R.id.checkHocAll);
        checkSao = findViewById(R.id.checkHocSaoNgoai);
        view1 = findViewById(R.id.checkHocTatCa);
        view2 = findViewById(R.id.checkHocSao);
        theghinho = findViewById(R.id.theghinho);
    }
}