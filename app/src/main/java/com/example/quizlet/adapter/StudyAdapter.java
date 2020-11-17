package com.example.quizlet.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.quizlet.COMMON;
import com.example.quizlet.dao.ImportQuestionDAO;
import com.example.quizlet.dao.JoinedCouesesDAO;
import com.example.quizlet.database.MyDatabase;
import com.example.quizlet.model.Answers;
import com.example.quizlet.model.ImportantQuestions;
import com.example.quizlet.model.Item;
import com.example.quizlet.model.JoinedCourses;
import com.example.quizlet.model.Question;
import com.example.quizlet.R;

import java.util.List;

public class StudyAdapter extends RecyclerView.Adapter<StudyAdapter.ViewHolder> {

    private Context context;
    private List<Item> items;
    private StudyAdapter.OnItemClickListener listener;
    JoinedCouesesDAO joinedCouesesDAO;
    MyDatabase myDatabase;
    ImportQuestionDAO importQuestionDAO;
    long joinCourseId;
    ImageView imageView;

    public StudyAdapter(Context context, List<Item> questions, long joinCourseId, StudyAdapter.OnItemClickListener listener) {
        this.context = context;
        this.items = questions;
        this.listener = listener;
        this.joinCourseId = joinCourseId;
        myDatabase = Room.databaseBuilder(context, MyDatabase.class, COMMON.DB_NAME).allowMainThreadQueries().build();

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
        joinedCouesesDAO = myDatabase.createJoinCourseDAO();
        importQuestionDAO = myDatabase.createImportQuestionDAO();
        List<Answers> answers = item.getDefinition();
        String quesstionName = item.getTerm().getQuestionName();
        String s = "";
        String anwserTrue = "";
        int a = 97;
        ImportantQuestions importantQuestions = importQuestionDAO.checkQuestion(joinCourseId, item.getTerm().getId());
        if (importantQuestions != null) {
            holder.star.setImageResource(R.drawable.saogim);
        } else {
            holder.star.setImageResource(R.drawable.star);

        }
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
                listener.OnClickMore(item.getTerm().getId());
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
        void OnClickMore(long idQuestion);
    }
}
