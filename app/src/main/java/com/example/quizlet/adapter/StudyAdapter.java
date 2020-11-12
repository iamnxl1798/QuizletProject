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

import com.example.quizlet.model.Answers;
import com.example.quizlet.model.Item;
import com.example.quizlet.model.Question;
import com.example.quizlet.R;

import java.util.List;

public class StudyAdapter extends RecyclerView.Adapter<StudyAdapter.ViewHolder> {

    private Context context;
    private List<Item> items;
    private StudyAdapter.OnItemClickListener listener;

    public StudyAdapter(Context context, List<Item> questions, StudyAdapter.OnItemClickListener listener) {
        this.context = context;
        this.items = questions;
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
        final Item item = items.get(position);

        List<Answers> answers = item.getDefinition();
        String quesstionName = item.getTerm();
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
        holder.nameQuestion.setText(quesstionName);

        holder.answer.setText(anwserTrue);
        holder.star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnClickMore(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
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
