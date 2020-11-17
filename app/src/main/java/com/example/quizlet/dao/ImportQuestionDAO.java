package com.example.quizlet.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.quizlet.model.Courses;
import com.example.quizlet.model.ImportantQuestions;
import com.example.quizlet.model.JoinedCourses;
import com.example.quizlet.model.Question;

import java.util.List;

@Dao
public interface ImportQuestionDAO {
    @Query("select Courses.id,Courses.name,Courses.createDate,Courses.creatorID from JoinedCourses,Courses where JoinedCourses.userId = :idUser")
    public List<Courses> getAllJoinedCoursesByUser(int idUser);

    @Query("select q.id as id, q.courseId as courseId,q.questionName as questionName from importantquestions as i,Question as q where q.id= i.questionId and i.joinCourseId=:idJoinCourse")
    public List<Question> getQuestionSao(long idJoinCourse);

    @Query(("select * from importantquestions where joinCourseId =:idJOinCouse and questionId =:idQuestion"))
    public ImportantQuestions checkQuestion(long idJOinCouse, long idQuestion);

    @Insert
    public void insert(ImportantQuestions importantQuestions);

    @Delete
    public void delete(ImportantQuestions importantQuestions);

    @Query("select * from importantquestions where joinCourseId =:idJoin")
    public List<ImportantQuestions> countQuestionImport(long idJoin);
}
