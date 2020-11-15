package com.example.quizlet.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizlet.R;
import com.example.quizlet.model.Answers;
import com.example.quizlet.model.AnswersOfQuestion;

import java.util.ArrayList;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder> {
    public static int count = 0;
    private ArrayList<Answers> getAnswers;
    private Context getContext;
    private ArrayList<Answers> getAnswers1;

    public AnswerAdapter(ArrayList<Answers> getAnswers, Context getContext, ArrayList<Answers> getAnswers1) {
        this.getAnswers = getAnswers;
        this.getContext = getContext;
        this.getAnswers1 = getAnswers1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(getContext).inflate(R.layout.the_answers_custom, parent, false));
    }

    public static ArrayList<AnswersOfQuestion> answersOfQuestions = new ArrayList<>();


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
//        holder.tvAnswer.setTextColor(Color.BLACK);
//        holder.tvAnswer.setBackgroundColor(Color.WHITE);
        final int[] markColor = {0};
        holder.tvAnswer.setText(getAnswers.get(position).getAnswer());
        holder.tvAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                if (markColor[0] == 0) {
                    holder.tvAnswer.setTextColor(Color.WHITE);
                    holder.tvAnswer.setBackgroundColor(Color.RED);
                    markColor[0] = 1;
                   // Toast.makeText(v.getContext(), "Mark = " + markColor[0], Toast.LENGTH_SHORT).show();
                } else if (markColor[0] == 1) {
                    holder.tvAnswer.setTextColor(Color.BLACK);
                    holder.tvAnswer.setBackgroundColor(Color.WHITE);
                    markColor[0] = 0;
                   //Toast.makeText(v.getContext(), "Mark = " + markColor[0], Toast.LENGTH_SHORT).show();
                }


                //handle to take id of answer by question
                for (int i = 0; i < getAnswers1.size(); i++) {
                    if (getAnswers1.get(i).getId() == getAnswers.get(position).getId()) {
                        //    Toast.makeText(getContext, "AnswerId = " + getAnswers.get(position).getId(), Toast.LENGTH_SHORT).show();
                        int markUpdateOrAdd = 0;
                        for (int j = 0; j < answersOfQuestions.size(); j++) {
                            if (answersOfQuestions.get(j).getAnswerId() == getAnswers.get(position).getId()
                                    && answersOfQuestions.get(j).getQuestionId() == getAnswers.get(position).getQuestionId()) {
                                markUpdateOrAdd = 1;
                               // Toast.makeText(getContext, "Update", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }
                        if (markUpdateOrAdd == 0) {
                            AnswersOfQuestion a = new AnswersOfQuestion();
                            a.setAnswerId(getAnswers.get(position).getId());
                            a.setQuestionId(getAnswers.get(position).getQuestionId());
                            answersOfQuestions.add(a);
                        }
                        break;
                    }
                }
                //Toast.makeText(getContext, "Answer " + (position + 1), Toast.LENGTH_SHORT).show();
                boolean isTrue = getAnswers.get(position).isTrue();
                if (isTrue) {
                    count++;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return getAnswers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvAnswer;
        int mark = 0;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAnswer = itemView.findViewById(R.id.tvAnswer);
            tvAnswer.setTextColor(Color.BLACK);
            tvAnswer.setBackgroundColor(Color.WHITE);
        }
    }
}
