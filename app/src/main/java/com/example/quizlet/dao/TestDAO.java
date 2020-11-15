package com.example.quizlet.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.quizlet.model.Question;

import java.util.List;

@Dao
public interface TestDAO {
    @Query("SELECT * FROM Question")
    public List<Question> getQuestion();

//    @Query("SELECT q.questionName , a.answer , a.isTrue FROM Question As q ,Answers As a where q.id = a.questionId ")
//    public List<Answers> getAnswers();
}
