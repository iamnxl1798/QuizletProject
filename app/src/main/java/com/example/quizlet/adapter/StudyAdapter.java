package com.example.quizlet.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizlet.model.Question;
import com.example.quizlet.R;

import java.util.List;

public class StudyAdapter extends RecyclerView.Adapter<StudyAdapter.ViewHolder> {

    private Context context;
    private List<Question> questions;
    private StudyAdapter.OnItemClickListener listener;

    public StudyAdapter(Context context, List<Question> questions, StudyAdapter.OnItemClickListener listener) {
        this.context = context;
        this.questions = questions;
        this.listener = listener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.review_study, parent, false);
        return new StudyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
//        final Question question = questions.get(position);
//        holder.nameQuestion.setText(question.getName());
//        holder.answer.setText(question.getAnswer());
//        if (question.isGim() == true) {
//            holder.star.setImageResource(R.drawable.star_click);
//            holder.star.setBackgroundColor(Color.YELLOW);
//        } else {
//            holder.star.setImageResource(R.drawable.star);
//        }
//
//        holder.star.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.OnClickMore(position);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameQuestion, answer;
        ImageView star;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameQuestion = itemView.findViewById(R.id.nameQuestion);
            answer = itemView.findViewById(R.id.answer);
            star = itemView.findViewById(R.id.gim);
        }
    }

    public interface OnItemClickListener {
        void OnClickMore(int position);
    }
}
