package com.example.quizlet.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizlet.R;
import com.example.quizlet.model.Answers;
import com.example.quizlet.model.Courses;
import com.example.quizlet.model.customModel.Course_AnswerCount;

import java.util.ArrayList;
import java.util.Date;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.Holder> {
    ArrayList<Course_AnswerCount> items;
    Context context;

    public CourseAdapter(ArrayList<Course_AnswerCount> items, Context context) {
        this.items = items;
        this.context = context;
    }
    public ArrayList<Course_AnswerCount> getItems() {
        return items;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CourseAdapter.Holder(LayoutInflater.from(context).inflate(R.layout.course_item,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        TextView course=holder.getCourse();
        TextView term=holder.termNumber;
        TextView creator=holder.creatorName;
        course.setText(items.get(position).getCourses().getName());
        term.setText(items.get(position).getAnswerNum());
        course.setText(new Date(items.get(position).getCourses().getCreateDate()*1000).toString());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView courseName,termNumber,creatorName;
        public Holder(@NonNull View itemView) {
            super(itemView);
            courseName=itemView.findViewById(R.id.tv_CourseName);
            termNumber=itemView.findViewById(R.id.tv_TermNumber);
            creatorName=itemView.findViewById(R.id.tv_creatorName);
        }
        public TextView getCourse(){
            return courseName;
        }
        public TextView getTerm(){
            return termNumber;
        }
        public TextView getCreator(){
            return creatorName;
        }
    }
}
