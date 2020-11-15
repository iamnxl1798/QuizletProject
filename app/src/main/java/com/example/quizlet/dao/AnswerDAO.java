package com.example.quizlet.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.quizlet.model.Answers;

import java.util.List;

@Dao
public interface AnswerDAO {
    @Query("Select * from Answers where questionId =:idQuestion")
    public List<Answers> getAnswerByQuestion(long idQuestion);

    @Query("SELECT * FROM Answers")
    List<Answers> getAnswersList();
}
