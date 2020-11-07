package com.example.quizlet.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.quizlet.model.Answers;
import com.example.quizlet.model.Courses;
import com.example.quizlet.model.Question;
import com.example.quizlet.model.User;

import java.util.List;

@Dao
public interface AddCourseDAO {
    @Insert
    public void insertCourse(Courses course);
    @Insert
    public void insertQuestion(Question question);
    @Insert
    public void insertAnswer(Answers course);

    @Query("SELECT * FROM Courses")
    public List<Courses> getCourses();
    @Query("SELECT * FROM Question ORDER BY Question.id DESC LIMIT 1;")
    public Question getLastesQuestion();
}
