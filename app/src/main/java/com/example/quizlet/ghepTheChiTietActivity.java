package com.example.quizlet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizlet.adapter.CourseAdapter;
import com.example.quizlet.adapter.ItemAdapter;
import com.example.quizlet.dao.CourseDAO;
import com.example.quizlet.database.MyDatabase;
import com.example.quizlet.model.Answers;
import com.example.quizlet.model.Courses;
import com.example.quizlet.model.Item;
import com.example.quizlet.model.Question;
import com.example.quizlet.model.customModel.Course_AnswerCount;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ghepTheChiTietActivity extends AppCompatActivity {

    private ImageView checkBtn;
    private RecyclerView rcl_courseA,rcl_courseB;
    public CourseDAO courseDAO;
    private ItemAdapter courseA,courseB;
    public MyDatabase myDatabase;
    private List<Item> listA,listB, newCourseList;
    private FloatingActionButton addItemBtn;
    private TextView txtA,txtB;
    private EditText newCourseName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghep_the_chi_tiet);
        checkBtn=findViewById(R.id.checkBtn);
        rcl_courseA=findViewById(R.id.rclA);
        rcl_courseB=findViewById(R.id.rclB);
        txtA=findViewById(R.id.courseA);
        txtB=findViewById(R.id.courseB);
        newCourseName=findViewById(R.id.newCourseName);
        Intent intent=getIntent();
        long id_A=intent.getLongExtra("oldCourseID",-1);
        long id_B=intent.getLongExtra("idCourse",-1);
        if(id_A!=-1&&id_B!=-1){
            myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, COMMON.DB_NAME).allowMainThreadQueries().build();
            courseDAO = myDatabase.createCourseDAO();
            listA = new ArrayList<>();
            List<Question> termA=courseDAO.getQuestionByCourseID(id_A);
            txtA.setText(courseDAO.getCourseByID(id_A).getName());
            for (Question item:termA){
                listA.add(new Item(
                        item,
                        courseDAO.getAnswerByQuestionID(item.getId())
                ));
            }
            courseA=new ItemAdapter(listA,getApplicationContext());
            rcl_courseA.setAdapter(courseA);
            listB = new ArrayList<>();
            List<Question> termB=courseDAO.getQuestionByCourseID(id_B);
            txtB.setText(courseDAO.getCourseByID(id_B).getName());
            for (Question item:termB){
                listB.add(new Item(
                        item,
                        courseDAO.getAnswerByQuestionID(item.getId())
                ));
            }
            courseB=new ItemAdapter(listB,getApplicationContext());
            rcl_courseB.setAdapter(courseB);
        }
        addItemBtn = findViewById(R.id.addItemBtn);
        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listB = ((ItemAdapter) rcl_courseB.getAdapter()).getItems();
                List<Answers> tempString = new ArrayList<>();
                tempString.add(new Answers());
                listB.add(new Item(new Question(), tempString));
                rcl_courseB.setAdapter(courseB);
                rcl_courseB.smoothScrollToPosition(listB.size()-1);
            }
        });
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(!newCourseName.getText().toString().isEmpty()){
                        newCourseName.setBackgroundColor(Color.WHITE);
                        listA = ((ItemAdapter) rcl_courseA.getAdapter()).getItems();
                        listB = ((ItemAdapter) rcl_courseB.getAdapter()).getItems();
                        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, COMMON.DB_NAME).allowMainThreadQueries().build();
                        courseDAO = myDatabase.createCourseDAO();
                        Date currentTime = Calendar.getInstance().getTime();
                        Courses courses = new Courses(newCourseName.getText().toString(), currentTime.getTime());
                        courseDAO.insertCourse(courses);
                        for(Item item:listA){
                            if(!item.getTerm().getQuestionName().isEmpty()){
                                courses= courseDAO.getLastCourse();
                                courseDAO.insertQuestion(new Question(item.getTerm().getQuestionName(), courses.getId()));
                                Question addQuestion=courseDAO.getLastQuestion();
                                List<Answers> tempA= new ArrayList<>();
                                for(Answers answer:item.getDefinition()){
                                    if(answer.getAnswer()==null||answer.getAnswer().isEmpty()){
                                        tempA.add(answer);
                                    }
                                    else {
                                        answer.setQuestionId(addQuestion.getId());
                                        courseDAO.insertAnswer(new Answers(answer.getAnswer(),answer.isTrue(),addQuestion.getId()));
                                    }
                                }
                                if(item.getDefinition().size()==tempA.size()){
                                    courseDAO.delLastQuestion();
                                }
                            }
                        }
                        for(Item item:listB){
                            if(!item.getTerm().getQuestionName().isEmpty()){
                                courses= courseDAO.getLastCourse();
                                courseDAO.insertQuestion(new Question(item.getTerm().getQuestionName(), courses.getId()));
                                Question addQuestion=courseDAO.getLastQuestion();
                                List<Answers> tempB= new ArrayList<>();
                                for(Answers answer:item.getDefinition()){
                                    if(answer.getAnswer()==null||answer.getAnswer().isEmpty()){
                                        tempB.add(answer);
                                    }
                                    else {
                                        answer.setQuestionId(addQuestion.getId());
                                        courseDAO.insertAnswer(new Answers(answer.getAnswer(),answer.isTrue(),addQuestion.getId()));
                                    }
                                }
                                if(item.getDefinition().size()==tempB.size()){
                                    courseDAO.delLastQuestion();
                                }
                            }
                        }
                        if(courseDAO.getQuestionOfLastCourse().size()==0){
                            courseDAO.delLastCourse();
                            throw new Exception("Course cannot be blank");
                        }
                        Toast.makeText(getApplicationContext(), "Add " + courses.getName() + " course success!", Toast.LENGTH_LONG).show();
                        checkBtn.setEnabled(false);
                        checkBtn.setBackground(null);
                    }else{
                        newCourseName.setBackgroundColor(Color.rgb(251,227,228));
                        Toast.makeText(getApplicationContext(), "Add course failed!", Toast.LENGTH_LONG).show();
                        courseDAO.delLastCourse();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    courseDAO.delLastCourse();
                }
            }
        });
    }
}