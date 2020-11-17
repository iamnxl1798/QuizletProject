package com.example.quizlet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.quizlet.adapter.ItemAdapter;
import com.example.quizlet.dao.CourseDAO;
import com.example.quizlet.database.MyDatabase;
import com.example.quizlet.model.Answers;
import com.example.quizlet.model.Courses;
import com.example.quizlet.model.Item;
import com.example.quizlet.model.Question;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EditCourseActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Item> items,updateItems;
    private ItemAdapter adapter;
    private Button checkBtn;
    private FloatingActionButton addItemBtn;
    public MyDatabase myDatabase;
    private CourseDAO addCourseDAO;
    private EditText edit_category;
    private long idCourse;
    private Courses courses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
        idCourse=getIntent().getLongExtra("idCourse",-1);
        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, COMMON.DB_NAME).allowMainThreadQueries().build();
        addCourseDAO = myDatabase.createCourseDAO();
        courses=addCourseDAO.getCourseByID(idCourse);
        items = new ArrayList<>();
        updateItems=new ArrayList<>();
        List<Question> qTemp= addCourseDAO.getQuestionByCourseID(courses.getId());
        for(Question q:qTemp){
            items.add(new Item(
                    q,
                    addCourseDAO.getAnswerByQuestionID(q.getId())
            ));
        }
        for(Item item:items){
            List<Answers> ansTemp=new ArrayList<>();
            for(Answers answers:item.getDefinition()){
                ansTemp.add(answers);
            }
            updateItems.add(new Item(
                    item.getTerm(),
                    ansTemp
            ));
        }
        recyclerView = findViewById(R.id.rcl_view);
        adapter = new ItemAdapter(updateItems, getApplicationContext());
        recyclerView.setAdapter(adapter);
        edit_category = findViewById(R.id.edit_category);
        edit_category.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!edit_category.getText().toString().isEmpty()){
                    edit_category.setBackgroundColor(Color.WHITE);
                }
            }
        });
        edit_category.setText(courses.getName());
        checkBtn = findViewById(R.id.checkBtn);
        checkBtn.setEnabled(true);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(!edit_category.getText().toString().isEmpty()){
                        edit_category.setBackgroundColor(Color.WHITE);
                        courses.setName(edit_category.getText().toString());
                        addCourseDAO.updateCourse(courses);
                        for(Item item:updateItems){
                            boolean questionExist=false;
                            Item itemExist=null;
                            for(Item i:items){
                                if(i.getTerm().getId()==item.getTerm().getId()){
                                    questionExist=true;
                                    itemExist=i;
                                    break;
                                }
                            }

                            if(questionExist){
                                    if(item.getTerm().getQuestionName().isEmpty()){
                                        addCourseDAO.deleteQuestion(item.getTerm());
                                    }
                                    else{
                                        addCourseDAO.updateQuestion(item.getTerm());
                                        List<Answers> temp= new ArrayList<>();
                                        boolean answerExist=false;
                                        for(Answers answer:item.getDefinition()){
                                            if(answer.getAnswer()==null){
                                                continue;
                                            }
                                            else if(answer.getAnswer().isEmpty()){
                                                addCourseDAO.deleteAnswer(answer);
                                                continue;
                                            }
                                            for(Answers a: itemExist.getDefinition()){
                                                if(answer.getId()==a.getId()){
                                                    answerExist=true;
                                                    break;
                                                }
                                                else{
                                                    answerExist=false;
                                                }
                                            }
                                            if(answer.getAnswer()==null||answer.getAnswer().isEmpty()){
                                                temp.add(answer);
                                            }
                                            else {
                                                if(answerExist){
                                                    addCourseDAO.updateAnswer(answer);
                                                }
                                                else{
                                                    answer.setQuestionId(item.getTerm().getId());
                                                    addCourseDAO.insertAnswer(answer);
                                                }
                                            }
                                        }
                                        if(item.getDefinition().size()==temp.size()){
                                            addCourseDAO.deleteQuestion(item.getTerm());
                                        }
                                    }
                            }
                            else{
                                if(!item.getTerm().getQuestionName().isEmpty()){
                                    courses= addCourseDAO.getLastCourse();
                                    addCourseDAO.insertQuestion(new Question(item.getTerm().getQuestionName(), courses.getId()));
                                    Question addQuestion=addCourseDAO.getLastQuestion();
                                    List<Answers> temp= new ArrayList<>();
                                    for(Answers answer:item.getDefinition()){
                                        if(answer.getAnswer()==null||answer.getAnswer().isEmpty()){
                                            temp.add(answer);
                                        }
                                        else {
                                            answer.setQuestionId(addQuestion.getId());
                                            addCourseDAO.insertAnswer(answer);
                                        }
                                    }
                                    if(item.getDefinition().size()==temp.size()){
                                        addCourseDAO.delLastQuestion();
                                    }
                                }
                            }
                        }
                        for(Item item:items){
                            boolean delQuestion=true;
                            for(Item i:updateItems){
                                if(i.getTerm().getId()==item.getTerm().getId()){
                                    delQuestion=false;
                                    break;
                                }
                            }
                            if(delQuestion){
                                addCourseDAO.deleteQuestion(item.getTerm());
                            }
                        }
                        Toast.makeText(getApplicationContext(), "Update " + courses.getName() + " course success!", Toast.LENGTH_LONG).show();
                        checkBtn.setEnabled(false);
                        checkBtn.setBackground(null);
                    }else{
                        edit_category.setBackgroundColor(Color.rgb(251,227,228));
                        Toast.makeText(getApplicationContext(), "Update course failed!", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        addItemBtn = findViewById(R.id.addItemBtn);
        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateItems = ((ItemAdapter) recyclerView.getAdapter()).getItems();
                List<Answers> tempString = new ArrayList<>();
                tempString.add(new Answers());
                updateItems.add(new Item(new Question(), tempString));
                recyclerView.setAdapter(adapter);
                recyclerView.smoothScrollToPosition(updateItems.size()-1);

            }
        });
    }
}