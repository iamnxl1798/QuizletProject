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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizlet.R;
import com.example.quizlet.model.Answers;
import com.example.quizlet.model.Courses;
import com.example.quizlet.model.customModel.Course_AnswerCount;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.Holder> implements Filterable {
    private List<Course_AnswerCount> items;
    private List<Course_AnswerCount> allItems;
    private Context context;

    public CourseAdapter(List<Course_AnswerCount> items, Context context) {
        this.items = items;
        allItems=new ArrayList<>(items);
        this.context = context;
    }
    public List<Course_AnswerCount> getItems() {
        return items;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CourseAdapter.Holder(LayoutInflater.from(context).inflate(R.layout.course_item,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        DateFormat df = new SimpleDateFormat("hh:mm dd/MM/yyyy");
        TextView course=holder.getCourse();
        TextView term=holder.getTerm();
        TextView creator=holder.getCreator();
        course.setText(items.get(position).getCourseName());
        term.setText("Terms: "+items.get(position).getAnswerNum());
        creator.setText("Created date: "+df.format(new Date(items.get(position).getCreatorDate())));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public Filter getFilter() {
        return myFilter;
    }
    Filter myFilter = new Filter() {

        //Automatic on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<Course_AnswerCount> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(allItems);
            } else {
                for (Course_AnswerCount item: allItems) {
                    if (item.getCourseName().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        //Automatic on UI thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            items.clear();
            items.addAll((Collection<? extends Course_AnswerCount>) filterResults.values);
            notifyDataSetChanged();
        }
    };
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
