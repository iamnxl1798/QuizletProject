package com.example.quizlet.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.quizlet.model.Courses;
import com.example.quizlet.model.JoinedCourses;

import java.util.List;

@Dao
public interface JoinedCouesesDAO {
    @Query("select Courses.id,Courses.name,Courses.createDate from JoinedCourses,Courses where JoinedCourses.userId = :idUser")
    public List<Courses> getAllJoinedCoursesByUser(int idUser);
}
