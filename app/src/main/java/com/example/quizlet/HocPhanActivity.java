package com.example.quizlet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.quizlet.adapter.CourseAdapter;
import com.example.quizlet.dao.CourseDAO;
import com.example.quizlet.database.MyDatabase;
import com.example.quizlet.model.Question;
import com.example.quizlet.model.customModel.Course_AnswerCount;

import java.util.List;

public class HocPhanActivity extends AppCompatActivity {

    private MyDatabase myDatabase;
    private CourseDAO courseDAO;
    private RecyclerView recyclerView;
    private List<Course_AnswerCount> items;
    private CourseAdapter adapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoc_phan);
        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, COMMON.DB_NAME).allowMainThreadQueries().build();
        courseDAO = myDatabase.createCourseDAO();
        items = courseDAO.getCoursesSearchView();
        List<Question> list = courseDAO.getQuestion();
        recyclerView = findViewById(R.id.searchCouseRecycle);
        adapter = new CourseAdapter(items, getApplicationContext(), new CourseAdapter.OnItemClickListener() {
            @Override
            public void OnClickMore(Course_AnswerCount course_AnswerCount) {
                Intent intent = new Intent(getApplicationContext(), StudyActivity.class);
                intent.putExtra("idCourse", course_AnswerCount.getId());
                intent.putExtra("totalQuestion", course_AnswerCount.getAnswerNum());
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "" + course_AnswerCount.getId(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
        searchView = findViewById(R.id.searchBar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ((CourseAdapter) recyclerView.getAdapter()).getFilter().filter(newText);
                return false;
            }
        });
    }
}