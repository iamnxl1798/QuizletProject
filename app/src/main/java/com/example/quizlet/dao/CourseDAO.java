package com.example.quizlet.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.quizlet.model.Answers;
import com.example.quizlet.model.Courses;
import com.example.quizlet.model.Question;
import com.example.quizlet.model.User;
import com.example.quizlet.model.customModel.Course_AnswerCount;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CourseDAO {
    @Insert
    public void insertCourse(Courses course);
    @Insert
    public void insertQuestion(Question question);
    @Insert
    public void insertAnswer(Answers course);

    @Query("SELECT * FROM Courses")
    public List<Courses> getCourses();

    @Query("SELECT * FROM Question")
    public List<Question> getQuestion();

    @Query("SELECT * FROM Question ORDER BY Question.id DESC LIMIT 1;")
    public Question getLastesQuestion();

    @Query("SELECT * FROM Courses ORDER BY Courses.id DESC LIMIT 1;")
    public Courses getLastesCourse();

    @Query("SELECT Courses.name as courseName, Courses.createDate as creatorDate, Count(Question.id) as answerNum FROM Courses, Question where Question.courseId=Courses.id Group by Question.courseId")
    public List<Course_AnswerCount> getCoursesSearchView();
}
