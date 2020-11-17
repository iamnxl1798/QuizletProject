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
    private ArrayList<Answers> getAnswers1;
    private Context getContext;
    private ArrayList<Answers> getAnswers;

    public AnswerAdapter(ArrayList<Answers> getAnswers1, Context getContext, ArrayList<Answers> getAnswers) {
        this.getAnswers1 = getAnswers1;
        this.getContext = getContext;
        this.getAnswers = getAnswers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(getContext).inflate(R.layout.the_answers_custom, parent, false));
    }

    //All answers are chose of a question
    public static ArrayList<AnswersOfQuestion> getAnswersAreChoseOfAllQuestions = new ArrayList<>();


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final int[] markColor = {0};
        holder.tvAnswer.setText(getAnswers1.get(position).getAnswer());
        holder.tvAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (markColor[0] == 0) {
                    holder.tvAnswer.setTextColor(Color.WHITE);
                    holder.tvAnswer.setBackgroundColor(Color.RED);
                    markColor[0] = 1;
                } else if (markColor[0] == 1) {
                    holder.tvAnswer.setTextColor(Color.BLACK);
                    holder.tvAnswer.setBackgroundColor(Color.WHITE);
                    markColor[0] = 0;
                }


                //handle to take id of answer by question
                for (int i = 0; i < getAnswers.size(); i++) {
                    if (getAnswers.get(i).getId() == getAnswers1.get(position).getId()) {
                        //    Toast.makeText(getContext, "AnswerId = " + getAnswers.get(position).getId(), Toast.LENGTH_SHORT).show();
                        int markUpdate = 0;
                        for (int j = 0; j < getAnswersAreChoseOfAllQuestions.size(); j++) {
                            if ( getAnswersAreChoseOfAllQuestions.get(j).getQuestionId() == getAnswers1.get(position).getQuestionId()
                                    && getAnswersAreChoseOfAllQuestions.get(j).getAnswerId() == getAnswers1.get(position).getId()) {
                                markUpdate= 1; // update
                                break;
                            }
                        }
                        if (markUpdate == 0) { // add
                            AnswersOfQuestion a = new AnswersOfQuestion();
                            a.setAnswerId(getAnswers1.get(position).getId());
                            a.setQuestionId(getAnswers1.get(position).getQuestionId());
                            getAnswersAreChoseOfAllQuestions.add(a);
                        }
                        break;
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return getAnswers1.size();
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
