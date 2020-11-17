package com.example.quizlet.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.quizlet.COMMON;
import com.example.quizlet.R;
import com.example.quizlet.dao.AnswerDAO;
import com.example.quizlet.dao.TestDAO;
import com.example.quizlet.database.MyDatabase;
import com.example.quizlet.model.Answers;

import com.example.quizlet.model.Question;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.Holder> {
    private List<Question> getQuestions;
    private Context context;
    private MyDatabase myDatabase;

    public TestAdapter(List<Question> getQuestions, Context context) {
        this.getQuestions = getQuestions;
        this.context = context;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.answer_custom, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        holder.tvQuestion.setText(getQuestions.get(position).getQuestionName());
        myDatabase = Room.databaseBuilder(context, MyDatabase.class, COMMON.DB_NAME).allowMainThreadQueries().build();
        AnswerDAO answerDAO = myDatabase.createAnswerDAO();
        ArrayList<Answers> getAnswers = (ArrayList<Answers>) answerDAO.getAnswersList();

        ArrayList<Answers> getAnswers1 = new ArrayList<>();
        for (int i = 0; i < getAnswers.size(); i++) {
            if (getAnswers.get(i).getQuestionId() == getQuestions.get(position).getId()) {
                getAnswers1.add(getAnswers.get(i));
            }
        }
        AnswerAdapter adapter = new AnswerAdapter(getAnswers1, context,getAnswers);
        holder.rvAnswer.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        holder.rvAnswer.setLayoutManager(layoutManager);
    }

    @Override
    public int getItemCount() {
        return getQuestions.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tvQuestion;
        RecyclerView rvAnswer;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.tvQuestion);
            rvAnswer = itemView.findViewById(R.id.rvAnswer);
        }

    }
}
