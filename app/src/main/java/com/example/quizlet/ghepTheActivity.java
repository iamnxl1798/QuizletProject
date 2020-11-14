package com.example.quizlet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.quizlet.adapter.CourseAdapter;
import com.example.quizlet.dao.CourseDAO;
import com.example.quizlet.database.MyDatabase;
import com.example.quizlet.model.customModel.Course_AnswerCount;

import java.util.ArrayList;
import java.util.List;

public class ghepTheActivity extends AppCompatActivity {
    private RecyclerView myCourseRcl,allCourseRcl;
    private SearchView searchAllCourse, searchMyCourse;
    public CourseDAO courseDAO;
    private CourseAdapter myCourse,allCourse;
    public MyDatabase myDatabase;
    private List<Course_AnswerCount> myCourseList,allCourseList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghep_the);
        Intent incomeIntent=getIntent();
        final long oldCourseID=incomeIntent.getLongExtra("idCourse",-1);
        myCourseRcl=findViewById(R.id.myCourseRcl);
        allCourseRcl=findViewById(R.id.allCourseRcl);
        searchAllCourse=findViewById(R.id.searchAllCourse);
        searchMyCourse=findViewById(R.id.searchMyCourse);
        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, COMMON.DB_NAME).allowMainThreadQueries().build();
        courseDAO = myDatabase.createCourseDAO();

        myCourseList = courseDAO.getCoursesSearchView();
        allCourseList = courseDAO.getCoursesSearchView();

        myCourse=new CourseAdapter(myCourseList,getApplicationContext(), new CourseAdapter.OnItemClickListener() {
            @Override
            public void OnClickMore(Course_AnswerCount course_AnswerCount) {
                Intent intent = new Intent(getApplicationContext(), ghepTheChiTietActivity.class);
                intent.putExtra("idCourse", course_AnswerCount.getId());
                intent.putExtra("oldCourseID", oldCourseID);
                startActivity(intent);
            }
        });
        LinearLayoutManager myCourseManager= new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        myCourseManager.scrollToPosition(0);
        myCourseRcl.setLayoutManager(myCourseManager);
        myCourseRcl.setAdapter(myCourse);
        searchMyCourse.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ((CourseAdapter) myCourseRcl.getAdapter()).getFilter().filter(newText);
                return false;
            }
        });

        allCourse=new CourseAdapter(allCourseList,getApplicationContext());
        LinearLayoutManager allCourseManager= new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        allCourseManager.scrollToPosition(0);
        allCourseRcl.setLayoutManager(allCourseManager);
        allCourseRcl.setAdapter(myCourse);
        searchAllCourse.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ((CourseAdapter) allCourseRcl.getAdapter()).getFilter().filter(newText);
                return false;
            }
        });

    }
}