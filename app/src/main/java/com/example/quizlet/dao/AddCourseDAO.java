package com.example.quizlet.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.quizlet.model.Answers;
import com.example.quizlet.model.Courses;
import com.example.quizlet.model.User;

@Dao
public interface AddCourseDAO {
    @Insert
    public void insertCourse(Courses course);
    @Insert
    public void insertAnswer(Answers course);
}
