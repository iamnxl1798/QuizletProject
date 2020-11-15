package com.example.quizlet.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.quizlet.model.Question;

import java.util.List;

@Dao
public interface QuesstionDAO {
    @Query("SELECT * FROM Question where courseId = :idCourse")
    public List<Question> getAllQuesstionByCourseId(long idCourse);

    @Query(("SELECT * FROM Question"))
    List<Question> listAll();
}
