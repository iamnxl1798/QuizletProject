package com.example.quizlet.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.quizlet.model.Courses;
import com.example.quizlet.model.Question;

import java.util.List;
@Dao
public interface ImportQuestionDAO {
    @Query("select Courses.id,Courses.name,Courses.createDate from JoinedCourses,Courses where JoinedCourses.userId = :idUser")
    public List<Courses> getAllJoinedCoursesByUser(int idUser);

    @Query("select q.id as id, q.courseId as courseId,q.questionName as questionName from importantquestions as i,Question as q where q.id= i.questionId and i.joinCourseId=:idJoinCourse")
    public List<Question> getQuestionSao(long idJoinCourse);
}
